//this is not the full front end, just a part of what we hope to implement for communication


function httpGetAsync(theUrl, callback)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }


xmlHttp.open("GET", localhost, true); // true for asynchronous
xmlHttp.send(null);
