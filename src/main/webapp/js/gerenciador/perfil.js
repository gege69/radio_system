
var $table = $('#tablePerfis');
var $tablePermissoes = $('#tablePerfis');

function queryParamsPerfis(params) {

    params.pageNumber = $table.bootstrapTable('getOptions').pageNumber;
    
    return params;
}


function queryParamsPermissoes(params) {

    params.pageNumber = $tablePermissoes.bootstrapTable('getOptions').pageNumber;
    
    return params;
}



var limpaSelecaoGrid = function()
{
    $tablePermissoes.bootstrapTable('uncheckAll');
}


var getDados = function( id )
{
    if ( id == null || id == undefined )
        alert('Id não encontrado');
    
    $tablePermissoes.bootstrapTable('refresh');

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
        
        if ( json != null )
        {
            var ids = [];
            $(json).each(function(){
                perfilPermissao = this;
                ids.push( perfilPermissao.permissao.idPermissao );
            });
            
            $tablePermissao.bootstrapTable('checkBy', {field:'idPermissao', values: ids } );
        }
    });
}



var salvarPermissao = function( idPermissao ){
    
    var idPerfil = $("#idPerfil").val();
    
    var url = buildUrl( "/perfis/{idPerfil}/permissoes/{idPermissao}", {
        idPerfil : idPerfil,
        idPermissao : idPermissao
    } );

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  dados
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
        getDados(row.idPerfil);
    }); 
    
    $('#password').keyup( function( event ) {
        keyup_validasenha( $("usuario-form"), event );
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
