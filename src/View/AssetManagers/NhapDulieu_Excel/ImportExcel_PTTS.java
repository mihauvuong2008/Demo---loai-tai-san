package View.AssetManagers.NhapDulieu_Excel;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import DAO.LOAITAISAN_CAP_III;
import DAO.NGUOIDUNG;
import DAO.NHOMTAISAN_CAP_V;
import DAO.PHONGBAN;
import DAO.TAISAN;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;
import jxl.read.biff.BiffException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;

public class ImportExcel_PTTS extends Shell {
	private Text text;
	private Tree tree;
	private Combo combo;
	private Combo combo_1;
	private static NGUOIDUNG user;
	private ArrayList<TAISAN> result;
	private Combo combo_2;
	private Combo combo_Phongban;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(ImportExcel_PTTS.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ImportExcel_PTTS shell = new ImportExcel_PTTS(display, user);
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
	public ImportExcel_PTTS(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(ImportExcel_PTTS.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		setLayout(new GridLayout(5, false));
		ImportExcel_PTTS.user = user;
		controler = new Controler(user);

		Label lblChonFile = new Label(this, SWT.NONE);
		lblChonFile.setText("File Báo cáo kiểm kê:");

		text = new Text(this, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Button btnChn = new Button(this, SWT.NONE);
		btnChn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE | SWT.CENTER);
				fd.setText("Chọn File Excel Dữ liệu tài sản (theo phòng)");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.xls", "*.xlsx", "*.*" };
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
						buf.append(files[i]);
						try {
							String filename = buf.toString();
							text.setText(filename);
							ExcelReader_ImportPTTS ei = new ExcelReader_ImportPTTS(filename);
							ArrayList<TAISAN> DanhsachTaisan = ei.getData();
							MessageBox m = new MessageBox(getShell());
							m.setText("Hoàn tất");
							m.setMessage("Đã nhận " + DanhsachTaisan.size() + " dòng dữ liệu");
							m.open();
							setResult(DanhsachTaisan);
							fillTree(DanhsachTaisan);
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
		GridData gd_btnChn = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnChn.widthHint = 75;
		btnChn.setLayoutData(gd_btnChn);
		btnChn.setText("Ch\u1ECDn file");

		Label lblPhngBan = new Label(this, SWT.NONE);
		lblPhngBan.setText("Đơn vị quản lý:");

		combo_2 = new Combo(this, SWT.READ_ONLY);
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label = new Label(this, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("Đơn vị sử dụng:");

		combo_Phongban = new Combo(this, SWT.READ_ONLY);
		combo_Phongban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnChnvsd = new Button(this, SWT.NONE);
		btnChnvsd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// MessageBox m = new MessageBox(getShell(), SWT.DIALOG_TRIM);
				// m.setMessage("Chức năng chưa mở");
				// m.open();

				PHONGBAN pb = (PHONGBAN) combo_Phongban.getData(combo_Phongban.getText());

				FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE | SWT.CENTER);
				fd.setText("Chọn File Excel Dữ liệu tài sản (theo phòng)");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.xls", "*.xlsx", "*.*" };
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
						buf.append(files[i]);
						try {
							String filename = buf.toString();

							ExcelReader_ImportPTTS_getDONVI_SUDUNG eig = new ExcelReader_ImportPTTS_getDONVI_SUDUNG(
									filename);
							ArrayList<String> rs = eig.getData();
							int count = 0;
							for (String s : rs) {
								for (TAISAN ts : getResult()) {
									if (ts.getTEN_TAISAN().equals(s) && ts.getMA_DON_VI_SU_DUNG() == 0) {
										ts.setMA_DON_VI_SU_DUNG(pb.getMA_PHONGBAN());
										count++;
										break;// cùng tên hay không?
									}
								}
							}
							MessageBox m = new MessageBox(getShell());
							m.setText("Hoàn tất");
							m.setMessage("Đã nhận " + count + " trên: " + rs.size() + " dòng dữ liệu");
							m.open();
							fillTree(getResult());
						} catch (BiffException | IOException e1) {
							if (e1.getMessage().equals("Unable to recognize OLE stream")) {
								MessageBox m = new MessageBox(getShell(), SWT.ERROR);
								m.setMessage("Hỗ trợ File Excel cho phiên bản Excel 2000 về trước");
								m.open();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnChnvsd.setText("Chọn T/sản");

		Label lbliNhm = new Label(this, SWT.NONE);
		lbliNhm.setText("Chọn nhóm tài sản:");

		combo = new Combo(this, SWT.NONE/* | SWT.RIGHT_TO_LEFT */);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree.getSelection();
				if (til.length > 0 && getResult() != null) {
					ArrayList<TAISAN> d = getResult();
					for (TreeItem ti : til) {
						int index = tree.indexOf(ti);
						TAISAN t = (TAISAN) ti.getData();
						NHOMTAISAN_CAP_V nhomTS = (NHOMTAISAN_CAP_V) combo.getData(combo.getText());
						t.setMA_NHOMTAISAN_CAP_V(nhomTS.getMA_NHOMTAISAN_CAP_V());
						d.set(index, t);
					}
					setResult(d);
					try {
						fillTree(getResult());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		combo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if (!combo.getText().equals(""))
						if (e.keyCode == 13 || e.keyCode == 16777296) {
							NHOMTAISAN_CAP_V l;
							l = controler.getControl_NHOMTAISAN_CAP_V()
									.getNHOMTAISAN_CAP_V(Integer.valueOf(combo.getText()));
							int select = combo
									.indexOf("(" + l.getMA_NHOMTAISAN_CAP_V() + ") - " + l.getTEN_NHOMTAISAN_CAP_V());
							combo.select(select);
							TreeItem[] til = tree.getSelection();
							if (til.length > 0 && getResult() != null) {
								ArrayList<TAISAN> d = getResult();
								for (TreeItem ti : til) {
									int index = tree.indexOf(ti);
									TAISAN t = (TAISAN) ti.getData();
									NHOMTAISAN_CAP_V nhomTS = (NHOMTAISAN_CAP_V) combo.getData(combo.getText());
									t.setMA_NHOMTAISAN_CAP_V(nhomTS.getMA_NHOMTAISAN_CAP_V());
									d.set(index, t);
								}
								setResult(d);
								fillTree(getResult());
							}
						}
				} catch (NumberFormatException | SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(this, SWT.NONE);

		Label lblinV = new Label(this, SWT.NONE);
		lblinV.setText("Chọn loại tài sản:");

		combo_1 = new Combo(this, SWT.NONE /* | SWT.RIGHT_TO_LEFT */);
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree.getSelection();
				if (til.length > 0 && getResult() != null) {
					ArrayList<TAISAN> d = getResult();
					for (TreeItem ti : til) {
						int index = tree.indexOf(ti);
						TAISAN t = (TAISAN) ti.getData();
						LOAITAISAN_CAP_III loaiTS = (LOAITAISAN_CAP_III) combo_1.getData(combo_1.getText());
						t.setMA_LOAITAISAN_CAP_III(loaiTS.getMA_LOAITAISAN_CAP_III());
						d.set(index, t);

					}
					setResult(d);
					try {
						fillTree(getResult());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		combo_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if (!combo_1.getText().equals(""))
						if (e.keyCode == 13 || e.keyCode == 16777296) {
							LOAITAISAN_CAP_III l;
							l = controler.getControl_LOAITAISAN_CAP_III()
									.get_LOAITAISAN_CAP_III(Integer.valueOf(combo_1.getText()));

							int select = combo_1.indexOf(
									"(" + l.getMA_LOAITAISAN_CAP_III() + ") - " + l.getTEN_LOAITAISAN_CAP_III());
							combo_1.select(select);
							TreeItem[] til = tree.getSelection();
							if (til.length > 0 && getResult() != null) {
								ArrayList<TAISAN> d = getResult();
								for (TreeItem ti : til) {
									int index = tree.indexOf(ti);
									TAISAN t = (TAISAN) ti.getData();
									LOAITAISAN_CAP_III loaiTS = (LOAITAISAN_CAP_III) combo_1.getData(combo_1.getText());
									t.setMA_LOAITAISAN_CAP_III(loaiTS.getMA_LOAITAISAN_CAP_III());
									d.set(index, t);

								}
								setResult(d);
								fillTree(getResult());
							}
						}
				} catch (NumberFormatException | SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(this, SWT.NONE);

		tree = new Tree(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));

		TreeColumn trclmnNewColumn = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn.setWidth(50);
		trclmnNewColumn.setText("STT");

		TreeColumn trclmnNewColumn_1 = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn_1.setWidth(200);
		trclmnNewColumn_1.setText("T\u00CAN, \u0110\u1EB6C \u0110I\u1EC2M, M\u00D4 T\u1EA2");

		TreeColumn trclmnNmSDng = new TreeColumn(tree, SWT.NONE);
		trclmnNmSDng.setWidth(100);
		trclmnNmSDng.setText("N\u0102M S\u1EEC D\u1EE4NG");

		TreeColumn trclmnSoLuong = new TreeColumn(tree, SWT.NONE);
		trclmnSoLuong.setWidth(100);
		trclmnSoLuong.setText("SỐ LƯỢNG");

		TreeColumn trclmnNguynGi = new TreeColumn(tree, SWT.RIGHT);
		trclmnNguynGi.setWidth(100);
		trclmnNguynGi.setText("NGUY\u00CAN GI\u00C1");

		TreeColumn trclmnNhmTiSn = new TreeColumn(tree, SWT.NONE);
		trclmnNhmTiSn.setWidth(100);
		trclmnNhmTiSn.setText("NH\u00D3M T\u00C0I S\u1EA2N");

		TreeColumn trclmnLoiTiSn = new TreeColumn(tree, SWT.NONE);
		trclmnLoiTiSn.setWidth(100);
		trclmnLoiTiSn.setText("LO\u1EA0I T\u00C0I S\u1EA2N");

		TreeColumn trclmnMSRing = new TreeColumn(tree, SWT.NONE);
		trclmnMSRing.setWidth(100);
		trclmnMSRing.setText("M\u00C3 S\u1ED0 RI\u00CANG");

		TreeColumn trclmnnV = new TreeColumn(tree, SWT.NONE);
		trclmnnV.setWidth(100);
		trclmnnV.setText("\u0110\u01A0N V\u1ECA");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Button btnNhp = new Button(this, SWT.NONE);
		btnNhp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// reset Phongban
				if (getResult() != null)
					try {
						PHONGBAN dvql = (PHONGBAN) combo_2.getData(combo_2.getText());
						// PHONGBAN dvsd = (PHONGBAN) combo_Phongban.getData(combo_Phongban.getText());
						for (TAISAN t : getResult()) {
							if (t.getMA_DON_VI_SU_DUNG() == 0)
								t.setMA_DON_VI_SU_DUNG(dvql.getMA_PHONGBAN());
							t.setMA_DON_VI_QUAN_LY(dvql.getMA_PHONGBAN());
						}
						if ((!combo.getText().equals("")) && (!combo_1.getText().equals(""))) {
							for (TAISAN t : getResult()) {
								controler.getControl_TAISAN().insert_TAISAN(t);
							}
						}
						dispose();
					} catch (SQLException e1) {
						log.error(e1.getMessage());
						e1.printStackTrace();
					}
			}
		});
		GridData gd_btnNhp = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnNhp.widthHint = 75;
		btnNhp.setLayoutData(gd_btnNhp);
		btnNhp.setText("Ho\u00E0n t\u1EA5t");

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
		buildNhomTaisan();
		buildLoaiTaisan();
		buildDonviQuanly();
		buildDonviSudung();
	}

	private void fillTree(ArrayList<TAISAN> danhsachTaisan) throws SQLException {
		tree.removeAll();
		int stt = 1;
		for (TAISAN t : danhsachTaisan) {
			TreeItem treeItem = new TreeItem(tree, SWT.NONE);
			String Date = "";
			try {
				Date = mdf.getString_FromDateYear(t.getNGAY_SU_DUNG());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			PHONGBAN pb = controler.getControl_PHONGBAN().get_PHONGBAN(t.getMA_DON_VI_SU_DUNG());
			treeItem.setText(new String[] { "" + stt, t.getTEN_TAISAN(), Date, "" + t.getSOLUONG(),
					String.valueOf(t.getNGUYEN_GIA()),
					t.getMA_NHOMTAISAN_CAP_V() == 0 ? "" : (t.getMA_NHOMTAISAN_CAP_V() + ""),
					t.getMA_LOAITAISAN_CAP_III() == 0 ? "" : (t.getMA_LOAITAISAN_CAP_III() + ""),
					t.getMA_TANSAN_KETOAN(), pb == null ? "--" : pb.getTEN_PHONGBAN() });
			treeItem.setData(t);
			stt++;
		}
	}

	private void buildDonviQuanly() throws SQLException {
		ArrayList<PHONGBAN> data = controler.getControl_PHONGBAN().getAllDonvi();
		for (PHONGBAN e : data) {
			combo_2.add("(" + e.getMA_PHONGBAN() + ") - " + e.getTEN_PHONGBAN());
			combo_2.setData("(" + e.getMA_PHONGBAN() + ") - " + e.getTEN_PHONGBAN(), e);
		}
		combo_2.select(0);
	}

	private void buildDonviSudung() throws SQLException {
		ArrayList<PHONGBAN> data = controler.getControl_PHONGBAN().getAllDonvi();
		for (PHONGBAN e : data) {
			combo_Phongban.add("(" + e.getMA_PHONGBAN() + ") - " + e.getTEN_PHONGBAN());
			combo_Phongban.setData("(" + e.getMA_PHONGBAN() + ") - " + e.getTEN_PHONGBAN(), e);
		}
		combo_Phongban.select(0);
	}

	private void buildLoaiTaisan() throws SQLException {
		ArrayList<LOAITAISAN_CAP_III> data = controler.getControl_LOAITAISAN_CAP_III().getAllData();
		for (LOAITAISAN_CAP_III e : data) {
			combo_1.add("(" + e.getMA_LOAITAISAN_CAP_III() + ") - " + e.getTEN_LOAITAISAN_CAP_III());
			combo_1.setData("(" + e.getMA_LOAITAISAN_CAP_III() + ") - " + e.getTEN_LOAITAISAN_CAP_III(), e);
		}
	}

	private void buildNhomTaisan() throws SQLException {
		ArrayList<NHOMTAISAN_CAP_V> data = controler.getControl_NHOMTAISAN_CAP_V().getAllData();
		for (NHOMTAISAN_CAP_V e : data) {
			combo.add("(" + e.getMA_NHOMTAISAN_CAP_V() + ") - " + e.getTEN_NHOMTAISAN_CAP_V());
			combo.setData("(" + e.getMA_NHOMTAISAN_CAP_V() + ") - " + e.getTEN_NHOMTAISAN_CAP_V(), e);
		}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Nhập dữ liệu Tài sản từ File Excel");
		setSize(747, 460);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ArrayList<TAISAN> getResult() {
		return result;
	}

	public void setResult(ArrayList<TAISAN> result) {
		this.result = result;
	}

}
