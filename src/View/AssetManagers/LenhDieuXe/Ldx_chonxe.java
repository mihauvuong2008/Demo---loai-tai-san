package View.AssetManagers.LenhDieuXe;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import Control.PHUONGTIEN_GIAOTHONG.Control_LOAI_XE;
import Control.TAISAN.Control_TAISAN;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;
import View.Template.TreeRowStyle;
import View.Template.TreeTemplate;

import org.eclipse.swt.layout.GridLayout;

public class Ldx_chonxe extends Dialog {

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
	public Ldx_chonxe(Shell parent, int style, NGUOIDUNG user, Point p) {
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
		shlChnNgiL.setSize(450, 300);
		shlChnNgiL.setText("Chọn xe");

		Rectangle rect = shlChnNgiL.getBounds();
		int x1 = p.x + 180;
		int y1 = p.y - rect.height / 2;
		shlChnNgiL.setLocation(x1, y1);
		shlChnNgiL.setLayout(new GridLayout(1, false));

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
							fillOto(p);
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void fillOto(PHONGBAN dv) throws SQLException {
				table.removeAll();
				Control_TAISAN cp = new Control_TAISAN(user);
				ArrayList<TAISAN> tl = cp.get_Taisan_Oto(dv);
				if (tl != null) {
					int i = 1;
					for (TAISAN t : tl) {
						TableItem ti = new TableItem(table, SWT.NONE);
						Control_LOAI_XE clx = new Control_LOAI_XE(user);
						LOAI_XE lx = clx.get_LOAI_XE(t.getPhuongtien_Giaothong().getMA_LOAI_XE());
						ti.setText(new String[] { "" + i, t.getPhuongtien_Giaothong().getBIENSO(), lx.getTEN_DONG_XE(),
								t.getMA_TAISAN() + "" });
						ti.setData(t.getPhuongtien_Giaothong());
						i++;
					}
				}
			}
		});
		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				TableItem[] tableItem = table.getSelection();
				if (tableItem.length > 0) {
					result = (PHUONGTIEN_GIAOTHONG) tableItem[0].getData();
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
		tblclmnTnNgiLi.setText("BIỂN SỐ XE");

		TableColumn tblclmnLoiXe = new TableColumn(table, SWT.NONE);
		tblclmnLoiXe.setWidth(100);
		tblclmnLoiXe.setText("LOẠI XE");

		TableColumn tblclmnTiKhon = new TableColumn(table, SWT.NONE);
		tblclmnTiKhon.setWidth(100);
		tblclmnTiKhon.setText("MÃ PTTS");
		sashForm.setWeights(new int[] { 150, 316 });

		Button btnng = new Button(shlChnNgiL, SWT.NONE);
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnNgiL.dispose();
			}
		});
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
	}

}
