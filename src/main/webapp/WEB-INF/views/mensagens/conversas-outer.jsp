<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="row" id="alertArea"> 
      </div>
      <div class="panel panel-default">
        <div class="panel-body">
        <h3>Mensagens  </h3>
         
        <jsp:include page="/WEB-INF/views/mensagens/conversas.jsp" />
        
        </div>
      </div> 
    </div>
    
    <div class="row">
      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
        <div class="">
        </div>            
      </div>
      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
        <div class="pull-right">
          <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
        </div>
      </div>
    </div>
      
  </div> <!-- /container -->
      
      

<jsp:include page="/WEB-INF/views/bottom.jsp" />