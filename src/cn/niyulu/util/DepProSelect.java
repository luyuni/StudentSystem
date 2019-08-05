package cn.niyulu.util;

import java.util.LinkedHashMap;

import java.util.Map;
import java.util.Set;


public class DepProSelect {
	private static Map<String,String[]> model=new LinkedHashMap<String, String[]>();
	static {
		model.put("软件学院", new String[]{"软件开发","移动互联","嵌入式开发","软件工程+会计","软件工程+金融","软件工程+英语","物联网"});
		model.put("计信院", new String[] {"电子商务","计算机科学技术","信息工程","网络工程"});
		model.put("工学院", new String[] {"车辆工程","土木工程","工程管理","机械制造与自动化"});
	}

	public static Object[] getDepartment() {
	   Map<String, String[]> map = DepProSelect.model;
	   Set<String> set = map.keySet(); 
	   Object[] department = set.toArray(); 
	   return department; 
	}
	public static String[] getProfession(String selectDepartment) {
	   Map<String, String[]> map = DepProSelect.model;
	   String[] profession = map.get(selectDepartment); 
	   return profession; 
	}
}
