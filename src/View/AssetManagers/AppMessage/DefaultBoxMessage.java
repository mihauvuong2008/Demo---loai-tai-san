package View.AssetManagers.AppMessage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

public class DefaultBoxMessage {

	public void Notification(String title, String message) {
		MessageBox m = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_WARNING);
		m.setText(title);
		m.setMessage(message);
		m.open();
	}
}
