package cn.jframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SingleCheck extends JPanel {
	private JLabel quote;
	private JRadioButton comedy, philosophy, carpentry;
	private String comedyQuote, philosophyQuote, carpentryQuote;

	// 在Panel中设置一个Label和三个RadioButton控制它的内容
	public SingleCheck() {
		comedyQuote = "I love you what you are!";
		philosophyQuote = "I think, therefore I am.";
		carpentryQuote = "Never，never give up.";

		quote = new JLabel(comedyQuote);
		quote.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 24));

		comedy = new JRadioButton("Comedy", true);
		comedy.setBackground(Color.green);
		philosophy = new JRadioButton("philosophy");
		philosophy.setBackground(Color.green);
		carpentry = new JRadioButton("carpentry");
		carpentry.setBackground(Color.green);

		ButtonGroup group = new ButtonGroup();
		group.add(comedy);
		group.add(philosophy);
		group.add(carpentry);

		QuoteListener listener = new QuoteListener();
		comedy.addActionListener(listener);
		philosophy.addActionListener(listener);
		carpentry.addActionListener(listener);

		add(quote);
		add(comedy);
		add(philosophy);
		add(carpentry);

		setBackground(Color.green);
		setPreferredSize(new Dimension(300, 100));
	}

	// 所有单选按钮的监听器
	private class QuoteListener implements ActionListener {
		// 根据按下的单选按钮设置标签的内容
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == comedy)
				quote.setText(comedyQuote);
			else if (source == philosophy)
				quote.setText(philosophyQuote);
			else
				quote.setText(carpentryQuote);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Quote Options");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new SingleCheck());

		frame.pack();
		frame.setVisible(true);
	}
}