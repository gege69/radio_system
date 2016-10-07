
var idAmbiente = $('#idAmbiente').val();

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
    
    var descricao = value.descricao;
    var codigo = value.codigo;

    var clazz = "";
    if (codigo == "comercial")
        clazz = "divCategoria alert alert-danger";
    else if (codigo == "inst")
        clazz = "divCategoria alert alert-warning";
    else if (codigo == "programete")
        clazz = "divCategoria alert alert-info";
    else if (codigo == "vinheta")
        clazz = "divCategoria alert alert-custom-grey";
        
    return '<span class="'+clazz+'">'+descricao+'</span>';
}


function queryParamsMonitoramento( params ){
    
    params.pageNumber = $("#tableMonitoramento").bootstrapTable('getOptions').pageNumber;
    
    params.idCategoria = $("#tipo").val();
    params.dataInicio = $("#dataInicio").val();
    params.dataFim = $("#dataFim").val();
    
    return params;
}


var colunas = [
//               {
//                   field : "idAmbiente",
//                   title : "id"
//               },
               {
                   field : "nome",
                   title : "Ambiente"
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
                   field : "idAmbiente",
                   title : "Status",
                   formatter : "statusFormatter"
               }
               
               ];


function expandTable(index, row, $detail){
    
    var $el = $detail.html('<table></table>').find('table');
    
    $el.bootstrapTable({
        data : row.
    });
    
    
}


var buscar = function()
{
    var datasOk = validaDatas();

    if ( datasOk )
    {
        var url = buildUrl( "/ambientes/monitoramento" );

        $("#tableMonitoramento").bootstrapTable("destroy").bootstrapTable({
            url : url,
            columns : colunas,
            onExpandRow: function (index, row, $detail) {
                expandTable(index, row, $detail);
            }
        });
    }
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

    $('.input-group.date').datepicker('update', new Date());

});
