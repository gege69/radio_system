<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
 
    <div class="row">
    
      <div class="panel panel-default">
        <div class="panel-body">

          <div class="row">
            <div class="col-lg-6 col-md-6">
              <h3>Busca Básica<br/>
                <small>Busca de arquivos de mídia</small>
              </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    
                    <div class="col-md-12">
                      <form action="#" 
                            id="ambiente-search-midia" 
                            class="form"
                            onsubmit="return false;" >
                      
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                        <div class="col-lg-7 col-md-9 col-sm-12">
                          <div class="form-group">
                            <label for="file">Nome do <b>arquivo</b> ou nome da <b>música</b>:</label>
                            <input type="text" class="form-control" id="nome-midia" name="nome" placeholder="Ex: 'Meteoro da paixão' ou 'Run to the Hills'">
                          </div>
                        </div>

                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                          <div class="form-group" id="checkBoxContainer">
                          </div>
                        </div>
                        
                        <div class="col-lg-7 col-md-9 col-sm-12">
                          <div class="form-group">
                            <a class="btn btn-default" href="#" id="btnSearchMidia">Pesquisar</a>
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
                         data-url = "${context}/ambientes/${idAmbiente}/midias/searches/"
                         data-toggle="table"
                         data-height="400"
                         data-side-pagination="server"
                         data-pagination="true"
                         data-page-size=7
                         data-page-list="[7]"
                         data-locale = "pt_BR"
                         data-query-params = "queryParams" >
                        <thead>
                          <tr>
                              <th data-field="idMidia">ID</th>
                              <th data-field="nome">Nome</th>
                              <th data-field="dataUpload">Data Upload</th>
                              <th data-field="vinheta" data-formatter="catFormatter">Vinheta</th>
                              <th data-field="inst" data-formatter="catFormatter">Institucional</th>
                              <th data-field="comercial" data-formatter="catFormatter">Comercial</th>
                              <th data-field="programete" data-formatter="catFormatter">Programete</th>
                              <th data-field="chamada-func" data-formatter="catFormatter">Cham. Funcionário</th>
                              <th data-field="chamada-inst" data-formatter="catFormatter">Cham. Instantânea</th>
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
                <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/view" >Administrar Ambiente</a>
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


<script src="https://cdnjs.cloudflare.com/ajax/libs/jsrender/0.9.78/jsrender.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.7.2/jquery.serializejson.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-pt-BR.min.js" charset="UTF-8"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet">

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
        
        formparams = $('#ambiente-search-midia').serializeJSON();
        
        for ( var k in formparams )
            params[k] = formparams[k];
        
        return params;
    }
    
    
    var listaCategorias = function( doJump ){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/categorias',
            dataType: 'json'
        }).done( function(json){
            makeListTmpl(json);
        } );
    }
    
    
    var makeListTmpl = function(json){
        
        var tmpl = $.templates('#viewTmpl');
        
        $('#checkBoxContainer').empty();
        
        var content = tmpl.render(json.rows);
        
        $('#checkBoxContainer').append(content);
    };
    

    $(function(){

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        var idAmbiente = $('#idAmbiente').val();

        
        $('#btnUploadMidia').on('click', function(){
            upload( $('#idAmbiente').val() );
        });

        
        $('#btnSearchMidia').on('click', function(){
            $table.bootstrapTable('refresh');    
        });
        
        
//         listaCategorias();
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />