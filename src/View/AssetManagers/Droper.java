package View.AssetManagers;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import DAO.LOAITAISAN_CAP_III;
import DAO.NHOMTAISAN_CAP_V;
import DAO.PHONGBAN;

public class Droper {
	private Transfer[] types_Data;
	private DropTarget target_Type;
	private Menu_Handler mh;

	public Droper(Tree tree_NhomTaisan, Menu_Handler mh) {
		this.mh = mh;
		// Drag
		types_Data = new Transfer[] { TextTransfer.getInstance() };
		target_Type = new DropTarget(tree_NhomTaisan, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
		target_Type.setTransfer(types_Data);
	}

	public final Transfer[] getTypes_Data() {
		return types_Data;
	}

	public final void setTypes_Data(Transfer[] types_Data) {
		this.types_Data = types_Data;
	}

	public final DropTarget getTarget_Data() {
		return target_Type;
	}

	public final void setTarget_Data(DropTarget target_Data) {
		this.target_Type = target_Data;
	}

	public void setListennerNhomTaisan() {

		target_Type.addDropListener(new DropTargetAdapter() {
			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// will accept text but prefer to have files dropped
				for (int i = 0; i < event.dataTypes.length; i++) {
					if (TextTransfer.getInstance().isSupportedType(event.dataTypes[i])) {
						event.currentDataType = event.dataTypes[i];
						// files should only be copied
						if (event.detail != DND.DROP_COPY) {
							event.detail = DND.DROP_NONE;
						}
						break;
					}
				}
			}

			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
				}
			}

			public void dragOperationChanged(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// allow text to be moved but files should only be copied
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					if (event.detail != DND.DROP_COPY) {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			public void dragLeave(DropTargetEvent event) {
			}

			public void dropAccept(DropTargetEvent event) {
			}

			public void drop(DropTargetEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					Object o = ((TreeItem) event.item).getData("lv5");
					if (o instanceof NHOMTAISAN_CAP_V) {
						String text = event.data.toString();
						NHOMTAISAN_CAP_V dv = (NHOMTAISAN_CAP_V) o;
						int MA_NHOMTAISAN = dv.getMA_NHOMTAISAN_CAP_V();
						mh.OpenForm_ChuyenNhomTaisan(Display.getDefault(), text.split(" "), MA_NHOMTAISAN);
					}
				}
			}
		});
	}

	public void setListennerLoaiTaisan() {

		target_Type.addDropListener(new DropTargetAdapter() {
			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				for (int i = 0; i < event.dataTypes.length; i++) {
					if (TextTransfer.getInstance().isSupportedType(event.dataTypes[i])) {
						event.currentDataType = event.dataTypes[i];
						if (event.detail != DND.DROP_COPY) {
							event.detail = DND.DROP_NONE;
						}
						break;
					}
				}
			}

			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
				}
			}

			public void dragOperationChanged(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// allow text to be moved but files should only be copied
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					if (event.detail != DND.DROP_COPY) {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			public void dragLeave(DropTargetEvent event) {
			}

			public void dropAccept(DropTargetEvent event) {
			}

			public void drop(DropTargetEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					Object o = ((TreeItem) event.item).getData("l_lv3");
					if (o instanceof LOAITAISAN_CAP_III) {
						String text = event.data.toString();
						LOAITAISAN_CAP_III dv = (LOAITAISAN_CAP_III) o;
						int MA_LOAITAISAN_CAP_III = dv.getMA_LOAITAISAN_CAP_III();
						mh.OpenForm_ChuyenLoaiTaisan(Display.getDefault(), text.split(" "), MA_LOAITAISAN_CAP_III);
					}
				}
			}
		});
	}

	public void SetListennerPhongban() {
		target_Type.addDropListener(new DropTargetAdapter() {
			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// will accept text but prefer to have files dropped
				for (int i = 0; i < event.dataTypes.length; i++) {
					if (TextTransfer.getInstance().isSupportedType(event.dataTypes[i])) {
						event.currentDataType = event.dataTypes[i];
						// files should only be copied
						if (event.detail != DND.DROP_COPY) {
							event.detail = DND.DROP_NONE;
						}
						break;
					}
				}
			}

			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					// NOTE: on unsupported platforms this will return null
					// Object o =
					// TextTransfer.getInstance().nativeToJava(event.currentDataType);
					// String t = (String) o;
					// if (t != null)
					// System.out.println(t);
				}
			}

			public void dragOperationChanged(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// allow text to be moved but files should only be copied
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					if (event.detail != DND.DROP_COPY) {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			public void dragLeave(DropTargetEvent event) {
			}

			public void dropAccept(DropTargetEvent event) {
			}

			public void drop(DropTargetEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					String text = event.data.toString();
					System.out.println(event.data);
					// System.out.println(((TreeItem)event.item).getText());
					// if (event.item != null)
					PHONGBAN dv = (PHONGBAN) ((TreeItem) event.item).getData();
					int MAPHONGBAN = dv.getMA_PHONGBAN();
					mh.OpenForm_ChuyenGiaoTaiSan_NoiBo(text.split(" "), MAPHONGBAN);
				}
			}
		});
	}
}
