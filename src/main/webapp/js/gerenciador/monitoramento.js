
var idAmbiente = $('#idAmbiente').val();

var buscou = false;

var validaDatas = function(){
    
    var dataInicioVal = $("#dataInicio").val();
    var dataFimVal = $("#dataFim").val();

    if ( dataInicioVal == null || dataInicioVal == '' ){
        preencheAlertGeral("alertArea", "Data de Início é obrigatória");
        return false;
    }

    if ( dataFimVal == null || dataFimVal == '' ){
        preencheAlertGeral("alertArea", "Data Fim é obrigatória");
        return false;
    }
    
    return true;
}


function statusFormatter(value, row, index){
    
    var result = "";
    
    if (value == false)
        result = '<div class="divAlerta alert alert-danger">OFFLINE</div>';
    else if (value == true)
        result = '<div class="divAlerta alert alert-success">ONLINE</div>';
        
    return result;
}


function nomeFormatter(value, row, index) {

    var texto = value;
    if ( row.ativo == false )
        texto = texto + " (inativo)";
    
    var url = buildUrl( "/ambientes/{idAmbiente}/view", { idAmbiente : row.idAmbiente } );
    
    return '<a class="" href="'+url+'">' + texto + '</a>';
}

function queryParamsMonitoramento( params ){
    
    params.pageNumber = $("#tableMonitoramento").bootstrapTable('getOptions').pageNumber;
    
    params.tipo = $("#tipo").val();
    params.dataInicio = $("#dataInicio").val();
    params.dataFim = $("#dataFim").val();
    
    return params;
}


var colunas = [
               {
                   field : "nome",
                   title : "Ambiente",
                   formatter : "nomeFormatter"
               },
               {
                   field : "login",
                   title : "Login"
               },
               {
                   field : "telefone1",
                   title : "Telefone"
               },
               {
                   field : "statusMonitoramento",
                   title : "Status",
                   formatter : "statusFormatter"
               }
               
               ];

var colunasDetalhe = [
               {
                   field : "dataCriacao",
                   title : "Data Login"
               },
               {
                   field : "dataLogout",
                   title : "Data Logout"
               }
               ];


function expandTable(index, row, $detail){
    
    var $el = $detail.html('<div class="col-lg-6 col-md-6"><table></table></div><div class="col-lg-6 col-md-6"><div class="pull-right"></div></div>').find('table');
    
    $el.bootstrapTable({
        data : row.detalhesMonitoramento,
        columns : colunasDetalhe
    });
    
}


var buscar = function()
{
    var datasOk = validaDatas();

    if ( datasOk )
    {
        var url = buildUrl( "/monitoramento" );

        $("#tableMonitoramento").bootstrapTable("destroy").bootstrapTable({
            url : url,
            columns : colunas,
            onExpandRow: function (index, row, $detail) {
                expandTable(index, row, $detail);
            },
            onPostBody : function(data){
                buscou = true;
            }
        });
    }
}

var csv = function()
{
    var datasOk = validaDatas();

    if ( datasOk )
    {
        var path = { idAmbiente : idAmbiente };
        var search = {
                tipo : $("#tipo").val(),
                dataInicio : $("#dataInicio").val(),
                dataFim : $("#dataFim").val()
        };
        
        var url = buildUrl( "/api/monitoramento/csv", path, search );
        
        window.open( url, '_blank' );
    }
}



function timeout() {
    setTimeout(function () {
        
        if (buscou)
            buscar();
        
        timeout();
    }, 60000);
}


$(function(){

    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    
    $('#btnGerar').on('click', function(){
        buscar();
    });
    
    $('.input-group.date').datepicker({
        format: "dd/mm/yyyy",
        clearBtn: true,
        language: "pt-BR",
        todayBtn : "linked",
        autoclose : true
    });

    $('#btnGeraCsv').on('click', function(){
        csv();
    });

    $('.input-group.date').datepicker('update', new Date());
    
    
    timeout();

});
