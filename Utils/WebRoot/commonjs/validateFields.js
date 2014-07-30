/******************************常用输入栏位验证逻辑***********************/


//给String添加trim方法 
String.prototype.trim = function(){
   return this.replace(/(^\s*)|(\s*$)/g, "");
}

/******************验证入口对象*******************/
function checkList(){
    this.blankList = new Array(0);//必须输入
    this.numList = new Array(0);  //输入必须为数值
    this.intList = new Array(0);  //输入必须为整数
    this.idCardList = new Array(0); //输入必须为正确的身份证号
    this.emailList = new Array(0);//输入必须为正确的email
    this.zipList = new Array(0);  //输入必须为正确的邮编
    this.phoneList = new Array(0);//输入必须为正确的电话号码
    this.dateList = new Array(0); //输入必须为正确的日期类型
    this.compareDateList = new Array(0); //比较日期大小
	this.regularExpressionList=new Array(0);//验证正则表达式
    this.msgList = new Array(0);  //验证信息
    this.error = false ;

    this.appendList = appendList;
    this.addBlank = addBlank;
    this.addNum = addNum;
    this.addInt = addInt;
    this.addIdCard = addIdCard;
    this.addEmail = addEmail;
    this.addZip = addZip;
    this.addPhone = addPhone;
    this.addDate = addDate;
    this.addCompareDate = addCompareDate;
	this.addRegularExpreesion=addRegularExpreesion;

    this.check = check;
    this.checkBlank = checkBlank;
    this.checkNum = checkNum;
    this.checkInt = checkInt;
    this.checkIdCard = checkIdCard;
    this.checkEmail = checkEmail;
    this.checkZip = checkZip;
    this.checkPhone = checkPhone;
    this.checkDate = checkDate;
    this.checkDateHelper = checkDateHelper;
    this.checkCompareDate = checkCompareDate;
	this.checkRegularExpression=checkRegularExpression;
}



//把一个输入栏位名称加入到数组中
function appendList(objList, fldName){
    var size = objList.length;
    objList[size] = fldName;
}

//加入一个必须输入的栏位名称
function addBlank(fldName){
    eval("this.appendList(this.blankList,'"+fldName+"')");
}

//加入一个需要输入为数值的栏位名称
function addNum(fldName){
    eval("this.appendList(this.numList,'"+fldName+"')");
}

//加入一个需要输入为整型的栏位名称
function addInt(fldName){
    eval("this.appendList(this.intList,'"+fldName+"')");
}

//加入一个需要输入身份证号的栏位名称
function addIdCard(fldName){
    eval("this.appendList(this.idCardList,'"+fldName+"')");
}

//加入一个需要输入Email的栏位名称
function addEmail(fldName){
    eval("this.appendList(this.emailList,'"+fldName+"')");
}

//加入一个需要输入电话号码的栏位名称
function addZip(fldName){
    eval("this.appendList(this.zipList,'"+fldName+"')");
}

//加入一个需要输入电话号码的栏位名称
function addPhone(fldName){
    eval("this.appendList(this.phoneList,'"+fldName+"')");
}

//加入一个需要输入日期的栏位名称
function addDate(fldName){
    eval("this.appendList(this.dateList,'"+fldName+"')");
}

//加入需要比较日期大小的两个栏位名称
function addCompareDate(){
    this.appendList(this.compareDateList,addCompareDate.arguments);
    this.addDate(addCompareDate.arguments[0]);
    this.addDate(addCompareDate.arguments[1]);
}


//验证正则表达式
function addRegularExpreesion(fldName,regularExpression){
	if(addRegularExpreesion.arguments.length==1)
	{
		regularExpression=/^[^`~!@#$%^&*()+=|\\\][\]\{\}:;'\,.<>/?]{1}[^`~!@$%^&()+=|\\\][\]\{\}:;'\,.<>?]{0,19}$/; 
	}
	var regularObj={fldName:fldName,regularExpression:regularExpression};
	this.appendList(this.regularExpressionList,regularObj);
}


/*----------------检验操作，验证所有栏位----------------*/
function check(){
    //加入提示信息框
    document.body.appendChild(mouseMsg); 
    //验证
    if(this.blankList){
       for(var i=0; i<this.blankList.length; i++){
          this.checkBlank(this.blankList[i]);
       }
    }
    if(this.numList){
       for(var i=0; i<this.numList.length; i++){
          this.checkNum(this.numList[i]);
       }
    }
    if(this.intList){
       for(var i=0; i<this.intList.length; i++){
          this.checkInt(this.intList[i]);
       }
    }
    if(this.idCardList){
       for(var i=0; i<this.idCardList.length; i++){
          this.checkIdCard(this.idCardList[i]);
       }
    }
    if(this.emailList){
       for(var i=0; i<this.emailList.length; i++){
          this.checkEmail(this.emailList[i]);
       }
    }
    if(this.zipList){
       for(var i=0; i<this.zipList.length; i++){
          this.checkZip(this.zipList[i]);
       }
    }
    if(this.phoneList){
       for(var i=0; i<this.phoneList.length; i++){
          this.checkPhone(this.phoneList[i]);
       }
    }
    if(this.dateList){
       for(var i=0; i<this.dateList.length; i++){
          this.checkDate(this.dateList[i]);
       }
    }
    if(this.compareDateList){
       for(var i=0; i<this.compareDateList.length; i++){
          this.checkCompareDate(this.compareDateList[i]);
       }
    }
	if(this.regularExpressionList){
		this.checkRegularExpression(this.regularExpressionList);
	}
    //释放内存
    this.blankList = new Array(0);
    this.numList = new Array(0);
    this.intList = new Array(0);
    this.idCardList = new Array(0);
    this.emailList = new Array(0);
    this.zipList = new Array(0);
    this.phoneList = new Array(0);
    this.dateList = new Array(0);
    this.compareDateList = new Array(0);
    //如果有错，则提示使用者
    if((this.msgList && this.msgList.length>0) || this.error){
        alert("输入错误");
        return false;
    }else{
        return true;
    }
}


//检验必须输入的字段
function checkBlank(fldName){
    eval('var obj = document.getElementsByName("'+fldName+'")');
    for(var i=0; i<obj.length; i++){
       if(doTrim(obj[i].value) == ""){
          obj[i].style.backgroundColor = errBgColor;
          obj[i].setAttribute("tt","不能为空值");
          eval("this.appendList(this.msgList,'0"+fldName+"')");
       }else{
          obj[i].style.backgroundColor = ""; 
          obj[i].setAttribute("tt","输入正确");
       }
       obj[i].attachEvent("onmouseover",showMsg);
       obj[i].attachEvent("onmouseout",hideMsg);
    }
}

//检验必须为数字的字段
function checkNum(fldName){
    eval('var obj = document.getElementsByName("'+fldName+'")');
    for(var i=0; i<obj.length; i++){
       if(isNotNum(obj[i])){  //不是数值
          obj[i].style.backgroundColor = errBgColor;
          obj[i].setAttribute("tt","必须为数字");
          eval("this.appendList(this.msgList,'1"+fldName+"')");
       }else{
          obj[i].style.backgroundColor = "";
          obj[i].setAttribute("tt","输入正确");
       }
       obj[i].attachEvent("onmouseover",showMsg);
       obj[i].attachEvent("onmouseout",hideMsg);
    }
}

//检验必须为整数的字段
function checkInt(fldName){
    eval('var obj = document.getElementsByName("'+fldName+'")');
    for(var i=0; i<obj.length; i++){
        if(isNotNum(obj[i])){  //不是数值
          obj[i].style.backgroundColor = errBgColor;
          obj[i].setAttribute("tt","必须为整数");
          eval("this.appendList(this.msgList,'1"+fldName+"')");
       }else if(isNotInt(obj[i])){
          obj[i].style.backgroundColor = errBgColor;
          obj[i].setAttribute("tt","必须为整数");
          eval("this.appendList(this.msgList,'1"+fldName+"')");
       }else{
          obj[i].style.backgroundColor = "";
          obj[i].setAttribute("tt","输入正确");
       }
       obj[i].attachEvent("onmouseover",showMsg);
       obj[i].attachEvent("onmouseout",hideMsg);
    }
}

//验证身份证号
function checkIdCard(fldName){
    eval('var obj = document.getElementsByName("'+fldName+'")');
    for(var i=0; i<obj.length; i++){
       var chkFlag = true;
       var errMsg = "";
       var temp = doTrim(obj[i].value);
       if(temp==""){
          chkFlag = true;
       }else if((temp.length!=15) && (temp.length!=18)){ //位数错误
          chkFlag = false;
          errMsg = "身份证号位数为"+temp.length+"错误，必须为15或18";
       }else{
          var pid = temp.length==18 ? temp.substring(0,17) : temp.slice(0,6)+"19"+temp.slice(6,14);
          var reg = /^\d+$/;
          if(!reg.test(pid)){
             chkFlag = false;
             errMsg = "身份证号除最后一位外，必须为数字";
          }else{
             var year = temp.slice(6,10);
             var month = temp.slice(10,12)-1;
             var day = temp.slice(12,14);
             var date = new Date(year, month, day);
             var nowDate = new Date();
             var chkedYear = date.getFullYear();
             var chkedMonth = date.getMonth();
             var chkedDay = date.getDate();
             if((year!=chkedYear) || (month!=chkedMonth) || (day!=chkedDay) || (date>nowDate) || (year<1940)){
                 chkFlag = false;
                 errMsg = "身份证号输入错误";
             }
          }
       }
       //输入错误
       if(!chkFlag){ 
          obj[i].style.backgroundColor = errBgColor;  
          obj[i].setAttribute("tt",errMsg);
          eval("this.appendList(this.msgList,'2"+fldName+"')");
       }else{
          obj[i].style.backgroundColor = "";
          obj[i].setAttribute("tt","输入正确");
       }
       obj[i].attachEvent("onmouseover",showMsg);
       obj[i].attachEvent("onmouseout",hideMsg);
    }
}

//验证Email
function checkEmail(fldName){
    eval('var obj = document.getElementsByName("'+fldName+'")');
    for(var i=0; i<obj.length; i++){
       var reg = /^(.+)@(.+)$/;
       if((doTrim(obj[i].value)!="") && !reg.test(obj[i].value)){
          obj[i].style.backgroundColor = errBgColor;  
          obj[i].setAttribute("tt","email输入错误");
          eval("this.appendList(this.msgList,'3"+fldName+"')");
       }else{
          obj[i].style.backgroundColor = "";
          obj[i].setAttribute("tt","输入正确");
       }
       obj[i].attachEvent("onmouseover",showMsg);
       obj[i].attachEvent("onmouseout",hideMsg);
    }
}

//验证邮政编码
function checkZip(fldName){
    eval('var obj = document.getElementsByName("'+fldName+'")');
    for(var i=0; i<obj.length; i++){
       var reg = /^\d{6}$/;
       if((doTrim(obj[i].value)!="") && !reg.test(obj[i].value)){
          obj[i].style.backgroundColor = errBgColor;  
          obj[i].setAttribute("tt","邮政编码输入错误，必须为6位数字");
          eval("this.appendList(this.msgList,'4"+fldName+"')");
       }else{
          obj[i].style.backgroundColor = "";
          obj[i].setAttribute("tt","输入正确");
       }
       obj[i].attachEvent("onmouseover",showMsg);
       obj[i].attachEvent("onmouseout",hideMsg);
    }
}

//验证电话号码
function checkPhone(fldName){
    eval('var obj = document.getElementsByName("'+fldName+'")');
    for(var i=0; i<obj.length; i++){
       var reg = /((\(\d{3}\)|(\d{3}-))?\d{8})|((\(\d{4}\)|(\d{4}-))?\d{7})/;
       if((doTrim(obj[i].value)!="") && !reg.test(obj[i].value)){
          obj[i].style.backgroundColor = errBgColor;  
          obj[i].setAttribute("tt","电话号码输入错误");
          eval("this.appendList(this.msgList,'5"+fldName+"')");
       }else{
          obj[i].style.backgroundColor = "";
          obj[i].setAttribute("tt","输入正确");
       }
       obj[i].attachEvent("onmouseover",showMsg);
       obj[i].attachEvent("onmouseout",hideMsg);
    }
}

//验证日期
function checkDate(fldName){
    var flag=false;
    eval('var obj = document.getElementsByName("'+fldName+'")');
    for(var i=0; i<obj.length; i++){
       var trimedDate = doTrim(obj[i].value);
       if(trimedDate!=""){
	       var dateStr = preDateFormat(doTrim(obj[i].value));
	       var reg = /^\d{8}$/;
	       if(!reg.test(dateStr)){
	          obj[i].style.backgroundColor = errBgColor;  
	          obj[i].setAttribute("tt","日期输入错误，除掉分隔符必须为8位数字");
	          eval("this.appendList(this.msgList,'7"+fldName+"')");
	       }else if(this.checkDateHelper(dateStr)!=""){
	          switch(parseInt(this.checkDateHelper(dateStr))){
	             case 1:
	                 obj[i].setAttribute("tt","日期格式月份有误") ;
	                 break;
	             case 2:
	                 obj[i].setAttribute("tt","日期格式日子有误") ;
	                 break;
	             case 3:
	                 obj[i].setAttribute("tt","日期输入错误") ;
	                 break;
	          }
	          obj[i].style.backgroundColor = errBgColor;  
	          eval("this.appendList(this.msgList,'7"+fldName+"')");
			  
	       }else{
	          obj[i].style.backgroundColor = "";
	          obj[i].setAttribute("tt","输入正确");
			  flag=true;
	       }
	   }
       obj[i].attachEvent("onmouseover",showMsg);
       obj[i].attachEvent("onmouseout",hideMsg);
	   return flag;
    }
}

//把日期的斜杠、分隔符去掉
function preDateFormat(dateStr){
    var partArr = dateStr.split("-");
    if(partArr.length == 1)
        partArr = dateStr.split("/");
    var generalDate="";
    for(var i=0;i<partArr.length;i++){
        if(i>0 && partArr[i].length==1)
      	   partArr[i]="0"+partArr[i] ;
        generalDate += partArr[i];
    }
    return generalDate;
}

//验证日期输入是否正确
function checkDateHelper(dateStr){
    var len = dateStr.length;
    var yy = dateStr.substring(0,len-4);
    var mm =0,dd=0;
    if(dateStr.substr(len-4,2).substr(0,1)=="0")    
        mm = parseInt(dateStr.substr(len-3,1));
    else
        mm = parseInt(dateStr.substr(len-4,2));
    if(dateStr.substr(len-2,2).substr(0,1)=="0")
        dd = parseInt(dateStr.substr(len-1,1));
    else
        dd = parseInt(dateStr.substr(len-2,2));
    
    if(mm>12 || mm<=0){
        return "1";
    }else{
        switch(mm){
           case 1:case 3:case 5:case 7:case 8:case 10:case 12:
              if(dd>31 || dd<1)  return "2";
              else   return "";
              break;
           case 4:case 6:case 9:case 11:
              if (dd>30 || dd<1)    return "2";
              else   return "";
              break;
           case 2:
              if (dd>29 || dd<1){
                  return "2";
              }else if(dd==29){
                  if(isReun(yy))  return "";
                  else return "2";
              }else{
                  return "";
              }
              break;
           default:
              return "3";
        }
    }
}

//判断某年是否是闰年
function isReun(yy){
    if(yy%400 == 0){
        return true;
    }else if(yy%100 == 0){
        return false;
    }else if(yy%4 == 0){
        return true;
    }
}


//比较两个日期大小
function checkCompareDate(target){
   
    if(!this.checkDate(target[0])) return; //验证日期输入是否正确
    if(!this.checkDate(target[1])) return; //验证日期输入是否正确 
    var compareDateObj1 = document.getElementsByName(target[0])[0];
    var compareDateObj2 = document.getElementsByName(target[1])[0];
    if(compareDateObj1.value.length != compareDateObj2.value.length){
	 
		compareDateObj1.style.backgroundColor = errBgColor;  
        compareDateObj1.setAttribute("tt","左右比较的日期个数必须相等");
        eval("this.appendList(this.msgList,'8"+target[0]+"')");
        compareDateObj2.style.backgroundColor = errBgColor;  
        compareDateObj2.setAttribute("tt","左右比较的日期个数必须相等");
        eval("this.appendList(this.msgList,'8"+target[1]+"')");
       
    }else{
		    
            if(compareDateObj1.value > compareDateObj2.value){
                compareDateObj1.style.backgroundColor = errBgColor;  
		        compareDateObj1.setAttribute("tt","开始日期不能大于结束日期");
		        eval("this.appendList(this.msgList,'8"+target[0]+"')");
		        compareDateObj2.style.backgroundColor = errBgColor;  
		        compareDateObj2.setAttribute("tt","开始日期不能大于结束日期");
		        eval("this.appendList(this.msgList,'8"+target[1]+"')");
            }else{
               compareDateObj1.style.backgroundColor = "";
               compareDateObj1.setAttribute("tt","输入正确");
               compareDateObj2.style.backgroundColor = "";
               compareDateObj2.setAttribute("tt","输入正确");
            } 
            compareDateObj1.attachEvent("onmouseover",showMsg);
            compareDateObj1.attachEvent("onmouseout",hideMsg);
            compareDateObj2.attachEvent("onmouseover",showMsg);
            compareDateObj2.attachEvent("onmouseout",hideMsg);
        }
    
}


//验证是否满足正则表达式
function checkRegularExpression(regularExpressionList){
	for(var i=0;i<regularExpressionList.length;i++){
		var regularObj=regularExpressionList[i];
		var obj=document.getElementById(regularObj.fldName);
		if(obj.value!=""&&!regularObj.regularExpression.test(obj.value)){
			obj.style.backgroundColor = errBgColor;  
			obj.setAttribute("tt","输入错误，请检查是否包含[~!@#$%^&*]等特殊字符");
			eval("this.appendList(this.msgList,'9"+obj+"')");
			obj.attachEvent("onmouseover",showMsg);
			obj.attachEvent("onmouseout",hideMsg);
		}else{
			obj.style.backgroundColor = "";
			obj.setAttribute("tt","输入正确");
		}
	}


}



//把空格过滤掉
function doTrim(s){
    if(s==null){
       return "";
    }else{
       return s.trim();
    }   
}

//是否为数字
function isNotNum(obj){
   if((obj!=null) && (doTrim(obj.value)!="")){
      var reg = /,/g;
      var temp = obj.value.replace(reg,""); //去掉逗点
      return isNaN(temp)
   }
   return false;
}

//是否是整数
function isNotInt(obj){
   if(obj==null || obj.value==null)
      return true;
   var p = obj.value.indexOf(".");
   if(p != -1)
      if(parseFloat(obj.value.substring(p))!=0)
         return true;
   return false;
}

//显示错误信息提示
function showMsg(){
   mouseMsg.style.cssText = "position:absolute;";
   mouseMsg.style.backgroundColor = "#ffffcc"; //提示小窗口的背景色
   mouseMsg.style.visibility = "visible";
   mouseMsg.style.left = event.x + 8;
   mouseMsg.style.top = event.y + document.body.scrollTop + 8;
   if (event.srcElement.tt){
      mouseMsg.innerHTML = "&nbsp;"+event.srcElement.tt+"&nbsp;";
   }
}

//隐藏错误信息
function hideMsg(){
   mouseMsg.style.visibility = "hidden";
}

//错误信息显示框
var mouseMsg = document.createElement("DIV");
//出错栏位背景色
var errBgColor = "#ffc8ff"; 