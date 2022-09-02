$(document).ready(function() {
	$("button").click(function() {
		$.ajax({
			type : 'POST',
			url : 'http://localhost:8080/api/v1/shorturl',
			data : JSON.stringify({
				"full_url" : $("#urlinput").val()
			}),
			contentType : "application/json; charset=utf-8",
			success : function(data) {
			
				$("a.target").text(data.short_url);
				$("a.target").attr("href", data.short_url);

			}
		});
	});
});