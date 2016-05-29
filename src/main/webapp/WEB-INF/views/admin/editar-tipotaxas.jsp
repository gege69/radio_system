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
              <h3>Cadastro de Tipos de Taxas</h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>

          <form class="form-horizontal" id="tipotaxasform" action="#" method="POST">
          
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" id="idTipotaxa" name="idTipotaxa" value="${idTipotaxa}" >
  
            <div class="row">
              <div class="col-lg-12 col-md-12">
              
                <div class="form-group">
                  <label for="descricao" class="control-label col-sm-2 col-md-2">Descrição</label>
                  <div class="col-lg-7 col-sm-8 col-md-8">
                    <input type="text" class="form-control" id="descricao" name="descricao">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="porambiente" class="control-label col-sm-2 col-md-2">Por ambiente</label>
                  <div class="col-lg-7 col-md-8 col-sm-8">
                    <div class="checkbox" style="    margin-left: 21px;">
                      <input type="checkbox" id="porambiente" name="porambiente" value="true">
                    </div>
                  </div>
                </div>

                <div class="form-group">
                  <label for="operacao" class="control-label col-sm-2 col-md-2">Operação</label>
                  <div class="col-lg-5 col-md-5 col-sm-6">
                    <select class="form-control" id="operacao" name="operacao">
                      <option value="COBRANCA" selected="selected" >Cobrança</option>
                      <option value="DESCONTO" >Desconto</option>
                    </select>
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
                  <a class="btn btn-default" href="${context}/admin/tipotaxas/searches" >
                    <i class="fa fa-arrow-left"></i>
                    Voltar para Cadastro de Tipos de Taxa</a>
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
      var url = buildUrl( "/admin/tipotaxas/{idTipotaxa}", {
          idTipotaxa : $("#idTipotaxa").val(),
      }); 
      
      $.ajax({
          type: 'GET',
          contentType: 'application/json',
          url: url,
          dataType: 'json'
      }).done( function(json) {
          
          removeErros();
          
          $('#tipotaxasform').populate(json);

          jump('ncmForm');
      });
  }


  var validaForm = function(){
      
      var isOk = true;
      
      removeErros();
      
      var arrayCampos = [
                          {field: "descricao",      desc : "Descrição"},
                          {field: "operacao",     desc : "Operação" } 
                        ];
      
      isOk = validaCampos( arrayCampos );
      
      return isOk;
  };

  var salvar = function(){
      
      if ( validaForm() ){
          
          var dados = JSON.stringify( $('#tipotaxasform').serializeJSON() );
          
          var url = buildUrl( "/admin/tipotaxas" ); 

          $.ajax({
              
              type: 'POST',
              contentType: 'application/json',
              url: url,
              dataType: 'json',
              data: dados 
              
          }).done( function(json){ 

              if ( json.idTipotaxa && json.idTipotaxa > 0){

                  $("#idTipotaxa").val( json.idTipotaxa );
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