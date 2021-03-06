<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<title>Frugal Mumma Auckland - Welcome!</title>
	<meta charset="utf-8"></meta>
  	<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"></link>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous"></link>
	<link href="resources/css/signin.css" rel="stylesheet"></link>	
	<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="resources/js/jquery.blockUI.js" ></script>
	<script src="resources/js/fblogin.js" ></script>	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
      <div class="form-signin" >
        <h2 class="form-signin-heading">Welcome to Frugal Mumma Auckland</h2>        
        <fb:login-button scope="public_profile,email" onlogin="checkLoginState();" size="xlarge" default_audience="only_me" ></fb:login-button>
        <div id="status">${message}</div>
      </div>
	</div>
	<form id="loginForm" action="login" method="post" >
		<input type="hidden" id="userid" name="userid" />
		<input type="hidden" id="displayName" name="displayName" />		
	</form>
</body>
</html>