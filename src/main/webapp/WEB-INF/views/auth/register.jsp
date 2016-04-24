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

        <form:form modelAttribute="user" id="ambiente-form" method="POST">
          <div class="form-group">
            <label for="login">Empresa</label>
            <form:input class="form-control" path="nmEmpresa" value="" placeholder="Nome"/>
            <form:errors path="nmEmpresa" element="div" class="alert alert-danger"/>
          </div>
          <div class="form-group">
            <label for="login">CNPJ/CPF</label>
            <form:input class="form-control cpfcnpj" id="cpfcnpj" path="cdCNPJCPF" value="" placeholder="Nome"/>
            <form:errors path="cdCNPJCPF" element="div" class="alert alert-danger"/>
          </div>
          <div class="form-group">
            <label for="estabelecimento">Estabelecimento</label>
            <form:select class="form-control" path="estabelecimento" id="estabelecimento">
                <form:option value="LOJA" label="Loja" selected="selected"/>
                <form:option value="RESTAURANTE" label="Restaurante"/>
                <form:option value="SUPERMERCADO" label="Supermercado"/>
                <form:option value="ACADEMIA" label="Academia"/>
                <form:option value="REVENDA" label="Revenda"/>
                <form:option value="OUTRO" label="Outro"/>
            </form:select>
          </div>
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
            <form:input class="form-control" path="password" value="" id="password" placeholder="Senha" type="password"/>
            <form:errors path="password" element="div" class="alert alert-danger"/>
          </div>
          <div class="form-group">
            <label for="senha">Repetir Senha</label>
            <form:input class="form-control" path="matchingPassword" value="" placeholder="Senha" type="password"/>
            <form:errors path="matchingPassword" element="div" class="alert alert-danger"/>
          </div>
          
          <div class="spacer-vertical20"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <a class="btn btn-default" href="${context}"><i class="fa fa-arrow-left"></i> Voltar ao Início</a>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <button type="submit" class="btn btn-primary">Gravar</button>
              </div>
            </div>
          </div>
          
          <div class="spacer-vertical20"></div>
          
          <form:errors element="div" class="alert alert-danger"/>
        </form:form>
        
      </div>
    </div>
      
  </div> <!-- /container -->



<script type="text/javascript" src="${context}/js/required/jquery.mask.min.js"></script>
<script type="text/javascript" src="${context}/js/required/zxcvbn.js"></script>

<script type="text/javascript">


  


$(function(){
    
    
    $("#cpfcnpj").keydown(function(){
        try {
            $("#cpfcnpj").unmask();
        } catch (e) {}

        var tamanho = $("#cpfcnpj").val().length;

        if(tamanho <= 11){
            $("#cpfcnpj").mask("999.999.999-999");
        } else {
            $("#cpfcnpj").mask("99.999.999/9999-99");
        }                   
    });

    $('#password').keyup( function( event ) {
        keyup_validasenha( event );
    });

});

</script>


<jsp:include page="../bottom.jsp" />