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
          
          <div class="spacer-vertical40"></div>

          <form class="form-horizontal" id="usuario-form" action="#" method="POST">
          
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" id="idUsuario" name="usuario[idUsuario]" value="${idUsuario}" >
            <input type="hidden" id="idEmpresa" name="usuario[idEmpresa]" value="${idEmpresa}" >
  
            <div class="row">
              <div class="col-lg-6 col-md-6">
              
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Nome:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="nome" name="usuario[nome]">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">E-mail:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="email" class="form-control" id="email" name="usuario[email]">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Login:</label>
                  <div class="col-sm-3 col-md-5">
                    <input type="text" class="form-control" id="login" name="usuario[login]" placeholder="Login" style="text-transform: lowercase;">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Senha:</label>
                  <div class="col-sm-3 col-md-5">
                    <input type="password" class="form-control" id="password" name="usuario[password]" placeholder="Senha">
                  </div>
                </div>

                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4"></label>
                  <label style="font-weight: initial; text-align: left; " class="control-label col-sm-2 col-md-4">
                    <input type="checkbox" id="mostrarSenha" name="mostrarSenha" value="false"> Mostrar senha
                  </label>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Ativo: </label>
                  <div class="checkbox col-sm-3 col-md-5">
                    <label>
                      <input type="checkbox" id="ativo" name="usuario[ativo]" value="true"> 
                    </label>
                  </div>
                </div>
                
              </div>
              
              <div class="col-lg-5 col-md-5">
                <table  
                   id="table-perfis"
                   class="tabela-perfis"
                   data-toggle="table"
                   data-url="${context}/perfis/"
                   data-side-pagination="server"
                   data-pagination="true"
                   data-page-size=7
                   data-page-list="[5]"
                   data-locale = "pt_BR"
                   data-height="400"
                   data-query-params="queryParamsPerfis" >
                    <thead>
                      <tr>
                        <th data-field="state" data-checkbox="true"></th>
<!--                         <th data-field="idPerfil" >Id</th> -->
                        <th data-field="nome">Perfil</th>
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
                  <a class="btn btn-default" href="${context}/perfis/view">
                    <i class="fa fa-legal"></i>
                    Administrar Perfis</a>
                </div>
              </div>
            </div>
            
            <div class="spacer-vertical40"></div>
            
            <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <div class="">
                  <a class="btn btn-default" href="${context}/usuarios/searches" >
                    <i class="fa fa-arrow-left"></i>
                    Voltar para Usuários</a>
                </div>            
              </div>
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <div class="pull-right">
                  <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
                </div>
              </div>
            </div>
          </form>
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