
var $table = $('#table-eventos');


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

var salvar = function()
{
    if ( validaForm() )
    {
        $.ajax({
            
            type: 'POST',
            contentType: 'application/json',
            url: '${context}/ambientes',
            dataType: 'json',
            data:  JSON.stringify( $('#ambiente-form').serializeJSON() )
            
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


$(function(){

    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    
    $('#btnUploadMidia').on('click', function(){
        salvar();
    });
    
    $('.input-group.date').datepicker({
        format: "dd/mm/yyyy",
        clearBtn: true,
        language: "pt-BR",
        autoclose : true
    });
    
    $('#linkAddHorario').on('click', function(event){
        
        event.preventDefault();
        makeListTmpl({rows:[{hora:1, minuto:1}]});
        $(".spinner").spinner();
    });
    
});
