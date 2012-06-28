var edit = false;

$(document).ready(function() {

	$('input[type!="submit"][type!="button"]').keypress(function(e) {
		if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
			return false;
		} else {
			return true;
		}
	});

	$('.edit').click(function() {
		if (edit) {
			var ok = true;
			
			$('#car_form').attr('action', $(this).attr('name'));
			
			$('input:text').each(function() {
				if($(this).attr('value') == '') {
					alert("ကွက်လပ်များအား ဖြည့်စွက်ပါ။");
					ok = false;
					return false;
				}
			});

			if(ok) {
				$('#car_form').submit();
			}
			
		} else {
			$('.member').each(function() {
				var value = $(this).text();
				$(this).text('');

				$(this).append($('<input />').attr({
					'type' : 'text',
					'name' : $(this).attr("id"),
					'value' : value
				}));
			});
			$('.action').remove();
			edit = true;
		}
	});

	$('.action').click(function() {
		// form-action
		$('#car_form').attr('action', $(this).attr('name'));
		$('#car_form').submit();
	});
});