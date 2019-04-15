$( document ).ready(function() {
    $("#workerID20 .workerTabShowContract").click(function (e) { 
        e.preventDefault();
        var testJSON = {
            workerID: 20,
            date: "ACTUAL",
            type: "GET_CONTRACT"
        };
        var toSend = JSON.stringify(testJSON);
        console.log(toSend); //this will be sent

        /* It should be there if we wanted to perform a real request
            $.ajax({
            method: "POST",
            url: "something.php",
            data: testJSON
            })
            .done(function( msg ) {
                alert( "TEST INFO");
            });
            enc ...
            ... 
            ...
        */

    });
    $("#workerID20 .workerTabEditData").click(function (e) { 
        e.preventDefault();
        console.log("BUTTON WAS CLICKED");
    });
    $("#workerID20 .workerTabCompareOptionsMonth").click(function(e){
        var testJSON = {
            workerID: 20,
            intervalStart: "DD-MM-YYYY",
            intervalEnd: "DD-MM-YYYY",
            type: "GET_RATIOS_FOR_PERIOD"
        };
        var toSend = JSON.stringify(testJSON);
        console.log(toSend);
    });

    sendRefreshPeriodically();

});

function sendRefreshPeriodically(){
    var testJSON = {
        type: "SERVER_STATS_GET",
        countClics: 21,
        tabsClicked: "Ratios, Work-Data"
    }
    var toSend = JSON.stringify(testJSON);
    console.log(toSend);
    setTimeout(sendRefreshPeriodically,5000);
}

