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
          <h3>Gêneros<br/>
            <small>Selecione os gêneros musicais que serão reproduzidos em ${nome}</small>
          </h3>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-11 col-md-12">
              <div class="panel panel-default">
                <div class="panel-body">
                   
                  <div class="row">
                    <form action="" id="ambiente-generos-form">
                      <input type="hidden" id="id_ambiente" value="${id_ambiente}">
                      <div class="container" id="view-container">
                      </div>
                    </form>
                  </div>
                  
                </div>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-10 col-md-10">
              
              <div class="">
                <a class="btn btn-default" href="${context}/incluir-ambiente">Adicionar Novo Ambiente</a>
              </div>            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-offset-10 col-sm-offset-9 col-xs-offset-7">
        <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->



<script id="viewTmpl" type="text/x-jsrender">
    <div class="form-inline">
      <div class="checkbox col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label>
          <input type="checkbox" id="{{:id_genero}}" name="id_genero" value="true"> {{:descricao}}
        </label>
      </div>
    </div>  
</script>  


<script type="text/javascript">

    var pagina = 0, limit = 6;

    var listaGeneros = function( id_ambiente, doJump ){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/'+id_ambiente+'/generos',
            dataType: 'json',
            data: {'pagina': pagina}
        }).done( function(json){
            makeListTmpl(json);
        } );
    }

    var makeListTmpl = function(json){
        
        var tmpl = $.templates('#viewTmpl');
        
        $('#view-container').empty();
        
        var content = tmpl.render(json.data);
        
        $('#view-container').append(content);
    };

    $(function(){

        var id_ambiente = $('#id_ambiente').val();
        listaGeneros( id_ambiente, false);
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />