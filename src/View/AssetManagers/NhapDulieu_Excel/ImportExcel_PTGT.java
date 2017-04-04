package View.AssetManagers.NhapDulieu_Excel;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import View.AssetManagers.NhapDulieu_Excel.ExcelReader_ImportPTGT.ExcelRow_PTGT;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import jxl.read.biff.BiffException;

import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ImportExcel_PTGT extends Shell {
	private Text text_File;
	private ArrayList<PHUONGTIEN_GIAOTHONG> result;
	private Tree tree;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static NGUOIDUNG user;
	private static Log log = LogFactory.getLog(ImportExcel_PTGT.class);
	private Combo combo_Dongxe;
	private Combo combo_Loaixe;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ImportExcel_PTGT shell = new ImportExcel_PTGT(display, user);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 * @throws SQLException
	 */

	public ImportExcel_PTGT(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(ImportExcel_PTGT.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		setLayout(new GridLayout(6, false));
		ImportExcel_PTGT.user = user;
		controler = new Controler(user);

		Label lblDanhSchTi = new Label(this, SWT.NONE);
		lblDanhSchTi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDanhSchTi.setText("Danh s\u00E1ch t\u00E0i s\u1EA3n:");

		text_File = new Text(this, SWT.BORDER);
		text_File.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

		Button btnChn = new Button(this, SWT.NONE);
		btnChn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE | SWT.CENTER);
				fd.setText("Chọn File Excel Dữ liệu tài sản (theo phòng)");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.xls", "*.xlsx" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				if (selected != null) {
					String[] files = fd.getFileNames();
					for (int i = 0, n = files.length; i < n; i++) {
						StringBuffer buf = new StringBuffer();
						buf.append(fd.getFilterPath());
						if (buf.charAt(buf.length() - 1) != File.separatorChar) {
							buf.append(File.separatorChar);
						}
						buf.append(files[i]);// get file
						try {
							String filename = buf.toString();
							text_File.setText(filename);
							ExcelReader_ImportPTGT ei = new ExcelReader_ImportPTGT(filename);
							ArrayList<ExcelReader_ImportPTGT.ExcelRow_PTGT> DanhsachPTGT = ei.getData();
							MessageBox m = new MessageBox(getShell());
							m.setText("Hoàn tất");
							m.setMessage("Đã nhận " + DanhsachPTGT.size() + " dòng dữ liệu");
							m.open();
							setResult(buildResult(DanhsachPTGT));
							fillTree(getResult());
						} catch (BiffException | IOException | SQLException e1) {
							if (e1.getMessage().equals("Unable to recognize OLE stream")) {
								MessageBox m = new MessageBox(getShell(), SWT.ERROR);
								m.setMessage("Hỗ trợ File Excel cho phiên bản Excel 2000 về trước");
								m.open();
							}
						}
					}
				}
			}

		});
		GridData gd_btnChn = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnChn.widthHint = 75;
		btnChn.setLayoutData(gd_btnChn);
		btnChn.setText("Ch\u1ECDn");

		Label lblLoiXe = new Label(this, SWT.NONE);
		lblLoiXe.setText("Loại xe:");

		combo_Loaixe = new Combo(this, SWT.READ_ONLY);
		combo_Loaixe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree.getSelection();
				if (til.length > 0 && getResult() != null) {
					ArrayList<PHUONGTIEN_GIAOTHONG> d = getResult();
					for (TreeItem ti : til) {
						int index = tree.indexOf(ti);
						PHUONGTIEN_GIAOTHONG t = (PHUONGTIEN_GIAOTHONG) ti.getData();
						int oto_xemay = (int) combo_Loaixe.getData(combo_Loaixe.getText());
						Fill_ItemData fi = new Fill_ItemData();
						try {
							if (oto_xemay == 1) {
								ArrayList<LOAI_XE> lx;
								lx = controler.getControl_LOAI_XE().get_AllLOAI_XE_OTO();
								fi.setComboBox_Dongxe(combo_Dongxe, lx);
							} else {
								ArrayList<LOAI_XE> lx = controler.getControl_LOAI_XE().get_AllLOAI_XE_XEMAY();
								fi.setComboBox_Dongxe(combo_Dongxe, lx);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						t.setLOAI_PHUONGTIEN_GIAOTHONG(oto_xemay);
						d.set(index, t);
					}
					setResult(d);
					try {
						fillTree(getResult());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		combo_Loaixe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblDngXe = new Label(this, SWT.NONE);
		lblDngXe.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDngXe.setText("Dòng xe:");

		combo_Dongxe = new Combo(this, SWT.READ_ONLY);
		combo_Dongxe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree.getSelection();
				if (til.length > 0 && getResult() != null) {
					ArrayList<PHUONGTIEN_GIAOTHONG> d = getResult();
					for (TreeItem ti : til) {
						int index = tree.indexOf(ti);
						PHUONGTIEN_GIAOTHONG t = (PHUONGTIEN_GIAOTHONG) ti.getData();
						LOAI_XE lx = (LOAI_XE) combo_Dongxe.getData(combo_Dongxe.getText());
						t.setMA_LOAI_XE(lx.getMA_LOAI_XE());
						d.set(index, t);
					}
					setResult(d);
					try {
						fillTree(getResult());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		combo_Dongxe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnToLoiXe = new Button(this, SWT.NONE);
		btnToLoiXe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Import_LOAIXE il = new Import_LOAIXE(getShell(), SWT.DIALOG_TRIM, user);
				il.open();
				try {
					init();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnToLoiXe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnToLoiXe.setText("Nhập Loại xe");

		tree = new Tree(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem ti[] = tree.getSelection();
				if (ti.length > 0) {
					PHUONGTIEN_GIAOTHONG pg = (PHUONGTIEN_GIAOTHONG) ti[0].getData();
					try {
						LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(pg.getMA_LOAI_XE());
						if (pg.getLOAI_PHUONGTIEN_GIAOTHONG() == 1) {
							combo_Loaixe.select(0);
							if (lx != null) {
								combo_Dongxe.setText(lx.getTEN_DONG_XE());
							} else {
								ArrayList<LOAI_XE> lxl = controler.getControl_LOAI_XE().get_AllLOAI_XE_OTO();
								new Fill_ItemData().setComboBox_Dongxe(combo_Dongxe, lxl);
							}
						} else if (pg.getLOAI_PHUONGTIEN_GIAOTHONG() == 2) {
							combo_Loaixe.select(1);
							if (lx != null) {
								combo_Dongxe.setText(lx.getTEN_DONG_XE());
							} else {
								ArrayList<LOAI_XE> lxl = controler.getControl_LOAI_XE().get_AllLOAI_XE_XEMAY();
								new Fill_ItemData().setComboBox_Dongxe(combo_Dongxe, lxl);
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));

		TreeColumn trclmnStt = new TreeColumn(tree, SWT.NONE);
		trclmnStt.setWidth(50);
		trclmnStt.setText("STT");

		TreeColumn trclmnMTiSn_1 = new TreeColumn(tree, SWT.NONE);
		trclmnMTiSn_1.setWidth(100);
		trclmnMTiSn_1.setText("MÃ TÀI SẢN");

		TreeColumn treeColumn = new TreeColumn(tree, SWT.NONE);
		treeColumn.setWidth(150);
		treeColumn.setText("TÊN PTTS");

		TreeColumn trclmnTnPtts = new TreeColumn(tree, SWT.NONE);
		trclmnTnPtts.setWidth(207);
		trclmnTnPtts.setText("TÊN PHƯƠNG TIỆN GIAO THÔNG");

		TreeColumn trclmnBinS = new TreeColumn(tree, SWT.NONE);
		trclmnBinS.setWidth(100);
		trclmnBinS.setText("BI\u1EC2N S\u1ED0");

		TreeColumn trclmnNewColumn = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn.setWidth(120);
		trclmnNewColumn.setText("NĂM SỬ DỤNG");

		TreeColumn trclmnSKhung = new TreeColumn(tree, SWT.NONE);
		trclmnSKhung.setWidth(100);
		trclmnSKhung.setText("S\u1ED0 KHUNG");

		TreeColumn trclmnSMy = new TreeColumn(tree, SWT.NONE);
		trclmnSMy.setWidth(100);
		trclmnSMy.setText("S\u1ED0 M\u00C1Y");

		TreeColumn trclmnMTiSn = new TreeColumn(tree, SWT.NONE);
		trclmnMTiSn.setWidth(150);
		trclmnMTiSn.setText("MÃ TÀI SẢN KẾ TOÁN");

		TreeColumn trclmnNewColumn_2 = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn_2.setWidth(100);
		trclmnNewColumn_2.setText("LOẠI XE");

		TreeColumn trclmnNewColumn_1 = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn_1.setWidth(100);
		trclmnNewColumn_1.setText("Ô TÔ - XE MÁY");

		Menu menu = new Menu(tree);
		tree.setMenu(menu);

		MenuItem mntmXa = new MenuItem(menu, SWT.NONE);
		mntmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree.getSelection();
				if (til.length > 0) {
					for (TreeItem ti : til) {
						PHUONGTIEN_GIAOTHONG pg = (PHUONGTIEN_GIAOTHONG) ti.getData();
						ti.dispose();
						result.remove(pg);
					}
					try {
						fillTree(result);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXa.setText("Xóa");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Button btnHonTt = new Button(this, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					for (PHUONGTIEN_GIAOTHONG pg : result) {
						controler.getControl_PHUONGTIEN_GIAOTHONG().insert_PHUONGTIEN_GIAOTHONG(pg);
					}
					getShell().dispose();
				} catch (Exception e2) {
					log.error(e2.getMessage());
				}
			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Ho\u00E0n t\u1EA5t");

		Button btnng = new Button(this, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		btnng.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnng.setText("\u0110\u00F3ng");
		createContents();
	}

	protected ArrayList<PHUONGTIEN_GIAOTHONG> buildResult(ArrayList<ExcelRow_PTGT> danhsachPTGT) throws SQLException {
		// tree.removeAll();
		ArrayList<PHUONGTIEN_GIAOTHONG> pgl = new ArrayList<>();
		for (ExcelReader_ImportPTGT.ExcelRow_PTGT t : danhsachPTGT) {
			TAISAN ts = controler.getControl_TAISAN().getFlashTaisan_MaLienKet(t.getMaTaisanLienket());
			if (ts != null) {
				PHUONGTIEN_GIAOTHONG pg = new PHUONGTIEN_GIAOTHONG();
				pg.setMA_PHUONGTIEN_GIAOTHONG("S" + ts.getMA_TAISAN());
				pg.setTEN_PHUONGTIEN_GIAOTHONG(t.getTenPTGT());
				pg.setBIENSO(t.getBienso());
				pg.setSOKHUNG(t.getSokhung());
				pg.setSOMAY(t.getSomay());
				pg.setMA_TAISAN(ts.getMA_TAISAN());
				pg.setTHOIHAN_DANGKIEM(new Date());
				pgl.add(pg);
			} else {
				System.out.println(t.getMaTaisanLienket());
			}
		}
		return pgl;
	}

	/**
	 * Create contents of the shell.
	 * 
	 * @throws SQLException
	 */
	protected void createContents() throws SQLException {

		setText("Nh\u1EADp danh s\u00E1ch Ph\u01B0\u01A1ng ti\u1EC7n giao th\u00F4ng");
		setSize(750, 482);
		new FormTemplate().setCenterScreen(getShell());
		init();
	}

	private void init() throws SQLException {
		Fill_ItemData fi = new Fill_ItemData();
		fi.setComboBox_LOAIPHUONGTIEN_Phuongtien_Giaothong(combo_Loaixe, 1);
		ArrayList<LOAI_XE> lx = controler.getControl_LOAI_XE().get_AllLOAI_XE_OTO();
		fi.setComboBox_Dongxe(combo_Dongxe, lx);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public final ArrayList<PHUONGTIEN_GIAOTHONG> getResult() {
		return result;
	}

	public final void setResult(ArrayList<PHUONGTIEN_GIAOTHONG> result) {
		this.result = result;
	}

	private void fillTree(ArrayList<PHUONGTIEN_GIAOTHONG> danhsachPHUONGTIEN_GIAOTHONG) throws SQLException {
		tree.removeAll();
		int stt = 1;
		for (PHUONGTIEN_GIAOTHONG t : danhsachPHUONGTIEN_GIAOTHONG) {
			LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(t.getMA_LOAI_XE());
			TAISAN ts = controler.getControl_TAISAN().get_Taisan(t.getMA_TAISAN());
			TreeItem treeItem = new TreeItem(tree, SWT.NONE);
			treeItem.setText(new String[] { "" + stt, ts.getMA_TAISAN() + "", ts.getTEN_TAISAN(),
					t.getTEN_PHUONGTIEN_GIAOTHONG(), t.getBIENSO(), mdf.getViewStringDate(ts.getNGAY_SU_DUNG()),
					t.getSOKHUNG(), t.getSOMAY(), ts.getMA_TANSAN_KETOAN(), lx == null ? "--" : lx.getTEN_DONG_XE(),
					new Fill_ItemData().getStringLOAI_PHUONGTIEN_GIAOTHONG(t.getLOAI_PHUONGTIEN_GIAOTHONG()) });
			treeItem.setData(t);
			stt++;
		}
	}
}
