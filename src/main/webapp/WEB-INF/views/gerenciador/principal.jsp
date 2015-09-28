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
          
              <div class="row row-centered">
                <div class="container">
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md btn-md botao-main" href="${context}/incluir-ambiente">
                      <i class="fa fa-3x icone-main fa-shopping-cart"></i>
                      <span class="label-botao-main">Incluir Ambiente</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/administrar-ambiente">
                      <i class="fa fa-3x icone-main fa-random"></i>
                      <span class="label-botao-main">Administar Ambiente</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/alterar-senha">
                      <i class="fa fa-3x icone-main fa-lock"></i>
                      <span class="label-botao-main">Alterar Senha</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/mensagens">
                      <i class="fa fa-3x icone-main fa-envelope-o"></i>
                      <span class="label-botao-main">Mensagens</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/monitoramento">
                      <i class="fa fa-3x icone-main fa-eye"></i>
                      <span class="label-botao-main">Monitoramento</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/view-atalho-cham-func">
                      <i class="fa fa-3x icone-main fa-users"></i>
                      <span class="label-botao-main">Chamada de</br>Funcionários</span>
                    </a>
                  </div>
                </div>              
              </div>
              
              <div class="row row-centered">
                <div class="container">
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/view-upload-multi">
                      <i class="fa fa-3x icone-main fa-cloud-upload"></i>
                      <span class="label-botao-main">Upload Ambientes</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/fazer">
                      <i class="fa fa-3x icone-main fa-key"></i>
                      <span class="label-botao-main">Ferramentas</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/fazer">
                      <i class="fa fa-3x icone-main fa-floppy-o"></i>
                      <span class="label-botao-main">Softwares</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/view-list-usuarios-sistema">
                      <i class="fa fa-3x icone-main fa-user-plus"></i>
                      <span class="label-botao-main">Usuários do<br/>Sistema</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/fazer">
                      <i class="fa fa-3x icone-main fa-android"></i>
                      <span class="label-botao-main">Mobile</span>
                    </a> 
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                     
                  </div>
                </div>              
              </div>
          </div>
          
          <div class="spacer-vertical40">
          </div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              Você possui ${qtdAmbientes} ambientes cadastrados
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
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
    </div>
      
  </div> <!-- /container -->

<jsp:include page="/WEB-INF/views/bottom.jsp" />