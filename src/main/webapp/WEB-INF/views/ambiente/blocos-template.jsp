<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
                          <label for="posicaoSilencio" class="control-label col-lg-4 col-sm-4 col-md-4">Posicao Silêncio:</label>
                          <div class="col-sm-8 col-md-8 col-lg-8">
                            <select class="form-control controleBloco" id="posicaoSilencio" name="posicaoSilencio">
                              <option value="NAO_INCLUIR" selected="selected">Não incluir silêncio</option> 
                              <option value="ANTES_MUSICA">Antes das Músicas</option>
                              <option value="DEPOIS_MUSICA">Depois das Músicas</option>
                              <option value="ANTES_BLOCO_COMERCIAL">Antes do bloco Comercial</option>
                              <option value="DEPOIS_BLOCO_COMERCIAL">Depois do bloco Comercial</option>
                              <option value="ANTES_INSTITUCIONAL">Antes do Institucional</option> 
                              <option value="DEPOIS_INSTITUCIONAL">Depois do Institucional</option> 
                              <option value="ANTES_PROGRAMETE">Antes do Programete</option> 
                              <option value="DEPOIS_PROGRAMETE">Depois do Programete</option>
                            </select>
                          </div>
                        </div>


                        <div class="form-group">
                          <label for="tamanhoSilencio" class="control-label col-lg-4 col-sm-4 col-md-4">Tamanho de cada silêncio:</label>
                          <div class="col-sm-8 col-md-8 col-lg-8">
                            <select class="form-control controleBloco" id="tamanhoSilencio" name="tamanhoSilencio">
                              <option value="NENHUM" >Nenhum silêncio</option>
                              <option value="_30_SEGUNDOS" >30 segundos</option>
                              <option value="_60_SEGUNDOS" >60 segundos</option>
                              <option value="_90_SEGUNDOS" >90 segundos</option>
                              <option value="_120_SEGUNDOS" >120 segundos</option>
                              <option value="_150_SEGUNDOS" >150 segundos</option>
                              <option value="_180_SEGUNDOS" >180 segundos</option>
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

                      </form>
                    </div>
                    
                  </div>