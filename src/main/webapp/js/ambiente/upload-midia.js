

var idAmbiente = $('#idAmbiente').val();
 
var idCategoria = $('#idCategoria').val();

var $table = null;

var funcionalidades = {};

var diasMap = { 
    SEGUNDA: "Seg",
    TERCA: "Ter",
    QUARTA: "Qua",
    QUINTA: "Qui",
    SEXTA: "Sex",
    SABADO: "Sáb",
    DOMINGO: "Dom"
};

function catFormatter(value, row) {
    
    var cats = row.categorias;
    
    var html_array = [];
    
    $.each(cats, function(i, el){
        
        var func = funcionalidades[el.codigo];
        
        if ( func ){
            html_array.push('<i class="'+func.sizeSmall +' '+ func.classesIcone+'" title="'+el.descricao+'" style="cursor: help;">'+func.icone+'</i>&nbsp;');
        }
    });

    return html_array.join('');
}

function deleteFormatter(value, row) {

    return '<a href="#" class="btnDeletar" idMidia="'+ value +'"><i class="fa fa-lg fa-trash" style="color : red;"></i></a>';
}


function diasFormatter(value, row) {
    
    var dias = row.dias;
    
    var html_array = [];
    
    $.each(dias, function(i, el){
        var dia_bonito = diasMap[el.diaSemana];
        html_array.push(dia_bonito);
    });
    
    return html_array.join(', ');
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


var getDiasSelecionados = function()
{
    var array_values = [];
    $('.check-dias').each( function() {
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
            var array_values_dias = getDiasSelecionados();
            
            data.formData = { 
                              _csrf: $("#csrf").val(), 
                              "categorias[]" : array_values,
                              "dias[]" : array_values_dias,
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
        stop : function(e, data) {
            var erros = $("#alertArea .alert-danger").length;
            if ( erros == null || erros == 0 )
                preencheAlertGeral( "alertArea", "Upload realizado com sucesso", "success" );
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



var listaFuncionalidadesCategorias = function(){
    return $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: buildUrl( "/ambientes/funcionalidades/midias" ),
        dataType: 'json'
    });
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
    
    listaFuncionalidadesCategorias().done( function(json){
        funcionalidades = json;

        $table = $('#tabelaMidiaUpload').bootstrapTable({
            queryParams : queryParams
        });

        $table.on('load-success.bs.table', function( data ){
            $('.btnDeletar').click( function(){
                var idMidia = $(this).attr("idMidia"); 
                bootbox.confirm( "Tem certeza que deseja excluir essa mídia?", function( result ){
                    if ( result )
                        deletaMidia( idMidia );
                });
            });
        });
    });

    listaCategorias();
    
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

