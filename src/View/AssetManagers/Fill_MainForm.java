package View.AssetManagers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.PHUKIEN;
import DAO.TAISAN;
import View.AssetManagers.Wait.Wait;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

public class Fill_MainForm {
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private Shell shell;
	private Wait w;
	private static Log log = LogFactory.getLog(Fill_MainForm.class);

	public Fill_MainForm(NGUOIDUNG user, Shell shell) {
		controler = new Controler(user);
		this.shell = shell;
	}

	public void getData_viewMainForm_Lichsu_Suachua(Integer MaTaiSan, Table table) throws Exception {
		table.clearAll();
		table.removeAll();
		if (table.getListeners(SWT.SetData) != null)
			for (Listener l : table.getListeners(SWT.SetData)) {
				table.removeListener(SWT.SetData, l);
			}
		ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> cviec_list = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
				.get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(MaTaiSan);
		if (cviec_list != null) {
			int COUNT = cviec_list.size();
			table.addListener(SWT.SetData, new Listener() {
				private DOT_THUCHIEN_SUACHUA_BAODUONG c;

				public void handleEvent(Event event) {
					try {
						TableItem parent = (TableItem) event.item;
						int index = event.index;
						c = cviec_list.get(index);
						parent.setText(0, "" + (index + 1));
						parent.setText(1, ((c == null) ? "-" : c.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG()));
						DE_XUAT dx;
						dx = controler.getControl_DEXUAT().get_DEXUAT(c);

						parent.setText(2, dx == null ? "-" : mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()));
						GIAI_DOAN_QUYET_TOAN q = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(c);
						if (q != null)
							parent.setText(3, q.getTHOI_GIAN_KET_THUC() == null ? "chưa hoàn thành"
									: mdf.getViewStringDate(q.getTHOI_GIAN_KET_THUC()));
						else
							parent.setText(3, "-");
					} catch (SQLException e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			});

			table.setItemCount(COUNT);
		}
		for (TableColumn tc : table.getColumns())
			tc.pack();
	}

	public void loadData_ViewTaiSan_MainForm(Tree tree, ArrayList<TAISAN> row, String ItemIndex) {
		if (tree.isVisible() && row != null) {
			if (row.size() > 1000) {
				if (w != null)
					w.dispose();
				w = new Wait(shell.getDisplay());
				new FormTemplate().setRightScreen(w.getShell());
				w.open();
			}
			if (tree != null) {
				if (tree.getListeners(SWT.SetData) != null)
					for (Listener lst : tree.getListeners(SWT.SetData)) {
						tree.removeListener(SWT.SetData, lst);
					}
				tree.removeAll();
				int COUNT = row.size();
				if (COUNT > 0) {
					tree.addListener(SWT.SetData, new Listener() {
						public void handleEvent(Event event) {
							TreeItem parent = (TreeItem) event.item;
							int index = event.index;
							TAISAN t = row.get(index);
							parent.setText(new String[] { (index + 1) + "", t.getTEN_TAISAN(),
									t.getMODEL().equals("null") ? "--" : t.getMODEL(),
									t.getDonvi_Sudung().getTEN_PHONGBAN(), mdf.getViewStringDate(t.getNGAY_SU_DUNG()),
									t.getSERI().equals("null") ? "--" : t.getSERI(), t.getMA_TAISAN() + "" });
							parent.setData(t);
							ArrayList<PHUKIEN> Childrow = t.getPhukienList();
							if (Childrow != null) {
								for (int i = 0; i < Childrow.size(); i++) {
									PHUKIEN pk = Childrow.get(i);
									TreeItem child = new TreeItem(parent, SWT.NONE);
									child.setText(new String[] { String.valueOf(i + 1), pk.getTEN_PHUKIEN(),
											pk.getMODEL().equals("null") ? "--" : pk.getMODEL(), "", "",
											pk.getSERI().equals("null") ? "--" : pk.getSERI(),
											String.valueOf(pk.getMA_PHUKIEN()) });
									child.setData("PrimaryKey_Phukien", pk.getMA_PHUKIEN());
								}
							}
							parent.setExpanded(true);
						}
					});
				}
				tree.setItemCount(COUNT);

				if (COUNT < 1000)
					for (TreeColumn tc : tree.getColumns())
						tc.pack();

				if (ItemIndex != null) {
					int index = Integer.valueOf(ItemIndex);
					if (index > 0 && index < tree.getItemCount()) {
						TreeItem treeItem = tree.getItem(index);
						tree.setSelection(treeItem);
					}
				}
			}

		} else {
			tree.removeAll();
		}
		if (w != null)
			w.dispose();
	}

	public void fillDataXemay(Table table_Xemay, ArrayList<TAISAN> tl) throws SQLException {
		table_Xemay.removeAll();
		if (tl != null) {
			int i = 1;
			for (TAISAN t : tl) {
				TableItem ti = new TableItem(table_Xemay, SWT.NONE);
				LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(t.getPhuongtien_Giaothong().getMA_LOAI_XE());
				ti.setText(new String[] { "" + i, t.getTEN_TAISAN(), t.getPhuongtien_Giaothong().getBIENSO(),
						lx.getHANG_SAN_XUAT(), lx.getTEN_DONG_XE(), t.getPhuongtien_Giaothong().getSOKHUNG(),
						t.getPhuongtien_Giaothong().getSOMAY() });
				ti.setData(t);
				i++;
			}
		}
	}

	public void fillDataOto(Table table_Oto, ArrayList<TAISAN> tl) throws SQLException {
		table_Oto.removeAll();
		if (tl != null) {
			int i = 1;
			for (TAISAN t : tl) {
				TableItem ti = new TableItem(table_Oto, SWT.NONE);
				LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(t.getPhuongtien_Giaothong().getMA_LOAI_XE());
				ti.setText(new String[] { "" + i, t.getTEN_TAISAN(), t.getPhuongtien_Giaothong().getBIENSO(),
						lx.getHANG_SAN_XUAT(), lx.getTEN_DONG_XE(), t.getPhuongtien_Giaothong().getSOKHUNG(),
						t.getPhuongtien_Giaothong().getSOMAY() });
				ti.setData(t);
				i++;
			}
		}
	}

}
