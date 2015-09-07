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
            <div class="col-lg-11 col-md-12">
              <div class="panel panel-default">
                <div class="panel-heading">
<%--                   <h2>Upload de ${nome_categoria}</h2> --%>
                </div>
              
                <div class="panel-body">
                   
                  <div class="row">
                    <form action="#" id="ambiente-generos-form" method="POST">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                      <input type="hidden" id="id_ambiente" value="${id_ambiente}">
                      <input type="hidden" id="idCategoria" value="${idCategoria}">
                      <div class="container" id="view-container">
                        
                      </div>
                    </form>
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



<script id="viewTmpl" type="text/x-jsrender">
    <div class="form-inline">
      <div class="checkbox col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label>
          <input type="checkbox" id="genero-{{:id_genero}}" name="genero[id_genero]" value="{{:id_genero}}"> {{:descricao}}
        </label>
      </div>
    </div>  
</script>  


<script type="text/javascript">


    
    var upload = function( id_ambiente )
    {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '${context}/ambientes/'+id_ambiente+'/upload',
            dataType: 'json',
            data : dados 
        }).done( function(json){
            
        } );
        
    }

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        var id_ambiente = $('#id_ambiente').val();
        
        $('#btnUpload').on('click', function(){
            upload( $('#id_ambiente').val() );
        });
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />