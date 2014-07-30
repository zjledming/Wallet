package cn.eclipse;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
/**
 * @author huangfox
 *
 */
public class ComB extends Composite {
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public ComB(Composite parent, int style) {
		super(parent, style);
		final Label compositeBLabel = new Label(this, SWT.NONE);
		compositeBLabel.setText("composite b");
		compositeBLabel.setBounds(156, 154, 193, 17);
		//
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
