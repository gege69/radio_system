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
              
              <c:if test="${configuracao != null && configuracao.nobreak}">
                <li><a href="#"  id="btn-nobreak">No-Break</a></li>
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
          <h3>Simulador do Player<br/>
            ${configuracao.chamVeiculo}
            <small></small>
          </h3>
          

          <div class="panel panel-default">
            <div class="panel-heading">
              
            </div>
            <div class="panel-body">
            
              <div class="spacer-vertical40"></div>
               
              <div class="row row-centered" >
                <img alt="logo" src="${context}/ambientes/${idAmbiente}/logomarca" id="marca" style="height: 200px;">
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
        <div style="display: none;">
          <audio id="player2" type="audio/mp3" controls="controls">   
          </audio>
        </div>
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
  
  
<script>

    var player = new MediaElementPlayer('#player2');
    
    var volumeMusicas = 100;
    
    var volumeChamadas = 100;
    
    var volumeComerciais = 100;
    
    var volumeGeral = 100;
    
    var volumeIndividual = false;

    var stop = function(){
        player.pause();
    };
    
    var alteraVolume = function( valor ) {

        var volume = 1;
        
        if ( valor != null && valor >= 0 && valor <= 100 )
            volume = valor / 100;
        
        player.media.volume = volume;
    };
    
    var determinaVolume = function( json ){

        if ( json != null  )
        {
            if ( volumeIndividual && json.categoria != null )    
            {
                var volume = 0;
                
                if ( json.categoria.codigo == 'musica' )
                    volume = volumeMusicas;
                
                if ( json.categoria.codigo == 'chamada_inst' ||
                     json.categoria.codigo == 'chamada_func_nome' ||
                     json.categoria.codigo == 'chamada_func_frase'  )
                    volume = volumeChamadas;
                
                if ( json.categoria.codigo == 'comercial' ||
                     json.categoria.codigo == 'vinheta' ||
                     json.categoria.codigo == 'inst' ||
                     json.categoria.codigo == 'programete'  )
                    volume = volumeComerciais;
                
                alteraVolume( volume );
                $('#slider2').bootstrapSlider('setValue', volume );
            }
            else
            {
                alteraVolume( volumeGeral );
                $('#slider2').bootstrapSlider('setValue', volumeGeral );
            }
        }
        
    }
    
    var play = function(){

        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/api/ambientes/${idAmbiente}/transmissoes/live',
            dataType: 'json',
        }).done( function( content ){
            
            if ( content.midia != null )
            {
                if ( content.midia.title == null || content.midia.title == '' )
                    $('#nome-musica').html( content.midia.nome );
                else
                    $('#nome-musica').html( content.midia.title );
            }
            
            if ( content.link != null && content.link != '' )
            {
                console.log(content.link);
                
                determinaVolume( content );
                
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
            
            if ( content.midia != null )
            {
                if ( content.midia.title == null || content.midia.title == '' )
                    $('#nome-musica').html( content.midia.nome );
                else
                    $('#nome-musica').html( content.midia.title );
            }
            
            if ( content.link != null && content.link != '' )
            {
                console.log(content.link);
                
                determinaVolume( content );
                
                player.setSrc( content.link );
                player.play();
            }

        });
        
    };
    
    player.media.onended = function(){ next(); };
    
    
    var abreModal = function( url ){ 
        var url = "${context}/ambientes/${idAmbiente}/simulacoes" + url;
        $('.my-modal-cont').load(url,function(result){
            $('#myModal').modal({
                show:true, 
                backdrop: 'static',              
                keyboard: false
            });
        });
    };
    
    var getConfiguracoes = function(){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/configuracoes',
            dataType: 'json'
        }).done( function(json) {
            
            if ( json != null )
            {
                if ( json.autoplay )
                    play();
                
                if ( json.controleVolumeIndividual )
                {
                    volumeIndividual = true;
                    
                    volumeMusicas = json.volumeMusicas;
                    volumeChamadas = json.volumeChamadas;
                    volumeComerciais = json.volumeComerciais;
                }
                else
                {
                    volumeIndividual = false;
                    
                    volumeMusicas = json.volumeGeral;
                    volumeChamadas = json.volumeGeral;
                    volumeComerciais = json.volumeGeral;                
                    
                    alteraVolume(json.volumeGeral);
                }
                volumeGeral = json.volumeGeral;
            }
            else 
            {
                volumeGeral = 100;
            }
            
        });
    }
    
    
    var registraModal = function( element, url ){
        $(element).click( function() {
            abreModal( url ); 
        });
    }
    
    
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
        
        $(".campo-slider").on("slideStop", function(slideEvt) {
            alteraVolume(slideEvt.value);
        });

        registraModal('#btn-chamada-veiculo', "/chamadaveiculos/view");
        registraModal('#btn-chamada-funcionarios', "/chamadafuncionarios/view");
        registraModal('#btn-chamada-inst', "/chamadainst/view");
        registraModal('#btn-horoscopo', "/horoscopo/view");
        registraModal('#btn-config-comerciais', "/configcomerciais/view");
        registraModal('#btn-config-inst', "/configinst/view");
        
        registraModal('#btn-generos', "/generos/view");
        registraModal('#btn-blocos', "/blocos/view");
        registraModal('#btn-nobreak', "/nobreak/view");
        registraModal('#btn-downloads', "/downloads/view");
        registraModal('#btn-relatorios', "/relatorios/view");
        registraModal('#btn-atendimento', "/atendimento/view");
        
        getConfiguracoes();
        
    });


</script>
  
     
  
<jsp:include page="/WEB-INF/views/bottom.jsp" />
