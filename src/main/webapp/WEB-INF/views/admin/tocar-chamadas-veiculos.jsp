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
              <h3>Tocar Chamadas de Veículos </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              <form action="#" id="formtocar" class="form-horizontal">

                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2 col-sm-3">Frase Inicial</label>
                  <div class="col-lg-4 col-md-4 col-sm-6">
                    <select class="form-control" id="comboFraseInicial" name="veic_frase_ini">
                    </select>
                  </div>
                </div> 
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2 col-sm-3">Marca</label>
                  <div class="col-lg-4 col-md-4 col-sm-6">
                    <select class="form-control" id="comboMarca" name="veic_marca">
                    </select>
                  </div>
                </div> 
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2 col-sm-3">Modelo</label>
                  <div class="col-lg-4 col-md-4 col-sm-6">
                    <select class="form-control" id="comboModelo" name="veic_modelo">
                    </select>
                  </div>
                </div> 
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2">Cor</label>
                  <div class="col-lg-4 col-md-4 col-sm-6">
                    <select class="form-control" id="comboCor" name="veic_cor">
                    </select>
                  </div>
                </div> 
                
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2 col-sm-3">Placa</label>
                  <div class="col-lg-2 col-md-2 col-sm-2">
                    <select class="form-control" id="comboPlacaLetra1" name="veic_placa_letra">
                    </select>
                  </div>
                  <div class="col-lg-2 col-md-2 col-sm-2">
                    <select class="form-control" id="comboPlacaLetra2" name="veic_placa_letra">
                    </select>
                  </div>
                  <div class="col-lg-2 col-md-2 col-sm-2">
                    <select class="form-control" id="comboPlacaLetra3" name="veic_placa_letra">
                    </select>
                  </div>
                  
                  <div class="col-lg-2 col-md-2 col-sm-2">
                    <select class="form-control" id="comboPlacaNumero1" name="veic_placa_numero">
                    </select>
                  </div>
                  <div class="col-lg-2 col-md-2 col-sm-2">
                    <select class="form-control" id="comboPlacaNumero2" name="veic_placa_numero">
                    </select>
                  </div>
                </div> 
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2">Frase Final</label>
                  <div class="col-lg-4 col-md-4 col-sm-6">
                    <select class="form-control" id="comboFraseFinal" name="veic_frase_fim">
                    </select>
                  </div>
                </div>
                
              </form>
            
            
            </div>
          </div>
          
          <div class="spacer-vertical10"></div>

          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="pull-right">
                <button type="button" class="btn btn-primary" id="btnTocar">
                  <i class="fa fa-lg fa-play"></i>
                  Tocar Chamada Completa
                </button>
              </div>            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>

          <div class="player" id="player1" style="display : none;" >
              <audio controls>
                  <source src="" type="audio/ogg">
              </audio>
          </div>
          
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                <a class="btn btn-default" href="${context}/admin/upload-painel/view">
                <i class="fa fa-arrow-left"></i>
                Voltar para Upload de Mídias</a>    
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/admin/painel">Painel de Admin</a>    
              </div>          
            </div>
          </div>            
        </div>
      </div>
    </div>
    
      
  </div> <!-- /container -->


<script type="text/javascript" src="${context}/js/required/jquery.serializejson.js" defer></script>

<link rel="stylesheet" href="https://cdn.plyr.io/1.3.7/plyr.css" defer>
<script src="https://cdn.plyr.io/1.3.7/plyr.js" defer></script>

<script type="text/javascript">


    var player = null;
    
    var playlist = [];

    var playSequence = function( array ){
        
        if ( array == null || array.length <= 0 )
            return;
        
        player.pause();
        
        playlist = array.slice();
        
        schedulePlay();
    }

    var schedulePlay = function()
    {
        if ( playlist == null || playlist.length <= 0 )
            return;

        player.pause();

        var musicaAtual = playlist.splice(0, 1);        

        if ( musicaAtual == null || musicaAtual == undefined )
            return;
        
        var url = buildUrl( "/api/admin/midias/{idMidia}", { idMidia: musicaAtual });
        
        player.source( url );
        player.play();
    }
       
    
    var carregarCombo = function( element )
    {
        var url = buildUrl( "/admin/midias?codigo={codigo}", {
            codigo : element.attr('name')
        });

        element.empty();
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: url,
            dataType: 'json'
        }).done( function(json) {
            
            $.each( json.rows , function (i, midia){
                element.append($('<option>', { 
                    value: midia.idMidia,
                    text : midia.descricao  
                }));
            });
        });
    };
    
    
    
    var validaForm = function(){
        
        var isOk = true;
        
        removeErros();
        
        var arrayCampos = [
                            {field: "comboFraseInicial",      desc : ""},
                            {field: "comboMarca",           desc : ""},
                            {field: "comboModelo",          desc : ""},
                            {field: "comboCor",             desc : ""},
                            {field: "comboPlacaLetra1",      desc : ""},
                            {field: "comboPlacaLetra2",      desc : ""},
                            {field: "comboPlacaLetra3",      desc : ""},
                            {field: "comboPlacaNumero1",      desc : ""},
                            {field: "comboPlacaNumero2",     desc : "" },
                            {field: "comboFraseFinal",      desc : ""}
                          ];
        
        isOk = validaCampos( arrayCampos );
        
        return isOk;
    };
    
    var validarETocar = function(){
        
        if ( validaForm() )
        {
            console.log("ok");

            var arr = [];
            
            arr[0] = $("#comboFraseInicial").val();
            arr[1] = $("#comboMarca").val();
            arr[2] = $("#comboModelo").val();
            arr[3] = $("#comboCor").val();
            arr[4] = $("#comboPlacaLetra1").val();
            arr[5] = $("#comboPlacaLetra2").val();
            arr[6] = $("#comboPlacaLetra3").val();
            arr[7] = $("#comboPlacaNumero1").val();
            arr[8] = $("#comboPlacaNumero2").val();
            arr[9] = $("#comboFraseFinal").val();
            
            playSequence( arr );
        }
    };

   

    $(function(){
        
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

// PRECISO EVOLUIR O PLYR PARA A VERSÃO 1.5 NÃO ESTAVA TOCANDO....

// NÃO ESTAVA NEM CHEGANDO NO SPRING, TALVEZ ELE NO ESTEJA FAZENDO GET.

        plyr.setup( { options : ["current-time", "duration", "mute"]});

        player = $('#player1')[0].plyr;

//         player = plyr.setup( { controls : ["restart", "rewind", "play", "current-time", "duration", "mute" ], fullscreen : { enabled : false } } )[0];
        
        player.media.addEventListener("ended", function() { 
            schedulePlay();
        });

        $("#btnAlfa").click( function(){
            salvarAlfa();
        });
        
        carregarCombo( $("#comboFraseInicial") );
        carregarCombo( $("#comboMarca") );
        carregarCombo( $("#comboModelo") );
        carregarCombo( $("#comboCor") );
        carregarCombo( $("#comboPlacaLetra1") );
        carregarCombo( $("#comboPlacaLetra2") );
        carregarCombo( $("#comboPlacaLetra3") );
        carregarCombo( $("#comboPlacaNumero1") );
        carregarCombo( $("#comboPlacaNumero2") );
        carregarCombo( $("#comboFraseFinal") );
        
        $("#btnTocar").click( function(){
            validarETocar();
        });
        
    });

</script>

<style type="text/css">

#table-generos tr{
  cursor: pointer;
}

</style>

<jsp:include page="/WEB-INF/views/bottom.jsp" />