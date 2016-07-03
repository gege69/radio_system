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
              <h3>Upload de Músicas </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>

          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              
              <div class="row">
                <div class="col-lg-12 col-md-12">

                  <div class="panel panel-default">
                    <div class="panel-body">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf" />

                      <input type="file" id="fileupload" name="file" multiple style="display : none;">

                      <div class="col-lg-2 col-md-3 col-sm-4">
                        <span class="btn btn-primary btn-file">
                            Escolha os arquivos<input type="file" id="outrofileupload" name="file2" multiple>
                        </span>
                      </div>
                      
                      <div class="col-lg-offset-2 col-md-offset-3 col-sm-offset-4">          
                        <p class="form-control-static" id="static-arquivos"></p>
                      </div>

                      <div class="spacer-vertical10"></div>

                      <div id="resultados">
                        <div id="progress" class="progress">
                            <div class="progress-bar progress-bar-success"></div>
                        </div>
                        <div id="files" class="files"></div>            
                      </div>

                      <div class="spacer-vertical10"></div>

                      <h4>Gêneros musicais</h4>

                      <div class="container col-md-12" id="view-container">
<!--                       http://stackoverflow.com/questions/33699782/multi-column-lists-in-bootstrap-foundation -->
                      
                      
                      </div>

                      <div class="spacer-vertical10"></div>

                      <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                          <div class="pull-right">
                            <button type="button" class="btn btn-success" id="btnIniciar">
                              <i class="fa fa-lg fa-cloud-upload"></i> Iniciar Upload</a> 
                            </button>
                          </div>          
                        </div>
                      </div>            
                      
                    </div>
                  </div>

                </div>
              
              </div>
            
              <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                    <a class="btn btn-default" href="${context}/admin/upload-painel/view">
                    <i class="fa fa-arrow-left"></i>
                    Voltar para Upload de Mídias</a>    
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                  <div class="pull-right">
                    <a class="btn btn-default" href="${context}/admin/musicas/gerencia/view">
                    <i class="fa fa-pie-chart"></i>
                    <i class="fa fa-music"></i>
                    &nbsp;Gerência de Músicas</a>    
                  </div>          
                </div>
              </div>            

              <div class="spacer-vertical20"></div>
              
              <div class="row">
                <div class="col-lg-12 col-md-12">
                  <p><span class="totalMusicas">0</span> <span id="totalMusicasTexto"></span></p>
                </div>
              </div>
              

              <div id="toolbar">
                <div class="form-inline" role="form">
                  <button type="button" class="btn btn-default" id="btnLimparFiltro">
                    <i class="fa fa-refresh"></i>
                    Limpar Filtro
                  </button>
                  <button type="button" class="btn btn-default" id="btnAlterarGenerosGeral" title="Alterar Gêneros de TODAS as músicas que estão sendo listadas pela Grid" >
                    <i class="material-icons md-18">library_music</i>
                    Alterar Gêneros
                  </button>
                  <button type="button" class="btn btn-default" id="btnDeletar" title="Deletar TODAS as músicas que estão sendo listadas pela Grid">
                    <i class="material-icons md-18">delete_forever</i>
                    Deletar Músicas
                  </button>
                </div>
              </div>
              <table  
                 id="table-musicas"
                 data-toggle="table"
                 data-url="${context}/admin/midias/musicas"
                 data-height="555"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=7
                 data-locale = "pt_BR"
                 data-toolbar="#toolbar"
                 data-unique-id="idMidia"
                 data-search="true"
                 data-page-list = "[7,15,30]"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="idMidia" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">ID</th>
                      <th data-field="nome" class="col-lg-4 col-md-3 col-sm-3 col-xs-3">Nome do Arquivo</th>
                      <th data-field="generosResumo" class="col-lg-2 col-md-3 col-sm-3 col-xs-3">Gênero(s)</th>
                      <th data-field="artist" class="col-lg-2 col-md-2 col-sm-2 col-sm-2">Artista</th>
                      <th data-field="idMidia" data-formatter="editarFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Editar</th>
                      <th data-field="idMidia" data-formatter="removerFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Remover</th>
                      <th data-field="idMidia" data-formatter="playFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Tocar</th>
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



<div class="modal" id="myModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Alterar dados da Música</h4>
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

              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Descrição</label>
                <div class="col-lg-8 col-md-10">
                  <input type="text" class="form-control" id="descricaoMidia" name="descricao">
                </div>
              </div>

              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Artista</label>
                <div class="col-lg-8 col-md-10">
                  <input type="text" class="form-control" id="artistaMidia" name="artist">
                </div>
              </div>

              <h4>Gêneros da música</h4>

              <div class="container col-md-12" id="viewContainerModal">
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



<div class="modal" id="myModalGenerosGeral">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Alterar Gêneros de <span class="qtdMusicasGeneros"></span> Música(s)</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="altera-nome-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="alert alert-warning" id="divWarningGeneros" role="alert">
                <strong>Atenção!</strong> Os gêneros escolhidos aqui serão atribuídos para TODAS as músicas selecionadas pela pesquisa atual.<br/>
                <span class="totalMusicas"></span> música(s) listada(s).
              </div>

            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">

              <h4>Atribuir Gêneros para <span class="totalMusicas"></span> música(s) listada(s)</h4>

              <div class="container col-md-12" id="viewContainerGenerosGeralModal">
              </div>

            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
        <button type="button" class="btn btn-primary" id="btnSalvarGenerosGeral">Alterar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal" id="myDialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Remover Música</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="remove-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idMidiaDialog" name="idMidia" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <h4><span id="nomeMusica"></span></h4>
            </div>
          
            <div class="spacer-vertical10"></div>

            <div class="col-lg-12 col-md-12">
              Deseja realmente remover essa Música?
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

<script src="${context}/js/required/jsrender.min.js"></script>
<script src="${context}/js/required/reckon.min.js"></script>

<link rel="stylesheet" href="https://cdn.plyr.io/1.3.7/plyr.css" defer>
<script src="https://cdn.plyr.io/1.3.7/plyr.js" defer></script>

<link rel="stylesheet" href="http://s.mlcdn.co/animate.css">

<script id="viewTmpl" type="text/x-jsrender">
    
      <div class="checkbox col-lg-4 col-md-4 col-sm-6 col-xs-12">
        <label>
          <input type="checkbox" class="checkbox-genero" idGenero="{{:idGenero}}" name="genero[idGenero]" value="{{:idGenero}}"> {{:descricao}}
        </label>
      </div>
      
</script>  

<script src="${context}/js/admin/upload-musica.js" charset="UTF-8"></script>
<script src="${context}/js/admin/grid-musica.js" charset="UTF-8"></script>

<style type="text/css">
.modal {
  overflow-y: hidden;
}
body.modal-open {
  margin-right: 0;
}

</style>



<jsp:include page="/WEB-INF/views/bottom.jsp" />