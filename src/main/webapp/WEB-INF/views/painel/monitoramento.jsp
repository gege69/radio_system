<jsp:include page="/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Monitoramento<br/>
            <small>Monitoramento de ambientes</small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-md-12 text-right">
              Nenhnuma nova Mensagem | Rádios online 1 de 1
            </div>
          </div>
          
          <div class="spacer-vertical10"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="panel panel-default">
                <div class="panel-body">
                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead>
                        <th>
                          Ambiente
                        </th>
                        
                        <th>
                          Status
                        </th>
                        
                        <th>
                          Opções
                        </th>
                        <th></th>
                        <th></th>
                      </thead>
                      <tbody>
                        <tr>
                          <td width="40%">
                            <a class="btn btn-link" href="${context}/views/painel/log-conexoes-ambiente.jsp?ambiente=Mercado+Teste">
                              Mercado Teste
                            </a>
                          </td>
                          <td width="40%">
                            Status
                          </td>
                          <td width="6.6%">
                            <a class="btn btn-link" href="#">
                              <i class="fa fa-lg fa-tasks"></i>
                            </a>
                          </td>
                          <td width="6.6%">
                            <a class="btn btn-link" href="#">
                              <i class="fa fa-lg fa-check-square-o"></i>
                            </a>
                          </td>
                          <td width="6.6%">
                            <a class="btn btn-link" href="#">
                              <i class="fa fa-lg fa-check-circle"></i>
                            </a>
                          </td>
                        </tr>
                        <tr>
                          <td width="40%">
                            <a class="btn btn-link" href="${context}/views/painel/log-conexoes-ambiente.jsp?ambiente=Outro+mercado">
                              Outro mercado
                            </a>
                          </td>
                          <td width="40%">
                            Status
                          </td>
                          <td>
                            <a class="btn btn-link" href="#">
                              <i class="fa fa-lg fa-tasks"></i>
                            </a>
                          </td>
                          <td>
                            <a class="btn btn-link" href="#">
                              <i class="fa fa-lg fa-check-square-o"></i>
                            </a>
                          </td>
                          <td>
                            <a class="btn btn-link" href="#">
                              <i class="fa fa-lg fa-check-circle"></i>
                            </a>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                    
                  </div> 
                </div>
              </div>
            </div>
          </div>
          

          <div class="row">
            <div class="col-md-12 text-right">
              Rádio online: 1 de 1
            </div>
          </div>  

          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              
              <div class="spacer-vertical40"></div>
              
              <div class="">
                <a class="btn btn-default" href="${context}/views/painel/monitoramento.jsp">Reiniciar Rádios</a>
              </div>            
            </div>
          </div>
          
          <div class="spacer-vertical20"></div>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-offset-10 col-sm-offset-9 col-xs-offset-7">
        <a class="btn btn-default" href="${context}/views/painel/principal.jsp">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->

<jsp:include page="/bottom.jsp" />