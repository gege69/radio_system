
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
         
        jump('ambiente-bloco-form');
    });
}


var constroiCombo = function( combo )
{
    var qtd = $('#qtdMusicas').val(); 

    for ( i = 1; i <= 20; i++ )
    {
        var val = qtd * i;
         
        combo.append($("<option/>", {
            value: val,
            text: "Depois de "+ val+ " músicas"
        }));
    }    

}

var configuraMultiplosMusica = function()
{
    $('#indexInstitucionais').empty();
    $('#indexInstitucionais').append($("<option/>", { value: 0, text: 'Não incluir institucionais' }));
    
    $('#indexProgrametes').empty();
    $('#indexProgrametes').append($("<option/>", { value: 0, text: 'Não incluir programetes' }));
    
    constroiCombo( $('#indexInstitucionais') );
    constroiCombo( $('#indexProgrametes') );
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

