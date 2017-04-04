package View.AssetManagers.Hoso;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.SWTResourceManager;

import View.Template.FormTemplate;

public class QuanLyTaiSan {

	protected Shell shlHoso;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			QuanLyTaiSan window = new QuanLyTaiSan();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		new FormTemplate().setCenterScreen(shlHoso);
		shlHoso.open();
		shlHoso.layout();
		while (!shlHoso.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlHoso = new Shell();
		shlHoso.setSize(955, 582);
		shlHoso.setText("H\u1ED3 s\u01A1 l\u01B0u tr\u1EEF");
		shlHoso.setLayout(new GridLayout(1, false));

		TabFolder tabFolder_2 = new TabFolder(shlHoso, SWT.NONE);
		tabFolder_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TabItem tbtm_HosoTaiSan = new TabItem(tabFolder_2, SWT.NONE);
		tbtm_HosoTaiSan.setText("H\u1ED3 s\u01A1 T\u00E0i s\u1EA3n");

		Composite composite_3 = new Composite(tabFolder_2, SWT.NONE);
		tbtm_HosoTaiSan.setControl(composite_3);
		GridLayout gl_composite_3 = new GridLayout(1, false);
		gl_composite_3.marginHeight = 0;
		gl_composite_3.marginWidth = 0;
		gl_composite_3.verticalSpacing = 0;
		composite_3.setLayout(gl_composite_3);

		SashForm sashForm_HosoTaisan = new SashForm(composite_3, SWT.NONE);
		sashForm_HosoTaisan.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		SashForm sashForm = new SashForm(sashForm_HosoTaisan, SWT.VERTICAL);

		Tree tree = new Tree(sashForm, SWT.BORDER);
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		sashForm.setWeights(new int[] { 1 });

		Composite composite_ViewListImage = new Composite(sashForm_HosoTaisan, SWT.NONE);
		composite_ViewListImage.setLayout(new GridLayout(2, false));

		ScrolledComposite scrolledComposite = new ScrolledComposite(composite_ViewListImage,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scrolledComposite = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_scrolledComposite.widthHint = 389;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		Composite composite_ListImage = new Composite(scrolledComposite, SWT.NONE);
		composite_ListImage.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		composite_ListImage.setLayout(new GridLayout(4, false));
		int NumOfImage = 50;
		ArrayList<Button> ListImage = new ArrayList<>();
		for (int col = 0; col < NumOfImage; col++) {
			Button btnCheckButton = new Button(composite_ListImage, SWT.CHECK);
			GridData gd_LbImage = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
			gd_LbImage.verticalIndent = 5;
			gd_LbImage.horizontalIndent = 5;
			gd_LbImage.widthHint = 120;
			gd_LbImage.heightHint = 100;
			btnCheckButton.setLayoutData(gd_LbImage);
			btnCheckButton.setText("none");
			Image img = SWTResourceManager.getImage(QuanLyTaiSan.class, "/question_mark.png");
			btnCheckButton.setImage(resize(img, 100, 100));
			btnCheckButton.addMouseTrackListener(new MouseTrackAdapter() {
				@Override
				public void mouseEnter(MouseEvent e) {
					if (btnCheckButton.getSelection()) {
						btnCheckButton.setBackground(new Color(null, 190, 215, 235));
					} else
						btnCheckButton.setBackground(new Color(null, 210, 230, 255));
				}

				@Override
				public void mouseExit(MouseEvent e) {
					if (btnCheckButton.getSelection()) {
						btnCheckButton.setBackground(new Color(null, 190, 215, 235));
					} else
						btnCheckButton.setBackground(null);
				}
			});
			ListImage.add(btnCheckButton);
			// count++;
		}

		scrolledComposite.setContent(composite_ListImage);
		scrolledComposite.setMinSize(composite_ListImage.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		Button btnBChnTt = new Button(composite_ViewListImage, SWT.NONE);
		GridData gd_btnBChnTt = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnBChnTt.widthHint = 90;
		btnBChnTt.setLayoutData(gd_btnBChnTt);
		btnBChnTt.setText("B\u1ECF ch\u1ECDn t\u1EA5t c\u1EA3");

		Button btnChnTtC = new Button(composite_ViewListImage, SWT.NONE);
		GridData gd_btnChnTtC = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnChnTtC.widthHint = 90;
		btnChnTtC.setLayoutData(gd_btnChnTtC);
		btnChnTtC.setText("Ch\u1ECDn t\u1EA5t c\u1EA3");
		composite_ViewListImage.addListener(SWT.Resize, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				Rectangle rect = composite_ViewListImage.getClientArea();
				float width = rect.width;
				try {
					int NumOfColumn = (int) ((width / 110) + 0.5);
					float NumOfRow = (float) ((20 / NumOfColumn) + 0.5);
					System.out.println(width + " " + NumOfColumn + " " + NumOfRow);
					// composite_ListImage.setLayout(new GridLayout(6, false));
					// composite_ListImage.redraw();
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});

		Composite composite_1 = new Composite(sashForm_HosoTaisan, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));

		Composite composite_ViewImage = new Composite(composite_1, SWT.BORDER);
		composite_ViewImage.setLayout(new GridLayout(2, false));
		composite_ViewImage.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		// The scrolled composite
		ScrolledComposite sc = new ScrolledComposite(composite_ViewImage, SWT.H_SCROLL | SWT.V_SCROLL);
		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
		layoutData.grabExcessVerticalSpace = true;
		layoutData.verticalAlignment = SWT.FILL;
		layoutData.verticalSpan = 2;
		layoutData.horizontalSpan = 2;
		layoutData.heightHint = 400;
		sc.setLayoutData(layoutData);

		Label label = new Label(sc, SWT.BORDER | SWT.SHADOW_IN | SWT.CENTER);
		// label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2,
		// 1));
		label.setSize(new Point(15, 17));
		sc.setContent(label);

		Button btnTang = new Button(composite_ViewImage, SWT.NONE);
		btnTang.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Image i = label.getImage();
				if (i.getBounds().width < 900) {
					float width = (float) (i.getBounds().width * 1.2);
					float height = (float) (i.getBounds().height * 1.2);
					label.setSize((int) width, (int) height);
					Image ii = SWTResourceManager.getImage(
							"C:\\Users\\NgocDong\\Desktop\\cay-tung-coi-dep-uon-canh-bang-phuong-phap-bo-van.jpg");
					label.setImage(resize(ii, (int) width, (int) height));
				}
			}
		});

		GridData gd_btnTang = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnTang.widthHint = 40;
		btnTang.setLayoutData(gd_btnTang);
		btnTang.setText("+");
		Image ii = SWTResourceManager
				.getImage("C:\\Users\\NgocDong\\Desktop\\cay-tung-coi-dep-uon-canh-bang-phuong-phap-bo-van.jpg");
		// add item

		Button btnGim = new Button(composite_ViewImage, SWT.NONE);
		btnGim.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Image i = label.getImage();
				if (i.getBounds().width > 90) {
					float width = (float) (i.getBounds().width * 0.8);
					float height = (float) (i.getBounds().height * 0.8);
					label.setSize((int) width, (int) height);
					label.setImage(resize(ii, (int) width, (int) height));
				}
			}
		});
		GridData gd_btnGim = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnGim.widthHint = 40;
		btnGim.setLayoutData(gd_btnGim);
		btnGim.setText("-");

		// Label imgLabel = new Label(sc, SWT.NONE);
		// imgLabel.setImage(image);
		// imgLabel.setSize(imgLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		// sc.setContent(imgLabel);

		// Label l = new Label(composite_3, SWT.NONE);
		// l.setText("Nom actuel :");
		// layoutData = new GridData();
		// layoutData.verticalIndent = 20;
		// l.setLayoutData(layoutData);
		//
		// Text text = new Text(composite_3, SWT.SINGLE | SWT.BORDER |
		// SWT.READ_ONLY);
		// text.setText("The image name");
		// layoutData = new GridData(GridData.FILL_HORIZONTAL);
		// layoutData.verticalIndent = 20;
		// text.setLayoutData(layoutData);

		composite_ViewImage.addListener(SWT.Resize, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				Rectangle rect = composite_ViewImage.getClientArea();
				int width = rect.width;
				int height = rect.height;
				Image i = SWTResourceManager.getImage(
						"C:\\Users\\NgocDong\\Desktop\\cay-tung-coi-dep-uon-canh-bang-phuong-phap-bo-van.jpg");
				float imageWidth = i.getBounds().width;
				float imageHeight = i.getBounds().height;
				float tiso = imageWidth / imageHeight;
				try {
					if (!label.isDisposed() && width > 0 && height > 0) {
						if (!composite_ViewImage.isDisposed()) {
							label.setSize(rect.width + 1, (int) (rect.width / tiso) + 1);
							label.setImage(resize(i, rect.width, (int) (rect.width / tiso)));
						}
					}
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});

		sashForm_HosoTaisan.setWeights(new int[] { 220, 260, 226 });

	}

	private Image resize(Image image, int width, int height) {
		Image Img_scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(Img_scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();
		// image.dispose(); // don't forget about me!
		return Img_scaled;
	}
}
