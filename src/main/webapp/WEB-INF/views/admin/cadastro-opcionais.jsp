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
              <h3>Administrar Opcionais<br/>
                <small>Você possui ${qtdOpcionais} opcional(ais) cadastrado(s)</small>
              </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            
              <table  
                 id="tableOpcionais"
                 data-toggle="table"
                 data-url="${context}/admin/opcionais"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=6
                 data-locale = "pt_BR"
                 data-unique-id="idOpcional"
                 data-search="true"
                 data-page-list = "[6,12,25]"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="idOpcional" class="col-md-1">ID</th>
                      <th data-field="nome" class="col-md-5">Nome</th>
                      <th data-field="descricao" class="col-md-3">Descrição</th>
                      <th data-field="ativo" data-formatter="ativoFormatter" class="col-md-1">Ativo</th>
                      <th data-field="idOpcional" data-formatter="editarFormatter" class="col-md-1">Editar</th>
                      <th data-field="idOpcional" data-formatter="removerFormatter" class="col-md-1">Inativar</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">          
                <a class="btn btn-primary" href="${context}/admin/opcionais/new">Adicionar Novo Opcional</a>
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




<div class="modal fade" id="myDialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Inativar Opcional</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="inativaOpcionalForm" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idOpcionalDialog" name="idOpcional" value="0">
          
          <div class="row">
          
            <div class="col-lg-12 col-md-12">
              <h4><span id="nomeOpcional"></span></h4>
            </div>
          
            <div class="spacer-vertical10"></div>

            <div class="col-lg-12 col-md-12">
              <p>Esse Opcional não irá mais ser mostrado aos clientes e também não será possível realizar upload de mídias para ele.</p>
              <p>Deseja inativar?</p>
            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnNaoDialog" data-dismiss="modal">Não</button>
        <button type="button" class="btn btn-primary" id="btnConfirmarInativar">Sim</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->





<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-pt-BR.min.js" charset="UTF-8"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet">



<script type="text/javascript">

    var pagina = 0, limit = 6;

    function queryParams(params) {

        params.pageNumber = $('#tableOpcionais').bootstrapTable('getOptions').pageNumber;
        
        return params;
    };

    function ativoFormatter(value, row) {
        var icon = value == true ? 'fa-check' : 'fa-circle-thin';
        return '<i class="fa '+ icon + '"></i>';
    }

    function editarFormatter(value, row) {
        return '<a class="btn btn-link" href="${context}/admin/opcionais/'+ row.idOpcional +'/view"> <i class="fa fa-lg fa-pencil-square-o"></i></a>';
    }
    
    function removerFormatter(value, row) {
        return '<a class="btn btn-link inativar-opcional-class" id="btnRemoverGenero" idOpcional="'+ row.idOpcional +'" href="#"> <i class="fa fa-lg fa-thumbs-o-down"></i></a>';
    }

    
    var openDialog = function( element )
    {
        var idOpcional = element.attr("idOpcional");

        var row = $('#tableOpcionais').bootstrapTable('getRowByUniqueId', idOpcional);
        
        $('#idOpcionalDialog').val( idOpcional );

        $('#nomeOpcional').html( row.nome );
        
        $('#myDialog').modal('show');
    }

    
    var deletar = function()
    {
        var idOpcional = $("#idOpcionalDialog").val();
        
        if ( idOpcional == null || idOpcional == 0 )
            preencheAlertGeral( "alertArea", "Opcional não encontrado" );

        var url = buildUrl( "/admin/opcionais/{idOpcional}", { idOpcional : idOpcional } );
        
        $.ajax({
            type: 'DELETE',
            contentType: 'application/json',
            url: url,
            dataType: 'json'
        }).done( function(json){ 

            if (json.ok == 1){
                preencheAlertGeral( "alertArea", "Registro removido com sucesso", "success" );
                $("#tableOpcionais").bootstrapTable('refresh');
                $('#myDialog').modal('toggle');
            }
            else{
                $('#myDialog').modal('toggle');
                preencheErros( json.errors );
            }
        });
    } 
    
    

    $(function(){
        
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        $("#tableOpcionais").on( 'load-success.bs.table', function( e, data ) {

            $('.inativar-opcional-class').off( 'click' );

            $('.inativar-opcional-class').click( function(){
                openDialog($(this));
            });
        });

        $('#tableOpcionais').on('page-change.bs.table', function( e, number, size ){

            $('.inativar-opcional-class').off( 'click' );

            $('.inativar-opcional-class').click( function(){
                openDialog($(this));
            });
        });
        
        $("#btnConfirmarInativar").click( function(){
            deletar();
        });
    });

</script>

<style type="text/css">

#table-generos tr{
  cursor: pointer;
}

</style>

<jsp:include page="/WEB-INF/views/bottom.jsp" />