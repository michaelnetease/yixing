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
imgtest{border-radius:50%
}
imgtest img{ border-radius:50%} 

img {
	max-width: 100%;
	max-height:100%;


}


.STYLE1 {
	color: #666666;
	font-size: 16px;
}
.STYLE5 {color: #666666}

body {
	margin: 0;
	padding: 0;
	background: #dcdcde;

}
.STYLE12 {color: #dcdcde; font-size: x-small; }
.STYLE14 {color: #dcdcde; font-size: xx-small; }
.STYLE15 {color: #FFFFFF}
.STYLE18 {
	font-size: xx-small;
	color: #FFFFFF;
}
.STYLE20 {font-size: 20px}
</style>

<jsp:directive.page import="java.util.List" />


</head>
<body>


	<div align="center"><a href="http://www.163.com" target="_blank"><img src="${fenxiang}" width="100%"  height="80" hspace="0" vspace="0"/></a></div>

	<table cellpadding="0" cellspacing="0" width="95%"  border="0" align="center" bgcolor="#dcdcde">
		<tr>
			<td width="100%" align="center" valign="top">

				<c:forEach var="dataHappened" items="${dataList}">
					<table width="100%" cellpadding="0"  cellspacing="0">
						<tr>
							<td>
							  <span class="STYLE12">6545646456						    </span></td>
						</tr>
						<tr>
						
							<td align="left" valign="middle" bgcolor="#dcdcdb"><img src="../image/day${dataHappened.daySeq}.png" /></td>
						</tr>
						<tr>
							<td>
							  <span class="STYLE12">6545646456						    </span></td>
						</tr>
					</table>
					

						<c:forEach var="record" items="${dataHappened.recordList}">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
									<tr>
										<td colspan="2" bgcolor="#FFFFFF">
										  <span class="STYLE18">dsfew									    </span></td>
									</tr>
									<tr   valign="middle">
										<td align="left"  bordercolor="#A7C0DC"><div align="center" class="imgtest"><img src="${record.iconUrl}"   alt=<img  src="../image/web_number.png"/>   style="border-radius:50px"/></div></td>
									    <td align="left"  bordercolor="#A7C0DC">${record.author}</span></td>
									    <td align="left"  bordercolor="#A7C0DC"><span class="STYLE20"></td>
									    <td align="left"  bordercolor="#A7C0DC">&nbsp;</td>
									</tr>

									<c:forEach var="imgItem" items="${record.picUrls}">
							
														<tr>
															<td colspan="4" >
															  <div align="center">
																<table width="90%" border="0" cellpadding="0" cellspacing="0">
																  <tr valign="top">
																	<td valign="top"><div  >
																	  <div align="center"><img src="${imgItem}" hspace="0" vspace="0"  align="top"/></div>
																	</div></td>
																	<br />
																  </tr>
																</table>
														  </div></td>
														</tr>
									
										<tr>
										  <td colspan="4"><span class="STYLE5"></span> </td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="4" bgcolor="#FFFFFF"><span class="STYLE15">34356454</span></td>
									</tr>
									<tr>
										<td colspan="4"><span class="STYLE1">&nbsp;&nbsp;${record.text}</span></td>
									</tr>
									<tr>
									  <td colspan="4"><hr /></td>
									</tr>
									<tr>
										<td width="7%" colspan="2" align="left" valign="middle" nowrap="nowrap">
										<span class="STYLE1">&nbsp;&nbsp;${record.time} </span></td>
										<td width="93%" colspan="2" align="right" valign="middle">
										<span class="STYLE1">${record.location}&nbsp;&nbsp;</span></td>
									</tr>
						  </table>
						  <table width="100%" bgcolor="#dcdcde" cellpadding="0"  cellspacing="0">
									<tr><td><span class="STYLE14">sdfewf</span></td>
									</tr>
						  </table>
						</c:forEach>

			  </c:forEach>
			</td>
		</tr>
	</table>


</body>
</html>