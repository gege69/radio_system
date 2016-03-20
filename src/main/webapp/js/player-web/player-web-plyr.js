
var player = null;

var player2 = null;

var player3 = null;

var idAmbiente = $('#idAmbiente').val();

var volumeMusicas = 100;

var volumeChamadas = 100;

var volumeComerciais = 100;

var volumeGeral = 100;

var volumeIndividual = false;

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

    player2.source( url );
    player2.play();

}

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
    
    if ( player2.media.paused == false )
        return;
    
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
        }
        
        if ( content.link != null && content.link != '' )
        {
            console.log(content.link);
            
            determinaVolume( content );
            
            player.source( content.link );
            player.play();
        }

    });
};

var next = function(){
    
    if ( player2.media.paused == false )
        return;
    
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
        }
        
        if ( content.link != null && content.link != '' )
        {
            console.log(content.link);
            
            determinaVolume( content );

            player.source( content.link );
            player.play();
        }

    });
    
};



var abreModal = function( tela ){ 
    $("#" + tela).modal({
        show:true, 
        backdrop: 'static',              
        keyboard: false
    });
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



var retornaMusica = function()
{
    if ( micRetorna != null && micRetorna != undefined )
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

$(document).ready(function() {

    plyr.setup();
    
    player = $('#player1')[0].plyr;

    player2 = $('#player2')[0].plyr;

    player3 = $('#playerDing')[0].plyr;

    player.media.addEventListener("ended", function() { 
        next();
    });
    
    player2.media.addEventListener("ended", function() { 
        schedulePlay();
    });
    
    
    $(".campo-slider").bootstrapSlider({
        ticks: [0, 20, 40, 60, 80, 100],
        ticks_labels: ['0', '20', '40', '60', '80', '100'],
        ticks_snap_bounds: 3
    });
    
    $('#botao-stop').click( function(){
        stop();
    });
    
    $('#botao-play').click( function(){
        desligaMicrofone();
        play();
    });
    
//    $('#botao-next').click( function(){
//        desligaMicrofone();
//        next();
//    });
    
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
    
    registraModal('#btn-generos', "myModalGeneros");
    registraModal('#btn-blocos', "myModalBlocos");
    registraModal('#btn-downloads', "downloads");
    registraModal('#btn-relatorios', "relatorios");
    registraModal('#btn-atendimento', "myModalConversas");
    
    getConfiguracoes();
    
    $("#mic").click( function(){
        toggleMicrofone();
    });
    
});
