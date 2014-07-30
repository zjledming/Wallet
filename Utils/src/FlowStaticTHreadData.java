

import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: FlowStaticTHreadData
 * @Description: 工作流临时静态线程数据,业务待办中使用
 * 使用规则：
 * 存入变量：FlowStaticTHreadData.setMgrName(StringUtil.deNull(mgrName));
 * 获取变量：String mgrName = FlowStaticTHreadData.getMgrName();
 * @author XiMing.Fu
 * @date 2014-3-12 下午02:32:54
 * 
 */
public class FlowStaticTHreadData {

	/**
	 * 用来临时存储应用id
	 */
	private static Map<Long, String> CurrentAppId = new HashMap<Long, String>();
	/**
	 * 用来临时存储业务数据
	 */
	private static Map<Long, Map<String,String>> CurrentBusidata = new HashMap<Long, Map<String,String>>();
	/**
	 * 用来临时存储业务id
	 */
	private static Map<Long, String> BuisinessId = new HashMap<Long, String>();
	/**
	 * 用来临时存储流程名称
	 */
	private static Map<Long, String> MgrName = new HashMap<Long, String>();
	/**
	 * 用来临时存数模块(事项)id
	 */
	private static Map<Long, String> ModuleId = new HashMap<Long, String>();
	/**
	 * 是否暂存
	 */
	private static Map<Long, Boolean> IsTobe = new HashMap<Long, Boolean>();
	/**
	 * 用来临时存储流程名称
	 */
	private static Map<Long, String> Action_form = new HashMap<Long, String>();
	
	/**
	 * 用来临时存储流程名称
	 */
	private static Map<Long, String> Backflow_username = new HashMap<Long, String>();
	
	/**
	 * 待办状态 (05 已终止, 04 暂存, 01 在办, 02 一般办结)
	 */
	private static Map<Long, String> Status_code = new HashMap<Long, String>();
	
	/**
	 * 动态执行人
	 */
	private static Map<Long, String> Dynamicperformer = new HashMap<Long, String>();
	
	public static void setDynamicperformer(String dynamicperformer){
		Dynamicperformer.put(Thread.currentThread().getId(), dynamicperformer);
	}
	
	public static String getDynamicperformer(){
		if(Dynamicperformer.containsKey(Thread.currentThread().getId())){
			String dynamicperformer = Dynamicperformer.get(Thread.currentThread().getId());
			return dynamicperformer == null ? "" : dynamicperformer;
		}else{
			return "";
		}
	}
	
	public static void setBackflow_username(String backflow_username){
		Backflow_username.put(Thread.currentThread().getId(), backflow_username);
	}
	
	public static String getBackflow_username(){
		if(Backflow_username.containsKey(Thread.currentThread().getId())){
			String backflow_username = Backflow_username.get(Thread.currentThread().getId());
			return backflow_username == null ? "" : backflow_username;
		}else{
			return "";
		}
	}

	public static void setStatus_code(String status_code){
		Status_code.put(Thread.currentThread().getId(), status_code);
	}
	
	public static String getStatus_code(){
		if(Status_code.containsKey(Thread.currentThread().getId())){
			String status_code = Status_code.get(Thread.currentThread().getId());
			return status_code == null ? "" : status_code;
		}else{
			return "";
		}
	}
	
	public static void setAction_form(String action_form){
		Action_form.put(Thread.currentThread().getId(), action_form);
	}
	
	public static String getAction_form(){
		if(Action_form.containsKey(Thread.currentThread().getId())){
			String action_form = Action_form.get(Thread.currentThread().getId());
			return action_form == null ? "" : action_form;
		}else{
			return "";
		}
	}
	
	public static void set(String appId){
		CurrentAppId.put(Thread.currentThread().getId(), appId);
	}
	
	public static String get(){
		if(CurrentAppId.containsKey(Thread.currentThread().getId())){
			String appId = CurrentAppId.get(Thread.currentThread().getId());
			return appId == null ? "module" : appId;
		}else{
			return "module";
		}
	}

	public static void setIsTobe(boolean isTobe){
		IsTobe.put(Thread.currentThread().getId(), isTobe);
	}
	
	public static boolean getIsTobe(){
		if(IsTobe.containsKey(Thread.currentThread().getId())){
			boolean istobe = IsTobe.get(Thread.currentThread().getId());
			return istobe;
		}else{
			return false;
		}
	}

	public static void setMgrName(String mgrName){
		MgrName.put(Thread.currentThread().getId(), mgrName);
	}
	
	public static String getMgrName(){
		if(MgrName.containsKey(Thread.currentThread().getId())){
			String mgrName = MgrName.get(Thread.currentThread().getId());
			return mgrName == null ? "" : mgrName;
		}else{
			return "";
		}
	}

	public static void setModuleId(String moduleId){
		ModuleId.put(Thread.currentThread().getId(), moduleId);
	}
	
	public static String getModuleId(){
		if(ModuleId.containsKey(Thread.currentThread().getId())){
			String moduleId = ModuleId.get(Thread.currentThread().getId());
			return moduleId == null ? "" : moduleId;
		}else{
			return "";
		}
	}
	
	public static void setBuisinessId(String busiId){
		System.out.println("Thread Id(setBuisinessId):"+Thread.currentThread().getId());
//		FlowClassUtil.writeStr2File("c:\\Result.txt","Thread Id(setBuisinessId):"+Thread.currentThread().getId());
		BuisinessId.put(Thread.currentThread().getId(), busiId);
	}
	
	public static String getBuisinessId(){
		if(BuisinessId.containsKey(Thread.currentThread().getId())){
			System.out.println("Thread Id(getBuisinessId):"+Thread.currentThread().getId());
//			FlowClassUtil.writeStr2File("c:\\Result.txt","Thread Id(getBuisinessId):"+Thread.currentThread().getId());
			String busiId = BuisinessId.get(Thread.currentThread().getId());
			return busiId == null ? "" : busiId;
		}else{
			return "";
		}
	}
	
	public static void removeAppId(){
		if(CurrentAppId.containsKey(Thread.currentThread().getId())){
			CurrentAppId.remove(Thread.currentThread().getId());
		}
	}

	public static void removeBusidata(){
		if(CurrentBusidata.containsKey(Thread.currentThread().getId())){
			CurrentBusidata.remove(Thread.currentThread().getId());
		}
	}
	
	public static void removeBuisinessId(){
		if(BuisinessId.containsKey(Thread.currentThread().getId())){
			BuisinessId.remove(Thread.currentThread().getId());
		}
	}

	public static void removeMgrName(){
		if(MgrName.containsKey(Thread.currentThread().getId())){
			MgrName.remove(Thread.currentThread().getId());
		}
	}

	

	public static void removeIsTobe(){
		if(IsTobe.containsKey(Thread.currentThread().getId())){
			IsTobe.remove(Thread.currentThread().getId());
		}
	}
	
	public static void removeModuleId(){
		if(ModuleId.containsKey(Thread.currentThread().getId())){
			ModuleId.remove(Thread.currentThread().getId());
		}
	}
	
	public static void removeAction_form(){
		if(Action_form.containsKey(Thread.currentThread().getId())){
			Action_form.remove(Thread.currentThread().getId());
		}
	}
	
	public static void removeStatus_code(){
		if(Status_code.containsKey(Thread.currentThread().getId())){
			Status_code.remove(Thread.currentThread().getId());
		}
	}
	
	public static void removeBackflow_username(){
		if(Backflow_username.containsKey(Thread.currentThread().getId())){
			Backflow_username.remove(Thread.currentThread().getId());
		}
	}
	
	public static void removeDynamicperformer(){
		if(Dynamicperformer.containsKey(Thread.currentThread().getId())){
			Dynamicperformer.remove(Thread.currentThread().getId());
		}
	}
	
	
	
	/**
	 * 释放统一待办数据
	 */
	public static void removeAll(){
		removeAppId();
		removeBusidata();
		removeBuisinessId();
		removeMgrName();
		removeModuleId();
		removeIsTobe();
		removeAction_form();
		removeStatus_code();
		removeBackflow_username();
		removeDynamicperformer();
	}
	
	public static void setBusidata(Map<String,String> map){
		CurrentBusidata.put(Thread.currentThread().getId(), map);
	}

	public static Map<String,String> getBusidata(){
		if(CurrentBusidata.containsKey(Thread.currentThread().getId())){
			Map<String,String> map = CurrentBusidata.get(Thread.currentThread().getId());
			return map == null ? new HashMap<String,String>() : map;
		}else{
			return new HashMap<String,String>();
		}
	}
}
