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
              <h3>Listagem de Cadastros<br/>
                <small>Voc� possui ${qtdClientes} cliente(es) cadastrado(s)</small>
              </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>

          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              <table  
                 id="tableClienteRelatorio"
                 data-toggle="table"
                 data-url="${context}/admin/clientes/searches/relatorio"
                 data-height="600"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=8
                 data-locale = "pt_BR"
                 data-search="true"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="usuario.nome">Usu�rio</th>
                      <th data-field="cliente.cnpj">CNPJ</th>
                      <th data-field="cliente.razaosocial">Raz�o Social</th>
                      <th data-field="totalAmbientes">Ambientes</th>
                      <th data-field="cliente.diaVencimento">Dia Vencimento</th>
                      <th data-field="valorTotalLiquido" data-formatter="valorFormatter">Valor Total</th>
                      <th data-field="valorTotalDescontos" data-formatter="valorFormatter">Descontos</th>
                      <th data-field="valorTotalAPagar" data-formatter="valorFormatter">Total Pagar</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">          
                <a class="btn btn-primary" href="${context}/admin/clientes/new">Adicionar Novo Cliente</a>
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

        params.pageNumber = $('#tableClienteRelatorio').bootstrapTable('getOptions').pageNumber;
        
        return params;
    };
    
    function valorFormatter(value, row) {
        
        return "R$ "+ value;
    }

    var editarClienteSelecionado = function( e, row, el )
    {
        if ( row == null || row.cliente == null ) 
            return;
        
        if ( row.cliente.idCliente == null || row.cliente.idCliente == 0 )
        {
            preencheAlertGeral("alertArea", "Cliente n�o foi selecionado corretamente.", "danger");
            return false;
        }
        else
        {
            var url = buildUrl( "/admin/clientes/{idCliente}/view", { 
                idCliente: row.cliente.idCliente 
            });
            
            window.location = url;
        }
    };
    

    $(function(){
        
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        $('#tableClienteRelatorio').on('click-row.bs.table', function( e, row, el ){
            editarClienteSelecionado( e, row, el );
        });
    });

</script>

<style type="text/css">

#tableClienteRelatorio tr{
  cursor: pointer;
}

</style>

<jsp:include page="/WEB-INF/views/bottom.jsp" />