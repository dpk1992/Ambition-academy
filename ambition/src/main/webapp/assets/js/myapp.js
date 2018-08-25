$function()
{
	switch (menu) {

	case 'Home':
		$('#home').addClass('active');
		break;
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProduct').addClass('active');
		break;
	default:
		if (menu == Home) break;
		$('#listProduct').addClass('active');
		$('#a_' + menu).addClass('active');
		break;

	}
}