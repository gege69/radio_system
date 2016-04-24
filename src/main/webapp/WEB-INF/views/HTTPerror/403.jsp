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
              403 - Acesso Negado
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