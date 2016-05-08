<jsp:include page="/WEB-INF/views/main.jsp" />   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">

    <div class="row">

      <div class="row">
        <div class="col-lg-12 col-md-12" id="alertArea">
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Espelhamento de Rádio<br/>
            <small>Escolha qual ambiente servirá como modelo para ${nome}</small>
          </h3>
          
          <div class="spacer-vertical40"></div>

          <div class="row">
            <div class="col-sm-10 col-md-10">
              <div class="panel panel-default">
                <div class="panel-heading">
                  Utilize este recurso se você deseja que a rádio atual tenha exatamente a mesma programação de outra rádio. <br/>
                  Selecione abaixo em qual ambiente deve servir como modelo.
                </div>
                <div class="panel-body">

                  <div class="col-lg-12 center-block">
                    <div class="row row-centered">
                        <h4>Alvo : ${nome}</h4>
                        <i class="fa fa-3x fa-arrow-up"></i>
                        <h4>Modelo : <span id="descAmbienteTemplate">Escolha um ambiente</span></h4>
                    </div>
                  </div>
                  
                  <div class="spacer-vertical20"></div>
                
                  <form class="form-horizontal" id="formEspelhar">
                  
                    <input type="hidden" id="idAmbienteAtual" name="idAmbienteAtual" value="${idAmbiente}">
                  
               
                    <div class="form-group">
                      <label for="login" class="control-label col-sm-2 col-md-3">Ambiente modelo:</label>
                      <div class="col-sm-10 col-md-8">
                        <select class="form-control" id="idAmbienteTemplate" name="idAmbienteTemplate">
                        </select>
                      </div>
                      
                    </div>
                    
                    <div class="form-group">
                      <label for="login" class="control-label col-sm-2 col-md-3">&nbsp;</label>  <!-- hack -->
                      <div class="col-sm-10 col-md-8">
                        <button type="submit" class="btn btn-primary">Espelhar</button>
                      </div>
                    </div>
                    
                  </form>
                </div>
              
              </div>
            </div>
          </div>
          
          <div class="spacer-vertical10"></div>

          <div class="row">
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
              <div class="">
                <a class="btn btn-default" href="${context}/ambientes/searches" >
                  <i class="fa fa-arrow-left"></i>
                  Voltar para Administrar Ambientes</a>
              </div>            
            </div>
          </div>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="pull-right">
        <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->



<script type="text/javascript">

    var getAmbientes = function()
    {
        var url = buildUrl( "/ambientes"); 
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: url,
            dataType: 'json'
        }).done( function(json) {
            
            var idAmbienteAtual = parseInt($("#idAmbienteAtual").val());
            
            $("#idAmbienteTemplate").empty();
            $('#idAmbienteTemplate').append('<option value="" disabled selected>Selecione o ambiente</option>');
            $.each(json.rows, function (i, amb) {
                if ( amb.idAmbiente != idAmbienteAtual ){
                    $('#idAmbienteTemplate').append($('<option>', { 
                        value: amb.idAmbiente,
                        text : amb.nome  
                    }));
                }
            });
            
            jump('ncmForm');
        });
    };


    var espelharAmbiente = function() 
    {Base
        var idAmbiente = $("#idAmbienteDialog").val();
        
        if ( idAmbiente == null || idAmbiente == 0 )
            preencheAlertGeral( "alertArea", "Ambiente não encontrado" );

        var url = buildUrl( "/ambientes/inativar");
        
        var formData =  $('#inativarAmbienteForm').serializeJSON();
        
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: url,
            data:  JSON.stringify(formData),
            dataType: 'json'
        }).done( function(json){ 

            if (json.ok == 1){
                preencheAlertGeral( "alertArea", "Ambiente desativado com sucesso", "success" );
            }
            else{
                preencheErros( json.errors );
            }
        });
    };

    $(function(){
        
       getAmbientes();
       
       $("#idAmbienteTemplate").on("change", function(){
          $("#descAmbienteTemplate").html( $( "#idAmbienteTemplate option:selected" ).text() );
       });
        
    });

</script>






<jsp:include page="/WEB-INF/views/bottom.jsp" />