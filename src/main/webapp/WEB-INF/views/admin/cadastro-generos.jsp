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
          <h3>Administrar Gêneros Musicais<br/>
            <small>Você possui ${qtdGeneros} gênero(os) cadastrado(s)</small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              <table  
                 id="table-generos"
                 data-toggle="table"
                 data-url="${context}/admin/generos"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=6
                 data-locale = "pt_BR"
                 data-page-list = "[6,12,25]"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="idGenero">ID</th>
                      <th data-field="nome">Nome</th>
                      <th data-field="descricao">Descrição</th>
                      <th data-field="idGenero" data-formatter="removerFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-2">Remover</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                <a class="btn btn-primary" href="${context}/admin/generos/new">Adicionar Novo Gênero</a>
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

        params.pageNumber = $('#table-generos').bootstrapTable('getOptions').pageNumber;
        
        return params;
    };
    
    function removerFormatter(value, row) {
        return '<a class="btn btn-link remover-genero-class" id="btnRemoverGenero" idGenero="'+ row.idGenero +'" href="#"> <i class="fa fa-lg fa-trash-o"></i></a>';
    }

    var editarGeneroSelecionado = function( e, row, el )
    {
        if ( row == null ) 
            return;
        
        if ( row.idGenero == null || row.idGenero == 0 )
        {
            preencheAlertGeral("alertArea", "Gênero não foi selecionado corretamente.", "danger");
            return false;
        }
        else
        {
            var url = buildUrl( "/admin/generos/{idGenero}/view", { 
                idGenero: row.idGenero 
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
        
        $('#table-generos').on('click-row.bs.table', function( e, row, el ){
            editarGeneroSelecionado( e, row, el );
        });
        
        $('#table-generos').on('page-change.bs.table', function( e, number, size ){

            $('.remover-genero-class').off( 'click' );

            $('.remover-genero-class').click( function(){
                
                var idGenero = $(this).attr('idGenero');
                
                console.log(idGenero);
            });
        });
        
        
    });

</script>

<style type="text/css">

#table-generos tr{
  cursor: pointer;
}

</style>

<jsp:include page="/WEB-INF/views/bottom.jsp" />