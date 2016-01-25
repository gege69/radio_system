

var idAmbiente = $('#idAmbiente').val();
 
var idCategoria = $('#idCategoria').val();

var $table = $('#table');

function catFormatter(value, row) {
    
    var icon = value == true ? 'fa-check' : 'fa-circle-thin';

    return '<i class="fa '+ icon + '"></i>';
}

function deleteFormatter(value, row) {

    return '<a href="#" class="btnDeletar" idMidia="'+ value +'"><i class="fa fa-lg fa-trash" style="color : red;"></i></a>';
}


function queryParams(params) {

    params.pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
    
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

// futuramente tornar esse Upload em ajax
var upload = function( idAmbiente )
{
    $('#btnUploadMidia').disable();
    
    // pegar as categorias e fazer a listinha de strings 
    $("#ambiente-upload-midia").submit();
    
    $("#ambiente-upload-midia").bind('ajax:complete', function() {
        console.log('teste');
    });
}


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
            jump('');
        }
        else{
            preencheErros( json.errors );
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
    
    $('#btnUploadMidia').on('click', function(){
        upload( idAmbiente );
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
    
});

