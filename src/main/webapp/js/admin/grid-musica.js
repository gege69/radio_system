
var pagina = 0, limit = 6;

var generoFixado = 0;

function queryParams(params) {

    params.pageNumber = $('#table-musicas').bootstrapTable('getOptions').pageNumber;
    params.codigo = "musica";
    params.generoFixado = generoFixado;
    
    return params;
};

function editarFormatter(value, row) {
    return '<a class="btn btn-link editar-class" idMidia="'+ row.idMidia +'" href="#"> <i class="fa fa-lg fa-font"></i><i class="fa fa-lg fa-pencil"></i></a>';
}

function removerFormatter(value, row) {
    return '<a class="btn btn-link remover-class" idMidia="'+ row.idMidia +'" href="#"> <i class="fa fa-lg fa-trash-o"></i></a>';
}

function playFormatter(value, row) {
    return '<a class="btn btn-link play-class" idMidia="'+ row.idMidia +'" href="#"> <i class="fa fa-lg fa-play-circle"></i></a>';
}

var player = null;

var idTocando = null;

var playChamada = function( element )
{
    var idMidia = element.attr("idMidia");

    if ( idMidia == idTocando && !player.media.paused ){
        element.find('i').addClass('fa-play-circle').removeClass('fa-pause-circle');
        player.pause();
    }
    else
    {
        idTocando = idMidia; 
        
        player.pause();
        var url = buildUrl( "/admin/midias/{idMidia}", { idMidia: idMidia });
        
        element.find('i').removeClass('fa-play-circle').addClass('fa-pause-circle');

        player.source( url );
        player.play();
    }
    
}


var openDialog = function( element )
{
    var idMidia = element.attr("idMidia");
    
    var row = $('#table-musicas').bootstrapTable('getRowByUniqueId', idMidia);
    
    $('#idMidiaDialog').val( idMidia );
    
    $('#nomeMusica').html( idMidia + " - " + row.nome )
    
    $('#myDialog').modal('show');
}


var deletar = function()
{
    var idMidia = $("#idMidiaDialog").val();
    
    if ( idMidia == null || idMidia == 0 )
        preencheAlertGeral( "alertArea", "Mídia não encontrada" );

    var url = buildUrl( "/admin/chamada-veiculos/{idMidia}", { idMidia : idMidia } );
    
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json){ 

        if (json.ok == 1){
            preencheAlertGeral( "alertArea", "Registro removido com sucesso", "success" );
            $("#table-musicas").bootstrapTable('refresh');
            $('#myDialog').modal('toggle');
        }
        else{
            $('#myDialog').modal('toggle');
            preencheErros( json.errors );
        }
    });
} 


var marcaGenerosAssociados = function( midia ){
    
    $('.checkbox-genero-editar').prop('checked', false);

    if ( midia == null || midia.generos == null )
        return;
    
    $.each( midia.generos, function( idx, obj ){
        $('#genero-editar-'+obj.idGenero).prop('checked', true);
    });
}    

var openPopup = function( element )
{
    var idMidia = element.attr("idMidia");
    
    var row = $('#table-musicas').bootstrapTable('getRowByUniqueId', idMidia);

    $('#idMidia').val( idMidia );
    $('#nomeMidia').val( row.nome );
    $('#descricaoMidia').val( row.descricao );
    $('#artistaMidia').val( row.artist );
    
    marcaGenerosAssociados(row);

    $('#myModal').modal('show');
    $('#nomeMidia').focus();
}

var makeListTmplModal = function(json){
    
    var tmpl = $.templates('#viewTmplModal');
    
    $('#viewContainerModal').empty();
    
    var content = tmpl.render(json.rows);
    
    $('#viewContainerModal').append(content);
};


var getGenerosSelecionadosEditar = function()
{
    var array_values = [];
    $('.checkbox-genero-editar').each( function() {
        if( $(this).is(':checked') ) {
            array_values.push( { idGenero : parseInt( $(this).val() ) } );
        }
    });
    
    return array_values;
}


// mudar o nome da midia
var salvar = function()
{
    var url = buildUrl( "/admin/midias/musicas");

    var array_values = getGenerosSelecionadosEditar();
    
    var dadosForm = $('#altera-nome-midia-form').serializeJSON();
    dadosForm.generos = array_values;

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  JSON.stringify(dadosForm)
    }).done( function(json){ 

        if (json.ok == 1){
            preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
            $("#table-musicas").bootstrapTable('refresh');
            $('#myModal').modal('toggle');
        }
        else{
            $('#myModal').modal('toggle');
            preencheErros( json.errors );
        }
    });
} 



var listaGenerosModal = function(){
    
    var url = buildUrl( "/generos" );

    return $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,    
        dataType: 'json'
    });
}


function detailFormatter(index, row) {
    
    var detalheFormatado = formatter( index, row );

    var templateGeneros = '<p>Gêneros da música "{{nome}}" :</p>';
    
    templateGeneros = templateGeneros.reckon(row);
    
    return templateGeneros + detalheFormatado;
}


function formatter( index, row, short ) {
    
    var html = [];
    var template = '<li>{{descricao}}</li>';
    
    $.each(row.generos, function(i, el){
        html.push( template.reckon(el) );
    });
    
    return "<ul>" + html.join('') + "</ul>";
}


function detailFormatter(index, row) {
    
    var detalheFormatado = formatter( index, row );

    var templateGeneros = '<p>Gêneros da música "{{nome}}" :</p>';
    
    templateGeneros = templateGeneros.reckon(row);
    
    return templateGeneros + detalheFormatado;
}


function formatter( index, row, short ) {
    
    var html = [];
    var template = '<li>{{descricao}}</li>';
    
    $.each(row.generos, function(i, el){
        html.push( template.reckon(el) );
    });
    
    return "<ul>" + html.join('') + "</ul>";
}



$(function(){
    
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    plyr.setup( { options : ["current-time", "duration", "mute"]});

    player = $('#player1')[0].plyr;
    
    $("#table-musicas").on( 'load-success.bs.table', function( e, data ) {

        var pesquisa = $('.bootstrap-table .search input').val();
        
        var texto = ""; 
        
        if ( pesquisa != "" )
            texto = ' encontradas para a pesquisa : "' + pesquisa + '"';
        else
            texto = " no total";
        
        if ( data.total != null )
            $("#totalMusicas").html(data.total + " música(s) " + texto );
        else
            $("#totalMusicas").html("Nenhuma música " + texto );

        $(".editar-class").click( function(){
            openPopup($(this));
        });

        $(".remover-class").click( function(){
            openDialog($(this));
        });
        
        $(".play-class").click( function(e){
            e.preventDefault();
            playChamada($(this));
        });
    });
    
    $("#table-musicas").on( 'page-change.bs.table', function ( e, number, size ){
        $(".editar-class").click( function(){
            openPopup($(this));
        });
        
        $(".remover-class").click( function(){
            openDialog($(this));
        });

        $(".play-class").click( function(e){
            e.preventDefault();
            playChamada($(this));
        });
    });

    
    $("#btnSalvar").click( function(){
        salvar();
    });

    $("#btnConfirmarDelete").click( function(){
        deletar();
    });
   
    $('#myModal').on('shown.bs.modal', function () {
        $('#nomeMidia').focus();
    });

    $('#myDialog').on('shown.bs.modal', function () {
        $('#btnNaoDialog').focus();
    });
    
    listaGenerosModal().done( function(json){
        makeListTmplModal(json);
    });
    
});