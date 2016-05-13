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
          <h3>Usuário<br/>
            <small>Preencha as informações do Usuário</small>
          </h3>
          
          <div class="spacer-vertical40"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6">
              <table  
                 id="tablePerfis"
                 class="tabelaPerfis"
                 data-toggle="table"
                 data-url="${context}/perfis/"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=5
                 data-page-list="[5]"
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
              <table  
                 id="tablePermissoes"
                 class="tabelaPermissoes"
                 data-toggle="table"
                 data-url="${context}/perfis/permissoes"
                 data-side-pagination="server"
                 data-pagination="false"
                 data-page-size=15
                 data-page-list="[15]"
                 data-locale = "pt_BR"
                 data-height="600"
                 data-query-params="queryParamsPerfis" >
                  <thead>
                    <tr>
                      <th data-field="state" data-checkbox="true"></th>
                      <th data-field="codigo">Permissões</th>
                    </tr>
                  </thead>
              </table>
            </div>
          </div>


          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <button type="button" class="btn btn-primary" id="btnSalvar">
                  <i class="fa fa-floppy-o"></i>
                  Salvar Alterações
                </button>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/principal">
                  <i class="fa fa-key"</i>
                  Administrar Perfis</a>
              </div>
            </div>
          </div>

          
          
          <div class="spacer-vertical40"></div>
          
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
<script src="${context}/js/required/jquery.populate.js"></script>

<script type="text/javascript" src="${context}/js/required/zxcvbn.js"></script>

<script src="${context}/js/gerenciador/usuario.js"></script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />