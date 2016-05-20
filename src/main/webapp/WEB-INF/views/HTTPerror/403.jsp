<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
 
    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Acesso Negado<br/>
            <small></small>
          </h3>
          
          <div class="spacer-vertical40"></div>

          <div class="panel panel-default">
            <div class="panel-heading">
              <p>
                403 - Informe o erro ao Administrador do Sistema, os motivos são:
              </p>
              <p>
                Você não possui permissão para essa funcionalidade, ou;
              </p>
              <p>
                Você está acessando duas conexões com o mesmo navegador e por motivos de segurança o sistema realiza o bloqueio, por favor, utilize apenas uma sessão de acesso.             
              </p>
            </div>
            <div class="panel-body">
            </div>
          </div>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-offset-10 col-sm-offset-9 col-xs-offset-7">
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
          <input type="submit" class="btn btn-link" value="Log out" />
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="/WEB-INF/views/bottom.jsp" />