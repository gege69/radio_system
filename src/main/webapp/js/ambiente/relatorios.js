
var idAmbiente = $('#idAmbiente').val();


function categoriaFormatter(value, row, index){
    
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

function statusFormatter(value, row, index){
 
	var result = "";

    if (value == "GERADA")
        result = "Gerada"
    else if (value == "NAFILA")
        result = "Na fila"
    else if (value == "TOCANDO")
        result = "Tocando"
    else if (value == "FIM")
        result = "Tocada";
    
    return result;
}


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


function queryParamsRelatorio( params ){
    
    params.pageNumber = $("#tableRelatorio").bootstrapTable('getOptions').pageNumber;
    
    params.idCategoria = $("#combocategoria").val();
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
        var url = buildUrl( "/ambientes/{idAmbiente}/relatorios", {
            idAmbiente : idAmbiente
        });

        $("#tableRelatorio").bootstrapTable("destroy").bootstrapTable({
            url : url,
            columns : colunas
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
                idCategoria : $("#combocategoria").val(),
                dataInicio : $("#dataInicio").val(),
                dataFim : $("#dataFim").val()
        };
        
        var url = buildUrl( "/api/ambientes/{idAmbiente}/relatorios/csv", path, search );
        
        window.open( url, '_blank' );
    }
}


var getCategorias = function()
{
    var url = buildUrl( "/api/categorias?simpleUpload=true" );

    $('#combocategoria').empty();
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        
        $('#combocategoria').append('<option value="" selected>Todas as mídias</option>');
        
        $.each( json.rows , function (i, cat){
            $('#combocategoria').append($('<option>', { 
                value: cat.idCategoria,
                text : cat.descricao  
            }));
        });
    });
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

    $('#btnGeraCsv').on('click', function(){
        csv();
    });

    
    $('.input-group.date').datepicker({
        format: "dd/mm/yyyy",
        clearBtn: true,
        language: "pt-BR",
        todayBtn : "linked",
        autoclose : true
    });

    var d = new Date();
    d.setDate(d.getDate()-20);

    var f = new Date();
    f.setDate(f.getDate()+5);

    $('#datePickerInicio').datepicker('update', d);
    $('#datePickerFim').datepicker('update', f);
    
    getCategorias();
    
    $('#btnLimpar').on('click', function(){
        limparForm();
    })
    
    $('.campoForm').change(function(){
        $("#tableRelatorio").bootstrapTable("destroy");
    });


    
});
