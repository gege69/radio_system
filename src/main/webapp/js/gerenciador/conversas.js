

var $table = $('#table-conversas');

function queryParamsConversas(params) {

    params.pageNumber = $table.bootstrapTable('getOptions').pageNumber;
    
    return params;
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
        
        $("#idConversa").val( row.idConversa );
        
        $("#conversa").scrollTop($("#conversa")[0].scrollHeight);
        
    });
}



var makeListTmpl = function(json){
    
    $('#conversa').empty();
    
    var tmpl = $.templates('#viewTmplMensagem');
    
    var content = tmpl.render(json.rows);
    
    $('#conversa').append(content);
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
