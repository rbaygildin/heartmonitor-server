<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/maps/documentation/javascript/demos/demos.css">
</head>
<body>
<div id="map"></div>
<label id="latitude" style="visibility: hidden;">${latitude}</label>
<label id="longitude" style="visibility: hidden;">${longitude}</label>
<script>
    function initMap() {
        var lat_l = document.getElementById("latitude");
        var lng_l = document.getElementById("longitude");
        var lat = parseFloat(lat_l.value());
        var lng = parseFloat(lng_l.value());
        var map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: lat, lng: lng},
            scrollwheel: false,
            zoom: 8
        });
    }

</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCz7VNWEr8PdiENFlzXQsVB3mfsot4C13s&callback=initMap" async defer></script>
