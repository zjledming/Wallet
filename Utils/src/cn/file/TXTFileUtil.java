package cn.file;

import java.io.*;

/**
 * 
 * 功能描述：创建 TXT 文件并进行读、写、修改操作
 * 
 */
public class TXTFileUtil {
	public static BufferedReader bufread = null;
	// 指定文件路径和名称
	private static String path = "D:/suncity.txt";
	private static File filename = new File(path);
	private static String readStr = "";

	/** */
	/**
	 * 创建文本文件 .
	 * 
	 * @throws IOException
	 * 
	 */
	public static void creatTxtFile() throws IOException {
		if (!filename.exists()) {
			filename.createNewFile();
			System.err.println(filename + "已创建！ ");
		}
	}

	/**
	 * 未读取换行
	 * 读取文本文件 .
	 * 
	 */
	public static String readTxtFile() {
		String read;
		FileReader fileread = null;
		try {
			fileread = new FileReader(filename);
			bufread = new BufferedReader(fileread);
			try {
				while ((read = bufread.readLine()) != null) {
					readStr = readStr + read + "/r/n";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (bufread != null) {
					bufread.close();
				}
				if (fileread != null) {
					fileread.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("文件内容是 :" + "/r/n" + readStr);
		return readStr;
	}

	/** */
	/**
	 * 写文件 .
	 * 
	 */
	public static void writeTxtFile(String newStr) throws IOException {
		// 先读取原有文件内容，然后进行写入操作
		String filein = newStr + "/r/n" + readStr + "/r/n";
		RandomAccessFile mm = null;
		try {
			mm = new RandomAccessFile(filename, "rw");
			mm.writeBytes(filein);
		} catch (IOException e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		} finally {
			if (mm != null) {
				try {
					mm.close();
				} catch (IOException e2) {
					// TODO 自动生成 catch 块
					e2.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将文件中指定内容的第一行替换为其它内容 .
	 * 
	 * @param oldStr
	 *            查找内容
	 * @param replaceStr
	 *            替换内容
	 */
	public static void replaceTxtByStr(String oldStr, String replaceStr) {
		String temp = "";
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			// 保存该行前面的内容
			for (int j = 1; (temp = br.readLine()) != null
					&& !temp.equals(oldStr); j++) {
				buf = buf.append(temp);
				buf = buf.append(System.getProperty("line.separator"));
			}

			// 将内容插入
			buf = buf.append(replaceStr);

			// 保存该行后面的内容
			while ((temp = br.readLine()) != null) {
				buf = buf.append(System.getProperty("line.separator"));
				buf = buf.append(temp);
			}

			br.close();
			FileOutputStream fos = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写入文件
	 * @param file
	 * @param content
	 */
	public static void writeStr2File(String file, String content) {
		try {
			File f = new File(file);
			if (!f.exists()) {
				f.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file, false); //true 末尾接着写
			fileWriter.write(content + "\r\n");
			fileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	/**
	 * 按字节读取文件：不遗漏任何字符。 包括换行，换行之前的空格
	 * @param file
	 * @return
	 */
	public static String read(File file) {
		BufferedReader br = null;
		InputStreamReader isr = null;
		StringBuffer buffer = new StringBuffer();
		try {
			isr = new InputStreamReader(new FileInputStream(file), "gbk");
			br = new BufferedReader(isr);
			int s;
			while ((s = br.read()) != -1) {
				buffer.append((char) s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}

	/**
	 * 按行读取文件（发现bug：未读取换行符）
	 * @param file
	 * @return
	 */
	public String readFile(File file) {
		StringBuffer sb = new StringBuffer();
		FileReader fileread = null;
		BufferedReader bufread = null;
		try {
			fileread = new FileReader(file);
			bufread = new BufferedReader(fileread);
			String read;
			while ((read = bufread.readLine()) != null) {
				sb.append(read);//.append("/r/n")
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufread != null) {
					bufread.close();
				}
				if (fileread != null) {
					fileread.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/** */
	/**
	 * main方法测试
	 * 
	 * @param s
	 * @throws IOException
	 */
	public static void main(String[] s) throws IOException {
//		TXTFileUtil.creatTxtFile();
//		TXTFileUtil.readTxtFile();
		System.out.println(new TXTFileUtil().readFile(filename));
//		TXTFileUtil.writeTxtFile("20080808:12:13");
//		TXTFileUtil.replaceTxtByStr("ken", "zhang");
//		TXTFileUtil.writeStr2File(filename.getPath(),"趋势吧");
//		System.out.println(TXTFileUtil.read(filename));
		
	}
}