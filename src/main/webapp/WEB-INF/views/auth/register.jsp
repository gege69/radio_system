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

        <form:form modelAttribute="user" method="POST">
          <div class="form-group">
            <label for="login">Nome</label>
            <form:input class="form-control" path="nmUsuario" value="" placeholder="Nome"/>
            <form:errors path="nmUsuario" element="div" class="alert alert-danger"/>
          </div>
          <div class="form-group">
            <label for="login">Email</label>
            <form:input class="form-control" path="cdEmail" value="" placeholder="Email"/>
            <form:errors path="cdEmail" element="div" class="alert alert-danger"/>
          </div>
          <div class="form-group">
            <label for="login">Login</label>
            <form:input class="form-control" path="cdLogin" value="" placeholder="Login"/>
            <form:errors path="cdLogin" element="div" class="alert alert-danger"/>
          </div>
          <div class="form-group">
            <label for="senha">Senha</label>
            <form:input class="form-control" path="password" value="" placeholder="Senha" type="password"/>
            <form:errors path="password" element="div" class="alert alert-danger"/>
          </div>
          <div class="form-group">
            <label for="senha">Repetir Senha</label>
            <form:input class="form-control" path="matchingPassword" value="" placeholder="Senha" type="password"/>
            <form:errors path="matchingPassword" element="div" class="alert alert-danger"/>
          </div>
          <button type="submit" class="btn btn-default">Gravar</button>
          
          <div class="spacer-vertical20"></div>
          
          <form:errors element="div" class="alert alert-danger"/>
        </form:form>
        
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="../bottom.jsp" />