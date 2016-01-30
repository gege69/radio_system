
var listaGeneros = function( doJump ){
    
    var urlGeneros = buildUrl( "/api/generos", { 
        idAmbiente: idAmbiente
    });
    
    var urlGenerosAmbiente = buildUrl( "/api/ambientes/{idAmbiente}/generos", { 
        idAmbiente: idAmbiente
    });
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: urlGeneros,    // busca a lista de gêneros geral ( não restringe pelo ambiente )
        dataType: 'json'
    }).done( function(json){
        makeListTmpl(json);
        
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: urlGenerosAmbiente,    // busca a lista de generos que está relacionada com o ambiente 
            dataType: 'json'
        }).done( function(json){
            
            var lista = json.rows;

            $.each( lista, function( idx, obj ){
                $('#genero-'+obj.idGenero).prop('checked', true);
            });
            
        });
        
    } );
}


var salvarGeneros = function()
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
    
    var urlGenerosAmbiente = buildUrl( "/api/ambientes/{idAmbiente}/generos", { 
        idAmbiente: idAmbiente
    });
    
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: urlGenerosAmbiente,
        dataType: 'json',
        data : dados 
    }).done( function(json){
        if (json.ok != null){

            geraNovaProgramacaoMusical();

            $('#myModal').modal('hide');
        }
        else{
            preencheErros( json.errors );
        }
    } );
    
}


var geraNovaProgramacaoMusical = function(){
    
    var urlTransmissao = buildUrl( "/api/ambientes/{idAmbiente}/transmissoes/new", { 
        idAmbiente: idAmbiente
    });
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: urlTransmissao,  
        dataType: 'json'
    });
}


var makeListTmpl = function(json){
    
    var tmpl = $.templates('#viewTmpl');
    
    $('#view-container').empty();
    
    var content = tmpl.render(json.rows);
    
    $('#view-container').append(content);
};

$(function(){

    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    
    listaGeneros(false);
    
    $('#btnSalvarGeneros').on('click', function(){
        salvarGeneros();
    });
    
});

