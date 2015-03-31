function changeNumberOfLines(nb){
    var $root = $(".root-catalogues");
    var $oneLine;
    var $lines = $(".separate-field");
    if($lines.length === 1){
        $oneLine = $lines.clone();
    }
    else{
        $oneLine = $($lines[$lines.length - 1]).clone();
    }

    $(".selectCatalogue" ,$oneLine).attr("id", "codeCatalogue" + nb);
    $(".selectCatalogue" ,$oneLine).attr("name", "codeCatalogue" + nb);
    $(".quantiteCatalogue", $oneLine).attr("id", "quantiteDistribution" + nb);
    $(".quantiteCatalogue", $oneLine).attr("name", "quantiteDistribution" + nb);
    $root.append($oneLine);
};

$("#add-catalogues").click(function(){
    var $nb = $("#nb-catalogues");
    var nb =  parseInt($nb.val()) + 1;
    $nb.val(nb);
    changeNumberOfLines(nb);
});
