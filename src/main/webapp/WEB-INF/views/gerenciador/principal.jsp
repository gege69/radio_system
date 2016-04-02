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
                    <a class="btn btn-default btn-block btn-md btn-md botao-main" href="${context}/ambientes/new">
                      <i class="fa fa-2x icone-main fa-plus"></i>
                      <i class="fa fa-3x icone-main fa-tasks"></i>
                      <span class="label-botao-main">Incluir Ambiente</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/ambientes/searches">
                      <i class="fa fa-3x icone-main fa-tasks"></i>
                      <span class="label-botao-main">Administar Ambientes</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/senha/edit">
                      <i class="fa fa-3x icone-main fa-lock"></i>
                      <span class="label-botao-main">Alterar Senha</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/conversas/view">
                      <i class="fa fa-3x icone-main fa-envelope-o"></i>
                      <span class="label-botao-main">Mensagens</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main disabled" data-toggle="modal" href="${context}/fazer">
                      <i class="fa fa-3x icone-main fa-eye"></i>
                      <span class="label-botao-main">Monitoramento</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/view-atalho-cham-func">
                      <i class="fa fa-3x icone-main fa-users"></i>
                      <span class="label-botao-main">Chamada de</br>Funcion�rios</span>
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
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/clientes/view">
                      <i class="fa fa-3x icone-main fa-street-view"></i>
                      <span class="label-botao-main">Dados de Cliente</span>
                    </a> 
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main disabled" data-toggle="modal" href="${context}/fazer">
                      <i class="fa fa-3x icone-main fa-floppy-o"></i>
                      <span class="label-botao-main">Softwares</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/view-list-usuarios-sistema">
                      <i class="fa fa-3x icone-main fa-user-plus"></i>
                      <span class="label-botao-main">Usu�rios do<br/>Sistema</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main disabled" data-toggle="modal" href="${context}/fazer">
                      <i class="fa fa-3x icone-main fa-android"></i>
                      <span class="label-botao-main">Mobile</span>
                    </a> 
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main disabled" data-toggle="modal" href="${context}/fazer">
                      <i class="fa fa-3x icone-main fa-key"></i>
                      <span class="label-botao-main">Ferramentas</span>
                    </a>
                  </div>
                </div>              
              </div>
              
              <div class="row row-centered">
                <div class="container">
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <c:if test="${isAdministrador}">
                      <a class="btn btn-default btn-block btn-md botao-main" href="${context}/admin/painel">
                        <i class="fa fa-3x icone-main fa-street-view"></i>
                        <span class="label-botao-main">ADMINISTRAR</span>
                      </a> 
                    </c:if>
                  </div>
                </div>
              </div>
          </div>
          
          <div class="spacer-vertical40">
          </div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              Voc� possui ${qtdAmbientes} ambientes cadastrados
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