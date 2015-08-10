<jsp:include page="main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />


  <div class="container theme-showcase">
  
    <div class="jumbotron">
      <h1>Logotipo aqui!</h1>
    </div>

    <div class="row row-centered">
      <div class="col-xs-12 col-md-5 col-sm-6 col-lg-5 col-centered">
      
        <form action="login" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="form-group">
            <label for="login">Login</label>
            <input type="email" class="form-control" id="login" placeholder="Login">
          </div>
          <div class="form-group">
            <label for="senha">Senha</label>
            <input type="password" class="form-control" id="senha" placeholder="Senha">
          </div>
          <button type="submit" class="btn btn-default">Entrar</button>
        </form>
        
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="bottom.jsp" />