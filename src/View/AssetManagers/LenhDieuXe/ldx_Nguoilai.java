package View.AssetManagers.LenhDieuXe;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import Control.NGUOIDUNG.Control_NGUOIDUNG;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import View.Template.TreeRowStyle;
import View.Template.TreeTemplate;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ldx_Nguoilai extends Dialog {

	protected Object result;
	protected Shell shlChnNgiL;
	private Table table;
	private TreeRowStyle tsl = new TreeRowStyle();
	private NGUOIDUNG user;
	private Point p;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ldx_Nguoilai(Shell parent, int style, NGUOIDUNG user, Point p) {
		super(parent, style);
		setText("SWT Dialog");
		this.user = user;
		this.p = p;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlChnNgiL.open();
		shlChnNgiL.layout();
		Display display = getParent().getDisplay();
		while (!shlChnNgiL.isDisposed()) {
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
		shlChnNgiL = new Shell(getParent(), getStyle());
		shlChnNgiL.setSize(485, 300);
		shlChnNgiL.setText("Chọn người lái");
		shlChnNgiL.setLayout(new GridLayout(1, false));

		Rectangle rect = shlChnNgiL.getBounds();
		int x1 = p.x + 180;
		int y1 = p.y - rect.height / 2;
		shlChnNgiL.setLocation(x1, y1);

		SashForm sashForm = new SashForm(shlChnNgiL, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Tree tree = new Tree(sashForm, SWT.BORDER);
		new TreeTemplate(user).getTreePHONGBAN(tree);
		tsl.setTreeItemHeight(tree, 20);
		tree.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree.getSelection();
					if (items.length > 0) {
						Object o = items[0].getData();
						if (o instanceof PHONGBAN) {
							PHONGBAN p = (PHONGBAN) o;
							FillNguoidung(p);
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void FillNguoidung(PHONGBAN p) throws SQLException {
				table.removeAll();
				Control_NGUOIDUNG cn = new Control_NGUOIDUNG(user);
				ArrayList<NGUOIDUNG> ndl = cn.get_NGUOIDUNG(p);
				int i = 1;
				for (NGUOIDUNG nd : ndl) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(new String[] { i + "", nd.getTEN_CAN_BO(), nd.getTEN_TAI_KHOAN() });
					tableItem.setData(nd);
					i++;
				}
			}
		});
		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				TableItem[] tableItem = table.getSelection();
				if (tableItem.length > 0) {
					result = (NGUOIDUNG) tableItem[0].getData();
					shlChnNgiL.dispose();
				}
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnNgiLi = new TableColumn(table, SWT.NONE);
		tblclmnTnNgiLi.setWidth(150);
		tblclmnTnNgiLi.setText("T\u00CAN NG\u01AF\u1EDCI L\u00C1I");

		TableColumn tblclmnTiKhon = new TableColumn(table, SWT.NONE);
		tblclmnTiKhon.setWidth(100);
		tblclmnTiKhon.setText("T\u00C0I KHO\u1EA2N");
		sashForm.setWeights(new int[] { 150, 316 });

		Button btnng = new Button(shlChnNgiL, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnNgiL.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
	}

}
