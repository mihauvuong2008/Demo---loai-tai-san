package View.AssetManagers.CongViec.CongviecDangthuchien;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_NGHIEMTHU;
import DAO.NGUOIDUNG_QUYETTOAN;
import DAO.NGUOIDUNG_THUCHIEN;
import DAO.TAP_HO_SO;
import DAO.user_congviec;
import View.AssetManagers.Hoso.TAPHOSO_View;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ViewPartHoso extends Dialog {

	protected Object result;
	protected Shell shlXemHS;
	private Table table_hoso;
	private final Tree tree_NGUOIDUNG;
	private final Object phanviec;
	private final NGUOIDUNG user;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;
	private static Log log = LogFactory.getLog(ViewPartHoso.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param gdth
	 * @param tree_NGUOIDUNG
	 * @param user
	 */
	public ViewPartHoso(Shell parent, int style, NGUOIDUNG user, Tree tree_NGUOIDUNG, Object phanviec) {
		super(parent, style);
		setText("SWT Dialog");
		this.tree_NGUOIDUNG = tree_NGUOIDUNG;
		this.phanviec = phanviec;
		this.user = user;
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
		shlXemHS.open();
		shlXemHS.layout();
		Display display = getParent().getDisplay();
		while (!shlXemHS.isDisposed()) {
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
		shlXemHS = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX);
		shlXemHS.setImage(
				SWTResourceManager.getImage(ViewPartHoso.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlXemHS.setSize(728, 450);
		new FormTemplate().setCenterScreen(shlXemHS);
		shlXemHS.setText("Xem h\u1ED3 s\u01A1 ph\u1EA7n vi\u1EC7c:");
		shlXemHS.setLayout(new GridLayout(1, false));

		SashForm sashForm = new SashForm(shlXemHS, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table_hoso = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table_hoso.setHeaderVisible(true);
		table_hoso.setLinesVisible(true);

		TableColumn tblclmnStt_1 = new TableColumn(table_hoso, SWT.NONE);
		tblclmnStt_1.setWidth(45);
		tblclmnStt_1.setText("STT");

		TableColumn tblclmnMTpH = new TableColumn(table_hoso, SWT.NONE);
		tblclmnMTpH.setWidth(100);
		tblclmnMTpH.setText("M\u00C3 T\u1EACP H\u1ED2 S\u01A0");

		TableColumn tblclmnTnTpH = new TableColumn(table_hoso, SWT.NONE);
		tblclmnTnTpH.setWidth(100);
		tblclmnTnTpH.setText("T\u00CAN T\u1EACP H\u1ED2 S\u01A0");

		TableColumn tblclmnNgyTo = new TableColumn(table_hoso, SWT.NONE);
		tblclmnNgyTo.setWidth(100);
		tblclmnNgyTo.setText("NG\u00C0Y T\u1EA0O");

		TableColumn tblclmnGiiThiu = new TableColumn(table_hoso, SWT.NONE);
		tblclmnGiiThiu.setWidth(100);
		tblclmnGiiThiu.setText("GI\u1EDAI THI\u1EC6U");

		TableColumn tblclmnNgiTo = new TableColumn(table_hoso, SWT.NONE);
		tblclmnNgiTo.setWidth(100);
		tblclmnNgiTo.setText("NG\u01AF\u1EDCI T\u1EA0O");

		TableColumn tblclmnNgyThamGia = new TableColumn(table_hoso, SWT.NONE);
		tblclmnNgyThamGia.setWidth(120);
		tblclmnNgyThamGia.setText("NG\u00C0Y THAM GIA");

		TableColumn tblclmnHnhThcNhn = new TableColumn(table_hoso, SWT.NONE);
		tblclmnHnhThcNhn.setWidth(150);
		tblclmnHnhThcNhn.setText("H\u00CCNH TH\u1EE8C NH\u1EACN VI\u1EC6C");

		Menu menu = new Menu(table_hoso);
		table_hoso.setMenu(menu);

		MenuItem mntmXemTpH = new MenuItem(menu, SWT.NONE);
		mntmXemTpH.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] tbi = table_hoso.getSelection();
					if (tbi.length > 0) {
						TAP_HO_SO ths = (TAP_HO_SO) tbi[0].getData();
						TAPHOSO_View thss = new TAPHOSO_View(shlXemHS, SWT.DIALOG_TRIM, user, ths, false);
						thss.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemTpH.setText("Xem T\u1EADp h\u1ED3 s\u01A1");
		sashForm.setWeights(new int[] { 1000 });

		Button btnng = new Button(shlXemHS, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlXemHS.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	private void init() throws SQLException {
		Fill_ItemData f = new Fill_ItemData();
		int i = 1;
		for (TreeItem ti : tree_NGUOIDUNG.getItems()) {
			user_congviec uc = (user_congviec) ti.getData();
			if (phanviec instanceof GIAI_DOAN_THUC_HIEN) {
				GIAI_DOAN_THUC_HIEN gdth = (GIAI_DOAN_THUC_HIEN) phanviec;
				NGUOIDUNG_THUCHIEN ndth = controler.getControl_THUCHIEN_CANBO()
						.getNGUOIDUNG_THUCHIEN(uc.getTEN_TAI_KHOAN(), gdth);
				TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth);
				if (ths != null) {
					TableItem tix = new TableItem(table_hoso, SWT.NONE);
					tix.setText(
							new String[] { (i++) + "", ths.getMA_TAPHOSO() + "", ths.getTEN_TAPHOSO(),
									(ths.getNGAY_TAO_TAPHOSO() == null ? "-"
											: mdf.getViewStringDate(ths.getNGAY_TAO_TAPHOSO())),
									uc.getTEN_TAI_KHOAN(), uc.getTEN_TAI_KHOAN(),
									(ths.getNGAY_TAO_TAPHOSO() == null ? "-"
											: mdf.getViewStringDate(ndth.getNGAY_THAM_GIA())),
									f.getString_GIAO_NHANVIEC(ndth) });
					tix.setData(ths);
				}

			} else if (phanviec instanceof GIAI_DOAN_NGHIEM_THU) {
				GIAI_DOAN_NGHIEM_THU gdth = (GIAI_DOAN_NGHIEM_THU) phanviec;
				NGUOIDUNG_NGHIEMTHU ndth = controler.getControl_NGHIEMTHU_CANBO()
						.getNGUOIDUNG_NGHIEMTHU(uc.getTEN_TAI_KHOAN(), gdth);
				TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth);
				if (ths != null) {
					TableItem tix = new TableItem(table_hoso, SWT.NONE);
					tix.setText(
							new String[] { (i++) + "", ths.getMA_TAPHOSO() + "", ths.getTEN_TAPHOSO(),
									(ths.getNGAY_TAO_TAPHOSO() == null ? "-"
											: mdf.getViewStringDate(ths.getNGAY_TAO_TAPHOSO())),
									uc.getTEN_TAI_KHOAN(), uc.getTEN_TAI_KHOAN(),
									(ths.getNGAY_TAO_TAPHOSO() == null ? "-"
											: mdf.getViewStringDate(ndth.getNGAY_THAM_GIA())),
									f.getString_GIAO_NHANVIEC(ndth) });
					tix.setData(ths);
				}
			} else if (phanviec instanceof GIAI_DOAN_QUYET_TOAN) {
				GIAI_DOAN_QUYET_TOAN gdth = (GIAI_DOAN_QUYET_TOAN) phanviec;
				NGUOIDUNG_QUYETTOAN ndth = controler.getControl_QUYETTOAN_CANBO()
						.getNGUOIDUNG_QUYETTOAN(uc.getTEN_TAI_KHOAN(), gdth);
				TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth);
				if (ths != null) {
					TableItem tix = new TableItem(table_hoso, SWT.NONE);
					tix.setText(
							new String[] { (i++) + "", ths.getMA_TAPHOSO() + "", ths.getTEN_TAPHOSO(),
									(ths.getNGAY_TAO_TAPHOSO() == null ? "-"
											: mdf.getViewStringDate(ths.getNGAY_TAO_TAPHOSO())),
									uc.getTEN_TAI_KHOAN(), uc.getTEN_TAI_KHOAN(),
									(ths.getNGAY_TAO_TAPHOSO() == null ? "-"
											: mdf.getViewStringDate(ndth.getNGAY_THAM_GIA())),
									f.getString_GIAO_NHANVIEC(ndth) });
					tix.setData(ths);
				}
			}
		}
	}

}
