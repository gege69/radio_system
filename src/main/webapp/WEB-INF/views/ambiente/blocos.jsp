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
              <h3><i class="${func.sizeSmall} ${func.classesIcone}">${func.icone}</i> Configurar Blocos</h3>
              <h4><small>Configuração de blocos de áudio de ${nome}</small></h4>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-7 col-md-8 col-sm-8">
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
                          <label for="qtdMusicas" class="control-label col-lg-4 col-sm-4 col-md-4">Músicas em sequência:</label>
                          <div class="col-sm-8 col-md-8 col-lg-8">
                            <select class="form-control" id="qtdMusicas" name="qtdMusicas">
                              <option value="0" >Nenhuma Música</option>
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
                          <label for="posicaoVinheta" class="control-label col-lg-4 col-sm-4 col-md-4">Posicao Vinheta:</label>
                          <div class="col-sm-8 col-md-8 col-lg-8">
                            <select class="form-control controleBloco" id="posicaoVinheta" name="posicaoVinheta">
                              <option value="ANTES_CADA_MUSICA">Antes de cada música</option>
                              <option value="ANTES_BLOCO_COMERCIAL">Antes do bloco Comercial</option>
                              <option value="DEPOIS_BLOCO_COMERCIAL">Depois do bloco Comercial</option>
                              <option value="NAO_INCLUIR">Não incluir vinhetas</option> 
                            </select>
                          </div>
                        </div>


                        <div class="form-group">
                          <label for="posicaoComercial" class="control-label col-lg-4 col-sm-4 col-md-4">Posicao Comercial:</label>
                          <div class="col-sm-8 col-md-8 col-lg-8">
                            <select class="form-control controleBloco" id="posicaoComercial" name="posicaoComercial">
                              <option value="DEPOIS_MUSICAS">Depois das Músicas</option>
                              <option value="ANTES_INSTITUCIONAL">Antes do Institucional</option>
                              <option value="DEPOIS_INSTITUCIONAL">Depois do Institucional</option>
                              <option value="ANTES_PROGRAMETE">Antes do Programete</option>
                              <option value="DEPOIS_PROGRAMETE">Depois do Programete</option>
                              <option value="NAO_INCLUIR">Não incluir comerciais</option>
                            </select>
                          </div>
                        </div>
                        
                        
                        <div class="form-group">
                          <label for="qtdComerciais" class="control-label col-lg-4 col-sm-4 col-md-4">Comerciais em sequência:</label>
                          <div class="col-sm-8 col-md-8 col-lg-8">
                            <select class="form-control controleBloco" id="qtdComerciais" name="qtdComerciais">
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
                          <label for="indexInstitucionais" class="control-label col-lg-4 col-sm-4 col-md-4">Index Institucionais:</label>
                          <div class="col-sm-8 col-md-8 col-lg-8">
                            <select class="form-control controleBloco" id="indexInstitucionais" name="indexInstitucionais">
                              <option value="0" >Não incluir institucionais</option>
                            </select>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label for="indexProgrametes" class="control-label col-lg-4 col-sm-4 col-md-4">Index Programetes:</label>
                          <div class="col-sm-8 col-md-8 col-lg-8">
                            <select class="form-control controleBloco" id="indexProgrametes" name="indexProgrametes">
                              <option value="0" >Não incluir programetes</option>
                            </select>
                          </div>
                        </div>

                        <div class="form-group">
                          <label for="indexOpcionais" class="control-label col-lg-4 col-sm-4 col-md-4">Index Opcionais:</label>
                          <div class="col-sm-8 col-md-8 col-lg-8">
                            <select class="form-control controleBloco" id="indexOpcionais" name="indexOpcionais">
                              <option value="0" >Não incluir opcionais</option>
                            </select>
                          </div>
                        </div>


                        <div class="form-group">

                          <label for="indexOpcionais" class="control-label col-lg-4 col-sm-4 col-md-4">Opcionais:</label>
                          
                          <div class="col-sm-8 col-md-8 col-lg-8" id="containerOpcionais">
                          </div>

                        </div>


                        <div class="spacer-vertical20"></div>
  
                        <div class="row">
                          <div class="col-lg-6 col-md-7 col-sm-6 col-xs-6">
                            <a class="btn btn-primary" id="btnSalvarBloco" href="#">Salvar Alterações</a>

                          </div>
                        </div>                     
                          
                      </form>
                    </div>
                    
                  </div>
                  
                </div>
              </div>
            </div>

<!--             <div class="col-lg-7 col-md-8 col-sm-8"> -->
            <div class="col-lg-5 col-md-4 col-sm-4 hidden-xs">
              <div class="panel panel-default">
                <div class="panel-body">
                  <h4>Exemplo: <span id="indicadorSalvo" style="display: none;" class="label label-success">Salvo</span></h4>
                  <div class="row">
                    <div class="col-lg-12">
                      <div id="containerExemplo" style="margin-left:10px;"></div>
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
                <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/programacoes/view" id="btnAvançado">
                  <i class="material-icons md-18">grid_on</i> Configurar Programação Musical
                </a>
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


<script src="https://cdnjs.cloudflare.com/ajax/libs/jsrender/0.9.78/jsrender.min.js"></script>

<script id="viewTmplOpcionais" type="text/x-jsrender"  charset="UTF-8">
  <div class="checkbox col-lg-6 col-md-6 col-sm-6 col-xs-12">
    <label>
      <input type="checkbox" class="checkbox-opcional checkBloco" id="opcional-{{:idOpcional}}" name="opcionais[][idOpcional]" value="{{:idOpcional}}"> {{:nome}}
    </label>
  </div> 
</script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.7.2/jquery.serializejson.min.js"></script>
<script src="${context}/js/required/jquery.populate.min.js"></script>

<script src="${context}/js/ambiente/blocos.js"  charset="UTF-8"></script>

<jsp:include page="/WEB-INF/views/bottom.jsp" />