package com.chinacreator.toolkit;

import com.chinacreator.util.CryptUtil;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainDialog extends JFrame {
	private static final long serialVersionUID = 1L;
	// 窗体标题
	private String title;
	// 设置此窗体是否可由用户调整大小。
	private boolean resizable;

	private JPanel contentPane;
	private JTextField userName;
	private JTextField pwd;
	private JTextField userName1;
	private JTextField pwd1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDialog frame = new MainDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainDialog() {
		setTitle("加密解密工具");
		// 设置此窗体是否可由用户调整大小。
		setResizable(false);
		// 设置用户在此窗体上发起 "close" 时默认执行的操作
		setDefaultCloseOperation(3);
		/*
		 * (int x, int y, int width, int height)移动组件并调整其大小，使其符合新的有界矩形 r。由 r.x 和
		 * r.y 指定组件的新位置，由 r.width 和 r.height 指定组件的新大小 如果 r.width 值或 r.height
		 * 值小于之前调用 setMinimumSize 指定的最小大小，则它的值将自动增加。
		 */
		setBounds(400, 200, 396, 306);
		this.contentPane = new JPanel();
		// setBorder
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// 设置 contentPane 属性
		setContentPane(this.contentPane);
		// 设置此容器的布局管理器
		this.contentPane.setLayout(null);

		JLabel label = new JLabel("用户名：");
		label.setBounds(25, 30, 54, 15);
		this.contentPane.add(label);

		JLabel label_1 = new JLabel("密码：");
		label_1.setBounds(25, 69, 54, 15);
		this.contentPane.add(label_1);

		JLabel label_2 = new JLabel("用户名：");
		label_2.setBounds(25, 166, 54, 15);
		this.contentPane.add(label_2);

		JLabel label_3 = new JLabel("密码：");
		label_3.setBounds(25, 205, 54, 15);
		this.contentPane.add(label_3);

		this.userName = new JTextField();
		this.userName.setBounds(76, 27, 290, 21);
		// 设置此 TextField 中的列数，然后验证布局
		this.userName.setColumns(10);
		this.contentPane.add(this.userName);

		this.pwd = new JTextField();
		this.pwd.setColumns(10);
		this.pwd.setBounds(76, 66, 290, 21);
		this.contentPane.add(this.pwd);

		this.userName1 = new JTextField();
		this.userName1.setEditable(false);
		this.userName1.setColumns(10);
		this.userName1.setBounds(76, 163, 290, 21);
		this.contentPane.add(this.userName1);

		this.pwd1 = new JTextField();
		this.pwd1.setEditable(false);
		this.pwd1.setColumns(10);
		this.pwd1.setBounds(76, 202, 290, 21);
		this.contentPane.add(this.pwd1);

		JButton button = new JButton("加密");
		/*
		 * 将一个 ActionListener 添加到按钮中;
		 * 用于接收操作事件的侦听器接口。对处理操作事件感兴趣的类可以实现此接口，而使用该类创建的对象可使用组件的 addActionListener
		 * 方法向该组件注册。在发生操作事件时，调用该对象的 actionPerformed 方法。
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((MainDialog.this.pwd.getText().length() > 0)
						&& (MainDialog.this.userName.getText().length() > 0)) {
					// 赋值
					MainDialog.this.pwd1.setText(CryptUtil
							.encrypt(MainDialog.this.pwd.getText()));
					MainDialog.this.userName1.setText(CryptUtil
							.encrypt(MainDialog.this.userName.getText()));
				} else {
					// 提示框
					JOptionPane.showMessageDialog(MainDialog.this,
							"用户名和密码不能为空！");
				}
			}
		});
		button.setBounds(121, 116, 65, 21);
		this.contentPane.add(button);

		JButton button_1 = new JButton("解密");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((MainDialog.this.pwd.getText().length() > 0)
						&& (MainDialog.this.userName.getText().length() > 0)) {
					MainDialog.this.pwd1.setText(CryptUtil
							.decrypt(MainDialog.this.pwd.getText()));
					MainDialog.this.userName1.setText(CryptUtil
							.decrypt(MainDialog.this.userName.getText()));
				} else {
					JOptionPane.showMessageDialog(MainDialog.this,
							"用户名和密码不能为空！");
				}
				if (MainDialog.this.pwd1.getText().length() == 0)
					JOptionPane
							.showMessageDialog(MainDialog.this, "不是密文，解密出错！");
			}
		});
		button_1.setBounds(217, 116, 65, 21);
		this.contentPane.add(button_1);

		JButton button_2 = new JButton("关闭");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_2.setBounds(291, 250, 65, 21);
		this.contentPane.add(button_2);
	}
}