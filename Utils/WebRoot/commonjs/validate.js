/*
	* 验证邮件格式是否正确，如果正确则返回true，否则警告并返回false
	* value为要验证的值
	* alertValue为警告值
	* regExp为正则表达式
	* 返回值类型为boolean
*/
function checkEmail(value,alertValue){
	var regExp = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	value = value.trim();
	if(!IsSpace(value)){
		if(!regExp.test(value)){
			alert(alertValue);
			return false;
		}
	}
	return true;
}

/*
	* 验证电话号码格式是否正确，如果正确则返回true，否则警告并返回false
	* value为要验证的值
	* alertValue为警告值
	* regExp为正则表达式
	* 返回值类型为boolean
*/
function checkPhone(value,alertValue){
	var regExp = /(^\d{7}$)|(^0\d{3}-?\d{7}$)|(^0?1[35]\d{9}$)/;
	value = value.trim();
	if(!IsSpace(value)){
		if(!regExp.test(value)){
			alert(alertValue);
			return false;
		}
	}
	return true;
}

/*
	* 验证手机号码格式是否正确，如果正确则返回true,否则警告并返回false
	* value为要验证的值
	* alertValue为警告值
	* regExp为正则表达式
	* 返回值类型为boolean
*/
function checkMobile(value,alertValue){
	value = value.trim();
	var regExp = /^0?\d{11}$/;
	if(!IsSpace(value)){
		if(!regExp.test(value)){
			alert(alertValue);
			return false;
		}
	}
	return true;
}

/*
	* 判断值是否为空，如果不为空则返回true，否则警告并返回false
	* value为要验证的值
	* alertValue为警告值
	* 返回值类型为boolean
*/
function checkIsEmpty(value,alertValue){
	value = value.trim();
	if(IsSpace(value)){
		alert(alertValue);
		return false;
	}
	return true;
}

/*
	* 判断值得长度是否大于规定长度，如果不大于则返回true，否则警告并返回false
	* value为要验证的值
	* length为规定长度
	* alertValue为警告值
	* 返回值类型为boolean
*/
function checkLength(value,length,alertValue){
	value = value.trim();
	if(value.length>length){
		alert(alertValue);
		return false;
	}
	return true;
}

/*
	* 验证是否为规定的格式的数字，如果是的则返回true，否则警告并返回false
	* value为要验证的值
	* intLength为整数位数
	* floatLength为小数位数
	* alertValue为警告值
	* id为获取值的文本框的ID
	* 返回值类型为boolean
*/
function checkNum(value,intLength,floatLength,alertValue,id){
	value = value.trim();
	if(!IsSpace(value)){
		value = Math.round(value*Math.pow(10,floatLength));
		value = value.toString();
		value = value.substring(0,value.length-floatLength)+"."+value.substring(value.length-floatLength,value.length);
		if(isNaN(value)){
			alert(alertValue);
			return false;
		}
		document.getElementById(id).value = value;
	}
	return true;
}

/*
	* 验证是否为规定的格式的数字，如果是的则返回true，否则警告并返回false
	* value为要验证的值
	* intLength为整数位数，为大于0的整数
	* floatLength为小数位数，为大于等于0的整数，如果验证值为整数，则此值为0
	* alertValue为警告值
	* regExp为正则表达式
	* 返回值类型为boolean
*/
function checkNum(value,intLength,floatLength,alertValue){
	value = value.trim();
	var regExp = /^(-|\+)?\d+(\.)?(\d+)*$/;
	var index = value.indexOf(".");
	if(!IsSpace(value)){
		var values = new Array();
		if(index != "-1"){
			values[0] = value.substring(0,index);
			values[1] = value.substring(index+1,value.length);
		}
		else{
			values[0] = value;
			values[1] = "0";
		}
		if(floatLength==0&&index!="-1"){
			alert(alertValue);
			return false;
		}
		if(values[0].length>intLength){
			alert(alertValue);
			return false;
		}
		else if(floatLength!=0){
			if(values[1].length>floatLength){
				alert(alertValue);
				return false;
			}
		}
		if(!regExp.test(value)){
			alert(alertValue);
			return false;
		}
	}
	return true;
}

/*
	* 验证日期是否正确，如果是的则返回true，否则警告并返回false
	* value为要验证的值
	* alertValue为警告值
	* 返回值类型为boolean
*/
function checkDate(value,alertValue){
	value = value.trim();
	var regExp = /^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0?[13578]|1[02])-(0?[1-9]|[12][0-9]|3[01]))|((0?[469]|11)-(0?[1-9]|[12][0-9]|30))|(0?2-(0?[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-0?2-29))$/;
	if(!IsSpace(regExp)){
		if(!regExp.test(value)){
			alert(alertValue);
			return false;
		}
	}
	return true;
}

/*
	* 验证特殊字符单引号（'）,如果含有单引号则警告并返回false,否则返回true
	* value为要验证的值
	* 返回类型为boolean
*/
function checkSpecial(value){
	value = value.trim();
	var str = value;
	var SPECIAL_STR = "'";
	for(i=0;i<str.length;i++){
		if (SPECIAL_STR.indexOf(str.charAt(i))!= -1){
			alert("不能含有字符单引号("+str.charAt(i)+")，请重新输入！");
			return false;
		}
	}
	return true;
}