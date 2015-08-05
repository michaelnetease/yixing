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
</style>

<jsp:directive.page import="java.util.List" />


</head>
<body bgcolor="#dcdcde">
	<table width="98%"  border="0" align="center" bgcolor="#dcdcde">
		<tr>
			<td width="100%" align="center" valign="top">
				<table bgcolor="#FFFFFF" width="100%">
					<tr>
						<td><div align="center"><a href="http://www.163.com" target="_blank"><img src="${fenxiang}" width="100%"  height="80px"/></a></div></td>
					</tr>

			  </table> 
				<c:forEach var="data" items="${dataList}">
					<table width="100%" bgcolor="#FFFFFF">
						<tr  height="40px">
							<td align="left" valign="middle">
							<img src="${data.get('basic').get(0)}"  alt="icon" /> </td>
					        <td align="left" valign="middle"><span class="STYLE1">${data.get("basic").get(1)}</span></td>
					        <td align="left" valign="middle">&nbsp;</td>
					        <td align="left" valign="middle">&nbsp;</td>
					        <td align="left" valign="middle">&nbsp;</td>
						</tr>

						<c:forEach var="imgItem" items="${data.get('picIds')}">
							<tr>
								<td colspan="5"><div align="center"><img src="${imgItem}" alt="这位游客真懒，不留下任何照片"/></div></td>
							</tr>
							<tr>
							  <td colspan="5"><span class="STYLE5"></span> </td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="5"><span class="STYLE1">${data.get("basic").get(4)}</span></td>
						</tr>
						<tr>
						  <td colspan="5"><hr /></td>
						</tr>
						<tr>
							<td width="50%" colspan="4" align="left" valign="middle">
						    <span class="STYLE1">${data.get("basic").get(2)} </span></td>
						    <td width="50%" align="right" valign="middle">
					        <span class="STYLE1">${data.get("basic").get(3)}</span></td>
						</tr>
				  </table>
				  <p></p>
			  </c:forEach>
			</td>
		</tr>
	</table>


</body>
</html>