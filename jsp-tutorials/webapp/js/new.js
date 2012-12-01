$(document).ready(function() {

	$('input[type!="submit"][type!="button"]').keypress(function(e) {
		if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
			return false;
		} else {
			return true;
		}
	});

	$('form').submit(function () {
		var ok = true;
		$('input:text').each(function () {
			if($(this).attr('value') == '') {
				alert("ကွက်လပ်များအား ဖြည့်စွက်ပါ။");
				ok = false;
				return false;
			}
		});
		
		if(ok) {
			return true;
		} else {
			return false;
		}
	});
});