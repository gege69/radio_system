var maskBehaviorTel = function (val) {
    return val.replace(/\D/g, '').length === 11 ? '00000-0000' : '0000-00009';
};
  
var optionsTel = {onKeyPress: function(val, e, field, options) {
          field.mask(maskBehaviorTel.apply({}, arguments), options);
      }
};


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


//Tabela de Titulos( pagamentos )
var $tablePag = $('#tablePagamentosTitulos');

function queryParamsPag(params) {
    params.pageNumber = $('#tablePagamentosTitulos').bootstrapTable('getOptions').pageNumber;
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
            
            $('#idTipoTaxa').append($('<option>', { 
                value: tx.idTipotaxa,
                text : descricao  
            }));
        });
        
        jump('ncmForm');
    });
};


var salvarCondicaoComercial = function(){
    
    if ( validaForm() ){
        
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
                $('#myModalCondicaoComercial').modal('hide');
                preencheErros( json.errors );
            }
        });
    }
    
};


var editCondicaoComercial = function( e, row, el )
{
    var rowcopy = extend( row );

    var tipoTaxa = rowcopy.tipoTaxa;
    if ( tipoTaxa != null )
        $('#idTipoTaxa').val( tipoTaxa.idTipotaxa );
    
    formReset( $('#formCondicaoComercial') );

    $("#idClienteModal").val($("#idCliente").val());
   
    $('#formCondicaoComercial').populate(rowcopy);
}


var abreModalCondicaoComercial = function(){ 
    
    getTipoTaxas();
  
    $("#idClienteModal").val($("#idCliente").val());

    $("#definicaoTaxa").val('VALOR');
    
    $("#myModalCondicaoComercial").modal({
        show:true, 
        backdrop: 'static',              
        keyboard: false
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
    }

    $("#btnInserirCondicaoComercial").click( function() {
        formReset($("#formCondicaoComercial"));

        abreModalCondicaoComercial();
    });

    $('#tableCondicoesComerciais').on('click-row.bs.table', function( e, row, el ){
        abreModalCondicaoComercial();
        editCondicaoComercial( e, row, el );
    });
    

    $("#btnSalvarCondicao").click( function() {
        salvarCondicaoComercial();
    });

});
