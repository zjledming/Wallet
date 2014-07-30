package cn.cmd;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.junit.Test;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Woodstox;

/**
 * Java 调用cmd.exe命令
 * 
 * @author ximing.fu
 * 
 */
public class CmdUtil {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		CmdUtil util = new CmdUtil();
		// test11();
		// test44();
		// test6();
		// util.opennote();
		//util.setFoulder();
		util.doEXE();
	}

	/**
	 * 从D盘copy一份文件到E盘-测试通过
	 */
	@Test
	public void test1() {
		try {
			String cmdStr = "cmd /c copy d:\\test.txt e:\\";
			Runtime.getRuntime().exec(cmdStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制文件夹
	 */
	public static void test11() {
		try {
			// xcopy c:\img d:\file /d/e
			String cmdStr = "cmd /c xcopy c:\\img d:\\file /d/e";
			Runtime.getRuntime().exec(cmdStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void test12() {
		try {

			/* 在dos窗口:xcopy C:\源文件 D:\源文件\/S/E */
			/* 字符所需的格式:xcopy C:\\源文件 D:\\源文件\\/S/E */
			System.out.println("请输入源文件的地址栏路径:");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String input = br.readLine();
			/* 将地址中的单斜杠变为双斜杠 */
			String from = input.replaceAll("\\\\", "\\\\\\\\");
			/* 获取文件名 */
			String name_file = from.substring(from.lastIndexOf("\\") + 1,
					from.length());
			/* 把目标拷贝到D盘根目录 */
			String to = "D:\\" + name_file + "\\/S/E";
			/* 拼装命令 */
			String cmd = "cmd.exe /C xcopy " + from + " " + to;
			System.out.println(cmd);
			/* 执行命令 */
			java.lang.Runtime.getRuntime().exec(cmd);
			System.out.println("文件拷贝完毕。");
			System.out.println("文件已经拷贝到:D:\\" + name_file);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 删除一个文件(注意是单个文件，非文件夹)-测试通过
	 */
	@Test
	public void test2() {
		try {
			String cmdStr = "cmd /c del e:\\test.txt";
			Runtime.getRuntime().exec(cmdStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 强制删除e:/test文件夹下的所有文件夹及文件-测试通过
	 */
	@Test
	public void test3() {
		try {
			String cmdStr = "cmd /c rd/s/q e:\\tomcat-6.0\\work";
			Runtime.getRuntime().exec(cmdStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 执行bat文件-测试通过，但是窗体未自动关闭
	 */
	@Test
	public static void test4() {
		String path = "F:\\tempServer\\tomcat-6.0\\bin\\startup.bat";
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run.exec("cmd.exe /k start " + path);

			// 将调用结果打印到控制台上
			InputStream in = process.getInputStream();
			while (in.read() != -1) {
				System.out.println(in.read());
			}
			in.close();
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void test44() throws IOException {
		String path = "F:\\tempServer\\tomcat-6.0\\bin\\startup.bat";
		// Runtime.getRuntime().exec(path);

		try {
			Process p = Runtime.getRuntime().exec(path);
			DataInputStream ls_in = new DataInputStream(p.getInputStream());
			String ls_str;

			try {
				while ((ls_str = ls_in.readLine()) != null) {
					System.out.println(ls_str);
				}
			} catch (IOException e) {
				System.exit(0);
			}

		} catch (Exception ex) {
			System.out.println("startup Exception: " + ex);
		}
	}

	/**
	 * ProcessDemo：打开记事本 from url: http://www.tutorialspoint.com/java/index.htm;
	 * 需要关闭记事本才能终止程序
	 */
	@Test
	public void opennote() {
		try {
			// create a new process
			System.out.println("Creating Process...");
			Process p = Runtime.getRuntime().exec("notepad.exe");

			// get the output stream
			OutputStream out = p.getOutputStream();

			// close the output stream
			System.out.println("Closing the output stream...");
			out.close();
			System.out.println(p.toString());

			// 打开txt文件
			String[] cmd = { "notepad.exe", "c:\\Result.txt" };
			Runtime.getRuntime().exec(cmd);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("done");
	}

	/**
	 * 打开文件，并且与程序独立，不关闭文件 程序也继续向下执行。 /E:ON 允许cmd扩展 /c 指之后的都是字符串
	 * 测试时把E改成F，执行结果是一样的。
	 * 
	 * @throws IOException
	 */
	public void opennotedo() throws IOException {
		Process process = Runtime.getRuntime().exec(
				"cmd /E:ON /c start c:\\Result.txt");
		// Process process =
		// Runtime.getRuntime().exec("cmd.exe /c start F:\\long_cennect.txt");
		// // 用这句话测试结果和上句话是一样的。
		InputStreamReader inputStr = new InputStreamReader(
				process.getInputStream());
		BufferedReader br = new BufferedReader(inputStr);
		String temp = "";
		while ((temp = br.readLine()) != null) {
			System.out.println(temp);
		}

		process.destroy();
		br.close();
		inputStr.close();
	}

	/**
	 * 设置工作目录，对执行与工作目录相关的批处理文件是有用的
	 * 
	 * @throws IOException
	 */
	public void setFoulder() throws IOException {
		File dir = new File("d:\\");
		Process process = Runtime.getRuntime().exec("d:\\delWork.bat",
				null, dir);
	}

	/**
	 * 显示信息
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void show() throws IOException, InterruptedException {
		String str;
		Process process = Runtime.getRuntime().exec("cmd /c dir windows");
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(process.getInputStream()));
		while ((str = bufferedReader.readLine()) != null)
			System.out.println(str);
		process.waitFor();
	}
	
	/**
	 * 执行本地ext文件，可拓展执行任何文件
	 */
	public void doEXE(){
		 // TODO Auto-generated method stub
        Runtime r = Runtime.getRuntime();
        //应用程序所在的路径
        String str_path = "C:\\Users\\android\\Desktop\\AdbeRdr11000_zh_CN.exe";
        
        try {
            //该方法开启一个新的进程
            Process pro = r.exec(str_path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //利用该方法结束开启的进程
        //pro.destroy（）；
	}

}
