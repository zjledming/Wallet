package cn.jframe;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class RadioGetValue extends JFrame {
	private JRadioButton jrb;

	public RadioGetValue() {
		jrb = new JRadioButton("µ„Œ“");
		this.setLayout(new FlowLayout());
		this.add(jrb);
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public boolean getJRadioButtonValue() {
		return jrb.isSelected();
	}

	public static void main(String[] args) {
		RadioGetValue my = new RadioGetValue();
		my.jrb.setSelected(true);
		System.out.println(my.getJRadioButtonValue());
		my.jrb.setSelected(false);
		System.out.println(my.getJRadioButtonValue());
	}

}