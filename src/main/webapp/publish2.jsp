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
.STYLE1 {
	color: #666666;
	font-size: 16px;
}
.STYLE5 {color: #666666}
.STYLE6 {
	color: #8FD16B;
	font-family: "宋体";
	font-size: 18px;
	font-weight: bold;
}

body {
	margin: 0;
	padding: 0;
	background: #dcdcde;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	color: #A4E4F5;
}

.STYLE7 {color: #dcdcde}
</style>

<jsp:directive.page import="java.util.List" />


</head>
<body>


	<div align="center"><a href="http://www.163.com" target="_blank"><img src="${fenxiang}" width="100%"  height="100" hspace="0" vspace="0"/></a></div>

	<table width="95%"  border="0" align="center" bgcolor="#dcdcde">
		<tr>
			<td width="100%" align="center" valign="top">

				<c:forEach var="dataHappened" items="${dataList}">
					<table width="100%">
						<tr>
						
							<td align="left" valign="middle" bgcolor="#dcdcdb">
							<span class="STYLE6">DAY${dataHappened.daySeq}</span></td>
						</tr>
					</table>
					

						<c:forEach var="record" items="${dataHappened.recordList}">
						<table width="100%" bgcolor="#FFFFFF">
									<tr  height="40px">
										<td width="8%" align="left" valign="middle" bordercolor="#A7C0DC">
										<img src="${record.iconUrl}"  alt="icon" hspace="0" vspace="0" align="top" /> </td>
										<td width="38%" align="left" valign="middle"><span class="STYLE1">${record.author}</span></td>
										<td width="2%" align="left" valign="middle">&nbsp;</td>
										<td width="3%" align="left" valign="middle">&nbsp;</td>
										<td align="left" valign="middle">&nbsp;</td>
									</tr>

									<c:forEach var="imgItem" items="${record.picUrls}">
										<tr>
											<td colspan="5" >
											  <div align="center">
											    <table width="95%" border="0">
                                                  <tr>
                                                    <td><div align="center"><img src="${imgItem}" alt="这位游客真懒，不留下任何照片" width="100%" height="100%" hspace="0" vspace="0" align="absmiddle"/></div></td>
                                                  </tr>
                                                </table>
									      </div></td>
										</tr>
										<tr>
										  <td colspan="5"><span class="STYLE5"></span> </td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="5"><span class="STYLE1">${record.text}</span></td>
									</tr>
									<tr>
									  <td colspan="5"><hr /></td>
									</tr>
									<tr>
										<td colspan="4" align="left" valign="middle">
										<span class="STYLE1">${record.time} </span></td>
										<td width="49%" align="right" valign="middle">
										<span class="STYLE1">${record.location}</span></td>
									</tr>
						  </table>
								<table width="100%" bgcolor="#dcdcde" >
									<tr><td><span class="STYLE7">sdfewf</span></td>
									</tr>
						  </table>
						</c:forEach>
				  
				  <p class="STYLE7">ewfewfewfew</p>
			  </c:forEach>
			</td>
		</tr>
	</table>


</body>
</html>