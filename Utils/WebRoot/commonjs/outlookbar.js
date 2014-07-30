/**
 *  Outlook风格菜单
 *
 * @author 唐露明
 * @version 1.0
 */

/**
 * OutlookBar对象
 * 对象结构
 *   OutlookBar 
 *      OutlookMenu
 *         OutlookMenuItem
 *  
 *   一、批量加载
 *         [
 *		    {	
 *			    menuText:"菜单一",
 *	 		    menuItems:[
 *						    {text:"待办任务",action:"",defaultImage:"",selectedImage:""},
 *						    {text:"流程监控",action:"",defaultImage:"",selectedImage:""}
 *						   ]
 *			},
 *			{	
 *			    menuText:"菜单二",
 *			    menuItems:[
 *						    {text:"待办任务",action:"",defaultImage:"",selectedImage:""},
 *						    {text:"流程监控",action:"",defaultImage:"",selectedImage:""}
 *						   ]
 *			},
 *			{	
 *			    menuText:"菜单三",
 *			    menuItems:[
 *						    {text:"待办任务",action:"",defaultImage:"",selectedImage:""},
 *						    {text:"流程监控",action:"",defaultImage:"",selectedImage:""}
 *						   ]
 *			},
 *			{	
 *			    menuText:"菜单四",
 *			    menuItems:[
 *						    {text:"待办任务",action:"",defaultImage:"",selectedImage:""},
 *						    {text:"流程监控",action:"",defaultImage:"",selectedImage:""}
 *						   ]
 *			}
 *		]
 */
var OutlookBar = function(objName, containerElementId, width, height) {
	this.rootPath = "";
	this.currentExpandMenu = null;
	this.menuQueue = [];
	this.obj = objName;
	this.containerEl = document.getElementById(containerElementId);
	if(typeof this.containerEl=="undefined" || this.containerEl==null){
		throw new Error("id为"+containerElementId+"的未找到");
	}
}
OutlookBar.flyInc = 7;//移动高度

OutlookBar.fly = function(oldMenuItemObj,menuItemObj, menuFlyHeight, contextObject){
	if(menuFlyHeight==0){
		oldMenuItemObj.childNodes[0].style.overflow = "hidden";
		menuItemObj.childNodes[0].style.overflow = "hidden";

		setTimeout(function(){OutlookBar.fly.apply(contextObject,[oldMenuItemObj,menuItemObj, OutlookBar.flyInc, contextObject]);}, 2);
	
	}else{
	  var mHeight = menuFlyHeight+OutlookBar.flyInc;
    mHeight = (mHeight>100)?100:mHeight;
    		
		menuItemObj.style.height = mHeight + "%";
		oldMenuItemObj.style.height = 100-mHeight + "%";
		
		if(mHeight<100){
			setTimeout(function(){OutlookBar.fly.apply(contextObject,[oldMenuItemObj,menuItemObj, mHeight,contextObject]);}, 2);	
		}else{
			oldMenuItemObj.style.display = "none";
			menuItemObj.childNodes[0].style.overflow = "auto";
			contextObject.currentExpandMenu = menuItemObj;
		}
	}
};

OutlookBar.prototype = {
	
	addMenu : function(menu) {
		this.menuQueue.push(menu);
	},
	doExpand:function(evt){
		  var evt = evt || window.event;
			var srcEle = event.srcElement||event.target;
			while(srcEle!=null && srcEle.className!="outlook_menu"){
				srcEle = srcEle.parentNode;
			}
			if(srcEle!=null){
				  srcEle = srcEle.parentNode;
					this.expandMenuItem(srcEle.nextSibling);
			}
	},
	expandMenuItem:function(menuItemNode){
		
		var childMenuItemNode = menuItemNode.childNodes[0];
		childMenuItemNode.style.height = "0%";
		childMenuItemNode.style.display = "block";
		
			if(this.currentExpandMenu!=null && this.currentExpandMenu!=childMenuItemNode){
				var _curMenu = this.currentExpandMenu;
				OutlookBar.fly.apply(this,[_curMenu, childMenuItemNode, 0, this]);
			}else{				
				childMenuItemNode.style.height = "100%";
				this.currentExpandMenu = childMenuItemNode;
			}
		
	},
	
	doResize:function(){
			if(this.currentExpandMenu!=null){
				this.currentExpandMenu.style.height=this.getMenuItemHeight(); 
			}
	},
	getHeight:function(){
		//return (this.height==null)?document.documentElement.clientHeight:this.height;
		return document.documentElement.clientHeight;
	},
	getWidth:function(){
		 return (this.width==null)?document.documentElement.clientWidth:this.width;
	},
	loadMenus:function(menuConfigArray){
		var menuSize = menuConfigArray.length;
		for(var i=0;i<menuSize;i++){
			var menuConfig = menuConfigArray[i];
			var outlookMenu = new OutlookMenu(menuConfig.menuText);
			var menuItemSize = menuConfig.menuItems.length;
			for(var j=0;j<menuItemSize;j++){
				outlookMenu.addItem(new OutlookMenuItem(menuConfig.menuItems[j].text,menuConfig.menuItems[j].action, menuConfig.menuItems[j].target,menuConfig.menuItems[j].defaultImage,menuConfig.menuItems[j].selectedImage));
			}
			this.addMenu(outlookMenu);
		}
		this.showEl();
	},
	getOutlookBarHtml:function(){
		var htmlArray = [];
		var menuSize = this.menuQueue.length;
		for(var i=0;i<menuSize;i++){
			htmlArray.push(this.menuQueue[i].toHtml(this.obj));
		}
		return htmlArray.join("");
	},
	showEl:function(){
		if(this.containerEl){
			var htmlArray = [
			        "<table style=\"width:100%;height:100%;\" cellspacing=0 cellpadding=0 id=\"outlookBarTable\">",
			        this.getOutlookBarHtml(),"</table>"
			     ];
			this.containerEl.innerHTML = htmlArray.join("");
			var firstMenuNode = document.getElementById("outlookBarTable").rows[1].cells[0];
			firstMenuNode.style.display ="block";
			firstMenuNode.style.height = "100%";
			this.currentExpandMenu = firstMenuNode;
		}else{
			alert("container对象未定义");
		}
	},
	getMenuItemHeight:function(){
		var _height =  this.getHeight() - (this.menuQueue.length * 27);//每个菜单项是30的高度 	
		_height = (_height<0)?0:_height;
		return _height;
	}
};

/**
 * ItemMenu对象
 * @param text 菜单项名称
 * @param action 菜单项行为
 * @param defaultImage 默认菜单项图标
 * @param selectedImage 选中菜单项图标
 */
var OutlookMenuItem = function(text,action,target,itemImage,selectedImage) {
	this.text = text;
	this.action = action;
	this.target = target;
	this.itemImage = itemImage;
	this.selectedImage = selectedImage;
};

OutlookMenuItem.prototype = {
	//转成HTML字符串
	toHtml:function(objName){
		var htmlArray = [
		   "<div style=\"white-space:nowrap\">",
		   "<a href=\"",
		   (this.action=="")?"#":this.action,
		   "\" target=\"",this.target,
		   "\" onfocus=\"blur()\">","<img src=\"",
		   this.itemImage==""?"images/outlookbar/menuitem.gif":this.itemImage,
		   "\"/>",this.text,"</a>",
		   "</div>"
		];
		return htmlArray.join("");
	}
};

/**
 * OutlookItem对象
 */
var OutlookMenu = function(text) {
	this.text = text;
	this.itemQueue = [];
	
};
OutlookMenu.prototype = {
	elementIndex:0, 
	identifierPrefix:"ol_menu_",
	//添加Item项
	addItem : function(item) {
		this.itemQueue.push(item);
	},
	doOnClick:function(event, element){
		alert("onclick");
	},
	//获取当前Menu的HTML代码
	getMenuHtml:function(objName){
		var htmlArray = [
		                "<tr><td class=\"outlook_menu\" id=\"",this.identifier,this.elementIndex++,"\">",
		     		    "<a href=\"javascript:void(0)\" ", 
		     		    " onclick=\"",objName,".doExpand(event)\" onfocus=\"blur()\">",
		     		    "<img src=\"images/outlookbar/menu_collapse.gif\"/>",
		     		    this.text,"</a>",
		     		    "</td></tr>"
		     		];
		return htmlArray.join("");
	},
	
	toHtml:function(objName){
		var htmlArray = [];
		htmlArray.push(this.getMenuHtml(objName));
		var itemQueueSize = this.itemQueue.length;
		htmlArray.push("<tr><td class=\"outlook_menu_item\"><div style=\"height:100%;width:100%;overflow:auto\">");
		for(var i=0;i<itemQueueSize;i++){
			htmlArray.push(this.itemQueue[i].toHtml(objName));
		}
		htmlArray.push("</div></td></tr>");
		return htmlArray.join("");
	}
};
