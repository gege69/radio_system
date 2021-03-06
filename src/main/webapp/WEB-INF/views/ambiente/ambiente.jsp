<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
  
    <div class="row">
    
      <div class="panel panel-default">
        <div class="panel-heading">
          <input type="hidden" id="idAmbiente" value="${idAmbiente}">
          
          <div class="row">
            <div class="col-lg-6 col-md-6">
              <h3>${nome}<br/>
                <small>Login : ${login}</small><br/>
              </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>
          
        </div>
        
        <div class="panel-body">
          
          <div class="row">
          </div>
          
          <div class="row">
          
              <div class="row row-centered">
                <div class="container" id="view-container">
<!--                   botoes s�o inseridos por template aqui -->
                </div>
              </div>
              
          </div>
          
          <div class="spacer-vertical40">
          </div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
              <a class="btn btn-default" href="${context}/ambientes/searches">
                <i class="fa fa-arrow-left"></i>
              Voltar para Ambientes</a>
            </div>
            
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="pull-right-not-xs">
                <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
              </div>
            </div>
                  
          </div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">          
              <div class="pull-right-not-xs">
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
    </div>
      
  </div> <!-- /container -->


<script src="https://cdnjs.cloudflare.com/ajax/libs/jsrender/0.9.78/jsrender.min.js"></script>


<script id="viewTmpl" type="text/x-jsrender">
<div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
  <a class="btn btn-default btn-block btn-md btn-md botao-main" href="${context}{{:url_funcionalidade}}" {{:extrahtml}}>
    <i class="icone-main {{:sizeBig}} {{:classesIcone}}">{{:icone}}</i>
    <span class="label-botao-main">{{:nome}}</span>
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
            
            $('a[data-toggle="modal"]').addClass("disabled");
        } );
        
    }

    var makeListTmpl = function(json){
        
        var tmpl = $.templates('#viewTmpl');
        
        $('#view-container').empty();
        
        var content = tmpl.render(json);
        
        $('#view-container').append(content);
    };

    $(function(){

        var idAmbiente = $('#idAmbiente').val();
        listaFuncionalidades( idAmbiente, false);
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />