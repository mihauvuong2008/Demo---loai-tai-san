package View.AssetManagers.ThongBao;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.THONGBAO;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ViewTHONGBAO extends Dialog {

	protected Object result;
	protected Shell shlThngBo;
	private Text text_Noidung;
	private THONGBAO tb;
	private DateTime dateTime_ddMMyyyy;
	private Text lblMathongbao;
	private Text lblNguoigui;
	private Label lblNiDung;
	private Button btnng;
	private Button btnnhDuL;
	private static NGUOIDUNG user;
	private DateTime dateTime_hhmmss;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;
	private static Log log = LogFactory.getLog(ViewTHONGBAO.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param tb
	 */
	public ViewTHONGBAO(Shell parent, int style, THONGBAO tb, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		this.tb = tb;
		ViewTHONGBAO.user = user;
		controler = new Controler(user);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlThngBo.open();
		shlThngBo.layout();
		Display display = getParent().getDisplay();
		while (!shlThngBo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlThngBo = new Shell(getParent(), getStyle());
		shlThngBo.setImage(SWTResourceManager.getImage(ViewTHONGBAO.class, "/messages-icon.png"));
		shlThngBo.setSize(565, 350);
		new FormTemplate().setCenterScreen(shlThngBo);
		shlThngBo.setText("Th\u00F4ng b\u00E1o");
		shlThngBo.setLayout(new GridLayout(5, false));

		Label lblNgyGi = new Label(shlThngBo, SWT.NONE);
		lblNgyGi.setText("Ng\u00E0y g\u1EEDi:");

		dateTime_hhmmss = new DateTime(shlThngBo, SWT.BORDER | SWT.TIME | SWT.LONG);
		dateTime_hhmmss.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		dateTime_ddMMyyyy = new DateTime(shlThngBo, SWT.BORDER | SWT.LONG);
		dateTime_ddMMyyyy.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblMThngBo = new Label(shlThngBo, SWT.NONE);
		lblMThngBo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMThngBo.setText("M\u00E3 Th\u00F4ng b\u00E1o:");

		lblMathongbao = new Text(shlThngBo, SWT.BORDER);
		lblMathongbao.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblMathongbao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		lblMathongbao.setText("Mathongbao");

		Label lblNgiGi = new Label(shlThngBo, SWT.NONE);
		lblNgiGi.setText("Ng\u01B0\u1EDDi g\u1EEDi:");

		lblNguoigui = new Text(shlThngBo, SWT.BORDER);
		lblNguoigui.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNguoigui.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1));
		lblNguoigui.setText("Nguoigui");

		lblNiDung = new Label(shlThngBo, SWT.NONE);
		GridData gd_lblNiDung = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblNiDung.verticalIndent = 3;
		lblNiDung.setLayoutData(gd_lblNiDung);
		lblNiDung.setText("N\u1ED9i dung:");

		text_Noidung = new Text(shlThngBo, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Noidung.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		text_Noidung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		new Label(shlThngBo, SWT.NONE);
		new Label(shlThngBo, SWT.NONE);
		new Label(shlThngBo, SWT.NONE);

		btnnhDuL = new Button(shlThngBo, SWT.CHECK);
		btnnhDuL.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnnhDuL.setText("\u0110\u00E1nh d\u1EA5u l\u00E0 ch\u01B0a \u0111\u1ECDc");

		btnng = new Button(shlThngBo, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					READ_Message();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
				shlThngBo.dispose();
			}

			private void READ_Message() throws SQLException {
				if (!btnnhDuL.getSelection()) {
					Date d = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
					controler.getControl_NGUOIDUNG_NHAN_THONGBAO().set_NgayDocThongbao(tb, user, d);
				} else {
					controler.getControl_NGUOIDUNG_NHAN_THONGBAO().set_NgayDocThongbao(tb, user, null);
				}
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		fillData();
	}

	private void fillData() {
		lblMathongbao.setText(tb.getMA_THONGBAO() + "");
		lblNguoigui.setText(tb.getTEN_TAI_KHOAN_GUI());
		String date = mdf.getViewStringDate(tb.getNGAY_THONGBAO());
		String Time = mdf.getViewStringTime(tb.getNGAY_THONGBAO());
		String[] arr = date.split("-");
		int d = Integer.valueOf(arr[0]);
		int m = Integer.valueOf(arr[1]);
		int y = Integer.valueOf(arr[2]);
		String[] arr2 = Time.split(":");
		int hh = Integer.valueOf(arr2[0]);
		int mm = Integer.valueOf(arr2[1]);
		int ss = Integer.valueOf(arr2[2]);
		dateTime_ddMMyyyy.setDate(y, m - 1, d);
		dateTime_hhmmss.setTime(hh, mm, ss);
		text_Noidung.setText(tb.getNOIDUNG_THONGBAO());
	}
}
