/**
 * 解析json取值
 * @returns
 */
function getByJson(){
	var temp = '{"burstarea":"数量上下限为：1-9999999999","cc_form_instanceid":"","charge_basis":"","computation_unit":"元/平方米","formula_type":" 公式一(单价*数量)","levy_basis":"","org_id":"2f478626-7df1-47f3-a0ea-5a59f7b8b394","out_syscode":"","priceorrate":2,"ratelowerlimit":"","rateupperlimit":"","standard_code":"100017","standard_id":"1163","standard_lowerlimit":1,"standard_name":"建筑施工污染保洁费","standard_time":"2014-05-22 10:38:24.0","standard_unit":"","standard_upperlimit":1.0E10}';
	var obj = JSON.parse(temp); 
	alert(obj.burstarea);
}

//字符串转对象(strJSON代表json字符串) 
  var obj = eval(strJSON); 
  var obj = strJSON.parseJSON(); 
  var obj = JSON.parse(strJSON)； 
//json对象转字符串(obj代表json对象) 
  var str = obj.toJSONString(); 
  var str = JSON.stringify(obj) 
//运用时候需要除了eval()以外需要json.js包（切记哦）

/*
   函数:   toJSON
   说明:   Object转成Json对应
   参数:   Object
   返回值: 无
*/
/*
Object.prototype.toJSON = function()
{
	var temp = [];
	temp.push("{");
	for(var mem in this)
	{
		switch(typeof(this[mem]))
		{
			case "function":
				temp.push
				(
					//"\""+mem+"\":"+this[mem].toString().replace(/ {0,}[\t\r\v\f\n] {0,}/g,"").replace(/\s{2,}/g, " ")
				);
				break;
			case "object" :
				temp.push(this[mem]===null
				? "\"" + mem + "\":" + this[mem]
				: "\"" + mem + "\":" + this[mem].toJSON());
				break;
			case "undefined" :
			case "number"    :
			case "boolean"   : temp.push("\""+mem+"\":"+this[mem]); break;
			case "string"    :
			default          : temp.push("\""+mem+"\":\""+this[mem].replace(/"/g,'\\"')+"\""); break;
		}
		if(typeof(this[mem] == "function")){
			
		}else{
			temp.push(", ");
		}
		
	}
	temp.push("}");
	var strTemp = temp.join("").replace(/, {0,}$/,"");
	return strTemp;
}
*/
/*
函数:   toJSON
说明:   Array转成Json对应
参数:   Object
返回值: Json
*/
/*
Array.prototype.toJSON = function()
{
	var temp = ["["];
	for(var i=0; i < this.length; i++)
	{
		switch(typeof(this[i]))
		{
			case "function":
				temp.push
				(
					this[i].toString().replace(/ {0,}[\t\r\v\f\n] {0,}/g,"").replace(/\s{2,}/g, " ")
				);
				break;
			case "undefined" :
			case "number"    :
			case "boolean"   : temp.push(""+this[i]); break;
			case "object"    : temp.push(this[i]===null ? this[i] : this[i].toJSON1()); break;
			case "string"    :
			default          : temp.push("\""+this[i].replace(/"/g,'\\"')+"\""); break;
		}
		temp.push(i==(this.length-1) ? "" : ", ");
	}
	temp.push("]");
	return temp.join("");
}
*/
/*
函数:   unJSON
说明:   由JSON还原对象
参数:   
返回值: Object
*/
/*
String.prototype.unJSON = function()
{
	var strToObj = "strToObj="+this;
	try{eval(strToObj);}catch(e){strToObj=this;}
	return strToObj;
}
*/

/**
*函数:   getJsonString
*说明:   将JSON转换成字符串
*参数:   json对像
*返回值: 字符串
*/
function getJsonString(jsonObj){
	var sA = [];
	(function(o){
	   var isObj=true;
	   if(o instanceof Array)
	      isObj=false;
	   else if(typeof o!='object'){
	      if(typeof o=='string'){
	          sA.push("'"+o+"'");
	      }else{
	          sA.push(o);
	      }
	      return;
	   }
	   if(null != o && o!="null" && o!=""){
		   sA.push(isObj?"{":"[");
	   }else{ 
		   sA.push("'");
	   }
	   for(var i in o){
	    if(o.hasOwnProperty(i) && i!='prototype'  && i!= "toJSON"){
	     if(isObj)
	    	 sA.push("'");
	     
	      sA.push(i+"'"+':');
	      if(!IsSpace(o[i])){
		       arguments.callee(o[i]);
	      }else{
		       sA.push("''");   
	      }
	       sA.push(',');   
	    }
	   }
	   if(null != o && o!="null" && o!=""){
		   sA.push(isObj?"}":"]");
	   }else{ 
		   sA.push("'");
	   }
	})(jsonObj);
	return descape(sA.slice(0).join('').replace(/,\}/g,'}').replace(/,\]/g,']'));
}