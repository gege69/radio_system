<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en" style="height: 100%">
  <head>
  
    <jsp:include page="/WEB-INF/views/metaAndIcon.jsp" />
  
    <title>Radio System</title>
    
    <!-- O SIMULADOR TEM UM CSS DIFERENTE -->
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
              
              <c:if test="${configuracao != null && configuracao.controleComerciais}">
                <li><a href="#"  id="btn-config-comerciais">Comerciais</a></li>
              </c:if>
              
              <c:if test="${configuracao != null && configuracao.controleInstitucionais}">
                <li><a href="#"  id="btn-config-inst">Institucionais</a></li>
              </c:if>
              
              <c:if test="${configuracao != null && configuracao.controleProgrametes}">
                <li><a href="#"  id="btn-config-programetes">Programetes</a></li>
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
          </h3>
          

          <div class="panel panel-default">
            <div class="panel-heading">
              
            </div>
            <div class="panel-body">
            
              <div class="spacer-vertical40"></div>
               
              <div class="row row-centered" >
                <img alt="logo" src="${context}/api/ambientes/${idAmbiente}/logomarca" id="marca" class="logotipoimagem" >
              </div>
              
              <div class="spacer-vertical40"></div>
            
            </div>
          
          </div>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-offset-10 col-sm-offset-9 col-xs-offset-7">
<!--         <a class="btn btn-default" href=${context}/principal>Painel Gerencial</a> -->
      </div>
    </div>
    
  </div> <!-- /container -->
  
  <div class="footer footer-player navbar-fixed-bottom">

    <div class="row row-centered" style="margin-top: 11px;">
    
      <div class="col-lg-4 col-md-4 col-sm-3 col-xs-12">
        <span id="nome-musica"></span>
        <div class="spacer-vertical20"></div>
      </div>
      
      <div class="col-md-2 col-sm-2 col-xs-12 col-centered" >
      

        <div class="player" id="player1" style="display : none;">
            <audio controls>
                Audio files
                <source src="https://cdn.selz.com/plyr/1.0/logistics-96-sample.ogg" type="audio/ogg">
        
                Fallback for browsers that don't support the <audio> element
                <a href="https://cdn.selz.com/plyr/1.0/logistics-96-sample.mp3">Download</a>
            </audio>
        </div>

        <div class="player" id="player2" style="display : none;">
            <audio controls>
                Audio files
                <source src="https://cdn.selz.com/plyr/1.0/logistics-96-sample.ogg" type="audio/ogg">
        
                Fallback for browsers that don't support the <audio> element
                <a href="https://cdn.selz.com/plyr/1.0/logistics-96-sample.mp3">Download</a>
            </audio>
        </div>
      
<!--         <div style="display: none;"> -->
<!--           <audio id="player1" type="audio/mp3" controls="controls">    -->
<!--           </audio> -->
<!--           <audio id="player2" type="audio/mp3" controls="controls">    -->
<!--           </audio> -->
<!--         </div> -->
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
      
      <div class="col-lg-4 col-md-4 col-sm-3 col-xs-12 pull-right">
        <i class="fa fa-2x fa-volume-up" style="margin-right: 15px;"></i>
        <input id="slider2" class="campo-slider" data-slider-id='ex2Slider' type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="100"/>
        
      </div>
    </div>
  </div>
  
  
<div class="my-modal-base">
  <div class="my-modal-cont"></div>
</div>
  

  
<jsp:include page="/WEB-INF/views/scripts.jsp" />

<link rel="stylesheet" href="https://cdn.plyr.io/1.3.7/plyr.css">
<script src="https://cdn.plyr.io/1.3.7/plyr.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script> 

<link href="${context}/css/bootstrap-slider.min.css" rel="stylesheet">

<script src="${context}/js/required/bootstrap-slider.min.js" charset="UTF-8"></script>  

<script src="${context}/js/player-web/player-web-plyr.js" charset="UTF-8"></script>
  
<jsp:include page="/WEB-INF/views/bottom.jsp" />
