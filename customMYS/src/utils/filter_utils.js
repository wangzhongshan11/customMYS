import Vue from 'vue'
Vue.filter("formatDate", function formatDate(value) {
  var date = new Date(value);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  return year + "-" + month + "-" + day;
});
Vue.filter("formatDateTime", function formatDateTime(value) {
  var now = new Date();
  var date = new Date(value);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  var hours = date.getHours();
  var minutes = date.getMinutes();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  if (hours < 10) {
    hours = "0" + hours;
  }
  if (minutes < 10) {
    minutes = "0" + minutes;
  }
  if (year !== now.getFullYear()) {
    return year + "-" + month + "-" + day + " " + hours + ":" + minutes;
  } else {
    if (month !== (now.getMonth() + 1) || day !== now.getDate()) {
      return month + "-" + day + " " + hours + ":" + minutes;
    } else {
      if (hours !== now.getHours()) {
        return hours + ":" + minutes;
      } else {
        return minutes - now.getMinutes() + '分钟前';
      }
    }
  }

});

Vue.filter("formatDateTimeComment", function formatDateTime(value) {
  var now = new Date();
  var date = new Date(value);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  var hours = date.getHours();
  var minutes = date.getMinutes();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  if (hours < 10) {
    hours = "0" + hours;
  }
  if (minutes < 10) {
    minutes = "0" + minutes;
  }
  if (year !== now.getFullYear()) {
    return month + "月/" + year + "年";
  } else {
    if (month !== (now.getMonth() + 1) || day !== now.getDate()) {
      return month + "月/" + year + "年";
    } else {
      if (hours !== now.getHours()) {
        return '小时前';
      } else {
        return '分钟前';
      }
    }
  }
});
Vue.filter("formatDateTimeCommentDayHour", function formatDateTime(value) {
  var now = new Date();
  var date = new Date(value);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  var hours = date.getHours();
  var minutes = date.getMinutes();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  if (hours < 10) {
    hours = "0" + hours;
  }
  if (minutes < 10) {
    minutes = "0" + minutes;
  }
  if (year !== now.getFullYear()) {
    return day;
  } else {
    if (month !== (now.getMonth() + 1) || day !== now.getDate()) {
      return day;
    } else {
      if (hours !== now.getHours()) {
        return now.getHours() - hours;
      } else {
        return now.getMinutes() - minutes;
      }
    }
  }
});
Vue.filter("fixNumber", function fixNumber(number) {
  var fixedNumber = '';
  if (!isNaN(number) && number !== '') {
    fixedNumber = parseFloat(number).toFixed(2);
  } else {
    fixedNumber = '--';
  }
  return fixedNumber;
});

