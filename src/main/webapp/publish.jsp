
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="stylesheet" type="text/css" media="screen and (max-device-width: 400px)" href="tinyScreen.css" />  
	<meta name="viewport" content="width=device-width, initial-scale=1" />  
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<meta name="format-detection" content="telephone=no"/>
	<jsp:directive.page import="java.util.List"/>
	<jsp:directive.page import="com.netease.yixing.model.TravelRecord"/>
	<jsp:directive.page import="com.netease.yixing.model.TravelSchedule"/>
	<title>旅记</title>
    <style type="text/css">
		<!--
		.STYLE1 {
			font-size: 18px;
			font-weight: bold;
		}
		.STYLE2 {font-family: "宋体"}
		-->
		img { max-width: 100%;} 
		    </style>
</head>

<body>
<table width="100%" border="0" align="center">
  <tr>
    <td> <div align="center" class="STYLE2">
      <h1><strong>${travelSchedule.title}</strong></h1>
    </div></td>
  </tr>
  <tr>
    <td><strong>开始时间：${travelStartTime}</strong></td>
  </tr>
  <tr>
    <td><strong>结束时间：${travelEndTime}</strong></td>
  </tr>
  <c:forEach var="record" items="${recordList}">
	  <tr>
			<td><table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#000000">
			  <tr>
				<td valign="top"><div align="center" valign="top">
				  <p align="left"><img src="${record.pictureKey}" align="top" /></p>
				  <p align="left"><span class="STYLE1">${record.valid}说：</span> </p>
				  <p align="left">${record.text}</p>
			    </div></td>
			  </tr>
			</table></td>
	  </tr>
	  <tr><p></p></tr>
  </c:forEach>
</table>


</body>
</html>
