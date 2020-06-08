$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8083/finance"
    }).then(function(data) {
       $('finance-id').append(data.id);
       $('finance-revenue').append(data.revenue);
       $('finance-earning').append(data.earning);
    });
});