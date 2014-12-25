function setLanguage(lng) {
	
    $.ajax({
        data: {
            'lng' : lng}
    });
	
	var a = location.href;
    var myRegexp = /^(.*?)\&lang/;
	var myRegexp2 = /\?/;
	if (!(myRegexp2.test(a))) {
		document.getElementById(lng).setAttribute("href",
	            (a+"?&lang="+lng));
	}
	else if (myRegexp.test(a)) {
    document.getElementById(lng).setAttribute("href",
            (myRegexp.exec(a)[1] + "&lang="+lng));
    } 
    else {document.getElementById(lng).setAttribute("href",
            (a+"&lang="+lng));
    } 

}