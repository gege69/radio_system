<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
 
    <div class="row">
    
      <div class="row" id="alertArea">
      </div>
    
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Upload de Chamadas de Veículos<br/>
<%--             <small>Você possui ${qtdGeneros} gênero(os) cadastrado(s)</small> --%>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              
              <div class="row">
                <div class="form-group">
                  <label for="login" class="control-label col-lg-3 col-sm-3 col-md-3">Componente da Chamada:</label>
                  <div class="col-lg-6 col-md-6">
                    <select class="form-control" id="categoria-combo" name="categoria">
                      <option value="veic_frase_ini" selected="selected">Frase Inicial</option>
                      <option value="veic_marca">Marca</option>
                      <option value="veic_modelo">Modelo</option>
                      <option value="veic_cor">Cor</option>
                      <option value="veic_frase_fim">Frase Final</option>
                    </select>
                  </div>
                </div> 
              </div>

              <div class="spacer-vertical20"></div>
              
              <div class="row">
                <div class="col-lg-6 col-md-7">

                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf" />

                  <span class="btn btn-primary btn-file">
                      Escolha os arquivos<input type="file" id="fileupload" name="file" multiple>
                  </span>

                  <div class="spacer-vertical10"></div>

                  <div id="resultados">
                    <div id="progress" class="progress">
                        <div class="progress-bar progress-bar-success"></div>
                    </div>
                    <div id="files" class="files"></div>            
                  </div>

                </div>
              
              </div>
            
              <div class="spacer-vertical20"></div>
              
              <table  
                 id="table-chamadas-veiculos"
                 data-toggle="table"
                 data-url="${context}/admin/midias"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=6
                 data-locale = "pt_BR"
                 data-unique-id="idMidia"
                 data-page-list = "[6,12,25]"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="nome" class="col-lg-7 col-md-6">Nome do Arquivo</th>
                      <th data-field="descricao" class="col-lg-2 col-md-3">Descrição</th>
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
        <h4 class="modal-title" id="titulo-modal">Alterar nome da Chamada de Veículo</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="altera-nome-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idMidia" name="idMidia" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">

              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Tipo</label>
                <div class="col-lg-4 col-md-4 col-sm-6">
                  <p class="form-control-static" id="tipo"></p>
                </div>
              </div>
              
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



<div class="modal fade" id="myDialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Remover Chamada de Veículo</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="remove-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idMidiaDialog" name="idMidia" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              Deseja realmente remover essa Chamada de Veículo?
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

        params.pageNumber = $('#table-chamadas-veiculos').bootstrapTable('getOptions').pageNumber;
        params.codigo = $("#categoria-combo").val();
        
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
    

    var idTocando = null;
    
    var playChamada = function( element )
    {
        var idMidia = element.attr("idMidia");
        
        if ( idMidia == idTocando && !player.media.paused ){
            player.pause();
        }
        else
        {
            idTocando = idMidia; 
            
            player.pause();
            var url = buildUrl( "/admin/midia/{idMidia}", { idMidia: idMidia });
            
            player.source( url );
            player.play();
        }
        
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

        var url = buildUrl( "/admin/chamada-veiculos/{idMidia}", { idMidia : idMidia } );
        
        $.ajax({
            type: 'DELETE',
            contentType: 'application/json',
            url: url,
            dataType: 'json'
        }).done( function(json){ 

            if (json.ok == 1){
                preencheAlertGeral( "alertArea", "Registro removido com sucesso", "success" );
                $("#table-chamadas-veiculos").bootstrapTable('refresh');
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
        
        var row = $('#table-chamadas-veiculos').bootstrapTable('getRowByUniqueId', idMidia);

        $('#idMidia').val( idMidia );
        $('#nomeMidia').val( row.nome );
        $('#descricaoMidia').val( row.descricao );
        
        var texto = $("#categoria-combo :selected").text();
        
        $('#tipo').html( texto );
        
        $('#myModal').modal('show');
        $('#nomeMidia').focus();
    }


    
    var salvar = function()
    {
        var url = buildUrl( "/admin/chamada-veiculos");
        
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: 'json',
            data:  JSON.stringify( $('#altera-nome-midia-form').serializeJSON() )
            
        }).done( function(json){ 

            if (json.ok == 1){
                preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
                $("#table-chamadas-veiculos").bootstrapTable('refresh');
                $('#myModal').modal('toggle');
            }
            else{
                $('#myModal').modal('toggle');
                preencheErros( json.errors );
            }
        });
    } 
    
    
    var buscaValor = function()
    {
        var result = $("#categoria-combo").val();
        return result;
    }
    
   
    var configuraUploader = function() 
    {
        $('#fileupload').fileupload({
            dataType: 'json',
            formData: { 
                _csrf: $("#csrf").val() 
            },
            stop: function (e, data) {
                preencheAlertGeral( "alertArea", "Upload realizado com sucesso", "success" );
                $("#table-chamadas-veiculos").bootstrapTable('refresh');
                $('#progress .progress-bar').css(
                        'width',
                        0 + '%'
                    );
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
            } 
        }); 
        
        var _url = buildUrl( "/admin/upload-chamadas-veiculos" );

        $('#fileupload').fileupload(
           'option',
           {
              url : _url,
              formData: { 
                _csrf: $("#csrf").val(), 
                codigo : buscaValor() 
              }
           }
        );
    }
   

    $(function(){
        
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

// PRECISO EVOLUIR O PLYR PARA A VERSÃO 1.5 NÃO ESTAVA TOCANDO....

// NÃO ESTAVA NEM CHEGANDO NO SPRING, TALVEZ ELE NO ESTEJA FAZENDO GET.

        plyr.setup( { options : ["current-time", "duration", "mute"]});

        player = $('#player1')[0].plyr;

//         player = plyr.setup( { controls : ["restart", "rewind", "play", "current-time", "duration", "mute" ], fullscreen : { enabled : false } } )[0];
        
 
        $("#categoria-combo").change( function() {
           $("#table-chamadas-veiculos").bootstrapTable('refresh');
           var texto = $("#categoria-combo :selected").text();
           $("#categoria-span").html( texto );
           $("#botao-arquivo").html( "Clique para adicionar arquivos de '" + texto +"'" );

           $('#progress .progress-bar').css(
                   'width',
                   0 + '%'
               );

           var codigo = buscaValor();

           $('#fileupload').fileupload(
              'option',
              {
                 formData: { 
                   _csrf: $("#csrf").val(), 
                   codigo : codigo 
                 }
              }
           );
        });
        
        configuraUploader();
        
        $("#table-chamadas-veiculos").on( 'load-success.bs.table', function( e, data ) {
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
        
        $("#table-chamadas-veiculos").on( 'page-change.bs.table', function ( e, number, size ){
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
        
        $("#outrofileupload").blur(function(){
            mostrarArquivos();
        });
    });

</script>

<style type="text/css">

#table-generos tr{
  cursor: pointer;
}

</style>

<jsp:include page="/WEB-INF/views/bottom.jsp" />