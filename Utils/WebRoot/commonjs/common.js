/*
 * 取1-6的随机数，1和6都可以延伸
 */
var ch = 1+Math.floor(Math.random()*6);

/**
 * 格式化数字显示方式，也可以用于对数字重定精度。 用法： formatNumber(0,'') formatNumber(12432.21,'#,###')
 * formatNumber(12432.21,'#,###.000#') formatNumber('12432.415','####.0#')
 * formatNumber(12432,'#,###.00')
 */
function formatNumber(number,pattern){
    var str = number.toString();
    var strInt;
    var strFloat;
    var formatInt;
    var formatFloat;
    if(/\./g.test(pattern)){
        formatInt = pattern.split('.')[0];
        formatFloat = pattern.split('.')[1];
    }else{
        formatInt = pattern;
        formatFloat = null;
    }

    if(/\./g.test(str)){
        if(formatFloat!=null){
            var tempFloat = Math.round(parseFloat('0.'+str.split('.')[1])*Math.pow(10,formatFloat.length))/Math.pow(10,formatFloat.length);
            strInt = (Math.floor(number)+Math.floor(tempFloat)).toString();                
            strFloat = /\./g.test(tempFloat.toString())?tempFloat.toString().split('.')[1]:'0';            
        }else{
            strInt = Math.round(number).toString();
            strFloat = '0';
        }
    }else{
        strInt = str;
        strFloat = '0';
    }
    if(formatInt!=null){
        var outputInt = '';
        var zero = formatInt.match(/0*$/)[0].length;
        var comma = null;
        if(/,/g.test(formatInt)){
            comma = formatInt.match(/,[^,]*/)[0].length-1;
        }
        var newReg = new RegExp('(\\d{'+comma+'})','g');

        if(strInt.length<zero){
            outputInt = new Array(zero+1).join('0')+strInt;
            outputInt = outputInt.substr(outputInt.length-zero,zero)
        }else{
            outputInt = strInt;
        }

        var 
        outputInt = outputInt.substr(0,outputInt.length%comma)+outputInt.substring(outputInt.length%comma).replace(newReg,(comma!=null?',':'')+'$1')
        outputInt = outputInt.replace(/^,/,'');

        strInt = outputInt;
    }

    if(formatFloat!=null){
        var outputFloat = '';
        var zero = formatFloat.match(/^0*/)[0].length;

        if(strFloat.length<zero){
            outputFloat = strFloat+new Array(zero+1).join('0');
            // outputFloat = outputFloat.substring(0,formatFloat.length);
            var outputFloat1 = outputFloat.substring(0,zero);
            var outputFloat2 = outputFloat.substring(zero,formatFloat.length);
            outputFloat = outputFloat1+outputFloat2.replace(/0*$/,'');
        }else{
            outputFloat = strFloat.substring(0,formatFloat.length);
        }

        strFloat = outputFloat;
    }else{
        if(pattern!='' || (pattern=='' && strFloat=='0')){
            strFloat = '';
        }
    }
    return strInt+(strFloat==''?'':'.'+strFloat);
}

/*
 * 重置函数，清空text和textarea控制的值，并将combobox等类似下拉框控件的值赋为0
 * arguments_text为存有text或textarea的控件id的数组 arguments_text为存有下拉框等控件id的数组
 * 注意：下拉框控件必须包含值为“0”的选项，建议此选项的显示值为“全部”等词
 */
function reset_common(arguments_text,arguments_combobox){
	for (var i=0; i<arguments_text.length; i++) {
		document.getElementById(arguments_text[i]).value = "";
	}
	for (var j=0; j<arguments_combobox.length; j++)
	{
		document.getElementById(arguments_combobox[j]).value = "0";
	}
}

/*
 * 清除下拉框的除第一个选项以外的所有选项 id为要清除的下拉框的id
 */
function clearCombo(id){
	var object = document.getElementById(id);
	var optionIndex = object.options.length;
	for(;optionIndex>=1;optionIndex--){
		object.options.remove(optionIndex);
	}
}

/*
 * 清除下拉框的所有选项 id为要清除的下拉框的id
 */
function clearCombo_all(id){
	var object = document.getElementById(id);
	var optionIndex = object.options.length;
	for(;optionIndex>=0;optionIndex--){
		object.options.remove(optionIndex);
	}
}

/*
 * 查询函数，根据指定的sql语句和指定字段的排序方法查询数据 ds为数据集 pgaeSize为数据集每页查询的条数 sql为查询的sql语句
 * Fields为要排序的字段数组 Orders为要排序的字段的顺序数组 注意：Fields和Orders的长度必须一致，否则将报错
 */
function findData(ds,pageSize,sql,Fields,Orders){
	for(var i=0;i<Fields.length;i++){
		if(Orders[i]=="逆序"){
			sql += " order by "+Fields[i]+" desc";
		}
	}
	ds.PageSize = pageSize;
	ds.Open(sql);
}

/*
 * 弹出一个不带滚动条的模式对话框 url为对话框的路径 width为对话框的宽度 height为对话框的高度
 */
function openDlg(url,width,height){
	var returnVal = window.showModalDialog(url,window,"dialogWidth="+width+"px;dialogHeight="+height+"px;scroll=no;resizable=no;status=no");
	return returnVal;
}

/*
 * 弹出一个带滚动条的模式对话框 url为对话框的路径 width为对话框的宽度 height为对话框的高度
 */
function openDlg_scroll(url,width,height){
	var returnVal = window.showModalDialog(url,window,"dialogWidth="+width+"px;dialogHeight="+height+"px;scroll=yes;resizable=no;status=no");
	return returnVal;
}

// 只读设置
function app_1_elementReadOnly(element,style,oldValue) { // element 将设置只读的元素
															// style
															// 将在动态创建的输入框上应用的样式
	var id = element.id;
	var value = "";
	if(isSpace(oldValue)) {
		value = element.value;
		var tmp = element.outerHTML.substring(0,7).toUpperCase();
		if (tmp == "<SELECT") // 选择框的取值需要特殊处理
		{
			value = element.options[element.selectedIndex].text;
		}
	} else {
		value = oldValue;
	}
	// 创建输入框
	var myElement = document.createElement("input");
	myElement.setAttribute("id","my_" + id);
	myElement.setAttribute("value",value); // 赋值
// myElement.style.width = element.style.width; //协同办公关心的主要属性 建议通过原元素的属性传递
	myElement.style.width = "100%";
	myElement.readOnly = "true"; // 只读
	myElement.className = style; // 应用样式
	element.style.display = "none"; // 隐藏原元素
	element.parentNode.insertBefore(myElement,element); // 将动态创建的输入框插入到原元素之前
}

// 只读设置
function elementReadOnly(element,style) { // element 将设置只读的元素 style
											// 将在动态创建的输入框上应用的样式
	var id = element.id;
	var value = element.value;
	var tmp = element.outerHTML.substring(0,7).toUpperCase();
	if (tmp == "<SELECT") // 选择框的取值需要特殊处理
	{
		value = element.options[element.selectedIndex].text;
	}
	// 创建输入框
	var myElement = document.createElement("input");
	myElement.setAttribute("id","my_" + id);
	myElement.setAttribute("value",value); // 赋值
// myElement.style.width = element.style.width; //协同办公关心的主要属性 建议通过原元素的属性传递
	myElement.style.width = "100%";
	myElement.readOnly = "true"; // 只读
	myElement.className = style; // 应用样式
	element.style.display = "none"; // 隐藏原元素
	element.parentNode.insertBefore(myElement,element); // 将动态创建的输入框插入到原元素之前
}

/**
 * 执行查询语句
 * 
 * @param:sql-查询sql，index-起始页号，offset-页大小 return:result-二维数据类型
 */
function executeSelect(sql,index,offset){
	var result = new Array();
	var s = SelectSql(sql,index,offset);
	if (s == "<root></root>" || s == "<root>" ) {
		return result;
    	}
	else {
		var x = SetDom(s);
		var rowCount = x.childNodes[0].childNodes.length - 1;	
		var colCount = x.childNodes[0].childNodes[0].childNodes.length;
		for (var i = 0; i < rowCount; i++) {			
			var col = new Array();
			for (var j = 0; j < colCount; j++) {
				col[j] = x.childNodes[0].childNodes[i].childNodes[j].text;
			}
			result[i] = col;
		}
		return result;
	}
}

/**
 * 获取本行政区划代码
 */
function getAreaCode(){
    var userorgid = getSysElement('userorgid');
    var area_CodeV = SqlToField("select org_xzqm from td_sm_organization where org_id='"+userorgid+"'");

    return area_CodeV;
}

/**
 * 获取本系统行政区划代码
 */
function getSysAreaCode(){

	var area_code = SqlToField("select baseinfo_value from ta_dic_baseinfo where baseinfo_key ='CURRENT_SYS_AREACODE'");
	return area_code;
}



/**
 * 获取父行政区划代码
 */
function getParentAreaCode(area_code){
	var parent_areacode = SqlToField("select t.parent_areacode from ta_area t where area_code = '"+area_code+"'");
	return parent_areacode;
}

/**
 * 获取行政区划的类型
 */
function getAreaType(area_code){
	var areaType = SqlToField("select t.area_type from ta_area t where area_code = '"+area_code+"'");
	return areaType;
}

/**
 * 获取行政区划的名称
 */
function getAreaName(area_code){
	var areaName = SqlToField("select t.area_name from ta_dic_area t where area_code = '"+area_code+"'");
	return areaName;
}


/**
 * 格式化系统时间，格式为YYYY-MM-DD hh24:mi:ss return：String
 */
function app_1_formatTime() {
	var sysDate = new Date();
	var year = sysDate.getYear();
	var month = sysDate.getMonth() + 1;
	var date = sysDate.getDate();
	var hour = sysDate.getHours();
	var minute = sysDate.getMinutes();
	var second = sysDate.getSeconds();
	var formatTime = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	return 	formatTime;
}

/**
 * 只显示一级子区域树 param: areacode 区域编码字段名; param: areaname 区域名称字段名； return：String
 */
function chooseTestArea(areaValue,areacode,areaname) {
   var area_code=  areacode;
   var area_name = areaname;
   var url = "../../../ccapp/app_1/dzjctree/tree/areaTestRadioTree.jsp?areaValue="+areaValue+"&area_code="+area_code+"&area_name="+area_name+"&t="+new Date().getTime();
   var rtn = window.showModalDialog(url,window,"center:yes");
}

/**
 * 区域树 param: areacode 区域编码字段名; param: areaname 区域名称字段名； return：String
 */
function chooseArea(areacode,areaname) {
   var area_code=  areacode;
   var area_name = areaname;
   var url = "../../../ccapp/app_1/dzjctree/tree/areaRadioTree.jsp?area_code="+area_code+"&area_name="+area_name+"&t="+new Date().getTime();
   var rtn = window.showModalDialog(url,window,"center:yes");
}

/**
 * 权限区域树 param: areacode 区域编码字段名; param: areaname 区域名称字段名； return：String
 */
function chooseQxArea(areacode,areaname) {
   var area_code=  areacode;
   var area_name = areaname;
   var url = "../../../ccapp/app_1/dzjctree/tree/QxareaRadioTree.jsp?area_code="+area_code+"&area_name="+area_name+"&t="+new Date().getTime();
   var rtn = window.showModalDialog(url,window,"center:yes");
}

/**
 * 上级区域树 param: areacode 区域编码字段名; param: areaname 区域名称字段名； return：String
 */
function chooseParentArea(areacode,areaname) {
   var area_code=  areacode;
   var area_name = areaname;
   var url = "../../../ccapp/app_1/dzjctree/tree/parentAreaRadioTree.jsp?area_code="+area_code+"&area_name="+area_name+"&t="+new Date().getTime();
   var rtn = window.showModalDialog(url,window,"center:yes");
}

/**
 * 机构树 param: orgCodeValue 行政区划编码值; param: orgCode 机构编码字段名； param: orgName
 * 机构名称字段名； return：String
 */
function chooseOrg(orgCodeValue,orgCode,orgName,orgState){
	if(orgCodeValue==null){
	    orgCodeValue="";
	}
	
	  if(typeof(orgState)=="undefined"){
		orgState="";
	}
    var org_codevalue = orgCodeValue;
    // var parentOrgCodeValue = getParentAreaCode(org_codevalue);
	// var rootName=getAreaName(orgCodeValue);
	// var parentOrgCodeValue = orgCodeValue;
    var org_code= orgCode;
    var org_name = orgName;
    
    // var url =
	// "../../../ccapp/app_1/dzjctree/tree/orgRadioTree.jsp?orgCodeValue="+org_codevalue+"&parentOrgCodeValue="+parentOrgCodeValue+"&org_code="+org_code+"&org_name="+org_name+"&t="+new
	// Date().getTime()+"&orgState="+orgState;
	var url = "../../../ccapp/app_1/dzjctree/tree/dzjcOrgAreaTree.jsp?orgCodeValue="+org_codevalue+"&org_code="+org_code+"&org_name="+org_name+"&t="+new Date().getTime()+"&orgState="+orgState;    
    // var url = "../../../ccapp/app_1/dzjctree/tree/dzjcOrgAreaTree.jsp";
	 // alert(url);
	 var rtn = window.showModalDialog(url,window,"center:yes");

}


/**
 * 机构树 param: orgCodeValue 行政区划编码值; param: orgCode 机构编码字段名； param: orgName
 * 机构名称字段名； return：String
 */
function chooseTestOrg(orgCodeValue,orgCode,orgName,orgState){
	  if(typeof(orgState)=="undefined"){
		orgState="";
	}
    var org_codevalue = orgCodeValue;
    // var parentOrgCodeValue = getParentAreaCode(org_codevalue);
	// var rootName=getAreaName(orgCodeValue);
	// var parentOrgCodeValue = orgCodeValue;
    var org_code= orgCode;
    var org_name = orgName;
    // var url =
	// "../../../ccapp/app_1/dzjctree/tree/orgRadioTree.jsp?orgCodeValue="+org_codevalue+"&parentOrgCodeValue="+parentOrgCodeValue+"&org_code="+org_code+"&org_name="+org_name+"&t="+new
	// Date().getTime()+"&orgState="+orgState;
	var url = "../../../ccapp/app_1/dzjctree/tree/dzjcTestOrgAreaTree.jsp?orgCodeValue="+org_codevalue+"&org_code="+org_code+"&org_name="+org_name+"&t="+new Date().getTime()+"&orgState="+orgState;    
   
	 var rtn = window.showModalDialog(url,window,"center:yes");

}


/**
 * 权限机构树 param: orgCodeValue 行政区划编码值; param: orgCode 机构编码字段名； param: orgName
 * 机构名称字段名； return：String
 */
function chooseQxOrg(orgCodeValue,orgCode,orgName,orgState){
	  if(typeof(orgState)=="undefined"){
		orgState="";
	}
    var org_codevalue = orgCodeValue;
    // var parentOrgCodeValue = getParentAreaCode(org_codevalue);
	var rootName=getAreaName(orgCodeValue);
	var parentOrgCodeValue = orgCodeValue;
    var org_code= orgCode;
    var org_name = orgName;
    // var url =
	// "../../../ccapp/app_1/dzjctree/tree/orgRadioTree.jsp?orgCodeValue="+org_codevalue+"&parentOrgCodeValue="+parentOrgCodeValue+"&org_code="+org_code+"&org_name="+org_name+"&t="+new
	// Date().getTime()+"&orgState="+orgState;
	var url = "../../../ccapp/app_1/dzjctree/tree/QxdzjcOrgAreaTree.jsp?orgCodeValue="+org_codevalue+"&parentOrgCodeValue="+parentOrgCodeValue+"&org_code="+org_code+"&org_name="+org_name+"&t="+new Date().getTime()+"&orgState="+orgState+"&rootName="+rootName;    
    // var url = "../../../ccapp/app_1/dzjctree/tree/dzjcOrgAreaTree.jsp";
	 // alert(url);
	 var rtn = window.showModalDialog(url,window,"center:yes");

}

/**
 * 平台机构树 param: orgId 机构编码字段名； param: orgName 机构名称字段名； return：String
 */
function choosePTOrg(orgCode,orgName){
    var org_code= orgCode;
    var org_name = orgName;
    var url = "../../../ccapp/app_1/dzjctree/tree/orgPTRadioTree.jsp?&org_code="+org_code+"&org_name="+org_name+"&t="+new Date().getTime();   
    var rtn = window.showModalDialog(url,window,"center:yes");
	
}

/**
 * 平台人员树 param: userId 机构编码字段名； return：String
 */
function choosePTUser(userId,userName,areaOrgId){
    var user_id = userId;
    var user_name = userName;
    var area_orgId = areaOrgId;
    var url = "../../../ccapp/app_1/dzjctree/tree/userPTRadioTree.jsp?user_id="+user_id+"&user_name="+user_name+"&area_orgId="+area_orgId+"&t="+new Date().getTime(); 

    var rtn = window.showModalDialog(url,window,"center:yes");
	
}

/**
 * 监察类别树 param: superviseTypeCode 监察类别编码字段名; param: superviseTypeName 监察类别名称字段名；
 * return：String
 */
function chooseSuperviseType(superviseTypeCode,superviseTypeName){
   var supervise_type_code= superviseTypeCode;
   var supervise_type_name = superviseTypeName;
   var url = "../../../ccapp/app_1/dzjctree/tree/superviseTypeRadioTree.jsp?supervise_type_code="+supervise_type_code+"&supervise_type_name="+supervise_type_name+"&t="+new Date().getTime();   
   var rtn = window.showModalDialog(url,window,"center:yes");
}

/**
 * 指标分类树 param: area_code 行政区划编码值; param: pointTypeId 指标分类Id的id； param:
 * pointTypeName 指标分类名称的id； param: parentPointTypeId 上级指标分类Id的id return：String
 */
function choosePointType(area_code,pointTypeId,pointTypeName,parentPointTypeId){
    var parentPointTypeIdValue = "0";
    if(!IsSpace(parentPointTypeId)) {
	parentPointTypeIdValue = document.getElementById(parentPointTypeId).value;
	if(IsSpace(parentPointTypeIdValue)) {
	    parentPointTypeIdValue = "0";
	}
    }
    var url = "../../../ccapp/app_1/dzjctree/tree/pointTypeTree.jsp?areaCodeValue="+area_code+"&parentPointTypeIdValue="+parentPointTypeIdValue+"&pointType_id="+pointTypeId+"&pointType_name="+pointTypeName+"&t="+new Date().getTime(); 
    var rtn = window.showModalDialog(url,window,"center:yes");
}

/**
 * 绩效基数树 param: radixId 绩效分类Id的id； param: radixName 绩效分类name的id; return：String
 */
function chooseRadix(radixId,radixName){
    var radix_id=radixId;
    var radix_name=radixName;
    var url = "../../../ccapp/app_1/dzjctree/tree/evalRadixTree.jsp?radix_id="+radixId+"&radix_name="+radix_name+"&t="+new Date().getTime();   
    var rtn = window.showModalDialog(url,window,"center:yes");
}


/**
 * 考核指标树 param: pointId 指标Id的id； param: pointName 指标name的id; return：String
 */
function choosePoint(pointId,pointName,isRadio,area_code){
    var point_id=pointId;
    var point_name=pointName;
   // alert(isRadio);
    // alert(area_code);
    var url = "../../../ccapp/app_1/dzjctree/tree/evalPointTree.jsp?point_id="+pointId+"&point_name="+pointName+"&area_code="+area_code+"&t="+new Date().getTime(); 
    if("true" == isRadio) {
        url = "../../../ccapp/app_1/dzjctree/tree/evalPointRadioTree.jsp?point_id="+pointId+"&point_name="+pointName+"&area_code="+area_code+"&t="+new Date().getTime();
    }
    // alert(url);
    var rtn = window.showModalDialog(url,window,"center:yes");
}

/**
 * 考核项树 param: itemId 考核项Id的id； param: item_name 考核项name的id; return：String
 */
function chooseItem(itemId,itemNmae){
    var item_id=itemId;
    var item_name=itemNmae;
    var url = "../../../ccapp/app_1/dzjctree/tree/evalItemTree.jsp?item_id="+item_id+"&item_name="+item_name+"&t="+new Date().getTime();   
    var rtn = window.showModalDialog(url,window,"center:yes");
}

/**
 * 考核项考核指标树 param：id 接收id值的控件id param：name 接收name值的控件id return String
 */
function chooseFormulaItem(id,name) {
	var url = "../../../ccapp/app_1/dzjctree/tree/evalItemPointTree.jsp?id="+id+"&name="+name+"&t="+new Date().getTime();
	var rtn = window.showModalDialog(url,window,"center:yes");
}

/**
 * 用户树 param: userName 用户姓名的id； param: userName 用户id； param: mobile 手机号码的id;
 * return：String
 */
function chooseUser(userName,userId,mobile){
    var url = "../../../ccapp/app_1/dzjctree/tree/userRadixTree.jsp?user_name="+userName+"&user_id="+userId+"&mobile="+mobile+"&t="+new Date().getTime();   
    var rtn = window.showModalDialog(url,window,"center:yes");
}

/**
 * 本机构下的用户树 param: orgCode 机构码； return：String
 */
function chooseOrgUser(orgCode,userName,userId,mobile){
    var url = "../../../ccapp/app_1/dzjctree/tree/userRadixTree.jsp?orgCode="+orgCode+"&user_name="+userName+"&user_id="+userId+"&mobile="+mobile+"&t="+new Date().getTime();   
    var rtn = window.showModalDialog(url,window,"center:yes");
}

/**
 * 获取本系统行政区划代码
 */
function getSysAreaCode(){
	var area_code = SqlToField("select baseinfo_value from ta_dic_baseinfo where baseinfo_key ='CURRENT_SYS_AREACODE'");
	return area_code;
}

/**
 * 获取行政区划的类型
 */
function getAreaType(area_code){

	var areaType = SqlToField("select t.area_type from ta_area t where area_code = '"+area_code+"'");
	return areaType;
}


/**
 * 获取图形报表默认显示条数
 */
function getDefaultChartPageCount(){

	var chartPageCount = SqlToField("select baseinfo_value from ta_dic_baseinfo where baseinfo_key ='DEFAULT_CHART_PAGE_COUNT'");
	return chartPageCount;
}

/**
 * 获取图形报表最大显示条数
 */
function getMaxChartPageCount(){

	var chartPageCount = SqlToField("select baseinfo_value from ta_dic_baseinfo where baseinfo_key ='MAX_CHART_PAGE_COUNT'");
	return chartPageCount;
}



/**
 * 判断区域是否是省本级 add by yin.liu 20110415
 */
function isProvinceCurrentLevel(area_org_code){
		area_org_code=area_org_code.substr(0,12);
	var c = SqlToField("select count(*) n from v_td_sm_organization where org_this_level='1' and substr(org_code,1,12)='"+area_org_code+"'");
	if(c>0){
	    return "true";
	}else{
	    return "false";
	}
}


/**
 * 判断区域是否是市本级 add by yin.liu 20110414
 */
function isCityCurrentLevel(area_code){
	area_code=area_code.substr(0,12);

	var c = SqlToField("select count(*) n from v_td_sm_organization where org_this_level='2' and substr(org_code,1,12) = '"+area_code+"'");
	if(c>0){
	    return "true";
	}else{
	    return "false";
	}
}

/**
 * 获取(12位)区域名称 add by yin.liu 20110415
 */
function getOrgAreaName(area_code){
      return getOrgName(area_code);
	// var area_name = SqlToField("select org_name from v_td_sm_organization
	// where org_level in(1,2,3) and substr(org_code,1,12)= '"+area_code+"'");

	// return area_name;
}

/**
 * 获取(24位)机构名称 add by yin.liu 20110418
 */
function getOrgName(area_code){
   if(IsSpace(area_code)){
      return;
   }
    if(area_code.length==12){
       // return getOrgAreaName(area_code);
       area_code += "000000000000";
    }
	var area_name = SqlToField("select org_name from v_td_sm_organization where org_code= '"+area_code+"'");

	return area_name;
}


 
 /**
	 * 获取数据库服务时间 add by yin.liu 20110415
	 */
function getDateOfData(){

	var sql_date="select to_char(sysdate,'yyyy-MM-dd') from dual ";
    var date = SqlToField(sql_date);   
	return date;
}


 /**
	 * 根据机构编码获得其级别 add by yin.liu 20110415
	 */
function getOrgLevelOfOrgCode(orgCode){

    if(orgCode.length==12){
       orgCode=orgCode+"000000000000";
    }
	var sql="select org_level from v_td_sm_organization where org_code='"+orgCode+"' ";
    var level = SqlToField(sql);   
	return level;
}

 /**
	 * 根据机构编码获得其省级编码(24位) add by yin.liu 20110415
	 */
function getProvinceCodeOfOrgCode(orgCode){

    if(orgCode.length==12){
       orgCode=orgCode+"000000000000";
    }
	var sql="select province_code from v_td_sm_organization where org_code='"+orgCode+"' ";

    var level = SqlToField(sql);   
    if(level=="000000000000"){return "";}   
	return level+"000000000000";
}

 /**
	 * 根据机构编码获得其市级编码(24位) add by yin.liu 20110415
	 */
function getCityCodeOfOrgCode(orgCode){
    if(orgCode.length==12){
       orgCode=orgCode+"000000000000";
    }
	var sql="select city_code from v_td_sm_organization where org_code='"+orgCode+"' ";
    var level = SqlToField(sql);   
    if(level=="000000000000"){return "";}   
	return level+"000000000000";
}


/**
 * 根据机构编码获得其乡村街道级编码(24位) add by yin.liu 20110415
 */
function getCountyCodeOfOrgCode(orgCode){
    if(orgCode.length==12){
       orgCode=orgCode+"000000000000";
    }
	var sql="select county_code from v_td_sm_organization where org_code='"+orgCode+"' ";
    var level = SqlToField(sql);   
    if(level=="000000000000"){return "";}   
	return level+"000000000000";
}

/**
 * 根据机构编码获得其乡村街道级编码(24位) add by yin.liu 20110415
 */
function getStreetCodeOfOrgCode(orgCode){
    if(orgCode.length==12){
       orgCode=orgCode+"000000000000";
    }
	var sql="select street_code from v_td_sm_organization where org_code='"+orgCode+"' ";
    var level = SqlToField(sql);
    if(level=="000000000000"){return "";}   
	return level+"000000000000";
}

/**
 * 格式化数字显示方式，也可以用于对数字重定精度。 用法： formatNumber(0,'') formatNumber(12432.21,'#,###')
 * formatNumber(12432.21,'#,###.000#') formatNumber('12432.415','####.0#')
 * formatNumber(12432,'#,###.00')
 */
function formatNumber(number,pattern){
   var str = number.toString();
   var strInt;
   var strFloat;
   var formatInt;
   var formatFloat;
   if(/\./g.test(pattern)){
       formatInt = pattern.split('.')[0];
       formatFloat = pattern.split('.')[1];
   }else{
       formatInt = pattern;
       formatFloat = null;
   }

   if(/\./g.test(str)){
       if(formatFloat!=null){
           var tempFloat = Math.round(parseFloat('0.'+str.split('.')[1])*Math.pow(10,formatFloat.length))/Math.pow(10,formatFloat.length);
           strInt = (Math.floor(number)+Math.floor(tempFloat)).toString();                
           strFloat = /\./g.test(tempFloat.toString())?tempFloat.toString().split('.')[1]:'0';            
       }else{
           strInt = Math.round(number).toString();
           strFloat = '0';
       }
   }else{
       strInt = str;
       strFloat = '0';
   }
   if(formatInt!=null){
       var outputInt = '';
       var zero = formatInt.match(/0*$/)[0].length;
       var comma = null;
       if(/,/g.test(formatInt)){
           comma = formatInt.match(/,[^,]*/)[0].length-1;
       }
       var newReg = new RegExp('(\\d{'+comma+'})','g');

       if(strInt.length<zero){
           outputInt = new Array(zero+1).join('0')+strInt;
           outputInt = outputInt.substr(outputInt.length-zero,zero)
       }else{
           outputInt = strInt;
       }

       var 
       outputInt = outputInt.substr(0,outputInt.length%comma)+outputInt.substring(outputInt.length%comma).replace(newReg,(comma!=null?',':'')+'$1')
       outputInt = outputInt.replace(/^,/,'');

       strInt = outputInt;
   }

   if(formatFloat!=null){
       var outputFloat = '';
       var zero = formatFloat.match(/^0*/)[0].length;

       if(strFloat.length<zero){
           outputFloat = strFloat+new Array(zero+1).join('0');
           // outputFloat = outputFloat.substring(0,formatFloat.length);
           var outputFloat1 = outputFloat.substring(0,zero);
           var outputFloat2 = outputFloat.substring(zero,formatFloat.length);
           outputFloat = outputFloat1+outputFloat2.replace(/0*$/,'');
       }else{
           outputFloat = strFloat.substring(0,formatFloat.length);
       }

       strFloat = outputFloat;
   }else{
       if(pattern!='' || (pattern=='' && strFloat=='0')){
           strFloat = '';
       }
   }
   return strInt+(strFloat==''?'':'.'+strFloat);
}

/*
 * 重置函数，清空text和textarea控制的值，并将combobox等类似下拉框控件的值赋为0
 * arguments_text为存有text或textarea的控件id的数组 arguments_text为存有下拉框等控件id的数组
 * 注意：下拉框控件必须包含值为“0”的选项，建议此选项的显示值为“全部”等词
 */
function reset_common(arguments_text,arguments_combobox){
	for (var i=0; i<arguments_text.length; i++) {
		document.getElementById(arguments_text[i]).value = "";
	}
	for (var j=0; j<arguments_combobox.length; j++)
	{
		document.getElementById(arguments_combobox[j]).value = "0";
	}
}

/*
 * 清除下拉框的除第一个选项以外的所有选项 id为要清除的下拉框的id
 */
function clearCombo(id){
	var object = document.getElementById(id);
	var optionIndex = object.options.length;
	for(;optionIndex>=1;optionIndex--){
		object.options.remove(optionIndex);
	}
}

/*
 * 清除下拉框的所有选项 id为要清除的下拉框的id
 */
function clearCombo_all(id){
	var object = document.getElementById(id);
	var optionIndex = object.options.length;
	for(;optionIndex>=0;optionIndex--){
		object.options.remove(optionIndex);
	}
}

/*
 * 查询函数，根据指定的sql语句和指定字段的排序方法查询数据 ds为数据集 pgaeSize为数据集每页查询的条数 sql为查询的sql语句
 * Fields为要排序的字段数组 Orders为要排序的字段的顺序数组 注意：Fields和Orders的长度必须一致，否则将报错
 */
function findData(ds,pageSize,sql,Fields,Orders){
	for(var i=0;i<Fields.length;i++){
		if(Orders[i]=="逆序"){
			sql += " order by "+Fields[i]+" desc";
		}
	}
	ds.PageSize = pageSize;
	ds.Open(sql);
}

/*
 * 弹出一个不带滚动条的模式对话框 url为对话框的路径 width为对话框的宽度 height为对话框的高度
 */
function openDlg(url,width,height){
	var returnVal = window.showModalDialog(url,window,"dialogWidth="+width+"px;dialogHeight="+height+"px;scroll=no;resizable=no;status=no");
	return returnVal;
}

/*
 * 弹出一个带滚动条的模式对话框 url为对话框的路径 width为对话框的宽度 height为对话框的高度
 */
function openDlg_scroll(url,width,height){
	var returnVal = window.showModalDialog(url,window,"dialogWidth="+width+"px;dialogHeight="+height+"px;scroll=yes;resizable=no;status=no");
	return returnVal;
}

// 只读设置
function elementReadOnly(element,style) { // element 将设置只读的元素 style
											// 将在动态创建的输入框上应用的样式
	var id = element.id;
	var value = element.value;
	var tmp = element.outerHTML.substring(0,7).toUpperCase();
	if (tmp == "<SELECT") // 选择框的取值需要特殊处理
	{
		value = element.options[element.selectedIndex].text;
	}
	// 创建输入框
	var myElement = document.createElement("input");
	myElement.setAttribute("id","my_" + id);
	myElement.setAttribute("value",value); // 赋值
	myElement.style.width = element.style.width; // 协同办公关心的主要属性 建议通过原元素的属性传递
	myElement.readOnly = "true"; // 只读
	myElement.className = style; // 应用样式
	element.style.display = "none"; // 隐藏原元素
	element.parentNode.insertBefore(myElement,element); // 将动态创建的输入框插入到原元素之前
}

// js引入js
document.write("<script language='javascript' src='../../../ccapp/app_5/js/prototype.js'></script>");

function trimString(str){
    str = this!=window?this:str;
    return str.replace(/^\s+/g,'').replace(/\s+$/g,'');
}
function trim(checkedObject) {
	return checkedObject.replace(/(^\s+)|\s+$/g, "");
}

String.prototype.trim = trimString;

function NumberToMoneyStr(aNumber,aFixLen,aDigital) // return 1000500 to
													// 1,000,500
{
	var lblankStr = "                                               ";
	var lstr = "" + aNumber;
	var lFixLen = aFixLen == null ? 1 : ( aFixLen > 20 ? 20 : aFixLen)
	var ldigital = aDigital == null ? 3 : aDigital	

	var lbase = lstr.length % ldigital;
	var ldstr = (lbase >0) ? lstr.substr( 0, lbase) : "";	
	var lneed = true;
	if(lbase == 0 || (lbase ==1 && ldstr == "-"))
	{
		lneed = false;
	}
	
	for(var i = lbase; i < lstr.length; i +=ldigital)
	{
		
		ldstr += (lneed ? "," : "") + lstr.substr(i,ldigital);
		lneed = true;
	}
	
	if (ldstr.length < lFixLen)
	{
		ldstr = lblankStr.substr(0,lFixLen - ldstr.length) + ldstr;
	}
	return ldstr;	 
}
function MoneyStrToNumber(aStr,aDigital) // 1,000,500 to 1000500
{
	if(aStr == null || aStr == "") return 0;
	var lstr = aStr.trim()	
	if(lstr == "" ) return 0;
	lstr = aStr.replace(/,/g,'')
	
	var lint = parseInt(lstr);
	if(isNaN(lint)) lint = -1;
	return lint;
}


function DateToString(aDate) // return aStr like 2001-03-05
{

	if(aDate == null || aDate.constructor != Date) return "";
// aDate instanceof Date
	try{
		var ys = aDate.getFullYear();
		var ms = aDate.getMonth() + 1;
		var ds = aDate.getDate();
		
		var s = "" + ys + "-";
		
		if(ms<10) s += "0";
		s += ms +"-"
		
		if(ds<10) s +="0";
		s +=ds
		
		return s
	
	}catch(e){
		return "";
	}

}


function StringToDate(aStr) // aStr like 2001-03-05, return a DateObj
{
	var y = aStr.indexOf("-")
	if( y <=0 ) return null;
	var ys = parseInt(aStr.substr(0,y), 10);
	
	var m =  aStr.indexOf("-", y + 1)
	if( m <= y + 1 ) return null;
	var ms = parseInt(aStr.substr(y+1, m - y -1), 10);
	
	var ds = parseInt(aStr.substr(m + 1) , 10);

	if( isNaN(ys) || isNaN(ms) || isNaN(ds) ) 	return null;

	if( ys < 1970 || ys > 9999 ||
	    ms < 1    || ms > 12   ||
	    ds < 1    || ds > 31      ) return null	
	    
	if( ds > 28 ){
		if(ds == 31 )
		{
			if( ms == 2 || ms == 4 || ms ==6 || ms == 9 || ms == 11 ) return null;
		}
		else if( ds == 30 ){
			if( ms == 2 ) return null;
		}else {
			if( ms == 2 ){
				if( ys % 4 == 0 ) {
					if( ys % 100 == 0 && ys % 400 != 0){
						return null;
					}
					// yes...
				}else{
					return null;
				}
				
			}
		}
		
	}
	
	
	return new Date(ys, ms - 1, ds);
	
}
function StrToDateStr(aStr) // return "" or like 2001-03-05
{
	if(aStr == null || aStr == "") return "";
	
	var y = aStr.indexOf("-")
	if( y <=0 ) return "";
	var ys = parseInt(aStr.substr(0,y), 10);
	
	var m =  aStr.indexOf("-", y + 1)
	if( m <= y + 1 ) return "";
	var ms = parseInt(aStr.substr( y + 1, m - y - 1), 10);
	
	var ds = parseInt(aStr.substr(m + 1), 10);

	if( isNaN(ys) || isNaN(ms) || isNaN(ds) ) 	return "";

	if( ys < 1970 || ys > 9999 ||
	    ms < 1    || ms > 12   ||
	    ds < 1    || ds > 31      ) return ""	
	    
	if( ds > 28 ){
		if(ds == 31 )
		{
			if( ms == 2 || ms == 4 || ms ==6 || ms == 9 || ms == 11 ) return "";
		}
		else if( ds == 30 ){
			if( ms == 2 ) return "";
		}else {
			if( ms == 2 ){
				if( ys % 4 == 0 ) {
					if( ys % 100 == 0 && ys % 400 != 0){
						return "";
					}
					// yes...
				}else{
					return "";
				}
				
			}
		}
		
	}
	
	var s = "" + ys + "-";
	if(ms<10) s += "0";
	s +=ms +"-"
	if(ds<10) s +="0";
	s +=ds
	// alert(s);
	return s
	 
}

function StringIsDate(aStr) // is aStr like 2001-03-05, return true of false;
{
	if(aStr == null || aStr == "") return false;
	
	var y = aStr.indexOf("-")
	if( y <=0 ) return false;
	var ys = parseInt(aStr.substr(0,y), 10);
	
	var m =  aStr.indexOf("-", y + 1)
	if( m <= y + 1 ) return false;
	var ms = parseInt(aStr.substr(y+1, m - y - 1), 10);
	
	var ds = parseInt(aStr.substr(m + 1), 10);

	if( isNaN(ys) || isNaN(ms) || isNaN(ds) ) 	return false;

	if( ys < 1970 || ys > 9999 ||
	    ms < 1    || ms > 12   ||
	    ds < 1    || ds > 31   ) 
	    return false	
	    
	if( ds > 28 ){
		if(ds == 31 )
		{
			if( ms == 2 || ms == 4 || ms ==6 || ms == 9 || ms == 11 ) return false;
		}
		else if( ds == 30 ){
			if( ms == 2 ) return false;
		}else {
			if( ms == 2 ){
				if( ys % 4 == 0 ) {
					if( ys % 100 == 0 && ys % 400 != 0){
						return false;
					}
					// yes...
				}else{
					return false;
				}
				
			}
		}
		
	}
	
	return true;
	 
}
function link3(url,w,h)
{
	window.open(url, '', 'toolbar=0, scrollbars=0, resizable=1, width='+w+', height='+h+', top='+(screen.availHeight/2-h/2)+', left='+(screen.availWidth/2-w/2)+' ');
}

function link2(url,w,h)
{
	window.open(url, '', 'toolbar=0, scrollbars=1, resizable=1, width='+w+', height='+h+', top='+(screen.availHeight/2-h/2)+', left='+(screen.availWidth/2-w/2)+' ');
}

function link(url,w,h)
{
	window.open(url, '', 'toolbar=0, scrollbars=0, resizable=0, width='+w+', height='+h+', top='+(screen.availHeight/2-h/2)+', left='+(screen.availWidth/2-w/2)+' ');
}


/* 控制TAB页的显示或隐藏：以TAB页名称为基准 */
function hideOrShowTabByName(name, type) {
	  var realStyle = "";
	  if(IsSpace(type) || (type.toUpperCase()=="hide".toUpperCase())) {
		    realStyle = "none";
	  }
	  var objs = document.getElementsByTagName("H2");
	  for(var i=0; i<objs.length; i++) {
		    if(trim(objs[i].innerText) == trim(name)) {
			      objs[i].style.display = realStyle;
		    }
	  }
}

/*只显示给定名字的TAB页*/
function onlyShowTabByName(name) {
	  var objs=document.getElementsByTagName("H2");
	  for(i=0;i<objs.length;i++) {
		    if(trim(objs[i].innerText)!=trim(name)) {
			      objs[i].style.display="none";
		    }else {
			      objs[i].style.display="";
		    }
	  }
}

/*显示所有TAB页 除参数中指定的以外*/
function showAllTabExcept(name) {
	var objs=document.getElementsByTagName("H2");
	for(i=0;i<objs.length;i++) {

                  if(trim(objs[i].innerText)==trim(name)) {
			      objs[i].style.display="none";
		    }else {
			      objs[i].style.display="";
		    }
	}
}

/*显示所有TAB页*/
function showAllTab() {
	var objs=document.getElementsByTagName("H2");
	for(i=0;i<objs.length;i++) {
		objs[i].style.display="";
	}
}

/*隐藏所有TAB页*/
function hideAllTab() {
	var objs=document.getElementsByTagName("H2");
	for(i=0;i<objs.length;i++) {
		objs[i].style.display="none";
	}
}




/*根据页面元素ID名隐藏页面元素,传入元素ID名数组*/
function hideElementsById(elements){
   var element;
   for(var i=0;i<elements.length;i++){
   	  try{
         element=document.getElementById(elements[i]);
         element.style.display="none";
      }catch(e){}
  }
}

/*根据页面元素ID名显示页面元素,传入元素ID名数组*/
function showElementsById(elements){
    var element;
    for(var i=0;i<elements.length;i++){
    	 try{
          element = document.getElementById(elements[i]);
          element.style.display="";
       }catch(e){}
    }
}


/*得到选中的单选框值*/
function getRadioValue(RadioName) {
	RadioName="RG"+RadioName;
	var radioItem=document.getElementsByName(RadioName);
	for(i=0;i<radioItem.length;i++) {
		if(radioItem[i].checked) {
			return radioItem[i].value;
		}
	}
}


/*得到项目基本编号 EC_ID*/
function getEcId(){
    var url=parent.parent.document.URL;
    var purl=url.split("?");
    var vurl= purl[1].split("&");
    var vec_id=vurl[vurl.length-1]
    return vec_id.split("=")[1];
}


/* 通过TABLEID去控制里面HTML元素的访问范围：分只读、可编辑、隐藏三种 */
function setTableVisitStatus(tableId,visitType){
    var tableInfo = document.getElementById(tableId);
    var arrInput = tableInfo.getElementsByTagName("input");
    for(var i=0; i<arrInput.length; i++){
        var type = arrInput[i].getAttribute("controltype");
        if((type!=null) && (type!="undefind")){
            if(type.toLowerCase()=="text"){
                if(visitType.toLowerCase() == "view"){
                    arrInput[i].readOnly = true;
                }else if(visitType.toLowerCase() == "edit"){
                    arrInput[i].readOnly = false;
                }else{
                	  arrInput[i].style.display = "none";
                }
            }
        }
    }
    var arrComb = tableInfo.getElementsByTagName("select");
    for(var i=0; i<arrComb.length; i++){
        if(visitType.toLowerCase() == "view"){
            arrComb[i].disabled = true;
        }else if(visitType.toLowerCase() == "edit"){
            arrComb[i].disabled = false;
        }else{
            arrComb[i].style.display = "none";
        }
    }
}


/* 清空指定TABLE的所有输入字段 */
function setTableElementClear(tableId){
	  var tableInfo = document.getElementById(tableId);
    var arrInput = tableInfo.getElementsByTagName("input");
    for(var i=0; i<arrInput.length; i++){
        var type = arrInput[i].getAttribute("controltype");
        if((type!=null) && (type!="undefind")){
            if(type.toLowerCase()=="text"){
                arrInput[i].value = "";
                arrInput[i].readOnly = false;
            }
        }
    }
    var arrComb = tableInfo.getElementsByTagName("select");
    for(var i=0; i<arrComb.length; i++){
    	  arrComb[i].value = "";
        arrComb[i].disabled = false;
    }
}

/* 打开审批意见详细表单页面 */
function toShowTaskList(taskflow_id) {
	  var para = null;
         //para = new Array(taskflow_id,"0");		
	 //  DjOpen(1472,para,'展现',"有模式窗口","直接","审批意见详细");  
       var url="1472.jsp?cc_form_instanceid="+parent.cc_form_instanceid+
               "&taskflow_id="+taskflow_id;   
       
       window.showModalDialog(url,para,"dialogWidth:630px;");
	 
}

/* 给下拉选单创建一个值是空的选项 */
function buildEmptyOption(comboxId){
    var first = document.createElement("option");
    var text = document.createTextNode("-请选择-");
    first.appendChild(text);
    document.getElementById(comboxId).appendChild(first);
}


/*得到数据库时间*/
function getSysDate(){
	var sql="select to_char(sysdate,'yyyy-mm-dd') from dual";
	return SqlToField(sql);
}

/*得到数据库时间*/
function getSysDateTime(){
	var sql="select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual";
	return SqlToField(sql);
}
   

/* 打印报表 */
function printReport(raqId){
	  var rptArgs = getRptData();
	  var rptUrl = creator_getContextPath() + "/report/showreport.jsp?raq=" + raqId + rptArgs+"&needDecode=true";
    window.open(rptUrl);
}

/* 返回报表参数字符串 */
function getRptData(){
	  var args = "";
	  return args;
}

/* 关于流程中 word 控件使用
 * word 初始化参数
 * status 【word 状态】  
 * tempDoc【word 模板ID】
 */
function loadWordByStatusAndTemplete(status,tempDoc){

   showModalDialog(getOpenUrlByDjid("20081028092118156028")+ "&status="+status+"&tempDoc="+tempDoc+"&cc_form_instanceid="+creator_getSession('cc_form_instanceid'),window,"status:no;resizable:yes;;unadorne:yes;scroll:no");  
}

/* 在恢复页面中隐藏"退窗"和"下一步"按钮
* author 王安建
*/
function hiddenButtonFromResume(shenpi_insid)
{
    var sql="select status from app_5.ta_sp_instance where shenpi_insid='"+shenpi_insid+"'";
   var status=SqlToField(sql);
   
   if(status.trim()=="业务挂起")
   {
       document.getElementById("btnNextStep").style.display="none";
       document.getElementById("btnReject").style.display="none";
   }
}

//设置收费
function setChargetype(txt_type_id){
	try{ 
	  var id = (txt_type_id)?txt_type_id:"textxmlx"
	  var types = '不收费,政府基金,统筹基金,行政性收费,事业性收费,税金,服务性收费,服务项目预收费,服务项目补收费';
	  $(id).value = types.split(",")[$(id).value];
	}catch(e){
	}
}


/***************************审批系统公用js**********************************/

/**
*** add by 张显
***公用的js 与具体业务无关操作
***用于处理字符编码 转换等操作
***
***2008 - 12 - 10
***
***/

//将字符串转化成数据库能识别的字符串
// add by zhangxian 2008 -12-10
function escape2Sql( text ){
   return text.replace(/'/g,"''");
}

//将传递到url地址中中文和字符格式化 add by zhangxian 2008 -12-10
function getEscapeStr4Url(test){
    return base64encode(test);
}


/**************************************loading************************************/
/*************************** BEGIN ***************************/
/**
 *
 * 首先，调用 createGcjDhccFullScreen() 生成 fullScreen(全屏覆盖层)
 * 然后，
 * 调用 showGcjDhccFullScreen() 显示 fullScreen
 * 调用 hideGcjDhccFullScreen() 隐藏 fullScreen
 * 
 * ********** 变量说明 **********
 * path : 工程路径（该变量需在页面定义或赋值） var path = "<%=path%>";
 * gcjDhccFullScreenLoadingText : 设置 fullScreen 层的加载时显示的文字（可以是HTML）
 * gcjDhccFullScreenLoadingTextAlign : 设置 fullScreen 层的加载时显示的文字位于加载图片的位置：图片上方 up，下 down，左 left，右 right
 * gcjDhccFullScreenLoadingImgHAlign : 设置 fullScreen 层的加载图片的水平位置：左 left，中 center，右 right
 * gcjDhccFullScreenLoadingImgVAlign : 设置 fullScreen 层的加载图片的竖直位置：上 top，中 middle，下 bottom
 * gcjDhccFullScreenBKImgPath : 设置 fullScreen 层的背景图片路径
 * gcjDhccFullScreenLoadingImgPath : 设置 fullScreen 层的加载图片路径
 * gcjDhccFullScreenDocumentObj : 设置 fullScreen 层在那个页面显示
 *     本页面：var gcjDhccFullScreenDocumentObj = window.document;
 *     父页面：var gcjDhccFullScreenDocumentObj = window.parent.document;
 *     父页面的父页面：var gcjDhccFullScreenDocumentObj = window.parent.parent.document;
 *     依此类推。
 * 
 * 程序缺陷：gcjDhccFullScreenDocumentObj 现在只能设置在本页面，设置在父页面时无法隐藏 fullScreen（ hideGcjDhccFullScreen() 隐藏时会报错 ）
 * 
 */

var gcjDhccFullScreenLoadingText = '<font color="#ff0000">加载中 。。。</font>';
var gcjDhccFullScreenLoadingTextAlign = "up";
var gcjDhccFullScreenLoadingImgPath = "../../../ccapp/app_5/images/ajax-loader.gif";
var gcjDhccFullScreenLoadingImgHAlign = "center";
var gcjDhccFullScreenLoadingImgVAlign = "middle";
var gcjDhccFullScreenBKImgPath = "";
var gcjDhccFullScreenDocumentObj = window.document;

/**
 * 生成 fullScreen(全屏覆盖层)
 * 
 * loadingText = gcjDhccFullScreenLoadingText
 * loadingTextAlign = gcjDhccFullScreenLoadingTextAlign
 * loadingImgPath = gcjDhccFullScreenLoadingImgPath
 * imgHAlign = gcjDhccFullScreenLoadingImgHAlign
 * imgVAlign = gcjDhccFullScreenLoadingImgVAlign
 * bKImgPath = gcjDhccFullScreenBKImgPath
 * documentObj = gcjDhccFullScreenDocumentObj
 * 
 * 不需要改变的参数置空（值为 null ，不是字符串 "null" ）
 * 
 */
function createGcjDhccFullScreen(loadingText, loadingTextAlign, loadingImgPath, imgHAlign, imgVAlign, bKImgPath, documentObj)
{
	//初始化变量
	if(loadingText == null){ loadingText = gcjDhccFullScreenLoadingText; }
	if(loadingTextAlign == null){ loadingTextAlign = gcjDhccFullScreenLoadingTextAlign; }
	if(loadingImgPath == null){ loadingImgPath = gcjDhccFullScreenLoadingImgPath; }
	if(imgHAlign == null){ imgHAlign = gcjDhccFullScreenLoadingImgHAlign; }
	if(imgVAlign == null){ imgVAlign = gcjDhccFullScreenLoadingImgVAlign; }
	//if(bKImgPath == null){ bKImgPath = gcjDhccFullScreenBKImgPath; }
	if(documentObj == null){ documentObj = gcjDhccFullScreenDocumentObj; }
	else{gcjDhccFullScreenDocumentObj = documentObj;}
	
	//判断 fullScreen 是否存在
	try{
		if(documentObj.getElementById("gcjDhccFullScreen")){ return null; }
	}
	catch(e){ }
	//开始生成 fullScreen
	var divObj = documentObj.createElement("div");
	divObj.setAttribute("id", "gcjDhccFullScreen");
	divObj.style.display = "none";  //不可见
	divObj.style.position = "absolute";  //浮动
	divObj.style.top = 0;
	divObj.style.left = 0;
	divObj.style.width = documentObj.body.offsetWidth;
	divObj.style.height = documentObj.body.offsetHeight;
	divObj.style.background="#FFF";
	if(bKImgPath != null)
	{
		divObj.style.backgroundImage = "url("+ bKImgPath +")";
	}
	//divObj.style.backgroundImage = "url("+ bKImgPath +")";
	divObj.style.zIndex = 999;
	
	var loadingHtml = '<table width="100%" height="100%" align="'+ imgHAlign +'" valign="'+ imgVAlign +'" border="0" cellpadding="0" cellspacing="0">';
	loadingHtml += '<tr><td ondblclick="hideGcjDhccFullScreen();" align="'+ imgHAlign +'" valign="'+ imgVAlign +'">';
	//图片上方 up，下 down，左 left，右 right
	if(loadingTextAlign == "up"){
		loadingHtml += '<div align="'+ imgHAlign +'">'+ loadingText +'<div>';
		loadingHtml += '<img src="'+ loadingImgPath +'" />';
	}
	else if(loadingTextAlign == "down"){
		loadingHtml += '<img src="'+ loadingImgPath +'" />';
		loadingHtml += '<div align="'+ imgHAlign +'">'+ loadingText +'<div>';
	}
	else if(loadingTextAlign == "left"){
		loadingHtml += loadingText +'&nbsp;&nbsp;';
		loadingHtml += '<img src="'+ loadingImgPath +'" />';
	}
	else if(loadingTextAlign == "right"){
		loadingHtml += '<img src="'+ loadingImgPath +'" />';
		loadingHtml += '&nbsp;&nbsp;'+ loadingText;
	}
	loadingHtml += '</td></tr>';
	loadingHtml += '</table>';
	
	divObj.innerHTML = loadingHtml;
	
	//开始生成 iframe
	var iframeObj = documentObj.createElement("iframe");
	iframeObj.setAttribute("id", "gcjDhccFullScreenIframe");
	iframeObj.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)";
	
	iframeObj.style.display = "none";  //不可见
	iframeObj.style.position = "absolute";  //浮动
	iframeObj.style.top = 0;
	iframeObj.style.left = 0;
	iframeObj.style.width = documentObj.body.offsetWidth;
	iframeObj.style.height = documentObj.body.offsetHeight;
	iframeObj.style.zIndex = 998;
	
	// body 里加入 iframe
	documentObj.body.appendChild(iframeObj);
	
	// body 里加入 div
	documentObj.body.appendChild(divObj);
}
//显示 fullScreen
function showGcjDhccFullScreen()
{
	try{
		gcjDhccFullScreenDocumentObj.getElementById("gcjDhccFullScreen").style.display = "block";
		gcjDhccFullScreenDocumentObj.getElementById("gcjDhccFullScreenIframe").style.display = "block";
	}
	catch(e){ window.alert('showGcjDhccFullScreen'); }
}
//隐藏 fullScreen
function hideGcjDhccFullScreen()
{
	try{
		gcjDhccFullScreenDocumentObj.getElementById("gcjDhccFullScreen").style.display = "none";
		gcjDhccFullScreenDocumentObj.getElementById("gcjDhccFullScreenIframe").style.display = "none";
	}
	catch(e){ window.alert('hideGcjDhccFullScreen'); }
}
/**************************** END ****************************/


function showLoading()
{
 	createGcjDhccFullScreen('<font style="font-size:15px; color:#ff0000;">加载数据中 。。。</font>', 'right', null, null,null, null, null);
	showGcjDhccFullScreen();
}

function hideLoading()
{
	hideGcjDhccFullScreen();
}

/*取当前日期*/
function getCrntDateFmt1() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	month = month  + "";
	day = day + "";

	if(month.length<2) {
		month = "0"+month;
	}

	if(day.length<2) {
		day = "0" + day;
	}

	return (year+month+day);
}

/*格式化字符串*/
function fmtAddZero(orgStr,desLen) {
	while(orgStr.length < desLen) {
		orgStr = "0" + orgStr;
	}

	return orgStr;
}

/*去掉前面无用的'0'，比如'008'要修改成'8'*/
function fmtRemoveZero(orgStr) {
	var desStr = orgStr;

	while(desStr.length > 0) {
		var c = desStr.charAt(0);

		if(c == '0') {
			desStr = desStr.substr(1);
		}
		else {
			break;
		}
	}

	return desStr;
}

/*流程办结,传入参数：0无证无意见，1无证有意见，2有证无意见，3有证有意见*/
function finishMyWorkFlow(type){
    switch(type){
	  case 1: finishVersionOne();
			  break;
	  case 2: finishVersionTwo();
			  break;
	  
	  case 3: finishVersionThree();
	          break;
	  
	  case 4: finishVersionFore();
	          break;
	
	}
}

/**
*字符判空
*/
function isSpace(strMain) {
	var strComp = strMain;
	try {
		if (strComp == "　" || strComp == "" || strComp == " "
				|| strComp == null || strComp == "null" || strComp.length == 0
				|| typeof strMain == "undefined" || strMain == "undefined") {
			return true;
		} else {
			return false;
		}
	} catch (e) {
		return false;
	}
}

//取得单选按钮组的实际值
function getRadioGroupValue(objName){
  var radioGroupValue = "";
  var ridList = document.getElementsByName(objName);
  for(var i=0; i<ridList.length; i++){
      if(ridList[i].checked){
          radioGroupValue = ridList[i].value;
          break;
      }
  }
  return radioGroupValue; 
}


//设置单选按钮组的实际值
function setRadioGroupValue(objName, objVal){
  var ridList = document.getElementsByName(objName);
  for(var i=0; i<ridList.length; i++){
      if(ridList[i].value == objVal){
          ridList[i].checked = true;
          break;
      }
  }
}

/*
* 用于判断表单中引用控件中的iframe是否加载完成。
* 参数：iframeid 传递了该参数，则只判断该iframe是否完成加载。
* 返回值："true"：加载完成；'false':加载未完成。
*/
function creator_iframeIsCompleted(iframeid){
  var oIframes = document.all.tags('iframe');

  for(var i = 0;i < oIframes.length;i++){
      if(!IsSpace(oIframes[i].controltype) && oIframes[i].controltype=="creatorSubForm"){
      	if(!IsSpace(iframeid)){
      		if(oIframes[i].id != iframeid){
      			continue;
      		}
      	}
      	if(oIframes[i].autoload=='yes'){
          	//alert(oIframes[i].id+":"+oIframes[i].readyState);
          	if(oIframes[i].src =="" || oIframes[i].readyState!='complete'){
              	return 'false';
          	}
      	}
      }
  }
  return 'true';  
}

//删除总按钮行为
function checkAll(totalCheck,checkName){
   var selectAll = document.getElementsByName(totalCheck);
   var o = document.getElementsByName(checkName);
   if(selectAll[0].checked==true){
	   for (var i=0; i<o.length; i++){
      	  if(!o[i].disabled){
      	  	o[i].checked=true;
      	  }
	   }
   }else{
	   for (var i=0; i<o.length; i++){
   	  	  o[i].checked=false;
   	   }
   }
}

//各个删除按钮行为
function checkOne(totalCheck,checkName){
   var selectAll = document.getElementsByName(totalCheck);
   var o = document.getElementsByName(checkName);
	var cbs = true;
	for (var i=0;i<o.length;i++){
		if(!o[i].disabled){
			if (o[i].checked==false){
				cbs=false;
			}
		}
	}
	if(cbs){
		selectAll[0].checked=true;
	}else{
		selectAll[0].checked=false;
	}
}


//全选、取消
function checkAll(checkName,thisobj){
	 var checkObj = document.getElementsByName(checkName);
	 if( checkObj.length > 0){
	 	if(thisobj.innerText == "全选"){//表示全选
	 		for( var i = 0 ; i < checkObj.length ; i ++){
	 			checkObj[i].checked = true;
	 		}
	 		thisobj.innerText = "取消";
	 	}else{//表示反选
	 		for( var i = 0 ; i < checkObj.length ; i ++){
	 			checkObj[i].checked = false;
	 		}
	 		thisobj.innerText = "全选";
	 	}
	 }
}
function getCheckBoxsValue(boxname){
	var checkboxs=document.getElementsByTagName("input"); 
	var i,rv=""; 
	for(i=0;i<checkboxs.length; i++) { 
		if(checkboxs[i].type=='checkbox'&&boxname.indexOf(boxname)!=-1) {
			if(checkboxs[i].checked){
				if(rv!=""){
					rv+=",";
				}
				rv += checkboxs[i].value;
			}
		} 
	}
	return rv;
}


/*
	* 清除下拉框的除第一个选项以外的所有选项	
	* id为要清除的下拉框的id
*/
function clearCombo(id){
	var object = document.getElementById(id);
	var optionIndex = object.options.length;
	for(;optionIndex>=1;optionIndex--){
		object.options.remove(optionIndex);
	}
}

/*
	* 清除下拉框的所有选项	
	* id为要清除的下拉框的id
*/
function clearCombo_all(id){
	var object = document.getElementById(id);
	var optionIndex = object.options.length;
	for(;optionIndex>=0;optionIndex--){
		object.options.remove(optionIndex);
	}
}

/*
	* 查询函数，根据指定的sql语句和指定字段的排序方法查询数据
	* ds为数据集
	* pgaeSize为数据集每页查询的条数
	* sql为查询的sql语句
	* Fields为要排序的字段数组
	* Orders为要排序的字段的顺序数组
	* 注意：Fields和Orders的长度必须一致，否则将报错
*/
function findData(ds,pageSize,sql,Fields,Orders){
	for(var i=0;i<Fields.length;i++){
		if(Orders[i]=="逆序"){
			sql += " order by "+Fields[i]+" desc";
		}
	}
	ds.PageSize = pageSize;
	ds.Open(sql);
}

/*
	* 弹出一个不带滚动条的模式对话框
	* url为对话框的路径
	* width为对话框的宽度
	* height为对话框的高度
*/
function openDlg(url,width,height){
	var returnVal = window.showModalDialog(url,window,"dialogWidth="+width+"px;dialogHeight="+height+"px;scroll=no;resizable=no;status=no");
	return returnVal;
}

/*
	* 弹出一个带滚动条的模式对话框
	* url为对话框的路径
	* width为对话框的宽度
	* height为对话框的高度
*/
function openDlg_scroll(url,width,height){
	var returnVal = window.showModalDialog(url,window,"dialogWidth="+width+"px;dialogHeight="+height+"px;scroll=yes;resizable=no;status=no");
	return returnVal;
}

//只读设置
function elementReadOnly(element,style) { //element 将设置只读的元素 style 将在动态创建的输入框上应用的样式
	var id = element.id;
	var value = element.value;
	var tmp = element.outerHTML.substring(0,7).toUpperCase();
	if (tmp == "<SELECT") //选择框的取值需要特殊处理
	{
		value = element.options[element.selectedIndex].text;
	}
	//创建输入框
	var myElement = document.createElement("input");
	myElement.setAttribute("id","my_" + id);
	myElement.setAttribute("value",value); //赋值
	myElement.style.width = element.style.width; //协同办公关心的主要属性 建议通过原元素的属性传递
	myElement.readOnly = "true"; //只读
	myElement.className = style; //应用样式
	element.style.display = "none"; //隐藏原元素
	element.parentNode.insertBefore(myElement,element); //将动态创建的输入框插入到原元素之前
}

ParameterContant = function(){
	this.GENERALSEARCHLIST = 'generalsearchlist';
	this.GENERALSEARCHANNUALLIST = 'generalsearchannuallist';
	this.ENTERPRISEANNUALLISTV2 = 'enterpriseAnnualListV2';
	this.ENTERPRISEANNUALLIST = 'enterpriseAnnualList';
	this.PRODUCTREGISTRLIST = "productregistrlist";
	this.PRODUCTSCONTINUEDLIST = "productscontinuedlist";
	this.PRODUCTCHANGELIST = "productchangelist";
	this.ENTERPRISEIDENTIFYLIST = "enterpriseIdentifyList";
	this.ENTERPRISEIDENTIFYLISTV2 = "enterpriseIdentifyListV2";
	this.ENTERPRISEAPPLICATIONLIST = "enterpriseApplicationList";
	this.PRODUCTREPLACELIST = "productreplacelist";
	this.ENTERANNUALAPPROVEWORKLIST = "enterAnnualApproveWorkList";
	this.ENTERANUALDONEWORKLIST = "enterAnnualDoneWorkList";
	this.SPPRODUCTREGISTRAPPROVEWORKLIST = "spproductRegistrApproveWorkList";
	this.SPPRODUCTREGISTRDONEWORKLIST = "spproductRegistrDoneWorkList";
	this.SPPRODUCTCONTINUEAPPROVEWORKLIST = "spproductContinueApproveWorkList";
	this.SPPRODUCTCONTINUEDONEWORKLIST = "spproductContinueDoneWorkList";
	   
	   /**
	    *审批处理-产品变更-待办  
	    */
	   this.SPPRODUCTCHCHANGEWORKLIST = "spproductchchangeworklist";
	   
	   /**
	    *审批处理-产品变更-已办  
	    */
	   this.SPPRODUCTCHCHANGEWORKYIBAN = "spproductchchangeworkyiban";
	   
	   /**
	    *审批处理-企业认定-待办  
	    */
	   this.SPENTERVERIFYAPPROVEWORKLIST = "spenterVerifyApproveWorkList";
	   
	   /**
	    *审批处理-企业认定-已办  
	    */
	   this.SPENTERVERIFYDONEWORKLIST = "spenterVerifyDoneWorkList";
	   /**
	    *审批处理-企业变更-待办  
	    */
	   this.SPENTERCHANGEWORKLIST = "spenterChangeWorkList";
	   /**
	    *审批处理-企业变更-已办  
	    */
	   this.SPENTERCHANGEAFTERWORKLIST = "spenterChangeAfterWorkList";
	   /**
	    *预审处理-产品登记-待办  
	    */
	   this.PREPRODUCTREGISTRAPPROVEWORKLIST = "preproductRegistrApproveWorkList";
	   /**
	    *预审处理-产品登记-已办  
	    */
	   this.PREPRODUCTREGISTRDONEWORKLIST = "preproductRegistrDoneWorkList";
	   
	   /**
	    *预审处理-产品延续-待办  
	    */
	   this.PREPRODUCTCONTINUEAPPROVEWORKLIST = "preproductContinueApproveWorkList";
	   
	   /**
	    *预审处理-产品延续-已办  
	    */
	   this.PREPRODUCTCONTINUEDONEWORKLIST = "preproductContinueDoneWorkList";
	   
	   /**
	    *预审处理-产品变更-待办  
	    */
	   this.PREPRODUCTCHCHANGEWORKLIST = "preproductchchangeworklist";
	   
	   /**
	    *预审处理-产品变更-已办  
	    */
	   this.PREPRODUCTCHCHANGEWORKYIBAN = "preproductchchangeworkyiban";
	   
	   /**
	    *预审处理-企业认定-待办  
	    */
	   this.PREENTERVERIFYAPPROVEWORKLIST = "preenterVerifyApproveWorkList";
	   
	   /**
	    *预审处理-企业认定-已办  
	    */
	   this.PREENTERVERIFYDONEWORKLIST = "preenterVerifyDoneWorkList";
	   
	   /**
	    *预审处理-企业变更-待办  
	    */
	   this.PREENTERCHANGEWORKLIST = "preenterChangeWorkList";
	   
	   /**
	    *预审处理-企业变更-已办  
	    */
	   this.PREENTERCHANGEAFTERWORKLIST = "preenterChangeAfterWorkList";
	   
	   /**
	    *受理处理-产品登记-待办  
	    */
	   this.ACCEPTPRODUCTREGISTRAPPROVEWORKLIST = "acceptproductRegistrApproveWorkList";
	   /**
	    *受理处理-产品登记-已办  
	    */
	   this.ACCEPTPRODUCTREGISTRDONEWORKLIST = "acceptproductRegistrDoneWorkList";
	   /**
	    *受理处理-产品延续-待办  
	    */
	   this.ACCEPTPRODUCTCONTINUEAPPROVEWORKLIST = "acceptproductContinueApproveWorkList";
	   /**
	    *受理处理-产品延续-已办  
	    */
	   this.ACCEPTPRODUCTCONTINUEDONEWORKLIST = "acceptproductContinueDoneWorkList";
	   /**
	    *受理处理-产品变更-待办  
	    */
	   this.ACCEPTPRODUCTCHCHANGEWORKLIST = "acceptproductchchangeworklist";
	   /**
	    *受理处理-产品变更-已办  
	    */
	   this.ACCEPTPRODUCTCHCHANGEWORKYIBAN = "acceptproductchchangeworkyiban";
	   /**
	    *受理处理-企业认定-待办  
	    */
	   this.ACCEPTENTERVERIFYAPPROVEWORKLIST = "acceptenterVerifyApproveWorkList";
	   /**
	    *受理处理-企业认定-已办  
	    */
	   this.ACCEPTENTERVERIFYDONEWORKLIST = "acceptenterVerifyDoneWorkList";
	   /**
	    *受理处理-企业变更-待办  
	    */
	   this.ACCEPTENTERCHANGEWORKLIST = "acceptenterChangeWorkList";
	   /**
	    *受理处理-企业变更-已办  
	    */
	   this.ACCEPTENTERCHANGEAFTERWORKLIST = "acceptenterChangeAfterWorkList";
	   /**
	    *办结处理-产品登记-待办  
	    */
	   this.FINISHPRODUCTREGISTRAPROVEWORKLIST = "finishproductRegistrApproveWorkList";
	   /**
	    *办结处理-产品登记-已办  
	    */
	   this.FINISHPRODUCTREGISTRDONEWORKLIST = "finishproductRegistrDoneWorkList";
	   /**
	    *办结处理-产品延续-待办  
	    */
	   this.FINISHPRODUCTCONTINUEAPPROVEWORKLIST = "finishproductContinueApproveWorkList";
	   /**
	    *办结处理-产品延续-已办  
	    */
	   this.FINISHPRODUCTCONTINUEDONEWORKLIST = "finishproductContinueDoneWorkList";
	   /**
	    *办结处理-产品变更-待办  
	    */
	   this.FINISHPRODUCTCHCHANGEWORKLIST = "finishproductchchangeworklist";
	   /**
	    *办结处理-产品变更-已办  
	    */
	   this.FINISHPRODUCTCHCHANGEWORKYIBAN = "finishproductchchangeworkyiban";
	   /**
	    *办结处理-企业认定-待办  
	    */
	   this.FINISHENTERVERIFYAPPROVEWORKLIST = "finishenterVerifyApproveWorkList";
	   /**
	    *办结处理-企业认定-已办  
	    */
	   this.FINISHENTERVERIFYDONEWORKLIST = "finishenterVerifyDoneWorkList";
	   /**
	    *办结处理-企业变更-待办  
	    */
	   this.FINISHENTERCHANGEWORKLIST = "finishenterChangeWorkList";
	   /**
	    *办结处理-企业变更-已办  
	    */
	   this.FINISHENTERCHANGEAFTERWORKLIST = "finishenterChangeAfterWorkList";
	   /**
	    *办结处理-补证申请-待办  
	    */
	   this.FINISHPRODUCTCHREPLACEWORKLIST = "finishproductchreplaceworklist";
	   /**
	    *办结处理-补申请-已办  
	    */
	   this.FINISHPRODUCTCHREPLACEWORKYIBAN = "finishproductchreplaceworkyiban";
	   /**
	    *企业认定资格审核  
	    */
	   this.ENTERPRISEEXAMINELIST = "enterpriseExamineList";
	   /**
	    *综合查看
	    */
	   this.GENERALSEARCHLISTSHOW = "generalsearchlistshow";
	   /**
	    * 综合查看（年审）
	    */
	   this.GENERALSEARCHANNUALLISTSHOW = "generalsearchannuallistshow";
	
	
	
	
}
var ParameterContants = new ParameterContant();
//org递归
//打开系统管理的五大对象的树 type:'org','user','role','group','job'
//id:接收id的text控件的id
//name:接收name的text控件的id
//isRadio:true or false true 表示前面是单选框，false表示是复选框 ；不传该参数则默认是复选框。
//showmode:static-dynamic,static
//rootId-根节点id, rootName-根节点名称, expandLevel-展开级数
var openTree_orgids = "";
var openTree_userids = "";
function openTree(type, id, name, isRadio, treeshowmode, rootId, rootName, expandLevel) {
openTree_userids = id.value;
openTree_names = name.value;
var orgids = "";
try {
	eval("orgids=creator_tree_" + id.id);
} catch (e) {
};

var rootId = rootId || "0";
var rootName = rootName || "组织机构树";
var expandLevel = expandLevel || "1";

var url = new StringBuffer().append(location.protocol).append("//")
		.append(location.host).append(getContextPath())
		.append("/eformsys/systemManager/");
if (!IsSpace(type)) {
	if (type == "org") { // 机构树 单选与多选
		if (!IsSpace(isRadio) && isRadio) {
			url.append("org_tree_radio.jsp?rootId="+rootId + "&rootName="+rootName + "&expandLevel="+expandLevel);
		} else {
			url.append("org_tree.jsp?rootId="+rootId + "&rootName="+rootName + "&expandLevel="+expandLevel);
		}
	} else if (type == "user") { // 机构用户树 单选与多选
		if (!IsSpace(isRadio) && isRadio) {
			url.append("orgUserTreeRadio.jsp?userid=").append(id.value)
					.append("&userName=").append(name.value+"&rootId="+rootId + "&rootName="+rootName + "&expandLevel="+expandLevel);
		} else {
			url.append("orgUserTree.jsp?orgid=").append(orgids+"&rootId="+rootId + "&rootName="+rootName + "&expandLevel="+expandLevel);
		}

	} else if (type == "role") { // 角色树 单选与多选
		if (!IsSpace(isRadio) && isRadio) {
			url.append("role_tree_radio.jsp");
		} else {
			url.append("role_tree.jsp");
		}

	} else if (type == "group") { // 组 单选与多选
		if (!IsSpace(isRadio) && isRadio) {
			url.append("groupTreeRadio.jsp");
		} else {
			url.append("groupTree.jsp");
		}

	} else if (type == "job") { // 工作岗位树 单选与多选
		if (!IsSpace(isRadio) && isRadio) {
			url.append("jobTreeRadio.jsp");
		} else {
			url.append("jobTree.jsp");
		}

	} else if (type == "business") { // 业务类别树 只有单选
		url.append("business_type_tree.jsp?busids=").append(id.value);

	} else if (type == "report") { // 报表树 只有单选
		url.append("reportmain.jsp");
	} else {
		url.append("org_tree_radio.jsp");
	}

}

var myReturn = "";
if (type == "report") {
	myReturn = window.showModalDialog(url.toString(), window,
			"dialogWidth:600px");
} else {
	myReturn = window.showModalDialog(url.toString(), window);
}
if (myReturn != "undefined" && myReturn != null) {
	var arr = myReturn.split(";");
	id.value = arr[0];
	name.value = arr[1];
	if (arr.length > 2) {
		eval("creator_tree_" + id.id + "=arr[2]");
	}

}

}

//自动根据iframe尺寸调节tab尺寸
function autoSetTabSize(isSet,framename,scrollHeight){
if(isSet){
	if(scrollHeight !=null && scrollHeight != "" && scrollHeight > 0){
		document.getElementById(framename).style.height = scrollHeight + 10;
	}else{
		document.getElementById(framename).style.height = document.getElementById(framename).document.body.scrollHeight-10;
	}
}

}

//自动根据iframe尺寸调节tab尺寸
function parentAutoSetTabSize(isSet,framename){
if(isSet){
	parent.document.getElementById(framename).style.height = parent.document.getElementById(framename).document.body.scrollHeight-10;
}

}
//根据isHidden判断是否隐藏tab
function setTabHidden(isHidden,Tabname){
if(isHidden){
	document.getElementById(Tabname).style.display="none";	
}
}

//判断字符串是否为空
function IsSpace(strMain) {
var strComp = strMain;
try {
	if (strComp == "  " || strComp == "" || strComp == " "
			|| strComp == null || strComp == "null" || strComp.length == 0
			|| typeof strMain == "undefined" || strMain == "undefined") {
		return true;
	} else {
		return false;
	}
} catch (e) {
	return false;
}
}

function isSpace(strMain){
return IsSpace(strMain);
}

function isOutStrLenth(str,lenth){
var length = str.replace(/[^\x00-\xff]/g,"**").length;
if(length>lenth){
	return true;
}
return false;
}

function clearhuanghangchar(value){

    var newValue = value.replaceAll("\r\n", "\\r\\n");  
    return newValue;   
}  


//清空字符串两边的空白
function trim(strMain) {
if (strMain == null) {
	return "";
}
strMain = strMain + "";
return strMain.replace(/(^\s*)|(\s*$)/g, "");
}

//清空combox
function clearCombo(object){
var optionIndex = object.options.length;
for(;optionIndex>=1;optionIndex--){
    object.options.remove(optionIndex);
}
}

/**
* 获取名字为objName的checkbox中被选中的值,用“,"号拼接
* @param objName
* @return
*/
function getCheckBoxValue(objName){
var checkboxarry = document.getElementsByName(objName);
var checkboxvalue = "";
for(var i=0;i<checkboxarry.length;i++){
	if(checkboxarry[i].checked){
		checkboxvalue = checkboxvalue + checkboxarry[i].value+",";
	}
}
if(!IsSpace(checkboxvalue)){
	checkboxvalue = checkboxvalue.substr(0,checkboxvalue.length-1);
}
return checkboxvalue;
}

/**
* 设置名字为objName的checkbox
* @param objName
* @param checkboxvalue 用逗号拼接的checkbox值的字符串
* @return
*/
function setCheckBoxValue(objName,checkboxvalue){

var checkboxarry = document.getElementsByName(objName);
if(!IsSpace(checkboxvalue)){
	var checkboxvaluearry = checkboxvalue.split(",");
	for(var i=0;i<checkboxvaluearry.length;i++){
		alert(checkboxvaluearry[i]-1);
		checkboxarry[checkboxvaluearry[i]-1].checked = true;
	}
}

}

/**
* 获取名字为objName的radio中被选中的那一项的值
* @param objName
* @return
*/
function getRadioGroupValue(objName){
var radioGroupValue = "";
var ridList = document.getElementsByName(objName);
for(var i=0; i<ridList.length; i++){
    if(ridList[i].checked){
        radioGroupValue = ridList[i].value;
        break;
    }
}
return radioGroupValue; 
}

/**
* 设置单选按钮组的实际值
* @param objName
* @return
*/
function setRadioGroupValue(objName, objVal){
var ridList = document.getElementsByName(objName);
for(var i=0; i<ridList.length; i++){
  if(ridList[i].value == objVal){
      ridList[i].checked = true;
      break;
  }
}
}
/**
* 对字符串中的某些特定字符进行转义
* @param str
* @return
*/
function descape(str){
  if(/{\'/.test(str)){
	  str = str.replace("{'","chr(41)");
  }
  if(/\'}/.test(str)){
	  str = str.substring(0, str.length - 2) + str.substring(str.length -2, str.length).replace("'}","chr(42)");
  }
  if(/\}/.test(str)){
	  str = str.replaceAll("}","chr(46)");
  }
  if(/\{/.test(str)){
	  str = str.replaceAll("{","chr(47)");
  }
  if(/\'\,\'/.test(str)){
	  str = str.replaceAll("','","chr(43)");
  }
  if(/\':\'/.test(str)){
	  str = str.replaceAll("':'","chr(44)");
  }
  if(/\#/.test(str)){
	 str = str.replaceAll("#","chr(50)");
  }
  if(/\'/.test(str)){
	str = str.replaceAll("'","chr(39)")
  }
  if(/\"/.test(str)){
		str = str.replaceAll('"',"chr(45)")
	  }
  if(/\&/.test(str)){
	str = str.replaceAll("&","chr(38)")
  }
  if(/\</.test(str)){
	str = str.replaceAll("<","chr(60)")
  }
  if(/\>/.test(str)){
	str = str.replaceAll(">","chr(62)")
  }
  if(/\%/.test(str)){
	 str = str.replaceAll("%","chr(37)")
  }

  if(/\r\n/.test(str)){
	  str = str.replaceAll("\r\n","chr(64)")
  } 
  while(/\\/.test(str)){
	  str = str.replace("\\","chr(40)");
  }
  return str;
}

String.prototype.replaceAll = function (AFindText,ARepText){
raRegExp = new RegExp(AFindText,"g");
return this.replace(raRegExp,ARepText);
}

/**
* 获取当前应用上下文路径
* @param str
* @return
*/
function getContextPath() { 
var contextPath = document.location.pathname; 
if(contextPath.substr(0,1) != "/"){
	contextPath="/"+contextPath;
}
var index =contextPath.substr(1).indexOf("/"); contextPath = contextPath.substr(0,index+1); 
delete index;
return contextPath; 
} 


/*展示办件历史的公共方法*/

showFlowHistoryByInsId = function(item_insid){
var url = getContextPath()+"/kcapp/xzsp/jsp/instance/showflowhistorytiaozhuan.jsp?iteminsid="+item_insid;
var winwidth=(screen.width - 920)/2;
var winheight=(screen.height - 700)/2-30;
var freatrues = "height=700,width=920,top="+winheight+"px,left="+winwidth+"px,status=yes,toolbar=no,menubar=no,location=no";

window.open(url,"",freatrues ); 
}



/* 控制TAB页的显示或隐藏：以TAB页id为基准 */
function hideOrShowTabById(id, type) {
  var realStyle = "";
  if(IsSpace(type) || (type.toUpperCase()=="hide".toUpperCase())) {
	    realStyle = "none";
  }
  var objs = document.getElementsByTagName("div");
  for(var i=0; i<objs.length; i++) {
	    if(trim(objs[i].id) == trim(id+"-tab")) {
		      objs[i].style.display = realStyle;
	    }
  }
}

//获得url后面的某个参数的值。
function creator_getQueryString(item) {
var svalue = location.search.match(new RegExp(new StringBuffer()
		.append("[\?\&]").append(item).append("=([^\&]*)(\&?)").toString(),
		"i"));
return svalue ? svalue[1] : svalue;
}
/**
* 页面上的字段组装成VO对象
* @param vo对像
* @return
*/

function getVoFromPage(vo){ 

for(var name in vo ){
  var obj = document.getElementById(name.toUpperCase());
  if(obj){
	  var vo_node= eval("vo."+name);
	  var vo_value = eval(vo.name);
	  if(vo_node!=null ){
    	 eval("vo."+name+".value = trim(obj.value)") ;
	  }else{
		   eval("vo."+name+" = trim(obj.value)") ; 
	  }
  }else{
	   var vo_node= eval("vo."+name);
	   if(vo_node!=null){
	       if(eval("vo."+name+".value")){
		   }else{
			   //eval("vo."+name+" = undefined") ;//modifyby yi.yang vo有默认值情况
		   }
	  }else{
		   eval("vo."+name+" = undefined");
	  }
   }
}
return vo;
}


//=====20100223添加,js字符串连接优化封装。
function StringBuffer() {
this._strs = new Array;
}
StringBuffer.prototype.append = function(str) {
this._strs.push(str);
return this;
}
StringBuffer.prototype.toString = function() {
return this._strs.join("");
}


//===============================20080424添加，通过djid获取jsp文件的路径
function getFormUrlByDjid(myDjid) {
var formUrl = new StringBuffer().append(location.protocol).append("//")
		.append(location.host).append(getContextPath()).append("/eformsys").append("/")
		.append("jxc/dj/").append(myDjid).append(".")
		.append("jsp");
return formUrl;
}


//20080612新增，根据表单id获取打开表单的路径。
function getOpenUrlByDjid(myDjid) {
var openDjUrl = new StringBuffer();
openDjUrl.append(location.protocol).append("//")
		.append(location.host).append(getContextPath()).append("/eformsys")
		.append("/fceform/common/djframe.htm?djtype=").append("ZW")
		.append("&djsn=");
var formUrl = openDjUrl + myDjid;
return formUrl;
}


//控制显示申请者类型
function showApplyCtrl(applyType){
if(applyType == "1"){
    $('applyCorpArea').style.display = "";
    $('applicantArea').style.display = "none";   
}else{
	  $('applyCorpArea').style.display = "none";
      $('applicantArea').style.display = "";        
}
}

/* 控制目标对象是否显示：如果已显示，则隐藏；否则显示20095-5huajun.zhang */
function showAreaCtrl(destObjId){
var destObj = document.getElementById(destObjId);
var srcImg = event.srcElement;
if(destObj){
    var style = destObj.style;
    if(destObj.style.display == "none"){
        destObj.style.display = "";
        srcImg.src = "../../resources/images/menu_off.gif"
    }else{
        destObj.style.display = "none";
        srcImg.src = "../../resources/images/menu_off.gif"
    }
}
}
//动态创建INPUT元素，兼容IE和FF浏览器
function createInputElement(type, name){
  var oInput = null;
if(document.all){ //IE
	  var oStr = "<input type='"+type+"' name='"+name+"'>";
	  oInput = document.createElement(oStr);
	  
}else{
	  oInput = document.createElement("input");
	  oInput.type = type;
	  oInput.name = name;
}
return oInput;
}

/* 将日期转为长整型 */
function getLongFormat(datStr){
 if(IsSpace(datStr)){
 	   return null;
 }
 datStr = datStr.replace("-","/");
datStr = datStr.replace("-","/");
return Date.parse(datStr);
}
/* 取指定长度的字符串 */
function getContentByLimit(str, iLimit){
 if(IsSpace(str)){
 	   return null;
 }    
 str = trim(str);
 if(str.length <= iLimit){
 	  return str;
 }else{
 	  return str.substr(0,iLimit);
 }
}
/* 将日期的横杠改为年月日 */
function getChnFormat(datStr){
 if(IsSpace(datStr)){
 	   return null;
 }
 return datStr.substr(0,4) + "年" + datStr.substr(5,2) + "月" + datStr.substr(8,2) + "日";
}

//全选、取消！！注意该方法之所以命名为checkAll是因为pg标签里面有一个checkAll方法
function checkAllItem(checkName,thisobj){
 var checkObj = document.getElementsByName(checkName);
 if( checkObj.length > 0){
 	if(thisobj.innerText.trim() == "全选"){//表示全选
 		for( var i = 0 ; i < checkObj.length ; i++){
 			checkObj[i].checked = true;
 		}
 		thisobj.innerText = "取消";
 	}else{//表示反选
 		for( var i = 0 ; i < checkObj.length ; i++){
 			checkObj[i].checked = false;
 		}
 		thisobj.innerText = "全选";
 	}
 }
}


//验证身份证是否合法
function checkIdcard(idcard){ 
var Errors=new Array( 
"验证通过!", 
"×身份证号码位数不对!", 
"×身份证号码出生日期超出范围或含有非法字符!", 
"×身份证号码校验错误!", 
"×身份证地区非法!" 
); 
var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"} 
var idcard,Y,JYM; 
var S,M; 
var idcard_array = new Array(); 
idcard_array = idcard.split(""); 
if(area[parseInt(idcard.substr(0,2))]==null) return Errors[4]; 
switch(idcard.length){ 
case 15: 
if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){ 
ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/; 
} else { 
ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/; 
} 
if(ereg.test(idcard)) return Errors[0]; 
else return Errors[2]; 
break; 
case 18: 
if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){ 
ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式 
} else { 
ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式 
} 
if(ereg.test(idcard)){ 
S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 
+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9 
+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10 
+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 
+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8 
+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4 
+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 
+ parseInt(idcard_array[7]) * 1 
+ parseInt(idcard_array[8]) * 6 
+ parseInt(idcard_array[9]) * 3 ; 
Y = S % 11; 
M = "F"; 
JYM = "10X98765432"; 
M = JYM.substr(Y,1); 
if(M == idcard_array[17]) return Errors[0]; 
else return Errors[3]; 
} 
else return Errors[2]; 
break; 
default: 
return Errors[1]; 
break; 
} 
} 
//url特殊字符
function encodeURLKcapp(str){
if(!IsSpace(str)){
	return encodeURIComponent(encodeURIComponent(str));
}else{
	return str;
}
}
//解码
function decodeURLKcapp(str){
if(!IsSpace(str)){
	return decodeURIComponent(decodeURIComponent(str));
}else{
	return str;
}
}

//特殊字符转码（可扩展）
function Escape2Sql(str){
str = str.replaceAll("'","'||chr(39)||'")
str = str.replaceAll("&","'||chr(38)||'")
str = str.replaceAll("<","'||chr(60)||'")
str = str.replaceAll(">","'||chr(62)||'")
str = str.replaceAll("%","'||chr(37)||'")
//str = str.replaceAll("%","\\%")
return str;
}

function iframeFitHeight(oIframe)
{//Iframe窗口自适应高度 兼容IE6.0,google, FF2.0以上
try
{
var oWin = oIframe.name ? window.frames[oIframe.name] : oIframe.contentWindow; 
oIframe.style.height = oWin.document.body.scrollHeight + "px";
}
catch(e){}
}

/*
* 根据参数创建页面隐藏域
* */
function createHiddenHtml(reportParam){
var str = reportParam.split("&");
for(var i = 0 ; i < str.length ; i++){	
	var name = str[i].split("=")[0];
	//var value = decodeURLKcapp(str[i].split("=")[1]);
	var value = str[i].split("=")[1];
	if(!IsSpace(name)){
		value = value.replaceAll("%25","%");
		if (IsSpace(document.getElementsByName(name))) {
			value = value.replaceAll("'","&#x27;");
			var hiddenObj = document.createElement("<input type = 'hidden' name = '"+name+"' value = '" + value + "'>");
			var tdObj = document.getElementById("hiddenTd1");
			tdObj.appendChild(hiddenObj);
		} else {
			document.getElementsByName(name)[0].value = value;				
		}
	}
}
}

//初始化方法中调用js中已定义方法
function getInvoked(func) {   
return new Function(func);   
}

/**
* 
* @param from_page
* @param iscache  是否加载缓存
*/
function returnFun(from_page,iscache){
var cachestr = "?searchCache=true";
 if(iscache == "false"){
	 cachestr = "";
 }
 var url = "";
 if(from_page == ParameterContants.GENERALSEARCHLIST){
	 url = getContextPath()+"/srrz/jsp/generalsearch/generalsearchlist.jsp"+cachestr;
 }else if(from_page == ParameterContants.GENERALSEARCHANNUALLIST){
	 url = getContextPath()+"/srrz/jsp/generalsearch/generalsearchannuallist.jsp"+cachestr;
 }else if(from_page == ParameterContants.ENTERPRISEANNUALLISTV2){
	 url = getContextPath()+"/srrz/jsp/annualV2/enterpriseAnnualList.jsp"+cachestr;
 }else if(from_page == ParameterContants.ENTERPRISEANNUALLIST){
	 url = getContextPath()+"/srrz/jsp/annual/enterpriseAnnualList.jsp"+cachestr;
 }else if(from_page == ParameterContants.PRODUCTREGISTRLIST){
	 url = getContextPath()+"/srrz/jsp/productregistr/productregistrlist.jsp"+cachestr;
 }else if(from_page == ParameterContants.PRODUCTSCONTINUEDLIST){
	 url = getContextPath()+"/srrz/jsp/productscontinued/productscontinuedlist.jsp"+cachestr;
 }else if(from_page == ParameterContants.PRODUCTCHANGELIST){
	 url = getContextPath()+"/srrz/jsp/productchange/productchangelist.jsp"+cachestr;
 }else if(from_page == ParameterContants.ENTERPRISEIDENTIFYLIST){
	 url = getContextPath()+"/srrz/jsp/enterprise/enterpriseIdentifyList.jsp"+cachestr;
 }else if(from_page == ParameterContants.ENTERPRISEIDENTIFYLISTV2){
	 url = getContextPath()+"/srrz/jsp/enterpriseV2/enterpriseIdentifyList.jsp"+cachestr;
 }else if(from_page == ParameterContants.ENTERPRISEAPPLICATIONLIST){
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterprisechangeeapplication/enterpriseApplicationList.jsp"+cachestr;
 }else if(from_page == ParameterContants.PRODUCTREPLACELIST){
	 url = getContextPath()+"/srrz/jsp/productchange/productreplacelist.jsp"+cachestr;
 }else if(from_page == ParameterContants.ENTERANNUALAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterAnnualAuditTab.jsp?selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.ENTERANUALDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterAnnualAuditTab.jsp?selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.SPPRODUCTREGISTRAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productregistr/productRegistrAuditTab.jsp?currentstatus=S5&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.PREPRODUCTREGISTRAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productregistr/productRegistrAuditTab.jsp?currentstatus=S1&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.ACCEPTPRODUCTREGISTRAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productregistr/productRegistrAuditTab.jsp?currentstatus=S6&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.FINISHPRODUCTREGISTRAPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productregistr/productRegistrAuditTab.jsp?currentstatus=S8&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.SPPRODUCTREGISTRDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productregistr/productRegistrAuditTab.jsp?currentstatus=S5&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.PREPRODUCTREGISTRDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productregistr/productRegistrAuditTab.jsp?currentstatus=S1&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.ACCEPTPRODUCTREGISTRDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productregistr/productRegistrAuditTab.jsp?currentstatus=S6&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.FINISHPRODUCTREGISTRDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productregistr/productRegistrAuditTab.jsp?currentstatus=S8&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.SPPRODUCTCONTINUEAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/productContinueAuditTab.jsp?currentstatus=S5&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.PREPRODUCTCONTINUEAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/productContinueAuditTab.jsp?currentstatus=S1&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.ACCEPTPRODUCTCONTINUEAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/productContinueAuditTab.jsp?currentstatus=S6&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.FINISHPRODUCTCONTINUEAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/productContinueAuditTab.jsp?currentstatus=S8&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.SPPRODUCTCONTINUEDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/productContinueAuditTab.jsp?currentstatus=S5&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.PREPRODUCTCONTINUEDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/productContinueAuditTab.jsp?currentstatus=S1&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.ACCEPTPRODUCTCONTINUEDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/productContinueAuditTab.jsp?currentstatus=S6&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.FINISHPRODUCTCONTINUEDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/productContinueAuditTab.jsp?currentstatus=S8&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.SPPRODUCTCHCHANGEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productchange/productchangework/productchchangework.jsp?currentstatus=S5&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.PREPRODUCTCHCHANGEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productchange/productchangework/productchchangework.jsp?currentstatus=S1&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.ACCEPTPRODUCTCHCHANGEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productchange/productchangework/productchchangework.jsp?currentstatus=S6&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.FINISHPRODUCTCHCHANGEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productchange/productchangework/productchchangework.jsp?currentstatus=S8&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.SPPRODUCTCHCHANGEWORKYIBAN){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productchange/productchangework/productchchangework.jsp?currentstatus=S5&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.PREPRODUCTCHCHANGEWORKYIBAN){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productchange/productchangework/productchchangework.jsp?currentstatus=S1&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.ACCEPTPRODUCTCHCHANGEWORKYIBAN){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productchange/productchangework/productchchangework.jsp?currentstatus=S6&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.FINISHPRODUCTCHCHANGEWORKYIBAN){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productchange/productchangework/productchchangework.jsp?currentstatus=S8&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.SPENTERVERIFYAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterVerifyAuditTab.jsp?currentstatus=S5&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.PREENTERVERIFYAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterVerifyAuditTab.jsp?currentstatus=S1&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.ACCEPTENTERVERIFYAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterVerifyAuditTab.jsp?currentstatus=S6&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.FINISHENTERVERIFYAPPROVEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterVerifyAuditTab.jsp?currentstatus=S8&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.SPENTERVERIFYDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterVerifyAuditTab.jsp?currentstatus=S5&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.PREENTERVERIFYDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterVerifyAuditTab.jsp?currentstatus=S1&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.ACCEPTENTERVERIFYDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterVerifyAuditTab.jsp?currentstatus=S6&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.FINISHENTERVERIFYDONEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterVerifyAuditTab.jsp?currentstatus=S8&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.SPENTERCHANGEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterChangeAuditTab.jsp?currentstatus=S5&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.PREENTERCHANGEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterChangeAuditTab.jsp?currentstatus=S1&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.ACCEPTENTERCHANGEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterChangeAuditTab.jsp?currentstatus=S6&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.FINISHENTERCHANGEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterChangeAuditTab.jsp?currentstatus=S8&selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.SPENTERCHANGEAFTERWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterChangeAuditTab.jsp?currentstatus=S5&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.PREENTERCHANGEAFTERWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterChangeAuditTab.jsp?currentstatus=S1&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.ACCEPTENTERCHANGEAFTERWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterChangeAuditTab.jsp?currentstatus=S6&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.FINISHENTERCHANGEAFTERWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/softenterprise/enterChangeAuditTab.jsp?currentstatus=S8&selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.FINISHPRODUCTCHREPLACEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productchange/productchangework/productchreplacework.jsp?selectedTabPaneId=worklist"+cachestr;
 }else if(from_page == ParameterContants.FINISHPRODUCTCHREPLACEWORKLIST){
	 if(iscache != "false" ){
		 cachestr = "&iscache=true";
	 }
	 url = getContextPath()+"/srrz/jsp/productchange/productchangework/productchreplacework.jsp?selectedTabPaneId=done_work"+cachestr;
 }else if(from_page == ParameterContants.ENTERPRISEEXAMINELIST){
	 url = getContextPath()+"/srrz/jsp/annual/enterpriseExamineList.jsp"+cachestr;
 }else if(from_page == ParameterContants.GENERALSEARCHLISTSHOW){
	 url = getContextPath()+"/srrz/jsp/generalsearch/generalsearchlistshow.jsp"+cachestr;
 }else if(from_page == ParameterContants.GENERALSEARCHANNUALLISTSHOW){
	 url = getContextPath()+"/srrz/jsp/generalsearch/generalsearchannuallistshow.jsp"+cachestr;
 }else{
	 url = getContextPath()+"/srrz/jsp/main.jsp?menu_path=module";
 }
 

 window.parent.location = url;
 
}


function openNewDiv_new(iframeid) {
var m = "mask_new_div";
var _id = "mesWindow_div";
if (docEle(_id)) document.body.removeChild(docEle(_id));
if (docEle(m)) document.body.removeChild(docEle(m));
//mask遮罩层
var newMask = document.createElement("div");
newMask.id = m;
newMask.style.position = "absolute";
newMask.style.zIndex = "9999";
_scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
_scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
newMask.style.width = _scrollWidth + "px";
newMask.style.height = _scrollHeight + "px";
newMask.style.top = "0px";
newMask.style.left = "0px";
newMask.style.background = "#33393C";
newMask.style.filter = "alpha(opacity=20)";
newMask.style.opacity = "0.80";
document.body.appendChild(newMask);
//新弹出层
var newDiv = document.createElement("div");
newDiv.id = _id;
newDiv.style.position = "absolute";
newDiv.style.zIndex = "9999";
newDivWidth = 200;
newDivHeight = 40;
newDiv.style.width = newDivWidth + "px";
newDiv.style.height = newDivHeight + "px";
newDiv.style.top = (document.body.scrollTop + document.body.clientHeight/2 - newDivHeight/2) + "px";
newDiv.style.left = (document.body.scrollLeft + document.body.clientWidth/2 - newDivWidth/2) + "px";
newDiv.style.background = "#EFEFEF";
newDiv.style.border = "1px solid #860001";
newDiv.style.padding = "15px";
newDiv.id="mesWindow_div";
newDiv.className="mesWindow";
newDiv.innerHTML=document.frames[iframeid].document.getElementById("exportreport").innerHTML;
var v_top=(document.body.clientHeight-newDiv.clientHeight)/2;
v_top+=document.documentElement.scrollTop;
styleStr="top:"+(v_top-230)+"px;left:"+(document.body.clientWidth/2-newDiv.clientWidth/2)+"px;position:absolute;width:600px;height:300px;margin-left:-300px;left:50%;z-index:9999;";
newDiv.style.cssText=styleStr;
document.body.appendChild(newDiv);
}
//关闭层
function closeDiv_new(){
if(document.getElementById("mesWindow_div")!=null){
     document.body.removeChild(docEle('mesWindow_div'));
}
if(document.getElementById('mask_new_div')!=null){
     document.body.removeChild(docEle('mask_new_div'));
}
  return false;
}
var docEle = function() {
return document.getElementById(arguments[0]) || false;
}

