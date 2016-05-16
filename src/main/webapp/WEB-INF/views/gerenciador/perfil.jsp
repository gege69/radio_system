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
              <h3>Usuário<br/>
                <small>Preencha as informações do Usuário</small>
              </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div> 
          
          <div class="row spacer-vertical20"></div> 

          <input type="hidden" id="idPerfil" name="idPerfil">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

          <div class="row">
            <div class="col-lg-6 col-md-6">
              <table  
                 id="tablePerfis"
                 class="tabelaPerfis"
                 data-toggle="table"
                 data-url="${context}/perfis/"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=9
                 data-unique-id="idPerfil"
                 data-locale = "pt_BR"
                 data-height="400"
                 data-query-params="queryParamsPerfis" >
                  <thead>
                    <tr>
                      <th data-field="idPerfil">Id</th>
                      <th data-field="nome">Perfil</th>
                    </tr>
                  </thead>
              </table>
            </div>
            
            <div class="col-lg-6 col-md-6">
              <h4><span id="indicador" style="display: none;" class="label label-primary"></span></h4>
              <table  
                 id="tablePermissoes"
                 class="tabelaPermissoes"
                 data-toggle="table"
                 data-url="${context}/perfis/permissoes"
                 data-side-pagination="server"
                 data-pagination="false"
                 data-locale = "pt_BR"
                 data-unique-id="idPermissao"
                 data-height="400"
                 data-query-params="queryParamsPerfis" >
                  <thead>
                    <tr>
                      <th data-field="state" data-checkbox="true"></th>
                        <th data-field="idPermissao">Id</th>
                      <th data-field="descricao">Permissões</th>
                    </tr>
                  </thead>
              </table>
            </div>
          </div>

          <div class="spacer-vertical20"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <button type="button" class="btn btn-primary" id="btnSalvarPermissoes">Salvar Permissões</a>
              </div>
            </div>
          </div>

          <div class="spacer-vertical20"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="javascript:history.back(1)">
                  <i class="fa fa-arrow-left"></i>
                  Voltar</a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
      
  </div> <!-- /container -->

<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">

<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>
<script src="${context}/js/required/jquery.serializejson.js"></script>

<script src="${context}/js/gerenciador/perfil.js" charset="UTF-8"></script>



<style type="text/css">

#tablePerfis tr{
  cursor: pointer;
}

</style>

<jsp:include page="/WEB-INF/views/bottom.jsp" />