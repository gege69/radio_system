<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
 
    <div class="row">
    
      <div class="row" id="alertArea"> 
      </div>
      <div class="panel panel-default">
        <div class="panel-body">
        <h3>Mensagens  </h3>
         
        <jsp:include page="/WEB-INF/views/mensagens/template-conversas.jsp" />
        
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
      
      
<link href="${pageContext.request.contextPath}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-datepicker3.css" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.6.2/jquery.serializejson.min.js" defer></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<script src="${pageContext.request.contextPath}/js/required/bootstrap-table/bootstrap-table.js" async></script>
<script src="${pageContext.request.contextPath}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8" async></script>

<script src="${pageContext.request.contextPath}/js/required/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/js/required/bootstrap-datepicker.pt-BR.min.js"></script>

<script src="${pageContext.request.contextPath}/js/required/jsrender.min.js" defer></script>


<script src="${pageContext.request.contextPath}/js/gerenciador/conversas.js" charset="UTF-8"></script>



<jsp:include page="/WEB-INF/views/bottom.jsp" />