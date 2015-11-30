

var $table = $('#table-conversas');

function queryParamsConversas(params) {

    params.pageNumber = $table.bootstrapTable('getOptions').pageNumber;
    
    return params;
}

function ultimaMsgFormatter(value, row) {
    
    if ( row != null && row.mensagens != null && row.mensagens.length > 0 )
    {
        var mensagens = row.mensagens;
        
        var ultima = mensagens[mensagens.length-1];
    }
    
    return ultima;
}


var carregaMensagens = function( e, row, el )
{
    if ( row == null )
        return;
    
    var url = buildUrl( "/conversas/{idConversa}/mensagens", { 
        idConversa: row.idConversa 
    });

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        
        makeListTmpl( json );
        
        $("#conversa").scrollTop($("#conversa")[0].scrollHeight);
        
    });
}



var makeListTmpl = function(json){
    
    var tmpl = $.templates('#viewTmplMensagem');
    
    var content = tmpl.render(json.rows);
    
    $('#container').append(content);
};



var validaForm = function(){
    
    var isOk = true;
    
    removeErros( $('#usuario-form') );
    
    var arrayCampos = [
                        {field: "conteudo",           desc : "Conteúdo"}
                      ];
    
    isOk = validaCampos( arrayCampos );
    
    return isOk;
};



var salvar = function(){
    
    if ( validaForm() ){

        var dados = JSON.stringify( $('#mensagem-form').serializeJSON() );
        
        var url = buildUrl( "/usuarios" );

        $.ajax({
            
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: 'json',
            data:  dados
            
        }).done( function(json){ 

            if (json.ok == 1){
                
                jump(''); // topo da pagina
            }
            else{
                preencheErros( json.errors );
            }
        });
    }
    
};

$(function(){
    
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
   
    $('#btnSalvar').on('click', salvar);
    
//    getDados( $('#idUsuario').val() );
    
    $('#table-conversas').on('click-row.bs.table', function( e, row, el ){
        carregaMensagens( e, row, el );
    });

    
});
