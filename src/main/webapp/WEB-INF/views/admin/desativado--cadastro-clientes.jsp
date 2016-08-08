<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Administrar Clientes<br/>
            <small>Você possui ${qtdClientes} cliente(es) cadastrado(s)</small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              <table  
                 id="tableclientes"
                 data-toggle="table"
                 data-url="${context}/clientes"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=6
                 data-locale = "pt_BR"
                 data-search="true"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="razaosocial">Razão Social</th>
                      <th data-field="nomefantasia">Nome</th>
                      <th data-field="dominio">Domínio</th>
                      <th data-field="cnpj">CNPJ</th>
                      <th data-field="email">Email</th>
                      <th data-field="telefone" data-formatter="telefoneFormatter">Telefone</th>
                      <th data-field="qtd_tit">Qtd. Títulos em Aberto</th>
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

        params.pageNumber = $('#table-admin').bootstrapTable('getOptions').pageNumber;
        
        return params;
    };
    
    function telefoneFormatter(value, row) {
        
        if ( row.telefones && row.telefones.length > 0 )
            return row.telefones[0].ddd + " " + row.telefones[0].numero;
        else
          return "";
    }

    var editarClienteSelecionado = function( e, row, el )
    {
        if ( row == null ) 
            return;
        
        if ( row.idCliente == null || row.idCliente == 0 )
        {
            preencheAlertGeral("alertArea", "Cliente não foi selecionado corretamente.", "danger");
            return false;
        }
        else
        {
            var url = buildUrl( "/admin/clientes/{idCliente}/view", { 
                idCliente: row.idCliente 
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
        
        $('#tableclientes').on('click-row.bs.table', function( e, row, el ){
            editarClienteSelecionado( e, row, el );
        });
    });

</script>

<style type="text/css">

#tableclientes tr{
  cursor: pointer;
}

</style>

<jsp:include page="/WEB-INF/views/bottom.jsp" />