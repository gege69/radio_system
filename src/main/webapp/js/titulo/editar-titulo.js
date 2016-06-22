

var getDados = function()
{
    var idTitulo = $("#idTitulo").val();
    
    if ( idTitulo == null || idTitulo == undefined || idTitulo == "" )
        return;
    
    var url = buildUrl( "/titulos/{idTitulo}", {
        idTitulo : idTitulo,
    }); 
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        removeErros();

        $('#tituloform').populate(json);

        $('#dataEmissaoDate').datepicker('update', data(json.dataEmissao));
        $('#dataVencimentoDate').datepicker('update', data(json.dataVencimento));
        $('#dataPagamentoDate').datepicker('update', data(json.dataPagamento));
        $('#dataCancelamentoDate').datepicker('update', data(json.dataCancelamento));

        $("#valorLiquido").maskMoney('mask', json.valorLiquido);
        $("#valorTaxas").maskMoney('mask', json.valorTaxas);
        $("#valorJuros").maskMoney('mask', json.valorJuros);
        $("#valorAcresc").maskMoney('mask', json.valorAcresc);
        $("#valorDescontos").maskMoney('mask', json.valorDescontos);
        $("#valorTotal").maskMoney('mask', json.valorTotal);
        $("#valorPago").maskMoney('mask', json.valorPago);
        
        var pago = ( json.valorPago != null && json.valorPago > 0 && json.dataPagamento != null );
        var cancelado = ( json.dataCancelamento != null && json.dataCancelamento != "" );
        
        if ( pago || cancelado ) {
            $("#btnCancelar").hide();
            var arrayCampos = $("#tituloform").find(':input').not(':button, :submit, :reset, :checkbox, :hidden, :radio');

            $.each( arrayCampos, function( index, value ){
                $(value).attr("readOnly", true);
            });
        }
        
        getCliente();
        jump('ncmForm');
    });
};

var data = function( strData ){
    var x = moment( strData, 'DD/MM/YYYY', true);
    return x.toDate();
}


var getCliente = function()
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
        $("#cliente").val( json.razaosocial );
        $("#cnpjCliente").val( json.cnpj );
    });
}



var validaForm = function(){
    
    var isOk = true;
    
    removeErros();
    
    var arrayCampos = [
                        {field: "dataEmissao",      desc : "Data Emissão"},
                        {field: "dataVencimento",      desc : "Data Vencimento"},
                        {field: "valorLiquido",      desc : "Valor Liquido"},
                        {field: "valorTotal",      desc : "Valor Total"}
                      ];
    
    isOk = validaCampos( arrayCampos );

    var valor = $("#valorLiquido").val();

    if ( valor === "0,00" ){
        preencheErroField( "valorLiquido", "Valor Líquido precisa ser maior que zero." );
        isOk = false;
    }
    
    return isOk;
};

var salvar = function(){
    
    if ( validaForm() ){
        
        var dados = JSON.stringify( $('#tituloform').serializeJSON() );
        
        var url = buildUrl( "/titulos" ); 

        $.ajax({
            
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: 'json',
            data: dados 
            
        }).done( function(json){ 

            if ( json.idTitulo && json.idTitulo > 0){
                
                $("idTitulo").val( json.idTitulo );
                preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
                jump(''); // topo da pagina
            }
            else{
                preencheErros( json.errors );
                jump(''); // topo da pagina
            }
        });
    }
};

function tiraPontuacao( valorString ) {
    
    var result = "";
    
    if ( valorString == null || valorString == undefined )
        return result;

    if ( valorString.indexOf("R$ ") > -1 )
        valorString = valorString.replace("R$ ", "");

    result = valorString.split(".").join("");
    result = result.split(",").join(".");
    
    return result;
}


function getValor( fieldId ){
    
    var result = $("#" + fieldId ).val();
    
    result = tiraPontuacao(result);
    
    if ( result == null || result == undefined )
        return 0;
    else if ( $.isNumeric( result ) )
        return parseFloat( result )
    else
        return 0;
};


function recalcularTudo(){
    
    var liquido = getValor( "valorLiquido" );
    var taxas = getValor( "valorTaxas" );
    var juros = getValor( "valorJuros" );
    var acresc = getValor( "valorAcresc" );
    var desconto = getValor( "valorDescontos" );

    var result = ( liquido + taxas + juros + acresc ) - desconto;
    
    $("#valorTotal").maskMoney( 'mask', result );
};


var openDialog = function(){ 
    $('#myDialog').modal('show');
}


var cancelarTitulo = function(){
    
    var dados = JSON.stringify( $('#tituloform').serializeJSON() );
    
    var url = buildUrl( "/titulos" ); 

    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data: dados 
        
    }).done( function(json){ 

        if ( json.ok && json.ok > 0){
            
            preencheAlertGeral( "alertArea", "Registro cancelado com sucesso.", "success" );
            jump(''); // topo da pagina
        }
        else{
            preencheErros( json.errors );
            jump(''); // topo da pagina
        }
    });

}


$(function(){

    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
   
    $('#btnSalvar').on('click', salvar);

    getDados();

    $(".cnpj").mask('00.000.000/0000-00'); 

//    $(".money").maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
    $(".money").maskMoney({prefix:'', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
    $(".money").on('blur', function(){
        recalcularTudo();
    });
    
    $("#valorTotal").maskMoney({ allowNegative: true, thousands:'.', decimal:',', affixesStay: false});

    $('.input-group.date').datepicker({
        enableOnReadonly : false,
        format: "dd/mm/yyyy",
        clearBtn: true,
        language: "pt-BR",
        todayBtn : "linked",
        autoclose : true
    });

    $('#dataEmissaoDate').datepicker('update', new Date());

    $("#btnCancelar").click( function(){
        openDialog();
    });

    $("#btnConfirmarCancelar").click( function(){
        cancelarTitulo();
    });

});
