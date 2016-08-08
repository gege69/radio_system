<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">

    <div class="row">

      <div class="panel panel-default">
        <div class="panel-body">
        
          <div class="row">
            <div class="col-lg-6 col-md-6">
              <h3>Cadastro de Ambientes</h3>
            </div>
            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div> 
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              <table  
                 id="tableAmbientesAdmin"
                 data-toggle="table"
                 data-url="${context}/admin/clientes/${idCliente}/ambientes"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=5
                 data-search="true"
                 data-locale = "pt_BR"
                 data-unique-id="idAmbiente"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="idAmbiente">Id</th>
                      <th data-field="nome">Nome</th>
                      <th data-field="dataCriacao">Data Cadastro</th>
                      <th data-field="valor">Valor</th>
                      <th data-field="status">Status</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">          
              <div class="">
                <a class="btn btn-default" href="javascript:history.back(1)" >
                  <i class="fa fa-arrow-left"></i>
                  Voltar para Edição do Cliente</a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="pull-right-not-xs">
                <a class="btn btn-default" href="${context}/admin/painel">Painel de Admin</a>    
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

        params.pageNumber = $('#tableAmbientesAdmin').bootstrapTable('getOptions').pageNumber;
        return params;
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