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
          <h3>Programação Musical ( Gêneros )<br/>
            <small>Programação musical do ${nome}</small>
          </h3>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-8">
              <div class="panel panel-default">
                <div class="panel-body">
                   
                  <div class="row">
                    <div class="col-lg-12">
  
                        <div class="row">
                          <div class="col-lg-2 col-md-2 col-sm-4 col-xs-6">
                          
                            <table>
                              <tr>
                              </tr>
                              <tr>
                              </tr>
                              <tr>
                              </tr>
                              <tr>
                              </tr>
                            </table>
                            
                          </div>
                        </div>
  
  
                        <div class="row">
                          <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <a class="btn btn-primary" id="btnSalvarExpediente" href="#">Salvar Alterações</a>
                          </div>
                        </div>                     
                          
                    </div>
                  </div>
                  
                </div>
              </div>
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/view" >
                  <i class="fa fa-arrow-left"></i>
                  Voltar para ${nome}
                </a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
              </div>
            </div>
          </div>
          
        </div>
      </div>
    </div>
    
      
  </div> <!-- /container -->



<script type="text/javascript">


    var salvar = function(){
        
        $.ajax({
            
            type: 'POST',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/expedientes',
            dataType: 'json',
            data:  JSON.stringify( $('#ambiente-expediente-form').serializeJSON() )
            
        }).done( function(json){ 

            if (json.ok == 1 ){
                preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
                jump(''); // topo da pagina
            }
            else{
                preencheErros( json.errors );
            }
        });
    };

    var getDados = function( id )
    {
        if ( id == null || id == undefined )
            alert('Id não encontrado');
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/'+id,
            dataType: 'json'
        }).done( function(json) {
            
            removeErros( $('#ambiente-expediente-form') );
             $('#ambiente-expediente-form').populate(json);
            jump('ambiente-expediente-form');
        });
    }

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        $('#btnSalvarExpediente').on('click', salvar);
        
        getDados( $('#idAmbiente').val() );
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />