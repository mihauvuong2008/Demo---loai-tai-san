package View.AssetManagers.LenhDieuXe;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class Ldx_Calendar extends Dialog {

	protected Object result;
	protected Shell shlChnNgy;
	Point p;
	private String title;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param p
	 */
	public Ldx_Calendar(Shell parent, int style, Point p, String title) {
		super(parent, style);
		this.p = p;
		this.title = title;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlChnNgy.open();
		shlChnNgy.layout();
		Display display = getParent().getDisplay();
		while (!shlChnNgy.isDisposed()) {
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
		shlChnNgy = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlChnNgy.setImage(SWTResourceManager.getImage(Ldx_Calendar.class, "/Actions-view-calendar-day-icon.png"));
		shlChnNgy.setSize(364, 225);
		shlChnNgy.setLayout(new GridLayout(1, false));
		shlChnNgy.setText(title);

		Rectangle rect = shlChnNgy.getBounds();
		int x1 = p.x + 180;
		int y1 = p.y - rect.height / 2;
		shlChnNgy.setLocation(x1, y1);

		DateTime dateTime = new DateTime(shlChnNgy, SWT.CALENDAR | SWT.LONG);
		dateTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent event) {

				int day = dateTime.getDay();
				int month = dateTime.getMonth();
				int year = dateTime.getYear();
				int[] arr = new int[3];
				arr[0] = year;
				arr[1] = month;
				arr[2] = day;
				result = arr;
				shlChnNgy.dispose();
			}
		});
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

	}
}
