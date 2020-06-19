
$(document).ready(function(Request(username)) {
    $.ajax({
        url: "http://localhost:8081/greeting?name"+username
    }).then(function(data) {
       $('.greeting-content').append(data.content);
    });
});