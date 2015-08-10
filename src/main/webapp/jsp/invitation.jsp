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
<meta name="keywords" content="旅记" />
<meta name="description" content="旅记" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="com.netease.yixing.view.DayHappened" />
<jsp:directive.page import="com.netease.yixing.view.Record" />

<title>旅记邀请</title>


<script language="javascript" type="text/javascript">
	function clearText(field) {
		if (field.defaultValue == field.value)
			field.value = '';
		else if (field.value == '')
			field.value = field.defaultValue;

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
.STYLE16 {
	font-size: xx-large;
	color: #eeeeee;
}
.STYLE18 {font-size: 20px}
.STYLE20 {color: #FFFFFF}
</style>

<jsp:directive.page import="java.util.List" />


</head>
<body bgcolor="#eeeeee">

<p>&nbsp;</p>
<table width="100%"  height="100%" border="0" align="center" bgcolor="#eeeeee"  cellpadding="0" cellspacing="0"  >
		<tr>
		  <td colspan="2" align="center" valign="top">
				<table width="80%" border="0" align="center" cellpadding="0" cellspacing="1">
					<tr height="60%" >
						<td  colspan="3"><div align="center"><img src="../image/web_pic.png" width="100%" height="100%" /></div></td>
					</tr>
					<tr>
						<td height="%15" colspan="3"  bgcolor="#FFFFFF"><div align="center">
						  <p class="STYLE18">${title}</p>
						</div></td>
					</tr>			
					<tr height="%10">
						<td colspan="2" nowrap="nowrap"    bgcolor="#FFFFFF">
					    &nbsp;&nbsp;&nbsp;&nbsp;${time}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="../image/web_number.png" width="18" height="18" />${number}&nbsp;&nbsp;</td>
					</tr>
			
					<tr height="5%">
						<td colspan="2" bordercolor="#eeeeee"><span class="STYLE16">ewfewfew</span></td>
					</tr>
		
					<tr height="20%" >
							<td width="50%"><div align="left"><a href="FourthIOSMiniProject://${id}"><img height="90%" width="90%" src="../image/number_accept.png" /></a></div></td>
	                        <td  width="50%"><div align="right"><a href="FourthIOSMiniProject://$suijima"><img height="90%" width="90%" src="../image/web_download.png" /></a></div></td>
					</tr>
			</table>
		  </td>
		</tr>



</table>



	<p>&nbsp;</p>
</body>
</html>