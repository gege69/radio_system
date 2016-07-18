

var makeListTmpl = function(json){
    
    var tmpl = $.templates('#viewTmpl');
    
    $('#view-container').empty();
    
    var content = tmpl.render(json.rows);
    
    $('#view-container').append(content);
};


var listaGeneros = function(){
    
    var url = buildUrl( "/generos" );

    return $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,    
        dataType: 'json'
    });
}


var getGenerosSelecionados = function()
{
    var array_values = [];
    $('.checkbox-genero').each( function() {
        if( $(this).is(':checked') ) {
            array_values.push( parseInt( $(this).val() ) );
        }
    });
    
    return array_values;
}


var configuraUploader = function() 
{
    var _url = buildUrl( "/admin/upload-musica" );
    
    $('#fileupload').fileupload({
        dataType: 'json',
        url : _url,
        formData: { 
            _csrf: $("#csrf").val() 
        },
        add: function (e, data) {
            
            var array_values = getGenerosSelecionados();
            
            data.formData = { 
                              _csrf: $("#csrf").val(), 
                              codigo : "musica" ,
                              "generos[]" : array_values
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

            $("#table-musicas").bootstrapTable('refresh');
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
        preencheAlertGeral( "alertArea", "Selecione as músicas e algum gênero primeiro.");
        return;
    }
    
    var array_values = getGenerosSelecionados();
    
    if ( array_values == null || array_values.length == 0 ){
        preencheAlertGeral( "alertArea", "Nenhum gênero selecionado");
        return;
    }
    
    $('#fileupload').fileupload('add', { files : filesList } );
}

var mostrarArquivos = function()
{
    var filesList = $('#outrofileupload')[0].files; 
   
    if ( filesList && filesList.length > 0 )
      $("#static-arquivos").html( filesList.length + " arquivo(s) selecionado(s)" );
    
}



$(function(){
    
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $('#progress .progress-bar').css(
        'width',
        0 + '%'
    );
    
    configuraUploader();
    
    listaGeneros().done( function(json){
        makeListTmpl(json);
    });
    
    $("#btnIniciar").click( function(){
        iniciarUpload();  
    });

    $("#outrofileupload").click(function(){
        $(this).val();
    });
    
    $("#outrofileupload").change(function(){
        mostrarArquivos();
    });
    
    $("#outrofileupload").change(function(){
        $('#progress .progress-bar').css(
            'width',
            0 + '%'
        );
    }); 
    
});