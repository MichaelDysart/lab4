function createBook() {
    var bookName = $("#bookName").val();
    $.ajax({
        url: "http://localhost:8080/create?bookName=" + bookName
    }).then(function(data) {
        createBuddyInput(bookName);
    });
}

function createBuddyInput(bookName) {
    var el = $("#addBuddy");

    el.empty();

    // Add some good old cross site scripting ;)
    el.append($("<div>Book Name" + bookName + "</div>"));

    var buddyInfo = { bookName : bookName, name : "", address : "", phoneNumber : ""};

    var nmEl = $("<input type='text' />").on('change', function () { buddyInfo.name = $(this).val(); });
    var adEl = $("<input type='text' />").on('change', function () { buddyInfo.address = $(this).val(); });
    var phEl = $("<input type='text' />").on('change', function () { buddyInfo.phoneNumber = $(this).val(); });

    var tbl = $("<table>");
    tbl.append($("<tr>").append("<td>Book Name</td>").append($("<td>").text(bookName)));
    tbl.append($("<tr>").append("<td>Buddy Name</td>").append($("<td>").append(nmEl)));
    tbl.append($("<tr>").append("<td>Buddy Address</td>").append($("<td>").append(adEl)));
    tbl.append($("<tr>").append("<td>Buddy Phone Number</td>").append($("<td>").append(phEl)));

    el.append(tbl);

    var biEl = $("<button>Create Buddy</button>").on('click', function () {
        $.ajax({
            method: "POST",
            url   : "http://localhost:8080/add",
            data  : buddyInfo
        }).done(updateBook);
    });

    el.append(biEl);
}

function updateBook(data) {
    $("#book").text(data.content);
}

$(document).ready(function () {

    $("#createBook").on('click', createBook);

});