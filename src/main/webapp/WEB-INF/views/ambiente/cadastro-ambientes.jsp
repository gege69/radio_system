<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Administrar Ambientes<br/>
            <small>Voc� possui ${qtdAmbientes} ambiente(es) cadastrado(s)</small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              <table  
                 id="table-admin"
                 data-toggle="table"
                 data-url="${context}/ambientes"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=5
                 data-search="true"
                 data-locale = "pt_BR"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="idAmbiente" class="col-lg-1 col-md-1 col-sm-1 col-xs-1" data-formatter="nomeFormatter">Id</th>
                      <th data-field="nome" class="col-lg-5 col-md-5 col-sm-5 col-xs-5" data-formatter="nomeFormatter">Nome</th>
                      <th data-field="editar" class="col-lg-2 col-md-2 col-sm-2 col-xs-2" data-formatter="editarFormatter" data-halign="center" data-align="center">Editar</th>
                      <th data-field="espelhar" class="col-lg-2 col-md-2 col-sm-2 col-xs-2" data-formatter="espelharFormatter" data-halign="center" data-align="center">Espelhar</th>
                      <th data-field="remover" class="col-lg-2 col-md-2 col-sm-2 col-xs-2" data-formatter="removeFormatter" data-halign="center" data-align="center">Remover</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
<!--           <div class="row"> -->
<!--             <div class="col-lg-10 col-md-10"> -->
<!--               <div class="checkbox"> -->
<!--                 <label> -->
<!--                   <input type="checkbox" id="check_pacote"> Exibir somente ambientes com pacote de Programetes opcionais -->
<!--                 </label> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                <a class="btn btn-primary" href="${context}/ambientes/new">Adicionar Novo Ambiente</a>
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

<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>
<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">



<script type="text/javascript">

    var pagina = 0, limit = 6;

    function queryParams(params) {

        params.pageNumber = $('#table-admin').bootstrapTable('getOptions').pageNumber;
        
        return params;
    }

    function nomeFormatter(value, row) {
        return '<a class="btn btn-link" href="${context}/ambientes/'+ row.idAmbiente +'/view">' + value + '</a>';
    }
    
    function espelharFormatter(value, row) {
        return '<a class="btn btn-link" href="${context}/ambientes/'+ row.idAmbiente +'/espelhar"> <i class="fa fa-lg fa-files-o"></i></a>';
    }
    
    function editarFormatter(value, row) {
        return '<a class="btn btn-link" href="${context}/ambientes/'+ row.idAmbiente +'/editar"> <i class="fa fa-lg fa-pencil-square-o"></i></a>';
    }

    function removeFormatter(value, row) {
        // pensar melhor nesse.... tem que ser um ajax perguntando certeza e usando o DELETE 
        return '<i class="fa fa-lg fa-trash-o"></i>';
//         return '<a class="btn btn-link" href="#"> <i class="fa fa-lg fa-trash-o"></i></a>';
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