package fxm.patch.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;

public class PatchUtil {

	private String patchName;
	private String patchPath;
	private String svnFilePath;
	private String myeclipseFilePath;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private int count = 1;

	private String result = "";
	// 补丁包下生成日志文件
	private static File logFile;
	public static String cacheFilePath = "C:\\patchpackage.log";

	public PatchUtil() {
	}

	public PatchUtil(String patchName, String patchPath, String svnFilePath,
			String myeclipseFilePath) {
		this.patchName = patchName;
		this.patchPath = patchPath;
		this.svnFilePath = svnFilePath;
		this.myeclipseFilePath = myeclipseFilePath;
	}

	/**
	 * 写入日志
	 * 
	 * @param data
	 * @throws IOException
	 */
	public static void write2log(String data) throws IOException {
		// FileUtils.writeStringToFile(logFile, data);
		FileWriter fileWriter = new FileWriter(logFile, true);
		fileWriter.write(data + "\r\n");
		fileWriter.close();
	}

	/**
	 * 生成补丁包:<br>
	 * 1.生成【creatorepp】文件夹，并将svn中的【creatorepp】的内容copy到该文件夹中；<br>
	 * 2.生成【升级说明.txt】 ；<br>
	 * 3.将src中的java源文件copy到补丁包的class中
	 */
	public boolean creatPatch() {
		try {
			// 补丁包file
			if (!"".equals(patchName.trim())) {
				patchPath = patchPath + File.separator + patchName;
			}
			File patchFile = new File(patchPath);
			// 重新生成补丁包 - 再tm不删除了，不小心选错文件夹，后果太严重
			// FileUtils.deleteDirectory(patchFile);
			// String temp = patchPath.replace("\\", "\\\\");
			// String cmdStr = "cmd /c rd/s/q "+temp;
			// Runtime.getRuntime().exec(cmdStr);
			if (!patchFile.exists()) {
				patchFile.mkdirs();
			}

			// 补丁包下生成日志文件
			logFile = new File(patchFile, "patchpackage.log");
			if (!logFile.exists()) {
				logFile.createNewFile();
			} else {
				// 清空日志
				FileUtils.writeStringToFile(logFile, "");
			}

			write2log("==========================\r\n补丁包概况\r\n==========================");
			write2log("补丁包：" + patchFile.getPath());

			// 补丁包下creatorepp file
			File eppFile = new File(patchFile, "creatorepp");
			if (!eppFile.exists()) {
				eppFile.mkdirs();
			}
			write2log("补丁包┓creatorepp：" + eppFile.getPath());
			// 补丁包下升级说明.txt
			File sjFile = new File(patchFile, "升级说明.txt");
			if (!sjFile.exists()) {
				sjFile.createNewFile();
			}
			write2log("补丁包┓升级说明：" + sjFile.getPath());
			write2log("svn：" + svnFilePath);
			write2log("myeclipse：" + myeclipseFilePath);

			write2log("==========================\r\n步骤1：copy 【svn┓creatorepp】 to 【补丁包┓creatorepp】\r\n==========================");
			// 将svn导出文件夹下【creatorepp】下的内容复制到【eppFile】下
			String srcFilePath = this.svnFilePath + File.separator
					+ "creatorepp";
			File srcFile = new File(srcFilePath);
			/*
			 * 这里还有一种可能：就是如果只修改了java源代码，同样不会导出creatorepp文件夹
			 */
			if (!srcFile.exists()) {
				write2log("！注意：svn导出文件中不存在creatorepp目录，存在以下两种可能：");
				write2log("1.svn导出文件路径选择错误，请选择项目目录，例如：hnszwfw-core。");
				write2log("2.此次补丁并未修改任何creatorepp的文件，值修改了java源文件。");
				write2log("针对【2】，打包过程中会创建一个空的【creatorepp】目录，对补丁包无影响。");
				// this.result += "svn导出文件路径选择错误，请选择项目目录，例如：hnszwfw-core";
				// return "";
				// 创建他;在导出svn文件夹下面创建一个空creatorepp并无影响
				srcFile.mkdirs();
			}
			// 新东西就是好呀就是好...
			FileUtils.copyDirectoryToDirectory(srcFile, patchFile);

			write2log("==========================\r\n步骤2：创建【补丁包┓升级说明】，并初始信息\r\n==========================");
			// 初始【升级说明.txt】中内容
			write2sjsm(sjFile);
			write2log("==========================\r\n步骤3：处理java源文件\r\n==========================");
			write2log("a）copy：所有【svn┓src┓...┓java】下的子目录to【补丁包┓WEB-INF┓classes】");
			// 将src中的java源文件copy到补丁包的class中
			// SVN FILE
			File svnFile = new File(svnFilePath);
			write2log("svn:" + svnFile.getPath());
			// SVN 下src file
			File javaFile = new File(svnFile, "src");
			write2log("svn┓src:" + javaFile.getPath());
			if (javaFile.exists()) {
				/*
				 * 将java下的所有源代码copy到补丁包的\creatorepp\WEB-INF\classes下
				 */
				// 补丁包下creatorepp file
				File classFile = new File(eppFile, "WEB-INF\\classes");
				// 出现了一个bug，如果在不同source路径下，导出的java源代码会分别在不同的文件夹下，所以这里需要一一遍历
				for (File javaFileTemp : javaFile.listFiles()) {
					// 过滤掉source名称，直接找到java下的源代码
					File tempFile = javaFileTemp;
					write2log("svn┓src┓...:" + tempFile.getPath());
					// 如果不是java目录，找他的子目录
					while (!tempFile.getPath().endsWith("java")) {
						tempFile = tempFile.listFiles()[0];
					}
					write2log("svn┓src┓...┓java:" + tempFile.getPath());
					// 每次生成补丁包都重新产生class文件
					// 注意：这只是个补丁包工具，慎用delete，防止发生意外
					// classFile.deleteOnExit();
					if (!classFile.exists()) {
						classFile.mkdirs();
						write2log("创建  补丁包┓WEB-INF┓classes:"
								+ classFile.getPath());
					}
					File[] fileList = tempFile.listFiles();
					// 将java下的所有文件夹copy到补丁包的WEB-INF\\classes下
					for (int i = 0; i < fileList.length; i++) {
						FileUtils.copyDirectoryToDirectory(fileList[i],
								classFile);
						write2log("copy 【svn┓src┓...┓java】下的子目录【"
								+ fileList[i].getPath()
								+ "】 to 【补丁包┓WEB-INF┓classes】"
								+ classFile.getPath());
					}
				}
				write2log("b）替换：用【myeclipse┓creatorepp┓WEB-INF┓classes】下的class文件替换【补丁包┓WEB-INF┓classes】下的java文件");
				// 用项目中的class文件替换掉补丁包的\creatorepp\WEB-INF\classes下的java文件
				// 项目FILE
				File proFile = new File(myeclipseFilePath);
				write2log("myeclipse:" + proFile.getPath());
				// 项目下的class路径
				File proClassFile = new File(proFile,
						"creatorepp\\WEB-INF\\classes");
				write2log("myeclipse┓creatorepp┓WEB-INF┓classes:"
						+ proClassFile.getPath());

				// 替换文件
				// write2log("项目源代码地址：" + proClassFile.getPath());
				// write2log("补丁包class地址：" + classFile.getPath());
				// C:\Users\android\Desktop\吉首\bd1\creatorepp\WEB-INF\classes
				// E:\workspace\jszwfw-core\creatorepp\WEB-INF\classes
				copyFile(classFile, proClassFile);
				// write2log("================ class替换Java文件结束 ================");

				// System.out.println(proFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 从项目proClassFile中copy class文件到补丁包file当中
	 * 
	 * @param file
	 * @param proClassFile
	 * @throws Exception
	 */
	public void copyFile(File file, File proClassFile) throws Exception {
		// 便利补丁包中的java文件
		File[] listFiles = file.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			// 补丁包中java
			File javaFile = listFiles[i];
			if (javaFile.isFile()) {
				// java path
				String filePath = javaFile.getPath();
				// 找项目class下的对应过得文件路径
				String path = proClassFile.getPath()
						+ filePath.split("classes")[1];
				// 找的是.class文件，非.java文件
				System.out.println("====" + path);
				/*
				 * 发生异常点 原因：在补丁包中存在了.class文件 推测：应该是在补丁包删除的时候没有删除完全
				 * 解决：尽量在每次生成补丁包的时候，先删除在打包
				 */
				if (path.indexOf(".java") != -1) {
					String claFilePath = path.substring(0,
							path.indexOf(".java"))
							+ ".class";
					File claFile = new File(claFilePath);
					write2log("-----------" + count++ + "-----------");
					write2log(claFile.getPath() + "   copy到");
					write2log(javaFile.getPath());
					FileUtils.copyFileToDirectory(claFile,
							javaFile.getParentFile());
					// copy完成之后，删除补丁包下面的java文件
					javaFile.delete();
					write2log(filePath + "  已删除");
					write2log("----------------------");
				} else {
					// 记录异常
					write2log("！发生异常：当前文件非java文件：" + path
							+ "。情仔细检查文件是否为class文件：" + filePath);
				}
			} else {
				copyFile(javaFile, proClassFile);
			}
		}
		System.out.println(proClassFile);
	}

	private void write2sjsm(File f) throws Exception {
		if (!f.exists()) {
			f.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(f);
		fileWriter
				.write("补丁联系人：付细明\r\n补丁提交日期："
						+ format.format(new Date())
						+ "\r\n*****************************************\r\n注意事项：\r\n1、升级前请备份\r\n*****************************************\r\n升级步骤：\r\n1、覆盖creatorepp\r\n2、\r\n3、重启服务\r\n*****************************************\r\n功能说明：\r\n");
		fileWriter.close();
	}
	
	/**
	 * 缓存配置信息
	 * 
	 * @param patch
	 */
	public static void cacheInfo(PatchUtil patch) {
		try {
			Util.writeStr2File(cacheFilePath, JSON.toJSONString(patch));
		} catch (Exception e) {
			try {
				write2log("缓存配置信息失败：" + e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public String getPatchName() {
		return patchName;
	}

	public void setPatchName(String patchName) {
		this.patchName = patchName;
	}

	public String getPatchPath() {
		return patchPath;
	}

	public void setPatchPath(String patchPath) {
		this.patchPath = patchPath;
	}

	public String getSvnFilePath() {
		return svnFilePath;
	}

	public void setSvnFilePath(String svnFilePath) {
		this.svnFilePath = svnFilePath;
	}

	public String getMyeclipseFilePath() {
		return myeclipseFilePath;
	}

	public void setMyeclipseFilePath(String myeclipseFilePath) {
		this.myeclipseFilePath = myeclipseFilePath;
	}


}
