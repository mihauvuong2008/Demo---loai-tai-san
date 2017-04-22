package View.AssetManagers;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;

import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import java.sql.SQLException;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.LICH_CONG_TAC;
import DAO.NGUOIDUNG;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;

public class XemLichCongtac extends Dialog {

	protected Object result;
	protected Shell shlLchCngTc;
	private Text text;
	private DateTime dateTime;
	private DateTime dateTime_1;
	private Button btnLu;
	private Controler controler;
	private LICH_CONG_TAC lct;
	MyDateFormat mdf = new MyDateFormat();
	private Button btnThemNhieu;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public XemLichCongtac(Shell parent, int style, NGUOIDUNG user, LICH_CONG_TAC lct) {
		super(parent, style);
		setText("SWT Dialog");
		controler = new Controler(user);
		this.lct = lct;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlLchCngTc.open();
		shlLchCngTc.layout();
		Display display = getParent().getDisplay();
		while (!shlLchCngTc.isDisposed()) {
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
		shlLchCngTc = new Shell(getParent(), getStyle());
		shlLchCngTc.setSize(498, 182);
		shlLchCngTc.setText("Lịch Công tác");
		shlLchCngTc.setLayout(new GridLayout(6, false));
		new FormTemplate().setCenterScreen(shlLchCngTc);

		text = new Text(shlLchCngTc, SWT.BORDER | SWT.READ_ONLY);
		text.setEditable(true);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));

		dateTime_1 = new DateTime(shlLchCngTc, SWT.BORDER | SWT.TIME);

		dateTime = new DateTime(shlLchCngTc, SWT.BORDER);
		dateTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setDayOfWeek();
			}
		});
		new Label(shlLchCngTc, SWT.NONE);

		btnThemNhieu = new Button(shlLchCngTc, SWT.CHECK);
		btnThemNhieu.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnThemNhieu.setText("Thêm nhiều");

		btnLu = new Button(shlLchCngTc, SWT.NONE);
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lct = getLichCongtac();
				try {
					if (lct.getMA_LICH_CONG_TAC() > 0) {
						controler.getControl_LICH_CONG_TAC().update_LICH_CONG_TAC(lct);
					} else {
						controler.getControl_LICH_CONG_TAC().InsertLICH_CONG_TAC(lct);
					}
					text.setText("");
					if (!btnThemNhieu.getSelection())
						shlLchCngTc.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnLu = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnLu.widthHint = 75;
		btnLu.setLayoutData(gd_btnLu);
		btnLu.setText("Lưu");

		Button btnng = new Button(shlLchCngTc, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLchCngTc.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	protected LICH_CONG_TAC getLichCongtac() {
		lct.setNOIDUNG(text.getText());
		lct.setTHOIGIAN(mdf.getDate(dateTime_1, dateTime));
		return lct;
	}

	void setDayOfWeek() {
		Calendar c = mdf.getCalendar(mdf.getDate(dateTime));
		shlLchCngTc.setText("Lịch Công tác: "
				+ (c.get(Calendar.DAY_OF_WEEK) == 1 ? "Chủ nhật" : ("Thứ " + c.get(Calendar.DAY_OF_WEEK))));
	}

	private void init() {
		setDayOfWeek();
		if (lct == null)
			return;
		if (lct.getMA_LICH_CONG_TAC() > 0) {
			btnThemNhieu.dispose();
			text.setText(lct.getNOIDUNG());
			Calendar c = mdf.getCalendar(lct.getTHOIGIAN());
			dateTime.setTime(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), 0);
			dateTime_1.setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		}
	}

}
