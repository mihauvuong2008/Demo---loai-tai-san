package View.AssetManagers;

import java.sql.SQLException;
import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import Controler.Controler;
import DAO.NHOMTAISAN_CAP_I;
import DAO.NHOMTAISAN_CAP_II;
import DAO.NHOMTAISAN_CAP_III;
import DAO.NHOMTAISAN_CAP_IV;
import DAO.NHOMTAISAN_CAP_V;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class Nhomtaisan_162 {
	private Controler controler;
	private Tree tree_NHOMTaisan_Codinh;
	private Tree tree_NhomTaisan_Codinh_Vohinh;
	private Tree tree_NhomTaisan_Codinh_dacbiet;
	private Tree tree_Nhomtaisan_Codinh_Dacthu;
	private TreeItem tongItem_NhomtaisanCodinh;
	private TreeItem tongItem_NhomtaisanCodinh_Vohinh;
	private TreeItem tongItem_NhomtaisanCodinh_Dacthu;
	private TreeItem tongItem_NhomtaisanCodinh_Dacbiet;
	private Icondata icondata;

	public Nhomtaisan_162(Controler controler, Icondata icondata) {
		this.controler = controler;
		this.icondata = icondata;
	}

	public void initTongitem() {
		if (tree_NHOMTaisan_Codinh != null)
			tongItem_NhomtaisanCodinh = buildNhomtaisanCodinh(tree_NHOMTaisan_Codinh, icondata);
		if (tree_NhomTaisan_Codinh_Vohinh != null)
			tongItem_NhomtaisanCodinh_Vohinh = buildNhomtaisanCodinh(tree_NhomTaisan_Codinh_Vohinh, icondata);
		if (tree_Nhomtaisan_Codinh_Dacthu != null)
			tongItem_NhomtaisanCodinh_Dacthu = buildNhomtaisanCodinh(tree_Nhomtaisan_Codinh_Dacthu, icondata);
		if (tree_NhomTaisan_Codinh_dacbiet != null)
			tongItem_NhomtaisanCodinh_Dacbiet = buildNhomtaisanCodinh(tree_NhomTaisan_Codinh_dacbiet, icondata);
	}

	private TreeItem buildNhomtaisanCodinh(Tree tree_NHOMTaisan, Icondata icondata) {
		TreeItem TongItem_NHOMTaisan = new TreeItem(tree_NHOMTaisan, SWT.NONE);
		TongItem_NHOMTaisan.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		TongItem_NHOMTaisan.setImage(icondata.TongNhomtaisan);
		TongItem_NHOMTaisan.setText("Tất cả Nhóm tài sản");
		return TongItem_NHOMTaisan;
	}

	public void initItem_NhomTaisan_Codinh(boolean expand) throws SQLException {
		if (tongItem_NhomtaisanCodinh != null) {
			tongItem_NhomtaisanCodinh.removeAll();
			ArrayList<NHOMTAISAN_CAP_I> lv1_l = controler.getControl_NHOMTAISAN_CAP_I().getAllData();
			if (lv1_l != null)
				for (NHOMTAISAN_CAP_I l_lv1 : lv1_l) {
					TreeItem ti1 = new TreeItem(tongItem_NhomtaisanCodinh, SWT.None);
					ti1.setText(l_lv1.getTEN_NHOMTAISAN_CAP_I());
					ti1.setData(l_lv1);
					ArrayList<NHOMTAISAN_CAP_II> lv2_l = controler.getControl_NHOMTAISAN_CAP_II().getAllData();
					if (lv2_l != null)
						for (NHOMTAISAN_CAP_II l_lv2 : lv2_l) {
							if (l_lv1.getMA_NHOMTAISAN_CAP_I() == l_lv2.getMA_NHOMTAISAN_CAP_I()) {
								TreeItem ti2 = new TreeItem(ti1, SWT.None);
								ti2.setText(l_lv2.getTEN_NHOMTAISAN_CAP_II());
								ti2.setData(l_lv2);
								ArrayList<NHOMTAISAN_CAP_III> lv3_l = controler.getControl_NHOMTAISAN_CAP_III()
										.getAllData();
								if (lv3_l != null)
									for (NHOMTAISAN_CAP_III l_lv3 : lv3_l) {
										if (l_lv2.getMA_NHOMTAISAN_CAP_II() == l_lv3.getMA_NHOMTAISAN_CAP_II()) {
											TreeItem ti3 = new TreeItem(ti2, SWT.None);
											ti3.setText(l_lv3.getTEN_NHOMTAISAN_CAP_III());
											ti3.setData(l_lv3);
											ArrayList<NHOMTAISAN_CAP_IV> lv4_l = controler
													.getControl_NHOMTAISAN_CAP_IV().getAllData();
											if (lv4_l != null)
												for (NHOMTAISAN_CAP_IV l_lv4 : lv4_l) {
													if (l_lv3.getMA_NHOMTAISAN_CAP_III() == l_lv4
															.getMA_NHOMTAISAN_CAP_III()) {
														TreeItem ti4 = new TreeItem(ti3, SWT.None);
														ti4.setText(l_lv4.getTEN_NHOMTAISAN_CAP_IV());
														ti4.setData(l_lv4);
														ArrayList<NHOMTAISAN_CAP_V> lv5_l = controler
																.getControl_NHOMTAISAN_CAP_V().getAllData();
														if (lv5_l != null)
															for (NHOMTAISAN_CAP_V l_lv5 : lv5_l) {
																if (l_lv4.getMA_NHOMTAISAN_CAP_IV() == l_lv5
																		.getMA_NHOMTAISAN_CAP_IV()) {
																	TreeItem ti5 = new TreeItem(ti4, SWT.None);
																	ti5.setText(new String[] {
																			l_lv5.getTEN_NHOMTAISAN_CAP_V(),
																			String.valueOf(l_lv5.getTHOIGIAN_SUDUNG()),
																			String.valueOf(l_lv5.getTILE_HAOMON()) });
																	ti5.setData(l_lv5);
																}
															}
														ti4.setExpanded(expand);
													}
												}
											ti3.setExpanded(expand);
										}
									}
								ti2.setExpanded(expand);
							}
						}
					ti1.setExpanded(expand);
				}
			tongItem_NhomtaisanCodinh.setExpanded(true);
		}
	}

	public void setItem_NhomTaisan_Codinh_Vohinh() throws SQLException {
		if (tongItem_NhomtaisanCodinh_Vohinh != null) {
			tongItem_NhomtaisanCodinh_Vohinh.removeAll();
			ArrayList<NHOM_TAISANCODINH_VOHINH> lv1_l = controler.getControl_NHOM_TAISANCODINH_VOHINH().getAllData();
			if (lv1_l != null)
				for (NHOM_TAISANCODINH_VOHINH l_lv1 : lv1_l) {
					TreeItem ti1 = new TreeItem(tongItem_NhomtaisanCodinh_Vohinh, SWT.None);
					ti1.setText(new String[] { l_lv1.getTEN_NHOM_TAISANCODINH_VOHINH(),
							String.valueOf(l_lv1.getTHOIGIAN_SUDUNG()), String.valueOf(l_lv1.getTILE_HAOMON()) });
					ti1.setData(l_lv1);
				}
			tongItem_NhomtaisanCodinh_Vohinh.setExpanded(true);
		}
	}

	public void setItem_NhomTaisan_Codinh_Dacthu() throws SQLException {
		if (tongItem_NhomtaisanCodinh_Dacthu != null) {
			tongItem_NhomtaisanCodinh_Dacthu.removeAll();
			ArrayList<NHOM_TAISANCODINH_DACTHU> lv1_l = controler.getControl_NHOM_TAISANCODINH_DACTHU().getAllData();
			if (lv1_l != null)
				for (NHOM_TAISANCODINH_DACTHU l_lv1 : lv1_l) {
					TreeItem ti1 = new TreeItem(tongItem_NhomtaisanCodinh_Dacthu, SWT.None);
					ti1.setText(new String[] { l_lv1.getTEN_NHOM_TAISANCODINH_DACTHU(),
							String.valueOf(l_lv1.getTILE_HAOMON()), String.valueOf(l_lv1.getTHOIGIAN_SUDUNG()) });
					ti1.setData(l_lv1);
				}
			tongItem_NhomtaisanCodinh_Dacthu.setExpanded(true);
		}
	}

	public void setItem_NhomTaisan_Codinh_Dacbiet() throws SQLException {
		if (tongItem_NhomtaisanCodinh_Dacbiet != null) {
			tongItem_NhomtaisanCodinh_Dacbiet.removeAll();
			ArrayList<NHOM_TAISANCODINH_DACBIET> lv1_l = controler.getControl_NHOM_TAISANCODINH_DACBIET().getAllData();
			if (lv1_l != null)
				for (NHOM_TAISANCODINH_DACBIET l_lv1 : lv1_l) {
					TreeItem ti1 = new TreeItem(tongItem_NhomtaisanCodinh_Dacbiet, SWT.None);
					ti1.setText(new String[] { l_lv1.getTEN_NHOM_TAISANCODINH_DACBIET(),
							String.valueOf(l_lv1.getGIA_QUYUOC()) });
					ti1.setData(l_lv1);
				}
			tongItem_NhomtaisanCodinh_Dacbiet.setExpanded(true);
		}
	}

	public final void setTree_NHOMTaisan_Codinh(Tree tree_NHOMTaisan_Codinh) {
		this.tree_NHOMTaisan_Codinh = tree_NHOMTaisan_Codinh;
	}

	public final void setTree_NhomTaisan_Codinh_Vohinh(Tree tree_NhomTaisan_Codinh_Vohinh) {
		this.tree_NhomTaisan_Codinh_Vohinh = tree_NhomTaisan_Codinh_Vohinh;
	}

	public final void setTree_NhomTaisan_Codinh_dacbiet(Tree tree_NhomTaisan_Codinh_dacbiet) {
		this.tree_NhomTaisan_Codinh_dacbiet = tree_NhomTaisan_Codinh_dacbiet;
	}

	public final void setTree_Nhomtaisan_Codinh_Dacthu(Tree tree_Nhomtaisan_Codinh_Dacthu) {
		this.tree_Nhomtaisan_Codinh_Dacthu = tree_Nhomtaisan_Codinh_Dacthu;
	}

	public final Tree getTree_NHOMTaisan_Codinh() {
		return tree_NHOMTaisan_Codinh;
	}

	public final Tree getTree_NhomTaisan_Codinh_Vohinh() {
		return tree_NhomTaisan_Codinh_Vohinh;
	}

	public final Tree getTree_NhomTaisan_Codinh_dacbiet() {
		return tree_NhomTaisan_Codinh_dacbiet;
	}

	public final Tree getTree_Nhomtaisan_Codinh_Dacthu() {
		return tree_Nhomtaisan_Codinh_Dacthu;
	}
}
