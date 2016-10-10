<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">

    <div class="row">
    
      <div class="panel panel-default">
        <div class="panel-body">

          <div class="row">
            <div class="col-lg-6 col-md-6">
              <h3><i class="${func.sizeSmall} ${func.classesIcone}">${func.icone}</i> Chamada de Funcionários</h3>
              <h4><small>Gerencie os nomes e chamadas para este ambiente</small></h4>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
              <c:if test="${not empty error}">      
                <div class="alert alert-danger" role="alert" id="alertalertArea" >
                  <a href="#" class="close" data-dismiss="alert">&times;</a>
                  <div id="errogeral">${error}</div>
                </div>
              </c:if>
              
              <c:if test="${not empty success}">      
                <div class="alert alert-success" role="alert" id="alertalertArea" >
                  <a href="#" class="close" data-dismiss="alert">&times;</a>
                  <div id="errogeral">${success}</div>
                </div>
              </c:if>
            </div>
          </div>

          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-7 col-md-7">

                Nomes de Funcionários:
              <table  
                 id="tableNomes"
                 data-toggle="table"
                 data-url="${context}/ambientes/${idAmbiente}/midias-por-categoria?codigo=chamada_func_nome"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=7
                 data-page-list="[7]"
                 data-locale = "pt_BR"
                 data-unique-id="idMidia"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="nome">Arquivo</th>
                      <th data-field="descricao">Nome</th>
                      <th data-field="idMidia" data-formatter="deleteFormatter" data-align="center">Deletar</th>
                      <th data-field="idMidia" data-click-to-select="false" data-formatter="playFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Tocar</th>
                      <th data-field="idMidia" data-formatter="editarFormatter" >Editar</th>
                  </tr>
                </thead>
              </table>
              
              <div class="player" id="player1" style="display:none;" >
                <audio controls>
                  <source src="" type="audio/ogg">
                </audio>
              </div>

              <div class="spacer-vertical20"></div>

              <div class="panel panel-default">
                <div class="panel-body">
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf" />

                  <input type="hidden" id="idCategoria_nome" name="idCategoria_nome" value="${idCategoria_nome}">
                  <input type="hidden" id="codigo_nome" name="codigo_nome" value="${codigo_nome}">
                  <input type="hidden" id="nomeCategoria_nome" name="nomeCategoria_nome" value="${nomeCategoria_nome}">


                  <input type="hidden" id="idCategoria_frase" name="idCategoria_frase" value="${idCategoria_frase}">
                  <input type="hidden" id="codigo_frase" name="codigo_frase" value="${codigo_frase}">
                  <input type="hidden" id="nomeCategoria_frase" name="nomeCategoria_frase" value="${nomeCategoria_frase}">

                  <input type="file" id="fileupload" name="file" multiple style="display : none;">

                  <div class="row">
                    <div class="col-lg-12">
                      <span class="btn btn-primary btn-file">
                          Escolha os arquivos dos nomes<input type="file" id="outrofileupload" name="file2" multiple>
                      </span>
                    </div>
                    
                    <div class="col-lg-12">          
                      <p class="form-control-static" id="static-arquivos"></p>
                    </div>
                  </div>

                  <div class="spacer-vertical10"></div>

                  <div id="resultados">
                    <div id="progress" class="progress">
                        <div class="progress-bar progress-bar-success"></div>
                    </div>
                    <div id="files" class="files"></div>            
                  </div>

                  <div class="spacer-vertical10"></div>

                  <div class="col-lg-12">
                    <div class="form-group">
                      <label for="file">Nome do Funcionário: </label>
                      <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Nome do Funcionário">
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6">          
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6">
                      <div class="pull-right-not-xs">
                        <button type="button" class="btn btn-success" id="btnIniciar">
                          <i class="fa fa-lg fa-cloud-upload"></i> Iniciar Upload</a> 
                        </button>
                      </div>          
                    </div>
                  </div>            
                  
                </div>
              </div>

            </div>                        
            
            <div class="col-lg-5 col-md-5">
              Frases / Chamadas :
              <table  
                 id="tableFrases"
                 data-toggle="table"
                 data-url="${context}/ambientes/${idAmbiente}/midias-por-categoria?codigo=chamada_func_frase"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=7
                 data-page-list="[7]"
                 data-locale = "pt_BR"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="nome">Arquivo</th>
                      <th data-field="descricao">Descrição</th>
                  </tr>
                </thead>
              </table>
              
              <div class="spacer-vertical20"></div>
             
              Adicionar Frase
              <form action="${context}/ambientes/${idAmbiente}/view-chamada-funcionarios" 
                    method="POST" 
                    id="ambiente-upload-frase" 
                    class="form" 
                    enctype="multipart/form-data">
              
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" id="codigo" name="codigo" value="chamada_func_frase">
                <div class="col-lg-12 col-md-12">
                  <div class="form-group">
                    <label for="file">Arquivo:</label>
                    <input type="file" class="form-control" id="arquivo" name="file" placeholder="algum arquivo">
                  </div>
                </div>
                <div class="col-lg-12 col-md-12">
                  <div class="form-group">
                    <label for="file">Descrição:</label>
                    <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Escreva uma pequena descrição da frase">
                  </div>
                </div>
                
                <div class="row">
                  <div class="col-lg-12 col-md-12">
                    <div class="form-group">
                      <button type="button" class="btn btn-primary" id="btnUploadFrase">
                        <i class="fa fa-lg fa-cloud-upload"></i> Upload nova Frase</a> 
                      </button>
                    </div>
                  </div>
                </div>
              </form>
              
            </div>
          </div>

          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
              <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/view" >
                <i class="fa fa-arrow-left"></i>
                Voltar para ${nome}
              </a>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="pull-right-not-xs">
                <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>    
              </div>          
            </div>
          </div>
          
        </div>
      </div>
    </div>
    

      
  </div> <!-- /container -->



<div class="modal" id="modalEditar">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Alterar dados da Música</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="altera-nome-midia-form" method="POST">
          <input type="hidden" id="idMidia" name="idMidia" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">

              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Nome</label>
                <div class="col-lg-8 col-md-10">
                  <input type="text" class="form-control" id="nomeMidia" name="nome">
                </div>
              </div>

              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Descrição</label>
                <div class="col-lg-8 col-md-10">
                  <input type="text" class="form-control" id="descricaoMidia" name="descricao">
                </div>
              </div>

            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
        <button type="button" class="btn btn-primary" id="btnSalvar">Alterar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script src="https://cdnjs.cloudflare.com/ajax/libs/jsrender/0.9.78/jsrender.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.7.2/jquery.serializejson.min.js" defer></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-pt-BR.min.js" charset="UTF-8"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/blueimp-file-upload/9.12.5/js/jquery.iframe-transport.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/blueimp-file-upload/9.12.5/js/jquery.fileupload.min.js"></script>

<script src="${context}/js/required/bootbox.min.js"></script>

<link rel="stylesheet" href="https://cdn.plyr.io/1.3.7/plyr.css" defer>
<script src="https://cdn.plyr.io/1.3.7/plyr.js" defer></script>


<script id="viewTmpl" type="text/x-jsrender">
<label class="checkbox-inline">
  <input type="checkbox" id="inlineCheck{{:idCategoria}}" name="categorias[]" value="{{:idCategoria}}"> {{:nome}}
</label>
</script>  



<script type="text/javascript">

    var $table = $('#table');
    var idAmbiente = ${idAmbiente};
    
    function catFormatter(value, row) {
        
        var icon = value === "true" ? 'fa-check' : 'fa-circle-thin';

        return '<i class="fa '+ icon + '"></i>';
    }
    
    function queryParams(params) {

        params.pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
        
        return params;
    }
    

    var mostrarArquivos = function()
    {
        var filesList = $('#outrofileupload')[0].files; 
       
        if ( filesList && filesList.length > 0 )
          $("#static-arquivos").html( filesList.length + " arquivo(s) selecionado(s)" );
        else
          $("#static-arquivos").empty();
    }


    function deleteFormatter(value, row) {

        return '<a href="#" class="btnDeletar" idMidia="'+ value +'"><i class="fa fa-lg fa-trash" style="color : red;"></i></a>';
    }

    function editarFormatter(value, row) {
        return '<a class="btn btn-link editar-class" idMidia="'+ row.idMidia +'" href="#"> <i class="fa fa-lg fa-font"></i><i class="fa fa-lg fa-pencil"></i></a>';
    }

    function playFormatter(value, row) {
        return '<a class="btn btn-link play-class" idMidia="'+ row.idMidia +'" href="#"> <i class="fa fa-lg fa-play-circle"></i></a>';
    }
    
    var configuraUploader = function() 
    {
        var _url = buildUrl( "/api/ambientes/{idAmbiente}/upload-midia-chamada-funcionario", {
            idAmbiente : idAmbiente
        });
        
        $('#fileupload').fileupload({
            dataType: 'json',
            url : _url,
            add: function (e, data) {
                
                var filesList = $('#outrofileupload')[0].files; 
               
                var descricao = $("#descricao").val();

                if ( filesList && filesList.length > 0 )  // evitar que todos fiquem com a mesma descrição em caso de múltiplos arquivos
                    descricao = "";
                
                data.formData = { 
                                  _csrf: $("#csrf").val(), 
                                  categoria : $("#codigo_nome").val(),
                                  iniciovalidade : $("#dataInicio").val(),
                                  fimvalidade : $("#dataFim").val(),
                                  descricao : $("#descricao").val()
                                };

                $("#btnUploadFrase").prop("disabled", true);
                $("#btnIniciar").prop("disabled", true);
                
                data.submit();
            },
            fail: function (e, data) {
                var errors = data.jqXHR.responseJSON.errors;
                preencheErros( errors );
                $("#btnIniciar").prop("disabled", false);
                $("#btnUploadFrase").prop("disabled", false);
                $('#progress .progress-bar').css(
                        'width',
                        0 + '%'
                    );
            },
            stop : function(e, data) {
                var erros = $("#alertArea .alert-danger").length;
                if ( erros == null || erros == 0 )
                    preencheAlertGeral( "alertArea", "Upload realizado com sucesso", "success" );
                $("#tableNomes").bootstrapTable('refresh');
                $("#btnIniciar").prop("disabled", false);
                $("#btnUploadFrase").prop("disabled", false);
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
    
    var listaCategorias = function( doJump ){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/categorias?simpleUpload=true',
            dataType: 'json'
        }).done( function(json){
            makeListTmpl(json);
            
            var lista = json.rows;
            
            var id_categoria_tela = $('#idCategoria').val();

            $.each( lista, function( idx, obj ){

                if ( obj.idCategoria == id_categoria_tela )
                    $('#inlineCheck'+obj.idCategoria).prop('checked', true);
            });
            
        } );
    }
    

    var iniciarUploadNomes = function()
    {
        var filesList = $('#outrofileupload')[0].files;
        
        if ( filesList == null || filesList.length == 0 ) { 
            var nomeCategoria = $("#nomeCategoria").val();
            preencheAlertGeral( "alertArea", "Selecione os arquivos dos nomes para continuar.");
            return;
        }

        $('#fileupload').fileupload('add', { files : filesList } );
    }

    
    var makeListTmpl = function(json){
        
        var tmpl = $.templates('#viewTmpl');
        
        $('#checkBoxContainer').empty();
        
        var content = tmpl.render(json.rows);
        
        $('#checkBoxContainer').append(content);
    };
    
    
    function bindDeletar(){
        $('.btnDeletar').click( function(){
            var idMidia = $(this).attr("idMidia"); 
            bootbox.confirm( "Tem certeza que deseja excluir essa mídia?", function( result ){
                if ( result )
                    deletaMidia( idMidia );
            });
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
                $("#tableNomes").bootstrapTable('refresh');
                jump('');
            }
            else{
                preencheErros( json.errors );
            }
            
        } );
    }
    

    var openPopup = function( element )
    {
        var idMidia = element.attr("idMidia");
        
        var row = $('#tableNomes').bootstrapTable('getRowByUniqueId', idMidia);

        $('#idMidia').val( idMidia );
        $('#nomeMidia').val( row.nome );
        $('#descricaoMidia').val( row.descricao );
        $('#artistaMidia').val( row.artist );
        
        $('#modalEditar').modal('show');
        $('#nomeMidia').focus();
    }


    // mudar o nome da midia
    var salvar = function()
    {
        var url = buildUrl( "/api/ambientes/{idAmbiente}/midias/nome" , {idAmbiente, idAmbiente});

        var dadosForm = $('#altera-nome-midia-form').serializeJSON();

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: 'json',
            data:  JSON.stringify(dadosForm)
        }).done( function(json){ 

            if (json.ok == 1){
                preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
                $("#tableNomes").bootstrapTable('refresh');
                $('#modalEditar').modal('hide');
            }
            else{
                $('#modalEditar').modal('hide');
                preencheErros( json.errors );
            }
        });
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
            var url = buildUrl( "/ambientes/{idAmbiente}/midias/{idMidia}", { idAmbiente : idAmbiente, idMidia: idMidia });
            
            element.find('i').removeClass('fa-play-circle').addClass('fa-pause-circle');

            player.source( url );
            player.play();
        }
    }


    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        plyr.setup( { options : ["current-time", "duration", "mute"]});

        player = $('#player1')[0].plyr;

        configuraUploader();
        
        $('#btnUploadNome').on('click', function(){
            $("#ambiente-upload-nome").submit();
        });
        
        $('#btnUploadFrase').on('click', function(){
            $("#ambiente-upload-frase").submit();
        });
        
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

        listaCategorias();
        
        $("#btnIniciar").click( function(){
            iniciarUploadNomes();  
        });

        $("#tableNomes").on('load-success.bs.table', function( data ){
            bindDeletar();

            $(".editar-class").click( function(e){
                e.preventDefault();
                openPopup($(this));
            });

            $(".play-class").click( function(e){
                e.preventDefault();
                playChamada($(this));
            });
        });

        $("#tableNomes").on( 'page-change.bs.table', function ( e, number, size ){
            bindDeletar();

            $(".editar-class").click( function(e){
                e.preventDefault();
                openPopup($(this));
            });

            $(".play-class").click( function(e){
                e.preventDefault();
                playChamada($(this));
            });
        });

        $('#modalEditar').on('shown.bs.modal', function () {
            $('#nomeMidia').focus();
        });

        $('#modalEditar').on('shown.bs.modal', function () {
            $('#btnNaoDialog').focus();
        });

        $("#btnSalvar").click( function(){
            salvar();
        });

    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />