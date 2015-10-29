angular.module('app.filters', ["ngResource"])

.filter('formatShows', function () {
    return function (value, row) {
        var shows = "", index;
        for	(index = 0; index < row.shows.length; index++) {
            shows += "," + row.shows[index].time;
        }
        return shows;
    };
})

.filter('formatScreens', function () {
    return function (value, row) {
        var screens = "", index;
        for	(index = 0; index < row.screens.length; index++) {
            screens += row.screens[index].name + (row.screens[index].shows.length);
        }
        return screens;
    };
})

.filter('formatBoolean', function () {
    return function (value, fieldVal) {
        var value = "";
        if(fieldVal == true){
            value = "Yes";
        }else if(fieldVal == false){
            value = "No";
        }
        return value;
    };
})

.filter('formatTime', function () {
    return function (value, fieldVal) {
        var value = '';
        if(fieldVal){
            var date = new Date(fieldVal);
            date.setSeconds(0);
            value = date.toLocaleTimeString();
            /*value += date.getHours();
            value += date.getMinutes();*/
        }
        return value;
    };
});
