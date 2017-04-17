package View.AssetManagers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.LOAITAISAN_CAP_I;
import DAO.LOAITAISAN_CAP_II;
import DAO.LOAITAISAN_CAP_III;

public class Loaitaisan_19BCA {
	Controler controler;
	private TreeItem tong_Item;

	public Loaitaisan_19BCA(Tree tree_LoaiTaisan, Controler controler, Icondata icondata) {
		this.controler = controler;
		tong_Item = initTongLoaiItem(tree_LoaiTaisan, icondata);
	}

	public TreeItem initTongLoaiItem(Tree tree_LoaiTaisan, Icondata icondata) {
		TreeItem Tong_LOAIItem = new TreeItem(tree_LoaiTaisan, SWT.NONE);
		Tong_LOAIItem.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		Tong_LOAIItem.setText("Tất cả Loại tài sản");
		Tong_LOAIItem.setImage(icondata.TongLoaiitemIcon);
		return Tong_LOAIItem;
	}

	public void setItem_LoaiTaisan() throws SQLException {
		ArrayList<LOAITAISAN_CAP_I> ll = controler.getControl_LOAITAISAN_CAP_I().getAllData();
		for (LOAITAISAN_CAP_I l_lv1 : ll) {
			TreeItem ti = new TreeItem(tong_Item, SWT.None);
			ti.setText(l_lv1.getTEN_LOAITAISAN_CAP_I());
			ArrayList<LOAITAISAN_CAP_II> nl = controler.getControl_LOAITAISAN_CAP_II().getAllData();
			ti.setData(l_lv1);
			for (LOAITAISAN_CAP_II n : nl) {
				if (n.getMA_LOAITAISAN_CAP_I() == l_lv1.getMA_LOAITAISAN_CAP_I()) {
					TreeItem tii = new TreeItem(ti, SWT.None);
					tii.setText(n.getTEN_LOAITAISAN_CAP_II());
					tii.setData(n);
					ArrayList<LOAITAISAN_CAP_III> pl = controler.getControl_LOAITAISAN_CAP_III().getAllData();
					for (LOAITAISAN_CAP_III l_lv3 : pl) {
						if (l_lv3.getMA_LOAITAISAN_CAP_II() == n.getMA_LOAITAISAN_CAP_II()) {
							TreeItem tiii = new TreeItem(tii, SWT.None);
							tiii.setText(l_lv3.getTEN_LOAITAISAN_CAP_III());
							tiii.setData(l_lv3);
						}
					}
				}
			}
		}
		tong_Item.setExpanded(true);
	}
}
