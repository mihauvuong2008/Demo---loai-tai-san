package View.AssetManagers.Taikhoan;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.ROLE;
import View.AssetManagers.Role.ChonRole;
import View.Template.FormTemplate;

public class QuanlyTaikhoan extends Shell {
	private static NGUOIDUNG user;
	private Table table_danhsachNguoidung;
	private Table table_ChitietQuyenhan;
	private Table table_DanhsachQuyen;
	protected int selectedIndex;
	private int Mode;
	protected ArrayList<NGUOIDUNG> insert;
	protected ArrayList<NGUOIDUNG> delete;
	private final Controler controler;
	private static Log log = LogFactory.getLog(DoiMatkhau.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			QuanlyTaikhoan shell = new QuanlyTaikhoan(display, user);
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
	public QuanlyTaikhoan(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(QuanlyTaikhoan.class, "/Folder-Group-icon.png"));
		setLayout(new GridLayout(1, false));
		QuanlyTaikhoan.user = user;
		controler = new Controler(user);

		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		ToolItem tltmThmNgiDng = new ToolItem(toolBar, SWT.NONE);
		tltmThmNgiDng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (!isInsertMode()) {
						Taikhoan tk = new Taikhoan(getShell(), SWT.DIALOG_TRIM, user, null);
						tk.open();
						NGUOIDUNG nd = (NGUOIDUNG) tk.result;
						if (nd != null) {
							TableItem ti = new TableItem(table_danhsachNguoidung, SWT.NONE);
							ti.setText(new String[] { "-", nd.getTEN_TAI_KHOAN(), nd.getTEN_CAN_BO(),
									nd.getGIOI_THIEU(),
									controler.getControl_PHONGBAN().get_PHONGBAN(nd.getMA_PHONGBAN()).getTEN_PHONGBAN()
											+ "" });
							ti.setData(nd);
							if (insert == null)
								insert = new ArrayList<>();
							insert.add(nd);
						}
					}
					setInsertMode();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmThmNgiDng.setImage(SWTResourceManager.getImage(QuanlyTaikhoan.class, "/Users-Add-User-icon.png"));
		tltmThmNgiDng.setText("Th\u00EAm ng\u01B0\u1EDDi d\u00F9ng");

		ToolItem tltmThayiThng = new ToolItem(toolBar, SWT.NONE);
		tltmThayiThng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					setEditMode();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmThayiThng.setImage(SWTResourceManager.getImage(QuanlyTaikhoan.class, "/Edit-Male-User-icon.png"));
		tltmThayiThng.setText("Thay \u0111\u1ED5i th\u00F4ng tin");

		ToolItem tltmXa = new ToolItem(toolBar, SWT.NONE);
		tltmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] til = table_danhsachNguoidung.getSelection();
				if (til.length > 0) {
					if (isInsertMode()) {
						if (((ROLE) til[0].getData()).getMA_QUYEN() == 0)
							insert = new ArrayList<>();
					}
					if (isEditMode()) {
						Mode = 0;
					}
					removeTableItem(til);
				}
			}

			private void removeTableItem(TableItem[] til) {
				NGUOIDUNG r = (NGUOIDUNG) til[0].getData();
				if (delete == null)
					delete = new ArrayList<>();
				delete.add(r);
				table_danhsachNguoidung.remove(table_danhsachNguoidung.indexOf(til[0]));
			}
		});
		tltmXa.setImage(SWTResourceManager.getImage(QuanlyTaikhoan.class, "/Actions-user-group-delete-icon.png"));
		tltmXa.setText("X\u00F3a");

		ToolItem tltmLwu = new ToolItem(toolBar, SWT.NONE);
		tltmLwu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateItem(getItemUpdate());
					removeItem();
					insertItem();
					setComplete();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void setComplete() throws SQLException {
				Mode = 0;
				loadData();
			}

			private void insertItem() throws SQLException {
				if (insert != null) {
					for (NGUOIDUNG i : insert) {
						controler.getControl_NGUOIDUNG().insert_NGUOIDUNG(i);
					}
				}
				insert = new ArrayList<>();
			}

			private void removeItem() throws SQLException {
				if (delete != null)
					for (NGUOIDUNG i : delete) {
						controler.getControl_NGUOIDUNG().remove_NGUOIDUNG(i);
					}
				delete = new ArrayList<>();
			}

			private void updateItem(ArrayList<TableItem> itemUpdate) throws SQLException {
				for (TableItem i : itemUpdate) {
					NGUOIDUNG r = (NGUOIDUNG) i.getData();
					controler.getControl_NGUOIDUNG().update_NGUOIDUNG(r);
				}
			}

			private ArrayList<TableItem> getItemUpdate() {
				ArrayList<TableItem> result = new ArrayList<>();
				for (TableItem i : table_danhsachNguoidung.getItems()) {
					if (i.getData() != null)
						if (!i.getText(0).equals(""))
							result.add(i);
				}
				return result;
			}
		});
		tltmLwu.setText("Lưu");
		tltmLwu.setImage(SWTResourceManager.getImage(QuanlyTaikhoan.class, "/Actions-document-save-icon (1).png"));

		@SuppressWarnings("unused")
		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmCpQuyn = new ToolItem(toolBar, SWT.NONE);
		tltmCpQuyn.setImage(SWTResourceManager.getImage(QuanlyTaikhoan.class, "/folder-check-icon.png"));
		tltmCpQuyn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] til = table_danhsachNguoidung.getSelection();
					if (til.length > 0) {
						ChonRole cr = new ChonRole(getShell(), SWT.DIALOG_TRIM, user);
						cr.open();
						NGUOIDUNG nd = (NGUOIDUNG) til[0].getData();
						if (!nd.getTEN_TAI_KHOAN().equals("SYSTEM")) {
							ROLE r = (ROLE) cr.getResult();
							if (r != null) {
								controler.getControl_Role_User().Add_Role(nd, r);
							}
						}
					}
					refreshTable(getNewItemSelected());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmCpQuyn.setText("C\u1EA5p quy\u1EC1n");

		ToolItem tltmThuHiQuyn = new ToolItem(toolBar, SWT.NONE);
		tltmThuHiQuyn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] items = table_danhsachNguoidung.getSelection();
					if (items.length > 0) {
						NGUOIDUNG nd = (NGUOIDUNG) items[0].getData();
						TableItem[] items2 = table_DanhsachQuyen.getSelection();
						if (items2.length > 0) {
							ROLE r = (ROLE) items2[0].getData();
							controler.getControl_Role_User().Remove_Role(nd, r);
						}
						refreshTable(getNewItemSelected());
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmThuHiQuyn.setImage(SWTResourceManager.getImage(QuanlyTaikhoan.class, "/block-icon.png"));
		tltmThuHiQuyn.setText("Thu hồi quyền hạn");

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table_danhsachNguoidung = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table_danhsachNguoidung.setHeaderVisible(true);
		table_danhsachNguoidung.setLinesVisible(true);
		table_danhsachNguoidung.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TableItem[] items = table_danhsachNguoidung.getSelection();
				if (items.length > 0) {
					NGUOIDUNG nd = (NGUOIDUNG) items[0].getData();
					fillRole(nd);
					selectedIndex = table_danhsachNguoidung.indexOf(items[0]);
				}
			}

		});

		TableColumn tblclmnStt = new TableColumn(table_danhsachNguoidung, SWT.NONE);
		tblclmnStt.setWidth(100);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnTiKhon = new TableColumn(table_danhsachNguoidung, SWT.NONE);
		tblclmnTnTiKhon.setWidth(100);
		tblclmnTnTiKhon.setText("T\u00CAN T\u00C0I KHO\u1EA2N");

		TableColumn tblclmnTnNgiDng = new TableColumn(table_danhsachNguoidung, SWT.NONE);
		tblclmnTnNgiDng.setWidth(120);
		tblclmnTnNgiDng.setText("T\u00CAN NG\u01AF\u1EDCI D\u00D9NG");

		TableColumn tblclmnGiiThiuNgi = new TableColumn(table_danhsachNguoidung, SWT.NONE);
		tblclmnGiiThiuNgi.setWidth(200);
		tblclmnGiiThiuNgi.setText("GIỚI THIỆU NGƯỜI DÙNG");

		TableColumn tblclmnPhngBan = new TableColumn(table_danhsachNguoidung, SWT.NONE);
		tblclmnPhngBan.setWidth(100);
		tblclmnPhngBan.setText("PHÒNG BAN");

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		table_DanhsachQuyen = new Table(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_DanhsachQuyen.setHeaderVisible(true);
		table_DanhsachQuyen.setLinesVisible(true);
		table_DanhsachQuyen.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TableItem[] items = table_DanhsachQuyen.getSelection();

				if (items.length > 0) {
					ROLE r = (ROLE) items[0].getData();
					fill_info_Role(r);
				}
			}

			private void fill_info_Role(ROLE r) {
				table_ChitietQuyenhan.removeAll();
				int i = 1;
				if (r.getTHEM_NGUOIDUNG() == 1) {
					TableItem nguoidung_Them = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					nguoidung_Them.setText(new String[] { i + "", "THÊM NGƯỜI DÙNG" });
					i++;
				}
				if (r.getPHAN_QUYEN_NGUOIDUNG() == 1) {
					TableItem nguoidung_Sua = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					nguoidung_Sua.setText(new String[] { i + "", "SỬA THÔNG TIN NGƯỜI DÙNG" });
					i++;
				}

				if (r.getXEM_THONGTIN_NGUOIDUNG() == 1) {
					TableItem nguoidung_Xoa = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					nguoidung_Xoa.setText(new String[] { i + "", "XEM THÔNG TIN NGƯỜI DÙNG" });
					i++;
				}

				if (r.getXOA_NGUOIDUNG() == 1) {
					TableItem nguoidung_Xoa = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					nguoidung_Xoa.setText(new String[] { i + "", "XÓA THÔNG TIN NGƯỜI DÙNG" });
					i++;
				}

				if (r.getTHEM_THONGTIN_TAISAN() == 1) {
					TableItem Taisan_Them = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Taisan_Them.setText(new String[] { i + "", "NHẬP THÔNG TÀI SẢN MỚI" });
					i++;
				}

				if (r.getXEM_THONGTIN_TAISAN() == 1) {
					TableItem Taisan_Xem = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Taisan_Xem.setText(new String[] { i + "", "XEM THÔNG TIN TÀI SẢN" });
					i++;
				}

				if (r.getSUA_THONGTIN_TAISAN() == 1) {
					TableItem Taisan_Sua = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Taisan_Sua.setText(new String[] { i + "", "THAY ĐỔI THÔNG TIN TÀI SẢN" });
					i++;
				}

				if (r.getXOA_THONGTIN_TAISAN() == 1) {
					TableItem Taisan_Xoa = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Taisan_Xoa.setText(new String[] { i + "", "XÓA THÔNG TIN TÀI SẢN" });
					i++;
				}

				if (r.getTHEM_CONGVIEC() == 1) {
					TableItem Congviec_Them = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Congviec_Them.setText(new String[] { i + "", "TẠO CÔNG VIỆC" });
					i++;
				}

				if (r.getXEM_THONGTIN_CONGVIEC() == 1) {
					TableItem Congviec_Xem = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Congviec_Xem.setText(new String[] { i + "", "XEM THÔNG TIN CÔNG VIỆC" });
					i++;
				}

				if (r.getSUA_THONGTIN_CONGVIEC() == 1) {
					TableItem Congviec_Sua = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Congviec_Sua.setText(new String[] { i + "", "THAY ĐỔI THÔNG TIN CÔNG VIỆC" });
					i++;
				}

				if (r.getXOA_CONGVIEC() == 1) {
					TableItem Congviec_Xoa = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Congviec_Xoa.setText(new String[] { i + "", "XÓA THÔNG TIN CÔNG VIỆC" });
					i++;
				}

				if (r.getTHEM_HOSO() == 1) {
					TableItem Hoso_Them = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Hoso_Them.setText(new String[] { i + "", "THÊM HỒ SƠ" });
					i++;
				}

				if (r.getXEM_THONGTIN_HOSO() == 1) {
					TableItem Hoso_Xem = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Hoso_Xem.setText(new String[] { i + "", "XEM THÔNG TIN HỒ SƠ" });
					i++;
				}

				if (r.getSUA_THONGTIN_HOSO() == 1) {
					TableItem Hoso_Sua = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Hoso_Sua.setText(new String[] { i + "", "SỬA THÔNG TIN HỒ SƠ" });
					i++;
				}

				if (r.getXOA_HOSO() == 1) {
					TableItem Hoso_Xoa = new TableItem(table_ChitietQuyenhan, SWT.NONE);
					Hoso_Xoa.setText(new String[] { i + "", "XÓA THÔNG TIN HỒ SƠ" });
					i++;
				}

			}
		});

		TableColumn tblclmnStt_2 = new TableColumn(table_DanhsachQuyen, SWT.NONE);
		tblclmnStt_2.setWidth(45);
		tblclmnStt_2.setText("STT");

		TableColumn tblclmnTnQuyn_1 = new TableColumn(table_DanhsachQuyen, SWT.NONE);
		tblclmnTnQuyn_1.setWidth(120);
		tblclmnTnQuyn_1.setText("TÊN QUYỀN");

		TableColumn tblclmnGiiThiu = new TableColumn(table_DanhsachQuyen, SWT.NONE);
		tblclmnGiiThiu.setWidth(200);
		tblclmnGiiThiu.setText("GIỚI THIỆU");

		table_ChitietQuyenhan = new Table(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_ChitietQuyenhan.setHeaderVisible(true);
		table_ChitietQuyenhan.setLinesVisible(true);

		TableColumn tblclmnStt_1 = new TableColumn(table_ChitietQuyenhan, SWT.NONE);
		tblclmnStt_1.setWidth(45);
		tblclmnStt_1.setText("STT");

		TableColumn tblclmnTnQuyn = new TableColumn(table_ChitietQuyenhan, SWT.NONE);
		tblclmnTnQuyn.setWidth(200);
		tblclmnTnQuyn.setText("CHI TIẾT");
		sashForm_1.setWeights(new int[] { 618, 1000 });
		sashForm.setWeights(new int[] { 1000, 618 });

		Button btnng = new Button(this, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");

		createContents();

		loadData();

	}

	protected boolean isEditMode() {
		if (Mode == 2)
			return true;
		return false;
	}

	protected boolean isInsertMode() {
		if (Mode == 1)
			return true;
		return false;
	}

	protected void setInsertMode() {
		Mode = 1;
	}

	protected void setEditMode() throws SQLException {
		TableItem[] items = table_danhsachNguoidung.getSelection();
		if (items.length > 0) {
			NGUOIDUNG nd = (NGUOIDUNG) items[0].getData();
			Taikhoan tk = new Taikhoan(getShell(), SWT.DIALOG_TRIM, user, nd);
			tk.open();
			if (tk.result != null) {
				nd = (NGUOIDUNG) tk.result;
				items[0].setText(new String[] { items[0].getText(0), nd.getTEN_TAI_KHOAN(), nd.getTEN_CAN_BO(),
						nd.getGIOI_THIEU() });
				System.out.println(items[0].getText(2));
				items[0].setData(tk.result);
			}
		}
		Mode = 2;
	}

	protected NGUOIDUNG getNewItemSelected() throws SQLException {
		loadData();
		table_danhsachNguoidung.select(selectedIndex);
		TableItem[] items = table_danhsachNguoidung.getSelection();
		return (NGUOIDUNG) items[0].getData();
	}

	protected void refreshTable(NGUOIDUNG newData) {
		fillRole(newData);
		table_ChitietQuyenhan.removeAll();
	}

	private void fillRole(NGUOIDUNG nd) {
		table_DanhsachQuyen.removeAll();
		ArrayList<ROLE> rl = nd.getRolelist();
		int i = 1;
		if (rl != null)
			for (ROLE r : rl) {
				TableItem tb = new TableItem(table_DanhsachQuyen, SWT.NONE);
				tb.setText(new String[] { i + "", r.getTEN_QUYEN(), r.getMO_TA() });
				tb.setData(r);
				i++;
			}
		tablePack(table_DanhsachQuyen);
	}

	private void loadData() throws SQLException {
		// ts = new Table_Style();
		// ts.setTableItemHeight(table_ChitietQuyenhan, 25);
		table_danhsachNguoidung.removeAll();
		ArrayList<NGUOIDUNG> data = controler.getControl_NGUOIDUNG().get_All();
		int i = 1;
		for (NGUOIDUNG nd : data) {
			TableItem ti = new TableItem(table_danhsachNguoidung, SWT.NONE);
			PHONGBAN pb = controler.getControl_PHONGBAN().get_PHONGBAN(nd.getMA_PHONGBAN());
			ti.setText(new String[] { i + "", nd.getTEN_TAI_KHOAN(), nd.getTEN_CAN_BO(), nd.getGIOI_THIEU(),
					pb.getTEN_PHONGBAN() });
			ti.setData(nd);
			i++;
		}
		tablePack(table_danhsachNguoidung);
	}

	private void tablePack(Table table) {
		for (TableColumn tc : table.getColumns()) {
			tc.pack();
		}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(776, 480);
		setText("Quản lý tài khoản Người dùng");
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
