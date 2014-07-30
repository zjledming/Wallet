package fxm.patch.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.alibaba.fastjson.JSON;

import fxm.patch.util.PatchUtil;
import fxm.patch.util.Util;

public class MainDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	// 主panel
	private JPanel contentPane;
	// 文本框
	private JTextField patchName = new JTextField();
	private JTextField patchPath = new JTextField();
	private JTextField svnFilePath = new JTextField();
	private JTextField myeclipseFilePath = new JTextField();

	// 选择器
	JFileChooser chooser;

	// x,y,w,h - 初始大小
	int ix = 400;
	int iy = 200;
	int iwidth = 480;
	int iheight = 300;

	// 第一行第一列表格头部位置25, 30, 110, 15
	int tx = 25;
	int ty = 30;
	int twidth = 80;
	int theight = 21;

	// 文本框宽度
	int inputwidth = 250;
	// select but 宽度
	int swidth = 60;

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
				invoke(index);
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
	 * 根据index返回对应的文本框控件
	 * 
	 * @param index
	 * @return
	 */
	public JTextField getJTextFieldByIndex(int index) {
		switch (index) {
		case 2:
			return this.patchPath;
		case 3:
			return this.svnFilePath;
		case 4:
			return this.myeclipseFilePath;
		}
		return this.patchPath;
	}

	/**
	 * 点击按钮事件
	 * 
	 * @param index
	 */
	public String invoke(int index) {
		try {
			if (index == 10000) {
				// 生成补丁包
				if (MainDialog.this.patchName.getText().length() <= 0) {
					JOptionPane.showMessageDialog(MainDialog.this, "请输入补丁包名称");
					return "";
				}
				if ((MainDialog.this.patchPath.getText()
						.replace("定位到存储补丁包的文件夹(桌面)", "").length() > 0)
						&& (MainDialog.this.svnFilePath.getText()
								.replace("定位到svn导出文件下的项目(hnszwfw-core)", "")
								.length() > 0)
						&& (MainDialog.this.myeclipseFilePath.getText()
								.replace("定位到myeclipse下的项目(hnszwfw-core)", "")
								.length() > 0)) {
					PatchUtil patch = new PatchUtil(
							MainDialog.this.patchName.getText(),
							MainDialog.this.patchPath.getText(),
							MainDialog.this.svnFilePath.getText(),
							MainDialog.this.myeclipseFilePath.getText());
					boolean flag = patch.creatPatch();
					if (flag) {
						JOptionPane.showMessageDialog(MainDialog.this,
								"生成补丁包成功");
						// 生成成功之后缓存信息
						PatchUtil.cacheInfo(patch);
					} else {
						JOptionPane.showMessageDialog(MainDialog.this,
								"生成补丁包失败，详情见log");
					}
				} else {
					JOptionPane.showMessageDialog(MainDialog.this, "请选择路径");
				}
			} else {
				chooser = new JFileChooser();
				// 设置当前目录 - 默认指向tomcat默认路径
				chooser.setCurrentDirectory(new java.io.File("."));
				String title = "";
				switch (index) {
				case 2:
					title = "补丁包";
					if (bean != null) {
						chooser.setCurrentDirectory(new java.io.File(bean
								.getPatchPath()));
					} else {
						chooser.setCurrentDirectory(new java.io.File(
								"C:\\Users\\android\\Desktop"));
					}
					break;
				case 3:
					title = "svn导出文件";
					if (bean != null) {
						chooser.setCurrentDirectory(new java.io.File(bean
								.getSvnFilePath()));
					} else {
						chooser.setCurrentDirectory(new java.io.File(
								"C:\\Users\\android\\Desktop"));
					}
					break;
				case 4:
					if (bean != null) {
						chooser.setCurrentDirectory(new java.io.File(bean
								.getMyeclipseFilePath()));
					} else {
						title = "源代码";
						chooser.setCurrentDirectory(new java.io.File(
								"E:\\workspace"));
					}
					break;
				default:
					title = "";
					break;
				}
				int result;
				chooser.setDialogTitle("请选择" + title + "路径");
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
					// 给文本框赋值
					switch (index) {
					case 2:
						MainDialog.this.patchPath.setText(selectfile
								.getAbsolutePath());
						break;
					case 3:
						MainDialog.this.svnFilePath.setText(selectfile
								.getAbsolutePath());
						break;
					case 4:
						MainDialog.this.myeclipseFilePath.setText(selectfile
								.getAbsolutePath());
						break;
					default:
						JOptionPane.showMessageDialog(MainDialog.this,
								"警告：当前索引无效" + index);
						break;
					}
				} else {
					System.out.println("No Selection ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(MainDialog.this, "生成补丁包失败");
		}
		return null;
	}

	/**
	 * 初始化数据
	 */
	public void init() {
		try {
			if (bean != null) {
				patchName.setText(bean.getPatchName());
				patchPath.setText(bean.getPatchPath());
				svnFilePath.setText(bean.getSvnFilePath());
				myeclipseFilePath.setText(bean.getMyeclipseFilePath());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("初始化失败：" + e.getMessage());
		}
	}

	/**
	 * 设置按钮，倒数第二行，居中对齐
	 * 
	 * @param butText
	 */
	public void setButton(String butText) {
		// 设置按钮宽高,每2个字占60
		int bwidth = (butText.length() / 2) * 60;

		JButton button = new JButton(butText);
		/*
		 * 将一个 ActionListener 添加到按钮中;
		 * 用于接收操作事件的侦听器接口。对处理操作事件感兴趣的类可以实现此接口，而使用该类创建的对象可使用组件的 addActionListener
		 * 方法向该组件注册。在发生操作事件时，调用该对象的 actionPerformed 方法。
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invoke(10000);
			}
		});
		System.out.println(iheight);
		// button.setBounds(ix+iwidth/2-bwidth/2, iy+iheight-2*theight, bwidth,
		// theight);
		// 按钮的x.y都是相对窗体本身边界，不是显示屏的边界（减去title的宽度）
		button.setBounds(iwidth / 2 - bwidth / 2 - 5, iheight - 30 - 4
				* theight, bwidth, theight);
		this.contentPane.add(button);
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
					MainDialog frame = new MainDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static PatchUtil bean = null;

	public MainDialog() {
		setTitle("补丁包 tool");
		// 设置此窗体是否可由用户调整大小。
		setResizable(true);
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

		// 第一行头部
		setHeader("补丁包名称：", 1);
		// 第二行头部
		setHeader("补丁包路径：", 2);
		// 第三行头部
		setHeader("svn导出file：", 3);
		setHeader("源代码路径：", 4);

		// 第一行文本
		setText(this.patchName, 1, "");
		// 第二行文本
		setText(this.patchPath, 2, "定位到存储补丁包的文件夹(桌面)");
		// 第三行文本
		setText(this.svnFilePath, 3, "定位到svn导出文件下的项目(hnszwfw-core)");
		setText(this.myeclipseFilePath, 4, "定位到myeclipse下的项目(hnszwfw-core)");

		// 第一行按钮
		setSelect("选择", 2);
		// 第二行按钮
		setSelect("选择", 3);
		setSelect("选择", 4);

		// 设置按钮
		setButton("生成补丁包");

		// made by
		setMadeBy();

		// 初始化
		try {
			bean = JSON.parseObject(Util.read(PatchUtil.cacheFilePath),
					PatchUtil.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("初始异常：" + e.getMessage());
		}
		init();
	}
}