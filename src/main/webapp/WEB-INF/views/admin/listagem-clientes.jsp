<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Administrar Clientes<br/>
            <small>Voc� possui ${qtdClientes} cliente(es) cadastrado(s)</small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              <table  
                 id="tableclientes"
                 data-toggle="table"
                 data-url="${context}/admin/clientes"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=6
                 data-locale = "pt_BR"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="razaosocial">Raz�o Social</th>
                      <th data-field="nomefantasia">Nome</th>
                      <th data-field="dominio">Dom�nio</th>
                      <th data-field="cnpj">CNPJ</th>
                      <th data-field="email">Email</th>
                      <th data-field="telefone">Telefone</th>
                      <th data-field="qtd_tit">Qtd. T�tulos em Aberto</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                <a class="btn btn-primary" href="${context}/admin/cliente/new">Adicionar Novo Cliente</a>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/admin/painel">Painel de Admin</a>    
              </div>          
            </div>
          </div>            
        </div>
      </div>
    </div>
    
      
  </div> <!-- /container -->

<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>
<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">



<script type="text/javascript">

    var pagina = 0, limit = 6;

    function queryParams(params) {

        params.pageNumber = $('#table-admin').bootstrapTable('getOptions').pageNumber;
        
        return params;
    };
    
    var editarClienteSelecionado = function( e, row, el )
    {
        if ( row == null ) 
            return;
        
        if ( row.idCliente == null || row.idCliente == 0 )
        {
            preencheAlertGeral("alertArea", "Cliente n�o foi selecionado corretamente.", "danger");
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