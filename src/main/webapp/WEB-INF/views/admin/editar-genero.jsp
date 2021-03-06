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
              <h3>Cadastro de G�neros</h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>

          
          <div class="spacer-vertical40"></div>

          <form class="form-horizontal" id="generoform" action="#" method="POST">
          
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" id="idGenero" name="idGenero" value="${idGenero}" >
  
            <div class="row">
              <div class="col-lg-12 col-md-12">
              
                <div class="form-group">
                  <label for="nome" class="control-label col-sm-2 col-md-2">Nome</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="nome" name="nome">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="nome" class="control-label col-sm-2 col-md-2">Descri��o</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="descricao" name="descricao">
                  </div>
                </div>

              </div> 
            </div>
            
            
            <div class="row">
              <div class="col-lg-12 col-md-12">
                <div class="pull-right-not-xs">
                  <button type="button" class="btn btn-primary" id="btnSalvar">
                    <i class="fa fa-floppy-o"></i>
                    Salvar Altera��es
                  </button>
                </div>            
              </div>
            </div>
            
            <div class="spacer-vertical40"></div>
            
            <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-6">
                <div class="">
                  <a class="btn btn-default" href="${context}/admin/generos/searches" >
                    <i class="fa fa-arrow-left"></i>
                    Voltar para Cadastro de G�neros</a>
                </div>            
              </div>
              <div class="col-lg-6 col-md-6 col-sm-6">
                <div class="pull-right-not-xs">
                  <a class="btn btn-default" href="${context}/admin/painel">Painel de Admin</a>
                </div>
              </div>
            </div>
                        
          </form>
          
        </div>
      </div>
    </div>
    

      
  </div> <!-- /container -->


<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.7.2/jquery.serializejson.min.js" defer></script>
<script type="text/javascript" src="${context}/js/required/jquery.populate.min.js" async></script>


<script type="text/javascript">


  var getDados = function()
  {
      var url = buildUrl( "/admin/generos/{idGenero}", {
          idGenero : $("#idGenero").val(),
      }); 
      
      $.ajax({
          type: 'GET',
          contentType: 'application/json',
          url: url,
          dataType: 'json'
      }).done( function(json) {
          
          removeErros();
          
          $('#generoform').populate(json);

          jump('ncmForm');
      });
  }


  var validaForm = function(){
      
      var isOk = true;
      
      removeErros();
      
      var arrayCampos = [
                          {field: "descricao",      desc : "Descri��o"},
                          {field: "nome",     desc : "Nome" } 
                        ];
      
      isOk = validaCampos( arrayCampos );
      
      return isOk;
  };

  var salvar = function(){
      
      if ( validaForm() ){
          
          var dados = JSON.stringify( $('#generoform').serializeJSON() );
          
          var url = buildUrl( "/admin/generos" ); 

          $.ajax({
              
              type: 'POST',
              contentType: 'application/json',
              url: url,
              dataType: 'json',
              data: dados 
              
          }).done( function(json){ 

              if ( json.idGenero && json.idGenero > 0){
                  
                  var url_cadastro = buildUrl( "/admin/generos/searches?cadastro={nomeGenero}", {
                      nomeGenero : json.nome
                  }); 

                  window.location = url_cadastro;
              }
              else{
                  preencheErros( json.errors );
              }
          });
      }
      
  };


  $(function(){

      var token = $("input[name='_csrf']").val();
      var header = "X-CSRF-TOKEN";
      $(document).ajaxSend(function(e, xhr, options) {
          xhr.setRequestHeader(header, token);
      });
     
      $('#btnSalvar').on('click', salvar);
      
      getDados();
      
  });



</script>

<style type="text/css">

#linkaddtelefone{
    color: #326432;
    margin: 2px;
    display: block;
    margin-top: 7px;
    white-space: nowrap;
}

</style>


<jsp:include page="/WEB-INF/views/bottom.jsp" />