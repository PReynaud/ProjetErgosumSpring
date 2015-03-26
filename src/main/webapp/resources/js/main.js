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
        $(".red").css("visibility", "visible");
    }
    if(res && numberBoxes === 1){
        $(".green").css("visibility", "visible");
    }
    if(res && numberBoxes !== 1){
        $(".green").css("visibility", "hidden");
    }
    if(!res)
    {
        $(".red").css("visibility", "hidden");
        $(".green").css("visibility", "hidden");
    }
}

$("document").ready(function(){
    $(":checkbox").change(showDeleteButton);
});
