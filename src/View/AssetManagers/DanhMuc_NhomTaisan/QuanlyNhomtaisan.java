package View.AssetManagers.DanhMuc_NhomTaisan;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NHOMTAISAN_CAP_I;
import DAO.NHOMTAISAN_CAP_II;
import DAO.NHOMTAISAN_CAP_III;
import DAO.NHOMTAISAN_CAP_IV;
import DAO.NHOMTAISAN_CAP_V;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;
import View.AssetManagers.Nhomtaisan_162;
import View.AssetManagers.excel_NhapDulieu.ImportExcel_NhomTaisan;
import View.Template.FormTemplate;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class QuanlyNhomtaisan extends Shell {
	private Tree tree_NHOMTaisan_Codinh;
	private static NGUOIDUNG user;
	TreeItem TongItem_NhomTaisan;
	private Text text_Name;
	private int MODE;
	private Button btnCheckButton;
	private final Controler controler;
	private static Log log = LogFactory.getLog(QuanlyNhomtaisan.class);
	private Tree tree_NhomTaisan_Codinh_dacbiet;
	private Tree tree_Nhomtaisan_Codinh_Dacthu;
	private Tree tree_NhomTaisan_Codinh_Vohinh;
	private Nhomtaisan_162 N162;
	protected ArrayList<Object> delete_list;
	protected Object Insert_list;
	private Text text_Namsudung;
	private Text text_Khauhao;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			QuanlyNhomtaisan shell = new QuanlyNhomtaisan(display, user);
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
	public QuanlyNhomtaisan(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(user.getIcondata().TongNhomtaisan);
		setLayout(new GridLayout(8, false));
		QuanlyNhomtaisan.user = user;
		controler = new Controler(user);

		TabFolder tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 8, 1));

		TabItem tbtmTiSnC = new TabItem(tabFolder, SWT.NONE);
		tbtmTiSnC.setText("Tài sản Cố định");

		tree_NHOMTaisan_Codinh = new Tree(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tree_NHOMTaisan_Codinh.setLinesVisible(true);
		tbtmTiSnC.setControl(tree_NHOMTaisan_Codinh);
		tree_NHOMTaisan_Codinh.pack();

		TreeColumn trclmnTenNhom = new TreeColumn(tree_NHOMTaisan_Codinh, SWT.NONE);
		trclmnTenNhom.setWidth(450);
		trclmnTenNhom.setText("Ten nhom");

		TreeColumn trclmnThoiGianSu = new TreeColumn(tree_NHOMTaisan_Codinh, SWT.NONE);
		trclmnThoiGianSu.setWidth(150);
		trclmnThoiGianSu.setText("Thoi gian su dung");

		TreeColumn trclmnTiLeHao = new TreeColumn(tree_NHOMTaisan_Codinh, SWT.NONE);
		trclmnTiLeHao.setWidth(150);
		trclmnTiLeHao.setText("Ti le hao mon");

		TabItem tbtmTiSnC_3 = new TabItem(tabFolder, SWT.NONE);
		tbtmTiSnC_3.setText("Tài sản Cố định Vô hình");

		tree_NhomTaisan_Codinh_Vohinh = new Tree(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tree_NhomTaisan_Codinh_Vohinh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = tree_NhomTaisan_Codinh_Vohinh.getSelection();

				if (items.length > 0) {
					Object o = items[0].getData();
					if (o instanceof NHOM_TAISANCODINH_VOHINH) {
						text_Name.setText(((NHOM_TAISANCODINH_VOHINH) o).getTEN_NHOM_TAISANCODINH_VOHINH());
						text_Namsudung.setText(String.valueOf(((NHOM_TAISANCODINH_VOHINH) o).getTHOIGIAN_SUDUNG()));
						text_Khauhao.setText(String.valueOf(((NHOM_TAISANCODINH_VOHINH) o).getTILE_HAOMON()));
					} else {
						text_Name.setText("Tất cả");
					}
				}
			}
		});
		tree_NhomTaisan_Codinh_Vohinh.setLinesVisible(true);
		tbtmTiSnC_3.setControl(tree_NhomTaisan_Codinh_Vohinh);

		TreeColumn trclmnTenNhom_1 = new TreeColumn(tree_NhomTaisan_Codinh_Vohinh, SWT.NONE);
		trclmnTenNhom_1.setWidth(450);
		trclmnTenNhom_1.setText("Ten Nhom");

		TreeColumn trclmnThoiGianSu_1 = new TreeColumn(tree_NhomTaisan_Codinh_Vohinh, SWT.NONE);
		trclmnThoiGianSu_1.setWidth(150);
		trclmnThoiGianSu_1.setText("Thoi gian su dung");

		TreeColumn trclmnTiLeHao_1 = new TreeColumn(tree_NhomTaisan_Codinh_Vohinh, SWT.NONE);
		trclmnTiLeHao_1.setWidth(150);
		trclmnTiLeHao_1.setText("Ti le hao mon");

		TabItem tbtmTiSnC_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmTiSnC_2.setText("Tài sản cố định đặc thù");

		tree_Nhomtaisan_Codinh_Dacthu = new Tree(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Nhomtaisan_Codinh_Dacthu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = tree_Nhomtaisan_Codinh_Dacthu.getSelection();

				if (items.length > 0) {
					Object o = items[0].getData();
					if (o instanceof NHOM_TAISANCODINH_DACTHU) {
						text_Name.setText(((NHOM_TAISANCODINH_DACTHU) o).getTEN_NHOM_TAISANCODINH_DACTHU());
						text_Namsudung.setText(String.valueOf(((NHOM_TAISANCODINH_DACTHU) o).getTHOIGIAN_SUDUNG()));
						text_Khauhao.setText(String.valueOf(((NHOM_TAISANCODINH_DACTHU) o).getTILE_HAOMON()));
					} else {
						text_Name.setText("Tất cả");
					}
				}
			}
		});
		tree_Nhomtaisan_Codinh_Dacthu.setLinesVisible(true);
		tbtmTiSnC_2.setControl(tree_Nhomtaisan_Codinh_Dacthu);

		TreeColumn trclmnTenNhom_2 = new TreeColumn(tree_Nhomtaisan_Codinh_Dacthu, SWT.NONE);
		trclmnTenNhom_2.setWidth(450);
		trclmnTenNhom_2.setText("Ten nhom");

		TreeColumn trclmnThoiGianSu_2 = new TreeColumn(tree_Nhomtaisan_Codinh_Dacthu, SWT.NONE);
		trclmnThoiGianSu_2.setWidth(150);
		trclmnThoiGianSu_2.setText("Thoi gian su dung");

		TreeColumn trclmnTiLeHao_2 = new TreeColumn(tree_Nhomtaisan_Codinh_Dacthu, SWT.NONE);
		trclmnTiLeHao_2.setWidth(150);
		trclmnTiLeHao_2.setText("Ti le hao mon");
		TabItem tbtmTiSnC_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmTiSnC_1.setText("Tài sản Cố định Đặc biệt");

		tree_NhomTaisan_Codinh_dacbiet = new Tree(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tree_NhomTaisan_Codinh_dacbiet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = tree_NhomTaisan_Codinh_dacbiet.getSelection();

				if (items.length > 0) {
					Object o = items[0].getData();
					if (o instanceof NHOM_TAISANCODINH_DACBIET) {
						text_Name.setText(((NHOM_TAISANCODINH_DACBIET) o).getTEN_NHOM_TAISANCODINH_DACBIET());
						text_Khauhao.setText(String.valueOf(((NHOM_TAISANCODINH_DACBIET) o).getGIA_QUYUOC()));
					} else {
						text_Name.setText("Tất cả");
					}
				}
			}
		});
		tree_NhomTaisan_Codinh_dacbiet.setLinesVisible(true);
		tbtmTiSnC_1.setControl(tree_NhomTaisan_Codinh_dacbiet);

		TreeColumn trclmnTenNhom_3 = new TreeColumn(tree_NhomTaisan_Codinh_dacbiet, SWT.NONE);
		trclmnTenNhom_3.setWidth(450);
		trclmnTenNhom_3.setText("Ten Nhom");

		TreeColumn trclmnGiaQuyUoc = new TreeColumn(tree_NhomTaisan_Codinh_dacbiet, SWT.NONE);
		trclmnGiaQuyUoc.setWidth(150);
		trclmnGiaQuyUoc.setText("Gia quy uoc");
		tree_NHOMTaisan_Codinh.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TreeItem[] items = tree_NHOMTaisan_Codinh.getSelection();

				if (items.length > 0) {
					Object o = items[0].getData();
					if (o instanceof NHOMTAISAN_CAP_I) {
						text_Name.setText(((NHOMTAISAN_CAP_I) o).getTEN_NHOMTAISAN_CAP_I());
					} else if (o instanceof NHOMTAISAN_CAP_II) {
						text_Name.setText(((NHOMTAISAN_CAP_II) o).getTEN_NHOMTAISAN_CAP_II());

					} else if (o instanceof NHOMTAISAN_CAP_III) {
						text_Name.setText(((NHOMTAISAN_CAP_III) o).getTEN_NHOMTAISAN_CAP_III());

					} else if (o instanceof NHOMTAISAN_CAP_IV) {
						text_Name.setText(((NHOMTAISAN_CAP_IV) o).getTEN_NHOMTAISAN_CAP_IV());

					} else if (o instanceof NHOMTAISAN_CAP_V) {
						text_Name.setText(((NHOMTAISAN_CAP_V) o).getTEN_NHOMTAISAN_CAP_V());
						text_Namsudung.setText(String.valueOf(((NHOMTAISAN_CAP_V) o).getTHOIGIAN_SUDUNG()));
						text_Khauhao.setText(String.valueOf(((NHOMTAISAN_CAP_V) o).getTILE_HAOMON()));
					} else {
						text_Name.setText("Tất cả");
					}
				}
			}
		});

		Label lblTenNhom = new Label(this, SWT.NONE);
		lblTenNhom.setText("Tên nhóm: ");

		text_Name = new Text(this, SWT.BORDER);
		text_Name.setEditable(false);
		text_Name.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		text_Namsudung = new Text(this, SWT.BORDER);
		text_Namsudung.setText("0");
		text_Namsudung.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				Text text = (Text) e.getSource();
				final String oldS = text.getText();
				String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

				boolean isFloat = true;
				try {
					Float.parseFloat(newS);
				} catch (NumberFormatException ex) {
					isFloat = false;
				}
				if (!isFloat)
					e.doit = false;
			}
		});
		text_Namsudung.setEditable(false);
		text_Namsudung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Namsudung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		text_Khauhao = new Text(this, SWT.BORDER);
		text_Khauhao.setText("0");
		text_Khauhao.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				Text text = (Text) e.getSource();
				final String oldS = text.getText();
				String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

				boolean isFloat = true;
				try {
					Float.parseFloat(newS);
				} catch (NumberFormatException ex) {
					isFloat = false;
				}
				if (!isFloat)
					e.doit = false;
			}
		});
		text_Khauhao.setEditable(false);
		text_Khauhao.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Khauhao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		btnCheckButton = new Button(this, SWT.CHECK);
		btnCheckButton.setSelection(true);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					loadAll();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnCheckButton.setText("Mở rộng");
		new Label(this, SWT.NONE);

		Button btnThm = new Button(this, SWT.NONE);
		btnThm.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnThm.setImage(user.getIcondata().addIcon);
		btnThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setInsertMode();
			}
		});
		btnThm.setText("Thêm");

		Button btnSa = new Button(this, SWT.NONE);
		btnSa.setImage(user.getIcondata().editIcon);
		btnSa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEditMode();
			}
		});
		btnSa.setText("Sửa");

		Button btnXa = new Button(this, SWT.NONE);
		btnXa.setImage(user.getIcondata().deleteIcon);
		btnXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					if (tabFolder.getSelectionIndex() == 0) {
						TreeItem[] items = tree_NHOMTaisan_Codinh.getSelection();
						if (items.length > 0) {
							Object o = items[0].getData();
							if (o instanceof NHOMTAISAN_CAP_I) {
								NHOMTAISAN_CAP_I l = (NHOMTAISAN_CAP_I) o;
								controler.getControl_NHOMTAISAN_CAP_I().delete_NHOMTAISAN_CAP_I(l);
							} else if (o instanceof NHOMTAISAN_CAP_II) {
								NHOMTAISAN_CAP_II l = (NHOMTAISAN_CAP_II) o;
								controler.getControl_NHOMTAISAN_CAP_II().delete_NHOMTAISAN_CAP_II(l);
							} else if (o instanceof NHOMTAISAN_CAP_III) {
								NHOMTAISAN_CAP_III l = (NHOMTAISAN_CAP_III) o;
								controler.getControl_NHOMTAISAN_CAP_III().delete_NHOMTAISAN_CAP_III(l);
							} else if (o instanceof NHOMTAISAN_CAP_IV) {
								NHOMTAISAN_CAP_IV l = (NHOMTAISAN_CAP_IV) o;
								controler.getControl_NHOMTAISAN_CAP_IV().delete_NHOMTAISAN_CAP_IV(l);
							} else if (o instanceof NHOMTAISAN_CAP_V) {
								NHOMTAISAN_CAP_V l = (NHOMTAISAN_CAP_V) o;
								controler.getControl_NHOMTAISAN_CAP_V().delete_NHOMTAISAN_CAP_V(l);
							}
						}
					} else if (tabFolder.getSelectionIndex() == 1) {
						TreeItem[] items = tree_NhomTaisan_Codinh_Vohinh.getSelection();
						if (items.length > 0) {
							Object o = items[0].getData();
							if (o instanceof NHOM_TAISANCODINH_VOHINH) {
								NHOM_TAISANCODINH_VOHINH l = (NHOM_TAISANCODINH_VOHINH) o;
								controler.getControl_NHOM_TAISANCODINH_VOHINH().delete_NHOM_TAISANCODINH_VOHINH(l);
							}
						}
					} else if (tabFolder.getSelectionIndex() == 2) {
						TreeItem[] items = tree_Nhomtaisan_Codinh_Dacthu.getSelection();
						if (items.length > 0) {
							Object o = items[0].getData();
							if (o instanceof NHOM_TAISANCODINH_DACTHU) {
								NHOM_TAISANCODINH_DACTHU l = (NHOM_TAISANCODINH_DACTHU) o;
								controler.getControl_NHOM_TAISANCODINH_DACTHU().delete_NHOM_TAISANCODINH_DACTHU(l);
							}
						}
					} else if (tabFolder.getSelectionIndex() == 3) {
						TreeItem[] items = tree_NhomTaisan_Codinh_dacbiet.getSelection();
						if (items.length > 0) {
							Object o = items[0].getData();
							if (o instanceof NHOM_TAISANCODINH_DACBIET) {
								NHOM_TAISANCODINH_DACBIET l = (NHOM_TAISANCODINH_DACBIET) o;
								controler.getControl_NHOM_TAISANCODINH_DACBIET().delete_NHOM_TAISANCODINH_DACBIET(l);
							}
						}
					}
					loadAll();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnXa.setText("Xóa");

		Button btnLu = new Button(this, SWT.NONE);
		btnLu.setImage(user.getIcondata().saveIcon);
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (tabFolder.getSelectionIndex() == 0) {
						TreeItem[] items = tree_NHOMTaisan_Codinh.getSelection();
						if (items.length > 0) {
							Object o = items[0].getData();
							if (isInsertMode()) {
								if (!text_Name.getText().equals("")) {
									if (o instanceof NHOMTAISAN_CAP_I) {
										NHOMTAISAN_CAP_II l = new NHOMTAISAN_CAP_II();
										l.setTEN_NHOMTAISAN_CAP_II(text_Name.getText());
										l.setMA_NHOMTAISAN_CAP_I(((NHOMTAISAN_CAP_I) o).getMA_NHOMTAISAN_CAP_I());
										controler.getControl_NHOMTAISAN_CAP_II().insert_NHOMTAISAN_CAP_II(l);
									} else if (o instanceof NHOMTAISAN_CAP_II) {
										NHOMTAISAN_CAP_III l = new NHOMTAISAN_CAP_III();
										l.setTEN_NHOMTAISAN_CAP_III(text_Name.getText());
										l.setMA_NHOMTAISAN_CAP_II(((NHOMTAISAN_CAP_II) o).getMA_NHOMTAISAN_CAP_II());
										controler.getControl_NHOMTAISAN_CAP_III().insert_NHOMTAISAN_CAP_III(l);
									} else if (o instanceof NHOMTAISAN_CAP_III) {
										NHOMTAISAN_CAP_IV l = new NHOMTAISAN_CAP_IV();
										l.setTEN_NHOMTAISAN_CAP_IV(text_Name.getText());
										l.setMA_NHOMTAISAN_CAP_III(((NHOMTAISAN_CAP_III) o).getMA_NHOMTAISAN_CAP_III());
										controler.getControl_NHOMTAISAN_CAP_IV().insert_NHOMTAISAN_CAP_IV(l);
									} else if (o instanceof NHOMTAISAN_CAP_IV) {
										NHOMTAISAN_CAP_V l = new NHOMTAISAN_CAP_V();
										l.setTEN_NHOMTAISAN_CAP_V(text_Name.getText());
										l.setTHOIGIAN_SUDUNG(Integer.valueOf(text_Namsudung.getText()));
										l.setTILE_HAOMON(Double.valueOf(text_Khauhao.getText()));
										l.setMA_NHOMTAISAN_CAP_IV(((NHOMTAISAN_CAP_IV) o).getMA_NHOMTAISAN_CAP_IV());
										controler.getControl_NHOMTAISAN_CAP_V().insert_NHOMTAISAN_CAP_V(l);
									} else {
										NHOMTAISAN_CAP_I l = new NHOMTAISAN_CAP_I();
										l.setTEN_NHOMTAISAN_CAP_I(text_Name.getText());
										controler.getControl_NHOMTAISAN_CAP_I().insert_NHOMTAISAN_CAP_I(l);
									}
								} else {
									MessageBox m = new MessageBox(getShell(), SWT.NONE);
									m.setMessage("Không để trống Tên nhóm");
									m.open();
								}
								loadAll();
							} else if (isEditMode()) {
								if (!text_Name.getText().equals("")) {
									if (o instanceof NHOMTAISAN_CAP_I) {
										NHOMTAISAN_CAP_I l = (NHOMTAISAN_CAP_I) o;
										l.setTEN_NHOMTAISAN_CAP_I(text_Name.getText());
										controler.getControl_NHOMTAISAN_CAP_I().update_NHOMTAISAN_CAP_I(l);
									} else if (o instanceof NHOMTAISAN_CAP_II) {
										NHOMTAISAN_CAP_II l = (NHOMTAISAN_CAP_II) o;
										l.setTEN_NHOMTAISAN_CAP_II(text_Name.getText());
										controler.getControl_NHOMTAISAN_CAP_II().update_NHOMTAISAN_CAP_II(l);
									} else if (o instanceof NHOMTAISAN_CAP_III) {
										NHOMTAISAN_CAP_III l = (NHOMTAISAN_CAP_III) o;
										l.setTEN_NHOMTAISAN_CAP_III(text_Name.getText());
										controler.getControl_NHOMTAISAN_CAP_III().update_NHOMTAISAN_CAP_III(l);
									} else if (o instanceof NHOMTAISAN_CAP_IV) {
										NHOMTAISAN_CAP_IV l = (NHOMTAISAN_CAP_IV) o;
										l.setTEN_NHOMTAISAN_CAP_IV(text_Name.getText());
										controler.getControl_NHOMTAISAN_CAP_IV().update_NHOMTAISAN_CAP_IV(l);
									} else if (o instanceof NHOMTAISAN_CAP_V) {
										NHOMTAISAN_CAP_V l = (NHOMTAISAN_CAP_V) o;
										l.setTEN_NHOMTAISAN_CAP_V(text_Name.getText());
										l.setTHOIGIAN_SUDUNG(Integer.valueOf(text_Namsudung.getText()));
										l.setTILE_HAOMON(Double.valueOf(text_Khauhao.getText()));
										controler.getControl_NHOMTAISAN_CAP_V().update_NHOMTAISAN_CAP_V(l);
									}
								} else {
									MessageBox m = new MessageBox(getShell(), SWT.NONE);
									m.setMessage("Không để trống Tên nhóm");
									m.open();
								}
								loadAll();
							}
						}
					} else if (tabFolder.getSelectionIndex() == 3) {
						if (isInsertMode()) {
							if (!text_Name.getText().equals("")) {
								int key = controler.getControl_NHOMTAISAN_CAP_IV().getkey_TaisanCodinhDacbiet();
								if (key <= 0)
									initNhomTaisan();
								NHOM_TAISANCODINH_DACBIET l = new NHOM_TAISANCODINH_DACBIET(key);
								l.setTHOIGIAN_SUDUNG(Integer.valueOf(text_Namsudung.getText()));
								l.setGIA_QUYUOC(Double.valueOf(text_Khauhao.getText()));
								l.setTEN_NHOM_TAISANCODINH_DACBIET(text_Name.getText());
								controler.getControl_NHOM_TAISANCODINH_DACBIET().insert_NHOM_TAISANCODINH_DACBIET(l);
							}
						} else if (isEditMode()) {
							if (!text_Name.getText().equals("")) {
								TreeItem[] items = tree_NhomTaisan_Codinh_dacbiet.getSelection();
								if (items.length > 0) {
									Object o = items[0].getData();
									if (o instanceof NHOM_TAISANCODINH_DACBIET) {
										NHOM_TAISANCODINH_DACBIET l = (NHOM_TAISANCODINH_DACBIET) o;
										l.setTEN_NHOM_TAISANCODINH_DACBIET(text_Name.getText());
										l.setTHOIGIAN_SUDUNG(Integer.valueOf(text_Namsudung.getText()));
										l.setGIA_QUYUOC(Double.valueOf(text_Khauhao.getText()));
										controler.getControl_NHOM_TAISANCODINH_DACBIET()
												.update_NHOM_TAISANCODINH_DACBIET(l);
									}
								}
							}
						}
					} else if (tabFolder.getSelectionIndex() == 2) {
						if (isInsertMode()) {
							if (!text_Name.getText().equals("")) {
								int key = controler.getControl_NHOMTAISAN_CAP_IV().getkey_TaisanCodinhDacthu();
								if (key <= 0)
									initNhomTaisan();
								NHOM_TAISANCODINH_DACTHU l = new NHOM_TAISANCODINH_DACTHU(key);
								l.setTEN_NHOM_TAISANCODINH_DACTHU(text_Name.getText());
								l.setTHOIGIAN_SUDUNG(Integer.valueOf(text_Namsudung.getText()));
								l.setTILE_HAOMON(Double.valueOf(text_Khauhao.getText()));
								controler.getControl_NHOM_TAISANCODINH_DACTHU().insert_NHOM_TAISANCODINH_DACTHU(l);
							}
						} else if (isEditMode()) {
							TreeItem[] items = tree_Nhomtaisan_Codinh_Dacthu.getSelection();
							if (items.length > 0) {
								Object o = items[0].getData();
								if (!text_Name.getText().equals("")) {
									if (o instanceof NHOM_TAISANCODINH_DACTHU) {
										NHOM_TAISANCODINH_DACTHU l = (NHOM_TAISANCODINH_DACTHU) o;
										l.setTEN_NHOM_TAISANCODINH_DACTHU(text_Name.getText());
										l.setTHOIGIAN_SUDUNG(Integer.valueOf(text_Namsudung.getText()));
										l.setTILE_HAOMON(Double.valueOf(text_Khauhao.getText()));
										controler.getControl_NHOM_TAISANCODINH_DACTHU()
												.update_NHOM_TAISANCODINH_DACTHU(l);
									}
								}
							}
						}
					} else if (tabFolder.getSelectionIndex() == 1) {
						if (isInsertMode()) {
							if (!text_Name.getText().equals("")) {
								int key = controler.getControl_NHOMTAISAN_CAP_IV().getkey_TaisanCodinhVohinh();
								if (key <= 0)
									initNhomTaisan();
								NHOM_TAISANCODINH_VOHINH l = new NHOM_TAISANCODINH_VOHINH(key);
								l.setTEN_NHOM_TAISANCODINH_VOHINH(text_Name.getText());
								l.setTHOIGIAN_SUDUNG(Integer.valueOf(text_Namsudung.getText()));
								l.setTILE_HAOMON(Double.valueOf(text_Khauhao.getText()));
								controler.getControl_NHOM_TAISANCODINH_VOHINH().insert_NHOM_TAISANCODINH_VOHINH(l);
							}
						} else if (isEditMode()) {
							TreeItem[] items = tree_NhomTaisan_Codinh_Vohinh.getSelection();
							if (items.length > 0) {
								Object o = items[0].getData();
								if (o instanceof NHOM_TAISANCODINH_VOHINH) {
									if (!text_Name.getText().equals("")) {
										NHOM_TAISANCODINH_VOHINH l = (NHOM_TAISANCODINH_VOHINH) o;
										l.setTEN_NHOM_TAISANCODINH_VOHINH(text_Name.getText());
										l.setTHOIGIAN_SUDUNG(Integer.valueOf(text_Namsudung.getText()));
										l.setTILE_HAOMON(Double.valueOf(text_Khauhao.getText()));
										controler.getControl_NHOM_TAISANCODINH_VOHINH()
												.update_NHOM_TAISANCODINH_VOHINH(l);
									}
								}
							}
						}
					}
					setComplete();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

		});
		btnLu.setText("Hoàn tất");

		Button btnToMiT = new Button(this, SWT.NONE);
		btnToMiT.setImage(user.getIcondata().importIcon);
		btnToMiT.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ImportExcel_NhomTaisan in = new ImportExcel_NhomTaisan(display, user);
					in.open();
					loadAll();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnToMiT.setText("Tạo mới từ excel");

		Button btnng = new Button(this, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		createContents();
	}

	protected void setComplete() throws SQLException {
		MODE = 0;
		text_Name.setText("");
		text_Namsudung.setText("");
		text_Khauhao.setText("");
		text_Name.setEditable(false);
		text_Namsudung.setEditable(false);
		text_Khauhao.setEditable(false);
		loadAll();
	}

	protected void setEditMode() {
		MODE = 1;
		text_Name.setEditable(true);
		text_Namsudung.setEditable(true);
		text_Khauhao.setEditable(true);
	}

	protected void setInsertMode() {
		MODE = 2;
		text_Name.setText("");
		text_Namsudung.setText("");
		text_Khauhao.setText("");
		text_Name.setEditable(true);
		text_Namsudung.setEditable(true);
		text_Khauhao.setEditable(true);
	}

	public boolean isEditMode() {
		if (MODE == 1)
			return true;
		return false;
	}

	public boolean isInsertMode() {
		if (MODE == 2)
			return true;
		return false;
	}

	/**
	 * Create contents of the shell.
	 * 
	 * @throws SQLException
	 */
	protected void createContents() throws SQLException {
		setText("Quản lý Nhóm tài sản");
		setSize(800, 500);
		new FormTemplate().setCenterScreen(getShell());
		init();
	}

	private void init() throws SQLException {
		initNhomTaisan();
		N162 = new Nhomtaisan_162(controler, user.getIcondata());
		N162.setTree_NHOMTaisan_Codinh(tree_NHOMTaisan_Codinh);
		N162.setTree_NhomTaisan_Codinh_dacbiet(tree_NhomTaisan_Codinh_dacbiet);
		N162.setTree_Nhomtaisan_Codinh_Dacthu(tree_Nhomtaisan_Codinh_Dacthu);
		N162.setTree_NhomTaisan_Codinh_Vohinh(tree_NhomTaisan_Codinh_Vohinh);
		N162.initTongitem();
		loadAll();
	}

	private void initNhomTaisan() throws SQLException {
		initNhomTaisanCodinhVohinh();
		initNhomTaisanCodinhDacthu();
		initNhomTaisanCodinhDacbiet();
		// MyTreeEditor te = new MyTreeEditor();
		// te.setEditorNhomtaisanCodinhHuuhinh(tree_NHOMTaisan_Codinh, this);
		// te.setEditorNhomtaisanCodinhVohinh(tree_NhomTaisan_Codinh_Vohinh,
		// this);
		// te.setEditorNhomtaisanCodinhDacthu(tree_Nhomtaisan_Codinh_Dacthu,
		// this);
		// te.setEditorNhomtaisanCodinhDacbiet(tree_NhomTaisan_Codinh_dacbiet,
		// this);
	}

	private void initNhomTaisanCodinhDacbiet() throws SQLException {
		int key_I = controler.getControl_NHOMTAISAN_CAP_I().getkey_TaisanCodinhDacbiet();
		if (key_I > 0) {
		} else {
			key_I = controler.getControl_NHOMTAISAN_CAP_I().insert_getkey_TaisanCodinhDacbiet();
		}
		int key_II = controler.getControl_NHOMTAISAN_CAP_II().getkey_TaisanCodinhDacbiet();
		if (key_II > 0) {
		} else {
			key_II = controler.getControl_NHOMTAISAN_CAP_II().insert_getkey_TaisanCodinhDacbiet(key_I);
		}
		int key_III = controler.getControl_NHOMTAISAN_CAP_III().getkey_TaisanCodinhDacbiet();
		if (key_III > 0) {
		} else {
			key_III = controler.getControl_NHOMTAISAN_CAP_III().insert_getkey_TaisanCodinhDacbiet(key_II);
		}
		int key_IV = controler.getControl_NHOMTAISAN_CAP_IV().getkey_TaisanCodinhDacbiet();
		if (key_IV > 0) {
		} else {
			key_IV = controler.getControl_NHOMTAISAN_CAP_IV().insert_getkey_TaisanCodinhDacbiet(key_III);
		}
	}

	private void initNhomTaisanCodinhDacthu() throws SQLException {
		int key_I = controler.getControl_NHOMTAISAN_CAP_I().getkey_TaisanCodinhDacthu();
		if (key_I > 0) {
		} else {
			key_I = controler.getControl_NHOMTAISAN_CAP_I().insert_getkey_TaisanCodinhDacthu();
		}
		int key_II = controler.getControl_NHOMTAISAN_CAP_II().getkey_TaisanCodinhDacthu();
		if (key_II > 0) {
		} else {
			key_II = controler.getControl_NHOMTAISAN_CAP_II().insert_getkey_TaisanCodinhDacthu(key_I);
		}
		int key_III = controler.getControl_NHOMTAISAN_CAP_III().getkey_TaisanCodinhDacthu();
		if (key_III > 0) {
		} else {
			key_III = controler.getControl_NHOMTAISAN_CAP_III().insert_getkey_TaisanCodinhDacthu(key_II);
		}
		int key_IV = controler.getControl_NHOMTAISAN_CAP_IV().getkey_TaisanCodinhDacthu();
		if (key_IV > 0) {
		} else {
			key_IV = controler.getControl_NHOMTAISAN_CAP_IV().insert_getkey_TaisanCodinhDacthu(key_III);
		}
	}

	private void initNhomTaisanCodinhVohinh() throws SQLException {
		int key_I = controler.getControl_NHOMTAISAN_CAP_I().insert_getkey_TaisanCodinhVohinh();
		if (key_I > 0) {
		} else {
			key_I = controler.getControl_NHOMTAISAN_CAP_I().insert_getkey_TaisanCodinhVohinh();
		}
		int key_II = controler.getControl_NHOMTAISAN_CAP_II().getkey_TaisanCodinhVohinh();
		if (key_II > 0) {
		} else {
			key_II = controler.getControl_NHOMTAISAN_CAP_II().insert_getkey_TaisanCodinhVohinh(key_I);
		}
		int key_III = controler.getControl_NHOMTAISAN_CAP_III().getkey_TaisanCodinhVohinh();
		if (key_III > 0) {
		} else {
			key_III = controler.getControl_NHOMTAISAN_CAP_III().insert_getkey_TaisanCodinhVohinh(key_II);
		}
		int key_IV = controler.getControl_NHOMTAISAN_CAP_IV().getkey_TaisanCodinhVohinh();
		if (key_IV > 0) {
		} else {
			key_IV = controler.getControl_NHOMTAISAN_CAP_IV().insert_getkey_TaisanCodinhVohinh(key_III);
		}
	}

	private void loadAll() throws SQLException {
		N162.initItem_NhomTaisan_Codinh(btnCheckButton.getSelection());
		N162.setItem_NhomTaisan_Codinh_Vohinh();
		N162.setItem_NhomTaisan_Codinh_Dacthu();
		N162.setItem_NhomTaisan_Codinh_Dacbiet();
	};

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
