	function showHtmlcallJava(){
		var str = window.jsObj.HtmlcallJava();
		alert(str);
	}
	
	function showHtmlcallJava2(){
		var str = window.jsObj.HtmlcallJava2("IT-homer blog");
		alert(str);
	}
	
	function showFromHtml(){
		document.getElementById("id_input").value = "Java call Html";
	}
	
	function showFromHtml2( param ){
		document.getElementById("id_input2").value = "Java call Html : " + param; 
	}