/**
 * 功能：创建鼠标提示消息层
 */

/*
 * 功能：获得或设置提示消息层的ID，显示或隐藏鼠标提示消息层
 * 参数：tipDiv提示消息层的ID
 * 方法：getTipDivId()获得提示消息层的ID
 *       setTipDivId(tipDivId)设置提示消息层的ID
 *       show()显示提示消息层
 *       hide()隐藏提示消息层
 * 用法：设置提示消息层的ID：tipDiv.getTipDivId()
 *       设置提示消息层的ID：tipDiv.setTipDivId(tipDivId)
 *       显示提示消息层：tipDiv.show()
 *       隐藏提示消息层：tipDiv.hide();      
 */
var tipDiv = {
	//提示消息层的ID
	tipDivId : "null",
	//获得提示消息层的ID
	getTipDivId : function () {
		return this.tipDivId;
	},
	//设置提示消息层的ID
	setTipDivId : function (tipDivId) {
		this.tipDivId = tipDivId;
	},
	//显示提示消息层
	show : function () { 
		var tip = document.getElementById(this.getTipDivId());
		//if(event.srcElement.innerText!=null&&event.srcElement.innerText!="") {
		with(tip.style){ 
			//设置提示层的X坐标为鼠标位置偏右10象素 
			posLeft=document.body.scrollLeft+event.x+10; 
			//设置提示层的Y坐标为鼠标位置偏下10象素 
			posTop=document.body.scrollTop+event.y+10;
			if(event.srcElement.tagName=="INPUT") {
				oTip.innerText = document.getElementById(event.srcElement.id).value;
			}
			else {
				//设置提示层内的文字为对象属性值 
				oTip.innerText=event.srcElement.innerText; 
			}
			if(oTip.innerText!=null&&oTip.innerText!=""){
				//以块元素方式显示提示层
				display="block";
			}
		}
	},
	//隐藏提示消息层
	hide : function () {
		var tip = document.getElementById(this.getTipDivId());
		//清空提示层内文本 
		tip.innerText="";  
		//隐藏提示层 
		tip.style.display="none"; 
	}
}

/*
 * 功能：创建鼠标提示消息层
 * 用法：创建鼠标提示消息层：windowInit()
 */
function windowInit(){
	//创建一个DIV对象，并将句柄赋给tip，这个是用作显示提示文字的层 
	var tip=document.createElement("DIV");  
	//设置DIV对象的id为oTip，方便在脚本中控制         
	tip.id="oTip";       
	with(tip.style){ 
		//设置对象为绝对定位，因为提示层的位置是随鼠标位置变化的，所以必须为绝对定位的对象 
		position="absolute";   
		//设置对象的边框为1象素宽并且颜色为灰色(#363636)的实线 
		border="1px solid #363636";   
		//设置对象的背景颜色为淡黄色(#F8F8E0) 
		backgroundColor="#F8F8E0";  
		//设置对象内的字体为Tahoma，大小为12象素 
		font="normal 12px Tahoma"; 
		//设置对象上右下左四边的填充值，单位象素 
		padding="1 3 1 3"; 
		display="none"; 
	} 
	//给文档区域追加子元素tip
	document.body.appendChild(tip);
	//设置提示消息层tip的ID  
	tipDiv.setTipDivId(tip.id);  
};

//将windowInit()方法绑定到表单的window.onload事件
window.attachEvent("onload", windowInit);
