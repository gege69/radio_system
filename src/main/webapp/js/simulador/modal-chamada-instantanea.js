var listaChamadas = function(){
    
    var url = buildUrl( "/api/ambientes/{idAmbiente}/midias-por-categoria?codigo=chamada_inst", { 
        idAmbiente: idAmbiente
    });
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json){
        
        var lista = json.rows;
        
        var listitems = "";
        $('#chamada_inst').empty();
        
        $.each( lista, function( idx, obj ){
            listitems += '<option value=' + obj.idMidia + '>' + obj.descricao + '</option>';
        });
        
        $('#chamada_inst').append(listitems);
    } );
}

var confirma = function()
{
    var primeiro = $('#chamada_inst').val();
    
    var array = [];
    array[0] = primeiro;
    
    playSequence( array );
    $('#myModal').modal('hide');
}


$(function(){
    
    listaChamadas();
    
    $("#botaoConfirma").click( function(){
        confirma();
    });          
    
});
