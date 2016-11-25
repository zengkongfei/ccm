<%
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	java.util.Date currentTime = new java.util.Date();//得到当前系统时间

	String str_date1 = formatter.format(currentTime); //将日期时间格式化 
	//String str_date2 = currentTime.toString(); //将Date型日期时间转换成字符串形式
%>
<%=str_date1%>