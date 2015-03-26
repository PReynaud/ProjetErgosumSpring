$("document").ready(function(){
    $(":checkbox").change(showDeleteButton);
    $(".red").click(onRedClick);
    $(".green").click(onGreenClick);
});

function showDeleteButton(){
    var listBoxes = $(":checkbox");
    var res = false;
    var numberBoxes = 0;
    for (box in listBoxes){
        if(listBoxes[box].checked){
            numberBoxes ++;
            res = true;
        }
    }
    if(res){
        showRed();
    }
    if(res && numberBoxes === 1){
        showGreen();
    }
    if(res && numberBoxes !== 1){
        hideGreen();
    }
    if(!res)
    {
        hideRed();
        hideGreen();
    }
};

function showRed(){
    $(".red").css("visibility", "visible");
    $(".red").animate({
        bottom: "60"
    }, 100, "easeOutCirc", null);
};

function showGreen(){
    $(".green").css("visibility", "visible");
    $(".green").animate({
        bottom: "130"
    }, 100, "easeOutCirc",null);
};

function hideRed(){
    /*$(".red").css("visibility", "hidden");*/
    $(".red").animate({
        bottom: "0"
    }, 100, "easeOutCirc", function() {
        $(".red").css("visibility", "hidden");
    });
};

function hideGreen(){
    /*$(".green").css("visibility", "hidden");*/
    $(".green").animate({
        bottom: "0"
    }, 100, "easeOutCirc", function() {
        $(".green").css("visibility", "hidden");
    });
};

function onRedClick(){
    var url = "/effacerJouet" + "?id=";
    var listBoxes = $(":checkbox");
    var res = false;
    var numberBoxes = 0;
    var value;
    for (box in listBoxes){
        if(listBoxes[box].checked){
            numberBoxes ++;
            res = true;
            value = $(listBoxes[box]).attr("value");
            if(numberBoxes === 1){
                url += value;
            }
            else{
                url += "&" + value;
            }
        }
    }
    window.location.href = url;
};

function onGreenClick(){
    var url = "/modifierJouet" + "?id=";
    var listBoxes = $(":checkbox");
    var res = false;
    for (box in listBoxes){
        if(listBoxes[box].checked){
            res = true;
            url += $(listBoxes[box]).attr("value");
        }
    }
    window.location.href = url;
};