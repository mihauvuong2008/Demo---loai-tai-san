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

	public Nhomtaisan_162(Controler controler, Tree tree_NHOMTaisan_Codinh, Tree tree_NhomTaisan_Codinh_Vohinh,
			Tree tree_NhomTaisan_Codinh_dacbiet, Tree tree_Nhomtaisan_Codinh_Dacthu, Icondata icondata) {
		this.tree_NHOMTaisan_Codinh = tree_NHOMTaisan_Codinh;
		this.tree_NhomTaisan_Codinh_Vohinh = tree_NhomTaisan_Codinh_Vohinh;
		this.tree_NhomTaisan_Codinh_dacbiet = tree_NhomTaisan_Codinh_dacbiet;
		this.tree_Nhomtaisan_Codinh_Dacthu = tree_Nhomtaisan_Codinh_Dacthu;
		this.controler = controler;
		tongItem_NhomtaisanCodinh = buildNhomtaisanCodinh(tree_NHOMTaisan_Codinh, icondata);
		tongItem_NhomtaisanCodinh_Vohinh = buildNhomtaisanCodinh(tree_NhomTaisan_Codinh_Vohinh, icondata);
		tongItem_NhomtaisanCodinh_Dacthu = buildNhomtaisanCodinh(tree_NhomTaisan_Codinh_dacbiet, icondata);
		tongItem_NhomtaisanCodinh_Dacbiet = buildNhomtaisanCodinh(tree_Nhomtaisan_Codinh_Dacthu, icondata);
	}

	private TreeItem buildNhomtaisanCodinh(Tree tree_NHOMTaisan, Icondata icondata) {
		TreeItem TongItem_NHOMTaisan = new TreeItem(tree_NHOMTaisan, SWT.NONE);
		TongItem_NHOMTaisan.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		TongItem_NHOMTaisan.setImage(icondata.TongNhomtaisan);
		TongItem_NHOMTaisan.setText("Tất cả Nhóm tài sản");
		return TongItem_NHOMTaisan;
	}

	public void setItem_NhomTaisan_Codinh() throws SQLException {
		tongItem_NhomtaisanCodinh.removeAll();
		ArrayList<NHOMTAISAN_CAP_I> lv1_l = controler.getControl_NHOMTAISAN_CAP_I().getAllData();
		if (lv1_l != null)
			for (NHOMTAISAN_CAP_I l_lv1 : lv1_l) {
				TreeItem ti1 = new TreeItem(tongItem_NhomtaisanCodinh, SWT.None);
				ti1.setText(l_lv1.getTEN_NHOMTAISAN_CAP_I());
				ti1.setData("lv1", l_lv1);
				ArrayList<NHOMTAISAN_CAP_II> lv2_l = controler.getControl_NHOMTAISAN_CAP_II().getAllData();
				if (lv2_l != null)
					for (NHOMTAISAN_CAP_II l_lv2 : lv2_l) {
						if (l_lv1.getMA_NHOMTAISAN_CAP_I() == l_lv2.getMA_NHOMTAISAN_CAP_I()) {
							TreeItem ti2 = new TreeItem(ti1, SWT.None);
							ti2.setText(l_lv2.getTEN_NHOMTAISAN_CAP_II());
							ti2.setData("lv2", l_lv2);
							ArrayList<NHOMTAISAN_CAP_III> lv3_l = controler.getControl_NHOMTAISAN_CAP_III()
									.getAllData();
							if (lv3_l != null)
								for (NHOMTAISAN_CAP_III l_lv3 : lv3_l) {
									if (l_lv2.getMA_NHOMTAISAN_CAP_II() == l_lv3.getMA_NHOMTAISAN_CAP_II()) {
										TreeItem ti3 = new TreeItem(ti2, SWT.None);
										ti3.setText(l_lv3.getTEN_NHOMTAISAN_CAP_III());
										ti3.setData("lv3", l_lv3);
										ArrayList<NHOMTAISAN_CAP_IV> lv4_l = controler.getControl_NHOMTAISAN_CAP_IV()
												.getAllData();
										if (lv4_l != null)
											for (NHOMTAISAN_CAP_IV l_lv4 : lv4_l) {
												if (l_lv3.getMA_NHOMTAISAN_CAP_III() == l_lv4
														.getMA_NHOMTAISAN_CAP_III()) {
													TreeItem ti4 = new TreeItem(ti3, SWT.None);
													ti4.setText(l_lv4.getTEN_NHOMTAISAN_CAP_IV());
													ti4.setData("lv4", l_lv4);
													ArrayList<NHOMTAISAN_CAP_V> lv5_l = controler
															.getControl_NHOMTAISAN_CAP_V().getAllData();
													if (lv5_l != null)
														for (NHOMTAISAN_CAP_V l_lv5 : lv5_l) {
															if (l_lv4.getMA_NHOMTAISAN_CAP_IV() == l_lv5
																	.getMA_NHOMTAISAN_CAP_IV()) {
																TreeItem ti5 = new TreeItem(ti4, SWT.None);
																ti5.setText(l_lv5.getTEN_NHOMTAISAN_CAP_V());
																ti5.setData("lv5", l_lv5);
															}
														}
												}
											}
									}
								}
						}
					}
			}
		tongItem_NhomtaisanCodinh.setExpanded(true);
	}

	public void setItem_NhomTaisan_Codinh_Vohinh() throws SQLException {
		tongItem_NhomtaisanCodinh_Vohinh.removeAll();
		ArrayList<NHOMTAISAN_CAP_I> lv1_l = controler.getControl_NHOMTAISAN_CAP_I().getAllData();
		if (lv1_l != null)
			for (NHOMTAISAN_CAP_I l_lv1 : lv1_l) {
				TreeItem ti1 = new TreeItem(tongItem_NhomtaisanCodinh_Vohinh, SWT.None);
				ti1.setText(l_lv1.getTEN_NHOMTAISAN_CAP_I());
				ti1.setData("lv1", l_lv1);
				ArrayList<NHOMTAISAN_CAP_II> lv2_l = controler.getControl_NHOMTAISAN_CAP_II().getAllData();
				if (lv2_l != null)
					for (NHOMTAISAN_CAP_II l_lv2 : lv2_l) {
						if (l_lv1.getMA_NHOMTAISAN_CAP_I() == l_lv2.getMA_NHOMTAISAN_CAP_I()) {
							TreeItem ti2 = new TreeItem(ti1, SWT.None);
							ti2.setText(l_lv2.getTEN_NHOMTAISAN_CAP_II());
							ti2.setData("lv2", l_lv2);
							ArrayList<NHOMTAISAN_CAP_III> lv3_l = controler.getControl_NHOMTAISAN_CAP_III()
									.getAllData();
							if (lv3_l != null)
								for (NHOMTAISAN_CAP_III l_lv3 : lv3_l) {
									if (l_lv2.getMA_NHOMTAISAN_CAP_II() == l_lv3.getMA_NHOMTAISAN_CAP_II()) {
										TreeItem ti3 = new TreeItem(ti2, SWT.None);
										ti3.setText(l_lv3.getTEN_NHOMTAISAN_CAP_III());
										ti3.setData("lv3", l_lv3);
										ArrayList<NHOMTAISAN_CAP_IV> lv4_l = controler.getControl_NHOMTAISAN_CAP_IV()
												.getAllData();
										if (lv4_l != null)
											for (NHOMTAISAN_CAP_IV l_lv4 : lv4_l) {
												if (l_lv3.getMA_NHOMTAISAN_CAP_III() == l_lv4
														.getMA_NHOMTAISAN_CAP_III()) {
													TreeItem ti4 = new TreeItem(ti3, SWT.None);
													ti4.setText(l_lv4.getTEN_NHOMTAISAN_CAP_IV());
													ti4.setData("lv4", l_lv4);
													ArrayList<NHOMTAISAN_CAP_V> lv5_l = controler
															.getControl_NHOMTAISAN_CAP_V().getAllData();
													if (lv5_l != null)
														for (NHOMTAISAN_CAP_V l_lv5 : lv5_l) {
															if (l_lv4.getMA_NHOMTAISAN_CAP_IV() == l_lv5
																	.getMA_NHOMTAISAN_CAP_IV()) {
																TreeItem ti5 = new TreeItem(ti4, SWT.None);
																ti5.setText(l_lv5.getTEN_NHOMTAISAN_CAP_V());
																ti5.setData("lv5", l_lv5);
															}
														}
												}
											}
									}
								}
						}
					}
			}
		tongItem_NhomtaisanCodinh_Vohinh.setExpanded(true);
	}

	public void setItem_NhomTaisan_Codinh_Dacthu() throws SQLException {
		tongItem_NhomtaisanCodinh_Dacthu.removeAll();
		ArrayList<NHOMTAISAN_CAP_I> lv1_l = controler.getControl_NHOMTAISAN_CAP_I().getAllData();
		if (lv1_l != null)
			for (NHOMTAISAN_CAP_I l_lv1 : lv1_l) {
				TreeItem ti1 = new TreeItem(tongItem_NhomtaisanCodinh_Dacthu, SWT.None);
				ti1.setText(l_lv1.getTEN_NHOMTAISAN_CAP_I());
				ti1.setData("lv1", l_lv1);
				ArrayList<NHOMTAISAN_CAP_II> lv2_l = controler.getControl_NHOMTAISAN_CAP_II().getAllData();
				if (lv2_l != null)
					for (NHOMTAISAN_CAP_II l_lv2 : lv2_l) {
						if (l_lv1.getMA_NHOMTAISAN_CAP_I() == l_lv2.getMA_NHOMTAISAN_CAP_I()) {
							TreeItem ti2 = new TreeItem(ti1, SWT.None);
							ti2.setText(l_lv2.getTEN_NHOMTAISAN_CAP_II());
							ti2.setData("lv2", l_lv2);
							ArrayList<NHOMTAISAN_CAP_III> lv3_l = controler.getControl_NHOMTAISAN_CAP_III()
									.getAllData();
							if (lv3_l != null)
								for (NHOMTAISAN_CAP_III l_lv3 : lv3_l) {
									if (l_lv2.getMA_NHOMTAISAN_CAP_II() == l_lv3.getMA_NHOMTAISAN_CAP_II()) {
										TreeItem ti3 = new TreeItem(ti2, SWT.None);
										ti3.setText(l_lv3.getTEN_NHOMTAISAN_CAP_III());
										ti3.setData("lv3", l_lv3);
										ArrayList<NHOMTAISAN_CAP_IV> lv4_l = controler.getControl_NHOMTAISAN_CAP_IV()
												.getAllData();
										if (lv4_l != null)
											for (NHOMTAISAN_CAP_IV l_lv4 : lv4_l) {
												if (l_lv3.getMA_NHOMTAISAN_CAP_III() == l_lv4
														.getMA_NHOMTAISAN_CAP_III()) {
													TreeItem ti4 = new TreeItem(ti3, SWT.None);
													ti4.setText(l_lv4.getTEN_NHOMTAISAN_CAP_IV());
													ti4.setData("lv4", l_lv4);
													ArrayList<NHOMTAISAN_CAP_V> lv5_l = controler
															.getControl_NHOMTAISAN_CAP_V().getAllData();
													if (lv5_l != null)
														for (NHOMTAISAN_CAP_V l_lv5 : lv5_l) {
															if (l_lv4.getMA_NHOMTAISAN_CAP_IV() == l_lv5
																	.getMA_NHOMTAISAN_CAP_IV()) {
																TreeItem ti5 = new TreeItem(ti4, SWT.None);
																ti5.setText(l_lv5.getTEN_NHOMTAISAN_CAP_V());
																ti5.setData("lv5", l_lv5);
															}
														}
												}
											}
									}
								}
						}
					}
			}
		tongItem_NhomtaisanCodinh_Dacthu.setExpanded(true);
	}

	public void setItem_NhomTaisan_Codinh_Dacbiet() throws SQLException {
		tongItem_NhomtaisanCodinh_Dacbiet.removeAll();
		ArrayList<NHOMTAISAN_CAP_I> lv1_l = controler.getControl_NHOMTAISAN_CAP_I().getAllData();
		if (lv1_l != null)
			for (NHOMTAISAN_CAP_I l_lv1 : lv1_l) {
				TreeItem ti1 = new TreeItem(tongItem_NhomtaisanCodinh_Dacbiet, SWT.None);
				ti1.setText(l_lv1.getTEN_NHOMTAISAN_CAP_I());
				ti1.setData("lv1", l_lv1);
				ArrayList<NHOMTAISAN_CAP_II> lv2_l = controler.getControl_NHOMTAISAN_CAP_II().getAllData();
				if (lv2_l != null)
					for (NHOMTAISAN_CAP_II l_lv2 : lv2_l) {
						if (l_lv1.getMA_NHOMTAISAN_CAP_I() == l_lv2.getMA_NHOMTAISAN_CAP_I()) {
							TreeItem ti2 = new TreeItem(ti1, SWT.None);
							ti2.setText(l_lv2.getTEN_NHOMTAISAN_CAP_II());
							ti2.setData("lv2", l_lv2);
							ArrayList<NHOMTAISAN_CAP_III> lv3_l = controler.getControl_NHOMTAISAN_CAP_III()
									.getAllData();
							if (lv3_l != null)
								for (NHOMTAISAN_CAP_III l_lv3 : lv3_l) {
									if (l_lv2.getMA_NHOMTAISAN_CAP_II() == l_lv3.getMA_NHOMTAISAN_CAP_II()) {
										TreeItem ti3 = new TreeItem(ti2, SWT.None);
										ti3.setText(l_lv3.getTEN_NHOMTAISAN_CAP_III());
										ti3.setData("lv3", l_lv3);
										ArrayList<NHOMTAISAN_CAP_IV> lv4_l = controler.getControl_NHOMTAISAN_CAP_IV()
												.getAllData();
										if (lv4_l != null)
											for (NHOMTAISAN_CAP_IV l_lv4 : lv4_l) {
												if (l_lv3.getMA_NHOMTAISAN_CAP_III() == l_lv4
														.getMA_NHOMTAISAN_CAP_III()) {
													TreeItem ti4 = new TreeItem(ti3, SWT.None);
													ti4.setText(l_lv4.getTEN_NHOMTAISAN_CAP_IV());
													ti4.setData("lv4", l_lv4);
													ArrayList<NHOMTAISAN_CAP_V> lv5_l = controler
															.getControl_NHOMTAISAN_CAP_V().getAllData();
													if (lv5_l != null)
														for (NHOMTAISAN_CAP_V l_lv5 : lv5_l) {
															if (l_lv4.getMA_NHOMTAISAN_CAP_IV() == l_lv5
																	.getMA_NHOMTAISAN_CAP_IV()) {
																TreeItem ti5 = new TreeItem(ti4, SWT.None);
																ti5.setText(l_lv5.getTEN_NHOMTAISAN_CAP_V());
																ti5.setData("lv5", l_lv5);
															}
														}
												}
											}
									}
								}
						}
					}
			}
		tongItem_NhomtaisanCodinh_Dacbiet.setExpanded(true);
	}

}
