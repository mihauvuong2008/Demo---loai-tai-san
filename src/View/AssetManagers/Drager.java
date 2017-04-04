package View.AssetManagers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import DAO.TAISAN;

public class Drager {
	private DragSource source;

	public Drager(Tree tree_DanhsachTaisan) {
		source = new DragSource(tree_DanhsachTaisan, SWT.DRAG);
		source.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {

				// Get the selected items in the drag source
				DragSource ds = (DragSource) event.widget;
				Tree tree = (Tree) ds.getControl();
				TreeItem[] selection = tree.getSelection();
				StringBuffer buff = new StringBuffer();
				for (int i = 0, n = selection.length; i < n; i++) {
					// TreeItem t = selection[i];
					if (selection[i].getData() != null)
						buff.append(((TAISAN) selection[i].getData()).getMA_TAISAN() + " ");
				}
				event.data = buff.toString();
			}
		});
	}

	public void setTranfer(Transfer[] types_DataType) {
		source.setTransfer(types_DataType);
	}

}
