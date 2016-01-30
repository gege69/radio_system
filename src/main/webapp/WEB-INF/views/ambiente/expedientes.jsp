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
          <h3>Definir Expediente<br/>
            <small>Configure os horários de início e fim da transmissão em ${nome}</small>
          </h3>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-8">
              <div class="panel panel-default">
                <div class="panel-body">
                   
                  <div class="row">
                    <div class="col-lg-12">
                      <form action="#" 
                            id="ambiente-expediente-form" 
                            class="form-horizontal"
                            method="POST">
                            
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" id="idAmbiente" name="idAmbiente" value="${idAmbiente}">
                        
                        <div class="form-group">
                          <label class="control-label text-right col-lg-3 col-md-3 col-sm-3 col-xs-3">Iniciar : </label>
                          <div class="form-inline col-lg-2 col-md-3 col-sm-3 col-xs-4 ">
                            <select class="form-control" name="horaIniExpediente" id="horaIniExpediente" >
                              <option value="0" >00</option>
                              <option value="1" >01</option>
                              <option value="2" >02</option>
                              <option value="3" >03</option>
                              <option value="4" >04</option>
                              <option value="5" >05</option>
                              <option value="6" >06</option>
                              <option value="7" >07</option>
                              <option value="8" >08</option>
                              <option value="9" >09</option>
                              <option value="10">10</option>
                              <option value="11">11</option>
                              <option value="12">12</option>
                              <option value="13">13</option>
                              <option value="14">14</option>
                              <option value="15">15</option>
                              <option value="16">16</option>
                              <option value="17">17</option>
                              <option value="18">18</option>
                              <option value="19">19</option>
                              <option value="20">20</option>
                              <option value="21">21</option>
                              <option value="22">22</option>
                              <option value="23">23</option>
                            </select>
                          </div>
                          <div class="form-inline col-lg-2 col-md-3 col-sm-3 col-xs-4 ">
                            <select class="form-control" name="minutoIniExpediente" id="minutoIniExpediente">
                              <option value="0">00</option>
                              <option value="5">05</option>
                              <option value="10">10</option>
                              <option value="15">15</option>
                              <option value="20">20</option>
                              <option value="25">25</option>
                              <option value="30">30</option>
                              <option value="35">35</option>
                              <option value="40">40</option>
                              <option value="45">45</option>
                              <option value="50">50</option>
                              <option value="55">55</option>
                              <option value="59">59</option>
                            </select>
                          </div>
                        </div>
  
                        <div class="form-group">
                          <label class="control-label text-right col-lg-3 col-md-3 col-sm-3 col-xs-3">Finalizar : </label>
                          <div class="form-inline col-lg-2 col-md-3 col-sm-3 col-xs-4 ">
                            <select class="form-control" name="horaFimExpediente" id="horaFimExpediente">
                              <option value="0" >00</option>
                              <option value="1" >01</option>
                              <option value="2" >02</option>
                              <option value="3" >03</option>
                              <option value="4" >04</option>
                              <option value="5" >05</option>
                              <option value="6" >06</option>
                              <option value="7" >07</option>
                              <option value="8" >08</option>
                              <option value="9" >09</option>
                              <option value="10">10</option>
                              <option value="11">11</option>
                              <option value="12">12</option>
                              <option value="13">13</option>
                              <option value="14">14</option>
                              <option value="15">15</option>
                              <option value="16">16</option>
                              <option value="17">17</option>
                              <option value="18">18</option>
                              <option value="19">19</option>
                              <option value="20">20</option>
                              <option value="21">21</option>
                              <option value="22">22</option>
                              <option value="23">23</option>
                            </select>
                          </div>
                          <div class="form-inline col-lg-2 col-md-3 col-sm-3 col-xs-4 ">
                            <select class="form-control" name="minutoFimExpediente" id="minutoFimExpediente">
                              <option value="0">00</option>
                              <option value="5">05</option>
                              <option value="10">10</option>
                              <option value="15">15</option>
                              <option value="20">20</option>
                              <option value="25">25</option>
                              <option value="30">30</option>
                              <option value="35">35</option>
                              <option value="40">40</option>
                              <option value="45">45</option>
                              <option value="50">50</option>
                              <option value="55">55</option>
                              <option value="59">59</option>
                            </select>
                          </div>
                        </div>
                        
                        <div class="spacer-vertical20"></div>
  
                        <div class="row">
                          <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <a class="btn btn-primary" id="btnSalvarExpediente" href="#">Salvar Alterações</a>
                          </div>
                        </div>                     
                          
                      </form>
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


<script src="${context}/js/required/jquery.serializejson.js"></script>
<script src="${context}/js/required/jquery.populate.js"></script>

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
            
            debugger;
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