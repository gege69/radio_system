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
              <h3><i class="${func.sizeSmall} ${func.classesIcone}">${func.icone}</i> Chamada de Funcionários</h3>
              <h4><small>Gerencie os nomes e chamadas para este ambiente</small></h4>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
              <c:if test="${not empty error}">      
                <div class="alert alert-danger" role="alert" id="alertalertArea" >
                  <a href="#" class="close" data-dismiss="alert">&times;</a>
                  <div id="errogeral">${error}</div>
                </div>
              </c:if>
              
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
            <div class="col-lg-12 col-md-12">
              <div class="panel panel-default">
              
                <div class="panel-body">
                  <div class="row">
                    <div class="col-lg-6 col-md-6">
                        Nomes de Funcionários:
                      <table  
                         id="table"
                         data-toggle="table"
                         data-url="${context}/ambientes/${idAmbiente}/midias-por-categoria?codigo=chamada_func_nome"
                         data-height="400"
                         data-side-pagination="server"
                         data-pagination="true"
                         data-page-size=7
                         data-page-list="[7]"
                         data-locale = "pt_BR"
                         data-query-params="queryParams" >
                        <thead>
                          <tr>
                              <th data-field="state" data-checkbox="true"></th>
                              <th data-field="nome">Arquivo</th>
                              <th data-field="descricao">Nome</th>
                          </tr>
                        </thead>
                      </table>
                      
                      <div class="spacer-vertical20"></div>
                      
                      Adicionar Nome
                      <form action="${context}/ambientes/${idAmbiente}/view-chamada-funcionarios" 
                            method="POST" 
                            id="ambiente-upload-nome" 
                            class="form" 
                            enctype="multipart/form-data">
                      
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" id="codigo" name="codigo" value="chamada_func_nome">
                        <div class="col-lg-12 col-md-12">
                          <div class="form-group">
                            <label for="file">Arquivo:</label>
                            <input type="file" class="form-control" id="arquivo" name="file" placeholder="algum arquivo">
                          </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                          <div class="form-group">
                            <label for="file">Nome do Funcionário:</label>
                            <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Preencha o nome do Funcionário">
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                              <a class="btn btn-primary" href="#" id="btnUploadNome">Upload novo Nome</a>
                            </div>
                          </div>
                        </div>                        
                      </form>

                    </div>                        
                    
                    <div class="col-lg-6 col-md-6">
                      Frases / Chamadas :
                      <table  
                         id="table"
                         data-toggle="table"
                         data-url="${context}/ambientes/${idAmbiente}/midias-por-categoria?codigo=chamada_func_frase"
                         data-height="400"
                         data-side-pagination="server"
                         data-pagination="true"
                         data-page-size=7
                         data-page-list="[7]"
                         data-locale = "pt_BR"
                         data-query-params="queryParams" >
                        <thead>
                          <tr>
                              <th data-field="state" data-checkbox="true"></th>
                              <th data-field="nome">Arquivo</th>
                              <th data-field="descricao">Descrição</th>
                          </tr>
                        </thead>
                      </table>
                      
                      <div class="spacer-vertical20"></div>
                     
                      Adicionar Frase
                      <form action="${context}/ambientes/${idAmbiente}/view-chamada-funcionarios" 
                            method="POST" 
                            id="ambiente-upload-frase" 
                            class="form" 
                            enctype="multipart/form-data">
                      
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" id="codigo" name="codigo" value="chamada_func_frase">
                        <div class="col-lg-12 col-md-12">
                          <div class="form-group">
                            <label for="file">Arquivo:</label>
                            <input type="file" class="form-control" id="arquivo" name="file" placeholder="algum arquivo">
                          </div>
                        </div>
                        <div class="col-lg-12 col-md-12">
                          <div class="form-group">
                            <label for="file">Descrição:</label>
                            <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Escreva uma pequena descrição da frase">
                          </div>
                        </div>
                        
                        <div class="row">
                          <div class="col-lg-12 col-md-12">
                            <div class="form-group">
                              <a class="btn btn-primary" href="#" id="btnUploadFrase">Upload nova Frase</a>
                            </div>
                          </div>
                        </div>
                      </form>
                      
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/view" >
                <i class="fa fa-arrow-left"></i>
                Voltar para ${nome}
              </a>
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


<script src="${context}/js/required/jsrender.min.js"></script>
<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>

<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">


<script id="viewTmpl" type="text/x-jsrender">
<label class="checkbox-inline">
  <input type="checkbox" id="inlineCheck{{:idCategoria}}" name="categorias[]" value="{{:idCategoria}}"> {{:nome}}
</label>
</script>  



<script type="text/javascript">

    var $table = $('#table');
    
    function catFormatter(value, row) {
        
        var icon = value === "true" ? 'fa-check' : 'fa-circle-thin';

        return '<i class="fa '+ icon + '"></i>';
    }
    
    function queryParams(params) {

        params.pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
        
        return params;
    }
    
    
    var listaCategorias = function( doJump ){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/categorias?simpleUpload=true',
            dataType: 'json'
        }).done( function(json){
            makeListTmpl(json);
            
            var lista = json.rows;
            
            var id_categoria_tela = $('#idCategoria').val();

            $.each( lista, function( idx, obj ){

                if ( obj.idCategoria == id_categoria_tela )
                    $('#inlineCheck'+obj.idCategoria).prop('checked', true);
            });
            
        } );
    }
    
    
    var makeListTmpl = function(json){
        
        var tmpl = $.templates('#viewTmpl');
        
        $('#checkBoxContainer').empty();
        
        var content = tmpl.render(json.rows);
        
        $('#checkBoxContainer').append(content);
    };
    

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        var idAmbiente = $('#idAmbiente').val();
        
        $('#btnUploadNome').on('click', function(){
            $("#ambiente-upload-nome").submit();
        });
        
        $('#btnUploadFrase').on('click', function(){
            $("#ambiente-upload-frase").submit();
        });
        
        listaCategorias();
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />