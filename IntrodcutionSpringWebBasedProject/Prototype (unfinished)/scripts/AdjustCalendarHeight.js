function adjustCalendarHeight(selector,container){
    $(selector).height($(container).height()-$(selector).siblings().outerHeight());
}
$( document ).ready(function() {
    adjustCalendarHeight(".calendarTabMainContainer",window);
});

$(window).resize(function () { 
    adjustCalendarHeight(".calendarTabMainContainer",window);
});