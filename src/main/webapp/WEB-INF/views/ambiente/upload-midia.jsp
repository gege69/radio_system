<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="row" id="alertArea">

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
                      <form action="${context}/ambientes/${idAmbiente}/view-upload-midia/${codigo}" 
                            method="POST" 
                            id="ambiente-upload-midia" 
                            class="form" 
                            enctype="multipart/form-data">
                      
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" id="idCategoria" name="idCategoria" value="${idCategoria}">
                        <input type="hidden" id="idAmbiente" name="idAmbiente" value="${idAmbiente}">
                        
                        
                        <div class="col-lg-7 col-md-9 col-sm-12">
                          <div class="form-group">
                            <label for="file">Arquivo:</label>
                            <input type="file" class="form-control" id="arquivo" name="file" placeholder="algum arquivo">
                          </div>
                        </div>

                        <div class="col-lg-7 col-md-9 col-sm-12">
                          <div class="form-group">
                            <label for="file">Descrição:</label>
                            <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Preencha alguma descrição da mídia">
                          </div>
                        </div>

                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                          <div class="form-group" id="checkBoxContainer">
                          </div>
                        </div>
                        
                        <div class="row">
                          <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <div class="form-group">
                              <a class="btn btn-primary" href="#" id="btnUploadMidia">Upload Mídia</a>
                            </div>
                          </div>
                          <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <div class="form-group pull-right">
                              <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/view-pesquisa-midia" id="btnPesquisar">Busca Básica</a>
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
                         id="table"
                         data-toggle="table"
                         data-url="${context}/ambientes/${idAmbiente}/midias-por-categoria/${idCategoria}/"
                         data-height="400"
                         data-side-pagination="server"
                         data-pagination="true"
                         data-page-size=7
                         data-page-list="[7]"
                         data-locale = "pt_BR"
                         data-query-params="queryParams" >
                        <thead>
                          <tr>
<!--                               <th data-field="idMidia">ID</th> -->
                              <th data-field="nome">Nome</th>
                              <th data-field="descricao">Descrição</th>
                              <th data-field="dataUpload">Upload</th>
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


<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">

<script id="viewTmpl" type="text/x-jsrender">
<label class="checkbox-inline">
  <input type="checkbox" id="inlineCheck{{:idCategoria}}" name="categorias[]" value="{{:idCategoria}}"> {{:nome}}
</label>
</script>  

<script src="${context}/js/ambiente/upload-midia.js"  charset="UTF-8"></script>




<jsp:include page="/WEB-INF/views/bottom.jsp" />