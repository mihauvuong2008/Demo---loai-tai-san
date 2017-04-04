package View.AssetManagers.Taisan.Phuongtiengiaothong.LichDangkiem;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;

import View.AssetManagers.Hoso.TAPHOSO_View;
import View.AssetManagers.Hoso.Vanban_View;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DOT_THUCHIEN_DANGKIEM;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAP_HO_SO;
import DAO.VANBAN;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CongViec_Dangkiem extends Dialog {

	protected Object result;
	protected Shell shlngKimPhng;
	private Table table;
	private Text text_TenPTGT;
	private Text text_Bienso;
	private Text text_Ghichu;
	private DOT_THUCHIEN_DANGKIEM dtd = null;
	private final Controler controler;
	private final NGUOIDUNG user;
	private DateTime dateTime;
	private PHUONGTIEN_GIAOTHONG pg;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @wbp.parser.constructor
	 */
	public CongViec_Dangkiem(Shell parent, int style, NGUOIDUNG user, DOT_THUCHIEN_DANGKIEM dtd) {
		super(parent, style);
		setText("SWT Dialog");
		this.dtd = dtd;
		this.user = user;
		controler = new Controler(user);
	}

	public CongViec_Dangkiem(Shell parent, int style, NGUOIDUNG user, PHUONGTIEN_GIAOTHONG pg) {
		super(parent, style);
		setText("SWT Dialog");
		this.pg = pg;
		this.user = user;
		controler = new Controler(user);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlngKimPhng.open();
		shlngKimPhng.layout();
		Display display = getParent().getDisplay();
		while (!shlngKimPhng.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @throws SQLException
	 */
	private void createContents() throws SQLException {
		shlngKimPhng = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX);
		shlngKimPhng.setSize(647, 400);
		shlngKimPhng.setText("Đăng kiểm Phương tiện giao thông: ");
		shlngKimPhng.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlngKimPhng);

		SashForm sashForm = new SashForm(shlngKimPhng, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblNgyThcng = new Label(composite, SWT.NONE);
		lblNgyThcng.setText("Ngày thực Đăng kiểm:");

		dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTnPhngTin = new Label(composite, SWT.NONE);
		lblTnPhngTin.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTnPhngTin.setText("Tên phương tiện: ");

		text_TenPTGT = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text_TenPTGT.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_TenPTGT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblBinS = new Label(composite, SWT.NONE);
		lblBinS.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBinS.setText("Biển số: ");

		text_Bienso = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text_Bienso.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Bienso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGhiCh = new Label(composite, SWT.NONE);
		lblGhiCh.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblGhiCh.setText("Ghi chú: ");

		text_Ghichu = new Text(composite, SWT.BORDER);
		text_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnHS = new TableColumn(table, SWT.NONE);
		tblclmnTnHS.setWidth(150);
		tblclmnTnHS.setText("SỐ VĂN BẢN");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem mntmXem = new MenuItem(menu, SWT.NONE);
		mntmXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem til[] = table.getSelection();
				if (til.length > 0) {
					TAP_HO_SO ths = null;
					try {
						ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dtd.getMA_TAPHOSO());
						if (ths != null) {
							VANBAN vb = (VANBAN) til[0].getData();
							Vanban_View Vv = new Vanban_View(shlngKimPhng, SWT.DIALOG_TRIM, user, ths, vb, false);
							Vv.open();
							loadVanban();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXem.setText("Xem");

		MenuItem mntmThm = new MenuItem(menu, SWT.NONE);
		mntmThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TAP_HO_SO ths;
				try {
					ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dtd.getMA_TAPHOSO());
					if (ths != null) {
						Vanban_View vv = new Vanban_View(shlngKimPhng, SWT.DIALOG_TRIM, user, ths, null, false);
						vv.open();
						loadVanban();
					} else {
						if (pg == null) {
							pg = controler.getControl_PHUONGTIEN_GIAOTHONG()
									.get_PHUONGTIEN_GIAOTHONG(dtd.getMA_PHUONGTIEN_GIAOTHONG());
						}
						if (pg != null) {
							if (dtd != null) {
								ths = new TAP_HO_SO();
								ths.setTEN_TAPHOSO("Tập hồ sơ Đăng kiểm xe ô tô: " + pg.getBIENSO() + ", ngày: "
										+ new MyDateFormat().getViewStringDate(dtd.getNGAY_THUCHIEN()));
								ths.setGIOITHIEU_TAPHOSO("Tập hồ sơ Đăng kiểm xe ô tô: " + pg.getBIENSO() + ", ngày: "
										+ new MyDateFormat().getViewStringDate(dtd.getNGAY_THUCHIEN()));
								int key = controler.getControl_TAPHOSO().Create_TAP_HO_SO(ths);
								if (key > 0) {
									dtd.setMA_TAPHOSO(key);
									controler.getControl_DOT_THUCHIEN_DANGKIEM().update_DOT_THUCHIEN_DANGKIEM(dtd);
									ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(key);
									TAPHOSO_View tv = new TAPHOSO_View(shlngKimPhng, SWT.DIALOG_TRIM, user, ths, false);
									tv.open();
									loadVanban();
								}
							}
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mntmThm.setText("Thêm");

		MenuItem mntmXa = new MenuItem(menu, SWT.NONE);
		mntmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem til[] = table.getSelection();
				if (til.length > 0) {
					VANBAN vb = (VANBAN) til[0].getData();
					try {
						controler.getControl_VANBAN().delete_VANBAN(vb);
						loadVanban();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXa.setText("Xóa");

		TableColumn tblclmnNgyBanHnh = new TableColumn(table, SWT.NONE);
		tblclmnNgyBanHnh.setWidth(100);
		tblclmnNgyBanHnh.setText("NGÀY BAN HÀNH");

		TableColumn tblclmnnVBan = new TableColumn(table, SWT.NONE);
		tblclmnnVBan.setWidth(100);
		tblclmnnVBan.setText("ĐƠN VỊ BAN HÀNH");
		sashForm.setWeights(new int[] { 1000, 618 });

		Button btnHonThnhCng = new Button(shlngKimPhng, SWT.NONE);
		btnHonThnhCng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (dtd == null) {// create
					dtd = getDOT_THUCHIEN_DANGKIEM();
					try {
						dtd.setMA_PHUONGTIEN_GIAOTHONG(pg.getMA_PHUONGTIEN_GIAOTHONG());
						controler.getControl_DOT_THUCHIEN_DANGKIEM().insert_DOT_THUCHIEN_DANGKIEM(dtd);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {// save
					int key = dtd.getMA_DOT_THUCHIEN_DANGKIEM();
					String keyptgt = dtd.getMA_PHUONGTIEN_GIAOTHONG();
					int keyths = dtd.getMA_TAPHOSO();
					dtd = getDOT_THUCHIEN_DANGKIEM();
					dtd.setMA_DOT_THUCHIEN_DANGKIEM(key);
					dtd.setMA_PHUONGTIEN_GIAOTHONG(keyptgt);
					dtd.setMA_TAPHOSO(keyths);
					try {
						controler.getControl_DOT_THUCHIEN_DANGKIEM().update_DOT_THUCHIEN_DANGKIEM(dtd);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				shlngKimPhng.dispose();
			}

			private DOT_THUCHIEN_DANGKIEM getDOT_THUCHIEN_DANGKIEM() {
				DOT_THUCHIEN_DANGKIEM rs = new DOT_THUCHIEN_DANGKIEM();
				rs.setNGAY_THUCHIEN(new MyDateFormat().getDate(dateTime));
				rs.setGHI_CHU(text_Ghichu.getText());
				return rs;
			}
		});
		btnHonThnhCng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnHonThnhCng.setText("Lưu - Hoàn thành công việc");

		Button btnNewButton = new Button(shlngKimPhng, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlngKimPhng.dispose();
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 75;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("Đóng");

		init();

	}

	protected void loadVanban() throws SQLException {
		table.removeAll();
		if (dtd != null) {
			TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dtd.getMA_TAPHOSO());
			if (ths != null) {
				ArrayList<VANBAN> vbl = controler.getControl_VANBAN().get_AllVanban(ths);
				int i = 1;
				for (VANBAN vb : vbl) {
					TableItem ti = new TableItem(table, SWT.NONE);
					ti.setText(new String[] { i + "", vb.getSO_VANBAN(),
							new MyDateFormat().getViewStringDate(vb.getNGAY_BAN_HANH()), vb.getCO_QUAN_BAN_HANH() });
					ti.setData(vb);
					i++;
				}
			}
		}

	}

	private void init() throws SQLException {
		if (dtd != null) {
			PHUONGTIEN_GIAOTHONG pg = controler.getControl_PHUONGTIEN_GIAOTHONG()
					.get_PHUONGTIEN_GIAOTHONG(dtd.getMA_PHUONGTIEN_GIAOTHONG());
			if (pg != null) {
				Calendar c = new MyDateFormat().getCalendar(dtd.getNGAY_THUCHIEN());
				dateTime.setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
				text_TenPTGT.setText(pg.getTEN_PHUONGTIEN_GIAOTHONG());
				text_Bienso.setText(pg.getBIENSO());
				text_Ghichu.setText(dtd.getGHI_CHU());
			}
			fillTable();
			loadVanban();
		} else {
			text_TenPTGT.setText(pg.getTEN_PHUONGTIEN_GIAOTHONG());
			text_Bienso.setText(pg.getBIENSO());
		}
	}

	private void fillTable() throws SQLException {
		table.removeAll();
		TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dtd.getMA_TAPHOSO());
		if (ths != null) {
			ArrayList<VANBAN> data = controler.getControl_VANBAN().get_AllVanban(ths);
			int i = 0;
			for (VANBAN vb : data) {
				TableItem ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { i + "", vb.getSO_VANBAN(),
						new MyDateFormat().getViewStringDate(vb.getNGAY_BAN_HANH()), vb.getCO_QUAN_BAN_HANH() });
				ti.setData(vb);
				i++;
			}
		}
	}
}
