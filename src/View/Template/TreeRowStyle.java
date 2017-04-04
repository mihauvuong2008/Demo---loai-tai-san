package View.Template;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

public class TreeRowStyle {

	public void setTableItemHeight(Table table, int height) {
		table.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				// height cannot be per row so simply set
				event.height = height;
			}
		});
	}

	public void setTreeItemHeight(TreeViewer viewer, int height) {
		setTreeItemHeight(viewer.getTree(), height);
	}

	public void setTreeItemHeight(Tree tree, int height) {
		try {
			Method method = null;

			Method[] methods = tree.getClass().getDeclaredMethods();
			method = findMethod(methods, "setItemHeight", 1); //$NON-NLS-1$
			if (method != null) {
				boolean accessible = method.isAccessible();
				method.setAccessible(true);
				method.invoke(tree, Integer.valueOf(height));
				method.setAccessible(accessible);
			}
		} catch (SecurityException e) {
			// ignore
		} catch (IllegalArgumentException e) {
			// ignore
		} catch (IllegalAccessException e) {
			// ignore
		} catch (InvocationTargetException e) {
			// ignore
		}
	}

	private static Method findMethod(Method[] methods, String name, int parameterCount) {
		for (Method method : methods) {// thần thánh quá :((
			if (method.getName().equals(name) && method.getParameterTypes().length == parameterCount) {
				return method;
			}
		}
		return null;
	}

	public void Pack(Table table) {
		for (TableColumn ti : table.getColumns()) {
			ti.pack();
		}
	}

	public void Pack(Tree Tree) {
		for (TreeColumn ti : Tree.getColumns()) {
			ti.pack();
		}
	}
}
