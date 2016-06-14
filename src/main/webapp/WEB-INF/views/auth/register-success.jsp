<jsp:include page="../main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
  
    <div class="row">
      <div class="col-xs-12 col-md-5 col-sm-6 col-lg-5">

        <div class="panel panel-default">
            <div class="panel-heading">
              <p>Usuário cadastrado com sucesso: ${username}</p>
              <p>Foi enviado um email para validação do seu cadastro, para realizar o acesso, por favor, verifique seu email.</p>
            </div>
            <div class="panel-body">
              <a href="${context}/login">Clique aqui para realizar o login</a> 
            </div>
          </div>        
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="../bottom.jsp" />