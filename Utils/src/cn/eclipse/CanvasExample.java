package cn.eclipse;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CanvasExample {

	Display display = new Display();
	Shell shell = new Shell(display);

	public CanvasExample() {
		Canvas canvas = new Canvas(shell, SWT.NULL);
		canvas.setBounds(10, 10, 200, 100);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				e.gc.drawRoundRectangle(10, 10, 180, 80, 10, 10);
			}
		});

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public static void main(String[] args) {
		new CanvasExample();
	}
}
