<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/maps/documentation/javascript/demos/demos.css"/>
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        #map {
            height: 100%;
        }

    </style>
</head>
<body>
<div id="map"></div>
<input type="hidden" id="latitude" value="${latitude}">
<input type="hidden" id="longitude" value="${longitude}">
<script>
    (function () {
        var lat_l = document.getElementById("latitude");
        var lng_l = document.getElementById("longitude");
        window.lat = parseFloat(lat_l.value.replace(',', '.'));
        window.lng = parseFloat(lng_l.value.replace(',', '.'));
    })();
    function initMap() {

        var map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: window.lat, lng: window.lng},
            scrollwheel: false,
            zoom: 16
        });
    }

</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCz7VNWEr8PdiENFlzXQsVB3mfsot4C13s&callback=initMap" async defer></script>
</body>
</html>