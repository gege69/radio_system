
var $table = $('#tablePerfis');
var $tablePermissoes = null;

function queryParamsPerfis(params) {

    params.pageNumber = $table.bootstrapTable('getOptions').pageNumber;
    
    return params;
}


function queryParamsPermissoes(params) {

    params.pageNumber = $('#tablePermissoes').bootstrapTable('getOptions').pageNumber;
    
    return params;
}



var limpaSelecaoGrid = function()
{
    $tablePermissoes.bootstrapTable('uncheckAll');
}


var copia_dados = [];

var getDados = function( id )
{
    if ( id == null || id == undefined )
        alert('Id não encontrado');
   
    if ( $tablePermissoes == null ){

        $tablePermissoes = $('#tablePermissoes');
        $tablePermissoes.bootstrapTable({
            queryParams : queryParamsPermissoes
        }); 
    }

    var url = buildUrl( "/perfis/{idPerfil}/permissoes", { 
        idPerfil: id
    });

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        
        limpaSelecaoGrid();
        
        if ( json != null && json.rows != null )
        {
            var ids = [];
            copia_dados = [];
            $(json.rows).each(function(){
                perfilPermissao = this;
                ids.push( perfilPermissao.permissao.idPermissao );
                copia_dados.push( perfilPermissao.permissao.idPermissao );
            });

            $("#tablePermissoes").bootstrapTable('checkBy', {field:'idPermissao', values: ids } );
        }
    });
}




var salvarPermissao = function(){
    
    var idPerfil = $("#idPerfil").val();

    if ( idPerfil == null || idPerfil == "" ){
        preencheAlertGeral( "alertArea", "É necessário selecionar um Perfil na tabela da esquerda.");
        return;
    }
    
    var url = buildUrl( "/perfis/{idPerfil}/permissoes", {
        idPerfil : idPerfil,
    });
    
    var selecao = $tablePermissoes.bootstrapTable('getSelections');
    
    var arr_permissoes = [];

    $(selecao).each(function(){
        var linha = this;
        arr_permissoes.push( linha.idPermissao );
    });

    var json = { idPerfil : idPerfil, idPermissoes : arr_permissoes };
    var dados = JSON.stringify( json );

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data : dados
    }).done( function(json){ 
        
        if ( json.ok == 1 ){
            $('#alertArea').empty();

            preencheAlertGeral( "alertArea", "Registro salvo com sucesso", "success" );
        }
        if (json.ok == null || json.ok != 1){
            preencheErros( json.errors );
        }
    });
    
};



$(function(){
    
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    
    $table.on('click-row.bs.table', function( e, row, el ){
        $("#idPerfil").val( row.idPerfil );

        $("#indicador").show();
        $("#indicador").html( row.nome );
            
        getDados(row.idPerfil);
    }); 

    $('#password').keyup( function( event ) {
        keyup_validasenha( $("usuario-form"), event );
    });
    
    $("#btnSalvarPermissoes").click( function() {
        salvarPermissao();
    });

    $("#mostrarSenha").click(function(){
        if ( $("#mostrarSenha").prop('checked') ){
            $("#matchingPassword").attr("type", "input");
            $("#password").attr("type", "input");
        }
        else {
            $("#matchingPassword").attr("type", "password");
            $("#password").attr("type", "password");
        }
    }); 
});
