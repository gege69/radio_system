var maskBehaviorTel = function (val) {
    return val.replace(/\D/g, '').length === 11 ? '00000-0000' : '0000-00009';
};
  
var optionsTel = {onKeyPress: function(val, e, field, options) {
          field.mask(maskBehaviorTel.apply({}, arguments), options);
      }
};


function perfisUsuarioFormatter(value, row) {

    var result = '';
    
    if ( value ) {
        for (var i=0; i < value.length; i++){
            
            if ( result.length > 0 )
                result += ', ';
            
            result += value[i].nome;
        }
    }

    return result;
}


function senhaUsuarioFormatter(value, row){
    return '<a class="btn btn-link senha-class" idUsuario="'+ row.idUsuario +'" href="#"> <i class="fa fa-lg fa-unlock-alt"></i></a>';
}


function abrePopupAlterarSenha( element ){

    var idUsuario = element.attr("idUsuario");
    
    if ( idUsuario == null || idUsuario == "0" ){
        preencheAlertGeral("alertArea", "Usuário não encontrado" );
        return;
    }
    
    var row = $('#tableUsuarios').bootstrapTable('getRowByUniqueId', idUsuario);

    $("#idUsuario").val(row.idUsuario);
    $("#login").val(row.login);
    
    $("#myModalAlterarSenha").modal('show');
}


function alterarSenha(){

    var dados = $('#alteraSenhaForm').serializeJSON();
    
    var url = buildUrl( "/usuarios/{idUsuario}/senha", { 
        idUsuario : dados.idUsuario
    } );

    var dados = JSON.stringify( dados );
    
    $("#password").val('');
    $("#matchingPassword").val('');
    $("#mostrarSenha").prop('checked', false)
    $("#matchingPassword").attr("type", "password");
    $("#password").attr("type", "password");
    keyup_validasenha( $("alteraSenhaForm"), event );

    $.ajax({
        
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  dados
        
    }).done( function(json){ 
        
        if (json.ok == 1){
            preencheAlertGeral( "alertArea", "Senha alterada com sucesso.", "success" );
            $('#myModalAlterarSenha').modal('toggle');
            jump(''); // topo da pagina
        }
        else{
            preencheErros( json.errors, "alertAreaSenha" );
        }
    });
}



//Tabela de Condições Comerciais
var $tableCC = $('#tableCondicoesComerciais');

function queryParamsCondicoesComerciais(params) {

    params.pageNumber = $tableCC.bootstrapTable('getOptions').pageNumber;
     
    return params;
};


function valorFormatter(index, row) {
 
    var stringSimbolo = "";
     
    if ( row.definicaoTaxa == "PORCENTAGEM")
        stringSimbolo = " %";
    else
        stringSimbolo = " R$";
    
    return row.valor + stringSimbolo;
};


function descricaoTaxaFormatter(index, row) {
    
    var descricao = row.tipoTaxa.descricao;
 
    if ( row.tipoTaxa.ativo == false )
        descricao = descricao + " (inativa)";

    return descricao;
};



//Tabela de Titulos( pagamentos )
var $tablePag = $('#tablePagamentosTitulos');

function queryParamsPag(params) {
    params.pageNumber = $('#tablePagamentosTitulos').bootstrapTable('getOptions').pageNumber;
    return params;
};


var editTitulo = function( e, row, el )
{
    if ( row == null ) 
        return;
    
    if ( row.idTitulo == null || row.idTitulo == 0 )
    {
        preencheAlertGeral("alertArea", "Titulo não foi selecionado corretamente.", "danger");
        return false;
    }
    else
    {
        var url = buildUrl( "/titulos/{idTitulo}/view", { 
            idTitulo: row.idTitulo
        }, {
            idCliente: $("#idCliente").val()
        });
        window.location = url;
    }
}



//Tabela de Usuários
var $tableUsuarios = $('#tableUsuarios');

function queryParamsUsuarios(params) {
    params.pageNumber = $tableUsuarios.bootstrapTable('getOptions').pageNumber;
    return params;
};



var getDados = function()
{
    var url = buildUrl( "/clientes/{idCliente}", {
        idCliente : $("#idCliente").val(),
    }); 
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        
        removeErros();
        
        var telefones = json.telefones;
       
        delete json["telefones"];
        
        $('#clienteform').populate(json);
        
        $('.linha-telefone').remove()
        
        $.each(telefones, function (key, tel) {
            if ( key == 0)
            {
                $('#ddd0').val( tel.ddd );
                $('#numero0').val( tel.numero );
                $('#idTelefone0').val( tel.idTelefone );
            }
            else
                makeListTmpl({rows:[{id: tel.idTelefone, ddd: tel.ddd , numero: tel.numero }]});
        });
        
        jump('ncmForm');
    });
};


var getResumo = function()
{
    var url = buildUrl( "/clientes/{idCliente}/resumo", {
        idCliente : $("#idCliente").val(),
    }); 
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        $('#formResumo').populate(json);
    });

};



var validaForm = function(){
    
    var isOk = true;
    
    removeErros();
    
    var arrayCampos = [
                        {field: "razaosocial",      desc : "Razão Social"},
                        {field: "nomefantasia",     desc : "Nome Fantasia" } 
                      ];
    
    isOk = validaCampos( arrayCampos );
    
    return isOk;
};

var salvar = function(){
    
    if ( validaForm() ){
        
        var dados = JSON.stringify( $('#clienteform').serializeJSON() );
        
        var url = buildUrl( "/clientes" ); 

        $.ajax({
            
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: 'json',
            data: dados 
            
        }).done( function(json){ 

            if ( json.idCliente && json.idCliente > 0){
                
                $("#idCliente").val( json.idCliente );
                preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
                jump(''); // topo da pagina
            }
            else{
                preencheErros( json.errors );
            }
        });
    }
    
};

var makeListTmpl = function(json){
    
    var tmpl = $.templates('#viewTmplTelefones');
    
    var content = tmpl.render(json.rows);
    
    $('#containertelefones').append(content);
    
    $(".phone").mask(maskBehaviorTel, optionsTel);
    $(".ddd").mask('000');        
    
    refreshLinkRemoverTelefone();
};

var removerTelefone = function( element )
{
    var $div = element.parents('.linha-telefone');
    
    $div.remove();
    
    refreshLinkRemoverTelefone();
};

var addTelefone = function() {
    
    event.preventDefault();
    makeListTmpl({rows:[{id: "", ddd: "", numero: ""}]});
};

var refreshLinkRemoverTelefone = function() {
    $('.removertelefone').off('click');
    $('.removertelefone').on('click', function(event){
        removerTelefone( $(this) );
     });
};

var getTipoTaxas = function()
{
    var url = buildUrl( "/clientes/{idCliente}/tipotaxas", {
        idCliente : $("#idCliente").val(),
    }); 
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        
        $("#idTipoTaxa").empty();
        
        $.each(json.rows, function (i, tx) {
            
            var descricao = '';
            
            if ( tx.por_ambiente )
                descricao = tx.descricao + " (por ambiente)";
            else
                descricao = tx.descricao;

            if ( tx.ativo == false )
                descricao = descricao +" (inativa)";
            
            $('#idTipoTaxa').append($('<option>', { 
                value: tx.idTipotaxa,
                text : descricao  
            }));
        });
        
        jump('ncmForm');
    });
};


var salvarCondicaoComercial = function(){
    
    var dados = JSON.stringify( $('#formCondicaoComercial').serializeJSON() );
    
    var url = buildUrl( "/clientes/{idCliente}/condicoescomerciais", {
        idCliente : $("#idCliente").val(),
    }); 

    $.ajax({
        
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data: dados 
        
    }).done( function(json){ 

        if ( json.idCondcom && json.idCondcom > 0){
            preencheAlertGeral( "alertArea", "Registro de Condição Comercial salvo com sucesso.", "success" );
            $('#tableCondicoesComerciais').bootstrapTable('refresh');
            $('#myModalCondicaoComercial').modal('hide');
        }
        else{
            preencheErros( json.errors );
        }
    });

};


var editCondicaoComercial = function( e, row, el )
{
    var rowcopy = extend( row );

    formReset( $('#formCondicaoComercial') );

    $("#idClienteModal").val($("#idCliente").val());
    
    $('#formCondicaoComercial').populate(rowcopy);

}


var abreModalCondicaoComercial = function(){ 
   
  
    $("#idClienteModal").val($("#idCliente").val());

    $("#definicaoTaxa").val('VALOR');
    
    $("#myModalCondicaoComercial").modal({
        show:true, 
        backdrop: 'static'              
    });
};



$(function(){

    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
   
    $('#btnSalvar').on('click', salvar);
    
    $(".phone").mask(maskBehaviorTel, optionsTel);
    $(".ddd").mask('000');        
    $(".cnpj").mask('00.000.000/0000-00'); 
    $(".inteiro").mask('000');        
    $(".money").mask('000.000,00', {reverse: true});
    
    var idCliente = $("#idCliente").val();
    
    if ( idCliente != null && idCliente > 0 ){
        getDados();
        getResumo();
    }
    
    $("#linkaddtelefone").click( function() {
        addTelefone(); 
    });
    
    refreshLinkRemoverTelefone();
    
    $("#abas li:eq(0) a").tab('show'); 

    if ( idCliente != null && idCliente > 0 ){
        $('#tablePagamentosTitulos').bootstrapTable({
            queryParams : queryParamsPag
        });

        $('#tableCondicoesComerciais').bootstrapTable({
            queryParams : queryParamsCondicoesComerciais
        });

        $('#tableUsuarios').bootstrapTable({
            queryParams : queryParamsUsuarios
        });
    }

    $("#btnInserirCondicaoComercial").click( function() {
        formReset($("#formCondicaoComercial"));

        abreModalCondicaoComercial();
    });

    $('#tableCondicoesComerciais').on('click-row.bs.table', function( e, row, el ){
        abreModalCondicaoComercial();
        editCondicaoComercial( e, row, el );
    });
    
    $('#tablePagamentosTitulos').on('click-row.bs.table', function( e, row, el ){
        editTitulo( e, row, el );
    });


    $("#btnSalvarCondicao").click( function() {
        salvarCondicaoComercial();
    });
    
    $("#btnConfirmarSenha").click( function() { 
        alterarSenha();
    });

    $("#tableUsuarios").on( 'load-success.bs.table', function( e, data ) {
        $(".senha-class").click( function(){
            abrePopupAlterarSenha($(this));
        });
    });
    
    $("#tableUsuarios").on( 'page-change.bs.table', function ( e, number, size ){
        $(".senha-class").click( function(){
            abrePopupAlterarSenha($(this));
        });
    });

    $('#password').keyup( function( event ) {
        keyup_validasenha( $("alteraSenhaForm"), event );
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

    getTipoTaxas();
});
