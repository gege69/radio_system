var maskBehaviorTel = function (val) {
    return val.replace(/\D/g, '').length === 11 ? '00000-0000' : '0000-00009';
  };
  
var optionsTel = {onKeyPress: function(val, e, field, options) {
          field.mask(maskBehaviorTel.apply({}, arguments), options);
      }
  };

var getDados = function()
{
    var url = buildUrl( "/clientes/{idCliente}", {
        idCliente : $("#idCliente").val(),
    }); 
    
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,
        dataType: 'json'
    }).done( function(json) {
        
        removeErros( $('#clienteform') );
        
        var telefones = json.telefones;
       
        delete json["telefones"];
        
        $('#clienteform').populate(json);
        
        $('.linha-telefone').remove()
        
        $.each(telefones, function (key, tel) {
            if ( key == 0)
            {
                $('#ddd0').val( tel.ddd );
                $('#numero0').val( tel.numero );
                $('#idTelefone0').val( tel.idTelefone );
            }
            else
                makeListTmpl({rows:[{id: tel.idTelefone, ddd: tel.ddd , numero: tel.numero }]});
        });
        
        jump('ncmForm');
    });
}


var validaForm = function(){
    
    var isOk = true;
    
    removeErros( $('#clienteform') );
    
    var arrayCampos = [
                        {field: "razaosocial",      desc : "Razão Social"},
                        {field: "nomefantasia",     desc : "Nome Fantasia" } 
                      ];
    
    isOk = validaCampos( arrayCampos );
    
    return isOk;
};

var salvar = function(){
    
    if ( validaForm() ){
        
        var dados = JSON.stringify( $('#clienteform').serializeJSON() );
        
        var url = buildUrl( "/clientes" ); 

        $.ajax({
            
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: 'json',
            data: dados 
            
        }).done( function(json){ 

            if ( json.idCliente && json.idCliente > 0){
                
                $("#idCliente").val( json.idCliente );
                preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
                jump(''); // topo da pagina
            }
            else{
                preencheErros( json.errors );
            }
        });
    }
    
};

var makeListTmpl = function(json){
    
    var tmpl = $.templates('#viewTmplTelefones');
    
    var content = tmpl.render(json.rows);
    
    $('#containertelefones').append(content);
    
    $(".phone").mask(maskBehaviorTel, optionsTel);
    $(".ddd").mask('000');        
    
    refreshLinkRemoverTelefone();
};

var removerTelefone = function( element )
{
    var $div = element.parents('.linha-telefone');
    
    $div.remove();
    
    refreshLinkRemoverTelefone();
}

var addTelefone = function() {
    
    event.preventDefault();
    makeListTmpl({rows:[{id: "", ddd: "", numero: ""}]});
};

var refreshLinkRemoverTelefone = function() {
    $('.removertelefone').off('click');
    $('.removertelefone').on('click', function(event){
        removerTelefone( $(this) );
     });
}


$(function(){

    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
   
    $('#btnSalvar').on('click', salvar);
    
    $(".phone").mask(maskBehaviorTel, optionsTel);
    $(".ddd").mask('000');        
    $(".cnpj").mask('00.000.000/0000-00'); 

    getDados();
    
    $("#linkaddtelefone").click( function() {
        addTelefone(); 
    });
    
    refreshLinkRemoverTelefone();
});
