
var $table = $('#table-eventos');

var idAmbiente = $('#idAmbiente').val();

function queryParamsEventos(params) {

    params.pageNumber = $table.bootstrapTable('getOptions').pageNumber;
    
    return params;
}


function formatter( index, row, short ) {
    
    var html = [];

    var inicio = "";
    var fim = "";
    
    if ( row.dataInicio != null )
        inicio = row.dataInicio.substring(0,10);
    
    if ( row.dataFim != null )
        fim = row.dataFim.substring(0,10);
    
    html.push( '<b>Período</b> : '+ inicio + ' à ' + fim  );

    if ( short == null || short == false )
    {
        html.push( '<b>Tipo</b> : '+ row.categoria.descricao  );
        
        html.push( '<b>Mídia</b> : '+ row.midia.descricao );
    }
    
    if ( row.horarios != null && row.horarios.length > 0 )
    {
        var inner = [];
        
        inner.push('<b>Horários</b> : ' );
        var existe = false;
        
        $.each(row.horarios, function (key, horario) {

            var hora = padLeft( horario.hora, 2, '0' );
            var minuto = padLeft( horario.minuto, 2, '0' );
            
            var str = hora + ':' + minuto;
            if ( existe )
                str = ', ' + str;
            inner.push( str );
            existe = true;
            
        });

        html.push( inner.join('') );
    }
    
    return html;
}


function linhaFormatter(value, row) {
    
    var html = formatter( 0, row, true );
    
    return html.join(' ');;
}


function detailFormatter(index, row) {
    
    var html = formatter( index, row );
    
    return "<p>" + html.join('</p><p>') + "</p>";
}


var preencheTela = function()
{
    
}

var validaForm = function(){
    
    var isOk = true;
    
    removeErros();
    
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
                $table.bootstrapTable('refresh');
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



var getMidias = function( id_pre_selecao )
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
        
        if ( id_pre_selecao != null )
            $('#combomidia').val( id_pre_selecao );
    });
}


var removerHorario = function( element )
{
    console.log(element);
    
    var $div = element.parents('.linha-horarios');
    
    $div.remove();
    
    $('.removerLink').off('click');
    $('.removerLink').on('click', function(event){
        removerHorario( $(this) );
    });
}


var editEvento = function( e, row, el )
{
    $('#ajaxload').show();
    
    var rowcopy = extend( row );

    var cat = rowcopy.categoria;
    if ( cat != null )
        $('#combocategoria').val( cat.idCategoria );
    
    if ( rowcopy.midia != null )
        getMidias( rowcopy.midia.idMidia );

    formReset( $('#form-evento') );
   
    var horarios = rowcopy.horarios;
   
    delete rowcopy["horarios"];
    
    $('#form-evento').populate(rowcopy);
    
    $('.linha-horarios').remove()
    
    $.each(horarios, function (key, horario) {
        if ( key == 0)
        {
            $('#hora0').val( horario.hora );
            $('#minuto0').val( horario.minuto );
            $('#idEventoHorario0').val( horario.idEventoHorario );
        }
        else
        {
            makeListTmpl({rows:[{id: horario.idEventoHorario, hora: horario.hora , minuto: horario.minuto }]});
        }
    });
    
        
   
    $('#ajaxload').hide();
}



var limparForm = function(){
    
    $('#combomidia').empty();
    $('#combomidia').append('<option value="" disabled selected>Selecione a m&iacute;dia</option>');
    
    formReset( $('#form-evento') );
    $('#idEvento').val('');
    
    $('.input-group.date').datepicker('update', new Date());
    
    $(".input-horario").val('1');
    $('.linha-horarios').remove()
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
    
    $('.input-group.date').datepicker('update', new Date());
    
    $('#linkAddHorario').on('click', function(event){
        
        event.preventDefault();
        makeListTmpl({rows:[{id: "", hora:1, minuto:1}]});
        $(".spinner").spinner();

        $('.removerLink').off('click');
        $('.removerLink').on('click', function(event){
            removerHorario( $(this) );
         });
    });
    
    getCategorias();
    
    $('#combocategoria').on('change', function(event){
        getMidias();
    });
    
    $('.removerLink').off('click');
    $('#removerLink').on('click', function(event){
       removerHorario( $(this) );
    });

    
    $('#table-eventos').on('click-row.bs.table', function( e, row, el ){
        editEvento( e, row, el );
    });

    $('#btnLimpar').on('click', function(){
        limparForm();
    })
    
});
