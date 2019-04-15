function setTaskOnCalendar(startHour,endHour,weekDay,content){

    offsetTop = $("#calendarTabDelim"+startHour).position().top;
    offsetEnd = $("#calendarTabDelim"+endHour).position().top;
    console.log(offsetTop+" "+offsetEnd)
    height = offsetEnd-offsetTop;
    $(".calendarTabWeek"+weekDay).append('<div class = "calendarTabDisplayedTask" style = " top: '+offsetTop+'px;  height: '+height+'px ">'+content+'</div>');

}

$( document ).ready(function() {
    setTaskOnCalendar("80","100","Monday","test1");
    setTaskOnCalendar("103","113","Monday","test2");
    setTaskOnCalendar("60","90","Tuesday","test3");
    setTaskOnCalendar("50","63","Wednesday","test4");
});