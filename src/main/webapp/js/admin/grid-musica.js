
var pagina = 0, limit = 6;

var callbackRefresh;

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
    
    $('#dialogRemover').modal('show');
}


var deletar = function()
{
    var idMidia = $("#idMidiaDialog").val();
    
    if ( idMidia == null || idMidia == 0 )
        preencheAlertGeral( "alertArea", "Mídia não encontrada" );

    var url = buildUrl( "/api/admin/midia/{idMidia}", { idMidia : idMidia } );
    
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json){ 

        if (json.ok == 1){
            preencheAlertGeral( "alertArea", "Registro removido com sucesso", "success" );
            $("#table-musicas").bootstrapTable('refresh');
            $('#dialogRemover').modal('toggle');
        }
        else{
            $('#dialogRemover').modal('toggle');
            preencheErros( json.errors );
        }
    });
} 


var marcaGenerosAssociados = function( midia ){
    
    $('#viewContainerModal .checkbox-genero').prop('checked', false);

    if ( midia == null || midia.generos == null )
        return;
    
    $.each( midia.generos, function( idx, obj ){
        $('#viewContainerModal .checkbox-genero[idgenero='+obj.idGenero+']').prop('checked', true);
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

    $('#modalEditar').modal('show');
    $('#nomeMidia').focus();
}


var makeListTmplModal = function(container, json){
    
    var tmpl = $.templates('#viewTmpl');
    
    $(container).empty();
    
    var content = tmpl.render(json.rows);
    
    $(container).append(content);
};


var getGenerosSelecionadosEditar = function()
{
    var array_values = [];
    $('#viewContainerModal .checkbox-genero').each( function() {
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
            $('#modalEditar').modal('hide');
            limpaFiltro();

            if (typeof functionRefreshGrafico != 'undefined' && functionRefreshGrafico && typeof(functionRefreshGrafico) === "function"){
                functionRefreshGrafico();
            }
        }
        else{
            $('#modalEditar').modal('hide');
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


var openDialogGenerosGeral = function( element )
{
    $("#alertAreaModal").empty();

    var selections = $("#table-musicas").bootstrapTable('getSelections');
    
    var size = 0;
    if ( selections != null )
        size = selections.length;

    $(".tamanhoSelecao").html(size);

    $('#viewContainerGenerosGeralModal .checkbox-genero').prop('checked', false);
    $('#modalGeneros').modal('show');
}


var getGenerosSelecionadosGeral = function()
{
    var array_values = [];
    $('#viewContainerGenerosGeralModal .checkbox-genero').each( function() {
        if( $(this).is(':checked') ) {
            array_values.push( parseInt( $(this).val() ) );
        }
    });
    
    return array_values;
}


var salvarGenerosGeral = function()
{
    var url = buildUrl( "/admin/midias/musicas/generos");

    var array_values = getGenerosSelecionadosGeral();

    if ( array_values == null || array_values.length == 0 ){
        preencheAlertGeral( "alertAreaModal", "Nenhum Gênero selecionado. É necessário selecionar pelo menos 1 gênero.", "danger" );
        return;
    }
    
    var selections = $("#table-musicas").bootstrapTable('getSelections');

    if ( selections == null || selections.length == 0 ){
        preencheAlertGeral( "alertAreaModal", "Nenhuma música selecionada. É necessário selecionar pelo menos 1 música", "danger" );
        return;
    }

    var idsMidias_values = [];
    $.each(selections, function(i, el){
        idsMidias_values.push(el.idMidia);
    });
    
    var dadosForm = { idMidias : idsMidias_values,
                      idGenerosNovos : array_values };

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  JSON.stringify(dadosForm)
    }).done( function(json){ 

        if (json.ok == 1){
            preencheAlertGeral( "alertArea", "Gêneros alterados com sucesso.", "success" );
            generoFixado = 0;
            $("#table-musicas").bootstrapTable('refresh');
            $('#modalGeneros').modal('hide');
            limpaFiltro();
            jump('');

            if (typeof functionRefreshGrafico != 'undefined' && functionRefreshGrafico && typeof(functionRefreshGrafico) === "function"){
                functionRefreshGrafico();
            }
        }
        else{
            preencheErros( json.errors, "alertAreaModal" );
        }
    });
} 


var limpaFiltro = function(){
    $("#filtroGenero").hide();
    $("#filtroGenero").empty();
    generoFixado = 0; 
}



var openDialogDeletarSelecao = function( element )
{
    var selections = $("#table-musicas").bootstrapTable('getSelections');
    
    var size = 0;
    if ( selections != null )
        size = selections.length;

    $(".tamanhoSelecao").html(size);

    $('#dialogDeletarSelecao').modal('show');
}




var deletarSelecao = function()
{
    var url = buildUrl( "/admin/midias/musicas");

    var selections = $("#table-musicas").bootstrapTable('getSelections');

    if ( selections == null || selections.length == 0 ){
        preencheAlertGeral( "alertAreaModalDeletar", "Nenhuma música selecionada. É necessário selecionar pelo menos 1 música", "danger" );
        return;
    }

    var idsMidias_values = [];
    $.each(selections, function(i, el){
        idsMidias_values.push(el.idMidia);
    });
    
    var dadosForm = { idMidias : idsMidias_values };

    $('#ajaxload').show();

    $("#btnConfirmarDeleteSelecao").prop("disabled",true);

    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  JSON.stringify(dadosForm)
    }).done( function(json){ 

        if (json.ok == 1){
            preencheAlertGeral( "alertArea", "Músicas removidas com sucesso.", "success" );
            generoFixado = 0;
            $("#table-musicas").bootstrapTable('refresh');
            $('#dialogDeletarSelecao').modal('hide');
            limpaFiltro();
            jump('');

            if (typeof functionRefreshGrafico != 'undefined' && functionRefreshGrafico && typeof(functionRefreshGrafico) === "function"){
                functionRefreshGrafico();
            }
        }
        else{
            preencheErros( json.errors, "alertAreaModal" );
        }

        $("#btnConfirmarDeleteSelecao").prop("disabled",false);
        $('#ajaxload').hide();
    }).fail( function(){
        $('#ajaxload').hide();
        $("#btnConfirmarDeleteSelecao").prop("disabled",false);
    });
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
        
        if ( data.total != null ){
            $(".totalMusicas").html(data.total);
            $("#totalMusicasTexto").html(" música(s) " + texto );
        }
        else {
            $(".totalMusicas").empty();
            $("#totalMusicasTexto").html("Nenhuma música " + texto );
        }

        $(".editar-class").click( function(e){
            e.preventDefault();
            openPopup($(this));
        });

        $(".remover-class").click( function(e){
            e.preventDefault();
            openDialog($(this));
        });
        
        $(".play-class").click( function(e){
            e.preventDefault();
            playChamada($(this));
        });
    });
    
    $("#table-musicas").on( 'page-change.bs.table', function ( e, number, size ){
        $(".editar-class").click( function(e){
            e.preventDefault();
            openPopup($(this));
        });
        
        $(".remover-class").click( function(e){
            e.preventDefault();
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
   
    $('#modalEditar').on('shown.bs.modal', function () {
        $('#nomeMidia').focus();
    });

    $('#modalEditar').on('shown.bs.modal', function () {
        $('#btnNaoDialog').focus();
    });
    
    listaGenerosModal().done( function(json){
        makeListTmplModal($("#viewContainerModal"), json);
        makeListTmplModal($("#viewContainerGenerosGeralModal"), json);
    });

    $('#modalGeneros').on('shown.bs.modal', function() {
        $("#divWarningGeneros").addClass('animated zoomIn');
    });

    $("#btnAlterarGenerosGeral").click( function(){
        openDialogGenerosGeral();
    });
    
    $("#btnSalvarGenerosGeral").click( function(){
        salvarGenerosGeral();
    });
    
    $("#btnLimparFiltro").click(function(){
        limpaFiltro();
        jump("table-musicas");
        $("#table-musicas").bootstrapTable('uncheckAll');
        $("#table-musicas").bootstrapTable('resetSearch');
    });

    $("#btnDeletarSelecao").click( function(){
        openDialogDeletarSelecao();
    });

    $("#btnConfirmarDeleteSelecao").click( function(){
        deletarSelecao();
    });

});