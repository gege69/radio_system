<jsp:include page="/WEB-INF/views/main.jsp" /> />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Mensagens</h3>
          
          <div class="spacer-vertical40"></div>

          
          
<!--           Talvez depois tirar heading do panel...e usar os cabeçalhos da grid mesmo -->
          <div class="panel panel-default">
            <div class="panel-heading">
              <div class="row">
                <div class="col-md-6 col-sm-6">
                  Ambiente
                </div>
                <div class="col-md-4 col-sm-3">
                  Envio
                </div>
                <div class="col-md-2 col-sm-2">
                  Excluir
                </div>
              </div>
            </div>
            <div class="panel-body">
              <div class="table-responsive" >
                <table class="table table-hover">
                  <tr>
                    <td width="5%">
                      <input type="checkbox" value="true">
                    </td>
                    <td width="45%">
                      Mercado Teste
                    </td>
                    <td width="40%">
                      14/03/2015 - 14:00
                    </td>
                    <td width="10%">
                      <a class="btn btn-link" href="#">
                        <i class="fa fa-lg fa-trash-o"></i>
                      </a>
                    </td>
                  </tr>
                </table>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-offset-10 col-md-offset-9 col-sm-offset-8">
              <a class="btn btn-link" href="${context}/views/painel/mensagens.jsp">Excluir Selecionados</a>
            </div>
          
          
            <div class="spacer-vertical40"></div>
            
            <div class="col-lg-offset-9 col-md-offset-8 col-sm-offset-7">
              <a class="btn btn-default" href="${context}/views/painel/mensagens.jsp">Enviar Mensagem</a>
              <a class="btn btn-default" href="${context}/views/painel/mensagens.jsp">Configurações</a>
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