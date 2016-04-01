<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

</script>


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="row" id="alertArea">
      </div>
    
    
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Cadastro de Gêneros</h3>
          
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
                  <label for="nome" class="control-label col-sm-2 col-md-2">Descrição</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="descricao" name="descricao">
                  </div>
                </div>

              </div> 
            </div>
            
            
            <div class="row">
              <div class="col-lg-12 col-md-12">
                <div class="pull-right">
                  <button type="button" class="btn btn-primary" id="btnSalvar">
                    <i class="fa fa-floppy-o"></i>
                    Salvar Alterações
                  </button>
                </div>            
              </div>
            </div>
            
            <div class="spacer-vertical40"></div>
            
            <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <div class="">
                  <a class="btn btn-default" href="${context}/admin/generos/searches" >
                    <i class="fa fa-arrow-left"></i>
                    Voltar para Cadastro de Gêneros</a>
                </div>            
              </div>
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <div class="pull-right">
                  <a class="btn btn-default" href="${context}/admin/painel">Painel de Admin</a>
                </div>
              </div>
            </div>
                        
          </form>
          
        </div>
      </div>
    </div>
    

      
  </div> <!-- /container -->


<script type="text/javascript" src="${context}/js/required/jquery.serializejson.js" defer></script>
<script type="text/javascript" src="${context}/js/required/jquery.populate.js" async></script>


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
          
          removeErros( $('#generoform') );
          
          $('#generoform').populate(json);

          jump('ncmForm');
      });
  }


  var validaForm = function(){
      
      var isOk = true;
      
      removeErros( $('#generoform') );
      
      var arrayCampos = [
                          {field: "descricao",      desc : "Descrição"},
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

                  $("#idGenero").val( json.idGenero );
                  preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
                  jump(''); // topo da pagina
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