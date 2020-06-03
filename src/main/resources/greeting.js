$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8081/greeting?name=Arpita&description=I%20am%20a%20girl"
    }).then(function(data) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
    });
});