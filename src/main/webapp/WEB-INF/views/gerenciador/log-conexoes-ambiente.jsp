<jsp:include page="/WEB-INF/views/main.jsp" />  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
  
    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>${param['ambiente']}<br/>
            <small></small>
          </h3>
          
          <div class="spacer-vertical40"></div>

          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="panel panel-default">
                <div class="panel-body">
                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead>
                        <th>
                          Conectou em
                        </th>
                        <th>
                          Desconectou em
                        </th>
                        <th>
                          IP utilizado
                        </th>
                      </thead>
                      <tbody>
                        <tr>
                          <td>
                            22/03/2015 22:02:53
                          </td>
                          <td>
                            Conectado
                          </td>
                          <td>
                            999.999.999.999
                          </td>
                        </tr>
                        <tr>
                          <td>
                            21/03/2015 14:02:53
                          </td>
                          <td>
                            21/03/2015 19:00:00
                          </td>
                          <td>
                            999.999.999.999
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
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