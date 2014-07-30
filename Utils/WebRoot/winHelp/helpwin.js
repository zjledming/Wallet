function openHelpWin(oTargetObj) {
	var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
	if (targetObj.style.display == "none") {
		targetObj.style.display = "";
	} else {
		targetObj.style.display = "none"
	}
}