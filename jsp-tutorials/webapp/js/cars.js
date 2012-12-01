$(document).ready(function() {
	// row click
	$('tr.car-row').click(function() {

		// append input
		$(this).children("td").each(function() {
			$("<input />").attr({
				'type' : 'hidden',
				'name' : $(this).attr("class"),
				'value' : $(this).text()
			}).appendTo('form#details');
		});

		// submit
		$('form#details').submit();
	});

	// row mouse over
	$('tr.car-row').mouseover(function() {
		$(this).css("background-color", "red").css("color", "yellow");
	}).mouseout(function() {
		$(this).css("background-color", "#F2F2E6").css("color", "black");
	});
});