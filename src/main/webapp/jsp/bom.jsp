<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"> </script>
<script>

$(function() {

var entries = [];
var dmJSON = "https://www.nseindia.com/live_market/dynaContent/live_watch/stock_watch/foSecStockWatch.json";

$.getJSON( dmJSON, function(data) {
	$("#time").html(data.time);
   $.each(data.data, function(i, f) {
	var a="";
	var b="";
	if(f.open ==f.high){
		a="Sell";
		b="red";
	} else if(f.open ==f.low) {
		a="Buy";
		b="green";
	}
      var tblRow = "<tr>" + 
	  "<td>" + f.symbol + "</td>" + 
	  "<td>" + f.open + "</td>"  +
	  "<td>" + f.high + "</td>"  +
	  "<td>" + f.low + "</td>"  +
	  "<td>" + f.ltP + "</td>"  +
		"<td bgcolor="+b+">" + a + "</td>"  +	
	  "</tr>"
       $(tblRow).appendTo("#entrydata tbody");
 });

});

});
</script>
</head>

<body>
Market Time:<b id="time"></b>
<div class="wrapper">
<div class="profile">
<table id= "entrydata" border="1">
<thead>
        <th>Symbol</th>
        <th>Open</th>      
		<th>High</th>
		<th>Low</th>
		<th>LTP</th>
		<th>OHL</th>
    </thead>
  <tbody>

   </tbody>
</table>

</div>
</div>

</body>

</html>
