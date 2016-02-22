

var $table = $('#table-conversas');

var $tableparticipantes = $('#table-participantes');

function queryParamsConversas(params) {

    params.pageNumber = $table.bootstrapTable('getOptions').pageNumber;
    
    return params;
}

function queryParamsParticipantes(params) {

    params.pageNumber = $tableparticipantes.bootstrapTable('getOptions').pageNumber;
    
    params.sort = "nome";
    
    return params;
}


var carregaMensagensByGrid = function( e, row, el )
{
    if ( row == null )
        return;
    
    carregaMensagens( row );
    
    jump( "painel-mensagens" );
}


var carregaMensagens = function( record )
{
    var url = buildUrl( "/api/conversas/{idConversa}/mensagens", { 
        idConversa: record.idConversa 
    });

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        
        makeListTmpl( json );
        
        $("#idConversa").val( record.idConversa );
        $("#dataVigenciaInicio").val( record.dataVigenciaInicio );
        $("#dataVigenciaFim").val( record.dataVigenciaFim );
        
        $("#conversa").scrollTop($("#conversa")[0].scrollHeight);
        
        mostraMensagens();
    });
}



var makeListTmpl = function(json){
    
    $('#conversa').empty();
    
    var tmpl = $.templates('#viewTmplMensagem');
    
    var content = tmpl.render(json.rows);
    
    $('#conversa').append(content);
};


var addSingleTmpl = function(json){
    
    var tmpl = $.templates('#viewTmplMensagem');
    
    rows = [ json ];
    
    var content = tmpl.render( rows );
    
    $('#conversa').append(content);
};



var salvar = function(){
    
    var selecao = $tableparticipantes.bootstrapTable('getSelections');
    
    var idUsuarioAtual = $('#idUsuario').val();
    
    var count = 0;
    
    $(selecao).each(function(){
        var linha = this;

        if ( linha.idUsuario == idUsuarioAtual )
            return true;  // continue
        else
            count++;
    });
    
    if ( count < 1 )
    {
        preencheAlertGeral("alertArea", "Escolha ao menos 1 participantes para essa conversa. Você será incluído automaticamente.", "danger");
        return false;
    }
    
    var countPlayers = 0;
    
    var usuariosArray = [];
    
    $(selecao).each(function(){
        var linha = this;

        usuariosArray.push( { idUsuario : linha.idUsuario } );    
    });
    
    var url = buildUrl( "/api/conversas" );

    var conversa = $('#form-inicio-conversa').serializeJSON();
    conversa.usuarios = usuariosArray;

    console.log( conversa );
    
    console.log( JSON.stringify( conversa ) );
    
    $.ajax({
        
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  JSON.stringify( conversa )
        
    }).done( function(json){ 

        if (json.idConversa != null){
            
            $("#idConversa").val( json.idConversa );
            
            $table.bootstrapTable('refresh');
            
            carregaMensagens( json )
            
            mostraMensagens();
        }
        else{
            preencheErros( json.errors );
        }
    });
    
    return true;
    
};


var enviarMensagem = function(){
    
    var conteudo = $('#conteudo').val();
    
    if ( conteudo == "" || conteudo == null )
        return;
    
    var mensagem = $('#form-mensagem').serializeJSON();
    
    mensagem.conversa = { idConversa : mensagem.idConversa };   // para o jackson ler direto no VO

    var url = buildUrl( "/api/conversas/{idConversa}/mensagens", { 
        idConversa: $("#idConversa").val()
    });
    
    $.ajax({
        
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  JSON.stringify( mensagem )
        
    }).done( function(json){ 

        if (json.idMensagem != null){
            
            $('#conteudo').val('');
            
            addSingleTmpl( json );
            $("#conversa").scrollTop($("#conversa")[0].scrollHeight);
            
            jump( "painel-mensagens" );
        }
        else{
            preencheErros( json.errors );
        }
    });
    
}


var habilitaNovaMensagem = function(){
    
    $('#btnNovaMensagem').removeClass('btn-default');
    $('#btnNovaMensagem').addClass('btn-primary');
}

var desabilitaNovaMensagem = function(){
    
    $('#btnNovaMensagem').removeClass('btn-primary');
    $('#btnNovaMensagem').addClass('btn-default');
}


var mostraSelecaoParticipantes = function(){

    desabilitaNovaMensagem();
    
    $tableparticipantes.bootstrapTable('uncheckAll');
    
    $tableparticipantes.bootstrapTable('refresh');
    
    $('#selecao-participantes').show();
    $('#painel-mensagens').hide();
    
    
};

var mostraMensagens = function(){

    habilitaNovaMensagem();
    
    $tableparticipantes.bootstrapTable('uncheckAll');
    $tableparticipantes.bootstrapTable('refresh');
    
    $('#selecao-participantes').hide();
    $('#painel-mensagens').show();
    
};


var marcaLinha = function( e, row, el )
{
    var index = $(el).attr('data-index');

    if ( index != null && index >= 0 )
        $tableparticipantes.bootstrapTable('check', index); 
}


$(function(){
    
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
   
    $('.input-group.date').datepicker({
        format: "dd/mm/yyyy",
        clearBtn: true,
        language: "pt-BR",
        todayBtn : "linked",
        autoclose : true
    });
    
    $('#table-conversas').on('click-row.bs.table', function( e, row, el ){
        carregaMensagensByGrid( e, row, el );
    });
    
    $('#table-participantes').on('click-row.bs.table', function( e, row, el ){
        marcaLinha( e, row, el );
    });
    
    $('#btnNovaMensagem').on('click', function(){
        mostraSelecaoParticipantes();        
    });
    
    $('#btnIniciar').click( function(){ 
        salvar();       
    });

    $('#btnEnviarMensagem').on('click', function(){
        enviarMensagem();        
    });
    
    
    
    
});
