<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>


<link href="${context}/css/bootstrap-slider.min.css" rel="stylesheet">
<script src="${context}/js/bootstrap-slider.min.js" charset="UTF-8"></script>


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="row" id="alertArea">
      </div>
    
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Configurar Player do Ambiente<br/>
            <small>Configure os recursos que deseja disponibilizar para o player do ${nome}</small>
          </h3>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <form action="#" class="form" id="ambiente-config-form" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" id="idAmbiente" name="ambiente[idAmbiente]" value="${idAmbiente}">
                <input type="hidden" id="idAmbConfig" name="idAmbConfig" value="${idAmbConfig}">
                
                <div class="form-inline">
                  <div class="container" id="view-container">
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="autoplay" name="autoplay" value="true"> Auto-Play
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="selecaoGenero" name="selecaoGenero" value="true"> 
                        Habilitar m�dulo de sele��o de G�neros pelo cliente 
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="avancarRetornar" name="avancarRetornar" value="true"> 
                        Permitir "avan�ar" ou "retornar" m�sicas
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="atendimento" name="atendimento" value="true"> 
                        Habilitar sistema de atendimento
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="chamVeiculo" name="chamVeiculo" value="true"> 
                        Habilitar m�dulo para chamada de propriet�rios de ve�culos
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="chamFuncionarios" name="chamFuncionarios" value="true"> 
                        Habilitar m�dulo para chamada de funcion�rios
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="chamVariosFuncionarios" name="chamVariosFuncionarios" value="true"> 
                        Permitir chamar v�rios funcion�rios simult�neamente
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="chamInstantanea" name="chamInstantanea" value="true"> 
                        Habilitar m�dulo de chamada instant�nea (oferta rel�mpago)
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="rodoviarias" name="rodoviarias" value="true"> 
                        Habilitar m�dulo Rodovi�ria
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="horoscopo" name="horoscopo" value="true"> 
                        Habilitar m�dulo de hor�scopo
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="agendMidia" name="agendMidia" value="true"> 
                        Habilitar m�dulo de agendamento de m�dias c/ hora marcada
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="relatoriosMidia" name="relatoriosMidia" value="true"> 
                        Disponibilizar relat�rios de m�dias
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="controleBlocos" name="controleBlocos" value="true"> 
                        Permitir controle de blocos pelo cliente
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="controleComerciais" name="controleComerciais" value="true"> 
                        Permitir que o cliente ative, bloqueie ou reproduza comerciais
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="controleInstitucionais" name="controleInstitucionais" value="true"> 
                        Permitir que o cliente ative, bloqueie ou reproduza institucionais
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="controleProgrametes" name="controleProgrametes" value="true"> 
                        Permitir que o cliente ative, bloqueie ou reproduza programetes
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="nobreak" name="nobreak" value="true"> 
                        Habilitar no-break
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="menuDownloads" name="menuDownloads" value="true"> 
                        Habilitar o menu de Downloads 
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="locutorVirtual" name="locutorVirtual" value="true"> 
                        Habilitar o m�dulo Locutor Virtual
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="pedidoMusical" name="pedidoMusical" value="true"> 
                        Habilitar sistema de pedidos musicais
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="pedidoMusicalVinheta" name="pedidoMusicalVinheta" value="true"> 
                        Reproduzir vinheta do pedido musical
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="generosByCC" name="generosByCC" value="true"> 
                        Habilitar G�neros by Creative Commons
                      </label>
                    </div>
                    
                    <div class="checkbox col-lg-6 col-md-6 col-sm-12 col-xs-12">
                      <label>
                      <input type="checkbox" id="opcionais" name="opcionais" value="true"> 
                        Habilitar Opcionais
                      </label>
                    </div>
                    
                  </div>
                </div>
                
                <br/>
                
                <div class="row">
                  <div class="form-group">
                    <label class="control-label text-right col-lg-2 col-md-2 col-sm-3 col-xs-3">Voz Locu��o : </label>
                    <div class="form-inline col-lg-4 col-md-4 col-sm-8 col-xs-8 ">
                        <div class="radio">
                          <label>
                          <input type="radio" id="vozLocucao1" name="vozLocucao" value="MASCULINA" checked="checked"> Masculina
                          </label>
                        </div>
                        <div class="radio">
                          <label>
                          <input type="radio" id="vozLocucao2" name="vozLocucao" value="FEMININA"> Feminina
                          </label>
                        </div>
                    </div>
                  </div>
                </div>
                
                <div class="spacer-vertical20"></div>

                <div class="row">
                  <div class="form-group col-lg-6 col-md-6">
                    <label for="slider1" class="control-label">Volume das m�sicas:</label>
                    <div class="form-inline">
                      <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <input id="slider1" class="campo-slider" data-slider-id='ex1Slider' type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1"/>
                        <span id="slider1ValLabel" style="margin-left: 20px;">Valor: <span id="slider1Val"></span></span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group col-lg-6 col-md-6">
                    <label for="slider2" class="control-label">Volume dos comerciais, vinhetas e institucionais:</label>
                    <div class="form-inline">
                      <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <input id="slider2" class="campo-slider" data-slider-id='ex2Slider' type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1"/>
                        <span id="slider2ValLabel" style="margin-left: 20px;">Valor: <span id="slider2Val"></span></span>
                      </div>
                    </div>
                  </div>
                </div>
                
                <div class="row">
                  <div class="form-group col-lg-6 col-md-6">
                    <label for="slider3" class="control-label">Volume das chamadas (funcion�rios, ve�culos, instant�neas, etc):</label>
                    <div class="form-inline">
                      <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <input id="slider3" class="campo-slider" data-slider-id='ex3Slider' type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1"/>
                        <span id="slider3ValLabel" style="margin-left: 20px;">Valor: <span id="slider3Val"></span></span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group col-lg-6 col-md-6">
                    <label for="slider3" class="control-label">VOLUME GERAL:</label>
                    <div class="form-inline">
                      <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <input id="slider4" class="campo-slider" data-slider-id='ex4Slider' type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1"/>
                        <span id="slider4ValLabel" style="margin-left: 20px;">Valor: <span id="slider4Val"></span></span>
                      </div>
                    </div>
                  </div>
                </div>
                  
              </form>
            </div>
          </div>
          
          <br/>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-primary" href="#" id="btnSalvarConfig">Salvar Altera��es</a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
              </div>            
            </div>            
          </div>
          
          <div class="spacer-vertical80"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="${context}/view-ambiente/${idAmbiente}" >
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


    var getDados = function( id )
    {
        if ( id == null || id == undefined )
            alert('Id n�o encontrado');
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/'+id+'/configuracoes',
            dataType: 'json'
        }).done( function(json) {
            
            removeErros( $('#ambiente-config-form') );
            $('#ambiente-config-form').populate(json);
             
            $('#slider1').bootstrapSlider('setValue', json.volumeMusicas ? json.volumeMusicas : 0 );
            $('#slider2').bootstrapSlider('setValue', json.volumeChamadas ? json.volumeChamadas : 0 );
            $('#slider3').bootstrapSlider('setValue', json.volumeComerciais ? json.volumeComerciais : 0 );
            $('#slider4').bootstrapSlider('setValue', json.volumeGeral ? json.volumeGeral : 0 );
            
            $("#slider1Val").text(json.volumeMusicas ? json.volumeMusicas : 0 );
            $("#slider2Val").text(json.volumeChamadas ? json.volumeChamadas : 0 );
            $("#slider3Val").text(json.volumeComerciais ? json.volumeComerciais : 0 );
            $("#slider4Val").text(json.volumeGeral ? json.volumeGeral : 0 );
            
            jump('ambiente-config-form');
        });
    }

    
    var salvarConfig = function( idAmbiente )
    {
        var dados = $('#ambiente-config-form').serializeJSON();

        dados.volumeMusicas = $('#slider1').bootstrapSlider('getValue');
        dados.volumeChamadas = $('#slider2').bootstrapSlider('getValue');
        dados.volumeComerciais = $('#slider3').bootstrapSlider('getValue');
        dados.volumeGeral = $('#slider4').bootstrapSlider('getValue');

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '${context}/ambientes/'+idAmbiente+'/configuracoes',
            dataType: 'json',
            data : JSON.stringify( dados )
        }).done( function(json){
            if (json.ok != null){
                preencheAlertGeral( "alertArea", "Configura��es gravadas com sucesso.", "success" );
                jump(''); // topo da pagina
            }
            else{
                preencheErros( json.errors );
            }
        } );
        
    }
    

    var refreshValorSlider = function(slideEvt) {
        var campoId = slideEvt.target.id;
        $("#"+campoId+"Val").text(slideEvt.value);
    };
    

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        
        $('#btnSalvarConfig').on('click', function(){
            salvarConfig( $('#idAmbiente').val() );
        });
        
        getDados( $('#idAmbiente').val() );
        
      
        $(".campo-slider").bootstrapSlider({
            ticks: [0, 20, 40, 60, 80, 100],
            ticks_labels: ['0', '20', '40', '60', '80', '100'],
            ticks_snap_bounds: 3
        });

        $(".campo-slider").on("slide", function(slideEvt) {
            refreshValorSlider(slideEvt);
        });
        $(".campo-slider").on("slideStop", function(slideEvt) {
            refreshValorSlider(slideEvt);
        });
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />
