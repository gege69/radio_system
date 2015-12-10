

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
        $("#dataVigenciaInicio").val( row.dataVigenciaInicio );
        $("#dataVigenciaFim").val( row.dataVigenciaFim );
        
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



var salvar = function(){
    
    var dados = JSON.stringify( $('#form-inicio-conversa').serializeJSON() );

    var selecao = $tableparticipantes.bootstrapTable('getSelections');
    
    if ( selecao.length <= 1 )
    {
        preencheAlertGeral("alertArea", "Escolha ao menos 2 participantes para essa conversa.", "danger");
        return false;
    }
    
    var countPlayers = 0;
    
    $(selecao).each(function(){
        var linha = this;

        if ( linha.usuarioTipo == "PLAYER" )
            countPlayers++;
    });
    
    if ( countPlayers > 1 )
    {
        preencheAlertGeral("alertArea", "Escolha apenas 1 ambiente para conversar.", "danger");
        return false;
    }
    
    
    
    var url = buildUrl( "/conversa" );

    $.ajax({
        
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  dados
        
    }).done( function(json){ 

        if (json.idConversa != null){
            
            $("#idConversa").val( json.idConversa );
        }
        else{
            preencheErros( json.errors );
        }
    });
    
};

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


var iniciaConversa = function()
{
    salvar();
    
    mostraMensagens();
    
    $("#conversa").scrollTop($("#conversa")[0].scrollHeight);
}


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
   
    $('#btnSalvar').on('click', salvar);
    
    $('.input-group.date').datepicker({
        format: "dd/mm/yyyy",
        clearBtn: true,
        language: "pt-BR",
        todayBtn : "linked",
        autoclose : true
    });
    
    $('#table-conversas').on('click-row.bs.table', function( e, row, el ){
        carregaMensagens( e, row, el );
    });
    
    $('#table-participantes').on('click-row.bs.table', function( e, row, el ){
        marcaLinha( e, row, el );
    });
    
    $('#btnNovaMensagem').on('click', function(){
        mostraSelecaoParticipantes();        
    });
    
    $('#btnIniciar').click( function(){ 
       iniciaConversa();       
    });

    
});
