<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
 
    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Chamada de Funcionários<br/>
            <small>Clique no respectivo ambiente para acessar as Chamadas de Funcionários</small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-8 col-md-8">
            
              <table  
                 id="table-admin"
                 data-toggle="table"
                 data-url="${context}/ambientes"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=7
                 data-locale = "pt_BR"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="nome" class="col-lg-6 col-md-6 col-sm-6 col-xs-6" data-formatter="nomeFormatter">Nome do Ambiente</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>    
              </div>          
            </div>
          </div>            
        </div>
      </div>
    </div>
    
      
  </div> <!-- /container -->


<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-pt-BR.min.js" charset="UTF-8"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet">

<script type="text/javascript">

    var pagina = 0, limit = 6;

    function queryParams(params) {

        params.pageNumber = $('#table-admin').bootstrapTable('getOptions').pageNumber;
        
        return params;
    }

    function nomeFormatter(value, row) {
        return '<a class="btn btn-link" href="${context}/ambientes/'+ row.idAmbiente +'/view-chamada-funcionarios">' + value + '</a>';
    }
    
    
    $(function(){
        
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />