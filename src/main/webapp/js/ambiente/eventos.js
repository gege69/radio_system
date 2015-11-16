
var $table = $('#table-eventos');

var idAmbiente = $('#idAmbiente').val();

function queryParamsEventos(params) {

    params.pageNumber = $table.bootstrapTable('getOptions').pageNumber;
    
    return params;
}

function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}


var preencheTela = function()
{
    
}

var validaForm = function(){
    
    var isOk = true;
    
    removeErros( $('#form-evento') );
    
    var arrayCampos = [
                        {field: "combocategoria",       desc : "Tipo de Mídia"},
                        {field: "combomidia",           desc : "Mídia" } 
                      ];
    
    isOk = validaCampos( arrayCampos );
    
    return isOk;
};

var salvar = function()
{
    if ( validaForm() )
    {
        var url = buildUrl( "/ambientes/{idAmbiente}/eventos", {
            idAmbiente : idAmbiente
        });
        
        $.ajax({
            
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: 'json',
            data:  JSON.stringify( $('#form-evento').serializeJSON() )
            
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
}

var makeListTmpl = function(json){
    
    var tmpl = $.templates('#viewTmplHorarios');
    
    var content = tmpl.render(json.rows);
    
    $('#containerHorarios').append(content);
};


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
        
        $('#combocategoria').append('<option value="" disabled selected>Selecione o tipo de m&iacute;dia</option>');
        
        $.each( json.rows , function (i, cat){
            $('#combocategoria').append($('<option>', { 
                value: cat.idCategoria,
                text : cat.descricao  
            }));
        });
    });
    
}



var getMidias = function()
{
    var url = buildUrl( "/ambientes/{idAmbiente}/midias-por-categoria/{idCategoria}/", {
        idAmbiente : idAmbiente,
        idCategoria : $('#combocategoria').val()
    });

    $('#combomidia').empty();
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        
        $('#combomidia').append('<option value="" disabled selected>Selecione a m&iacute;dia</option>');
        
        $.each( json.rows , function (i, mid){
            $('#combomidia').append($('<option>', { 
                value: mid.idMidia,
                text : mid.descricao  
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
    
    $('#btnSalvarEvento').on('click', function(){
        salvar();
    });
    
    $('.input-group.date').datepicker({
        format: "dd/mm/yyyy",
        clearBtn: true,
        language: "pt-BR",
        todayBtn : "linked",
        autoclose : true
    });
    
    $('#linkAddHorario').on('click', function(event){
        
        event.preventDefault();
        makeListTmpl({rows:[{id: 1, hora:1, minuto:1}]});
        $(".spinner").spinner();
    });
    
    getCategorias();
    
    $('#combocategoria').on('change', function(event){
        getMidias();
    });
//    

    
});
