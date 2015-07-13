<jsp:include page="/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
      <div class="col-md-10 col-sm-10">
        <div class="panel panel-default">
          <div class="panel-body">
            <h3>Selecione o Ambiente<br/>
              <small>Acesso ao gerenciamento de ambientes</small>
            </h3>
            
            <div class="spacer-vertical40"></div>
  
            <div class="col-sm-10 col-md-8">
              <select class="form-control" id="ambiente" name="">
                <option value="1">Ambiente 1</option>
                <option value="2">Ambiente 2</option>
              </select>
            </div>
            
            <div class="col-md-10 col-sm-10">  <!-- hack -->
              <div class="spacer-vertical40">&nbsp;</div>
            </div>
            
          </div>
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-offset-7 col-sm-offset-7 col-xs-offset-7">
        <a class="btn btn-default" href="${context}/views/painel/principal.jsp">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="/bottom.jsp" />