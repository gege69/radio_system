
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


function queryParamsMonitoramento( params ){
    
    params.pageNumber = $("#tableMonitoramento").bootstrapTable('getOptions').pageNumber;
    
    params.idCategoria = $("#tipo").val();
    params.dataInicio = $("#dataInicio").val();
    params.dataFim = $("#dataFim").val();
    
    return params;
}


var colunas = [
//               {
//                   field : "idTransmissao",
//                   title : "id"
//               },
               {
                   field : "categoria",
                   title : "Categoria",
                   formatter : "categoriaFormatter"
               },
               {
                   field : "dataPrevisaoPlay",
                   title : "Data/Hora prevista"
               },
               {
                   field : "dataFinishPlay",
                   title : "Data/Hora término",
               },
               {
                   field : "statusPlayback",
                   title : "Status",
                   formatter : "statusFormatter"
               },
               {
                   field : "midia.descricao",
                   title : "Descrição"
               },
               {
                   field : "midia.nome",
                   title : "Arquivo"
               }
               
               ];

var buscar = function()
{
    var datasOk = validaDatas();

    if ( datasOk )
    {
        var url = buildUrl( "/ambientes/monitoramento" );

        $("#tableMonitoramento").bootstrapTable("destroy").bootstrapTable({
            url : url,
            columns : colunas
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
