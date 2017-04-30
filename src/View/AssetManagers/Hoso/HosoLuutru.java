package View.AssetManagers.Hoso;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.FILESCAN;
import DAO.HOSO_USER;
import DAO.NGUOIDUNG;
import DAO.TAPHOSO;
import DAO.VANBAN;
import View.AssetManagers.Wait.Wait;
import View.DateTime.MyDateFormat;
import View.Filter.Sortter;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import net.sf.jasperreports.engine.JRException;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;

public class HosoLuutru extends Shell {
	private Text text_Sovanban;
	private Text text_Coqunbanhanh;
	private Text text_Trichyeu;
	private Tree tree_TuHoso;
	private static NGUOIDUNG user;
	private Tree tree_IMG;
	private Label lbl_Image;
	private ScrolledComposite scrolledComposite;
	private Text text_Tentaphoso;
	private Text text_Mota;
	private final int treeItemHeight = 20;
	private int selectedIndex = 0;
	private Text text_TenFile;
	private Text text_Stt;
	private Text text_Mavanban;
	private Text text_MaFile;
	private Text text_Mataphoso;
	private DateTime dateTime;
	private DateTime dateTime_Start;
	private DateTime dateTime_End;
	private Text text_Search;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(HosoLuutru.class);
	private Button btnTimkiemtuongdoi;
	private Tree tree_Congviec;
	private TreeItem trtmTatCa;
	private TreeItem treeItem_Bangiaonoibo;
	private TreeItem treeItem_Baoduong;
	private TreeItem treeItem_Suachua;
	private TreeItem treeItem_Muasam;
	private TreeItem treeItem_Thanhly;
	private TreeItem treeItem_Dangkiem;
	private TreeItem trtmHosoCuatoi;
	private TreeItem trtmTaiLen;
	private TreeItem trtmDanhan;
	private TreeItem trtmCongViec;
	private TreeItem trtmHopThuden;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			HosoLuutru shell = new HosoLuutru(display, user);
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
	 * @param user
	 * @throws SQLException
	 */
	public HosoLuutru(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(user.getIcondata().QuanlyHosoIcon);
		setLayout(new GridLayout(8, false));
		HosoLuutru.user = user;
		TreeRowStyle ts = new TreeRowStyle();
		controler = new Controler(user);

		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 8, 1));

		ToolItem tltmThm = new ToolItem(toolBar, SWT.NONE);
		tltmThm.setImage(user.getIcondata().addIcon);
		tltmThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (tree_TuHoso.isFocusControl()) {
						TreeItem[] items = tree_TuHoso.getSelection();
						if (items.length > 0) {
							Object o = items[0].getData();
							if (o instanceof HOSO_USER) {
								HOSO_USER ths = (HOSO_USER) o;
								TAPHOSO t = new TAPHOSO();
								t.setMA_TAPHOSO(ths.getTaphoso().getMA_TAPHOSO());
								Vanban_View vv = new Vanban_View(getShell(), SWT.NONE, user, t, null, false);
								vv.open();

							} else {
								if (items.length > 0) {
									if (o instanceof VANBAN) {
										VANBAN vb = (VANBAN) o;

										FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.MULTI | SWT.CENTER);
										fd.setText("Chọn File scan (ảnh)");
										fd.setFilterPath("C:/");
										String[] filterExt = { "*.jpg" };
										fd.setFilterExtensions(filterExt);
										String selected = fd.open();

										Wait w = new Wait(Display.getDefault());
										w.open();
										Thread one = new Thread() {
											public void run() {
												try {
													if (selected != null) {
														String[] files = fd.getFileNames();
														for (int i = 0, n = files.length; i < n; i++) {
															StringBuffer buf = new StringBuffer();
															buf.append(fd.getFilterPath());
															if (buf.charAt(buf.length() - 1) != File.separatorChar) {
																buf.append(File.separatorChar);
															}
															buf.append(files[i]);
															BufferedImage image = null;
															try {
																image = ImageIO.read(new File(buf.toString()));
															} catch (IOException e1) {
																e1.printStackTrace();
															}
															ByteArrayOutputStream os = new ByteArrayOutputStream();
															try {
																ImageIO.write(image, "jpg", os);
															} catch (IOException e1) {
																e1.printStackTrace();
															}
															InputStream fis = new ByteArrayInputStream(
																	os.toByteArray());
															FILESCAN f = new FILESCAN();
															f.setImage_name(files[i]);
															f.setImage(fis);
															Date d = controler.getControl_DATETIME_FROM_SERVER()
																	.get_CURRENT_DATETIME();
															f.setNgaytao(d);
															f.setMA_VANBAN(vb.getMA_VANBAN());
															controler.getControl_FILESCAN().insert_IMGAGE(f);
														}
													}
													loadIMG(vb, w);
												} catch (Exception v) {
													v.printStackTrace();
												}
											}

										};
										one.start();
									}
								}
							}
						}

					}
					loadData(mdf.getDate(dateTime_Start), mdf.getDate(dateTime_End), text_Search.getText());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void loadIMG(VANBAN vb, Wait w) {
				getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						try {
							w.dispose();
							tree_IMG.removeAll();
							ArrayList<FILESCAN> fcl = controler.getControl_FILESCAN().get_IMAGE_l(vb);

							int i = 1;
							if (fcl != null)
								for (FILESCAN f : fcl) {
									TreeItem ti = new TreeItem(tree_IMG, SWT.NONE);
									String date = mdf.getViewStringDate(f.getNgaytao());
									ti.setText(new String[] { "" + i, f.getImage_name(), date, "" + f.getStt() });
									ti.setData(f);
									i++;
								}
						} catch (SQLException e) {
							log.error(e.getMessage());
							e.printStackTrace();
						}
					}
				});
			}
		});
		tltmThm.setText("Th\u00EAm");

		ToolItem tltmCpNht = new ToolItem(toolBar, SWT.NONE);
		tltmCpNht.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dateTime.setEnabled(true);
			}
		});
		tltmCpNht.setImage(user.getIcondata().editIcon);
		tltmCpNht.setText("Cập nhật");

		ToolItem tltmXa = new ToolItem(toolBar, SWT.NONE);
		tltmXa.setImage(user.getIcondata().deleteIcon);
		tltmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (tree_TuHoso.isFocusControl()) {
						TreeItem[] items = tree_TuHoso.getSelection();
						if (items.length > 0) {
							Object o = items[0].getData();
							if (o instanceof HOSO_USER) {
								HOSO_USER ths = (HOSO_USER) o;
								MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
								m.setText("Xóa");
								m.setMessage("Bạn muốn Xóa Tập hồ sơ: " + ths.getTaphoso().getTEN_TAPHOSO());
								int rs = m.open();
								switch (rs) {
								case SWT.YES:
									TAPHOSO t = new TAPHOSO();
									t.setMA_TAPHOSO(ths.getTaphoso().getMA_TAPHOSO());
									controler.getControl_TAPHOSO().delete_TAPHOSO(t);
									break;

								default:
									break;
								}
							} else {
								VANBAN vb = (VANBAN) o;
								if (vb == null)
									return;
								MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
								m.setText("Xóa");
								m.setMessage("Bạn muốn Xóa Văn bản: " + vb.getSO_VANBAN());
								int rs = m.open();
								switch (rs) {
								case SWT.YES:
									controler.getControl_VANBAN().delete_VANBAN(vb);
									break;

								default:
									break;
								}
							}
						}
					} else if (tree_IMG.isFocusControl()) {
						TreeItem[] items = tree_IMG.getSelection();
						if (items.length > 0) {
							MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
							m.setText("Xóa");
							m.setMessage("Bạn muốn Xóa File ảnh");
							int rs = m.open();
							switch (rs) {
							case SWT.YES:
								for (TreeItem item : items) {
									FILESCAN fcs = (FILESCAN) item.getData();
									controler.getControl_FILESCAN().delete_FILESCAN(fcs);
									item.dispose();
								}
								break;

							default:
								break;
							}
						}
					}
					loadData(mdf.getDate(dateTime_Start), mdf.getDate(dateTime_End), text_Search.getText());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

		});
		tltmXa.setText("X\u00F3a");

		ToolItem tltmLu = new ToolItem(toolBar, SWT.NONE);
		tltmLu.setImage(user.getIcondata().saveIcon);
		tltmLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					SaveVanBan();
					SaveTaphoso();
					SaveIMG();
					loadData(mdf.getDate(dateTime_Start), mdf.getDate(dateTime_End), text_Search.getText());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmLu.setText("Lưu");

		@SuppressWarnings("unused")
		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmTiLn = new ToolItem(toolBar, SWT.NONE);
		tltmTiLn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TailenTaphoso tlt = new TailenTaphoso(getShell(), SWT.DIALOG_TRIM, user);
				tlt.open();
				TAPHOSO ths = (TAPHOSO) tlt.result;
				if (ths == null)
					return;
				Taphoso_View thsv = new Taphoso_View(getShell(), SWT.DIALOG_TRIM, user, ths, false);
				try {
					thsv.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tltmTiLn.setImage(user.getIcondata().upload16);
		tltmTiLn.setText("Tải lên");

		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (tree_TuHoso.getSelectionCount() <= 0)
					return;
				ArrayList<TAPHOSO> thsl = new ArrayList<>();
				TreeItem til[] = tree_TuHoso.getSelection();
				for (TreeItem treeItem : til) {
					HOSO_USER ths = (HOSO_USER) treeItem.getData();
					thsl.add(ths.getTaphoso());
				}
				GuiTaphoso gths = new GuiTaphoso(getShell(), SWT.DIALOG_TRIM, user, thsl);
				gths.open();
			}
		});
		tltmNewItem.setImage(user.getIcondata().Sendicon16);
		tltmNewItem.setText("Gửi văn bản");

		ToolItem tltmGi = new ToolItem(toolBar, SWT.NONE);
		tltmGi.setImage(user.getIcondata().mailSent16);
		tltmGi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<HOSO_USER> hsrl;
				try {
					hsrl = controler.getControl_Hoso_Row().get_DaguiData(mdf.getDate(dateTime_Start),
							mdf.getDate(dateTime_End), user.getTEN_TAI_KHOAN(),
							btnTimkiemtuongdoi.getSelection() ? "" : text_Search.getText());
					HosoDagui nd = new HosoDagui(getShell(), SWT.DIALOG_TRIM, user, hsrl);
					nd.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		tltmGi.setText("Đã gửi");

		ToolItem tltmHpThn = new ToolItem(toolBar, SWT.NONE);
		tltmHpThn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NhanTaphoso nths = new NhanTaphoso(getShell(), SWT.DIALOG_TRIM, user);
				try {
					nths.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tltmHpThn.setImage(user.getIcondata().ThongbaoChuadocIcon);
		tltmHpThn.setText("Hộp thư đến");

		ToolItem tltmHpTh = new ToolItem(toolBar, SWT.NONE);
		tltmHpTh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ArrayList<HOSO_USER> hsrl = controler.getControl_Hoso_Row().get_DanhanData(
							mdf.getDate(dateTime_Start), mdf.getDate(dateTime_End), user.getTEN_TAI_KHOAN(),
							btnTimkiemtuongdoi.getSelection() ? "" : text_Search.getText());
					HosoDanhan hsdn = new HosoDanhan(getShell(), SWT.DIALOG_TRIM, user, hsrl);
					hsdn.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tltmHpTh.setImage(user.getIcondata().receiveIcon16);
		tltmHpTh.setText("Hộp thư đã nhận");

		@SuppressWarnings("unused")
		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmTmKim = new ToolItem(toolBar, SWT.NONE);
		tltmTmKim.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		tltmTmKim.setImage(user.getIcondata().searchIcon);
		tltmTmKim.setText("Tìm kiếm");

		ToolItem tltmIn = new ToolItem(toolBar, SWT.NONE);
		tltmIn.setImage(user.getIcondata().printIcon);
		tltmIn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem ti[] = tree_TuHoso.getSelection();
				VANBAN vb = null;
				ArrayList<VANBAN> vbl = null;
				if (ti.length > 0) {
					if (ti[0].getItems().length > 0) {
						vbl = new ArrayList<>();
						for (TreeItem ti_ : ti[0].getItems()) {
							VANBAN vb_ = (VANBAN) ti_.getData();
							vbl.add(vb_);
						}
					} else {
						if (ti[0].getParentItem() != null) {
							vb = (VANBAN) ti[0].getData();
						}
					}
				}
				if (vb != null) {
					Relator rl = new Relator();
					ArrayList<BeanRealator> a = new ArrayList<>();
					BeanRealator b;
					try {
						b = new BeanRealator(vb, user);
						a.add(b);
						rl.getRelator(a);
					} catch (SQLException | IOException | JRException e1) {
						e1.printStackTrace();
					}
				} else if (vbl != null) {
					Relator rl = new Relator();
					ArrayList<BeanRealator> a = new ArrayList<>();
					BeanRealator b;
					try {
						b = new BeanRealator(vbl, user);
						a.add(b);
						rl.getRelator(a);
					} catch (SQLException | IOException | JRException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		tltmIn.setText("In Văn bản");
		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.NONE);
		toolItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = tree_IMG.getSelection();
				Image i = lbl_Image.getImage();
				if (i != null)
					if (i.getBounds().width < 900) {
						float width = (float) (i.getBounds().width * 1.2);
						float height = (float) (i.getBounds().height * 1.2);
						lbl_Image.setSize((int) width, (int) height);
						if (items.length > 0) {

							FILESCAN fs = (FILESCAN) items[0].getData();
							try {
								fs.getImage().reset();
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							Image ii = new Image(null, fs.getImage());
							try {
								fs.getImage().close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							lbl_Image.setImage(resize(ii, (int) width, (int) height));
						}
					}
			}
		});
		toolItem_2.setText("Phóng ảnh");
		toolItem_2.setImage(user.getIcondata().buleAddIcon);

		ToolItem toolItem_3 = new ToolItem(toolBar, SWT.NONE);
		toolItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = tree_IMG.getSelection();

				Image i = lbl_Image.getImage();
				if (i != null)
					if (i.getBounds().width > 100) {
						float width = (float) (i.getBounds().width * 0.8);
						float height = (float) (i.getBounds().height * 0.8);
						lbl_Image.setSize((int) width, (int) height);
						if (items.length > 0) {
							FILESCAN fs = (FILESCAN) items[0].getData();
							try {
								fs.getImage().reset();
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							Image ii = new Image(null, fs.getImage());
							try {
								fs.getImage().close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							lbl_Image.setImage(resize(ii, (int) width, (int) height));
						}
					}
			}
		});
		toolItem_3.setText("Thu ảnh");
		toolItem_3.setImage(user.getIcondata().blueMinusIcon);

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 8, 1));

		SashForm sashForm_3 = new SashForm(sashForm, SWT.VERTICAL);

		SashForm sashForm_1 = new SashForm(sashForm_3, SWT.NONE);

		tree_Congviec = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Congviec.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem til[] = tree_Congviec.getSelection();
				if (til.length <= 0)
					return;
				try {
					loadData(mdf.getDate(dateTime_Start), mdf.getDate(dateTime_End), text_Search.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		tree_TuHoso = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tree_TuHoso.setLinesVisible(true);
		tree_TuHoso.setHeaderVisible(true);
		tree_TuHoso.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					clearText();
					TreeItem[] items = tree_TuHoso.getSelection();
					if (items.length <= 0)
						return;
					Object ob = items[0].getData();
					if (ob instanceof VANBAN) {
						VANBAN vb = (VANBAN) ob;
						fillVanban(vb);
						TAPHOSO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(vb.getMA_TAPHOSO());
						fillTaphoso(ths);
						loadIMG(vb, false);
					} else {
						clearText();
						HOSO_USER hrs = (HOSO_USER) ob;
						TAPHOSO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(hrs.getTaphoso().getMA_TAPHOSO());
						fillTaphoso(ths);
						ArrayList<VANBAN> vbl = controler.getControl_VANBAN().get_AllVanban(ths);
						if (vbl != null) {
							for (VANBAN vb : vbl) {
								loadIMG(vb, true);
							}
						}
					}
					selectedIndex = tree_TuHoso.indexOf(items[0]);
					if (selectedIndex < 0) {
						selectedIndex = tree_TuHoso.indexOf(items[0].getParentItem());
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}

			private void clearText() {
				tree_IMG.removeAll();
				lbl_Image.setImage(null);
				text_Mavanban.setText("0");
				text_Sovanban.setText("");
				dateTime.setEnabled(false);
				text_Coqunbanhanh.setText("");
				text_Trichyeu.setText("");
				text_MaFile.setText("0");
				text_TenFile.setText("");
				text_Stt.setText("0");
				text_Mataphoso.setText("0");
				text_Tentaphoso.setText("");
				text_Mota.setText("");
			}
		});
		TreeColumn trclmnStt = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnStt.setWidth(50);
		trclmnStt.setText("Stt");

		TreeColumn trclmnTnTpH = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnTnTpH.setWidth(140);
		trclmnTnTpH.setText("T\u00EAn H\u1ED3 s\u01A1");

		TreeColumn trclmnSLngVn = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnSLngVn.setWidth(100);
		trclmnSLngVn.setText("S\u1ED1 l\u01B0\u1EE3ng");

		TreeColumn trclmnNgyTo = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnNgyTo.setWidth(100);
		trclmnNgyTo.setText("Ng\u00E0y t\u1EA1o");

		TreeColumn trclmnMT = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnMT.setWidth(160);
		trclmnMT.setText("M\u00F4 t\u1EA3");
		ts.setTreeItemHeight(tree_TuHoso, treeItemHeight);

		Menu menu_1 = new Menu(tree_TuHoso);
		tree_TuHoso.setMenu(menu_1);

		MenuItem mntmXemTpH = new MenuItem(menu_1, SWT.NONE);
		mntmXemTpH.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree_TuHoso.getSelection();
					if (items.length <= 0)
						return;
					Object o = items[0].getData();
					if (o instanceof HOSO_USER) {
						HOSO_USER hsr = (HOSO_USER) o;
						TAPHOSO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(hsr.getTaphoso().getMA_TAPHOSO());
						if (ths == null)
							return;
						Taphoso_View thsv = new Taphoso_View(getShell(), SWT.DIALOG_TRIM, user, ths, false);
						thsv.open();
					} else {
						VANBAN vb = (VANBAN) o;
						TAPHOSO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(vb.getMA_TAPHOSO());
						if (ths == null)
							return;
						Taphoso_View thsv = new Taphoso_View(getShell(), SWT.DIALOG_TRIM, user, ths, false);
						thsv.open();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmXemTpH.setText("Xem tập hồ sơ");

		TreeColumn trclmnNewColumn = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnNewColumn.setWidth(100);
		trclmnNewColumn.setText("Người tạo");
		sashForm_1.setWeights(new int[] { 166, 239 });

		SashForm sashForm_2 = new SashForm(sashForm_3, SWT.NONE);

		ExpandBar expandBar = new ExpandBar(sashForm_2, SWT.V_SCROLL);
		expandBar.setSpacing(8);
		expandBar.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));

		ExpandItem xpndtmThngTinVn = new ExpandItem(expandBar, SWT.NONE);
		xpndtmThngTinVn.setImage(user.getIcondata().whiteTextIcon);
		xpndtmThngTinVn.setText("Thông tin văn bản");

		Composite composite_2 = new Composite(expandBar, SWT.NONE);
		xpndtmThngTinVn.setControl(composite_2);
		composite_2.setLayout(new GridLayout(2, false));

		Label lblMVnBn = new Label(composite_2, SWT.NONE);
		lblMVnBn.setText("Mã văn bản:");

		text_Mavanban = new Text(composite_2, SWT.RIGHT);
		text_Mavanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_Mavanban.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Mavanban.setText("0");
		text_Mavanban.setEditable(false);

		Label lblTnVnBn = new Label(composite_2, SWT.NONE);
		lblTnVnBn.setText("S\u1ED1 v\u0103n b\u1EA3n:");

		text_Sovanban = new Text(composite_2, SWT.NONE);
		text_Sovanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyBanHnh = new Label(composite_2, SWT.NONE);
		lblNgyBanHnh.setText("Ng\u00E0y ban h\u00E0nh:");

		dateTime = new DateTime(composite_2, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblCQuanBan = new Label(composite_2, SWT.NONE);
		lblCQuanBan.setText("Đơn vị ban hành:");

		text_Coqunbanhanh = new Text(composite_2, SWT.NONE);
		text_Coqunbanhanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTrchYu = new Label(composite_2, SWT.NONE);
		lblTrchYu.setText("Tr\u00EDch y\u1EBFu:");

		text_Trichyeu = new Text(composite_2, SWT.WRAP | SWT.MULTI);
		text_Trichyeu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		xpndtmThngTinVn.setHeight(150);

		ExpandItem xpndtmFile = new ExpandItem(expandBar, SWT.NONE);
		xpndtmFile.setImage(user.getIcondata().insertImageIcon);
		xpndtmFile.setText("File");

		Composite composite_3 = new Composite(expandBar, SWT.NONE);
		xpndtmFile.setControl(composite_3);
		xpndtmFile.setHeight(xpndtmFile.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		composite_3.setLayout(new GridLayout(2, false));

		Label lblMFile = new Label(composite_3, SWT.NONE);
		GridData gd_lblMFile = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblMFile.widthHint = 78;
		lblMFile.setLayoutData(gd_lblMFile);
		lblMFile.setText("Mã File:");

		text_MaFile = new Text(composite_3, SWT.RIGHT);
		text_MaFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_MaFile.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_MaFile.addVerifyListener(new VerifyListener() {
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
		text_MaFile.setText("0");
		text_MaFile.setEditable(false);

		Label lblTeenFile = new Label(composite_3, SWT.NONE);
		lblTeenFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblTeenFile.setText("Tên File:");

		text_TenFile = new Text(composite_3, SWT.NONE);
		text_TenFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblSThT = new Label(composite_3, SWT.NONE);
		lblSThT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblSThT.setText("Số thứ tự:");

		text_Stt = new Text(composite_3, SWT.RIGHT);
		text_Stt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_Stt.setText("0");

		ExpandItem xpndtmTpHS = new ExpandItem(expandBar, SWT.NONE);
		xpndtmTpHS.setImage(user.getIcondata().greyDocumentIcon);
		xpndtmTpHS.setText("Tập hồ sơ");

		Composite composite_4 = new Composite(expandBar, SWT.NONE);
		xpndtmTpHS.setControl(composite_4);
		xpndtmTpHS.setHeight(120);
		composite_4.setLayout(new GridLayout(2, false));

		Label lblMTpH = new Label(composite_4, SWT.NONE);
		lblMTpH.setText("Mã tập hồ sơ:");

		text_Mataphoso = new Text(composite_4, SWT.NONE);
		text_Mataphoso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_Mataphoso.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Mataphoso.setEditable(false);

		Label lblTpHS = new Label(composite_4, SWT.NONE);
		GridData gd_lblTpHS = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblTpHS.widthHint = 78;
		lblTpHS.setLayoutData(gd_lblTpHS);
		lblTpHS.setText("T\u00EAn T\u1EADp h\u1ED3 s\u01A1:");

		text_Tentaphoso = new Text(composite_4, SWT.NONE);
		text_Tentaphoso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblMT = new Label(composite_4, SWT.NONE);
		lblMT.setText("M\u00F4 t\u1EA3:");

		text_Mota = new Text(composite_4, SWT.NONE);
		text_Mota.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		text_Stt.addVerifyListener(new VerifyListener() {
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

		tree_IMG = new Tree(sashForm_2, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tree_IMG.setLinesVisible(true);
		tree_IMG.setHeaderVisible(true);
		tree_IMG.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TreeItem[] items = tree_IMG.getSelection();
				if (items.length > 0) {

					FILESCAN fs = (FILESCAN) items[0].getData();
					try {
						fs.getImage().reset();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					Image ii = null;
					try {
						ii = new Image(null, fs.getImage());

					} catch (Exception e2) {
						MessageBox m = new MessageBox(getShell(), SWT.ABORT);
						m.setText("Lỗi");
						m.setMessage("Lỗi load image" + e2.toString().substring(0, 200));
						m.open();
					}
					try {
						fs.getImage().close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if (ii != null) {
						Rectangle rect = scrolledComposite.getClientArea();
						int width = rect.width;
						int height = rect.height;
						float imageWidth = ii.getBounds().width;
						float imageHeight = ii.getBounds().height;
						float tiso = imageWidth / imageHeight;

						try {
							if (!lbl_Image.isDisposed() && width > 0 && height > 0) {
								if (!scrolledComposite.isDisposed()) {
									lbl_Image.setSize(rect.width + 1, (int) (rect.width / tiso) + 1);
									lbl_Image.setImage(resize(ii, rect.width, (int) (rect.width / tiso)));
								}
							}
						} catch (Exception e) {
							System.err.println(e);
						}
						text_MaFile.setText(String.valueOf(fs.getImage_id()));
						text_TenFile.setText(fs.getImage_name());
						text_Stt.setText(String.valueOf(fs.getStt()));
					}
				}
			}
		});
		TreeColumn trclmnStt_1 = new TreeColumn(tree_IMG, SWT.NONE);
		trclmnStt_1.setWidth(45);
		trclmnStt_1.setText("Stt");

		TreeColumn trclmnTnFile = new TreeColumn(tree_IMG, SWT.NONE);
		trclmnTnFile.setWidth(140);
		trclmnTnFile.setText("T\u00EAn file");

		TreeColumn trclmnNgyTo_1 = new TreeColumn(tree_IMG, SWT.NONE);
		trclmnNgyTo_1.setWidth(100);
		trclmnNgyTo_1.setText("Ng\u00E0y t\u1EA1o");

		TreeColumn trclmnStt_2 = new TreeColumn(tree_IMG, SWT.NONE);
		trclmnStt_2.setWidth(100);
		trclmnStt_2.setText("Stt");

		ts.setTreeItemHeight(tree_IMG, treeItemHeight);

		Menu menu = new Menu(tree_IMG);
		tree_IMG.setMenu(menu);

		MenuItem mntmnhSTrang = new MenuItem(menu, SWT.NONE);
		mntmnhSTrang.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til = tree_IMG.getItems();
					if (til.length > 0) {
						int i = 1;
						for (TreeItem ti : til) {
							FILESCAN f = (FILESCAN) ti.getData();
							controler.getControl_FILESCAN().update_STT(f, i);

							i++;
						}
						VANBAN vb = controler.getControl_VANBAN()
								.get_Vanban(((FILESCAN) til[0].getData()).getMA_VANBAN());
						loadIMG(vb, false);
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmnhSTrang.setText("Tự đánh số trang");
		sashForm_2.setWeights(new int[] { 166, 239 });
		sashForm_3.setWeights(new int[] { 713, 809 });

		scrolledComposite = new ScrolledComposite(sashForm, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		lbl_Image = new Label(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(lbl_Image);
		scrolledComposite.setMinSize(lbl_Image.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		sashForm.setWeights(new int[] { 419, 392 });

		Label lblTuNgay = new Label(this, SWT.NONE);
		lblTuNgay.setText("Từ ngày: ");

		dateTime_Start = new DateTime(this, SWT.BORDER);

		Label lblnNgy = new Label(this, SWT.NONE);
		lblnNgy.setText("Đến ngày: ");

		dateTime_End = new DateTime(this, SWT.BORDER);

		btnTimkiemtuongdoi = new Button(this, SWT.CHECK);
		btnTimkiemtuongdoi.setText("Tương đối: ");

		text_Search = new Text(this, SWT.BORDER | SWT.RIGHT);
		text_Search.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_Search.setMessage("Tìm kiếm theo tên, mô tả hồ sơ hoặc mã hồ sơ");

		Button btnXem = new Button(this, SWT.NONE);
		btnXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					loadData(mdf.getDate(dateTime_Start), mdf.getDate(dateTime_End), text_Search.getText());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
				return;
			}
		});
		btnXem.setImage(user.getIcondata().acceptIcon);
		GridData gd_btnXem = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXem.widthHint = 75;
		btnXem.setLayoutData(gd_btnXem);
		btnXem.setText("Xem");

		Button btnng = new Button(this, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		createContents();
		init();
	}

	protected void fillVanban(VANBAN vb) {
		text_Mavanban.setText(String.valueOf(vb.getMA_VANBAN()));
		text_Sovanban.setText(vb.getSO_VANBAN());
		if (vb.getNGAY_BAN_HANH() == null) {
			dateTime.setEnabled(false);
		} else {
			dateTime.setDay(mdf.getDay(vb.getNGAY_BAN_HANH()));
			dateTime.setMonth(mdf.getMonth(vb.getNGAY_BAN_HANH()));
			dateTime.setYear(mdf.getYear(vb.getNGAY_BAN_HANH()));
		}
		text_Coqunbanhanh.setText(vb.getCO_QUAN_BAN_HANH());
		text_Trichyeu.setText(vb.getTRICH_YEU());
	}

	protected void fillTaphoso(TAPHOSO ths) {
		if (ths == null)
			return;
		text_Mataphoso.setText(String.valueOf(ths.getMA_TAPHOSO()));
		text_Tentaphoso.setText(ths.getTEN_TAPHOSO());
		text_Mota.setText(ths.getGIOITHIEU_TAPHOSO());
	}

	private void init() throws SQLException {
		createTreeCongviec(tree_Congviec);

		Date thisDay = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisDay);
		cal.add(Calendar.DATE, -365);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		dateTime_Start.setDate(year, month, day);
		loadData(mdf.getDate(dateTime_Start), mdf.getDate(dateTime_End), text_Search.getText());
	}

	private void createTreeCongviec(Tree tree) {

		TreeColumn trclmnCngVic = new TreeColumn(tree, SWT.NONE);
		trclmnCngVic.setWidth(250);
		trclmnCngVic.setText("CÔNG VIỆC");

		trtmTatCa = new TreeItem(tree, SWT.NONE);
		trtmTatCa.setImage(user.getIcondata().buleFolderIcon);
		trtmTatCa.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		trtmTatCa.setText("Hồ sơ công việc");

		treeItem_Bangiaonoibo = new TreeItem(trtmTatCa, 0);
		treeItem_Bangiaonoibo.setImage(user.getIcondata().export_importIcon);
		treeItem_Bangiaonoibo.setText("Hồ sơ Bàn giao nội bộ");
		treeItem_Bangiaonoibo.setExpanded(true);

		treeItem_Baoduong = new TreeItem(trtmTatCa, 0);
		treeItem_Baoduong.setImage(user.getIcondata().BaoduongIcon);
		treeItem_Baoduong.setText("Hồ sơ Bảo dưỡng PTTS");

		treeItem_Suachua = new TreeItem(trtmTatCa, 0);
		treeItem_Suachua.setImage(user.getIcondata().SuachuaIcon);
		treeItem_Suachua.setText("Hồ sơ Sửa chữa PTTS");

		treeItem_Muasam = new TreeItem(trtmTatCa, 0);
		treeItem_Muasam.setImage(user.getIcondata().MuasamIcon);
		treeItem_Muasam.setText("Hồ sơ Mua sắm - Tiếp nhận");

		treeItem_Thanhly = new TreeItem(trtmTatCa, 0);
		treeItem_Thanhly.setImage(user.getIcondata().ThanhlyIcon);
		treeItem_Thanhly.setText("Hồ sơ Thanh lý - Chuyển giao");

		treeItem_Dangkiem = new TreeItem(trtmTatCa, 0);
		treeItem_Dangkiem.setImage(user.getIcondata().allItemIcon);
		treeItem_Dangkiem.setText("Hồ sơ Đăng kiểm PTGT");
		TreeRowStyle trs = new TreeRowStyle();
		trs.setTreeItemHeight(tree_Congviec, 21);
		trtmTatCa.setExpanded(false);

		trtmHosoCuatoi = new TreeItem(tree_Congviec, SWT.NONE);
		trtmHosoCuatoi.setImage(user.getIcondata().myDocument16);
		trtmHosoCuatoi.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		trtmHosoCuatoi.setText("Hồ sơ của tôi");

		trtmCongViec = new TreeItem(trtmHosoCuatoi, SWT.NONE);
		trtmCongViec.setImage(user.getIcondata().working16);
		trtmCongViec.setText("Công việc");

		trtmTaiLen = new TreeItem(trtmHosoCuatoi, SWT.NONE);
		trtmTaiLen.setImage(user.getIcondata().upload16);
		trtmTaiLen.setText("Sưu tập");

		trtmHopThuden = new TreeItem(trtmHosoCuatoi, SWT.NONE);
		trtmHopThuden.setImage(user.getIcondata().ThongbaoChuadocIcon);
		trtmHopThuden.setText("Hộp thư đến");

		trtmDanhan = new TreeItem(trtmHosoCuatoi, SWT.NONE);
		trtmDanhan.setImage(user.getIcondata().receiveIcon16);
		trtmDanhan.setText("Đã nhận");

		trtmHosoCuatoi.setExpanded(true);
	}

	protected void SaveIMG() throws SQLException {
		FILESCAN ths = new FILESCAN();
		ths.setImage_id(Integer.valueOf(text_MaFile.getText()));
		ths.setImage_name(text_TenFile.getText());
		ths.setStt(Integer.valueOf(text_Stt.getText()));
		controler.getControl_FILESCAN().update_IMG(ths);
	}

	protected void SaveTaphoso() throws SQLException {
		if (!text_Mataphoso.getText().equals("")) {
			TAPHOSO ths = new TAPHOSO();
			ths.setMA_TAPHOSO(Integer.valueOf(text_Mataphoso.getText()));
			ths.setTEN_TAPHOSO(text_Tentaphoso.getText());
			ths.setGIOITHIEU_TAPHOSO(text_Mota.getText());
			controler.getControl_TAPHOSO().update_TAPHOSO(ths);
		}
	}

	protected void SaveVanBan() throws SQLException {
		VANBAN vb = new VANBAN();
		vb.setMA_VANBAN(Integer.valueOf(text_Mavanban.getText()));
		vb.setSO_VANBAN(text_Sovanban.getText());
		vb.setCO_QUAN_BAN_HANH(text_Coqunbanhanh.getText());
		Date d = mdf.getDate(dateTime);
		vb.setNGAY_BAN_HANH(d);
		vb.setTRICH_YEU(text_Trichyeu.getText());
		controler.getControl_VANBAN().update_VANBAN(vb);
	}

	private Image resize(Image image, int width, int height) {
		Image Img_scaled = new Image(null, width, height);
		GC gc = new GC(Img_scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();
		image.dispose(); // don't forget about me!
		return Img_scaled;
	}

	protected void loadIMG(VANBAN vb, boolean add) throws SQLException {
		if (!add)
			tree_IMG.removeAll();
		ArrayList<FILESCAN> fcl = controler.getControl_FILESCAN().get_IMAGE_l(vb);
		int i = 1;
		if (fcl != null)
			for (FILESCAN f : fcl) {
				TreeItem ti = new TreeItem(tree_IMG, SWT.NONE);
				String date = mdf.getViewStringDate(f.getNgaytao());
				ti.setText(new String[] { "" + i, f.getImage_name(), date, "" + f.getStt() });
				ti.setData(f);
				i++;
			}
	}

	private void loadData(Date Start, Date End, String SearchString) throws SQLException {
		tree_TuHoso.removeAll();
		boolean tuongdoi = btnTimkiemtuongdoi.getSelection();
		TreeItem[] til = tree_Congviec.getSelection();
		ArrayList<HOSO_USER> hsrl = null;
		if (til.length > 0) {
			if (til[0].equals(trtmTatCa)) {
				hsrl = controler.getControl_Hoso_Row().get_AllData(Start, End, tuongdoi ? "" : SearchString);
			} else if (til[0].equals(treeItem_Bangiaonoibo)) {
				hsrl = controler.getControl_Hoso_Row().get_BangiaonoiboData(Start, End, tuongdoi ? "" : SearchString);
			} else if (til[0].equals(treeItem_Baoduong)) {
				hsrl = controler.getControl_Hoso_Row().get_BaoduongData(Start, End, tuongdoi ? "" : SearchString);
			} else if (til[0].equals(treeItem_Suachua)) {
				hsrl = controler.getControl_Hoso_Row().get_SuachuaData(Start, End, tuongdoi ? "" : SearchString);
			} else if (til[0].equals(treeItem_Muasam)) {
				hsrl = controler.getControl_Hoso_Row().get_MuasamData(Start, End, tuongdoi ? "" : SearchString);
			} else if (til[0].equals(treeItem_Thanhly)) {
				hsrl = controler.getControl_Hoso_Row().get_ThanhlyData(Start, End, tuongdoi ? "" : SearchString);
			} else if (til[0].equals(treeItem_Dangkiem)) {
				hsrl = controler.getControl_Hoso_Row().get_DangkiemData(Start, End, tuongdoi ? "" : SearchString);
			} else if (til[0].equals(trtmHosoCuatoi)) {
				hsrl = controler.getControl_Hoso_Row().get_AllData_HosocuaToi(Start, End, user.getTEN_TAI_KHOAN(),
						tuongdoi ? "" : SearchString);
				ArrayList<HOSO_USER> hsrl2 = controler.getControl_Hoso_Row().get_TailenData(Start, End,
						user.getTEN_TAI_KHOAN(), tuongdoi ? "" : SearchString);
				ArrayList<HOSO_USER> hsrl3 = controler.getControl_Hoso_Row()
						.get_VanbanguidenData(user.getTEN_TAI_KHOAN());
				ArrayList<HOSO_USER> hsrl4 = controler.getControl_Hoso_Row().get_DanhanData(Start, End,
						user.getTEN_TAI_KHOAN(), tuongdoi ? "" : SearchString);
				addHosoUSer(hsrl, hsrl2);
				addHosoUSer(hsrl, hsrl3);
				addHosoUSer(hsrl, hsrl4);
			} else if (til[0].equals(trtmCongViec)) {
				hsrl = controler.getControl_Hoso_Row().get_AllData_HosocuaToi(Start, End, user.getTEN_TAI_KHOAN(),
						tuongdoi ? "" : SearchString);
			} else if (til[0].equals(trtmTaiLen)) {
				hsrl = controler.getControl_Hoso_Row().get_TailenData(Start, End, user.getTEN_TAI_KHOAN(),
						tuongdoi ? "" : SearchString);
			} else if (til[0].equals(trtmHopThuden)) {
				hsrl = controler.getControl_Hoso_Row().get_VanbanguidenData(user.getTEN_TAI_KHOAN());
			} else if (til[0].equals(trtmDanhan)) {
				hsrl = controler.getControl_Hoso_Row().get_DanhanData(Start, End, user.getTEN_TAI_KHOAN(),
						tuongdoi ? "" : SearchString);
			}
		} else {
			hsrl = controler.getControl_Hoso_Row().get_AllData(Start, End, tuongdoi ? "" : SearchString);
		}
		if (hsrl == null)
			return;
		hsrl = new Sortter().SortHOSO_ROW(hsrl, SearchString);
		fillTree(hsrl);
	}

	private void addHosoUSer(ArrayList<HOSO_USER> hsrl, ArrayList<HOSO_USER> hsrl2) {
		if (hsrl2 != null) {
			if (hsrl == null)
				hsrl = new ArrayList<>();
			for (HOSO_USER add : hsrl2) {
				boolean f = true;
				for (HOSO_USER hoso_USER : hsrl) {
					if (add.getTaphoso().getMA_TAPHOSO() == hoso_USER.getTaphoso().getMA_TAPHOSO()) {
						f = false;
					}
				}
				if (f)
					hsrl.add(add);
			}
		}
	}

	private void fillTree(ArrayList<HOSO_USER> hsrl) {
		int i = 1;
		if (hsrl != null)
			for (HOSO_USER hsr : hsrl) {
				String date = hsr.getTaphoso().getNGAY_TAO_TAPHOSO() == null ? "-"
						: mdf.getViewStringDate(hsr.getTaphoso().getNGAY_TAO_TAPHOSO());
				ArrayList<VANBAN> vbl = hsr.getVanbanList();
				int soluong = vbl.size();
				TreeItem ti = new TreeItem(tree_TuHoso, SWT.NONE);
				ti.setText(new String[] { "" + i, hsr.getTaphoso().getTEN_TAPHOSO(), "" + soluong, date,
						hsr.getTaphoso().getGIOITHIEU_TAPHOSO(),
						hsr.getTEN_TAI_KHOAN() == null ? "--" : hsr.getTEN_TAI_KHOAN() });
				ti.setData(hsr);
				int ii = 1;
				for (VANBAN vb : vbl) {
					String date2 = null;
					if (vb.getNGAY_BAN_HANH() != null)
						date2 = mdf.getViewStringDate(vb.getNGAY_BAN_HANH());
					TreeItem Children = new TreeItem(ti, SWT.NONE);
					String ty = vb.getTRICH_YEU();
					int max = 20;
					if (ty.length() < 20)
						max = ty.length();
					Children.setText(new String[] { "" + ii, vb.getSO_VANBAN(), "-", date2,
							vb.getTRICH_YEU().substring(0, max) + (max == 20 ? "..." : "") });
					Children.setData(vb);
					ii++;
				}
				i++;
				ti.setExpanded(false);
			}
		if (selectedIndex > 0 && selectedIndex < tree_TuHoso.getItemCount()) {
			TreeItem treeItem = tree_TuHoso.getItem(selectedIndex);
			tree_TuHoso.setSelection(treeItem);
		}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Tủ Hồ sơ");
		setSize(840, 520);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
