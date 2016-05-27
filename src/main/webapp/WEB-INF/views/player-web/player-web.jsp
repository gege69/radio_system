<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en" style="height: 100%">
  <head>
  
    <jsp:include page="/WEB-INF/views/metaAndIcon.jsp" />
  
    <title>Radio System</title>
    
    <!-- O player-web TEM UM CSS DIFERENTE -->
    <link href="${context}/css/bootstrap-themes/slate/bootstrap.css" rel="stylesheet">

    <jsp:include page="/WEB-INF/views/customStyles.jsp" />

    <script type="text/javascript">
       var contextPath = "${pageContext.request.contextPath}";
    </script>
    
      
  </head>    
  

  <body class="player-body" role="document">


  <nav class="navbar navbar-static-top">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="fa fa-bars"></span>
        </button>
        <a class="navbar-brand" href="#">
          ${nome}
        </a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          
          <li class="dropdown" id="menu-pesquisas">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" id="link-menu-pesquisas">Chamadas <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
            
              <c:if test="${configuracao != null && configuracao.chamVeiculo}">
                <li><a href="#"  id="btn-chamada-veiculo">Proprietário de Veículo</a></li>
              </c:if>
              
              <c:if test="${configuracao != null && configuracao.chamFuncionarios}">
                <li><a href="#"  id="btn-chamada-funcionarios">Funcionários</a></li>
              </c:if>
              
              <c:if test="${configuracao != null && configuracao.chamInstantanea}">
                <li><a href="#"  id="btn-chamada-inst">Chamada Instantânea</a></li>
              </c:if>

              <c:if test="${configuracao != null && configuracao.horoscopo}">                
                <li><a href="#"  id="btn-horoscopo">Horóscopo</a></li>
              </c:if>
              
            </ul>
          </li>
          
          <li class="dropdown" id="menu-pesquisas">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" id="link-menu-pesquisas">Configurações <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              
              <c:if test="${configuracao != null && configuracao.selecaoGenero}">
                <li><a href="#"  id="btn-generos">Gêneros</a></li>
              </c:if>

              <c:if test="${configuracao != null && configuracao.controleBlocos}">              
                <li><a href="#"  id="btn-blocos">Blocos</a></li>
              </c:if>
              
              <c:if test="${configuracao != null && configuracao.menuDownloads}">
                <li><a href="#"  id="btn-downloads">Downloads</a></li>
              </c:if>
            </ul>
          </li>          

          <c:if test="${configuracao != null && configuracao.relatoriosMidia}">          
            <li><a href="#" id="btn-relatorios">Relatórios</a></li>
          </c:if>

          <c:if test="${configuracao != null && configuracao.atendimento}">          
            <li><a href="#" id="btn-atendimento">Atendimento</a></li>
          </c:if>          

        </ul>

        <ul class="nav navbar-nav" style="float: right">
          <li><a href="#" class="navbar-nav pull-right" id="btnFechar">Fechar</a>
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" id="formLogout" method="post">
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form> 
          </li>

        </ul>
        
      </div><!--/.nav-collapse -->
    </div>
  </nav>
  
  <div class="container">
    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Player<br/>
            <small></small>
            
            <input type="hidden" id="idAmbiente" value="${idAmbiente}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          </h3>
          

          <div class="panel panel-default">
            <div class="panel-heading">
              
            </div>
            <div class="panel-body">
            
              <div class="spacer-vertical40"></div>
               
              <div class="row row-centered" >
                <img alt="logo" src="${context}/api/ambientes/${idAmbiente}/logomarca" id="marca" class="logotipoimagem" >
              </div>

              <div class="row row-centered">
                <div class="col-lg-12 col-md-12 col-sm-12 col-centered" >
                  <div class="plyr player" id="player1" style="display : none;">
                      <audio controls>
                          <source src="" type="audio/ogg">
                      </audio>
                  </div>

                  <div class="plyr player" id="player2" style="display : none;">
                      <audio controls>
                          <source src="" type="audio/ogg">
                      </audio>
                  </div>
                
                  <div class="plyr player" id="playerDing" style="display : none;">
                      <audio controls>
                          <source src="${context}/static_sound/ding.mp3" type="audio/ogg">
                      </audio>
                  </div>
                </div>
              </div>


              
              <div class="spacer-vertical40"></div>
            
            </div>
          
          </div>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-offset-10 col-sm-offset-9 col-xs-offset-7">
      </div>
    </div>
    
  </div> <!-- /container -->
  
  <div class="footer footer-player navbar-fixed-bottom">

    <div class="row row-centered" style="margin-top: 11px;">
    
      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
          <a href="#" id="mic">
            <i class="fa fa-lg fa-microphone-slash player-mic-off" id="icon-mic"></i>
          </a>
        </div>
        <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
          <span id="nome-musica"></span>
          <div class="spacer-vertical20"></div>
        </div>
      </div>
      
      <div class="col-md-2 col-sm-2 col-xs-12 col-centered" >
        <table style=" margin: 0 auto;">
          <tr>
            <td>
              <a class="btn btn-default" href="#" id="botao-stop" role="button">
                <i class="fa fa-stop"></i>
              </a>
            </td>
            
            <td>
              <a class="btn btn-default" href="#" id="botao-play" role="button">
                <i class="fa fa-play"></i>
              </a>
            </td>
            
            <c:if test="${configuracao != null && configuracao.avancarRetornar}">
              <td>
                <a class="btn btn-default" href="#" id="botao-next" role="button">
                  <i class="fa fa-forward"></i>
                </a>
              </td>
            </c:if>
          </tr>
        </table>
        <div class="spacer-vertical20"></div>
      </div>
      
      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 pull-right">
        <i class="fa fa-2x fa-volume-up" style="margin-right: 15px;"></i>
        <input id="slider2" class="campo-slider" data-slider-id='ex2Slider' type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="100"/>
        
      </div>
    </div>
  </div>
  
  
<div class="my-modal-base">
  <div class="my-modal-cont"></div>
</div>
  

<div id="myModalBlocos" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Blocos</h3>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-lg-12">
            <form action="#" 
                  id="ambiente-bloco-form" 
                  class="form-horizontal"
                  method="POST">
                  
              <input type="hidden" id="idAmbienteBloco" name="idAmbiente" value="${idAmbiente}">
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

              <div class="form-group">
                <label for="indexHoroscopo" class="control-label col-sm-4 col-md-3">Index Horóscopo:</label>
                <div class="col-sm-6 col-md-8">
                  <select class="form-control" id="indexHoroscopo" name="indexHoroscopo">
                    <option value="0" >Não incluir horóscopo</option>
                  </select>
                </div>
              </div>

            </form>
          </div>
          
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Fechar</button>
        <button class="btn btn-primary" id="btnSalvarBloco" data-dismiss="modal">Salvar</button>
      </div>
    </div>
  </div>
</div>
  
<div id="myModalChamadaFuncionarios" class="modal fade" tabindex="-1" role="dialog" style="display : none;">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Chamada de Funcionários</h3>
      </div>
      <div class="modal-body">
        <div class="row">
        
          <div class="col-lg-12 col-md-12">
            <div class="form-group">
              <label for="file">Funcionário:</label>
              <select class="form-control" id="funcionario" name="funcionario">
              </select>
            </div>
          </div>
          <div class="col-lg-12 col-md-12">
            <div class="form-group">
              <label for="file">Frase/Chamada:</label>
              <select class="form-control" id="frase" name="frase">
              </select>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Fechar</button>
        <button class="btn btn-primary" id="btnTocaChamadaFuncionarios">Tocar</button>
      </div>
    </div>
  </div>
</div>
  
  
<div id="myModalChamadaInst" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Chamadas Instantâneas</h3>
      </div>
      <div class="modal-body">
        <div class="row">
        
          <div class="col-lg-12 col-md-12">
            <div class="form-group">
              <label for="file">Chamadas Instantâneas:</label>
              <select class="form-control" id="chamada_inst" name="chamada_inst">
              </select>
            </div>
          </div>

        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Fechar</button>
        <button class="btn btn-primary" id="btnTocaChamadaInst">Tocar</button>
      </div>
    </div>
  </div>
</div>



<div id="myModalChamadaVeiculos" class="modal fade" tabindex="-1" role="dialog" style="display : none;">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Chamada Proprietário de Veículo</h3>
      </div>
      <div class="modal-body">
        <div class="row">

          <div class="col-lg-12 col-md-12"> 
            <form action="#" id="formtocar" class="form-horizontal">

              <div class="form-group">
                <label for="login" class="control-label col-lg-2 col-md-2 col-sm-2">Frase Inicial</label>
                <div class="col-lg-8 col-md-8 col-sm-10">
                  <select class="form-control" id="comboFraseInicial" name="veic_frase_ini">
                  </select>
                </div>
              </div> 
              
              <div class="form-group">
                <label for="login" class="control-label col-md-2 col-sm-2">Marca</label>
                <div class="col-lg-7 col-md-7 col-sm-9">
                  <select class="form-control" id="comboMarca" name="veic_marca">
                  </select>
                </div>
              </div> 
              
              <div class="form-group">
                <label for="login" class="control-label col-md-2 col-sm-2">Modelo</label>
                <div class="col-lg-7 col-md-7 col-sm-9">
                  <select class="form-control" id="comboModelo" name="veic_modelo">
                  </select>
                </div>
              </div> 
              
              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Cor</label>
                <div class="col-lg-7 col-md-7 col-sm-9">
                  <select class="form-control" id="comboCor" name="veic_cor">
                  </select>
                </div>
              </div> 
              
              
              <div class="form-group">
                <label for="login" class="control-label col-md-2 col-sm-2 col-xs-12">Placa</label>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
                  <select class="form-control" id="comboPlacaLetra1" name="veic_placa_letra">
                  </select>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
                  <select class="form-control" id="comboPlacaLetra2" name="veic_placa_letra">
                  </select>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
                  <select class="form-control" id="comboPlacaLetra3" name="veic_placa_letra">
                  </select>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
                  <select class="form-control" id="comboPlacaNumero1" name="veic_placa_numero">
                  </select>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
                  <select class="form-control" id="comboPlacaNumero2" name="veic_placa_numero">
                  </select>
                </div>
              </div> 
              
              <div class="form-group">
                <label for="login" class="control-label col-lg-2 col-sm-2 col-md-2">Frase Final</label>
                <div class="col-lg-8 col-md-8 col-sm-10">
                  <select class="form-control" id="comboFraseFinal" name="veic_frase_fim">
                  </select>
                </div>
              </div>
              
            </form>
          </div>


        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Fechar</button>
        <button class="btn btn-primary" id="btnTocarChamadaVeiculos">Tocar</button>
      </div>
    </div>
  </div>
</div>


<div id="myModalGeneros" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
      
        <div class="row" id="alertArea">
        </div>
      
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Gêneros</h3>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-lg-12 col-md-12">
            <form action="#" id="ambiente-generos-form" method="POST">
              <input type="hidden" id="idAmbiente" value="${idAmbiente}">
              <div class="form-inline">
                <div class="container col-lg-12 col-md-12" id="view-container">
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Fechar</button>
        <button class="btn btn-primary" id="btnSalvarGeneros" >Salvar</button>
      </div>
    </div>
  </div>
</div>

<script id="viewTmpl" type="text/x-jsrender">
    
      <div class="checkbox col-lg-6 col-md-6 col-sm-6 col-xs-12">
        <label>
          <input type="checkbox" class="checkbox-genero" id="genero-{{:idGenero}}" name="genero[idGenero]" value="{{:idGenero}}"> {{:descricao}}
        </label>
      </div>
      
</script>  


<div id="myModalConversas" class="modal fade higherWider" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Mensagens</h3>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="panel panel-default">
            <div class="panel-body" id="container-conversas"> 
              <jsp:include page="/WEB-INF/views/mensagens/template-conversas.jsp" />
            </div>
          </div>  
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>
  
<jsp:include page="/WEB-INF/views/scripts.jsp" />


<link rel="stylesheet" href="https://cdn.plyr.io/1.6.16/plyr.css">
<script src="https://cdn.plyr.io/1.6.16/plyr.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script> 

<link href="${context}/css/bootstrap-slider.min.css" rel="stylesheet">
<script src="${context}/js/required/bootstrap-slider.min.js" charset="UTF-8"></script>  

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.6.2/jquery.serializejson.min.js" defer></script>
<script src="${context}/js/required/jquery.populate.js" defer></script>
  
<script src="${context}/js/player-web/modal-blocos.js" charset="UTF-8" defer></script>
<script src="${context}/js/player-web/modal-chamada-funcionarios.js" charset="UTF-8" defer></script>
<script src="${context}/js/player-web/modal-chamada-instantanea.js" charset="UTF-8" defer></script>
<script src="${context}/js/player-web/modal-chamada-veiculos.js" charset="UTF-8" defer></script>
<script src="${context}/js/player-web/modal-generos.js" charset="UTF-8" defer></script>

<!-- basicamente necessários para o "Conversas" -->
<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">
<script src="${context}/js/required/bootstrap-table/bootstrap-table.js" defer></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8" defer></script>

<link href="${context}/css/bootstrap-datepicker3.css" rel="stylesheet">
<script src="${context}/js/required/bootstrap-datepicker.min.js" defer></script>
<script src="${context}/js/required/bootstrap-datepicker.pt-BR.min.js" defer></script>
<script src="${context}/js/required/jsrender.min.js" defer></script>
<script src="${context}/js/gerenciador/conversas.js" defer></script>
<!-- basicamente necessários para o "Conversas" -->


<script src="${context}/js/player-web/player-web-plyr.js" charset="UTF-8"></script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />
