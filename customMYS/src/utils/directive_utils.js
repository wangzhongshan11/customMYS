import Vue from 'vue'
Vue.directive("dragVerticle", (el, { value }) => {
    el.addEventListener("mousedown", mousedown);
    var posX, posY;
    var _value = value;
    function mousedown(e) {
        posX = e.offsetX;
        posY = e.offsetY;
        // console.log(e.offsetY);
        // console.log(e.clientY);
        document.addEventListener("mousemove", mousemove);
        document.addEventListener("mouseup", mouseup);
    }
    function mousemove(e) {
        var dx = e.offsetX - posX;
        var dy = e.offsetY - posY;
        var firstC = parseFloat(-dy / 200 * 204 + 51).toFixed(0);
        var secondC = parseFloat(-dy / 200 * 102 + 153).toFixed(0);
        if (firstC > 255) {
            firstC = 255;
        }
        if (firstC < 51) {
            firstC = 51;
        }
        if (secondC > 255) {
            secondC = 255;
        }
        if (secondC < 153) {
            secondC = 153;
        }
        console.log("x移动" + dx + ",y移动" + dy);
        // console.log(_value);
        // console.log(document.getElementsByClassName(_value)[0]);
        console.log(document.getElementsByClassName(_value)[0].style.backgroundColor);
        console.log(firstC + ':' + secondC);
        console.log('#' + parseInt(firstC).toString(16) + ':' + parseInt(secondC).toString(16) + 'ff');
        document.getElementsByClassName(_value)[0].style.backgroundColor = '#' + parseInt(firstC).toString(16) + parseInt(secondC).toString(16) + 'ff';
    }
    function mouseup(e) {
        document.removeEventListener("mousemove", mousemove);
        document.removeEventListener("mouseup", mouseup);
    }
});