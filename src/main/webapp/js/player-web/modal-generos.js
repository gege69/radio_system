
var listaGeneros = function(){
    
    var param = { idAmbiente: $("#idAmbiente").val() };
    
    var urlGeneros = buildUrl( "/api/ambientes/{idAmbiente}/generos", param);
    var urlGenerosTotal = buildUrl( "/api/ambientes/{idAmbiente}/programacoes/generos/total", param);
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: urlGeneros,    // busca a lista de gêneros possível para o ambiente
        dataType: 'json'
    }).done( function(json){
        makeListTmpl(json);
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: urlGenerosTotal,    // busca a lista de generos que participam da programação da semana (total)
            dataType: 'json'
        }).done( function(json){
            
            var lista = json.rows;

            $.each( lista, function( idx, obj ){
                $('#genero-'+obj.idGenero).prop('checked', true);
            });
            
        });
        
    } );
}


var salvarProgramacaoMusicalGeneros = function()
{
    var array_values = [];
    $('.checkbox-genero').each( function() {
        if( $(this).is(':checked') ) {
            array_values.push( {idGenero: $(this).val()} );
        }
    });
    
    if ( array_values.length == 0 || array_values == undefined )
    { 
        preencheAlertGeral('alertArea', 'É necessário escolher pelo menos um Gênero Músical');
        return;
    }
    
    var idList = { lista : array_values };
    
    var dados = JSON.stringify( idList );
    
    var urlGenerosAmbiente = buildUrl( "/api/ambientes/{idAmbiente}/programacoes/generos/total", { 
        idAmbiente: $("#idAmbiente").val()
    });
    
    $("#btnSalvarProgramacaoMusicalGeneros").prop("disabled",true);
    $('#ajaxload').show();

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: urlGenerosAmbiente,
        dataType: 'json',
        data : dados 
    }).done( function(json){
        if (json.ok != null){
            $('#myModalGeneros').modal('hide');
        }
        else{
            preencheErros( json.errors );
        }

        $('#ajaxload').hide();
        $("#btnSalvarProgramacaoMusicalGeneros").prop("disabled",false);

    }).fail( function(){
        $('#ajaxload').hide();
        $("#btnSalvarProgramacaoMusicalGeneros").prop("disabled",false);
    });
}


var makeListTmpl = function(json){
    
    var tmpl = $.templates('#viewTmpl');
    
    $('#view-container').empty();
    
    var content = tmpl.render(json.rows);
    
    $('#view-container').append(content);
};

$(function(){
    
    /**
     * Esse modal foi alterado para escolher os gêneros da programação musical e não os gêneros habilitados para o Ambiente.
     */
    $("#myModalGeneros").on('shown.bs.modal', function(){
        listaGeneros();
    });
    
    $('#btnSalvarProgramacaoMusicalGeneros').on('click', function(){
        salvarProgramacaoMusicalGeneros();
    });
    
});

