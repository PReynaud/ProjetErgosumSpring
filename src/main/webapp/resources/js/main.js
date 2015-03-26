function verif()
{
    if(document.getElementById("id").value == "" ||
        document.getElementById("libelle").value == "" ||
        document.getElementById("datedebut").value == "" ||
        document.getElementById("datefin").value == "")
    {
        alert("Un des champs n'est pas rempli !");
        return false;
    }
    else{
        return true;
    }
};

function Chargement() {
    var obj = document.getElementById("id_erreur");
    if (obj.value != ''){
        alert('Erreur signalée  : "' + obj.value + "'");
    }
};

jQuery(function($){
    $.datepicker.setDefaults($.datepicker.regional['fr']);
    $('#datedebut').datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: true,
        yearRange: "-100:+00"
    });
    $('#datefin').datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: true,
        yearRange: "-100:+00"
    });
})

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
}

$("document").ready(function(){
    $(":checkbox").change(showDeleteButton);
});

function showRed(){
    $(".red").css("visibility", "visible");
    $(".red").animate({
        bottom: "60"
    }, 100, null);
};

function showGreen(){
    $(".green").css("visibility", "visible");
    $(".green").animate({
        bottom: "130"
    }, 100, null);
};

function hideRed(){
    /*$(".red").css("visibility", "hidden");*/
    $(".red").animate({
        bottom: "0"
    }, 100, function() {
        $(".red").css("visibility", "hidden");
    });
};

function hideGreen(){
    /*$(".green").css("visibility", "hidden");*/
    $(".green").animate({
        bottom: "0"
    }, 100, function() {
        $(".green").css("visibility", "hidden");
    });
};
