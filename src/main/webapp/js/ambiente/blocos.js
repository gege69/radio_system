
var idAmbiente = $('#idAmbiente').val();

var salvar = function(){
    
    var url = buildUrl( "/ambientes/{idAmbiente}/blocos", {
        idAmbiente : idAmbiente
    });
    
    $.ajax({
        
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  JSON.stringify( $('#ambiente-bloco-form').serializeJSON() )
        
    }).done( function(json){ 

        if (json.ok == 1 ){

            $("#indicadorSalvo").show();
            $("#indicadorSalvo").removeClass("label-warning");
            $("#indicadorSalvo").addClass("label-success");
            $("#indicadorSalvo").html("Salvo");

            preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );

            jump(''); // topo da pagina
        }
        else{
            preencheErros( json.errors );
        }
    });
    
};

var makeListTmplOpcionais = function(json){
    
    var tmpl = $.templates('#viewTmplOpcionais');
    
    $('#containerOpcionais').empty();
    
    var content = tmpl.render(json.rows);
    
    $('#containerOpcionais').append(content);
};


var listaOpcionais = function( opcionaisList ){
    
    var url = buildUrl( "/opcionais");

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,    
        dataType: 'json'
    }).done( function(json){
        makeListTmplOpcionais(json);
        
        $.each( opcionaisList, function( idx, obj ){
            $('#opcional-'+obj.idOpcional).prop('checked', true);
        });
        
        $('.checkBloco').change( function() { 
            getExemplo( null, false );
        });
    } );
}


var getDados = function()
{
    var url = buildUrl( "/ambientes/{idAmbiente}/blocos", {
        idAmbiente : idAmbiente
    });
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        
        removeErros();

        $('#ambiente-bloco-form').populate(json);
         
        configuraMultiplosMusica();
        
        $('#ambiente-bloco-form').populate(json);
        
        var opcionais = json.opcionais;
        
        listaOpcionais( opcionais );
         
        var salvo = ( json != null && json.idBloco != null && json.idBloco > 0 );
        getExemplo( json, salvo );
        
        jump('ambiente-bloco-form');
    });
}


var constroiCombo = function( combo, nome )
{
    var qtd = $('#qtdMusicas').val(); 
    
    if ( qtd == null || qtd <= 0 ){
        combo.append($("<option/>", {
            value: 1,
            text: "Incluir " + nome
        }));
    }
    else{
        for ( i = 1; i <= 20; i++ )
        {
            var val = qtd * i;
             
            combo.append($("<option/>", {
                value: val,
                text: "Depois de "+ val+ " músicas"
            }));
        }    
    }


}


var configuraComboVinheta = function(){
    
    var combo = $('#posicaoVinheta');
    combo.empty();
    
    var qtd = $('#qtdMusicas').val(); 
    
    if ( qtd != null && qtd > 0 )
        combo.append($("<option/>", { value: "ANTES_CADA_MUSICA", text: 'Antes de cada música' }));

    combo.append($("<option/>", { value: "ANTES_BLOCO_COMERCIAL", text: 'Antes do bloco Comercial' }));
    combo.append($("<option/>", { value: "DEPOIS_BLOCO_COMERCIAL", text: 'Depois do bloco Comercial' }));
    combo.append($("<option/>", { value: "NAO_INCLUIR", text: 'Não incluir vinhetas' }));
    
    if ( qtd == null || qtd <= 0 )
       combo.val("NAO_INCLUIR");
}        



var configuraComboComercial = function(){

    var combo = $('#posicaoComercial');
    combo.empty();
    
    var qtd = $('#qtdMusicas').val(); 
    
    if ( qtd != null && qtd > 0 )
        combo.append($("<option/>", { value: "DEPOIS_MUSICAS", text: 'Depois das Músicas' }));

    combo.append($("<option/>", { value: "ANTES_INSTITUCIONAL", text: 'Antes do Institucional' }));
    combo.append($("<option/>", { value: "DEPOIS_INSTITUCIONAL", text: 'Depois do Institucional' }));
    combo.append($("<option/>", { value: "ANTES_PROGRAMETE", text: 'Antes do Programete' }));
    combo.append($("<option/>", { value: "DEPOIS_PROGRAMETE", text: 'Depois do Programete' }));
    combo.append($("<option/>", { value: "NAO_INCLUIR", text: 'Não incluir comerciais' }));

    if ( qtd == null || qtd <= 0 )
       combo.val("ANTES_INSTITUCIONAL"); 
}        


var configuraMultiplosMusica = function()
{
    configuraComboVinheta();
    
    configuraComboComercial();

    $('#indexInstitucionais').empty();
    $('#indexInstitucionais').append($("<option/>", { value: 0, text: 'Não incluir Institucionais' }));
    
    $('#indexProgrametes').empty();
    $('#indexProgrametes').append($("<option/>", { value: 0, text: 'Não incluir Programetes' }));
    
    $('#indexOpcionais').empty();
    $('#indexOpcionais').append($("<option/>", { value: 0, text: 'Não incluir Opcionais' }));
    
    constroiCombo( $('#indexInstitucionais'), "Institucionais" );
    constroiCombo( $('#indexProgrametes'), "Programetes" );
    constroiCombo( $('#indexOpcionais'), "Opcionais" );
    
    resetCombosSilencio();
}


var resetCombosSilencio = function(){
    
    $('#posicaoSilencio').val("NAO_INCLUIR");
    $('#tamanhoSilencio').val("");
}


var getExemplo = function( bloco, salvo ){

    var existe = $( "#containerExemplo" ).length;

    if ( !existe )    
        return;

    var url = buildUrl( "/ambientes/{idAmbiente}/blocos/exemplo", {
        idAmbiente : idAmbiente
    });
    
    var dados = null;
    if ( bloco == null )
        dados = JSON.stringify( $('#ambiente-bloco-form').serializeJSON() );
    else
        dados = JSON.stringify( bloco );
    
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: 'json',
        data:  dados
        
    }).done( function(json){ 

        if (json != null ){
            
            $("#indicadorSalvo").show();
            
            if ( salvo == null || salvo == false ){
                $("#indicadorSalvo").removeClass("label-success");
                $("#indicadorSalvo").addClass("label-warning");
                $("#indicadorSalvo").html("Não salvo...");
            }
            
            var container = $("#containerExemplo");
            container.empty();

            $.each( json, function( idx, item ){
                container.append( $("<p/>", { "class": "small", text: item }) );
            });
        }
    });
};


$(function(){

    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    
    $('#btnSalvarBloco').on('click', salvar);
    
    $('#qtdMusicas').change( function() { 
        configuraMultiplosMusica(); 
        getExemplo( null, false );
    });

    $('.controleBloco').change( function() { 
        getExemplo( null, false );
    });
    
    getDados();
});

