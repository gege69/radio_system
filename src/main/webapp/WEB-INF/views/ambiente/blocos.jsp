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
          <h3>Configurar Blocos<br/>
            <small>Configuração de blocos de áudio de ${nome}</small>
          </h3>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-10 col-md-10">
              <div class="panel panel-default">
                <div class="panel-body">
                   
                  <div class="row">
                    <div class="col-lg-12">
                      <form action="#" 
                            id="ambiente-bloco-form" 
                            class="form-horizontal"
                            method="POST">
                            
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="hidden" id="idAmbiente" name="idAmbiente" value="${idAmbiente}">
                        <input type="hidden" id="idBloco" name="idBloco" value="${idBloco}">
                        
                        <div class="form-group">
                          <label for="posicaoVinheta" class="control-label col-sm-4 col-md-3">Posicao Vinheta:</label>
                          <div class="col-sm-6 col-md-8">
                            <select class="form-control" id="posicaoVinheta" name="posicaoVinheta">
                              <option value="ANTES_CADA_MUSICA">Antes de cada música</option>
                              <option value="ANTES_BLOCO_COMERCIAL">Antes do bloco Comercial</option>
                              <option value="DEPOIS_BLOCO_COMERCIAL">Depois do bloco Comercial</option>
                              <option value="NAO_INCLUIR">Não incluir vinhetas</option> 
                            </select>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label for="qtdMusicas" class="control-label col-sm-4 col-md-3">Qtd Musicas:</label>
                          <div class="col-sm-6 col-md-8">
                            <select class="form-control" id="qtdMusicas" name="qtdMusicas">
                              <option value="1" >1</option>
                              <option value="2" >2</option>
                              <option value="3" >3</option>
                              <option value="4" >4</option>
                              <option value="5" >5</option>
                              <option value="6" >6</option>
                              <option value="7" >7</option>
                              <option value="8" >8</option>
                              <option value="9" >9</option>
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
                              <option value="24">24</option>
                              <option value="25">25</option>
                              <option value="26">26</option>
                              <option value="27">27</option>
                              <option value="28">28</option>
                              <option value="29">29</option>
                              <option value="30">30</option>
                            </select>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label for="qtdComerciais" class="control-label col-sm-4 col-md-3">Qtd Comerciais:</label>
                          <div class="col-sm-6 col-md-8">
                            <select class="form-control" id="qtdComerciais" name="qtdComerciais">
                              <option value="1" >1</option>
                              <option value="2" >2</option>
                              <option value="3" >3</option>
                              <option value="4" >4</option>
                              <option value="5" >5</option>
                              <option value="6" >6</option>
                              <option value="7" >7</option>
                              <option value="8" >8</option>
                              <option value="9" >9</option>
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
                              <option value="24">24</option>
                              <option value="25">25</option>
                              <option value="26">26</option>
                              <option value="27">27</option>
                              <option value="28">28</option>
                              <option value="29">29</option>
                              <option value="30">30</option>
                            </select>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label for="indexInstitucionais" class="control-label col-sm-4 col-md-3">Index Institucionais:</label>
                          <div class="col-sm-6 col-md-8">
                            <select class="form-control" id="indexInstitucionais" name="indexInstitucionais">
                              <option value="0" >Não incluir institucionais</option>
                              <option value="1" >Depois de 1 música</option>
                              <option value="2" >Depois de 2 músicas</option>
                              <option value="3" >Depois de 3 músicas</option>
                              <option value="4" >Depois de 4 músicas</option>
                              <option value="5" >Depois de 5 músicas</option>
                              <option value="6" >Depois de 6 músicas</option>
                              <option value="7" >Depois de 7 músicas</option>
                              <option value="8" >Depois de 8 músicas</option>
                              <option value="9" >Depois de 9 músicas</option>
                              <option value="10">Depois de 10 músicas</option>
                              <option value="11">Depois de 11 músicas</option>
                              <option value="12">Depois de 12 músicas</option>
                              <option value="13">Depois de 13 músicas</option>
                              <option value="14">Depois de 14 músicas</option>
                              <option value="15">Depois de 15 músicas</option>
                              <option value="16">Depois de 16 músicas</option>
                              <option value="17">Depois de 17 músicas</option>
                              <option value="18">Depois de 18 músicas</option>
                              <option value="19">Depois de 19 músicas</option>
                              <option value="20">Depois de 20 músicas</option>
                              <option value="21">Depois de 21 músicas</option>
                              <option value="22">Depois de 22 músicas</option>
                              <option value="23">Depois de 23 músicas</option>
                              <option value="24">Depois de 24 músicas</option>
                              <option value="25">Depois de 25 músicas</option>
                              <option value="26">Depois de 26 músicas</option>
                              <option value="27">Depois de 27 músicas</option>
                              <option value="28">Depois de 28 músicas</option>
                              <option value="29">Depois de 29 músicas</option>
                              <option value="30">Depois de 30 músicas</option>
                            </select>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label for="indexProgrametes" class="control-label col-sm-4 col-md-3">Index Programetes:</label>
                          <div class="col-sm-6 col-md-8">
                            <select class="form-control" id="indexProgrametes" name="indexProgrametes">
                              <option value="0" >Não incluir programetes</option>
                              <option value="1" >Depois de 1 música</option>
                              <option value="2" >Depois de 2 músicas</option>
                              <option value="3" >Depois de 3 músicas</option>
                              <option value="4" >Depois de 4 músicas</option>
                              <option value="5" >Depois de 5 músicas</option>
                              <option value="6" >Depois de 6 músicas</option>
                              <option value="7" >Depois de 7 músicas</option>
                              <option value="8" >Depois de 8 músicas</option>
                              <option value="9" >Depois de 9 músicas</option>
                              <option value="10">Depois de 10 músicas</option>
                              <option value="11">Depois de 11 músicas</option>
                              <option value="12">Depois de 12 músicas</option>
                              <option value="13">Depois de 13 músicas</option>
                              <option value="14">Depois de 14 músicas</option>
                              <option value="15">Depois de 15 músicas</option>
                              <option value="16">Depois de 16 músicas</option>
                              <option value="17">Depois de 17 músicas</option>
                              <option value="18">Depois de 18 músicas</option>
                              <option value="19">Depois de 19 músicas</option>
                              <option value="20">Depois de 20 músicas</option>
                              <option value="21">Depois de 21 músicas</option>
                              <option value="22">Depois de 22 músicas</option>
                              <option value="23">Depois de 23 músicas</option>
                              <option value="24">Depois de 24 músicas</option>
                              <option value="25">Depois de 25 músicas</option>
                              <option value="26">Depois de 26 músicas</option>
                              <option value="27">Depois de 27 músicas</option>
                              <option value="28">Depois de 28 músicas</option>
                              <option value="29">Depois de 29 músicas</option>
                              <option value="30">Depois de 30 músicas</option>
                            </select>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label for="indexHoraCerta" class="control-label col-sm-4 col-md-3">Index Hora Certa:</label>
                          <div class="col-sm-6 col-md-8">
                            <select class="form-control" id="indexHoraCerta" name="indexHoraCerta">
                              <option value="0" >Não incluir Hora Certa</option>
                              <option value="3" >Depois de 3 músicas</option>
                              <option value="6" >Depois de 6 músicas</option>
                              <option value="9" >Depois de 9 músicas</option>
                              <option value="12">Depois de 12 músicas</option>
                              <option value="15">Depois de 15 músicas</option>
                              <option value="18">Depois de 18 músicas</option>
                              <option value="21">Depois de 21 músicas</option>
                              <option value="24">Depois de 24 músicas</option>
                              <option value="27">Depois de 27 músicas</option>
                              <option value="30">Depois de 30 músicas</option>
                            </select>
                          </div>
                        </div>
                        
                        <div class="spacer-vertical20"></div>
  
                        <div class="row">
                          <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <a class="btn btn-primary" id="btnSalvarBloco" href="#">Salvar Alterações</a>
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
                <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/programacoes/view" id="btnAvançado"><i class="fa fa-list-ol"></i> Configurar Programação Musical</a>              
              </div>
            </div>
          </div>

          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
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
            url: '${context}/ambientes/${idAmbiente}/blocos',
            dataType: 'json',
            data:  JSON.stringify( $('#ambiente-bloco-form').serializeJSON() )
            
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

    var getDados = function()
    {
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/blocos',
            dataType: 'json'
        }).done( function(json) {
            
            removeErros( $('#ambiente-bloco-form') );
             $('#ambiente-bloco-form').populate(json);
            jump('ambiente-bloco-form');
        });
    }

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        $('#btnSalvarBloco').on('click', salvar);
        
        getDados();
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />