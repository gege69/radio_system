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
          <h3>Gerenciar ${nomeCategoria}<br/>
            <small>Espa�o para armazenamento: 0 MB em uso, 500 MB dispon�veis </small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    
                    <div class="col-md-12">
                      <form action="${context}/ambientes/${id_ambiente}/view-list-upload-midia/${codigo}" 
                            method="POST" 
                            id="ambiente-upload-midia" 
                            class="form" 
                            enctype="multipart/form-data">
                      
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" id="idCategoria" name="idCategoria" value="${idCategoria}">
                        
                        <div class="col-lg-7 col-md-9 col-sm-12">
                          <div class="form-group">
                            <label for="file">Arquivo:</label>
                            <input type="file" class="form-control" id="arquivo" name="file" placeholder="Jane Doe">
                          </div>
                        </div>

                        <div class="col-lg-12" >
                          <div class="form-group" id="checkBoxContainer">
                          </div>
                        </div>
                        
                        <div class="col-lg-7 col-md-9 col-sm-12">
                          <div class="form-group">
                            <a class="btn btn-primary" href="#" id="btnUploadMidia">Upload M�dia</a>
                          </div>
                        </div>
                        
                      </form>
                    </div>
                    
                    
                  </div>
                </div>
              
                <div class="panel-body">
                   
                  <div class="row">
                    <div class="col-lg-12 col-md-12">
                      <table  
                         id="table"
                         data-toggle="table"
                         data-url="${context}/ambientes/${id_ambiente}/midias-por-categoria/${idCategoria}/"
                         data-height="400"
                         data-side-pagination="server"
                         data-pagination="true"
                         data-page-size=7
                         data-page-list="[7]"
                         data-locale = "pt_BR"
                         data-query-params="queryParams" >
                        <thead>
                          <tr>
                              <th data-field="idMidia">ID</th>
                              <th data-field="nome">Nome</th>
                              <th data-field="descricao">Descri��o</th>
                          </tr>
                        </thead>
                      </table>
                    </div>                        
                  </div>
                </div>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="${context}/view-ambiente/${id_ambiente}" >Administrar Ambiente</a>
              </div>            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="pull-right">
          <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
        </div>
      </div>
    </div>
      
  </div> <!-- /container -->




<script id="viewTmpl" type="text/x-jsrender">
<label class="checkbox-inline">
  <input type="checkbox" id="inlineCheck{{:idCategoria}}" name="categorias[]" value="{{:idCategoria}}"> {{:nome}}
</label>
</script>  



<script type="text/javascript">

    var $table = $('#table');
    
    function queryParams(params) {

        params.pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
        
//         console.log("xxxxx  "+JSON.stringify(params));
        // {"limit":10,"offset":0,"order":"asc","your_param1":1,"your_param2":2}
        return params;
    }
    
    
    var listaCategorias = function( doJump ){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/categorias',
            dataType: 'json'
        }).done( function(json){
            makeListTmpl(json);
            
            var lista = json.data;
            
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
        
        var content = tmpl.render(json.data);
        
        $('#checkBoxContainer').append(content);
    };
    
    // futuramente tornar esse Upload em ajax
    var upload = function( id_ambiente )
    {
        // pegar as categorias e fazer a listinha de strings 
        $("#ambiente-upload-midia").submit();
        
        $("#ambiente-upload-midia").bind('ajax:complete', function() {
            console.log('teste');
        });
    }

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        var id_ambiente = $('#id_ambiente').val();
        
        $('#btnUploadMidia').on('click', function(){
            upload( $('#id_ambiente').val() );
        });
        
        listaCategorias();
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />