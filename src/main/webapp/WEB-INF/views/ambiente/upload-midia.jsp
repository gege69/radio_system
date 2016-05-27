<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
 
    <div class="row">
    
      <div class="row">
        <div class="col-lg-12 col-md-12" id="alertArea">
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Gerenciar ${nomeCategoria}<br/>
            <small>Espaço para armazenamento: 0 MB em uso, 500 MB disponíveis </small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    
                    <div class="col-md-12">
                      <form 
                            action="#" 
                            method="POST" 
                            id="ambiente-upload-midia" 
                            class="form" 
                            enctype="multipart/form-data">
                      
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" id="idAmbiente" name="idAmbiente" value="${idAmbiente}">
                        <input type="hidden" id="idCategoria" name="idCategoria" value="${idCategoria}">
                        <input type="hidden" id="nomeCategoria" name="nomeCategoria" value="${nomeCategoria}">
                        
                        <input type="file" id="fileupload" name="file" multiple style="display : none;">

                        <div class="col-lg-2 col-md-3 col-sm-4">
                          <span class="btn btn-primary btn-file">
                              Escolha os arquivos<input type="file" id="outrofileupload" name="file2" multiple>
                          </span>
                        </div>

                        <input type="file" id="fileupload" name="file" multiple style="display : none;">

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

                        <div class="col-lg-7 col-md-9 col-sm-12">
                          <div class="form-group">
                            <label for="file">Descrição:</label>
                            <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Preencha alguma descrição da mídia">
                          </div>
                        </div>

                        <div class="col-lg-7 col-md-9 col-sm-12">
                          <div class="form-group">
                            <label class="control-label" for="chave">Data Início Validade:</label>
                            <div class="input-group date col-lg-4 col-md-6 col-sm-5" id="componenteDatePickerInicio">
                              <input type="text" class="form-control" id="dataInicio" name="dataInicio">
                                <span class="input-group-addon">
                                  <i class="fa fa-calendar"></i>
                                </span>
                            </div>
                          </div>
                          
                          <div class="form-group">
                            <label class="control-label" for="chave">Data Fim Validade:</label>
                            <div class="input-group date col-lg-4 col-md-6 col-sm-5" id="componenteDatePickerFim">
                              <input type="text" class="form-control" id="dataFim" name="dataFim">
                                <span class="input-group-addon">
                                  <i class="fa fa-calendar"></i>
                                </span>
                            </div>
                          </div>
                        </div>
                        

                        <div class="col-lg-7 col-md-9 col-sm-12">
                          <div class="form-group">
                            <label for="file">Categorias:</label>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                              <div class="form-group" id="checkBoxContainer">
                              </div>
                            </div>
                          </div>
                        </div>

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
                        
                      </form>
                    </div>
                    
                    
                  </div>
                </div>
              
                <div class="panel-body">
                   
                  <div class="row">
                    <div class="col-lg-12 col-md-12">
                      <table  
                         id="tabelaMidiaUpload"
                         data-toggle="table"
                         data-url="${context}/ambientes/${idAmbiente}/midias-por-categoria/${idCategoria}/"
                         data-height="400"
                         data-side-pagination="server"
                         data-pagination="true"
                         data-page-size=7
                         data-page-list="[7]"
                         data-locale = "pt_BR"
                         data-unique-id="idMidia"
                         data-search="true"
                         data-query-params="queryParams" >
                        <thead>
                          <tr>
<!--                               <th data-field="idMidia">ID</th> -->
                              <th data-field="nome">Nome</th>
                              <th data-field="descricao">Descrição</th>
                              <th data-field="dataInicioValidade">Início Validade</th>
                              <th data-field="dataFimValidade">Fim Validade</th>
                              <th data-field="vinheta" data-formatter="catFormatter" data-align="center">Vinheta</th>
                              <th data-field="inst" data-formatter="catFormatter" data-align="center">Institucional</th>
                              <th data-field="comercial" data-formatter="catFormatter" data-align="center">Comercial</th>
                              <th data-field="programete" data-formatter="catFormatter" data-align="center">Programete</th>
                              <th data-field="chamada_inst" data-formatter="catFormatter" data-align="center">Cham. Inst</th>
                              <th data-field="idMidia" data-formatter="deleteFormatter" data-align="center">Deletar</th>
                          </tr>
                        </thead>
                      </table>
                    </div>                        
                  </div>
                  
                  
                  
                  <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                      <div class="">
                        <a class="btn btn-default" href="#" id="aplicar-programacao" >
                          <i class="fa fa-refresh"></i>
                          Aplicar ${nomeCategoria}(s) na programação agora
                        </a>
                      </div>            
                    </div>
                  </div>
                  
                  
                </div>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/view" >
                  <i class="fa fa-arrow-left"></i>
                  Voltar para ${nome}
                </a>
              </div>            
            </div>
          </div>
          
        </div>
      </div>
    </div>
    

      
  </div> <!-- /container -->



<script src="${context}/js/required/jsrender.min.js"></script>
<script src="${context}/js/required/bootbox.min.js"></script>
<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>

<script src="${context}/js/required/jquery-ui.min.js"></script>

<script src="${context}/js/required/jquery.iframe-transport.js"></script>
<script src="${context}/js/required/jquery.fileupload.js"></script>

<script src="${context}/js/required/bootstrap-datepicker.min.js"></script>
<script src="${context}/js/required/bootstrap-datepicker.pt-BR.min.js"></script>
<link href="${context}/css/bootstrap-datepicker3.css" rel="stylesheet">

<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">

<script id="viewTmpl" type="text/x-jsrender">
<label class="checkbox-inline">
  <input type="checkbox" class="checkbox-categoria" id="inlineCheck{{:idCategoria}}" name="categorias[idCategoria]" value="{{:idCategoria}}"> {{:nome}}
</label>
</script>  



<script src="${context}/js/ambiente/upload-midia.js"  charset="UTF-8"></script>




<jsp:include page="/WEB-INF/views/bottom.jsp" />