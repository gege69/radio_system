

var confirma = function()
{
    var primeiro = $('#funcionario').val();
    var segundo = $('#frase').val();
    
    if ( primeiro == null || primeiro == undefined )
    {
        preencheErroField( "funcionario", "Preencha o campo" );
        return;
    }
    
    if ( segundo == null || segundo == undefined )
    {
        preencheErroField( "frase", "Preencha o campo" );
        return;
    }
    
    var array = [];
    array[0] = primeiro;
    array[1] = segundo;
    
    playSequence( array );
    $('#myModal').modal('hide');
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
    
    removeErros( $('#formtocar') );
    
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
        $( '.modal' ).modal( 'hide' ).data( 'bs.modal', null );
        $('#myModal').modal('hide');
    }
};


$(function(){

    debugger;

    $("#botaoConfirma").click( function(){
        validarETocar();
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
    
});

