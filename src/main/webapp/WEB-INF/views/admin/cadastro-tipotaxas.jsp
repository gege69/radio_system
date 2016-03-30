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
          <h3>Administrar Tipos de Taxas<br/>
            <small>Você possui ${qtdTipotaxas} tipo(os) de taxa cadastrado(s)</small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              <table  
                 id="tableTipoTaxas"
                 data-url="${context}/admin/tipotaxas"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=5
                 data-locale = "pt_BR"
                 data-search="true">
                <thead>
                  <tr>
                      <th data-field="idTipotaxa"  class="col-lg-1 col-md-1">ID</th>
                      <th data-field="descricao" class="col-lg-4 col-md-4">Descrição</th>
                      <th data-field="porambiente" data-formatter="porambienteFormatter" class="col-lg-2 col-md-2">Por Ambiente?</th>
                      <th data-field="operacao" class="col-lg-2 col-md-2">Operação</th>
                      <th data-field="porambiente" data-formatter="removerTipoTaxaFormatter" class="col-lg-2 col-md-2">Remover</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                <a class="btn btn-primary" href="${context}/admin/tipotaxas/new">Adicionar Novo Tipo Taxa</a>
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




<div class="modal fade" id="myDialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Remover Tipo de Taxa</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="removeTipoTaxaForm" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idTipotaxaDialog" name="idTipotaxa" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              Deseja realmente remover esse Tipo de Taxa?
            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnNaoDialog" data-dismiss="modal">Não</button>
        <button type="button" class="btn btn-primary" id="btnConfirmarDelete">Sim</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>
<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">



<script type="text/javascript">

    var pagina = 0, limit = 6;

    function queryParams(params) {

        params.pageNumber = $('#tableTipoTaxas').bootstrapTable('getOptions').pageNumber;        

        return params;
    };

    function porambienteFormatter(value, row){
        var icon = value == true ? 'fa-check' : 'fa-circle-thin'; 
        return '<i class="fa '+ icon + '"></i>';
    }
    
    function removerTipoTaxaFormatter(value, row) {
        return '<a class="btn btn-link remover-tipotaxa-class" id="btnRemoverTipoTaxa" idTipotaxa="'+ row.idTipotaxa +'" href="#"> <i class="fa fa-lg fa-trash-o"></i></a>';
    }


    var openDialog = function( element )
    {
        var idTipotaxa = element.attr("idTipotaxa");
        
        $('#idTipotaxaDialog').val( idTipotaxa );
        
        $('#myDialog').modal('show');
    }


    var editarTipoTaxa = function( e, row, el )
    {
        if ( row == null ) 
            return;
        
        if ( row.idTipotaxa == null || row.idTipotaxa == 0 )
        {
            preencheAlertGeral("alertArea", "Tipo de Taxa não foi selecionado corretamente.", "danger");
            return false;
        }
        else
        {
            var url = buildUrl( "/admin/tipotaxas/{idTipotaxa}/view", { 
                idTipotaxa: row.idTipotaxa 
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
        
        $('#tableTipoTaxas').bootstrapTable({
            queryParams : queryParams
        });

        $('#tableTipoTaxas').on('click-row.bs.table', function( e, row, el ){
            editarTipoTaxa( e, row, el );
        });
        
        $("#tableTipoTaxas").on( 'load-success.bs.table', function( e, data ) {
            $(".remover-tipotaxa-class").click( function(){
                openDialog($(this));
            });
        });
        
        $("#tableTipoTaxas").on( 'page-change.bs.table', function ( e, number, size ){
            $(".remover-tipotaxa-class").click( function(){
                openDialog($(this));
            });
        });
        
    });

</script>

<style type="text/css">

#tableTipoTaxas tr{
  cursor: pointer;
}

</style>

<jsp:include page="/WEB-INF/views/bottom.jsp" />