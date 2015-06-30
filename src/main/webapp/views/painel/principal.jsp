<jsp:include page="/main.jsp" />    
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
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-send"></span>
                    <a href="#">Teste</a>
                  </li>
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-music"></span>
                    <a href="#">Teste</a>
                  </li>
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-stats"></span>
                    <a href="#">Teste</a>
                  </li>
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-apple"></span>
                    <a href="#">Teste</a>
                  </li>
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-bitcoin"></span>
                    <a href="#">Teste</a>
                  </li>
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-phone"></span>
                    <a href="#">Teste</a>
                  </li>
                </div>              
              </div>
              
              <div class="row row-centered">
                <div class="container">
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-folder-open"></span>
                    <a href="#">Teste</a>
                  </li>
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-tint"></span>
                    <a href="#">Teste</a>
                  </li>
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-tag"></span>
                    <a href="#">Teste</a>
                  </li>
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-th-list"></span>
                    <a href="#">Teste</a>
                  </li>
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-home"></span>
                    <a href="#">Teste</a>
                  </li>
                  <li class="col-md-2 col-sm-2 col-xs-2">
                    <span class="glyphicon glyphicon-camera"></span>
                    <a href="#">Teste</a>
                  </li>
                </div>              
              </div>
            </ul>
          </div>
          
          <div class="row" style="padding: 25px;">
          </div>
          
          <div class="row">
            <div class="col-md-3 col-sm-3 col-xs-3">
              Você possui XX ambientes cadastrados
            </div>
            <div class="col-md-2 col-md-offset-7   col-sm-2 col-sm-offset-7   col-xs-4 col-xs-offset-5">
              <a href="${context}/">Logout</a>
            </div>
          </div>
          
        </div>
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="/bottom.jsp" />