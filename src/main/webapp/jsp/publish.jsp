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
.STYLE21 {color: #333333}
</style>

<jsp:directive.page import="java.util.List" />


</head>
<body>


	<div align="center"><a href="FourthIOSMiniProject://" target="_blank"><img src="${fenxiang}" width="100%"  height="70px" hspace="0" vspace="0"/></a></div>

	<table cellpadding="0" cellspacing="0" width="90%"  border="0" align="center" bgcolor="#dcdcde">
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
										<td colspan="7" bgcolor="#FFFFFF">
										  <span class="STYLE18">dsfew									    </span></td>
									</tr>


									<tr>
										<td  align="center">
										<table width="90%" border="0">
                                          <tr>
                                            <td width="16%"><img src="${record.iconUrl}"   alt="I am" style="border-radius:50px"/> </td>
                                            <td width="66%"><span class="STYLE21">${record.author}</span></td>
                                            <td width="6%">&nbsp;</td>
                                            <td width="6%">&nbsp;</td>
                                            <td width="6%">&nbsp;</td>
                                          </tr>
                                        </table></td>
									</tr>

									<c:forEach var="imgItem" items="${record.picUrls}">
							
														<tr>
															<td colspan="7" >
															  <div align="center">
																<table width="90%" border="0" cellpadding="0" cellspacing="0">
																  <tr valign="top">
																	<td valign="top"><div  >
																	  <div align="center"><img src="${imgItem}" width="100%" height="100%" hspace="0" vspace="0"  align="top"/></div>
																	</div></td>
																	<br />
																  </tr>
																</table>
														  </div></td>
														</tr>
									
										<tr>
										  <td colspan="7"><span class="STYLE5"></span> </td>
										</tr>
									</c:forEach>
									<tr>
										<td colspan="7" bgcolor="#FFFFFF"><span class="STYLE15">34356454</span></td>
									</tr>
		
									<tr>
										<td align="center" >
										  <table width="90%" border="0">
                                            <tr>
                                              <td><span class="STYLE21">${record.text}</span></td>
                                            </tr>
                                          </table></td>
									
									</tr>
									<tr>
									  <td colspan="7"><hr /></td>
									</tr>
	
	
									<tr>
										<td width="5%" align="center">   
										  <table width="90%" border="0">
                                            <tr>
                                              <td> <span class="STYLE5">${record.time}</span></td>
                                              <td> <div align="right" class="STYLE5">${record.location}</div></td>
                                            </tr>
                                          </table></td>
			
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