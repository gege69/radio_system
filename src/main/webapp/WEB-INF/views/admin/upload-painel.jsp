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
          Upload de Mídias
        </div>
        
        <div class="panel-body">
          
          <div class="row">
          
              <div class="row row-centered">
                <div class="container">
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md btn-md botao-main" href="${context}/admin/upload-chamadas-veiculos/view">
                      <i class="fa fa-3x icone-main fa-car"></i>
                      <span class="label-botao-main">Chamada de Veículos</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/admin/upload-letras/view">
                      <i class="fa fa-3x icone-main fa-font"></i>
                      <span class="label-botao-main">Letras e Números</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/admin/tocar-chamadas-veiculos/view">
                      <span class="fa-stack fa-2x" style=" margin: 3px auto 12px;">
                        <i class="fa fa-play fa-stack-2x"></i>
                        <i class="fa fa-car fa-stack-1x fa-inverse"></i>
                      </span>
                      <span class="label-botao-main">Tocar<br/>Chamadas Veículos</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/admin/upload-musica/view">
                      <i class="fa fa-3x icone-main fa-music"></i>
                      <span class="label-botao-main">Música</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/admin/upload-horoscopo/view">
                      <i class="fa fa-3x icone-main fa-star"></i>
                      <span class="label-botao-main">Horóscopo</span>
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