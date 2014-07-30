package cn.json;

import com.alibaba.fastjson.JSON;

import bean.JKSbean;

public class FastJson {
	public static void main(String[] args) {
		JKSbean bean = new JKSbean();
		bean.setDtbm("dtbn");
		bean.setDwbm("dwbm¸¸´°¿Ú");

		String jsonString = JSON.toJSONString(bean);
		System.out.println(jsonString);

		JKSbean bean1 = JSON.parseObject(jsonString, JKSbean.class);
		System.out.println(bean1.getDwbm());
	}

	public static <T> T fastjson_jsonToBean(String jsonString,
			Class<T> beanCalss) throws Exception {
		return JSON.parseObject(jsonString, beanCalss);
	}

	public static String fastjson_beanToJson(Object bean) throws Exception {
		return JSON.toJSONString(bean);
	}
}
