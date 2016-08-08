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
              <h3>Administrar Gêneros Musicais<br/>
                <small>Você possui ${qtdGeneros} gênero(os) cadastrado(s)</small>
              </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
              <c:if test="${not empty success}">      
                <div class="alert alert-success" role="alert" id="alertalertArea" >
                  <a href="#" class="close" data-dismiss="alert">&times;</a>
                  <div id="errogeral">${success}</div>
                </div>
              </c:if>
            </div>
          </div>

          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            
              <table  
                 id="table-generos"
                 data-toggle="table"
                 data-url="${context}/admin/generos"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=6
                 data-locale = "pt_BR"
                 data-unique-id="idGenero"
                 data-page-list = "[6,12,25]"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="idGenero" class="col-md-1">ID</th>
                      <th data-field="nome" class="col-md-5">Nome</th>
                      <th data-field="descricao" class="col-md-4">Descrição</th>
                      <th data-field="editar" data-formatter="editarFormatter" class="col-md-1">Editar</th>
                      <th data-field="remover" data-formatter="removerFormatter" class="col-md-1">Remover</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">          
                <a class="btn btn-primary" href="${context}/admin/generos/new">Adicionar Novo Gênero</a>
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
        <h4 class="modal-title" id="titulo-modal">Remover Gênero</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="removeGeneroForm" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idGeneroDialog" name="idGenero" value="0">
          
          <div class="row">
          
            <div class="col-lg-12 col-md-12">
              <h4><span id="nomeGenero"></span></h4>
            </div>
          
            <div class="spacer-vertical10"></div>

            <div class="col-lg-12 col-md-12">
              Esse gênero musical não irá mais ser mostrado aos clientes e também não será possível realizar upload de músicas para ele.
              <br/>
              <br/>
              Não é possível desfazer essa operação. 
              <br/>
              <br/>
              Deseja remover?
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





<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-pt-BR.min.js" charset="UTF-8"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet">



<script type="text/javascript">

    var pagina = 0, limit = 6;

    function queryParams(params) {

        params.pageNumber = $('#table-generos').bootstrapTable('getOptions').pageNumber;
        
        return params;
    };

    function editarFormatter(value, row) {
        return '<a class="btn btn-link" href="${context}/admin/generos/'+ row.idGenero +'/view"> <i class="fa fa-lg fa-pencil-square-o"></i></a>';
    }
    
    function removerFormatter(value, row) {
        return '<a class="btn btn-link remover-genero-class" id="btnRemoverGenero" idGenero="'+ row.idGenero +'" href="#"> <i class="fa fa-lg fa-trash-o"></i></a>';
    }

    
    var openDialog = function( element )
    {
        var idGenero = element.attr("idGenero");

        var row = $('#table-generos').bootstrapTable('getRowByUniqueId', idGenero);
        
        $('#idGeneroDialog').val( idGenero );

        $('#nomeGenero').html( row.nome );
        
        $('#myDialog').modal('show');
    }

    
    var deletar = function()
    {
        var idGenero = $("#idGeneroDialog").val();
        
        if ( idGenero == null || idGenero == 0 )
            preencheAlertGeral( "alertArea", "Gênero não encontrado" );

        var url = buildUrl( "/admin/generos/{idGenero}", { idGenero : idGenero } );
        
        $.ajax({
            type: 'DELETE',
            contentType: 'application/json',
            url: url,
            dataType: 'json'
        }).done( function(json){ 

            if (json.ok == 1){
                preencheAlertGeral( "alertArea", "Registro removido com sucesso", "success" );
                $("#table-generos").bootstrapTable('refresh');
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
        
        $("#table-generos").on( 'load-success.bs.table', function( e, data ) {

            $('.remover-genero-class').off( 'click' );

            $('.remover-genero-class').click( function(){
                openDialog($(this));
            });
        });

        $('#table-generos').on('page-change.bs.table', function( e, number, size ){

            $('.remover-genero-class').off( 'click' );

            $('.remover-genero-class').click( function(){
                openDialog($(this));
            });
        });
        
        $("#btnConfirmarDelete").click( function(){
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