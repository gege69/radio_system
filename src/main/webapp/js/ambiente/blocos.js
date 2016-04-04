
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
            preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
            jump(''); // topo da pagina
        }
        else{
            preencheErros( json.errors );
        }
    });
};

var makeListTmpl = function(json){
    
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
        makeListTmpl(json);
        
        $.each( opcionaisList, function( idx, obj ){
            $('#opcional-'+obj.idOpcional).prop('checked', true);
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
        
        removeErros( $('#ambiente-bloco-form') );

        $('#ambiente-bloco-form').populate(json);
         
        configuraMultiplosMusica();
        
        $('#ambiente-bloco-form').populate(json);
        
        var opcionais = json.opcionais;
        
        listaOpcionais( opcionais );
         
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
    
    $('#posicaoVinheta').empty();
    
    var qtd = $('#qtdMusicas').val(); 
    
    if ( qtd != null && qtd > 0 )
        $('#posicaoVinheta').append($("<option/>", { value: "ANTES_CADA_MUSICA", text: 'Antes de cada música' }));

    $('#posicaoVinheta').append($("<option/>", { value: "ANTES_BLOCO_COMERCIAL", text: 'Antes do bloco Comercial' }));
    $('#posicaoVinheta').append($("<option/>", { value: "DEPOIS_BLOCO_COMERCIAL", text: 'Depois do bloco Comercial' }));
    $('#posicaoVinheta').append($("<option/>", { value: "NAO_INCLUIR", text: 'Não incluir vinhetas' }));
    
    if ( qtd == null || qtd <= 0 )
       $('#posicaoVinheta').val("NAO_INCLUIR");
}        


var configuraMultiplosMusica = function()
{
    configuraComboVinheta();

    $('#indexInstitucionais').empty();
    $('#indexInstitucionais').append($("<option/>", { value: 0, text: 'Não incluir Institucionais' }));
    
    $('#indexProgrametes').empty();
    $('#indexProgrametes').append($("<option/>", { value: 0, text: 'Não incluir Programetes' }));
    
    $('#indexOpcionais').empty();
    $('#indexOpcionais').append($("<option/>", { value: 0, text: 'Não incluir Opcionais' }));
    
    constroiCombo( $('#indexInstitucionais'), "Institucionais" );
    constroiCombo( $('#indexProgrametes'), "Programetes" );
    constroiCombo( $('#indexOpcionais'), "Opcionais" );
}


$(function(){

    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    
    $('#btnSalvarBloco').on('click', salvar);
    
    $('#qtdMusicas').change( function() { configuraMultiplosMusica(); } );
    
    getDados();
});

