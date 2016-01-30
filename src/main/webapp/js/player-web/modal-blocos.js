

var salvar = function(){

    var url = buildUrl( "/api/ambientes/{idAmbiente}/blocos", { 
        idAmbiente: idAmbiente
    });
    
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  JSON.stringify( $('#ambiente-bloco-form').serializeJSON() )
        
    }).done( function(json){ 

        if (json.ok == 1 ){
            preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
            jump(''); // topo da pagina
        }
        else{
            preencheErros( json.errors );
        }
    });
};

var getDados = function()
{
    var url = buildUrl( "/api/ambientes/{idAmbiente}/blocos", { 
        idAmbiente: idAmbiente
    });
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        
        removeErros( $('#ambiente-bloco-form') );
         $('#ambiente-bloco-form').populate(json);
        jump('ambiente-bloco-form');
    });
}

$(function(){

    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    
    $('#btnSalvarBloco').on('click', salvar);
    
    getDados();
});

