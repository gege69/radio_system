
var listaMidias = function(){
    
    var param = { idAmbiente: $("#idAmbiente").val() };
    
    var urlMidiablock = buildUrl( "/api/ambientes/{idAmbiente}/programacoes/midias/block", param);
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: urlMidiablock,    
        dataType: 'json'
    }).done( function(json){
        makeListTmpl(json);
        
        $.each( json, function( idx, obj ){
            if ( obj.ativo === true )
                $('#midiablock-'+obj.idMidia).prop('checked', true);
        });
    } );
}


var salvarMidiaBlocks = function()
{
    var array_values = [];
    $('.checkbox-midiablock').each( function() {
        if( $(this).is(':checked') ) {
            array_values.push( {idMidia: $(this).val()} );
        }
    });
    
    var idList = { lista : array_values };
    
    var dados = JSON.stringify( idList );
    
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
        listaMidias();
    });
    
    $('#btnSalvarMidiaBlock').on('click', function(){
        salvarMidiaBlocks();
    });
    
});

