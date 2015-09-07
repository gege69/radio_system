<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

  <div class="container theme-showcase">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="row" id="alertArea">
      </div>
      
      <div class="panel panel-default">
        <div class="panel-heading">
          <input type="hidden" id="idAmbiente" value="${idAmbiente}">
          <h3>${nome}<br/>
            <small>URL Player : ${urlambiente}</small><br/>
            <small>Login : ${login}</small><br/>
          </h3>
          
        </div>
        
        <div class="panel-body">
          
          <div class="row">
          </div>
          
          <div class="row">
          
              <div class="row row-centered">
                <div class="container" id="view-container">
<!--                   botoes são inseridos por template aqui -->
                </div>
              </div>
              
          </div>
          
          <div class="spacer-vertical40">
          </div>
          
          <div class="row">
            <div class="col-md-4 col-sm-3 col-xs-3">
              <a class="btn btn-default" href="${context}/administrar-ambiente">
                <i class="fa fa-arrow-left"></i>
              Voltar para Ambientes</a>
            </div>
            <div class="col-md-3 col-md-offset-10   col-sm-2 col-sm-offset-7   col-xs-4 col-xs-offset-5">
              <c:url var="logoutUrl" value="/logout"/>
              <form action="${logoutUrl}" method="post">
                <input type="submit" class="btn btn-link" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              </form>
            </div>
          </div>
          
        </div>
      </div>
    </div>
      
  </div> <!-- /container -->


<script id="viewTmpl" type="text/x-jsrender">
  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
  <a class="btn btn-default btn-block btn-md btn-md botao-main" href="${context}{{:url_funcionalidade}}">
    <i class="fa fa-3x icone-main {{:icone_funcionalidade}}"></i>
    <span class="label-botao-main">{{:nome_funcionalidade}}</span>
  </a>
  </div>
</script>  


<script type="text/javascript">

    var pagina = 0, limit = 6;

    var listaFuncionalidades = function( idAmbiente, doJump ){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/'+idAmbiente+'/funcionalidades/geral',
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

        var idAmbiente = $('#idAmbiente').val();
        listaFuncionalidades( idAmbiente, false);
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />