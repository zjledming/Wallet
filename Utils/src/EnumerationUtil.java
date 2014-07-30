import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class EnumerationUtil {

	private static ButtonGroup group = new ButtonGroup();

	/**
	 * 遍历枚举类型
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Enumeration<AbstractButton> enumeration = group.getElements();
		while (enumeration.hasMoreElements()) {
			AbstractButton abstractButton = (AbstractButton) enumeration
					.nextElement();
			if (abstractButton.getText().equals("")) {
				abstractButton.setSelected(true);
			} else {
				abstractButton.setSelected(false);
			}
		}
	}
}
