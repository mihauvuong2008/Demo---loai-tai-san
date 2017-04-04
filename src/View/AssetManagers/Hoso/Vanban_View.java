package View.AssetManagers.Hoso;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.FILESCAN;
import DAO.NGUOIDUNG;
import DAO.TAP_HO_SO;
import DAO.VANBAN;
import View.AssetManagers.Wait.Wait;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;
import net.sf.jasperreports.engine.JRException;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class Vanban_View extends Dialog {

	protected Object result;
	protected Shell shlVanBan;
	private Text text_Sovanban;
	private Text text_Coquanbanhanh;
	private Text text_Trichyeu;
	private Table table;
	private Label lbl_Image;
	private ScrolledComposite scrolledComposite;
	private ArrayList<FILESCAN> fcl;
	private ArrayList<FILESCAN> fcl_save;
	private TAP_HO_SO ths;
	private DateTime dateTime;
	private VANBAN vb;
	private boolean view_mode;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;
	private NGUOIDUNG user;
	private static Log log = LogFactory.getLog(Vanban_View.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param vb
	 * @param view_mode
	 */
	public Vanban_View(Shell parent, int style, NGUOIDUNG user, TAP_HO_SO ths, VANBAN vb, boolean view_mode) {
		super(parent, style);
		this.ths = ths;
		this.vb = vb;
		this.view_mode = view_mode;
		this.user = user;
		controler = new Controler(user);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlVanBan.open();
		shlVanBan.layout();
		Display display = getParent().getDisplay();
		while (!shlVanBan.isDisposed()) {
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
		shlVanBan = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX | SWT.RESIZE);
		shlVanBan.setImage(SWTResourceManager.getImage(Vanban_View.class, "/Actions-document-edit-icon (1).png"));
		shlVanBan.setSize(730, 450);
		new FormTemplate().setCenterScreen(shlVanBan);
		setText("C\u00F4ng vi\u1EC7c c\u1EE7a t\u00F4i");
		shlVanBan.setText("Văn bản");
		if (view_mode) {
			shlVanBan.setText("Văn bản (Xem)");
		} else {
			shlVanBan.setText("Văn bản (Quản lý)");
		}
		shlVanBan.setLayout(new GridLayout(1, false));
		fcl = new ArrayList<>();

		ToolBar toolBar = new ToolBar(shlVanBan, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		ToolItem tltmThmFile = new ToolItem(toolBar, SWT.NONE);
		tltmThmFile.setImage(SWTResourceManager.getImage(Vanban_View.class, "/Actions-insert-image-icon (1).png"));
		tltmThmFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!view_mode) {
					FileDialog fd = new FileDialog(shlVanBan, SWT.OPEN | SWT.MULTI);
					fd.setText("Open");
					fd.setFilterPath("C:/");
					String[] filterExt = { "*.jpg" };
					fd.setFilterExtensions(filterExt);
					String selected = fd.open();
					Wait w = new Wait(shlVanBan.getDisplay());
					w.open();
					Date thisDay = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
					Thread one = new Thread() {
						public void run() {
							try {
								ArrayList<FILESCAN> FILESCAN_l = new ArrayList<>();
								if (selected != null) {
									String[] files = fd.getFileNames();
									for (int i = 0, n = files.length; i < n; i++) {
										// get file
										StringBuffer buf = new StringBuffer();
										buf.append(fd.getFilterPath());
										if (buf.charAt(buf.length() - 1) != File.separatorChar) {
											buf.append(File.separatorChar);
										}
										buf.append(files[i]);
										// System.out.println(files[i]);
										// load image to inpustream
										BufferedImage image = null;
										ByteArrayOutputStream os = new ByteArrayOutputStream() {
											@Override
											public synchronized byte[] toByteArray() {
												return this.buf;
											}
										};
										File fileImage = new File(buf.toString());
										try {
											image = ImageIO.read(fileImage);
											ImageIO.write(image, "jpg", os);
										} catch (IOException e1) {
											e1.printStackTrace();
										}
										// input stream to file scan
										InputStream fis = new ByteArrayInputStream(os.toByteArray());
										// System.out.println(fis.read());
										FILESCAN f = new FILESCAN();
										f.setImage_name(files[i]);
										f.setImage(fis);
										f.setImage_size(fileImage.length());
										f.setNgaytao(thisDay);
										FILESCAN_l.add(f);
									}
								}
								addItemImage_ToTable(FILESCAN_l, w);
							} catch (Exception v) {
								v.printStackTrace();
							}
						}
					};

					one.start();

				}
			}

			private void addItemImage_ToTable(ArrayList<FILESCAN> FILESCAN_l, Wait w) {
				getParent().getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						try {
							w.dispose();
							loadDataTable();
							if (fcl_save == null)
								fcl_save = new ArrayList<>();
							fcl_save.addAll(FILESCAN_l);
							loadDataSave();
						} catch (SQLException e) {
							log.error(e.getMessage());
							e.printStackTrace();
						}
					}

				});
			}
		});
		tltmThmFile.setText("Thêm ảnh");

		ToolItem tltmXa = new ToolItem(toolBar, SWT.NONE);
		tltmXa.setImage(SWTResourceManager.getImage(Vanban_View.class, "/Document-Delete-icon (1).png"));
		tltmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (!view_mode) {
						TableItem[] tableItem = table.getSelection();
						if (tableItem.length > 0) {
							for (TableItem ti : tableItem) {
								FILESCAN f = (FILESCAN) ti.getData();
								controler.getControl_FILESCAN().delete_FILESCAN(f);

							}
							loadDataTable();
						}

					}
					fcl_save = null;
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmXa.setText("Xóa ảnh");

		ToolItem tltmLu = new ToolItem(toolBar, SWT.NONE);
		tltmLu.setImage(SWTResourceManager.getImage(Vanban_View.class, "/Actions-document-save-as-icon.png"));
		tltmLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (!view_mode) {
						if (checkFieldNotnull()) {
							int MAVANBAN;
							if (vb == null) {
								vb = new VANBAN();
								buildVanban(vb);
								MAVANBAN = controler.getControl_VANBAN().insert_VANBAN(vb);
								vb.setMA_VANBAN(MAVANBAN);
							} else {
								buildVanban(vb);
								MAVANBAN = controler.getControl_VANBAN().update_VANBAN(vb);
							}
							if (MAVANBAN > 0 && fcl_save != null)
								for (FILESCAN f : fcl_save) {
									f.setMA_VANBAN(MAVANBAN);
									f.getImage().reset();
									controler.getControl_FILESCAN().insert_IMGAGE(f);
								}
							loadDataTable();
							fcl_save = null;
						} else {
							ShowMessage_BlankField();
						}
					}
				} catch (SQLException | IOException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void buildVanban(VANBAN vb) {
				vb.setSO_VANBAN(text_Sovanban.getText());
				vb.setCO_QUAN_BAN_HANH((text_Coquanbanhanh.getText()));
				if (ths != null)
					vb.setMA_TAPHOSO(ths.getMA_TAPHOSO());
				Date d = mdf.getDate(dateTime);
				vb.setNGAY_BAN_HANH(d);
				vb.setTRICH_YEU(text_Trichyeu.getText());
			}

			private void ShowMessage_BlankField() {
				MessageBox m = new MessageBox(shlVanBan);
				m.setText("Không để trống dữ liệu");
				m.setMessage("Số văn bản, cơ quan ban hành, Trích yếu không để trống");
				m.open();
			}

			private boolean checkFieldNotnull() {
				if (text_Sovanban.getText().equals(""))
					return false;
				if (text_Coquanbanhanh.getText().equals(""))
					return false;
				if (text_Trichyeu.getText().equals(""))
					return false;
				return true;
			}
		});
		tltmLu.setText("L\u01B0u");

		@SuppressWarnings("unused")
		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmIn = new ToolItem(toolBar, SWT.NONE);
		tltmIn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
				}
			}
		});
		tltmIn.setImage(SWTResourceManager.getImage(Vanban_View.class, "/Actions-document-print-icon.png"));
		tltmIn.setText("Xem và In văn bản");

		ToolItem tltmZoomIn = new ToolItem(toolBar, SWT.NONE);
		tltmZoomIn.setText("Phóng ảnh");
		tltmZoomIn.setImage(SWTResourceManager.getImage(Vanban_View.class, "/math-add-icon.png"));
		tltmZoomIn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = table.getSelection();

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

		ToolItem tltmZoomOut = new ToolItem(toolBar, SWT.NONE);
		tltmZoomOut.setText("Thu ảnh");
		tltmZoomOut.setImage(SWTResourceManager.getImage(Vanban_View.class, "/math-minus-icon.png"));
		tltmZoomOut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = table.getSelection();

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

		SashForm sashForm = new SashForm(shlVanBan, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		Group grpThngTinVn = new Group(sashForm_1, SWT.NONE);
		grpThngTinVn.setText("Th\u00F4ng tin v\u0103n b\u1EA3n");
		grpThngTinVn.setLayout(new GridLayout(2, false));

		Label lblSVnBn = new Label(grpThngTinVn, SWT.NONE);
		lblSVnBn.setText("S\u1ED1 v\u0103n b\u1EA3n:");

		text_Sovanban = new Text(grpThngTinVn, SWT.BORDER);
		text_Sovanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblCQuanBan = new Label(grpThngTinVn, SWT.NONE);
		lblCQuanBan.setText("C\u01A1 quan ban h\u00E0nh:");

		text_Coquanbanhanh = new Text(grpThngTinVn, SWT.BORDER);
		text_Coquanbanhanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyBanHnh = new Label(grpThngTinVn, SWT.NONE);
		GridData gd_lblNgyBanHnh = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblNgyBanHnh.verticalIndent = 3;
		lblNgyBanHnh.setLayoutData(gd_lblNgyBanHnh);
		lblNgyBanHnh.setText("Ng\u00E0y ban h\u00E0nh:");

		dateTime = new DateTime(grpThngTinVn, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

		Label lblTrchYu = new Label(grpThngTinVn, SWT.NONE);
		GridData gd_lblTrchYu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblTrchYu.verticalIndent = 3;
		lblTrchYu.setLayoutData(gd_lblTrchYu);
		lblTrchYu.setText("Tr\u00EDch y\u1EBFu:");

		text_Trichyeu = new Text(grpThngTinVn, SWT.BORDER);
		text_Trichyeu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table = new Table(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TableItem[] items = table.getSelection();
				if (items.length > 0) {

					lbl_Image = new Label(scrolledComposite, SWT.BORDER | SWT.CENTER);
					scrolledComposite.setContent(lbl_Image);
					scrolledComposite.setMinSize(lbl_Image.computeSize(SWT.DEFAULT, SWT.DEFAULT));

					FILESCAN fs = (FILESCAN) items[0].getData();

					Image ii = null;
					try {
						fs.getImage().reset();
						ii = new Image(shlVanBan.getDisplay(), fs.getImage());
						fs.getImage().close();
					} catch (Exception e1) {
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
					}
				}
			}
		});
		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(50);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnFile = new TableColumn(table, SWT.NONE);
		tblclmnTnFile.setWidth(220);
		tblclmnTnFile.setText("T\u00CAN FILE");

		TableColumn tblclmnLu = new TableColumn(table, SWT.NONE);
		tblclmnLu.setWidth(100);
		tblclmnLu.setText("ĐÃ LƯU");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem mntmMHnh = new MenuItem(menu, SWT.NONE);
		mntmMHnh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent ev) {

				TableItem[] items = table.getSelection();
				if (items.length > 0) {
					FILESCAN fs = (FILESCAN) items[0].getData();
					Path path = null;
					try {
						path = Files.createTempFile("viewImageTmp", ".jpg");
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					try (FileOutputStream out = new FileOutputStream(path.toFile())) {
						byte[] buffer = new byte[1024];
						int len;
						fs.getImage().reset();
						while ((len = fs.getImage().read(buffer)) != -1) {
							out.write(buffer, 0, len);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						Desktop.getDesktop().open(path.toFile());
						// Files.delete(path);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		mntmMHnh.setText("Mở hình");
		sashForm_1.setWeights(new int[] { 618, 1000 });

		Composite composite = new Composite(sashForm, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		composite.setLayout(gl_composite);

		Composite composite_1 = new Composite(composite, SWT.BORDER);
		composite_1.setLayout(new GridLayout(1, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));

		scrolledComposite = new ScrolledComposite(composite_1, SWT.H_SCROLL | SWT.V_SCROLL | SWT.CENTER);
		scrolledComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
		layoutData.grabExcessVerticalSpace = true;
		layoutData.verticalAlignment = SWT.FILL;
		layoutData.verticalSpan = 2;
		layoutData.horizontalSpan = 2;
		layoutData.heightHint = 400;
		scrolledComposite.setLayoutData(layoutData);

		lbl_Image = new Label(scrolledComposite, SWT.BORDER | SWT.SHADOW_IN | SWT.CENTER);
		lbl_Image.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		scrolledComposite.setContent(lbl_Image);
		sashForm.setWeights(new int[] { 618, 1000 });

		Button btnng = new Button(shlVanBan, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlVanBan.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	private void init() throws SQLException {
		loadDataTable();
		fillField();
	}

	private void fillField() {
		if (vb != null) {
			text_Sovanban.setText(vb.getSO_VANBAN() == null ? "" : vb.getSO_VANBAN());
			text_Coquanbanhanh.setText(vb.getCO_QUAN_BAN_HANH() == null ? "" : vb.getCO_QUAN_BAN_HANH());
			if (vb.getNGAY_BAN_HANH() != null) {
				dateTime.setDay(mdf.getDay(vb.getNGAY_BAN_HANH()));
				dateTime.setMonth(mdf.getMonth(vb.getNGAY_BAN_HANH()));
				dateTime.setYear(mdf.getYear(vb.getNGAY_BAN_HANH()));
			}
			text_Trichyeu.setText(vb.getTRICH_YEU() == null ? "" : vb.getTRICH_YEU());
		}
	}

	protected void loadDataSave() {
		int i = 1;
		if (fcl_save != null)
			for (FILESCAN fcs : fcl_save) {
				TableItem t = new TableItem(table, SWT.NONE);
				t.setText(new String[] { "" + i, fcs.getImage_name(), "chưa Lưu" });
				t.setData(fcs);
				i++;
			}
	}

	protected void loadDataTable() throws SQLException {
		table.removeAll();
		if (vb != null) {
			fcl = controler.getControl_FILESCAN().get_IMAGE_l(vb);
			table.removeAll();
			int i = 1;
			for (FILESCAN fcs : fcl) {
				TableItem t = new TableItem(table, SWT.NONE);
				t.setText(new String[] { "" + i, fcs.getImage_name() });
				t.setData(fcs);
				i++;
			}
		}
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
}
