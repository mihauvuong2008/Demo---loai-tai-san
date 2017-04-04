package View.AssetManagers.Taisan.Phuongtiengiaothong;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;

import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class Phuongtien_Giaothong extends Shell {
	private Text text_Mataisan;
	private Text text_Model;
	private Text text_seri;
	private Text text_Tentaisan;
	private static TAISAN t;
	private static NGUOIDUNG user;
	private Text text_Bienso;
	private Text text_Sokhung;
	private Text text_Somay;
	private Text text_So_Km_xe;
	private Combo combo_Loaiphuongtien;
	private Combo combo_Dongxe;
	private Combo combo_LOAI_NHIENLIEU;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(Phuongtien_Giaothong.class);
	private Text text_Tenphuongtien;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Phuongtien_Giaothong shell = new Phuongtien_Giaothong(display, user, t);
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
	public Phuongtien_Giaothong(Display display, NGUOIDUNG user, TAISAN t) throws SQLException {
		super(display, SWT.CLOSE | SWT.MIN | SWT.RESIZE | SWT.TITLE);
		setImage(
				SWTResourceManager.getImage(Phuongtien_Giaothong.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		setLayout(new GridLayout(3, false));
		Phuongtien_Giaothong.t = t;
		Phuongtien_Giaothong.user = user;
		controler = new Controler(user);

		Group grpThngTinChung = new Group(this, SWT.NONE);
		grpThngTinChung.setLayout(new GridLayout(2, false));
		grpThngTinChung.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		grpThngTinChung.setText("Th\u00F4ng tin chung");

		Label lblMTiSn = new Label(grpThngTinChung, SWT.NONE);
		GridData gd_lblMTiSn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblMTiSn.widthHint = 120;
		lblMTiSn.setLayoutData(gd_lblMTiSn);
		lblMTiSn.setText("M\u00E3 t\u00E0i s\u1EA3n:");

		text_Mataisan = new Text(grpThngTinChung, SWT.BORDER | SWT.RIGHT);
		text_Mataisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Mataisan.setEditable(false);
		text_Mataisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTnMT = new Label(grpThngTinChung, SWT.NONE);
		GridData gd_lblTnMT = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblTnMT.verticalIndent = 3;
		lblTnMT.setLayoutData(gd_lblTnMT);
		lblTnMT.setText("T\u00EAn, m\u00F4 t\u1EA3:");

		text_Tentaisan = new Text(grpThngTinChung, SWT.BORDER | SWT.V_SCROLL | SWT.RIGHT | SWT.MULTI);
		text_Tentaisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tentaisan.setEditable(false);
		text_Tentaisan.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblModel = new Label(grpThngTinChung, SWT.NONE);
		lblModel.setText("MODEL:");

		text_Model = new Text(grpThngTinChung, SWT.BORDER | SWT.RIGHT);
		text_Model.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Model.setEditable(false);
		text_Model.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblSeri = new Label(grpThngTinChung, SWT.NONE);
		lblSeri.setText("SERI:");

		text_seri = new Text(grpThngTinChung, SWT.BORDER | SWT.RIGHT);
		text_seri.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_seri.setEditable(false);
		text_seri.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group grpThngTinXe = new Group(this, SWT.NONE);
		grpThngTinXe.setLayout(new GridLayout(2, false));
		grpThngTinXe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		grpThngTinXe.setText("Th\u00F4ng tin xe");

		Label lblTnPhngTin = new Label(grpThngTinXe, SWT.NONE);
		lblTnPhngTin.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblTnPhngTin.setText("Tên phương tiện:");

		text_Tenphuongtien = new Text(grpThngTinXe, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_Tenphuongtien.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label = new Label(grpThngTinXe, SWT.NONE);
		label.setText("Lo\u1EA1i ph\u01B0\u01A1ng ti\u1EC7n:");

		combo_Loaiphuongtien = new Combo(grpThngTinXe, SWT.READ_ONLY);
		combo_Loaiphuongtien.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					int LoaiPTTG = (int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText());
					init(LoaiPTTG);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		combo_Loaiphuongtien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblLoiXe = new Label(grpThngTinXe, SWT.NONE);
		lblLoiXe.setText("Lo\u1EA1i xe:");

		combo_Dongxe = new Combo(grpThngTinXe, SWT.READ_ONLY);
		combo_Dongxe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_3 = new Label(grpThngTinXe, SWT.NONE);
		label_3.setText("Bi\u1EC3n s\u1ED1 xe:");

		text_Bienso = new Text(grpThngTinXe, SWT.BORDER);
		text_Bienso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_4 = new Label(grpThngTinXe, SWT.NONE);
		label_4.setText("S\u1ED1 khung:");

		text_Sokhung = new Text(grpThngTinXe, SWT.BORDER);
		text_Sokhung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(grpThngTinXe, SWT.NONE);
		label_5.setText("S\u1ED1 m\u00E1y:");

		text_Somay = new Text(grpThngTinXe, SWT.BORDER);
		text_Somay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_6 = new Label(grpThngTinXe, SWT.NONE);
		label_6.setText("S\u1ED1 Km hi\u1EC7n t\u1EA1i:");

		text_So_Km_xe = new Text(grpThngTinXe, SWT.BORDER | SWT.RIGHT);
		text_So_Km_xe.addVerifyListener(new VerifyListener() {
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
		text_So_Km_xe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_So_Km_xe.setText("0");

		Label lblLoiNhinLiu = new Label(grpThngTinXe, SWT.NONE);
		lblLoiNhinLiu.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLoiNhinLiu.setText("Loại nhiên liệu tiêu thụ:");

		combo_LOAI_NHIENLIEU = new Combo(grpThngTinXe, SWT.READ_ONLY);
		combo_LOAI_NHIENLIEU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblThiHnng = new Label(grpThngTinXe, SWT.NONE);
		GridData gd_lblThiHnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblThiHnng.widthHint = 120;
		lblThiHnng.setLayoutData(gd_lblThiHnng);
		lblThiHnng.setText("Th\u1EDDi h\u1EA1n \u0111\u0103ng ki\u1EC3m: ");

		DateTime dateTime = new DateTime(grpThngTinXe, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(this, SWT.NONE);

		Button btnNhp = new Button(this, SWT.NONE);
		btnNhp.setImage(
				SWTResourceManager.getImage(Phuongtien_Giaothong.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnNhp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					PHUONGTIEN_GIAOTHONG p = new PHUONGTIEN_GIAOTHONG();
					p.setMA_PHUONGTIEN_GIAOTHONG("S" + String.valueOf(t.getMA_TAISAN()));
					p.setTEN_PHUONGTIEN_GIAOTHONG(text_Tenphuongtien.getText());
					p.setLOAI_PHUONGTIEN_GIAOTHONG((int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText()));
					if (combo_Dongxe.getData(combo_Dongxe.getText()) != null)
						p.setMA_LOAI_XE(((LOAI_XE) combo_Dongxe.getData(combo_Dongxe.getText())).getMA_LOAI_XE());
					p.setBIENSO(text_Bienso.getText());
					p.setSOKHUNG(text_Sokhung.getText());
					p.setSOMAY(text_Somay.getText());
					p.setMA_TAISAN(t.getMA_TAISAN());
					Date date = mdf.getDate(dateTime);
					p.setSO_KM_XE(Integer.valueOf(text_So_Km_xe.getText()));
					p.setXANG_DAU((int) combo_LOAI_NHIENLIEU.getData(combo_LOAI_NHIENLIEU.getText()));
					p.setTHOIHAN_DANGKIEM(date);
					if (p.getMA_LOAI_XE() != 0) {
						controler.getControl_PHUONGTIEN_GIAOTHONG().insert_PHUONGTIEN_GIAOTHONG(p);
						dispose();
					} else {
						MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
						m.setText("Lỗi");
						m.setMessage("Điền đầy đủ thông tin");
						m.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnNhp = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnNhp.widthHint = 75;
		btnNhp.setLayoutData(gd_btnNhp);
		btnNhp.setText("Nh\u1EADp");

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
		init(1);
	}

	private void init(int loaiPTTG) throws SQLException {
		Fill_ItemData f = new Fill_ItemData();
		text_Mataisan.setText(String.valueOf(t.getMA_TAISAN()));
		text_Tentaisan.setText(t.getTEN_TAISAN());
		text_Model.setText(t.getMODEL());
		text_seri.setText(t.getSERI());
		f.setComboBox_LOAIPHUONGTIEN_Phuongtien_Giaothong(combo_Loaiphuongtien, loaiPTTG);
		f.setComboBox_LOAI_NHIENLIEU(combo_LOAI_NHIENLIEU);
		if ((int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText()) == f.getInt_Oto()) {
			ArrayList<LOAI_XE> pa = controler.getControl_LOAI_XE().get_AllLOAI_XE_OTO();
			f.setComboBox_Dongxe(combo_Dongxe, pa);
		} else if ((int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText()) == f.getInt_Xemay()) {
			ArrayList<LOAI_XE> pa = controler.getControl_LOAI_XE().get_AllLOAI_XE_XEMAY();
			f.setComboBox_Dongxe(combo_Dongxe, pa);
		}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Nh\u1EADp th\u00F4ng tin ph\u01B0\u01A1ng ti\u1EC7n giao th\u00F4ng");
		setSize(350, 480);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
