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

                  <div class="row">
                    <div class="col-md-offset-4 col-md-4">
                      <table>
                        <thead>
                          <td class="tdespecial">Alvo</td>
                          <td></td>
                          <td class="tdespecial">Modelo</td>
                        </thead>
                        <tr>
                          <td>
                            <h4>${nome}</h4>
                          </td>
                          <td>
                            <i class="fa fa-3x fa-arrow-left" style="margin-right: 20px; margin-left: 20px;"></i>
                          </td>
                          <td>
                            <h4><span id="descAmbienteTemplate">Escolha um ambiente</span></h4>
                          </td>
                        </tr>
                      </table>
                    </div>
                  </div>
                  
                  <div class="spacer-vertical20"></div>
                
                  <form class="form-horizontal" id="formEspelhar">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" id="idAmbienteAtual" name="idAmbienteAtual" value="${idAmbiente}">
                  
               
                    <div class="form-group">
                      <label for="login" class="control-label col-sm-2 col-md-3">Ambiente modelo:</label>
                      <div class="col-sm-10 col-md-8">
                        <select class="form-control" id="idAmbienteTemplate" name="idAmbienteTemplate">
                        </select>
                      </div>
                      
                    </div>
                    
                    <div class="form-group">
                      <label for="login" class="control-label col-sm-2 col-md-3">&nbsp;</label>
                      <div class="col-sm-10 col-md-8">
                        <button type="button" id="btnEspelharDialog" class="btn btn-primary">Espelhar</button>
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
  
  
  
<div class="modal fade" id="myDialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Espelhar ambiente</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="dialogEspelhar">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              Deseja realmente copiar a programação do ambiente <span id="nomeTemplate"></span> para o ambiente ${nome}?
            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnNaoDialog" data-dismiss="modal">Não</button>
        <button type="button" class="btn btn-primary" id="btnConfirmarEspelhar">Sim</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<style type="text/css">

.tdespecial {
  border: solid #CCCBCB 1px;
  background: #f5f5f5;
  text-align: center;
}

</style>


<script type="text/javascript" src="${context}/js/required/jquery.serializejson.js" defer></script>

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
    {
        var url = buildUrl( "/ambientes/espelhar");
        
        var formData =  $('#formEspelhar').serializeJSON();
        
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: url,
            data:  JSON.stringify(formData),
            dataType: 'json'
        }).done( function(json){ 

            if (json.ok == 1){
                preencheAlertGeral( "alertArea", "Ambiente espelhado com sucesso", "success" );
                $('#myDialog').modal('toggle');
            }
            else{
                preencheErros( json.errors );
                $('#myDialog').modal('toggle');
            }
        });
    };


    var openDialog = function()
    { 
        var idAmbienteTemplate = $("#idAmbienteTemplate").val();

        if (idAmbienteTemplate == null || idAmbienteTemplate == undefined || idAmbienteTemplate == 0 ) 
            preencheAlertGeral( "alertArea", "Selecione um ambiente modelo");
        else { 
            $('#nomeTemplate').html($("#idAmbienteTemplate option:selected" ).text());
            
            $('#myDialog').modal('show');
        }
        
    }


    $(function(){
        
       var token = $("input[name='_csrf']").val();
       var header = "X-CSRF-TOKEN";
       $(document).ajaxSend(function(e, xhr, options) {
           xhr.setRequestHeader(header, token);
       });

       getAmbientes();
       
       $("#idAmbienteTemplate").on("change", function(){
          $("#descAmbienteTemplate").html( $( "#idAmbienteTemplate option:selected" ).text() );
       });
       
       $("#btnEspelharDialog").click( function(){
           openDialog();
       });

       $("#btnConfirmarEspelhar").click( function(){
           espelharAmbiente();
       });
        
    });

</script>



<jsp:include page="/WEB-INF/views/bottom.jsp" />