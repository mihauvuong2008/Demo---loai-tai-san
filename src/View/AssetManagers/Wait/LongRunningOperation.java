package View.AssetManagers.Wait;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;

public class LongRunningOperation extends Thread {
	private static Log log = LogFactory.getLog(LongRunningOperation.class);
	private ProgressBar progressBar;

	public LongRunningOperation(Display display, ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public void run() {
		long t = System.nanoTime();
		while (!progressBar.isDisposed()) {
			try {
				log.info("waiting for loading data");
				Thread.sleep(200);
			} catch (InterruptedException e) {
				log.info("loading data Exception: " + e.getMessage());
			}
			if (!progressBar.isDisposed())
				progressBar.getDisplay().asyncExec(new Runnable() {
					public void run() {
						if (progressBar.isDisposed())
							return;
						if (progressBar.getSelection() >= 100) {
							progressBar.setSelection(0);
						}
						progressBar.setSelection(progressBar.getSelection() + 5);
						System.out.println("running");
					}
				});
		}
		System.out.println("time: " + (System.nanoTime() - t));
	}
}
