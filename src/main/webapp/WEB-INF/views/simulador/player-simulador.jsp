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
<%--     <link href="${context}/css/bootstrap.css" rel="stylesheet"> --%>

    <!-- Custom styles for this template -->
    <jsp:include page="/WEB-INF/views/customStyles.jsp" />
    
    
    <jsp:include page="/WEB-INF/views/scripts.jsp" />
    
    <script src="${context}/mediaelement/mediaelement-and-player.js"></script>
    <link rel="stylesheet" href="${context}/mediaelement/mediaelementplayer.css" />
    
    <link href="${context}/css/bootstrap-slider.min.css" rel="stylesheet">
    <script src="${context}/js/bootstrap-slider.min.js" charset="UTF-8"></script>
      
  </head>    
  

  <body class="player-body" role="document">

  <nav class="navbar navbar-static-top">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="fa fa-bars"></span>
        </button>
        <a class="navbar-brand" href="${context}/ambientes/${idAmbiente}/view">${nome}</a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          
          <li class="dropdown" id="menu-pesquisas">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" id="link-menu-pesquisas">Chamadas <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="#"  id="menu-palavras">Proprietário de Veículo</a></li>
              <li><a href="#"  id="menu-codigo-ncm">Funcionários</a></li>
              <li><a href="#"  id="menu-sumario">Chamada Instantânea</a></li>
              <li><a href="#"  id="menu-posicao">Horóscopo</a></li>
              <li><a href="#"  id="menu-naladi">Comerciais</a></li>
              <li><a href="#"  id="menu-tratamento">Institucionais</a></li>
            </ul>
          </li>
          
          <li class="dropdown" id="menu-pesquisas">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" id="link-menu-pesquisas">Configurações <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="#"  id="menu-palavras">Gêneros</a></li>
              <li><a href="#"  id="menu-codigo-ncm">Blocos</a></li>
              <li><a href="#"  id="menu-sumario">No-Break</a></li>
              <li><a href="#"  id="menu-posicao">Downloads</a></li>
            </ul>
          </li>          
          
          <li><a href="#" id="menu-retrospectiva">Relatórios</a></li>
          
          <li><a href="#" id="menu-retrospectiva">Atendimento</a></li>          
          
        </ul>
        
      </div><!--/.nav-collapse -->
    </div>
  </nav>
  
  <div class="container wrapper">
    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Player Protótipo<br/>
            <small></small>
          </h3>
          

          <div class="panel panel-default">
            <div class="panel-heading">
              
            </div>
            <div class="panel-body">
            
              <div class="spacer-vertical40"></div>
               
              <div class="row row-centered" >
                <img alt="logo" src="${context}/ambientes/${idAmbiente}/logomarca" id="marca">
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
        Nome de música bem grande bem grande bem grande bem grande bem grande
        <div class="spacer-vertical20"></div>
      </div>
      
      <div class="col-md-2 col-sm-2 col-xs-12 col-centered" >
        <div style="display: none;">
          <audio id="player2" type="audio/mp3" controls="controls">   
          </audio>
        </div>
        <table style=" margin: 0 auto;">
          <tr>
<!--             <td> -->
<!--               <a class="btn btn-default" href="#" role="button"> -->
<!--                 <i class="fa fa-backward"></i> -->
<!--               </a> -->
<!--             </td> -->
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
            <td>
              <a class="btn btn-default" href="#" id="botao-next" role="button">
                <i class="fa fa-forward"></i>
              </a>
            </td>
          </tr>
        </table>
        <div class="spacer-vertical20"></div>
      </div>
      
      <div class="col-lg-4 col-md-4 col-sm-3 col-xs-12 pull-right">
<!--         <span id="slider2ValLabel" style="margin-left: 20px;">Valor: <span id="slider2Val"></span></span> -->
        <input id="slider2" class="campo-slider" data-slider-id='ex2Slider' type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="1"/>
        
      </div>
    </div>
  </div>
  
  
<script>


    var player = new MediaElementPlayer('#player2');

    var stop = function(){
        player.pause();
    };
    
    var play = function( next ){

        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/api/ambientes/${idAmbiente}/transmissoes/live',
            dataType: 'json',
        }).done( function( content ){
            
            if ( content.link != null && content.link != '' )
            {
                console.log(content.link);
                
                player.setSrc( content.link );
                player.play();
            }

        });
    };

    var next = function(){

        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/api/ambientes/${idAmbiente}/transmissoes/live/next',
            dataType: 'json',
        }).done( function( content ){
            
            if ( content.link != null && content.link != '' )
            {
                console.log(content.link);
                
                player.setSrc( content.link );
                player.play();
            }

        });
        
    };
    
    player.media.onended = function(){ next(); };
    
    $(document).ready(function() {

        player.pause();

        $(".campo-slider").bootstrapSlider({
            ticks: [0, 20, 40, 60, 80, 100],
            ticks_labels: ['0', '20', '40', '60', '80', '100'],
            ticks_snap_bounds: 3
        });
        
        $('#botao-stop').click( function(){
            stop();
        });
        
        $('#botao-play').click( function(){
            play();
        });
        
        $('#botao-next').click( function(){
            next();
        });
        
    });


</script>
  
     
  
<jsp:include page="/WEB-INF/views/bottom.jsp" />