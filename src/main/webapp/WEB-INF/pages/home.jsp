<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Localizonles</title>

<!-- CSS -->
<link href="./resources/css/home.css" rel="stylesheet">

<!-- Bootstrap Core CSS -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

	<!-- Header -->
	<header id="top" class="header"> <!-- /container -->

	<div class="text-vertical-center">
		<h1>Localizonles</h1>
		<h3>Garder un oeil sur la prunelle de vos yeux</h3>

		<div class="container">
			<div class="col-xs-5 col-md-4 col-sm-offset-4 col-xs-offset-3 col-md-offset-4">
			
			<h4>Please sign in</h4>
				 
				<form:form class="form-signin" method="post" action="authentification">

					<form:label class="sr-only" path="email">email</form:label>
					<form:input type="email" class="form-control" path="email" />

					<form:label class="sr-only" path="password">password</form:label> </br>
					<form:input type="password" class="form-control" path="password" /></br></br>
					
					<input class="btn btn-lg btn-primary btn-block" type="submit" value ="Sign in"/>

				</form:form>
			
			</div>
		</div>
	</div>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>



