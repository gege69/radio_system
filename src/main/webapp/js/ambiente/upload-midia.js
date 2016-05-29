

var idAmbiente = $('#idAmbiente').val();
 
var idCategoria = $('#idCategoria').val();

var $table = $('#tabelaMidiaUpload');

function catFormatter(value, row) {
    
    var icon = value === "true" ? 'fa-check' : 'fa-circle-thin';

    return '<i class="fa '+ icon + '"></i>';
}

function deleteFormatter(value, row) {

    return '<a href="#" class="btnDeletar" idMidia="'+ value +'"><i class="fa fa-lg fa-trash" style="color : red;"></i></a>';
}


function queryParams(params) {

    params.pageNumber = $('#tabelaMidiaUpload').bootstrapTable('getOptions').pageNumber;
    
    return params;
}


var listaCategorias = function( doJump ){
    
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: buildUrl( "/categorias?simpleUpload=true" ),
        dataType: 'json'
    }).done( function(json){
        makeListTmpl(json);
        
        var lista = json.rows;
        
        $.each( lista, function( idx, obj ){

            if ( obj.idCategoria == idCategoria )
                $('#inlineCheck'+obj.idCategoria).prop('checked', true);
        });
        
    } );
}


var makeListTmpl = function(json){
    
    var tmpl = $.templates('#viewTmpl');
    
    $('#checkBoxContainer').empty();
    
    var content = tmpl.render(json.rows);
    
    $('#checkBoxContainer').append(content);
};


var deletaMidia = function( idMidia )
{
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: buildUrl( "/ambientes/{idAmbiente}/midia/{idMidia}", { idAmbiente : idAmbiente, idMidia : idMidia } ),
        dataType: 'json'
    }).done( function(json){
        
        if ( json.ok == 1 )
        {
            preencheAlertGeral( "alertArea", "Registro excluído com sucesso.", "success" );
            $("#tabelaMidiaUpload").bootstrapTable('refresh');
            jump('');
        }
        else{
            preencheErros( json.errors );
        }
        
    } );
}


var getCategoriasSelecionadas = function()
{
    var array_values = [];
    $('.checkbox-categoria').each( function() {
        if( $(this).is(':checked') ) {
            array_values.push( parseInt( $(this).val() ) );
        }
    });
    
    return array_values;
}


var configuraUploader = function() 
{
    var _url = buildUrl( "/api/ambientes/{idAmbiente}/upload-midia-categorias", {
        idAmbiente : idAmbiente
    });
    
    $('#fileupload').fileupload({
        dataType: 'json',
        url : _url,
        formData: { 
            _csrf: $("#csrf").val() 
        },
        add: function (e, data) {
            
            var array_values = getCategoriasSelecionadas();
            
            data.formData = { 
                              _csrf: $("#csrf").val(), 
                              "categorias[]" : array_values,
                              iniciovalidade : $("#dataInicio").val(),
                              fimvalidade : $("#dataFim").val(),
                              descricao : $("#descricao").val()
                            };
            
            $("#btnIniciar").prop("disabled", true);
            
            data.submit();
        },
        fail: function (e, data) {
            var errors = data.jqXHR.responseJSON.errors;
            preencheErros( errors );
            $("#btnIniciar").prop("disabled", false);
            $('#progress .progress-bar').css(
                    'width',
                    0 + '%'
                );
        },
        done : function(e, data) {
            preencheAlertGeral( "alertArea", "Upload realizado com sucesso", "success" );
        },
        stop : function(e, data) {
            $("#tabelaMidiaUpload").bootstrapTable('refresh');
            $("#btnIniciar").prop("disabled", false);
            $('#progress .progress-bar').css(
                    'width',
                    0 + '%'
                );
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .progress-bar').css(
                'width',
                progress + '%'
            );
            
            
        } 
    }); 
    
}

var iniciarUpload = function()
{
    var filesList = $('#outrofileupload')[0].files;
    
    if ( filesList == null || filesList.length == 0 ) { 
        var nomeCategoria = $("#nomeCategoria").val();
        preencheAlertGeral( "alertArea", "Selecione os arquivos de "+ nomeCategoria +" primeiro.");
        return;
    }
    
    var array_values = getCategoriasSelecionadas();
    
    if ( array_values == null || array_values.length == 0 ){
        preencheAlertGeral( "alertArea", "Nenhuma categoria selecionado");
        return;
    }
    
    var dataInicioVal = $("#dataInicio").val();
    var dataFimVal = $("#dataFim").val();

    if ( dataInicioVal == null || dataInicioVal == '' ){
        preencheAlertGeral("alertArea", "Data de Início da Validade é obrigatória");
        return;
    }

    if ( dataFimVal == null || dataFimVal == '' ){
        preencheAlertGeral("alertArea", "Data Fim da Validade é obrigatória");
        return;
    }

    $('#fileupload').fileupload('add', { files : filesList } );
}

var mostrarArquivos = function()
{
    var filesList = $('#outrofileupload')[0].files; 
   
    if ( filesList && filesList.length > 0 )
      $("#static-arquivos").html( filesList.length + " arquivo(s) selecionado(s)" );
    else
      $("#static-arquivos").empty();
}


var aplicarProgramacao = function(){
    
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: buildUrl( "/api/ambientes/{idAmbiente}/transmissoes/new", { idAmbiente : idAmbiente } ),
        dataType: 'json'
    }).done( function(json){
       
        if ( json.ok = 1 )
        {
            preencheAlertGeral( "alertArea", "Nova programação musical gerada incluindo as mídias atuais.", "success");
            jump('');
        }
        
    } );
}


$(function(){
    
    jQuery.fn.extend({
        disable: function(state) {
            return this.each(function() {
                var $this = $(this);
                if($this.is('input, button'))
                    this.disabled = state;
                else
                    $this.toggleClass('disabled', state);
            });
        }
    });
    
    $('body').on('click', 'a.disabled', function(event) {
        event.preventDefault();
    });

    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    
    listaCategorias();
    
    $table.on('load-success.bs.table', function( data ){
        $('.btnDeletar').click( function(){
            var idMidia = $(this).attr("idMidia"); 
            bootbox.confirm( "Tem certeza que deseja excluir essa mídia?", function( result ){
                if ( result )
                    deletaMidia( idMidia );
            });
        });
    });
    
    $('.input-group.date').datepicker({
        format: "dd/mm/yyyy",
        clearBtn: true,
        language: "pt-BR",
        todayBtn : "linked",
        autoclose : true
    });

    configuraUploader();

    $("#outrofileupload").click(function(){
        $(this).val();
    });

    $("#outrofileupload").change(function(){
        mostrarArquivos();
    });
    
    $("#outrofileupload").blur(function(){
        var filesList = $('#outrofileupload')[0].files;
        if ( filesList == null || filesList.length == 0 )
            $("#static-arquivos").empty();
    });
    
    $("#aplicar-programacao").click( function() {
        aplicarProgramacao();
    });

    $("#btnIniciar").click( function(){
        iniciarUpload();  
    });
});

