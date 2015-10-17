<jsp:include page="../main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
            <input type="text" class="form-control" id="username" name="username" placeholder="Login" value="fpazin">
          </div>
          <div class="form-group">
            <label for="senha">Senha</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Senha" value="123456">
          </div>
          <button type="submit" class="btn btn-default">Entrar</button>
          
          <div class="spacer-vertical20"></div>
          
          <c:if test="${not empty param.err}">
              <div class="alert alert-danger">
                  <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
              </div>
          </c:if> 
        </form>
        
      </div>
    </div>
    
    <div class="spacer-vertical80"></div>
    
    
<!--     <div class="row"> -->
<!--       <div class="col-xs-12 col-md-5 col-sm-6 col-lg-5 col-centered"> -->
<%--         <a href="${context}/register">Clique aqui para se cadastrar</a> --%>
<!--       </div> -->
<!--     </div> -->
      
  </div> <!-- /container -->

<jsp:include page="../bottom.jsp" />