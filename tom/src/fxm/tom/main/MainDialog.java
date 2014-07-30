//package fxm.tom.main;
//
//import java.awt.EventQueue;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
//import fxm.tom.util.Tomcat;
//
//public class MainDialog extends JFrame {
//	private static final long serialVersionUID = 1L;
//	// 窗体标题
//	private String title;
//	// 设置此窗体是否可由用户调整大小。
//	private boolean resizable;
//
//	private JPanel contentPane;
//	private JTextField tomcatPath;
//	private JTextField projectPath;
//
//	// private JTextField userName1;
//	// private JTextField pwd1;
//
//	JFileChooser chooser;
//
//	public static void main(String[] args) {
//		/*
//		 * 理解1：（很有道理，安全并且节省内存）
//		 * 使用eventqueue.invokelater()好处是显而易见的，这个方法调用完毕后，它会被销毁，因为匿名内部类是作为临时变量存在的，
//		 * 给它分配的内存在此时会被释放
//		 * 。这个对于只需要在一个地方使用时可以节省内存，而且这个类是不可以被其它的方法或类使用的，只能被EventQueue
//		 * .invokeLater(
//		 * )来使用。但如果你需要一个在很多地方都能用到的类，而不是只在某一个类里面或者方法里用的话，定义成匿名内部类显然是不可取的。
//		 * 是，runnable是跟线程相关的类。
//		 * 
//		 * 理解2： 把这个事件（new Runnable())添加到awt的事件处理线程当中去
//		 * awt的事件处理线程会按照队列的顺序依次调用每个待处理的事件来运行
//		 * 使用该方式的原因是：awt是单线程模式的，所有awt的组件只能在(推荐方式)事件处理线程中访问，从而保证组件状态的可确定性。
//		 * 
//		 * 注：awt:抽象窗口工具包 （Abstract Windowing Toolkit）
//		 */
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainDialog frame = new MainDialog();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	public MainDialog() {
//		setTitle("tomcat tool");
//		// 设置此窗体是否可由用户调整大小。
//		setResizable(false);
//		// 设置用户在此窗体上发起 "close" 时默认执行的操作
//		setDefaultCloseOperation(3);
//		/*
//		 * (int x, int y, int width, int height)移动组件并调整其大小，使其符合新的有界矩形 r。由 r.x 和
//		 * r.y 指定组件的新位置，由 r.width 和 r.height 指定组件的新大小 如果 r.width 值或 r.height
//		 * 值小于之前调用 setMinimumSize 指定的最小大小，则它的值将自动增加。
//		 */
//		
//		setBounds(400, 200, 478, 306);
//		this.contentPane = new JPanel();
//		// setBorder
//		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		// 设置 contentPane 属性
//		setContentPane(this.contentPane);
//		// 设置此容器的布局管理器
//		this.contentPane.setLayout(null);
//
//		JLabel label = new JLabel("tomcat路径：");
//		label.setBounds(25, 30, 110, 15);
//		this.contentPane.add(label);
//
//		// 选择tomcat按钮
//		JButton jb = new JButton("选择");
//		jb.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int result;
//				chooser = new JFileChooser();
//				// 设置当前目录 - 默认指向tomcat默认路径
//				// chooser.setCurrentDirectory(new java.io.File("."));
//				chooser.setCurrentDirectory(new java.io.File(
//						fxm.tom.util.Tomcat.tomcatPath));
//				chooser.setDialogTitle("请选择tomcat路径");
//				// 设置 JFileChooser，以允许用户只选择文件、只选择目录，或者可选择文件和目录
//				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // 这里选择目录
//				// 确定是否将 AcceptAll FileFilter 用作可选择过滤器列表中一个可用选项。
//				chooser.setAcceptAllFileFilterUsed(false);
//				// 弹出一个 "Open File" 文件选择器对话框，APPROVE_OPTION：选择确认（yes、ok）后返回该值
//				if (chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) {
//					// 返回当前目录
//					System.out.println("getCurrentDirectory(): "
//							+ chooser.getCurrentDirectory());
//					// 返回选中的文件
//					File selectfile = chooser.getSelectedFile();
//					System.out.println("getSelectedFile() : " + selectfile);
//					// 给tomcat路径赋值
//					MainDialog.this.tomcatPath.setText(selectfile
//							.getAbsolutePath());
//				} else {
//					System.out.println("No Selection ");
//				}
//			}
//		});
//		jb.setBounds(390, 27, 50, 21);
//		this.contentPane.add(jb);
//
//		JLabel label_1 = new JLabel("项目路径：");
//		label_1.setBounds(25, 69, 110, 15);
//		this.contentPane.add(label_1);
//
//		// 选择项目按钮
//		jb = new JButton("选择");
//		jb.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int result;
//				chooser = new JFileChooser();
//				// 设置当前目录
//				// chooser.setCurrentDirectory(new java.io.File("."));
//				chooser.setCurrentDirectory(new java.io.File(
//						fxm.tom.util.Tomcat.projectPath));
//				chooser.setDialogTitle("请选择项目路径");
//				// 设置 JFileChooser，以允许用户只选择文件、只选择目录，或者可选择文件和目录
//				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // 这里选择目录
//				// 确定是否将 AcceptAll FileFilter 用作可选择过滤器列表中一个可用选项。
//				chooser.setAcceptAllFileFilterUsed(false);
//				// 弹出一个 "Open File" 文件选择器对话框，APPROVE_OPTION：选择确认（yes、ok）后返回该值
//				if (chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) {
//					// 返回当前目录
//					System.out.println("getCurrentDirectory(): "
//							+ chooser.getCurrentDirectory());
//					// 返回选中的文件
//					File selectfile = chooser.getSelectedFile();
//					System.out.println("getSelectedFile() : " + selectfile);
//					// 给tomcat路径赋值
//					MainDialog.this.projectPath.setText(selectfile
//							.getAbsolutePath());
//				} else {
//					System.out.println("No Selection ");
//				}
//			}
//		});
//		jb.setBounds(390, 66, 50, 21);
//		this.contentPane.add(jb);
//
//		this.tomcatPath = new JTextField();
//		this.tomcatPath.setBounds(100, 27, 290, 21);
//		// 设置此 TextField 中的列数，然后验证布局
//		this.tomcatPath.setColumns(10);
//		this.tomcatPath.setText(fxm.tom.util.Tomcat.tomcatPath);
//		this.contentPane.add(this.tomcatPath);
//
//		this.projectPath = new JTextField();
//		this.projectPath.setColumns(10);
//		this.projectPath.setBounds(100, 66, 290, 21);
//		this.projectPath.setText(fxm.tom.util.Tomcat.projectPath);
//		this.contentPane.add(this.projectPath);
//
//		JButton button = new JButton("配置");
//		/*
//		 * 将一个 ActionListener 添加到按钮中;
//		 * 用于接收操作事件的侦听器接口。对处理操作事件感兴趣的类可以实现此接口，而使用该类创建的对象可使用组件的 addActionListener
//		 * 方法向该组件注册。在发生操作事件时，调用该对象的 actionPerformed 方法。
//		 */
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if ((MainDialog.this.projectPath.getText().length() > 0)
//						&& (MainDialog.this.tomcatPath.getText().length() > 0)) {
//					System.out.println(MainDialog.this.projectPath.getText()
//							+ MainDialog.this.tomcatPath.getText());
//					boolean flag = new Tomcat().config(
//							MainDialog.this.tomcatPath.getText(),
//							MainDialog.this.projectPath.getText());
//					if (flag) {
//						JOptionPane.showMessageDialog(MainDialog.this, "配置成功！");
//					}
//					flag = new Tomcat().clear(MainDialog.this.tomcatPath
//							.getText());
//					if (flag) {
//						JOptionPane.showMessageDialog(MainDialog.this,
//								"清除缓存成功！");
//					}
//				} else {
//					// 提示框
//					JOptionPane.showMessageDialog(MainDialog.this,
//							"请选择tomcat路径和项目路径！");
//				}
//			}
//		});
//		button.setBounds(121, 116, 65, 21);
//		this.contentPane.add(button);
//
//		JButton button_1 = new JButton("清除缓存");
//		button_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (MainDialog.this.tomcatPath.getText().length() > 0) {
//					boolean flag = new Tomcat()
//							.clear(MainDialog.this.tomcatPath.getText());
//					if (flag) {
//						JOptionPane.showMessageDialog(MainDialog.this,
//								"清除缓存成功！");
//					}
//				} else {
//					// 提示框
//					JOptionPane.showMessageDialog(MainDialog.this,
//							"请选择tomcat路径！");
//				}
//			}
//		});
//		button_1.setBounds(190, 116, 130, 21);
//		this.contentPane.add(button_1);
//
//		JButton button_2 = new JButton("启动");
//		button_2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (MainDialog.this.tomcatPath.getText().length() > 0) {
//					try {
//						new Tomcat().start(MainDialog.this.tomcatPath.getText());
//					} catch (Exception e2) {
//						e2.printStackTrace();
//						// 提示框
//						JOptionPane.showMessageDialog(MainDialog.this,
//								"tomcat启动失败！");
//					}
//				} else {
//					JOptionPane.showMessageDialog(MainDialog.this,
//							"请选择tomcat路径！");
//				}
//			}
//		});
//		button_2.setBounds(324, 116, 65, 21);
//		this.contentPane.add(button_2);
//
//		// made by
//		JLabel label_madeBy = new JLabel("made by ximing.fu 版权所有.");
//		label_madeBy.setBounds(284, 186, 165, 21);
//		this.contentPane.add(label_madeBy);
//
//	}
//}