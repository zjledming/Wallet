package cn.cc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;
import cn.cc.db.Config;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param bean
	 * @return
	 */
	public static String beanToJson(Object bean) throws Exception {

		JSONObject json = JSONObject.fromObject(bean);

		return json.toString();

	}
	

	public static String fastjson_beanToJson(Object bean) throws Exception {
		return JSON.toJSONString(bean);
	}

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象
	 * 
	 * @param jsonString
	 * @param beanCalss
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T bean = (T) JSONObject.toBean(jsonObject, beanCalss);

		return bean;

	}
	

	public static <T> T fastjson_jsonToBean(String jsonString, Class<T> beanCalss) throws Exception {
		return JSON.parseObject(jsonString, beanCalss);
	}
	

	/**
	 * 写入文件
	 * 
	 * @param content
	 * @throws Exception
	 */
	public static void writeStr2File(String content) throws Exception {
		writeStr2File(Config.logFile, content);
	}

	/**
	 * 写入文件
	 * 
	 * @param file
	 * @param content
	 */
	public static void writeStr2File(String file, String content)
			throws Exception {
		try {
			File f = new File(file);
			if (!f.exists()) {
				f.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file, false); // true 末尾接着写
			fileWriter.write(content + "\r\n");
			fileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}

	}

	
	public static String read() throws Exception {
		return read(Config.logFile);
	}
	/**
	 * 按字节读取文件：不遗漏任何字符。 包括换行，换行之前的空格
	 * 
	 * @param file
	 * @return
	 */
	public static String read(String file) throws Exception {
		BufferedReader br = null;
		InputStreamReader isr = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (!new File(file).exists()) {
				return "";
			}
			isr = new InputStreamReader(new FileInputStream(file), "gbk");
			br = new BufferedReader(isr);
			int s;
			while ((s = br.read()) != -1) {
				buffer.append((char) s);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
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

	public static void main(String[] args) throws Exception {
		Config bean = new Config();
		String re = fastjson_beanToJson(bean);
		System.out.println(re);
	}
}