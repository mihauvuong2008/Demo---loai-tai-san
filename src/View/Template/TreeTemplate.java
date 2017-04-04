package View.Template;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Control.PHONGBAN.Control_PHONGBAN;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import View.AssetManagers.MainForm;

public class TreeTemplate {
	private NGUOIDUNG user;

	public TreeTemplate(NGUOIDUNG user) {
		this.user = user;
	}

	public void getTreePHONGBAN(org.eclipse.swt.widgets.Tree tree_Phongban) throws SQLException {
		tree_Phongban.removeAll();
		TreeItem Cuc_Item = new TreeItem(tree_Phongban, SWT.NONE);
		Cuc_Item.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		Cuc_Item.setImage(SWTResourceManager.getImage(MainForm.class, "/city-icon.png"));
		Cuc_Item.setText("Cục ABC");
		setItem_PhongBan(Cuc_Item);
		Cuc_Item.setExpanded(true);
		tree_Phongban.pack();

	}

	private void setItem_PhongBan(TreeItem cuc_Item) throws SQLException {
		Control_PHONGBAN cddn = new Control_PHONGBAN(user);
		ArrayList<PHONGBAN> dv = cddn.getAllDonvi();
		if (dv != null)
			for (PHONGBAN d : dv) {
				TreeItem Item = new TreeItem(cuc_Item, SWT.NONE);
				Item.setImage(SWTResourceManager.getImage(MainForm.class, "/group-icon.png"));
				Item.setText(d.getTEN_PHONGBAN());
				Item.setData(d);
			}
	}
}
