
  window.fbAsyncInit = function() {
  FB.init({
    appId      : '974214759339775',
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.5' // use graph api version 2.5
  });

  // Now that we've initialized the JavaScript SDK, we call 
  // FB.getLoginStatus().  This function gets the state of the
  // person visiting this page and can return one of three states to
  // the callback you provide.  They can be:
  //
  // 1. Logged into your app ('connected')
  // 2. Logged into Facebook, but not your app ('not_authorized')
  // 3. Not logged into Facebook and can't tell if they are logged into
  //    your app or not.
  //
  // These three cases are handled in the callback function.

  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });

  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('login - statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
      doAfterConnected();
    } else if (response.status === 'not_authorized') {
      // The person is logged into Facebook, but not your app.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
    } else {
      // The person is not logged into Facebook, so we're not sure if
      // they are logged into this app or not.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into Facebook.';
    }
  }

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function doAfterConnected() {
    console.log('Welcome!  Fetching your information.... ');
    disableScreen();
    FB.api('/me', function(response) {
    	console.log(response);
    	console.log("going to validate if user is a FMA member");
        validateIfUserIsMember(response, 0);
    	
        console.log('Successful login for: ' + response.name);
        document.getElementById('status').innerHTML = 'Thanks for logging in, ' + response.name + '!' + response.id;
    });
  }
  
  
  function disableScreen(){
	  $.blockUI({ css: { 
          border: 'none', 
          padding: '15px', 
          backgroundColor: '#000', 
          '-webkit-border-radius': '10px', 
          '-moz-border-radius': '10px', 
          opacity: .5, 
          color: '#fff' 
      } }); 
  }
  
  function enableScreen(){
	  $.unblockUI();
  }

  
  function validateIfUserIsMember(user, offsetValue){
	  var userIsMember = false; 
	  FB.api(
			  '/195962353890440/members',
			  'GET',
			  {"limit":"1000","offset":offsetValue},
			  function(response) {
				  //check if user is a member
				  console.log("offset:" + offsetValue);
				  console.log(response);
			      $.each( response.data, function( index, member ) {
			    	  if(member.id == user.id){
			    		  console.log("member found");
			    		  console.log(member); 
			    		  userIsMember = true; 
			    	  }
			      });
			      

				  if(userIsMember){
					  //user is a member, can continue
					  console.log("authorized to use this app.");
					  $("#userid").val(user.id);
					  $("#displayName").val(user.name);
					  $("#loginForm").submit(); 
					  enableScreen(); 
				  }
				  else{
					  //check if next page exists
					  var nextPage =  response.paging.next;
					  if (typeof(nextPage) != 'undefined' && nextPage != null){
						  offsetValue += 1000;
				    	  console.log("continue to next page. offset:" + offsetValue);
				    	  validateIfUserIsMember(user, offsetValue);
					  } else{
						  //no next page, user is not a member 
						  console.log("not authorized to use this app.");
						  //todo: update user in admin page
						  FB.logout(function(response) {
							  // user is now logged out
							  document.getElementById('status').innerHTML = 'You are not a member of Frugal Mumma Auckland. Please login as a different user.'; 
							  enableScreen(); 
						  });
					  }					  
				  }
			  }
			);
  }
  
  