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
	content="Blog Green, HTML CSS layout, free website template" />
<meta name="description"
	content="旅记" />
<jsp:directive.page import="java.util.List" />

<title>旅记</title>


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
</style>

<jsp:directive.page import="java.util.List" />


</head>
<body bgcolor="#dcdcde">
	<table width="98%"  border="0" align="center" bgcolor="#dcdcde">
		<tr>
			<td width="100%" align="center" valign="top">
				<table bgcolor="#FFFFFF" width="100%">
					<tr>
						<td><div align="center"><a href="http://www.163.com" target="_blank"><img src="${fenxiang}" width="100%" /></a></div></td>
					</tr>
					<tr>
						<td><div align="center">${travelSchedule.title}</div></td>
					</tr>
			  </table> 
				<c:forEach var="data" items="${dataList}">
					<table width="100%" bgcolor="#FFFFFF">
						<tr>
							<td>
								<div align="left">
									<img src="${data.get('basic').get(0)}"  alt="icon" /> ${data.get("basic").get(1)}
								</div>
							</td>
						</tr>
						<tr>
							<td>${data.get("basic").get(2)} ${data.get("basic").get(3)}</td>
						</tr>
						<c:forEach var="imgItem" items="${data.get('picIds')}">
							<tr>
								<td><div align="center"><img src="${imgItem}" alt="image"/></div></td>
							</tr>
							<tr>
							  <td>&nbsp; </td>
							</tr>
						</c:forEach>
						<tr>
							<td>${data.get("basic").get(4)}</td>
						</tr>
				  </table>
				  <p></p>
			  </c:forEach>
			</td>
		</tr>
	</table>


</body>
</html>