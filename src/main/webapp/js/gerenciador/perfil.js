
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
    // percorrer a grid de permissoes limpando....
}


var getDados = function( id )
{
    if ( id == null || id == undefined )
        alert('Id não encontrado');
    
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



var salvar = function( id ){
    
    var selecao = $table.bootstrapTable('getSelections');
    
    var url = buildUrl( "/perfis/{idPerfil}/permissoes/{idPermissao}", {
        idPerfil : idPerfil
    } );

    $.ajax({
        
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  dados
        
    }).done( function(json){ 

        if (json.ok == 1){
            preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
            jump(''); // topo da pagina
        }
        else{
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
   
    $('#btnSalvar').on('click', salvar);
    
//    getDados( $('#idUsuario').val() );
    
    $table.on('load-success.bs.table', function( e, data ){
        loadSuccessGrid();
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
