(function($, undefined ) {
	var userPageIndex = 1, userAmountPerPage = 10;
	var defaultUserImage = "https://avatars1.githubusercontent.com/u/39437438?s=460&v=4";

	function loadUsers(){
		var urlRequest = "https://api.github.com/users?page=" + userPageIndex + "&per_page=" + userAmountPerPage;
		console.log("loading for page=" + userPageIndex+ " - " + urlRequest);
		$.ajax({
			url: urlRequest,
  			context: document.body,
			beforeSend: function(xhr) {
				console.log("loading");
			},
			statusCode: {
		    404: function() {
		      alert("page not found");
		    }
		  }
		})
		.done(function(data) {
			makeDOM(data);
		});
	}

	$(document).on('click','#btn_loadMore',function(){
		userPageIndex++;
  		loadUsers();
	});

	function makeDOM(data) {
	  for (var i = 0; i < data.length; i++) {
	  	var user = data[i];
	  	$('#ul_userList').append(
	  		$('<li>')
		    .append(
		        $('<img>').addClass('ui-li-thumb userthumb').attr('src', (user['avatar_url'] ? user['avatar_url'] : defaultUserImage))
	        ).append(
	            $('<h2>').append(user['login'])
	        ).append(
	            $('<p>').addClass('ui-li-aside')
	            .append(
	            	$('<p>').append(
	            		$('<a>').attr('href', '#bar').attr('target', '_blank').append('See in github')
	            	)
	            ).append(
	            	$('<p>').append(
	            		$('<a>').attr('href', '#bar').append('Repositories')
	            	)
	            )
	        )
		);  
	  }
	}
	loadUsers();

})(jQuery);
