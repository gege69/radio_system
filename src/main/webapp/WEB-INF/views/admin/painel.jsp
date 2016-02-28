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
          Administração
        </div>
        
        <div class="panel-body">
          
          <div class="row">
          
              <div class="row row-centered">
                <div class="container">
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md btn-md botao-main" href="${context}/admin/listagem-cadastro">
                      <i class="fa fa-3x icone-main fa-list-ol"></i>
                      <span class="label-botao-main">Listagem Cadastro</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/admin/cadastro">
                      <i class="fa fa-3x icone-main fa-cloud-upload"></i>
                      <span class="label-botao-main">Cadastro Cliente</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/admin/upload">
                      <i class="fa fa-3x icone-main fa-cloud-upload"></i>
                      <span class="label-botao-main">Upload Mídia</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/admin/monitoracao">
                      <i class="fa fa-3x icone-main fa-eye"></i>
                      <span class="label-botao-main">Monitorar Cliente</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                     
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