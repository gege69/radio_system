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
              <h3>Upload de M�sicas </h3>
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

                      <h4>G�neros musicais</h4>

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
                    Voltar para Upload de M�dias</a>    
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                  <div class="pull-right">
                    <a class="btn btn-default" href="${context}/admin/musicas/gerencia/view">
                    <i class="fa fa-pie-chart"></i>
                    <i class="fa fa-music"></i>
                    &nbsp;Ger�ncia de M�sicas</a>    
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
                  <button type="button" class="btn btn-default" id="btnAlterarGenerosGeral" title="Alterar G�neros de TODAS as m�sicas que est�o sendo listadas pela Grid" >
                    <i class="material-icons md-18">library_music</i>
                    Alterar G�neros
                  </button>
                  <button type="button" class="btn btn-default" id="btnDeletarSelecao" title="Deletar TODAS as m�sicas que est�o sendo listadas pela Grid">
                    <i class="material-icons md-18">delete_forever</i>
                    Deletar M�sicas
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
                 data-click-to-select="true"
                 data-search="true"
                 data-page-list = "[7,15,30,100]"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="state" data-checkbox="true" style="width: 36px;"></th>
                      <th data-field="nome">Nome do Arquivo</th>
                      <th data-field="generosResumo">G�nero(s)</th>
                      <th data-field="artist">Artista</th>
                      <th data-field="idMidia" data-click-to-select="false" data-formatter="editarFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Editar</th>
                      <th data-field="idMidia" data-click-to-select="false" data-formatter="removerFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Remover</th>
                      <th data-field="idMidia" data-click-to-select="false" data-formatter="playFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Tocar</th>
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
                Voltar para Upload de M�dias</a>    
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


<jsp:include page="/WEB-INF/views/admin/template-modais-grid-musica.jsp" />


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

#table-musicas tr{
  cursor: pointer;
}

</style>



<jsp:include page="/WEB-INF/views/bottom.jsp" />