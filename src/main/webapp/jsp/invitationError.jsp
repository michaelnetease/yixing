<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="keywords"
	content="旅记" />
<meta name="description"
	content="旅记" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="com.netease.yixing.view.DayHappened" />
<jsp:directive.page import="com.netease.yixing.view.Record" />

<title>${travelSchedule.title}</title>


<script language="javascript" type="text/javascript">
function clearText(field)
{
    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;

}
</script>
<style type="text/css">
img {
	max-width: 100%;
}

body {
	margin: 0;
	padding: 0;
	

}
</style>

<jsp:directive.page import="java.util.List" />


</head>
<body   background="../image/yaoqinghaoyou_bg.png">
<p>&nbsp;</p>
<table width="300px" height="400px" border="1" align="center" bgcolor="#FFFFFF">
  <tr>
    <td colspan="2" valign="top"><div align="center">
      <p align="left"><strong>错误信息: </strong></p>
      <p align="left">${errorMessage}</p>
    </div></td>
  </tr>
  
</table>



    <p>&nbsp;</p>
</body>
</html>