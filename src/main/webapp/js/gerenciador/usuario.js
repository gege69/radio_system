

var $table = $('#table-perfis');

function queryParamsPerfis(params) {

    params.pageNumber = $table.bootstrapTable('getOptions').pageNumber;
    
    return params;
}


function loadSuccessGrid()
{
    getDados( $('#idUsuario').val() );

}


var getDados = function( id )
{
    if ( id == null || id == undefined )
        alert('Id não encontrado');
    
    var url = buildUrl( "/usuarios/{idUsuario}", { 
        idUsuario: id
    });

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        
        removeErros();
        $('#usuario-form').populate(json);
        
        if ( json.perfis != null )
        {
            var ids = [];
            $(json.perfis).each(function(){
                perfil = this;
                ids.push( perfil.idPerfil );
            });
            
            $table.bootstrapTable('checkBy', {field:'idPerfil', values: ids } );
        }
        
        jump('ncmForm');
    });
}

var validaForm = function(){
    
    var isOk = true;
    
    removeErros();
    
    var arrayCampos = [
                        {field: "nome",           desc : "Nome do Usuário"},
                        {field: "email",         desc : "Email" },
                        {field: "login",         desc : "Login"}
//                        {field: "password",      desc : "Senha"}
                      ];
    
    isOk = validaCampos( arrayCampos );
    
    return isOk;
};

var salvar = function(){
    
    if ( validaForm() ){

        var selecao = $table.bootstrapTable('getSelections');
        
        if ( selecao.length <= 0 )
        {
            preencheAlertGeral("alertArea", "Escolha pelo menos um perfil para o usuário.", "danger");
            return false;
        }
        
        var dados = $('#usuario-form').serializeJSON();
        
        dados["perfis"] = selecao;
        
        var dados = JSON.stringify( dados );
        
        var url = buildUrl( "/usuarios" );

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
    
    $table.on('load-success.bs.table', function( e, data ){
        loadSuccessGrid();
    });
    
});
