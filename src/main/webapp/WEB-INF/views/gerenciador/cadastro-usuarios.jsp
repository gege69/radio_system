<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Usuários do Painel<br/>
            <small>Pessoas autorizadas a acessar seu painel de controle</small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              <table  
                 id="table-usu"
                 data-toggle="table"
                 data-url="${context}/usuarios"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=7
                 data-locale = "pt_BR"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="nome" class="col-lg-6 col-md-6 col-sm-6 col-xs-6" data-formatter="nomeFormatter">Usuário</th>
                      <th data-field="perfis" class="col-lg-6 col-md-6 col-sm-6 col-xs-6" data-formatter="perfisFormatter">Perfis</th>
                      <th data-field="editar" class="col-lg-2 col-md-2 col-sm-2 col-xs-6" data-formatter="editarFormatter" data-halign="center" data-align="center">Editar</th>
                      <th data-field="remover" class="col-lg-2 col-md-2 col-sm-2 col-xs-6" data-formatter="removeFormatter" data-halign="center" data-align="center">Remover</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical20"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-primary" href="${context}/usuarios/view">Adicionar Novo Usuário</a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/perfis/view">
                  <i class="fa fa-legal"></i>
                  Administrar Perfis</a>
              </div>
            </div>
          </div>

          <div class="spacer-vertical20"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
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

        params.pageNumber = $('#table-usu').bootstrapTable('getOptions').pageNumber;
        
        return params;
    }

    function nomeFormatter(value, row) {
        return '<a class="btn btn-link" href="${context}/usuarios/'+ row.idUsuario +'/view">' + value + '</a>';
    }
    
    function editarFormatter(value, row) {
        return '<a class="btn btn-link" href="${context}/usuarios/'+ row.idUsuario +'/view"> <i class="fa fa-lg fa-pencil-square-o"></i></a>';
    }

    function removeFormatter(value, row) {
        //melhorar ... fazer a pergunta de remoção
        
        return "";
//         return '<a class="btn btn-link" href="${context}/usuario/'+ row.idUsuario +'"> <i class="fa fa-lg fa-trash-o"></i></a>';
    }
    
    function perfisFormatter(value, row) {

        var result = '';
        
        if ( value )
        {
            for (var i=0; i < value.length; i++){
                
                if ( result.length > 0 )
                    result += ', ';
                
                result += value[i].nome;
            }
        }
        
        return result;
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