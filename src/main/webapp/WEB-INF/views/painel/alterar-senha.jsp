<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="col-md-10 col-sm-10">
        <div class="panel panel-default">
          <div class="panel-body">
            <h3>Alterar Senha<br/>
              <small>Atualize com frequência sua senha</small>
            </h3>
            
            
            <div class="spacer-vertical40"></div>
            
            <div>
              <form class="form-horizontal" action="#">
    
                <div class="form-group">
                  <label for="login" class="control-label col-sm-3 col-md-4">Senha Atual:</label>
                  <div class="col-sm-5 col-md-4">
                    <input type="password" class="form-control" id="login" placeholder="Atual">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-3 col-md-4">Nova Senha:</label>
                  <div class="col-sm-5 col-md-4">
                    <input type="password" class="form-control" id="login" placeholder="Nova">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-3 col-md-4">Confirmação Nova Senha:</label>
                  <div class="col-sm-5 col-md-4">
                    <input type="password" class="form-control" id="senha" placeholder="Confirmação">
                  </div>
                </div>
                
                <div class="form-group">
                  <div class="col-sm-offset-5 col-sm-6 col-md-offset-6">
                    <button type="submit" class="btn btn-default">Confirmar</button>
                  </div>
                </div>
              </form>
            </div>
            
          </div>
        </div>
      </div>
      
    </div>
    
    <div class="row">
      <div class="col-md-offset-8 col-sm-offset-7 col-xs-offset-7">
        <a class="btn btn-default" href="${context}/gerenciador/principal">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="/WEB-INF/views/bottom.jsp" />