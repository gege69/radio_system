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
                          <label for="qtdMusicas" class="control-label col-sm-4 col-md-3">Músicas em sequência:</label>
                          <div class="col-sm-6 col-md-8">
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
                          <label for="posicaoComercial" class="control-label col-sm-4 col-md-3">Posicao Comercial:</label>
                          <div class="col-sm-6 col-md-8">
                            <select class="form-control" id="posicaoComercial" name="posicaoComercial">
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
                          <label for="qtdComerciais" class="control-label col-sm-4 col-md-3">Comerciais em sequência:</label>
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
                            </select>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label for="indexProgrametes" class="control-label col-sm-4 col-md-3">Index Programetes:</label>
                          <div class="col-sm-6 col-md-8">
                            <select class="form-control" id="indexProgrametes" name="indexProgrametes">
                              <option value="0" >Não incluir programetes</option>
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



<script src="${context}/js/required/jquery.serializejson.js"></script>
<script src="${context}/js/required/jquery.populate.js"></script>

<script src="${context}/js/ambiente/blocos.js"  charset="UTF-8"></script>

<jsp:include page="/WEB-INF/views/bottom.jsp" />