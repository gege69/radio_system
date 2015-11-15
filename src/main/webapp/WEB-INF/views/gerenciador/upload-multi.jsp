<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="row" id="alertArea">

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
      
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Upload multi-Ambiente <br/>
            <small>Marque a categoria da mídia que deseja enviar e os ambientes que devem recebê-la</small>
          </h3>
          
          
          <form action="${context}/view-upload-multi" 
            method="POST" 
            id="ambiente-upload-multi" 
            class="form" 
            enctype="multipart/form-data">
          
            <div class="row">
            
              <div class="col-lg-6 col-md-6">
                <div class="panel panel-default">
                  <div class="panel-body">
                    <div class="row">
                      
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" id="idCategoria" name="idCategoria" value="${idCategoria}">
                        
                        
                        <div class="col-lg-12 col-md-12 col-sm-12">
                          <div class="form-group">
                            <label for="file">Arquivo:</label>
                            <input type="file" class="form-control" id="arquivo" name="file" placeholder="algum arquivo">
                          </div>
                        </div>
      
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                          <div class="form-group">
                            <div class="form-inline">
                              <div class="form-group" id="checkBoxContainer">
                              </div>
                            </div>
                          </div>
                        </div>
  
                    </div> 
                  </div>
                </div>
              </div>
              
              <div class="col-lg-6 col-md-6">
                <table  
                 id="table-ambientes"
                 data-toggle="table"
                 data-select-item-name="selectItem"
                 data-url="${context}/ambientes"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=7
                 data-locale = "pt_BR"
                 data-query-params="queryParamsAmbiente" >
                  <thead>
                    <tr>
                        <th data-field="state" data-checkbox="true"></th>
                        <th data-field="nome">Ambientes</th>
                    </tr>
                  </thead>
                </table>
              </div>
              
            </div>
          
                                
          </form>

          <div class="spacer-vertical40"></div>
        
          <div class="row row-centered">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
              <div class="">
                <a class="btn btn-primary text-center" href="#" id="btnUploadMidia">
                  <i class="fa fa-3x fa-cloud-upload pull-left"></i>
                  Upload Mídia para
                  ambientes<br/> selecionados</a>
              </div>
            </div>
          </div>          
          
          <div class="spacer-vertical40"></div>

          <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
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

      <div class="checkbox col-lg-6 col-md-6 col-sm-6 col-xs-6">
        <label>
          <input type="checkbox" id="inlineCheck{{:idCategoria}}" name="categorias[]" class="check-categorias" value="{{:idCategoria}}"> {{:nome}}
        </label>
      </div>
</script>  



<script type="text/javascript">

    var $table = $('#table-ambientes');
    
    
    function queryParamsAmbiente(params) {

        params.pageNumber = $table.bootstrapTable('getOptions').pageNumber;
        
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
        
        var content = tmpl.render(json.rows);"/home/pazin/workspace/java/projetos/bootstrap-table-examples/methods/checkBy-uncheckBy.html"
        
        $('#checkBoxContainer').append(content);
    };
    
    
    
    // futuramente tornar esse Upload em ajax
    var preparaUpload = function()
    {
        $('input[name="ambientes[]"]').remove();
        
        // pegar as categorias e criar inputs hidden no form
        var selecao = $table.bootstrapTable('getSelections');
        
        if ( selecao.length <= 0 )
        {
            preencheAlertGeral("alertArea", "Escolha pelo menos um ambiente para receber o arquivo.", "danger");
            return false;
        }
        
        var categoriasCheckadas = $( ".check-categorias:checked" ).length;
        
        if ( categoriasCheckadas <=0 )
        {
            preencheAlertGeral("alertArea", "Escolha pelo menos uma categoria para classificar o arquivo.", "danger");
            return false;
        }
            
        $(selecao).each(function(){
            var linha = this;

            $('<input>').attr({
                type: 'hidden',
                id : 'ambiente',
                name : 'ambientes[]',
                value : linha.idAmbiente
            }).appendTo('#ambiente-upload-multi');
        });
        
        return true;
    }

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        var idAmbiente = $('#idAmbiente').val();

        $('#ambiente-upload-multi').submit( function(e){
            e.preventDefault();
            if ( preparaUpload() )
                this.submit();   
        });
        
        $('#btnUploadMidia').on('click', function(){
            $('#ambiente-upload-multi').submit();
        });
        
        listaCategorias();
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />