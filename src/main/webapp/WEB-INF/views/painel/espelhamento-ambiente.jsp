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
          <h3>Espelhamento de Rádio<br/>
            <small>Configure em qual ambiente irá espelhar-se</small>
          </h3>
          
          <div class="spacer-vertical40"></div>

          <div class="col-sm-10 col-md-10">
            <div class="panel panel-default">
              <div class="panel-heading">
                Utilize este recurso se você deseja que a rádio atual tenha exatamente a mesma programação de outra rádio. <br/>
                Selecione abaixo em qual rádio ela deve se espelhar.
              </div>
              <div class="panel-body">
              
                <form class="form-horizontal" action="${context}/views/painel/editar-ambiente.jsp">
                
                  <div class="form-group">
                    <label for="login" class="control-label col-sm-2 col-md-3">Espelhar em:</label>
                    <div class="col-sm-10 col-md-8">
                      <select class="form-control" id="radios" name="">
                      </select>
                    </div>
                    
                  </div>
                  
                  <div class="form-group">
                    <label for="login" class="control-label col-sm-2 col-md-3">&nbsp;</label>  <!-- hack -->
                    <div class="col-sm-10 col-md-8">
                      <button type="submit" class="btn btn-default">Espelhar</button>
                    </div>
                  </div>
                  
                </form>
              </div>
            
            </div>
          </div>
          
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-offset-10 col-sm-offset-9 col-xs-offset-7">
        <a class="btn btn-default" href="${context}/views/painel/principal.jsp">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="/WEB-INF/views/bottom.jsp" />