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
            <small>Escolha qual ambiente servirá como base para esse</small>
          </h3>
          
          <div class="spacer-vertical40"></div>

          <div class="row">
            <div class="col-sm-10 col-md-10">
              <div class="panel panel-default">
                <div class="panel-heading">
                  Utilize este recurso se você deseja que a rádio atual tenha exatamente a mesma programação de outra rádio. <br/>
                  Selecione abaixo em qual ambiente deve servir como modelo.
                </div>
                <div class="panel-body">
                
                  <form class="form-horizontal" action="${context}/fazer">
                  
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
          
          <div class="spacer-vertical10"></div>

          <div class="row">
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
              <div class="">
                <a class="btn btn-default" href="${context}/ambientes/searches" >
                  <i class="fa fa-arrow-left"></i>
                  Voltar para Administrar Ambientes</a>
              </div>            
            </div>
          </div>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="pull-right">
        <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="/WEB-INF/views/bottom.jsp" />