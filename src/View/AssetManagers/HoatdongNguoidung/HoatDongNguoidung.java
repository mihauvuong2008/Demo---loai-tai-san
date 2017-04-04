package View.AssetManagers.HoatdongNguoidung;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.SYSTEM_LOG;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class HoatDongNguoidung extends Dialog {

	protected Object result;
	protected Shell shlHotngNgi;
	private DateTime dateTime_Begin;
	private DateTime dateTime_End;
	private Button btnXem;
	private Text keywordText;
	private String keyword;
	private StyledText styledText;
	private Label label_2;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(HoatDongNguoidung.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public HoatDongNguoidung(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
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
		shlHotngNgi.open();
		shlHotngNgi.layout();
		Display display = getParent().getDisplay();
		while (!shlHotngNgi.isDisposed()) {
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
		shlHotngNgi = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlHotngNgi.setImage(SWTResourceManager.getImage(HoatDongNguoidung.class, "/log-icon.png"));
		shlHotngNgi.setSize(728, 450);
		shlHotngNgi.setText("Ho\u1EA1t \u0111\u1ED9ng Ng\u01B0\u1EDDi d\u00F9ng");
		new FormTemplate().setCenterScreen(shlHotngNgi);
		shlHotngNgi.setLayout(new GridLayout(8, false));

		keywordText = new Text(shlHotngNgi, SWT.BORDER);
		keywordText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 7, 1));

		Button btnTm = new Button(shlHotngNgi, SWT.NONE);
		btnTm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				keyword = keywordText.getText();
				styledText.redraw();
			}
		});
		GridData gd_btnTm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnTm.widthHint = 75;
		btnTm.setLayoutData(gd_btnTm);
		btnTm.setText("T\u00ECm");

		styledText = new StyledText(shlHotngNgi, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 8, 1));
		styledText.addLineStyleListener(new LineStyleListener() {
			public void lineGetStyle(LineStyleEvent event) {
				if (keyword == null || keyword.length() == 0) {
					event.styles = new StyleRange[0];
					return;
				}

				String line = event.lineText;
				int cursor = -1;

				LinkedList<StyleRange> list = new LinkedList<StyleRange>();
				while ((cursor = line.indexOf(keyword, cursor + 1)) >= 0) {
					list.add(getHighlightStyle(event.lineOffset + cursor, keyword.length()));
				}

				event.styles = (StyleRange[]) list.toArray(new StyleRange[list.size()]);
			}
		});
		Label label_1 = new Label(shlHotngNgi, SWT.NONE);
		label_1.setText("T\u1EEB ng\u00E0y:");

		dateTime_Begin = new DateTime(shlHotngNgi, SWT.BORDER);

		Label label = new Label(shlHotngNgi, SWT.NONE);
		label.setText("\u0110\u1EBFn ng\u00E0y:");

		dateTime_End = new DateTime(shlHotngNgi, SWT.BORDER);
		init(getDate(dateTime_Begin), getDate(dateTime_End));

		btnXem = new Button(shlHotngNgi, SWT.NONE);
		btnXem.setImage(SWTResourceManager.getImage(HoatDongNguoidung.class, "/Accept-icon (1).png"));
		btnXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					init(getDate(dateTime_Begin), getDate(dateTime_End));
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnXem = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXem.widthHint = 75;
		btnXem.setLayoutData(gd_btnXem);
		btnXem.setText("Xem");

		label_2 = new Label(shlHotngNgi, SWT.SEPARATOR | SWT.VERTICAL);
		GridData gd_label_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label_2.heightHint = 20;
		label_2.setLayoutData(gd_label_2);
		new Label(shlHotngNgi, SWT.NONE);

		Button button = new Button(shlHotngNgi, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlHotngNgi.dispose();
			}
		});
		GridData gd_button = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 75;
		button.setLayoutData(gd_button);
		button.setText("\u0110\u00F3ng");
		init();
	}

	private void init() {
		Date thisDay = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisDay);
		cal.add(Calendar.DATE, -365);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		dateTime_Begin.setDate(year, month, day);
	}

	private void init(Date begin, Date end) throws SQLException {
		styledText.setText("");
		ArrayList<SYSTEM_LOG> slgl = controler.getControl_SYSTEM_LOG().getLOG(begin, end);
		if (slgl != null)
			for (SYSTEM_LOG t : slgl) {
				if (t != null) {
					NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(t.getTEN_TAI_KHOAN());
					String txt = "(" + t.getLOG_ID() + ") " + mdf.getViewStringDateTime_hm_dmy(t.getTHOIGIAN()) + " - "
							+ nd.getTEN_CAN_BO() + " [" + nd.getTEN_TAI_KHOAN() + "]: " + t.getNOIDUNG();
					styledText.append(txt);
					styledText.append("\n");
				}
			}
	}

	private StyleRange getHighlightStyle(int startOffset, int length) {
		StyleRange styleRange = new StyleRange();
		styleRange.start = startOffset;
		styleRange.length = length;
		styleRange.background = shlHotngNgi.getDisplay().getSystemColor(SWT.COLOR_YELLOW);
		return styleRange;
	}

	private Date getDate(DateTime dateTime) {
		if (dateTime != null) {
			return date(dateTime.getDay(), dateTime.getMonth(), dateTime.getYear());
		}
		return null;
	}

	private static Date date(final int day, final int month, final int year) {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		final Date result = calendar.getTime();
		return result;

	}
}
