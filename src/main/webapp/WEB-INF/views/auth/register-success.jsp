<jsp:include page="../main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

  <div class="container theme-showcase">
  
    <div class="jumbotron">
      <h1>Logotipo aqui!</h1>
    </div>

    <div class="row">
      <div class="col-xs-12 col-md-5 col-sm-6 col-lg-5">

        <div class="panel panel-default">
            <div class="panel-heading">
              <p>Usuário cadastrado com sucesso: ${username}</p>
              <p>Será enviado um email para a validação do cadastro.</p>
            </div>
            <div class="panel-body">
              <a href="${context}/login">Clique aqui para realizar o login</a> 
            </div>
          </div>        
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="../bottom.jsp" />