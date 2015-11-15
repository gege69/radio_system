
var listaFuncionarios = function(){
    
    var url = buildUrl( "/api/ambientes/{idAmbiente}/midias-por-categoria?codigo=chamada_func_nome", { 
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
        $('#funcionario').empty();
        
        $.each( lista, function( idx, obj ){
            listitems += '<option value=' + obj.idMidia + '>' + obj.descricao + '</option>';
        });
        
        $('#funcionario').append(listitems);
    } );
}

var listaFrase = function(){
    
    var url = buildUrl( "/api/ambientes/{idAmbiente}/midias-por-categoria?codigo=chamada_func_nome", { 
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
        $('#frase').empty();
        
        $.each( lista, function( idx, obj ){
            listitems += '<option value=' +  obj.idMidia + '>' + obj.descricao + '</option>';
        });
        
        $('#frase').append(listitems);
    } );
}

var confirma = function()
{
    var primeiro = $('#funcionario').val();
    var segundo = $('#frase').val();
    
    if ( primeiro == null || primeiro == undefined )
    {
        preencheErroField( "funcionario", "Preencha o campo" );
        return;
    }
    
    if ( segundo == null || segundo == undefined )
    {
        preencheErroField( "frase", "Preencha o campo" );
        return;
    }
    
    var array = [];
    array[0] = primeiro;
    array[1] = segundo;
    
    playSequence( array );
    $('#myModal').modal('hide');
}


$(function(){

    listaFuncionarios();
    listaFrase();
    
    $("#botaoConfirma").click( function(){
        confirma();
    });          
    
});

