package View.AssetManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import DAO.TAISAN;

public class MainTableBuilder {

	private Tree tree_DanhsachTaisan;
	private Fill_MainForm mainformfiller;
	private MenuItem mntmHienthiNamconlai;
	private MenuItem mntmHienthiGiatriconlai;
	private TreeColumn NAMCONLAI;
	private TreeColumn GIATRICONLAI;
	private TreeColumn MATAISAN;
	private TreeColumn SERINO;
	private TreeColumn NAMSUDUNG;
	private TreeColumn DONVI;
	private TreeColumn MODEL;
	private TreeColumn TEN_PTTS;
	private TreeColumn STT;

	public MainTableBuilder(Tree tree_DanhsachTaisan, Fill_MainForm mainformfiller, MenuItem mntmHienthiNamconlai,
			MenuItem mntmHienthiGiatriconlai) {
		this.tree_DanhsachTaisan = tree_DanhsachTaisan;
		this.mainformfiller = mainformfiller;
		this.mntmHienthiNamconlai = mntmHienthiNamconlai;
		this.mntmHienthiGiatriconlai = mntmHienthiGiatriconlai;
		buildColumn();
		addPackColumnListenner();
		addSortterColumn();
		addmntmListenner();
	}

	private void buildColumn() {
		STT = new TreeColumn(tree_DanhsachTaisan, SWT.CENTER);
		STT.setWidth(80);
		STT.setText("SỐ THỨ TỰ");

		TEN_PTTS = new TreeColumn(tree_DanhsachTaisan, SWT.LEFT);
		TEN_PTTS.setWidth(180);
		TEN_PTTS.setText("TÊN, MÔ TẢ PTTS");

		MODEL = new TreeColumn(tree_DanhsachTaisan, SWT.CENTER);
		MODEL.setMoveable(true);
		MODEL.setWidth(120);
		MODEL.setText("MODEL");

		DONVI = new TreeColumn(tree_DanhsachTaisan, SWT.CENTER);
		DONVI.setMoveable(true);
		DONVI.setWidth(120);
		DONVI.setText("ĐƠN VỊ");

		NAMSUDUNG = new TreeColumn(tree_DanhsachTaisan, SWT.CENTER);
		NAMSUDUNG.setMoveable(true);
		NAMSUDUNG.setWidth(120);
		NAMSUDUNG.setText("NĂM SỬ DỤNG");

		SERINO = new TreeColumn(tree_DanhsachTaisan, SWT.CENTER);
		SERINO.setMoveable(true);
		SERINO.setWidth(150);
		SERINO.setText("SỐ SÊ-RI");

		MATAISAN = new TreeColumn(tree_DanhsachTaisan, SWT.CENTER);
		MATAISAN.setMoveable(true);
		MATAISAN.setWidth(120);
		MATAISAN.setText("MÃ TÀI SẢN");

		NAMCONLAI = new TreeColumn(tree_DanhsachTaisan, SWT.NONE, 7);
		NAMCONLAI.setWidth(0);
		NAMCONLAI.setResizable(false);
		NAMCONLAI.setText("NĂM CÒN LẠI");

		GIATRICONLAI = new TreeColumn(tree_DanhsachTaisan, SWT.NONE, 8);
		GIATRICONLAI.setWidth(0);
		GIATRICONLAI.setResizable(false);
		GIATRICONLAI.setText("GIÁ TRỊ CÒN LẠI");

	}

	private void addmntmListenner() {
		mntmHienthiNamconlai.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (mntmHienthiNamconlai.getSelection()) {
					NAMCONLAI.setWidth(100);
					NAMCONLAI.setResizable(true);
				} else {
					NAMCONLAI.setWidth(0);
					NAMCONLAI.setResizable(false);
				}
			}
		});
		mntmHienthiGiatriconlai.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (mntmHienthiGiatriconlai.getSelection()) {
					GIATRICONLAI.setWidth(100);
					GIATRICONLAI.setResizable(true);
				} else {
					GIATRICONLAI.setWidth(0);
					GIATRICONLAI.setResizable(false);
				}
			}
		});
	}

	private void addSortterColumn() {
		Listener sortListener = new Listener() {
			public void handleEvent(Event e) {
				TreeColumn column = (TreeColumn) e.widget;
				treeRow[] rows = loadData(tree_DanhsachTaisan.getItems());
				if (column == GIATRICONLAI)
					Arrays.sort(rows, BY_VAL);
				if (column == NAMCONLAI)
					Arrays.sort(rows, BY_VAL2);
				if (column == MATAISAN)
					Arrays.sort(rows, BY_STR);
				if (column == NAMSUDUNG)
					Arrays.sort(rows, BY_DATE);
				tree_DanhsachTaisan.setSortColumn(column);
				updateTable(rows);
			}

			private treeRow[] loadData(TreeItem[] items) {
				treeRow[] rs = new treeRow[items.length];
				for (int i = 0; i < rs.length; i++) {
					rs[i] = new treeRow(new String[] { items[i].getText(0), items[i].getText(1), items[i].getText(2),
							items[i].getText(3), items[i].getText(4), items[i].getText(5), items[i].getText(6),
							items[i].getText(7), items[i].getText(8) }, (TAISAN) items[i].getData());
				}
				return rs;
			}
		};
		GIATRICONLAI.addListener(SWT.Selection, sortListener);
		NAMCONLAI.addListener(SWT.Selection, sortListener);
		NAMSUDUNG.addListener(SWT.Selection, sortListener);
		MATAISAN.addListener(SWT.Selection, sortListener);
	}

	private void updateTable(treeRow[] rows) {
		tree_DanhsachTaisan.removeAll();
		ArrayList<TAISAN> data = new ArrayList<>();
		for (treeRow row : rows) {
			data.add(row.getT());
			// TreeItem item = new TreeItem(tree_DanhsachTaisan, SWT.NONE);
			// item.setText(new String[] { row.getData()[0], row.getData()[1],
			// row.getData()[2], row.getData()[3],
			// row.getData()[4], row.getData()[5], row.getData()[6],
			// row.getData()[7], row.getData()[8] });
			// item.setData(row.getT());
		}
		mainformfiller.loadData_ViewTaiSan_MainForm(tree_DanhsachTaisan, data, "0", mntmHienthiNamconlai.getSelection(),
				mntmHienthiGiatriconlai.getSelection());
	}

	class treeRow {
		String[] data;
		TAISAN t;

		public treeRow(String[] data, TAISAN t) {
			this.data = data;
			this.t = t;
		}

		public final String[] getData() {
			return data;
		}

		public final void setData(String[] data) {
			this.data = data;
		}

		public final TAISAN getT() {
			return t;
		}

		public final void setT(TAISAN t) {
			this.t = t;
		}
	}

	public final Comparator<treeRow> BY_VAL = new Comparator<treeRow>() {
		@Override
		public int compare(treeRow o1, treeRow o2) {
			try {
				if (Double.valueOf(o1.getData()[8].split("%")[0]) < Double.valueOf(o2.getData()[8].split("%")[0]))
					return -1;
				if (Double.valueOf(o1.getData()[8].split("%")[0]) > Double.valueOf(o2.getData()[8].split("%")[0]))
					return 1;
			} catch (Exception e) {
				return 0;
			}
			return 0;
		}
	};

	public final Comparator<treeRow> BY_VAL2 = new Comparator<treeRow>() {
		@Override
		public int compare(treeRow o1, treeRow o2) {
			try {
				if (Double.valueOf(o1.getData()[7].split(" ")[0]) < Double.valueOf(o2.getData()[7].split(" ")[0]))
					return -1;
				if (Double.valueOf(o1.getData()[7].split(" ")[0]) > Double.valueOf(o2.getData()[7].split(" ")[0]))
					return 1;
			} catch (Exception e) {
				return 0;
			}
			return 0;
		}
	};

	public final Comparator<treeRow> BY_STR = new Comparator<treeRow>() {
		@Override
		public int compare(treeRow o1, treeRow o2) {
			try {
				return o1.getData()[6].compareTo(o2.getData()[6]);
			} catch (Exception e) {
				return 0;
			}
		}
	};

	public final Comparator<treeRow> BY_DATE = new Comparator<treeRow>() {
		@Override
		public int compare(treeRow o1, treeRow o2) {
			return (o1.getT()).getNGAY_SU_DUNG().compareTo((o2.getT()).getNGAY_SU_DUNG());
		}
	};

	private void addPackColumnListenner() {
		tree_DanhsachTaisan.addListener(SWT.Collapse, new Listener() {

			@Override
			public void handleEvent(Event e) {
				final TreeItem treeItem = (TreeItem) e.item;
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						for (TreeColumn tc : treeItem.getParent().getColumns())
							tc.pack();
					}
				});
			}
		});
		tree_DanhsachTaisan.addListener(SWT.Expand, new Listener() {

			@Override
			public void handleEvent(Event e) {
				final TreeItem treeItem = (TreeItem) e.item;
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						for (TreeColumn tc : treeItem.getParent().getColumns())
							tc.pack();
					}
				});
			}
		});
	}

}
