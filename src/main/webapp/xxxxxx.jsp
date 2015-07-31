

<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

	<head>
	
	
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
			<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
			<meta name="apple-mobile-web-app-capable" content="yes">
			<meta name="apple-mobile-web-app-status-bar-style" content="black">
			<meta name="format-detection" content="telephone=no">
			
			 
			 <jsp:directive.page import="java.util.List"/>
			 <jsp:directive.page import="com.netease.yixing.model.TravelRecord"/>
			 <jsp:directive.page import="com.netease.yixing.model.TravelSchedule"/>
			
			
			  <title>旅记</title>
	
	</head>
		
	<body id="activity-detail" class="zh_CN mm_appmsg not_in_mm" >
		<div id="js_article" class="rich_media">
			<div class="rich_media_inner">
				<div id="page-content">
					<div id="img-content" class="rich_media_area_primary">
						<h2 class="rich_media_title" id="activity-name"> ${travelSchedule.title}</h2>
							<hr></hr>
							<hr></hr>
						<div class="rich_media_meta_list">
							<em class="rich_media_meta rich_media_meta_text">开始时间：${travelSchedule.startTime}</em>
							<hr></hr>
							<em class="rich_media_meta rich_media_meta_text">结束时间： ${travelSchedule.endTime}</em> 
						</div>
						<div class="rich_media_content" id="js_content">
							<section data-width="10px"  style="color: inherit; float: left; width: 10px; margin-bottom: -10px; border-color: rgb(252, 180, 43); background-color: rgb(254, 254, 254); box-sizing: border-box; padding: 0px; height: 10px !important;">
								<p></p>
									<table>
										<tr>
											<td>我是明星</td>
										</tr>
										<c:forEach var="record" items="${recordList}">
											<tr>
												<td><img src="${record.pictureKey}" />
												</td>
		
											</tr>
											<tr>
												<td>${record.valid} 说：</td>
											</tr>
											<tr>
												<td>${record.text}</td>
											</tr>
											<p></p>
										</c:forEach>
									</table>
							</section>
						</div>
	
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
