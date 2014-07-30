package cn.reg;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlUtil {
	
	public static String file = "D:\\constructionproject.jsp";
	public static String descfile = "D:\\temp.txt";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String reString = read(new File(file));
//System.out.println(reString);
	String regex = "[\\s]param\\d{1,3}[\\s]";
	Pattern pattern = Pattern.compile(regex);
	Matcher m = pattern.matcher(reString);
	while (m.find()) {
		String temptrim  = m.group(0);
//		System.out.println(temptrim);
		String temp = temptrim.replaceAll("\\s", "");
//		System.out.println(temp);
		// 首字母大写
		String oTemp  = temp.substring(0, 1).toUpperCase()+temp.substring(1);
//		System.out.println(oTemp);
		String reTemp = "<input type=\"text\" name=\""+temp+"\" id=\""+temp+"\" value=\"<%=StringUtil.isBlank(bean.get"+oTemp+"())?StringUtil.deNull(bean1.get"+oTemp+"()):StringUtil.deNull(bean.get"+oTemp+"()) %>\" />";
//		System.out.println(reTemp);
//		System.out.println(temp);
//		System.out.println(reTemp);
		reString = reString.replace(temptrim, reTemp);
	}
	System.out.println(reString);
	// 

	
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
	
	public static String readFile(File file) {
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
	
}
