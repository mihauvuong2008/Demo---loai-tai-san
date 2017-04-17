package View.AssetManagers.Taisan.Phuongtiengiaothong.Loaixe_DinhmucNhienlieu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TreeCursor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import DAO.NHOMTAISAN_CAP_V;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_VOHINH;
import View.AssetManagers.DanhMuc_NhomTaisan.QuanlyNhomtaisan;

public class MyTreeEditor {

	public void setEditorNhomtaisanCodinhHuuhinh(Tree tree_NHOMTaisan_Codinh, QuanlyNhomtaisan obj) {
		final TreeCursor cursor = new TreeCursor(tree_NHOMTaisan_Codinh, SWT.NONE);
		final ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		cursor.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				tree_NHOMTaisan_Codinh.setSelection(new TreeItem[] { cursor.getRow() });
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent event) {
				if (obj.isEditMode()) {
					EditAction();
				} else if (obj.isInsertMode()) {
					if (checkCursor_in_NewItem()) {
						EditAction();
					}
				}
			}

			private boolean checkCursor_in_NewItem() {
				if (tree_NHOMTaisan_Codinh.indexOf(cursor.getRow()) == (tree_NHOMTaisan_Codinh.getItemCount() - 1))
					return true;
				return false;
			}

			void EditAction() {
				final Text text = new Text(cursor, SWT.NONE);
				text.setFocus();
				// Copy the text from the cell to the Text control
				text.setText(cursor.getRow().getText(cursor.getColumn()));
				text.setFocus();
				text.selectAll();
				// Add a handler to detect key presses
				text.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent event) {
						switch (event.keyCode) {
						case SWT.CR:
							cursor.getRow().setText(cursor.getColumn(), text.getText());
							TreeItem[] items = tree_NHOMTaisan_Codinh.getSelection();
							if (items.length > 0) {
								NHOMTAISAN_CAP_V r = (NHOMTAISAN_CAP_V) items[0].getData();
								if (r != null) {
									r.setTEN_NHOMTAISAN_CAP_V(items[0].getText(0));
									try {
										r.setTHOIGIAN_SUDUNG(Integer.valueOf(items[0].getText(1)));
										r.setTILE_HAOMON(Double.valueOf(items[0].getText(2)));
									} catch (Exception e) {
										MessageBox m = new MessageBox(obj.getShell());
										m.setMessage("Lỗi định dạng số!");
										m.open();
										items[0].setText(0, "0");
									}
									items[0].setData(r);
								}
							}
						case SWT.ESC:
							text.dispose();
							break;
						}
					}
				});
				editor.setEditor(text);
			}
		});
	}

	public void setEditorNhomtaisanCodinhVohinh(Tree tree_NhomTaisan_Codinh_Vohinh, QuanlyNhomtaisan quanlyNhomtaisan) {
		final TreeCursor cursor = new TreeCursor(tree_NhomTaisan_Codinh_Vohinh, SWT.NONE);
		final ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		cursor.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				tree_NhomTaisan_Codinh_Vohinh.setSelection(new TreeItem[] { cursor.getRow() });
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent event) {
				if (quanlyNhomtaisan.isEditMode()) {
					EditAction();
				} else if (quanlyNhomtaisan.isInsertMode()) {
					if (checkCursor_in_NewItem()) {
						EditAction();
					}
				}
			}

			private boolean checkCursor_in_NewItem() {
				if (tree_NhomTaisan_Codinh_Vohinh
						.indexOf(cursor.getRow()) == (tree_NhomTaisan_Codinh_Vohinh.getItemCount() - 1))
					return true;
				return false;
			}

			void EditAction() {
				final Text text = new Text(cursor, SWT.NONE);
				text.setFocus();
				// Copy the text from the cell to the Text control
				text.setText(cursor.getRow().getText(cursor.getColumn()));
				text.setFocus();
				text.selectAll();
				// Add a handler to detect key presses
				text.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent event) {
						switch (event.keyCode) {
						case SWT.CR:
							cursor.getRow().setText(cursor.getColumn(), text.getText());
							TreeItem[] items = tree_NhomTaisan_Codinh_Vohinh.getSelection();
							if (items.length > 0) {
								NHOM_TAISANCODINH_VOHINH r = (NHOM_TAISANCODINH_VOHINH) items[0].getData();
								if (r != null) {
									r.setTEN_NHOM_TAISANCODINH_VOHINH(items[0].getText(0));
									try {
										r.setTHOIGIAN_SUDUNG(Integer.valueOf(items[0].getText(1)));
										r.setTILE_HAOMON(Double.valueOf(items[0].getText(2)));
									} catch (Exception e) {
										MessageBox m = new MessageBox(quanlyNhomtaisan.getShell());
										m.setMessage("Lỗi định dạng số!");
										m.open();
										items[0].setText(0, "0");
									}
									items[0].setData(r);
								}
							}
						case SWT.ESC:
							text.dispose();
							break;
						}
					}
				});
				editor.setEditor(text);
			}
		});
	}

	public void setEditorNhomtaisanCodinhDacthu(Tree tree_Nhomtaisan_Codinh_Dacthu, QuanlyNhomtaisan quanlyNhomtaisan) {
		final TreeCursor cursor = new TreeCursor(tree_Nhomtaisan_Codinh_Dacthu, SWT.NONE);
		final ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		cursor.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				tree_Nhomtaisan_Codinh_Dacthu.setSelection(new TreeItem[] { cursor.getRow() });
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent event) {
				if (quanlyNhomtaisan.isEditMode()) {
					EditAction();
				} else if (quanlyNhomtaisan.isInsertMode()) {
					if (checkCursor_in_NewItem()) {
						EditAction();
					}
				}
			}

			private boolean checkCursor_in_NewItem() {
				if (tree_Nhomtaisan_Codinh_Dacthu
						.indexOf(cursor.getRow()) == (tree_Nhomtaisan_Codinh_Dacthu.getItemCount() - 1))
					return true;
				return false;
			}

			void EditAction() {
				final Text text = new Text(cursor, SWT.NONE);
				text.setFocus();
				// Copy the text from the cell to the Text control
				text.setText(cursor.getRow().getText(cursor.getColumn()));
				text.setFocus();
				text.selectAll();
				// Add a handler to detect key presses
				text.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent event) {
						switch (event.keyCode) {
						case SWT.CR:
							cursor.getRow().setText(cursor.getColumn(), text.getText());
							TreeItem[] items = tree_Nhomtaisan_Codinh_Dacthu.getSelection();
							if (items.length > 0) {
								NHOMTAISAN_CAP_V r = (NHOMTAISAN_CAP_V) items[0].getData();
								if (r != null) {
									r.setTEN_NHOMTAISAN_CAP_V(items[0].getText(0));
									try {
										r.setTHOIGIAN_SUDUNG(Integer.valueOf(items[0].getText(1)));
										r.setTILE_HAOMON(Double.valueOf(items[0].getText(2)));
									} catch (Exception e) {
										MessageBox m = new MessageBox(quanlyNhomtaisan.getShell());
										m.setMessage("Lỗi định dạng số!");
										m.open();
										items[0].setText(0, "0");
									}
									items[0].setData(r);
								}
							}
						case SWT.ESC:
							text.dispose();
							break;
						}
					}
				});
				editor.setEditor(text);
			}
		});
	}

	public void setEditorNhomtaisanCodinhDacbiet(Tree tree_NhomTaisan_Codinh_dacbiet,
			QuanlyNhomtaisan quanlyNhomtaisan) {
		final TreeCursor cursor = new TreeCursor(tree_NhomTaisan_Codinh_dacbiet, SWT.NONE);
		final ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		cursor.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				tree_NhomTaisan_Codinh_dacbiet.setSelection(new TreeItem[] { cursor.getRow() });
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent event) {
				if (quanlyNhomtaisan.isEditMode()) {
					EditAction();
				} else if (quanlyNhomtaisan.isInsertMode()) {
					if (checkCursor_in_NewItem()) {
						EditAction();
					}
				}
			}

			private boolean checkCursor_in_NewItem() {
				if (tree_NhomTaisan_Codinh_dacbiet
						.indexOf(cursor.getRow()) == (tree_NhomTaisan_Codinh_dacbiet.getItemCount() - 1))
					return true;
				return false;
			}

			void EditAction() {
				final Text text = new Text(cursor, SWT.NONE);
				text.setFocus();
				// Copy the text from the cell to the Text control
				text.setText(cursor.getRow().getText(cursor.getColumn()));
				text.setFocus();
				text.selectAll();
				// Add a handler to detect key presses
				text.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent event) {
						switch (event.keyCode) {
						case SWT.CR:
							cursor.getRow().setText(cursor.getColumn(), text.getText());
							TreeItem[] items = tree_NhomTaisan_Codinh_dacbiet.getSelection();
							if (items.length > 0) {
								NHOM_TAISANCODINH_DACBIET r = (NHOM_TAISANCODINH_DACBIET) items[0].getData();
								if (r != null) {
									r.setTEN_NHOM_TAISANCODINH_DACBIET(items[0].getText(0));
									try {
										r.setGIA_QUYUOC(Double.valueOf(items[0].getText(1)));
									} catch (Exception e) {
										MessageBox m = new MessageBox(quanlyNhomtaisan.getShell());
										m.setMessage("Lỗi định dạng số!");
										m.open();
										items[0].setText(0, "0");
									}
									items[0].setData(r);
								}
							}
						case SWT.ESC:
							text.dispose();
							break;
						}
					}
				});
				editor.setEditor(text);
			}
		});
	}
}
