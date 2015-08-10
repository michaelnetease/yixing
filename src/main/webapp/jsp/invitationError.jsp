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

<title>旅记</title>


<script language="javascript" type="text/javascript">
function clearText(field)
{
    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;

}
</script>
<style type="text/css">
imgtest{border-radius:50%
}
imgtest img{ border-radius:50%} 

img {
	max-width: 100%;
	max-height:100%;


}

body {
	margin: 0;
	padding: 0;
	background: #dcdcde;

}
.STYLE12 {color: #dcdcde; font-size: x-small; }
.STYLE21 {
	color: #000000;
	font-size: medium;
}
</style>

<jsp:directive.page import="java.util.List" />


</head>
<body>


	<div align="center"><a href="FourthIOSMiniProject://" target="_blank"><img src="../image/fenxiang.png" width="100%"  height="70px" hspace="0" vspace="0"/></a></div>

	<table cellpadding="0" cellspacing="0" width="95%"  border="0" align="center" bgcolor="#dcdcde">
		<tr>
			<td width="100%" align="center" valign="top">

					<table width="100%" cellpadding="0"  cellspacing="0">
						<tr>
							<td>
							  <span class="STYLE12">6545646456						    </span></td>
						</tr>
					
						<tr>
						
							<td align="left" valign="middle" bgcolor="#dcdcdb"><div align="center"><span class="STYLE21">${errorMessage}</span></div></td>
						</tr>
						<tr>
							<td>
							  <span class="STYLE12">6545646456						    </span></td>
						</tr>
					</table>
			</td>
		</tr>
	</table>


</body>
</html>