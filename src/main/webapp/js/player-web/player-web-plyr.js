
var player = null;

var player2 = null;

var player3 = null;

var idAmbiente = $('#idAmbiente').val();

var volumeMusicas = 100;

var volumeChamadas = 100;

var volumeComerciais = 100;

var volumeGeral = 100;

var volumeIndividual = false;

var autentica = 0;

var playlist = [];

var micRetorna = player;

var playSequence = function( array ){
    
    if ( array == null || array.length <= 0 )
        return;
    
    playlist = array.slice();
    
    schedulePlay();
}

var schedulePlay = function()
{
    pararEventosSilencio();

    var musicaAtual = playlist[0];
    
    if ( musicaAtual == null || musicaAtual == undefined )
    {
        var src = player.media.currentSrc;
        if ( src == null || src == "" )
            play();
        else
            player.play();
        
        return;
    }
    
    playlist.splice(0, 1);
    
    if ( player.media.paused == false )
        player.pause();
    
    var url = buildUrl( "/api/ambientes/{idAmbiente}/midia/{musicaAtual}", { 
        idAmbiente: idAmbiente,
        musicaAtual: musicaAtual
    });

    var fonte = { 
        type : 'audio' ,
        sources : [{
           src : url,
//           type : content.midia.mimetype
        }]
    };

    player2.source( fonte );
    player2.play();

    player2.media.addEventListener("ended", function() {
        schedulePlay();
    });
}

var stop = function(){

    pararEventosSilencio();

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
    
    if ( player2.media.paused == false )
        return;
    
    if ( player.media.paused == false )
        return;

    pararEventosSilencio();
    
    var url = buildUrl( "/api/ambientes/{idAmbiente}/transmissoes/live", { 
        idAmbiente: idAmbiente
    });
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
    }).done( function( content ){
        
        if ( content.midia != null )
        {
            if ( content.midia.title == null || content.midia.title == '' )
                $('#nome-musica').html( content.midia.nome );
            else
                $('#nome-musica').html( content.midia.title );
            
            $('#artista').html("");
            if ( content.midia.artist != null && content.midia.artist != '' )
                $('#artista').html( ' - ' + content.midia.artist );
        }
        
        if ( content.link != null && content.link != '' && content.midia != null )
        {
            determinaVolume( content );

            var urlMidia = buildUrl( content.link );

            var fonte = { 
                type : 'audio' ,
                sources : [{
                   src : urlMidia,
                   type : content.midia.mimetype
                }]
            };
            
            player.source(fonte);
            player.play();
            
            registraTempos();

            player.media.addEventListener("ended", function() {
                next();
            });
        }
        else {
            console.log("silencio!" + content.idTransmissao );
            stop();
            tocaSilencio( content.duracao );
        }
    });
};


function pararEventosSilencio(){
    if (myInterval)
        window.clearInterval(myInterval);

    if (clockInterval)
        window.clearInterval(clockInterval);
    
    secs = 0;
    duracaoSilencio = 0;
}

var secs = 0;
var duracaoSilencio = 0;

function increment() {

    var currentSeconds = 0;
    var currentMinutes = 0;

    currentMinutes = Math.floor(secs / 60);
    currentSeconds = secs % 60;
    if(currentSeconds <= 9) currentSeconds = "0" + currentSeconds;
    secs++;
    document.getElementById("spanTempoCorrido").innerHTML = currentMinutes + ":" + currentSeconds; //Set the element id you need the time put into.
    if(secs < duracaoSilencio) 
        clockInterval = setTimeout(increment,1000);
}

var myInterval;

var clockInterval;

var tocaSilencio = function( duracao ){
    
    console.log(duracao);
    
    pararEventosSilencio();

    myInterval = setTimeout(next, duracao * 1000);

    $('#nome-musica').html('Silêncio');
    $('#artista').empty();

    $("#spanTempoTotal").html("");
    $("#spanTempoTotal").html( processaTempo( duracao ) ); 

    $("#spanTempoCorrido").html("");

    secs = 1;
    duracaoSilencio = duracao;
    clockInterval = setTimeout(increment,1000); 
}


var registraTempos = function(){

    player.media.addEventListener("playing", function() {
        $("#spanTempoTotal").empty();
        $("#spanTempoTotal").html( processaTempo( player.media.duration ) ); 
    });

    player.media.addEventListener("timeupdate", function() {
        if ( player.media.currentTime != null && player.media.currentTime > 0 ){
            $("#spanTempoCorrido").empty();
            $("#spanTempoCorrido").html( processaTempo(player.media.currentTime) );
        }
    });
}

var processaTempo = function( time ){
    var duration = Math.round(time);
    var minutes = padLeft( Math.floor(duration/60), 2, '0' );
    var seconds = padLeft( (duration - minutes * 60), 2, '0');
    
    return ""+minutes+":"+seconds;
}


var next = function(){
    
    if ( player2.media.paused == false ){
        console.log('player2-paused');
        return;
    }

    pararEventosSilencio();
    
    var url = buildUrl( "/api/ambientes/{idAmbiente}/transmissoes/live/next", { 
        idAmbiente: idAmbiente
    });

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
    }).done( function( content ){
        
        if ( content.midia != null )
        {
            if ( content.midia.title == null || content.midia.title == '' )
                $('#nome-musica').html( content.midia.nome );
            else
                $('#nome-musica').html( content.midia.title );

            $('#artista').empty();
            if ( content.midia.artist != null && content.midia.artist != '' )
                $('#artista').html( ' - ' + content.midia.artist );
        }
        
        if ( content.link != null && content.link != '' && content.midia != null )
        {
            console.log(content.link);
            
            determinaVolume( content );

            var urlMidia = buildUrl( content.link );
            
            var fonte = { 
                type : 'audio' ,
                sources : [{
                   src : urlMidia,
                   type : content.midia.mimetype
                }]
            };

            player.source(fonte);
            player.play();

            registraTempos();

            player.media.addEventListener("ended", function() {
                next();
            });
        }
        else {
            console.log("silencio!" + content.idTransmissao );
            stop();
            tocaSilencio( content.duracao );
        }
    });
    
};



var abreModal = function( tela, extra ){ 
    $("#" + tela).modal({
        show:true, 
        backdrop: 'static',              
        keyboard: false
    });
    
    if (extra)
        $("#"+tela).data("param", extra);
};

var getConfiguracoes = function(){

    var url = buildUrl( "/api/ambientes/{idAmbiente}/configuracoes", { 
        idAmbiente: idAmbiente
    });

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
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
            
            if ( json.autenticaProgMusicalPlayer != null ){
                if ( json.autenticaProgMusicalPlayer == true && json.cadastrar == true ) {
                    autentica = 1;
                }
                else if ( json.autenticaProgMusicalPlayer == true && json.cadastrar == false ) {
                    autentica = 2;
                }
                registraModalPassword();
            }
        }
        else 
        {
            volumeGeral = 100;
        }
        
    });
}


var registraModal = function( element, url, extra ){
    $(element).click( function() {
        abreModal( url, extra ); 
    });
}



var retornaMusica = function()
{
    if ( micRetorna != null && micRetorna != undefined && duracaoSilencio === 0 )
    {
        micRetorna.play();
    }
}




var desligaMicrofone = function()
{
    var iconMic = $("#icon-mic");
    iconMic.removeClass("fa-microphone");
    iconMic.addClass("fa-microphone-slash");
    iconMic.addClass("player-mic-off");
    iconMic.removeClass("player-mic-on");
   
    retornaMusica();
}

var ligaMicrofone = function()
{
    var iconMic = $("#icon-mic");
    iconMic.addClass("fa-microphone");
    iconMic.removeClass("fa-microphone-slash");
    iconMic.removeClass("player-mic-off");
    iconMic.addClass("player-mic-on");
   
    if ( player.media.paused == false )
    {
        player.pause();
        micRetorna = player;
    }
    else if ( player2.media.paused == false )
    {
        player2.pause();
        micRetorna = player2;
    }

    player3.play();
}

var toggleMicrofone = function()
{
    var iconMic = $("#icon-mic");
    if ( iconMic.hasClass("fa-microphone") ) 
    {
        desligaMicrofone();
    }
    else if ( iconMic.hasClass("fa-microphone-slash") )
    {
        ligaMicrofone();
    }
}


var registraModalPassword = function(){

    if ( autentica == 1 ){
        $('#btn-generos').click( function() {
            $("#myModalGenerosCadastroAuth").modal({
                show:true, 
                backdrop: 'static',              
                keyboard: false
            });
        });
    }
    else if ( autentica == 2 ){
        $('#btn-generos').click( function() {
            $("#myModalGenerosAuth").modal({
                show:true, 
                backdrop: 'static',              
                keyboard: false
            });
        });
    }
    else{
        $('#btn-generos').off('click');
        registraModal('#btn-generos', "myModalGeneros");
    }
}

var authModal = function(){
    
    var url = buildUrl('/api/ambientes/{idAmbiente}/programacoes/autenticar', {
        idAmbiente : idAmbiente
    }); 
    
    var data = $('#autenticar-form').serializeJSON();

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data : JSON.stringify(data)
    }).done( function( json ){

        if ( json.ok == 1 ){
            // registra...
            $('#btn-generos').off('click');
            registraModal('#btn-generos', "myModalGeneros");
            $("#myModalGenerosAuth").modal('hide');
            $("#myModalGenerosCadastroAuth").modal('hide');
            // .. e abre
            abreModal("myModalGeneros");
        }
        else {
            preencheErros( json.errors, "alertaAuth" );
        }
    });
}


var cadastrarAuthModal = function(){
    
    var url = buildUrl('/api/ambientes/{idAmbiente}/programacoes/autenticar/new', {
        idAmbiente : idAmbiente
    }); 
    
    var data = $('#cadastrar-autenticar-form').serializeJSON();

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data : JSON.stringify(data)
    }).done( function( json ){

        if ( json.ok == 1 ){
            // registra...
            $('#btn-generos').off('click');
            registraModal('#btn-generos', "myModalGeneros");
            $("#myModalGenerosAuth").modal('hide');
            $("#myModalGenerosCadastroAuth").modal('hide');
            // .. e abre
            abreModal("myModalGeneros");
        }
        else {
            preencheErros( json.errors, "alertaCadastroAuth" );
        }
    });
}

$(document).ready(function() {

    plyr.setup({
        i18n: {
            restart:            "Restart",
            rewind:             "Rewind {seektime} secs",
            play:               "Play",
            pause:              "Pause",
            forward:            "Forward {seektime} secs",
            buffered:           "buffered",
            currentTime:        "Current time",
            duration:           "Duration",
            volume:             "Volume",
            toggleMute:         "Toggle Mute",
            toggleCaptions:     "Toggle Captions",
            toggleFullscreen:   "Toggle Fullscreen"
        }
    });
    
    player = $('#player1')[0].plyr;

    player2 = $('#player2')[0].plyr;

    player3 = $('#playerDing')[0].plyr;

    $(".campo-slider").bootstrapSlider({
        ticks: [0, 20, 40, 60, 80, 100],
        ticks_labels: ['0', '20', '40', '60', '80', '100'],
        ticks_snap_bounds: 3
    });
    
    $('#btnStop').click( function(){
        stop();
    });
    
    $('#btnPlay').click( function(){
        desligaMicrofone();
        play();
    });
    
    $('#btnNext').click( function(){
        desligaMicrofone();
        next();
    });
    
    $(".campo-slider").on("slide", function(slideEvt) {
        alteraVolume(slideEvt.value);
    });
    
    $(".campo-slider").on("slideStop", function(slideEvt) {
        alteraVolume(slideEvt.value);
    });

    registraModal('#btn-chamada-veiculo', "myModalChamadaVeiculos");
    registraModal('#btn-chamada-funcionarios', "myModalChamadaFuncionarios");
    registraModal('#btn-chamada-inst', "myModalChamadaInst");
    registraModal('#btn-horoscopo', "horoscopo");
    
//    registraModal('#btn-generos', "myModalGeneros");
    registraModal('#btn-blocos', "myModalBlocos");
    registraModal('#btn-downloads', "downloads");
    registraModal('#btn-relatorios', "relatorios");
    registraModal('#btn-atendimento', "myModalConversas");

    registraModal('#btn-programetes', "myModalMidiaBlock", "programete");
    registraModal('#btn-comerciais', "myModalMidiaBlock", "comercial");
    registraModal('#btn-institucionais', "myModalMidiaBlock", "inst");
    
    getConfiguracoes();
    
    $("#mic").click( function(){
        toggleMicrofone();
    });
    
    $("#btnFechar").click(function(){
        $("#myModalSair").modal({
            show:true, 
            backdrop: 'static',              
            keyboard: false
        });
    });

    $("#btnSairPlayer").click(function(){
        
        var simul = $("#simulacao").val();
        if ("true" === simul )
            window.close();
        else
            $("#formLogout").submit();
    })
    
    $("#btnAuth").click(function(){
        authModal();
    });

    $("#btnCadastroAuth").click(function(){
        cadastrarAuthModal();
    });

    $('#myModalGenerosAuth').on('shown.bs.modal', function() {
        $("#alertaAuth").empty();
    })

    $('#myModalGenerosCadastroAuth').on('shown.bs.modal', function() {
        $("#alertaAuth").empty();
    })
    
});
