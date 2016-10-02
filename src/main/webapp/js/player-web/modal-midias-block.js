
var listaMidiasBlocks = function(codigoCategoria){

    var pathVar = { idAmbiente: $("#idAmbiente").val() };
    var search = { codigoCategoria : codigoCategoria };

    var urlMidiablock = buildUrl( "/api/ambientes/{idAmbiente}/programacoes/midias/block", pathVar, search);
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: urlMidiablock,    
        dataType: 'json'
    }).done( function(json){
        
        var descricao = json.descricaoCategoria;
        
        $("#idMidiaBlockTitulo").html("Ativar ou Inativar " + descricao);
        
        makeListTmplMidiaBlock(json.midias);
        
        $.each( json.midias, function( idx, obj ){
            if ( obj.ativo === true )
                $('#midiablock-'+obj.idMidia).prop('checked', true);
        });
    } );
}


var salvarMidiaBlocks = function()
{
    var mapMidiasBlock = [];
    $('.checkbox-midiablock').each( function() {
        var checked = $(this).is(':checked');
        var idMidia = $(this).val();
//        mapMidiasBlock[""+idMidia+""] = checked;
    });
    
    var idList = { mapMidiasBlock : mapMidiasBlock };
    
    var dados = JSON.stringify( idList );
    
    console.log(dados);
    
    var urlGenerosAmbiente = buildUrl( "/api/ambientes/{idAmbiente}/programacoes/midias/block", { 
        idAmbiente: $("#idAmbiente").val()
    });
    
    $("#btnSalvarMidiaBlock").prop("disabled",true);
    $('#ajaxloadMidiaBlock').show();

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: urlGenerosAmbiente,
        dataType: 'json',
        data : dados 
    }).done( function(json){
        if (json.ok != null){
            $('#myModalMidiaBlock').modal('hide');
        }
        else
            preencheErros( json.errors, "alertAreaMidiaBlock" );

        $('#ajaxloadMidiaBlock').hide();
        $("#btnSalvarMidiaBlock").prop("disabled",false);

    }).fail( function(){
        $('#ajaxloadMidiaBlock').hide();
        $("#btnSalvarMidiaBlock").prop("disabled",false);
    });
}


var makeListTmplMidiaBlock = function(json){
    
    var tmpl = $.templates('#viewTmplMidiaBlock');
    
    $('#viewContainerMidiaBlock').empty();
    
    var content = tmpl.render(json.rows);
    
    $('#viewContainerMidiaBlock').append(content);
};

$(function(){
    
    $("#myModalMidiaBlock").on('shown.bs.modal', function(){

        $("#viewContainerMidiaBlock").empty();

        var codigoCategoria = $("#myModalMidiaBlock").data("param");

        listaMidiasBlocks(codigoCategoria);
    });
    
    $('#btnSalvarMidiaBlock').on('click', function(){
        salvarMidiaBlocks();
    });
    
});

