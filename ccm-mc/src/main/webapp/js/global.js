/* This function is used to change the style class of an element */
function swapClass(obj, newStyle) {
    obj.className = newStyle;
}

function isdigit(s)
	{
	 	var r,re;
	 	re = /\d*/i;    //\d表示数字,*表示匹配多个数字
	 	r = s.match(re);

	 	return (r==s)?1:0;
	}
	
function isNumber(s)
{
        var r,re;
	 	
	 	var re = /^[0-9]+.?[0-9]*$/;   
	 	
	 	r = s.match(re);

	 	return (r==s)?1:0;
	
}
	
    //比较时间 格式 yyyy-mm-dd hh:mi:ss
     
    function comptime(beginTime,endTime){
	
	    //var beginTimes=beginTime.substring(0,10).split('-');
	    //var endTimes=endTime.substring(0,10).split('-');
	
	    //beginTime=beginTimes[1]+'-'+beginTimes[2]+'-'+beginTimes[0]+' '+beginTime.substring(10,19);
	    //endTime=endTimes[1]+'-'+endTimes[2]+'-'+endTimes[0]+' '+endTime.substring(10,19);
    	//var a =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000;
    	
	    var endDateTime = new Date(Date.parse(endTime.replace(/-/g,"/")));
	    
	    var beginDateTime = new Date(Date.parse(beginTime.replace(/-/g,"/")));
	    
	     var a =(endDateTime-beginDateTime)/3600/1000;
	 
	    
        var flag = 0; 
	    if(a<0){

	    	flag = -1;

	    }else if (a>0){
	    	flag = 1;
	     
	    }else if (a==0){
	    	flag = 0
	       
	    }else{
		    
	       //return 'exception'
	    }

	    return flag;
    }
    
    
function isUndefined(value) {   
    var undef;   
    return value == undef; 
}

function checkAll(theForm) { // check all the checkboxes in the list
  for (var i=0;i<theForm.elements.length;i++) {
    var e = theForm.elements[i];
        var eName = e.name;
        if (eName != 'allbox' && 
            (e.type.indexOf("checkbox") == 0)) {
            e.checked = theForm.allbox.checked;        
        }
    } 
}

/* Function to clear a form of all it's values */
function clearForm(frmObj) {
    for (var i = 0; i < frmObj.length; i++) {
        var element = frmObj.elements[i];
        if(element.type.indexOf("text") == 0 || 
                element.type.indexOf("password") == 0) {
                    element.value="";
        } else if (element.type.indexOf("radio") == 0) {
            element.checked=false;
        } else if (element.type.indexOf("checkbox") == 0) {
            element.checked = false;
        } else if (element.type.indexOf("select") == 0) {
            for(var j = 0; j < element.length ; j++) {
                element.options[j].selected=false;
            }
            
            if(element.options!=null && element.options.length>0)
            	element.options[0].selected=true;
        }
    } 
}

/* Function to get a form's values in a string */
function getFormAsString(frmObj) {
    var query = "";
    for (var i = 0; i < frmObj.length; i++) {
        var element = frmObj.elements[i];
        if (element.type.indexOf("checkbox") == 0 || 
            element.type.indexOf("radio") == 0) { 
            if (element.checked) {
                query += element.name + '=' + escape(element.value) + "&";
            }
        } else if (element.type.indexOf("select") == 0) {
            for (var j = 0; j < element.length ; j++) {
                if (element.options[j].selected) {
                    query += element.name + '=' + escape(element.value) + "&";
                }
            }
        } else {
            query += element.name + '=' 
                  + escape(element.value) + "&"; 
        }
    } 
    return query;
}

/* Function to hide form elements that show through
   the search form when it is visible */
function toggleForm(frmObj, iState) // 1 visible, 0 hidden 
{
    for(var i = 0; i < frmObj.length; i++) {
        if (frmObj.elements[i].type.indexOf("select") == 0 || frmObj.elements[i].type.indexOf("checkbox") == 0) {
            frmObj.elements[i].style.visibility = iState ? "visible" : "hidden";
        }
    } 
}

/* Helper function for re-ordering options in a select */
function opt(txt,val,sel) {
    this.txt=txt;
    this.val=val;
    this.sel=sel;
}

/* Function for re-ordering <option>'s in a <select> */
function move(list,to) {     
    var total=list.options.length;
    index = list.selectedIndex;
    if (index == -1) return false;
    if (to == +1 && index == total-1) return false;
    if (to == -1 && index == 0) return false;
    to = index+to;
    var opts = new Array();
    for (i=0; i<total; i++) {
        opts[i]=new opt(list.options[i].text,list.options[i].value,list.options[i].selected);
    }
    tempOpt = opts[to];
    opts[to] = opts[index];
    opts[index] = tempOpt
    list.options.length=0; // clear
    
    for (i=0;i<opts.length;i++) {
        list.options[i] = new Option(opts[i].txt,opts[i].val);
        list.options[i].selected = opts[i].sel;
    }
    
    list.focus();
} 

/*  This function is to select all options in a multi-valued <select> */
function selectAll(elementId) {
    var element = document.getElementById(elementId);
    len = element.length;
    if (len != 0) {
        for (i = 0; i < len; i++) {
            element.options[i].selected = true;
        }
    }
}

/* This function is used to select a checkbox by passing
 * in the checkbox id
 */
function toggleChoice(elementId) {
    var element = document.getElementById(elementId);
    element.checked = !element.checked;
}

/* This function is used to select a radio button by passing
 * in the radio button id and index you want to select
 */
function toggleRadio(elementId, index) {
    var element = document.getElementsByName(elementId)[index];
    element.checked = true;
}

/* This function is used to open a pop-up window */
function openWindow(url, winTitle, winParams) {
    winName = window.open(url, winTitle, winParams);
    winName.focus();
}


/* This function is to open search results in a pop-up window */
function openSearch(url, winTitle) {
    var screenWidth = parseInt(screen.availWidth);
    var screenHeight = parseInt(screen.availHeight);

    var winParams = "width=" + screenWidth + ",height=" + screenHeight;
        winParams += ",left=0,top=0,toolbar,scrollbars,resizable,status=yes";

    openWindow(url, winTitle, winParams);
}

/* This function is used to set cookies */
function setCookie(name,value,expires,path,domain,secure) {
  document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires.toGMTString() : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
}

/* This function is used to get cookies */
function getCookie(name) {
    var prefix = name + "=";
    var start = document.cookie.indexOf(prefix);

    if (start==-1) {
        return null;
    }
    
    var end = document.cookie.indexOf(";", start+prefix.length);
    if (end==-1) {
        end=document.cookie.length;
    }

    var value=document.cookie.substring(start+prefix.length, end);
    return unescape(value);
}

/* This function is used to delete cookies */
function deleteCookie(name,path,domain) {
  if (getCookie(name)) {
    document.cookie = name + "=" +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}

// This function is for stripping leading and trailing spaces
String.prototype.trim = function () {
    return this.replace(/^\s*(\S*(\s+\S+)*)\s*$/, "$1");
};

// This function is used by the login screen to validate user/pass
// are entered. 
function validateRequired(form) {                                    
    var bValid = true;
    var focusField = null;
    var i = 0;                                                                                          
    var fields = new Array();                                                                           
    oRequired = new required();                                                                         
                                                                                                        
    for (x in oRequired) {                                                                              
        if ((form[oRequired[x][0]].type == 'text' || form[oRequired[x][0]].type == 'textarea' || form[oRequired[x][0]].type == 'select-one' || form[oRequired[x][0]].type == 'radio' || form[oRequired[x][0]].type == 'password') && form[oRequired[x][0]].value == '') {
           if (i == 0)
              focusField = form[oRequired[x][0]]; 
              
           fields[i++] = oRequired[x][1];
            
           bValid = false;                                                                             
        }                                                                                               
    }                                                                                                   
                                                                                                       
    if (fields.length > 0) {
       focusField.focus();
       alert(fields.join('\n'));                                                                      
    }                                                                                                   
                                                                                                       
    return bValid;                                                                                      
}

// This function is a generic function to create form elements
function createFormElement(element, type, name, id, value, parent) {
    var e = document.createElement(element);
    e.setAttribute("name", name);
    e.setAttribute("type", type);
    e.setAttribute("id", id);
    e.setAttribute("value", value);
    parent.appendChild(e);
}

function confirmDelete(obj) {   
    var msg = "Are you sure you want to delete this " + obj + "?";
    ans = confirm(msg);
    return ans;
}

function highlightTableRows(tableId) {
    var previousClass = null;
    var table = document.getElementById(tableId); 
    var startRow = 0;
    // workaround for Tapestry not using thead
    if (!table.getElementsByTagName("thead")[0]) {
	    startRow = 1;
    }
    var tbody = table.getElementsByTagName("tbody")[0];
    var rows = tbody.getElementsByTagName("tr");
    // add event handlers so rows light up and are clickable
    for (i=startRow; i < rows.length; i++) {
        rows[i].onmouseover = function() { previousClass=this.className;this.className+=' over' };
        rows[i].onmouseout = function() { this.className=previousClass };
        rows[i].onclick = function() {
            var cell = this.getElementsByTagName("td")[0];
            var link = cell.getElementsByTagName("a")[0];
            if (link.onclick) {
                call = link.getAttribute("onclick");
                if (call.indexOf("return ") == 0) {
                    call = call.substring(7);
                } 
                // this will not work for links with onclick handlers that return false
                eval(call);
            } else {
                location.href = link.getAttribute("href");
            }
            this.style.cursor="wait";
            return false;
        }
    }
}

function highlightFormElements() {
    // add input box highlighting
    addFocusHandlers(document.getElementsByTagName("input"));
    addFocusHandlers(document.getElementsByTagName("textarea"));
}

function addFocusHandlers(elements) {
    for (i=0; i < elements.length; i++) {
        if (elements[i].type != "hidden" && elements[i].type != "button" && elements[i].type != "submit" &&
            elements[i].type != "reset" && elements[i].type != "checkbox" && elements[i].type != "radio") {
            if (!elements[i].getAttribute('readonly') && !elements[i].getAttribute('disabled')) {
                elements[i].onfocus=function() {this.style.backgroundColor='#ffd';this.select()};
                elements[i].onmouseover=function() {this.style.backgroundColor='#ffd'};
                elements[i].onblur=function() {this.style.backgroundColor='';}
                elements[i].onmouseout=function() {this.style.backgroundColor='';}
            }
        }
    }
}

function radio(clicked){
    var form = clicked.form;
    var checkboxes = form.elements[clicked.name];
    if (!clicked.checked || !checkboxes.length) {
        clicked.parentNode.parentNode.className="";
        return false;
    }

    for (i=0; i<checkboxes.length; i++) {
        if (checkboxes[i] != clicked) {
            checkboxes[i].checked=false;
            checkboxes[i].parentNode.parentNode.className="";
        }
    }

    // highlight the row    
    clicked.parentNode.parentNode.className="over";
}
/*
window.onload = function() {
   highlightFormElements();
    if ($('successMessages')) {
        new Effect.Highlight('successMessages');
        // causes webtest exception on OS X : http://lists.canoo.com/pipermail/webtest/2006q1/005214.html
        // window.setTimeout("Effect.DropOut('successMessages')", 3000);
    }
    if ($('errorMessages')) {
        new Effect.Highlight('errorMessages');
    }
   
    if ($("primary-nav")) {
        var navItems = $("primary-nav").getElementsByTagName("li");
    
        for (var i=0; i<navItems.length; i++) {
            if (navItems[i].className == "menubar") {
                navItems[i].onmouseover=function() { this.className += " over"; }
                navItems[i].onmouseout=function() { this.className = "menubar"; }
            }
        }
    }
}

*/


function   customerCss(o){  
     alert("asddddddddddd");
  	  with(o){ 
	    width="100%" 
	    align="center" 
	    cellPadding="3" 
	    cellSpacing="0" 
	    border="2"  
	    borderColorDark="#ffffff" 
	    borderColorLight="B9B9B9" 
	    borderColor="#ffffff"
	  };   
}

function fucCheck(INDEX) {
    var i, j, strTemp;
    strTemp = "0123456789";
    for (i = 0; i < INDEX.length; i++) {
        j = strTemp.indexOf(INDEX.charAt(i));
        if (j == -1) {
            //说明有字符不合法        
            return false;
        }
    }
    //说明合法        
    return true;
}

//判断是否是email
function is_email(handle){
 var patternn = /^[a-zA-Z0-9-_\.@]+$/;
 if (!patternn.exec(handle.value)) {
 	return false;
 }
 var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
   if (!pattern.exec(handle.value)) return false;
   return true;
}
//页数验证
function submitKeyClick(){
	if(isNumber($("#jumpPageNo").val()) == 0){
		alert(resources.numberErrorMessage);   
		$("#jumpPageNo").val("");
	}
	if(parseInt($("#jumpPageNo").val().replace(',',''))> parseInt($("#pageCount").val().replace(',',''))){
		alert(resources.numberErrorMessage);   
		$("#jumpPageNo").val($("#pageCount").val());
	}
}
function pageJump(t){
	if($("#jumpPageNo").val()==''){
		alert(resources.numberErrorMessage);   
        return false;
	}
    var page = parseInt(document.getElementById("jumpPageNo").value.replace(',',''));
    var pageCount = parseInt(document.getElementById("pageCount").value.replace(',',''));
    if(!fucCheck(page) || page > pageCount || page < 1){   
        alert(resources.numberErrorMessage);   
        return false;   
    }
    var url=$(t).attr('url');
    if(url.indexOf('javascript:')>-1){
    	url=url.replace(/p',v:'1*/,"p',v:'"+page);
    	eval(url);
    }else{
    	url=url.replace(/p=1*/,"p="+page);
    	window.location=url;
    }
} 
/** 省份求城市，数据从数据库中读取 **/
function getCityByProvince(pid, cid, tid){
	var el = $("#"+tid);
	el.html("<option value=''>--请选择--</option>");
	$.get("landmarks_getCityByProvince.do?city.provinceId="+pid, function(data){
		
		var json = eval("("+data+")");
		if(json.success){
			el = $("#"+tid);
			citylist = eval("("+json.message+")");
			for(var i=0;i<citylist.length;++i){
			
				var sel = "";
				
				if(((cid==null || cid=='') && i==0) || citylist[i].cityId == cid) sel = "selected";
				
				var option	= '<option value="'+citylist[i].cityId+'"' + sel +'>'+citylist[i].cityName+'</option>';					
			
				el.append(option);
			}
		}
	})
}
