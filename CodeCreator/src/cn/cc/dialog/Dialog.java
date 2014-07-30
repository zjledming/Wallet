package cn.cc.dialog;

import java.awt.EventQueue;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.cc.db.Config;
import cn.cc.db.JDBCCRUD;
import cn.cc.install.Bean;
import cn.cc.install.BeanUtils;
import cn.cc.util.Constant;
import cn.cc.util.StringUtil;

public class Dialog extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Set<String> CODE_TYPE = new HashSet();

	// 主panel
	private JPanel contentPane;
	// 文本框
	private JTextField descPath = new JTextField();
	private JTextField url = new JTextField();
	private JTextField user = new JTextField();
	private JTextField password = new JTextField();
	private JTextField tablename = new JTextField();
	private JTextField charsetName = new JTextField();
	// 选择器
	JFileChooser chooser;

	// x,y,w,h - 初始大小
	int ix = 400;
	int iy = 200;
	int iwidth = 480;
	int iheight = 460;

	// 第一行第一列表格头部位置25, 30, 110, 15
	int tx = 25;
	int ty = 30;
	int twidth = 95;
	int theight = 21;

	// 文本框宽度
	int inputwidth = 250;
	// select but 宽度
	int swidth = 60;

	/**
	 * 实现ItemListener接口，监听复选框点击事件
	 * 
	 * @author ximing.fu
	 * 
	 */
	public class BoxListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			ItemSelectable source = e.getItemSelectable();
			if (source.toString().indexOf("text=bean") != -1) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					// 取消选择
					CODE_TYPE.remove("bean");
				} else {
					CODE_TYPE.add("bean");
				}
			} else if (source.toString().indexOf("text=dao") != -1) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					// 取消选择
					CODE_TYPE.remove("dao");
				} else {
					CODE_TYPE.add("dao");
				}
			} else if (source.toString().indexOf("text=service") != -1) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					// 取消选择
					CODE_TYPE.remove("service");
				} else {
					CODE_TYPE.add("service");
				}
			} else if (source.toString().indexOf("text=List") != -1) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					// 取消选择
					CODE_TYPE.remove("List");
				} else {
					CODE_TYPE.add("List");
				}
			}
			System.out.println(CODE_TYPE.toString());
		}
	}

	public static void main(String[] args) {
		/*
		 * 理解1：（很有道理，安全并且节省内存）
		 * 使用eventqueue.invokelater()好处是显而易见的，这个方法调用完毕后，它会被销毁，因为匿名内部类是作为临时变量存在的，
		 * 给它分配的内存在此时会被释放
		 * 。这个对于只需要在一个地方使用时可以节省内存，而且这个类是不可以被其它的方法或类使用的，只能被EventQueue
		 * .invokeLater(
		 * )来使用。但如果你需要一个在很多地方都能用到的类，而不是只在某一个类里面或者方法里用的话，定义成匿名内部类显然是不可取的。
		 * 是，runnable是跟线程相关的类。
		 * 
		 * 理解2： 把这个事件（new Runnable())添加到awt的事件处理线程当中去
		 * awt的事件处理线程会按照队列的顺序依次调用每个待处理的事件来运行
		 * 使用该方式的原因是：awt是单线程模式的，所有awt的组件只能在(推荐方式)事件处理线程中访问，从而保证组件状态的可确定性。
		 * 
		 * 注：awt:抽象窗口工具包 （Abstract Windowing Toolkit）
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dialog frame = new Dialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	String[] radioARR = new String[] { "oracle", "MySQL", "SqlServer",
			"informix" };

	public Dialog() {
		setTitle("代码自动生成 tool");
		// 设置此窗体是否可由用户调整大小。
		setResizable(false);
		// 设置用户在此窗体上发起 "close" 时默认执行的操作
		setDefaultCloseOperation(3);
		/*
		 * (int x, int y, int width, int height)移动组件并调整其大小，使其符合新的有界矩形 r。由 r.x 和
		 * r.y 指定组件的新位置，由 r.width 和 r.height 指定组件的新大小 如果 r.width 值或 r.height
		 * 值小于之前调用 setMinimumSize 指定的最小大小，则它的值将自动增加。
		 */
		setBounds(ix, iy, iwidth, iheight);
		this.contentPane = new JPanel();
		// setBorder
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// 设置 contentPane 属性
		setContentPane(this.contentPane);
		// 设置此容器的布局管理器
		this.contentPane.setLayout(null);

		int k = 1; // 行序号
		setHeader("代码输出地址：", k++);
		setRadio(radioARR, k++);
		setHeader("DB URL：", k++);
		setHeader("DB USER：", k++);
		setHeader("DB PASSWORD：", k++);
		setHeader("TABLE NAME：", k++);
		setHeader("CODE 编码：", k++);
		// setHeader("表对应序列：", k++);
		// setHeader("表说明：", k++);

		int m = 1;
		// 第一行文本
		setText(this.descPath, m++, "");
		m++;// 这一行为radio跳过
		// 第二行文本
		setText(this.url, m++, "Constant.oracle");
		// 第三行文本
		setText(this.user, m++, "");
		setText(this.password, m++, "");
		setText(this.tablename, m++, "");
		setText(this.charsetName, m++, "GBK");
		String[] arr = { "bean", "dao", "service", "List" };
		setBox(arr, m++);

		// 第一行按钮
		setSelect("选择", 1);

		CODE_TYPE.add("bean");
		CODE_TYPE.add("dao");
		CODE_TYPE.add("service");
		CODE_TYPE.add("List");

		// 设置按钮
		setButton(new String[] { "test db", "生成代码" });

		// made by
		setMadeBy();

		// 缓存初始化
		Config bean = Config.init();
		init(bean);
	}

	/**
	 * 初始化数据
	 * 
	 * @param bean
	 */
	public void init(Config bean) {
		try {
			if (bean != null) {
				descPath.setText(bean.getCodeUrl());
				url.setText(bean.getDbUrl());
				user.setText(bean.getDbUser());
				password.setText(bean.getDbPassword());
				tablename.setText(bean.getTableName());
				charsetName.setText(bean.getCharsetName());
				if (!StringUtil.isBlank(bean.getType())) {
					for (int i = 0; i < radioARR.length; i++) {
						if (bean.getType().equals(radioARR[i])) {
							Enumeration<AbstractButton> enumeration = group
									.getElements();
							while (enumeration.hasMoreElements()) {
								AbstractButton abstractButton = (AbstractButton) enumeration
										.nextElement();
								if (abstractButton.getText()
										.equals(radioARR[i])) {
									abstractButton.setSelected(true);
								} else {
									abstractButton.setSelected(false);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("初始化失败：" + e.getMessage());
		}
	}

	/**
	 * 创建复选框按键，并设置快捷键，和选定
	 * 
	 * @param boxText
	 * @param index
	 */
	public void setBox(String[] boxText, int index) {
		// 多了n行
		int n = index - 1;
		for (int i = 0; i < boxText.length; i++) {
			JCheckBox box = new JCheckBox(boxText[i]);
			if (!"List".equals(boxText)) {
				box.setSelected(true);
			}
			/*
			 * 设置当前模型上的键盘助记符。 助记符是某种键，它与外观的无鼠标修饰符（通常是
			 * Alt）组合时（如果焦点包含在此按钮祖先窗口中的某个地方）将激活此按钮。
			 */
			// chin.setMnemonic(KeyEvent.VK_C);
			box.setBounds(tx + i * 100 + 30, ty + 40 * n, twidth, theight);
			// 添加复选框的监听事件
			box.addItemListener(new BoxListener());
			this.contentPane.add(box);
		}
	}

	int radioWidth = 90;
	int radioGap = 5;

	private ButtonGroup group = new ButtonGroup();

	/**
	 * 添加单选框
	 * 
	 * @param texts
	 *            单选框文本数组
	 * @param index
	 *            行数
	 */
	public void setRadio(String[] texts, int index) {
		int n = index - 1;
		int x0 = (iwidth - texts.length * radioWidth - radioGap) / 2;
		for (int i = 0; i < texts.length; i++) {
			JRadioButton radioButton = new JRadioButton(texts[i]);
			if ("oracle".equals(texts[i])) {
				radioButton.setSelected(true);
			}
			radioButton.setBounds(x0 + i * (radioWidth + radioGap),
					ty + 40 * n, radioWidth, theight);
			radioButton.addItemListener(new RadioListener());
			this.contentPane.add(radioButton);
			group.add(radioButton);
		}
	}

	private static String dbtype = "oracle"; // db类型

	/**
	 * 监听radio点击事件
	 * 
	 * @author ximing.fu
	 * 
	 */
	public class RadioListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			String tempUrl = "Constant.oracle";
			if (e.toString().indexOf("text=oracle") != -1) {
				dbtype = Constant.oracle;
			} else if (e.toString().indexOf("text=MySQL") != -1) {
				tempUrl = Constant.mysql;
				dbtype = "MySQL";
			} else if (e.toString().indexOf("text=SqlServer") != -1) {
				tempUrl = Constant.sqlserver;
				dbtype = "SqlServer";
			} else if (e.toString().indexOf("text=informix") != -1) {
				tempUrl = Constant.informix;
				dbtype = "informix";
			}
			Config bean = Config.init();
			if(bean == null){
				url.setText(tempUrl);
			}
		}
	}

	int butWidth = 90;

	/**
	 * 设置按钮，倒数第二行，居中对齐
	 * 
	 * @param butText
	 */
	public void setButton(String[] butText) {
		int butx0 = (iwidth - butText.length * butWidth - 30) / 2;
		for (int i = 0; i < butText.length; i++) {
			JButton button = new JButton(butText[i]);
			/*
			 * 将一个 ActionListener 添加到按钮中;
			 * 用于接收操作事件的侦听器接口。对处理操作事件感兴趣的类可以实现此接口，而使用该类创建的对象可使用组件的
			 * addActionListener 方法向该组件注册。在发生操作事件时，调用该对象的 actionPerformed 方法。
			 */
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buttonEvent(e);
				}
			});
			// 按钮的x.y都是相对窗体本身边界，不是显示屏的边界（减去title的宽度）
			System.out.println(butx0);
			button.setBounds(butx0 + i * (butWidth + 30), iheight - 30 - 4
					* theight, butWidth, theight);
			this.contentPane.add(button);

		}

	}

	private static boolean testdbflag = false;

	/**
	 * 按钮处理方法
	 * 
	 * @param e
	 */
	public String buttonEvent(ActionEvent e) {
		String text = e.getActionCommand();
		String urltemp = Dialog.this.url.getText().replace(Constant.oracle, "")
				.replace(Constant.mysql, "").replace(Constant.sqlserver, "")
				.replace(Constant.informix, "");
		if (urltemp.length() <= 0) {
			JOptionPane.showMessageDialog(Dialog.this, "请输入数据库地址");
			return "";
		}
		if (Dialog.this.user.getText().length() <= 0) {
			JOptionPane.showMessageDialog(Dialog.this, "请输入用户名");
			return "";
		}
		if (Dialog.this.password.getText().length() <= 0) {
			JOptionPane.showMessageDialog(Dialog.this, "请输入密码");
			return "";
		}
		// if (Dialog.this.tablename.getText().length() <= 0) {
		// JOptionPane.showMessageDialog(Dialog.this, "请输入表名");
		// }
		if ("test db".equals(text)) {
			JDBCCRUD.url = StringUtil.deNull(Dialog.this.url.getText());
			JDBCCRUD.username = StringUtil.deNull(Dialog.this.user.getText());
			JDBCCRUD.password = StringUtil.deNull(Dialog.this.password
					.getText());
			JDBCCRUD.dbtype = dbtype;
			testdbflag = JDBCCRUD.test();
			if (testdbflag) {
				JOptionPane.showMessageDialog(Dialog.this,
						"DB Connection successful.");
				// 保存配置
				Config config = new Config(
						StringUtil.deNull(Dialog.this.descPath.getText()),
						dbtype, JDBCCRUD.url, JDBCCRUD.username,
						JDBCCRUD.password,
						StringUtil.deNull(Dialog.this.tablename.getText()),StringUtil.deNull(Dialog.this.charsetName.getText()));
				Config.saveConfig(config);
			} else {
				JOptionPane.showMessageDialog(Dialog.this,
						"DB Connection fail.");
			}
		} else if ("生成代码".equals(text)) {
			// 先测试连接
			if (!testdbflag) {
				JOptionPane.showMessageDialog(Dialog.this,
						"DB Connection fail，请修改db配置，【test DB】成功后才能生成代码");
				return "";
			}
			
			if (Dialog.this.descPath.getText().length() <= 0) {
				JOptionPane.showMessageDialog(Dialog.this, "请选择代码输出地址");
				return "";
			} 
			String tempdescPath = StringUtil.deNull(Dialog.this.descPath.getText());
			if (tempdescPath.split("src\\\\").length > 1) {
			} else {
				JOptionPane.showMessageDialog(Dialog.this,
						"请将代码输出到【project->src->任意package】下");
				return "";
			}
			if (Dialog.this.charsetName.getText().length() <= 0) {
				JOptionPane.showMessageDialog(Dialog.this, "请输入代码编码格式");
				return "";
			}
			
			Bean temp = new Bean();
			temp.setName(Dialog.this.tablename.getText());
			temp.setBeanUrl(Dialog.this.descPath.getText());
			// 编码
			JDBCCRUD.charsetName = StringUtil.deNull(Dialog.this.charsetName.getText());
			boolean flag = BeanUtils.execute(temp);
			if (flag) {
				JOptionPane.showMessageDialog(Dialog.this, "生成代码成功.");
				// 保存配置
				Config config = new Config(
						StringUtil.deNull(Dialog.this.descPath.getText()),
						dbtype, JDBCCRUD.url, JDBCCRUD.username,
						JDBCCRUD.password,
						StringUtil.deNull(Dialog.this.tablename.getText()),StringUtil.deNull(Dialog.this.charsetName.getText()));
				Config.saveConfig(config);
			} else {
				JOptionPane.showMessageDialog(Dialog.this, "生成代码失败.");
			}
		}
		return "";
	}

	/**
	 * 设置表格头部，每多一行，x不变，y+40，w,h不变
	 * 
	 * @param herdText
	 *            文本
	 * @param index
	 *            第几行头部，从index=1开始
	 */
	public void setHeader(String herdText, int index) {
		// 多了n行
		int n = index - 1;
		JLabel label = new JLabel(herdText);
		label.setBounds(tx, ty + 40 * n, twidth, theight);
		this.contentPane.add(label);
	}

	/**
	 * 版权说明
	 */
	public void setMadeBy() {
		JLabel label = new JLabel("made by ximing.fu 版权所有.");
		label.setBounds((int) (iwidth * 0.57), iheight - 30 - 2 * theight, 185,
				theight);
		this.contentPane.add(label);
	}

	/**
	 * 设置表格按钮，每多一行，x不变，y+40，w,h不变
	 * 
	 * @param butText
	 *            按钮名称
	 * @param index
	 *            第几行按钮，从index=1开始
	 */
	public void setSelect(String butText, final int index) {
		JButton jb = new JButton(butText);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				// 设置当前目录 - 默认指向tomcat默认路径
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("请选择代码输出地址路径");
				// 设置 JFileChooser，以允许用户只选择文件、只选择目录，或者可选择文件和目录(这里选择目录)
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				// 确定是否将 AcceptAll FileFilter 用作可选择过滤器列表中一个可用选项。
				chooser.setAcceptAllFileFilterUsed(false);
				// 弹出一个 "Open File" 文件选择器对话框，APPROVE_OPTION：选择确认（yes、ok）后返回该值
				if (chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) {
					// 返回当前目录
					System.out.println("getCurrentDirectory(): "
							+ chooser.getCurrentDirectory());
					// 返回选中的文件
					File selectfile = chooser.getSelectedFile();
					System.out.println("getSelectedFile() : " + selectfile);
					Dialog.this.descPath.setText(selectfile.getAbsolutePath());
				} else {
					System.out.println("No Selection ");
				}
			}
		});
		// 多了n行
		int n = index - 1;
		// x位置为边界+表头部宽度+文本框宽度+10（隔开一定距离）
		jb.setBounds(tx + twidth + inputwidth + 10, ty + 40 * n, swidth,
				theight);
		this.contentPane.add(jb);
	}

	/**
	 * 设置表格文本框，每多一行，x不变，y+40，w,h不变
	 * 
	 * @param jTextField
	 *            操作控件
	 * @param index
	 *            第几行文本，从index=1开始
	 */
	public void setText(JTextField jTextField, int index, String text) {
		// 多了n行
		int n = index - 1;
		// jTextField = new JTextField();
		// x位置为边界+表头部宽度
		jTextField.setBounds(tx + twidth, ty + 40 * n, inputwidth, theight);
		// 设置此 TextField 中的列数，然后验证布局
		jTextField.setColumns(10);
		jTextField.setText(text);
		this.contentPane.add(jTextField);
	}
}
