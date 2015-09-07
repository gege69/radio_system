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
      </div>
    
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Gerenciar Mídias<br/>
            <small>Espaço para armazenamento: 0 MB em uso, 500 MB disponíveis </small>
          </h3>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    
                    <div class="col-md-12">
                      <form action="${context}/ambientes/${id_ambiente}/upload" 
                            method="POST" 
                            id="ambiente-upload-midia" 
                            class="form-inline" 
                            enctype="multipart/form-data">
                      
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" id="name" name="name" value="teste">
                        <input type="hidden" id="categorias" name="categorias" value="">
                        
                        <div class="form-group">
                          <label for="file">Arquivo</label>
                          <input type="file" class="form-control" id="arquivo" name="file" placeholder="Jane Doe">
                        </div>
                        
                        <div class="form-group">
                          <a class="btn btn-primary" href="#" id="btnUploadMidia">Upload Mídia</a>
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
                         data-url="${context}/ambientes/${id_ambiente}/midias-por-categoria/${id_categoria}/"
                         data-height="400"
                         data-side-pagination="server"
                         data-pagination="true"
                         data-page-list="[5, 10]"
                         data-locale = "pt_BR"
                         data-query-params="queryParams" >
                        <thead>
                          <tr>
                              <th data-field="idMidia">ID</th>
                              <th data-field="nome">Nome</th>
                              <th data-field="descricao">Descrição</th>
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
          
          <div class="spacer-vertical10"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="#" id="btnSalvarGeneros">Modo Avançado</a>
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








<script type="text/javascript">

    var $table = $('#table');
    
    function queryParams(params) {

        params.pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
        
//         console.log("xxxxx  "+JSON.stringify(params));
        // {"limit":10,"offset":0,"order":"asc","your_param1":1,"your_param2":2}
        return params;
    }
    
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
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />