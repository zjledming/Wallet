		/**
		 * 保存查询条件到cookie中
		 * author:quan.zhou 2010.1.14
		 * modify:wen.zhu 2010.04.15
		 */
		function saveQueryCondition2Cookie(formID,queryTable,uname){
		    var cookieManager = CookieManager;
			var username = uname;
			var djid = formID;
			var tableObj = $(""+queryTable+"");
			cookieManager.setCookie("["+djid+"]USERNAME",username);
			var textObjs = tableObj.getElementsByTagName("input");//document
			var selectObjs = tableObj.getElementsByTagName("select");
		 
			for(var i=0;i<textObjs.length;i++){
			 if(textObjs[i].type!="radio"){  
				//  if(!IsSpace(textObjs[i].value)){
					cookieManager.setCookie("["+djid+"]"+textObjs[i].id,textObjs[i].value);
				  //}
			 }else{
				  
			  var radioName = textObjs[i].name; 
				if(textObjs[i].checked){
				   cookieManager.setCookie("["+djid+"]"+textObjs[i].name,textObjs[i].value);
				}
			 }
			}
			
			for(var i=0;i<selectObjs.length;i++){
			  cookieManager.setCookie("["+djid+"]"+selectObjs[i].id,selectObjs[i].value);
			}
			//保留20个
			DeleteCookie(40);
		}

		  
		/**
		 * 取出cookie中缓存的查询条件值，并设置页面查询条件
		 * author:quan.zhou 2010.1.14
		 */
		function setQueryConditionFromCookie(formID,queryTable,uname){   
			 var cookieManager = CookieManager;
			var djid = formID;
			var tableObj = $(""+queryTable+"");
			var username = uname;
			var cookUserName = cookieManager.getCookie("["+djid+"]USERNAME");
			if(username==cookUserName){
				var textObjs = tableObj.getElementsByTagName("input");
				var selectObjs = tableObj.getElementsByTagName("select");
				for(var i=0;i<textObjs.length;i++){
					if(textObjs[i].type!="radio"){
					  var value = cookieManager.getCookie("["+djid+"]"+textObjs[i].id);
					  if(!IsSpace(value)) textObjs[i].value=value;
					}else{
						var radioName = textObjs[i].name; 
						var radio_value = cookieManager.getCookie("["+djid+"]"+textObjs[i].name);
					//	alert(textObjs[i].value);
						if(radio_value==textObjs[i].value){
							textObjs[i].checked=true;
						}
					}
				}
				for(var i=0;i<selectObjs.length;i++){
					var value = cookieManager.getCookie("["+djid+"]"+selectObjs[i].id);
					if(!IsSpace(value)){
						var options = selectObjs[i].options;
						for(var j=0;j<options.length;j++){
							if(options[j].value==value){
								//options[j].selected=true;
									options[j].setAttribute("selected","selected");
								break;
							}
						}
					}
				}
			}
		}

		CookieManager = {
			  setCookie : function(keyName, keyValue){	  
			  var Then = new Date();
			  var time = Then.getTime();
				  Then.setTime(time+20*60000);//保存20分钟 //1*24*60*60*1000); //保存有效期1天
			  var cookieName =  keyName+"$"+time;
			  var myCookie = document.cookie;
				  DelCookie(keyName);//删除当前cookie
				  if(!IsSpace(keyValue)){
					document.cookie =cookieName+ "=" + escape (keyValue) + ";expires="+ Then.toGMTString();	
				  }
			},
			getCookie : function(keyName){
				  var myCookie = document.cookie;
			  var keyStart = myCookie.indexOf(keyName + "$");
			  if (keyStart != -1) { 
				  keyStart_1 = myCookie.indexOf("=",keyStart+13)+1; 
				  var end = myCookie.indexOf(";",keyStart_1); 
				  if (end == -1) { 
					  end = myCookie.length;
				  } 
				  var keyValue = unescape(myCookie.substring(keyStart_1,end)); 
				  if (keyValue!=null){
					return keyValue; 
				  }
			  }
			  return ""; 
			},
			setDefaultApplyType: function(ecId, applyType){
				var keyName = "defaultApplyType_"+ecId;
				CookieManager.setCookie(keyName,applyType);
			},	
			getDefaultApplyType: function(ecId){
				var keyName = "defaultApplyType_"+ecId;
				return CookieManager.getCookie(keyName);
			}
		}

		//删除cookie
		function DelCookie(keyName){
		   var aCookie = getCookieArray();
		   var cookieName;
		   var index;
		   for(i=0;i<aCookie.length;i++){
			   if((index = aCookie[i].indexOf(keyName))!=-1){
				 cookieName = aCookie[i].substring(0, aCookie[i].indexOf("="));
				delCookieFun(cookieName);
			   }
		   }  
		}
		function delCookieFun(cookieName){
			 document.cookie = cookieName + "=; expires=Fri, 31 Dec 1999 23:59:59 GMT;";
		}
		/*获取cookie数组*/
		function getCookieArray(){
			var aCookie = document.cookie.split("; ");
			return aCookie;
		}
		/*获取 $ 到 = 号之间的数值*/
		function getCookieTimes(){
			var aCookie = getCookieArray();
			var cookieTimes = [];
			var index = 0;
			for(i=0;i<aCookie.length;i++){
				if(aCookie[i].indexOf("$")!=-1&&aCookie[i].indexOf("$")<aCookie[i].indexOf("=")){
				 cookieTimes[index]=aCookie[i].substring(aCookie[i].indexOf("$")+1,aCookie[i].indexOf("="));
				 index++;
				}
			}
			if(index>1){
				var tempA;
				var tempB;
				for(i=0;i<cookieTimes.length;i++){
					for(j=i+1;j<cookieTimes.length;j++){
						var tempA = cookieTimes[i];
						var tempB = cookieTimes[j];
						if(tempA<tempB){
							 cookieTimes[i] = tempB;
							 cookieTimes[j] = tempA;
						}
					}
				}
			}
			return cookieTimes;
		}
		function DeleteCookie(lastIndex){
			//alert("dele保留："+lastIndex);
			var cookieTimes = getCookieTimes();
			var cookieArray = getCookieArray();
			var delCookieIndexs = new Array();
			var index=lastIndex>49?49:lastIndex;
			if(cookieTimes.length>index){
				for(i=index-1;i<cookieTimes.length;i++){
					delCookieIndexs.push(cookieTimes[i]+";");
				}
			}
			if(delCookieIndexs!=null){
			  var delTimesStr = delCookieIndexs.join("");
			  var cookie;
			  var cookieName;
				for(i=0;i<cookieArray.length;i++){
				  cookie = cookieArray[i];
					if(delTimesStr.indexOf(cookie.substring(cookie.indexOf("$")+1,cookie.indexOf("=")))>0){
					   cookieName = (cookie.substring(0,cookie.indexOf("=")));
					   delCookieFun(cookieName);
					}
				}
			}
		}

		/*判断是否使用cookie
		 *只有由上一个页面返回时才调用(url后无参数则认为是由上一个页面返回)
		 */
		function isUseredCookie(formID,tableID,uname){

			var upUrl=document.referrer;
			var djid=creator_getQueryString("djid");
	
			if(djid!="null"&&djid!=null)
			if(!IsSpace(upUrl)&&upUrl.indexOf(djid)==-1){
			   setQueryConditionFromCookie(creator_getQueryString("djid"),"tbody",uname);
			   return true;
			}
			   return false;
	
		}