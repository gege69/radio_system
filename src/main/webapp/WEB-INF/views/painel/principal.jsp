<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

  <div class="container theme-showcase">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-heading">
          Painel Gerencial
        </div>
        
        <div class="panel-body">
          
          <div class="row">
          
            <ul class="glyphicon-icons" style="max-width: 100%">
              <div class="row row-centered">
                <div class="container">
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md btn-md" href="${context}/gerenciador/incluir-ambiente">
                      <i class="fa fa-3x icone-main fa-shopping-cart"></i>
                      <span class="label-botao-main">Incluir Ambiente</span>
                    </a>
                  </li>
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md" href="${context}/gerenciador/administrar-ambiente">
                      <i class="fa fa-3x icone-main fa-random"></i>
                      <span class="label-botao-main">Administar Ambiente</span>
                    </a>
                  </li>
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md" href="${context}/gerenciador/alterar-senha">
                      <i class="fa fa-3x icone-main fa-lock"></i>
                      <span class="label-botao-main">Alterar Senha</span>
                    </a>
                  </li>
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md" href="${context}/gerenciador/mensagens">
                      <i class="fa fa-3x icone-main fa-envelope-o"></i>
                      <span class="label-botao-main">Mensagens</span>
                    </a>
                  </li>
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md" href="${context}/gerenciador/monitoramento">
                      <i class="fa fa-3x icone-main fa-eye"></i>
                      <span class="label-botao-main">Monitoramento</span>
                    </a>
                  </li>
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md" href="${context}/gerenciador/fazer">
                      <i class="fa fa-3x icone-main fa-users"></i>
                      <span class="label-botao-main-duas-linhas">Chamada de</br>Funcionários</span>
                    </a>
                  </li>
                </div>              
              </div>
              
              <div class="row row-centered top-buffer">
                <div class="container">
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md" href="${context}/gerenciador/fazer">
                      <i class="fa fa-3x icone-main fa-cloud-upload"></i>
                      <span class="label-botao-main">Upload Ambientes</span>
                    </a>
                  </li>
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md" href="${context}/gerenciador/fazer">
                      <i class="fa fa-3x icone-main fa-key"></i>
                      <span class="label-botao-main">Ferramentas</span>
                    </a>
                  </li>
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md" href="${context}/gerenciador/fazer">
                      <i class="fa fa-3x icone-main fa-floppy-o"></i>
                      <span class="label-botao-main">Softwares</span>
                    </a>
                  </li>
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md" href="${context}/gerenciador/fazer">
                      <i class="fa fa-3x icone-main fa-user-plus"></i>
                      <span class="label-botao-main">Administradores</span>
                    </a>
                  </li>
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md" href="${context}/gerenciador/fazer">
                      <i class="fa fa-3x icone-main fa-android"></i>
                      <span class="label-botao-main">Mobile</span>
                    </a> 
                  </li>
                  <li class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                     
                  </li>
                </div>              
              </div>
            </ul>
          </div>
          
          <div class="spacer-vertical40">
          </div>
          
          <div class="row">
            <div class="col-md-4 col-sm-3 col-xs-3">
              Você possui XX ambientes cadastrados
            </div>
            <div class="col-md-3 col-md-offset-10   col-sm-2 col-sm-offset-7   col-xs-4 col-xs-offset-5">
              <c:url var="logoutUrl" value="/logout"/>
              <form action="${logoutUrl}" method="post">
                <input type="submit" class="btn btn-link" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              </form>
            </div>
          </div>
          
        </div>
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="/WEB-INF/views/bottom.jsp" />