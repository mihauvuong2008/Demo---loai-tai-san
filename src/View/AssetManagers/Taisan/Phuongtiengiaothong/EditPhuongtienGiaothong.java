package View.AssetManagers.Taisan.Phuongtiengiaothong;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class EditPhuongtienGiaothong extends Dialog {

	protected Object result;
	protected Shell shlCpNhtPhng;
	private Text text_Mataisan;
	private Text text_TenTaisan;
	private Text text_Model;
	private Text text_Seri;
	private Text text_Biensoxe;
	private Text text_Sokhung;
	private Text text_Somay;
	private Text text_Sokm;
	private Combo combo_LoaiPhuongtien;
	private Combo combo_Loaixe;
	private Combo combo_LoainhienLieu;
	private DateTime dateTime;
	private TAISAN t;
	private PHUONGTIEN_GIAOTHONG ptgt;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(EditPhuongtienGiaothong.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 * @throws SQLException
	 */
	public EditPhuongtienGiaothong(Shell parent, int style, NGUOIDUNG user, TAISAN t) throws SQLException {
		super(parent, style);
		setText("SWT Dialog");
		this.t = t;
		controler = new Controler(user);
		ptgt = controler.getControl_PHUONGTIEN_GIAOTHONG().get_PHUONGTIEN_GIAOTHONG_FromTaisan(t.getMA_TAISAN());
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlCpNhtPhng.open();
		shlCpNhtPhng.layout();
		Display display = getParent().getDisplay();
		while (!shlCpNhtPhng.isDisposed()) {
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
		shlCpNhtPhng = new Shell(getParent(), getStyle());
		shlCpNhtPhng.setImage(SWTResourceManager.getImage(EditPhuongtienGiaothong.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlCpNhtPhng.setSize(350, 480);
		new FormTemplate().setCenterScreen(shlCpNhtPhng);
		shlCpNhtPhng.setText("Cập nhật Phương tiện giao thông");
		shlCpNhtPhng.setLayout(new GridLayout(2, false));

		Group group = new Group(shlCpNhtPhng, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		group.setText("Thông tin chung");
		group.setLayout(new GridLayout(2, false));

		Label label = new Label(group, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("Mã tài sản:");

		text_Mataisan = new Text(group, SWT.BORDER | SWT.RIGHT);
		text_Mataisan.setEditable(false);
		text_Mataisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Mataisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_1 = new Label(group, SWT.NONE);
		GridData gd_label_1 = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_label_1.verticalIndent = 3;
		label_1.setLayoutData(gd_label_1);
		label_1.setText("Tên, mô tả:");

		text_TenTaisan = new Text(group, SWT.BORDER | SWT.V_SCROLL | SWT.RIGHT | SWT.MULTI);
		text_TenTaisan.setEditable(false);
		text_TenTaisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_TenTaisan.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("MODEL:");

		text_Model = new Text(group, SWT.BORDER | SWT.RIGHT);
		text_Model.setEditable(false);
		text_Model.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Model.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("SERI:");

		text_Seri = new Text(group, SWT.BORDER | SWT.RIGHT);
		text_Seri.setEditable(false);
		text_Seri.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Seri.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group group_1 = new Group(shlCpNhtPhng, SWT.NONE);
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		group_1.setText("Thông tin xe");
		group_1.setLayout(new GridLayout(2, false));
		group_1.setBounds(0, 37, 334, 260);

		Label label_4 = new Label(group_1, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("Loại phương tiện:");

		combo_LoaiPhuongtien = new Combo(group_1, SWT.READ_ONLY);
		combo_LoaiPhuongtien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("Loại xe:");

		combo_Loaixe = new Combo(group_1, SWT.READ_ONLY);
		combo_Loaixe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_6 = new Label(group_1, SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_6.setText("Biển số xe:");

		text_Biensoxe = new Text(group_1, SWT.BORDER);
		text_Biensoxe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_7.setText("Số khung:");

		text_Sokhung = new Text(group_1, SWT.BORDER);
		text_Sokhung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_8 = new Label(group_1, SWT.NONE);
		label_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_8.setText("Số máy:");

		text_Somay = new Text(group_1, SWT.BORDER);
		text_Somay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_9 = new Label(group_1, SWT.NONE);
		label_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_9.setText("Số Km hiện tại:");

		text_Sokm = new Text(group_1, SWT.BORDER | SWT.RIGHT);
		text_Sokm.addVerifyListener(new VerifyListener() {
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
		text_Sokm.setText("0");
		text_Sokm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_10 = new Label(group_1, SWT.NONE);
		label_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_10.setText("Loại nhiên liệu tiêu thụ:");

		combo_LoainhienLieu = new Combo(group_1, SWT.READ_ONLY);
		combo_LoainhienLieu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_11 = new Label(group_1, SWT.NONE);
		label_11.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_11.setText("Thời hạn đăng kiểm: ");

		dateTime = new DateTime(group_1, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnLu = new Button(shlCpNhtPhng, SWT.NONE);
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					controler.getControl_PHUONGTIEN_GIAOTHONG()
							.update_PHUONGTIEN_GIAOTHONG(t.getPhuongtien_Giaothong());
				} catch (Exception e2) {
					log.error(e2.getMessage());
				}
			}
		});
		btnLu.setImage(SWTResourceManager.getImage(EditPhuongtienGiaothong.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
		GridData gd_btnLu = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnLu.widthHint = 75;
		btnLu.setLayoutData(gd_btnLu);
		btnLu.setText("Lưu");
		btnLu.setBounds(179, 302, 75, 25);

		Button button_1 = new Button(shlCpNhtPhng, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlCpNhtPhng.dispose();
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 75;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("Đóng");
		button_1.setBounds(259, 302, 75, 25);
		init(ptgt.getLOAI_PHUONGTIEN_GIAOTHONG());
	}

	private void init(int loaiPTTG) throws SQLException {
		Fill_ItemData f = new Fill_ItemData();
		t = controler.getControl_TAISAN().get_Taisan(t.getMA_TAISAN());
		text_Mataisan.setText(String.valueOf(t.getMA_TAISAN()));
		text_TenTaisan.setText(t.getTEN_TAISAN());
		text_Model.setText(t.getMODEL());
		text_Seri.setText(t.getSERI());
		f.setComboBox_LOAIPHUONGTIEN_Phuongtien_Giaothong(combo_LoaiPhuongtien, loaiPTTG);
		f.setComboBox_LOAI_NHIENLIEU(combo_LoainhienLieu);
		if ((int) combo_LoaiPhuongtien.getData(combo_LoaiPhuongtien.getText()) == f.getInt_Oto()) {
			ArrayList<LOAI_XE> pa = controler.getControl_LOAI_XE().get_AllLOAI_XE_OTO();
			f.setComboBox_Dongxe(combo_Loaixe, pa);
			LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(ptgt.getMA_LOAI_XE());
			combo_Loaixe.setText(lx.getTEN_DONG_XE());
		} else if ((int) combo_LoaiPhuongtien.getData(combo_LoaiPhuongtien.getText()) == f.getInt_Xemay()) {
			ArrayList<LOAI_XE> pa = controler.getControl_LOAI_XE().get_AllLOAI_XE_XEMAY();
			f.setComboBox_Dongxe(combo_Loaixe, pa);
			LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(ptgt.getMA_LOAI_XE());
			combo_Loaixe.setText(lx.getTEN_DONG_XE());
		}
		text_Biensoxe.setText(ptgt.getBIENSO());
		text_Sokm.setText(ptgt.getSO_KM_XE() + "");
		text_Sokhung.setText(ptgt.getSOKHUNG());
		text_Somay.setText(ptgt.getSOMAY());
		combo_LoainhienLieu.setText(f.get_String_LOAI_NHIENLIEU(ptgt.getXANG_DAU()));
		dateTime.setDate(mdf.getYear(ptgt.getTHOIHAN_DANGKIEM()), mdf.getMonth(ptgt.getTHOIHAN_DANGKIEM()),
				mdf.getDay(ptgt.getTHOIHAN_DANGKIEM()));
	}

}
