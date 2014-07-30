package mst;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class TestMain{
	/*
	 * 比如：1,2,3,4,5，6,7我任选5个，输出所有的组合
	 */
	public static void main(String[] args) {
		int arrays[] = new int [5];
		List<Integer> temp = new ArrayList<Integer>();//定义一个用来存放出现过数字的容易
		int numIndex = 0;
			for (arrays[0] = 1; arrays[0] <= 7; ++arrays[0]) {
				temp.clear();//清空标记集合
				if(!isExsit(temp, arrays[0])){//没有用过的数据才能使用
					temp.add(arrays[0]);
					for (arrays[1] = 1; arrays[1] <= 7; ++arrays[1]){
						if(!isExsit(temp, arrays[1])){
							temp.add(arrays[1]);
							for (arrays[2] = 1; arrays[2] <= 7; ++arrays[2]){
								if(!isExsit(temp, arrays[2])){
									temp.add(arrays[2]);
									for (arrays[3] = 1; arrays[3] <= 7; ++arrays[3]){
										if(!isExsit(temp, arrays[3])){
											temp.add(arrays[3]);
											for (arrays[4] = 1; arrays[4] <= 7; ++arrays[4]){
												if(!isExsit(temp, arrays[4])){
													System.out.println("number"+(++numIndex)+":"+arrays[0]+arrays[1]+arrays[2]+arrays[3]+arrays[4]+"");
												}
											}
										}
										
									}
								}
							}
						}
					}
				}
				
			}
			System.out.println();
	}
	
	public static boolean isExsit(List<Integer> temp,int number){
		boolean flag = false;
		for(Integer num:temp){
			if(num == number){
				flag = true;
				return flag;
			}
		}
		return flag;
	}
	
	public static Date changeDate(String dateStr){
		Date date = null;
		Student s = new Student();
		s.setS_name("1name");
		s.setS_pwd("1s_pwd");
		Student s2 = new Student();
		s2.setS_name("2name");
		s2.setS_pwd("2s_pwd");
		Map<String, Student> maps = new HashMap<String, Student>();
		maps.put("1", s);
		maps.put("2", s2);
		Set<String> keys = maps.keySet();
		Collection<Student> students = maps.values();
		Arrays.sort(maps.values().toArray());
		return date;
	}
}
