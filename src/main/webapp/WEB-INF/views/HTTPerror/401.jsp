<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Acesso Negado<br/>
            <small></small>
          </h3>
          
          <div class="spacer-vertical40"></div>

          <div class="panel panel-default">
            <div class="panel-heading">
              401 - Não autorizado
            </div>
            <div class="panel-body">
            </div>
          
          </div>
          
        </div>
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="/WEB-INF/views/bottom.jsp" />