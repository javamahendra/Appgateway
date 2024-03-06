<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hello, world!</title>
<link rel="stylesheet" type="text/css"
	href="//unpkg.com/swagger-ui-dist@3/swagger-ui.css">

<link rel="stylesheet"
	href="/static/appgateway/css/bootstrap/bootstrap.css" media="all" />
<link rel="stylesheet"
	href="/static/appgateway/css/common/responsives.css" media="all" />
<link rel="stylesheet"
	href="/static/appgateway/css/common/meterial-form.css" media="all" />
<script src="/static/appgateway/js/jquery/jquery.min.js"
	type="text/javascript"></script>
<script src="/static/appgateway/js/bootstrap/tether.min.js"
	type="text/javascript"></script>
<script src="/static/appgateway/js/bootstrap/bootstrap.min.js"
	type="text/javascript"></script>

<link href='static/swagger/css/typography.css' media='screen' rel='stylesheet' type='text/css' />
<link href='static/swagger/css/reset.css' media='screen' rel='stylesheet' type='text/css' />
<link href='static/swagger/css/screen.css' media='screen' rel='stylesheet' type='text/css' />
<link href='static/swagger/css/reset.css' media='print' rel='stylesheet' type='text/css' />
<link href='static/swagger/css/print.css' media='print' rel='stylesheet' type='text/css' />

<script src='static/swagger/lib/object-assign-pollyfill.js' type='text/javascript'></script>
<script src='static/swagger/lib/jquery-1.8.0.min.js' type='text/javascript'></script>
<script src='static/swagger/lib/jquery.slideto.min.js' type='text/javascript'></script>
<script src='static/swagger/lib/jquery.wiggle.min.js' type='text/javascript'></script>
<script src='static/swagger/lib/jquery.ba-bbq.min.js' type='text/javascript'></script>
<script src='static/swagger/lib/handlebars-4.0.5.js' type='text/javascript'></script>
<script src='static/swagger/lib/lodash.min.js' type='text/javascript'></script>
<script src='static/swagger/lib/backbone-min.js' type='text/javascript'></script>
<script src='static/swagger/swagger-ui.min.js' type='text/javascript'></script>
<script src='static/swagger/lib/highlight.9.1.0.pack.js' type='text/javascript'></script>
<script src='static/swagger/lib/highlight.9.1.0.pack_extended.js' type='text/javascript'></script>
<script src='static/swagger/lib/jsoneditor.min.js' type='text/javascript'></script>
<script src='static/swagger/lib/marked.js' type='text/javascript'></script>
<script src='static/swagger/lib/swagger-oauth.js' type='text/javascript'></script>
<link href='/static/swagger/lib/swagger-ui-3.css' media='screen' rel='stylesheet' type='text/css'>
<script src='/static/swagger/lib/swagger-ui-bundle.js'type='text/javascript'>
	
</script>
</head>

<body class="swagger-section">
<div id='header'>
  <div class="swagger-ui-wrap">
    <a id="logo" href="http://swagger.io"><img class="logo__img" alt="swagger" height="30" width="30" src="/static/images/logo_small.png" /><span class="logo__title">swagger</span></a>
    <form id='api_selector'>
      <div class='input'><input placeholder="http://example.com/api" id="input_baseUrl" name="baseUrl" type="text"/></div>
      <div id='auth_container'></div>
      <div class='input'><a id="explore" class="header__btn" href="#" data-sw-translate>Explore</a></div>
    </form>
  </div>
</div>

<div id="message-bar" class="swagger-ui-wrap" data-sw-translate>&nbsp;</div>
<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
	<div class="container">
		<div>API Endpoint Decoration</div>
		<div>Contact info</div>
		<div>Dev-Team, https://www.dev-team.com/, dev-team@gmail.com</div>
		<div>license : open source</div>
		<div>version : v1.0</div>
		<br />
		<div>
			<b>Apis Base URL : http://localhost:9091/api</b>
		</div>
		<br />

	</div>
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
			window.swaggerUi = new SwaggerUi({
				url : url,
				validatorUrl : null,
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

		});
	</script>

</body>
</html>

