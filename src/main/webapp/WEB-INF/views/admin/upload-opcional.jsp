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
              <h3>Upload de Opcionais </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>

          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">

              <div class="row"> 

                <div class="col-lg-9 col-md-9">
                  <div class="panel panel-default">
                    <div class="panel-body">
                    
                      <form action="#" id="formUploadOpcionais" class="form">

                        <div class="row">
                          <div class="col-lg-12 ">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf" />
                            <input type="hidden" name="codigo" value="opcional" id="codigo" />  

                            <input type="file" id="fileupload" name="file" multiple style="display : none;">

                            <div class="col-lg-3 col-md-4 col-sm-6">
                              <span class="btn btn-primary btn-file">
                                  Escolha os arquivos<input type="file" id="outrofileupload" name="file2" multiple>
                              </span>
                            </div>
                            
                            <div class="col-lg-offset-3 col-md-offset-4 col-sm-offset-6">          
                              <p class="form-control-static" id="static-arquivos"></p>
                            </div>

                            <div class="spacer-vertical10"></div>

                            <div id="resultados">
                              <div id="progress" class="progress">
                                  <div class="progress-bar progress-bar-success"></div>
                              </div>
                              <div id="files" class="files"></div>            
                            </div>
                            
                            <div class="form-group">
                              <label for="login" class="control-label col-lg-3 col-sm-3 col-md-3">Filtro do Opcional</label>
                              <div class="col-lg-6 col-md-6">
                                <select class="form-control" id="idOpcional" name="idOpcional">
                                  <option value="" disabled="disabled">Selecione</option>
                                </select>
                              </div>
                            </div> 

                          </div>
                        </div>
                        
                        <div class="row">
                          <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                          </div>
                          <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <div class="pull-right">
                              <a class="btn btn-success" id="btnIniciar" href="#"> <i class="fa fa-lg fa-cloud-upload"></i> Iniciar Upload</a>    
                            </div>          
                          </div>
                        </div>            

                      </form>

                    </div>
                  </div>
                </div>

              </div>

              <div class="spacer-vertical20"></div>

              <table  
                 id="tableOpcionais"
                 data-toggle="table"
                 data-url="${context}/admin/midias/opcionais"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=6
                 data-locale = "pt_BR"
                 data-page-list="[6,12,25]"
                 data-unique-id="idMidia"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="nome" class="col-lg-7 col-md-6">Nome do Arquivo</th>
                      <th data-field="opcional" class="col-lg-2 col-md-3">Opcional</th>
                      <th data-field="idMidia" data-formatter="editarFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-2">Editar</th>
                      <th data-field="idMidia" data-formatter="removerFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-2">Remover</th>
                      <th data-field="idMidia" data-formatter="playFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-2">Tocar</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical10"></div>

          <div class="player" id="player1" style="display:none;" >
              <audio controls>
                  <source src="" type="audio/ogg">
              </audio>
          </div>
          
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                <a class="btn btn-default" href="${context}/admin/upload-painel/view">
                <i class="fa fa-arrow-left"></i>
                Voltar para Upload de Mídias</a>    
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/admin/painel">Painel de Admin</a>    
              </div>          
            </div>
          </div>            
        </div>
      </div>
    </div>
    
      
  </div> <!-- /container -->


<div class="modal fade" id="myModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Alterar nome do Opcional</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="altera-nome-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idMidia" name="idMidia" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              
              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Nome</label>
                <div class="col-lg-8 col-md-10">
                  <input type="text" class="form-control" id="nomeMidia" name="nome">
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



<div class="modal fade" id="myDialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Remover Opcional</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="remove-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idMidiaDialog" name="idMidia" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              Deseja realmente remover esse Opcional?
            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnNaoDialog" data-dismiss="modal">Não</button>
        <button type="button" class="btn btn-primary" id="btnConfirmarDelete">Sim</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${context}/js/required/jquery.serializejson.js" defer></script>

<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>
<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">

<script src="${context}/js/required/jquery-ui.min.js"></script>
<script src="${context}/js/required/jquery.iframe-transport.js"></script>
<script src="${context}/js/required/jquery.fileupload.js"></script>

<link rel="stylesheet" href="https://cdn.plyr.io/1.3.7/plyr.css" defer>
<script src="https://cdn.plyr.io/1.3.7/plyr.js" defer></script>

<script type="text/javascript">

    var pagina = 0, limit = 6;

    function queryParams(params) {

        params.pageNumber = $('#tableOpcionais').bootstrapTable('getOptions').pageNumber;
        params.codigo = 'opcional'
        params.idOpcional = $("#idOpcional").val();
        return params;
    };

    function editarFormatter(value, row) {
        return '<a class="btn btn-link editar-class" id="btnEditarChamada" idMidia="'+ row.idMidia +'" href="#"> <i class="fa fa-lg fa-font"></i><i class="fa fa-lg fa-pencil"></i></a>';
    }
    
    function removerFormatter(value, row) {
        return '<a class="btn btn-link remover-class" id="btnRemoverChamada" idMidia="'+ row.idMidia +'" href="#"> <i class="fa fa-lg fa-times"></i></a>';
    }

    function playFormatter(value, row) {
        return '<a class="btn btn-link play-class" id="btnPlayChamada" idMidia="'+ row.idMidia +'" href="#"> <i class="fa fa-lg fa-play-circle"></i></a>';
    }
    
    var player = null;
    
    var playChamada = function( element )
    {
        player.pause();
        
        var idMidia = element.attr("idMidia");
        
        var url = buildUrl( "/admin/midia/{idMidia}", { idMidia: idMidia });
        
        player.source( url );
        player.play();
    }


    var openDialog = function( element )
    {
        var idMidia = element.attr("idMidia");
        
        $('#idMidiaDialog').val( idMidia );
        
        $('#myDialog').modal('show');
    }

    
    var deletar = function()
    {
        var idMidia = $("#idMidiaDialog").val();
        
        if ( idMidia == null || idMidia == 0 )
            preencheAlertGeral( "alertArea", "Mídia não encontrada" );

        var url = buildUrl( "/admin/midias/opcionais/{idMidia}", { idMidia : idMidia } );
        
        $.ajax({
            type: 'DELETE',
            contentType: 'application/json',
            url: url,
            dataType: 'json'
        }).done( function(json){ 

            if (json.ok == 1){
                preencheAlertGeral( "alertArea", "Registro removido com sucesso", "success" );
                $("#tableOpcionais").bootstrapTable('refresh');
                $('#myDialog').modal('toggle');
            }
            else{
                $('#myDialog').modal('toggle');
                preencheErros( json.errors );
            }
        });
    } 
    
    
    
    var openPopup = function( element )
    {
        var idMidia = element.attr("idMidia");
        
        var row = $('#tableOpcionais').bootstrapTable('getRowByUniqueId', idMidia);
        
        $('#idMidia').val( idMidia );
        $('#nomeMidia').val( row.nome );
        
        var texto = $("#categoria-combo :selected").text();
        
        $('#tipo').html( texto );
        
        $('#myModal').modal('show');
        $('#nomeMidia').focus();
    }


    var salvar = function()
    {
        var url = buildUrl( "/admin/midia");
        
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: 'json',
            data:  JSON.stringify( $('#altera-nome-midia-form').serializeJSON() )
            
        }).done( function(json){ 

            if (json.ok == 1){
                preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
                $("#tableOpcionais").bootstrapTable('refresh');
                $('#myModal').modal('toggle');
            }
            else{
                $('#myModal').modal('toggle');
                preencheErros( json.errors );
            }
        });
    } 
    
    
    
    var configuraUploader = function() 
    {
        var _url = buildUrl( "/admin/upload-opcional" );
        
        $('#fileupload').fileupload({
            dataType: 'json',
            url : _url,
            formData: { 
                _csrf: $("#csrf").val() 
            },
            add: function (e, data) {
                
                removeErros();
                
                var idOpcional = $("#idOpcional").val();
                if ( idOpcional == null || idOpcional == "" )
                {
                    preencheAlertGeral("alertArea", "Preencha o Opcional.", "danger");
                    preencheErroField("idOpcional", "Necessário");
                    return false;
                }
                
                data.formData = { 
                    _csrf: $("#csrf").val(), 
                    codigo : $("#codigo").val(),
                    idOpcional : $("#idOpcional").val()
                };
                
                data.submit();
            },
            fail: function (e, data) {
                var errors = data.jqXHR.responseJSON.errors;
                preencheErros( errors );
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
            },
            done : function(e, data) {
                preencheAlertGeral( "alertArea", "Upload realizado com sucesso", "success" );
            },
            stop : function( e, data) {
                $("#tableOpcionais").bootstrapTable('refresh');
                $('#progress .progress-bar').css(
                        'width',
                        0 + '%'
                    );
            }
        }); 

    }
   

    var iniciarUpload = function()
    {
        var filesList = $('#outrofileupload')[0].files;

        if ( filesList == null || filesList.length == 0 ) { 
            preencheAlertGeral( "alertArea", "Selecione o arquivo e adicione uma letra ou número");
            return;
        }
        
        var idOpcional = $("#idOpcional").val()
        
        if ( idOpcional == null || idOpcional === "" ){
            preencheAlertGeral( "alertArea", "Escolha para qual Opcional será adicionada essa Mídia");
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



    var getOpcionais = function()
    {
        var url = buildUrl( "/admin/opcionais"); 
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: url,
            dataType: 'json'
        }).done( function(json) {
            
            $("#idOpcional").empty();
            
            $('#idOpcional').append($('<option>', { 
                disabled : "disabled",
                selected : "selected",
                text: "Selecione"
            }));
            
            $.each(json.rows, function (i, op) {
                $('#idOpcional').append($('<option>', { 
                    value: op.idOpcional,
                    text : op.nome  
                }));
            });

           $("#tableOpcionais").bootstrapTable('refresh');
        });
    };

    
    $(function(){
        
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        plyr.setup( { options : ["current-time", "duration", "mute"]});

        player = $('#player1')[0].plyr;

        $("#idOpcional").change( function() {

           $("#tableOpcionais").bootstrapTable('refresh');

           $('#progress .progress-bar').css(
                   'width',
                   0 + '%'
               );
        });
        
        configuraUploader();
        
        $("#tableOpcionais").on( 'load-success.bs.table', function( e, data ) {
            $(".editar-class").click( function(){
                openPopup($(this));
            });

            $(".remover-class").click( function(){
                openDialog($(this));
            });
            
            $(".play-class").click( function(){
                playChamada($(this));
            });
        });
        
        $("#tableOpcionais").on( 'page-change.bs.table', function ( e, number, size ){
            $(".editar-class").click( function(){
                openPopup($(this));
            });
            
            $(".remover-class").click( function(){
                openDialog($(this));
            });

            $(".play-class").click( function(){
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
        })

        $('#myDialog').on('shown.bs.modal', function () {
            $('#btnNaoDialog').focus();
        })
        
        $("#btnIniciar").click( function(){
            iniciarUpload();  
        });
        
        $("#outrofileupload").blur(function(){
            mostrarArquivos();
        });

        $("#outrofileupload").change(function(){
            $('#progress .progress-bar').css(
                'width',
                0 + '%'
            );
        });

        getOpcionais();
    });

</script>

<style type="text/css">

#table-generos tr{
  cursor: pointer;
}

</style>

<jsp:include page="/WEB-INF/views/bottom.jsp" />