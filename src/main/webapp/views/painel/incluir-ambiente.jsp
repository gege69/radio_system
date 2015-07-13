<jsp:include page="/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Incluir Ambiente<br/>
            <small>Preencha as informações</small>
          </h3>
          
          
          <div class="spacer-vertical40"></div>
          
          <form class="form-horizontal" action="${context}/views/painel/incluir-ambiente.jsp">
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-3">Nome do Ambiente:</label>
              <div class="col-sm-10 col-md-8">
                <input type="text" class="form-control" id="login">
              </div>
            </div>
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-3">Login:</label>
              <div class="col-sm-5 col-md-4">
                <input type="email" class="form-control" id="login" placeholder="Login">
              </div>
            </div>
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-3">Senha:</label>
              <div class="col-sm-5 col-md-4">
                <input type="password" class="form-control" id="senha" placeholder="Senha">
              </div>
            </div>
            
            <div class="form-group">
              <div class="col-sm-offset-5 col-sm-6 col-md-offset-6">
                <button type="submit" class="btn btn-default">Adicionar</button>
              </div>
            </div>
          </form>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-offset-10 col-sm-offset-9 col-xs-offset-7">
        <a class="btn btn-default" href="${context}/views/painel/principal.jsp">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="/bottom.jsp" />