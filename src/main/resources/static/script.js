window.addEventListener("load", function(){
    fetch(""https://maps.googleapis.com/maps/api/js?key=AIzaSyADYvhBijgiw8gTEPLgqqGjtafQI1MdFhs&callback=initMap">").then(function(response){
        response.json().then(function(json) {
        function initMap() {
        }
  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 8,
    center: {
      address;
    }
  });
  var geocoder = new google.maps.Geocoder();

  document.getElementById('submit').addEventListener('click', function() {
    geocodeAddress(geocoder, map);
  });
}

function geocodeAddress(geocoder, resultsMap) {
  var address = document.getElementById('address').value;
  geocoder.geocode({
    'address': address
  }, function(results, status) {
    if (status === 'OK') {
      resultsMap.setCenter(results[0].geometry.location);
      var marker = new google.maps.Marker({
        map: resultsMap,
        position: results[0].geometry.location
      });
    } else {
      alert('Geocode was not successful for the following reason: ' + status);
    }
  });
}
