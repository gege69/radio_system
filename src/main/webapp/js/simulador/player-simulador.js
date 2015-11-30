
var player = new MediaElementPlayer('#player1');

var player2 = new MediaElementPlayer('#player2');

var idAmbiente = $('#idAmbiente').val();

var volumeMusicas = 100;

var volumeChamadas = 100;

var volumeComerciais = 100;

var volumeGeral = 100;

var volumeIndividual = false;

var playlist = [];

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

    player2.setSrc( url );
    player2.play();

    player2.media.onended = function() { schedulePlay(); };
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
            
            player.media.src = content.link;
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

            player.media.src = content.link;
            player.play();
        }

    });
    
};

player.media.onended = function(){ next(); };


var abreModal = function( tela ){ 
    
    var url = buildUrl( "/player/ambientes/{idAmbiente}/simulacoes/{tela}/view", { 
        idAmbiente: idAmbiente,
        tela : tela
     });
    
    $('.my-modal-cont').load( url, function(result){
        $('#myModal').modal({
            show:true, 
            backdrop: 'static',              
            keyboard: false
        });
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
    
    $(".campo-slider").on("slide", function(slideEvt) {
        alteraVolume(slideEvt.value);
    });
    
    $(".campo-slider").on("slideStop", function(slideEvt) {
        alteraVolume(slideEvt.value);
    });

    registraModal('#btn-chamada-veiculo', "chamadaveiculos");
    registraModal('#btn-chamada-funcionarios', "chamadafuncionarios");
    registraModal('#btn-chamada-inst', "chamadainst");
    registraModal('#btn-horoscopo', "horoscopo");
    registraModal('#btn-config-comerciais', "configcomerciais");
    registraModal('#btn-config-inst', "configinst");
    registraModal('#btn-config-programetes', "configprogrametes");
    
    registraModal('#btn-generos', "generos");
    registraModal('#btn-blocos', "blocos");
    registraModal('#btn-nobreak', "nobreak");
    registraModal('#btn-downloads', "downloads");
    registraModal('#btn-relatorios', "relatorios");
    registraModal('#btn-atendimento', "atendimento");
    
    getConfiguracoes();
    
});
