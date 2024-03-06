<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="IE=edge">
<title>Swagger UI</title>
<link rel="icon" type="image/png" href="images/favicon-32x32.png"
	sizes="32x32" />
<link rel="icon" type="image/png" href="images/favicon-16x16.png"
	sizes="16x16" />
<link href='static/swagger/css/typography.css' media='screen'
	rel='stylesheet' type='text/css' />
<link href='static/swagger/css/reset.css' media='screen'
	rel='stylesheet' type='text/css' />
<link href='static/swagger/css/screen.css' media='screen'
	rel='stylesheet' type='text/css' />
<link href='static/swagger/css/reset.css' media='print' rel='stylesheet'
	type='text/css' />
<link href='static/swagger/css/print.css' media='print' rel='stylesheet'
	type='text/css' />

<script src='static/swagger/lib/object-assign-pollyfill.js'
	type='text/javascript'></script>
<script src='static/swagger/lib/jquery-1.8.0.min.js'
	type='text/javascript'></script>
<script src='static/swagger/lib/jquery.slideto.min.js'
	type='text/javascript'></script>
<script src='static/swagger/lib/jquery.wiggle.min.js'
	type='text/javascript'></script>
<script src='static/swagger/lib/jquery.ba-bbq.min.js'
	type='text/javascript'></script>
<script src='static/swagger/lib/handlebars-4.0.5.js'
	type='text/javascript'></script>
<script src='static/swagger/lib/lodash.min.js' type='text/javascript'></script>
<script src='static/swagger/lib/backbone-min.js' type='text/javascript'></script>
<script src='static/swagger/swagger-ui.js' type='text/javascript'></script>
<script src='static/swagger/lib/highlight.9.1.0.pack.js'
	type='text/javascript'></script>
<script src='static/swagger/lib/highlight.9.1.0.pack_extended.js'
	type='text/javascript'></script>
<script src='static/swagger/lib/jsoneditor.min.js'
	type='text/javascript'></script>
<script src='static/swagger/lib/marked.js' type='text/javascript'></script>
<script src='static/swagger/lib/swagger-oauth.js' type='text/javascript'></script>
<style>
.ktable {
	border-style: ridge;
	border-color: #8ebf42;
	background-color: #d9d9d9;
}

.kth {
	border: 2px solid #095484;
}

.ktd {
	border: 2px groove #1c87c9;
}
</style>
<script type="text/javascript">
	$(function() {
		var url = window.location.search.match(/url=([^&]+)/);
		if (url && url.length > 1) {
			url = decodeURIComponent(url[1]);
		} else {
			url = "/static/swagger/app.json";
		}

		hljs.configure({
			highlightSizeThreshold : 5000
		});

		// Pre load translate...
		if (window.SwaggerTranslator) {
			window.SwaggerTranslator.translate();
		}
		window.swaggerUi = new SwaggerUi(
				{
					url : url,
					dom_id : "swagger-ui-container",
					supportedSubmitMethods : [ 'get', 'post', 'put', 'delete',
							'patch' ],
					onComplete : function(swaggerApi, swaggerUi) {
						if (typeof initOAuth == "function") {
							initOAuth({
								clientId : "your-client-id",
								clientSecret : "your-client-secret-if-required",
								realm : "your-realms",
								appName : "your-app-name",
								scopeSeparator : " ",
								additionalQueryStringParams : {}
							});
						}

						if (window.SwaggerTranslator) {
							window.SwaggerTranslator.translate();
						}
					},
					onFailure : function(data) {
						log("Unable to Load SwaggerUI");
					},
					docExpansion : "none",
					jsonEditor : false,
					defaultModelRendering : 'schema',
					showRequestHeaders : false,
					showOperationIds : false
				});

		window.swaggerUi.load();

		function log() {
			if ('console' in window) {
				console.log.apply(console, arguments);
			}
		}
	});
</script>
</head>

<body class="swagger-section">
	<div id='header'>
		<div class="swagger-ui-wrap">
			<a id="logo" href="http://swagger.io"><img class="logo__img"
				alt="swagger" height="30" width="30"
				src="/static/swagger/images/logo_small.png" /><span
				class="logo__title">swagger</span></a>
			<form id='api_selector'>
				<div class='input'>
					<input placeholder="http://example.com/api" id="input_baseUrl"
						name="baseUrl" type="text" />
				</div>
				<div id='auth_container'></div>
				<div class='input'>
					<a id="explore" class="header__btn" href="#" data-sw-translate>Explore</a>
				</div>
			</form>
		</div>
	</div>
	<br />
	<center>
		<table class="ktable">
			<tr>
				<th class="kth">Stage</th>
				<th class="kth">Client Id</th>
				<th class="kth">Client Secret</th>
			</tr>

			<c:forEach items="${headerkeys}" var="hkey">
				<tr>
					<td class="ktd"><c:out value="${hkey.stage}" /></td>
					<td class="ktd"><c:out value="${hkey.clientid}" /></td>
					<td class="ktd"><c:out value="${hkey.clientsecret}" /></td>
				</tr>

			</c:forEach>

		</table>
	</center>
	<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
	<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
</body>
</html>
