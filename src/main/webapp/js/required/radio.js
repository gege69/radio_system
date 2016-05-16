

var buildUrl = function( path , data )
{
    var url = URI.expand( contextPath + path, data ).toString();
    
    return url;
}


var makeRodapeTmpl = function( total, paginaAtual, listFunction ){
	
	$('#rodape').empty();

	var tmpl = $.templates('#rodapeTmpl');
	
	var data = { 
		de: 0, 
		ate: 0, 
		total: 0,
		hasMorePages: function(){
		  
			var has = false;
			
			if ( this.total > this.ate )
				has = true;
			else
				has = false;
			
			return has;
		}
	};
	
	data.de = (paginaAtual * limit) + 1;
	data.ate = (paginaAtual * limit) + (limit); 
	data.ate = total < data.ate?total:data.ate;
	data.total = total;
	
	var content = tmpl.render(data);
	
	$('#rodape').append(content);

	$('#btnAnterior').button();

	$('#btnProximo').button();
	
	if ( data.de == 1 ){
		$('#btnAnterior').button('disable');
	}
	
	$('#btnProximo').button('disable');
	
	if ( data.hasMorePages() ){
		$('#btnProximo').button('enable');
	}

	$('#btnAnterior').on('click', function(){
		pagina--;
		listFunction();
	});
	
	$('#btnProximo').on('click', function(){
		pagina++;
		listFunction();
	});
	
};


var removeErros = function()
{
    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('.form-group').removeClass('has-warning');
    $('#inputError2Status').remove();
    $('.icone-fa-feedback').remove();
      
    $('.alert').remove(); // limpando geral  
}

var formReset = function( form )
{
	removeErros();
	
	$(form).find(':input').not(':button, :submit, :reset, :checkbox, :radio').val('');
	$(form).find(':checkbox, :radio').prop('checked', false);
	
}


var validaCampos = function( arrayCampos )
{
    var isOk = true;
      
    $.each( arrayCampos, function( index, value ){
    
        if ( validaCampoIndividual( value.field, value.desc, value.message ) == false ) 
            isOk = false;
    });
      
    return isOk;
}

var validaCampoIndividual = function( nomeCampo, desc, message )
{
	var isOk = true;
	var conteudo = $('#'+nomeCampo).val();

	if ( conteudo == '' || conteudo == null || conteudo == undefined ){
		
		var msgDefault = ''; ;
		
		if ( ( message == null || message == undefined ) && desc != null && desc != undefined )
			msgDefault = '*O campo ' + desc + ' é obrigatório';
		else if ( message != null && message != undefined )
			msgDefault = message;
		
		preencheErroField( nomeCampo, msgDefault );
		
		isOk = false;
	}
	
	return isOk;
}

var preencheErros = function( errors )
{
    if ( errors == null ) 
        return;
    
    var isGeral = false;
    var erro = null;
    
    if ( errors.length == 1 )
        erro = errors[0];
    
    if ( errors.length == 1 && ( erro.field == "alertArea" || erro.field == "global" ) )
    {
        preencheAlertGeral( erro.field, erro.message );
    }
    else
    {
        $.each(errors, function(pos){
            
            var obj = errors[pos];
            
            preencheErroField( obj.field, obj.message );
            
        });  
    }
}


var preencheErroField = function( nomeCampo, msg )
{
    if ( $('#' + nomeCampo).length <= 0 )
        preencheAlertGeral( 'alertArea', "Problema ao detectar o campo relacionado : " + nomeCampo );
    else
    {
        $('<span id="inputError2Status" class="sr-only">(error)</span>').insertAfter( '#'+nomeCampo );
        $('<span class="fa fa-times form-control-feedback icone-fa-feedback" aria-hidden="true"></span>').insertAfter( '#'+nomeCampo );
          
        if ( msg != null && msg != '' )
            $('<div class="alert alert-danger">' + msg+ '</div>').insertAfter( '#'+nomeCampo );
        
        var formgroup = $('#'+nomeCampo).closest(".form-group");
          
        formgroup.addClass('has-error has-feedback');
    }
}


var preencheErroFieldUpdate = function( nomeCampo, msg )
{
    if ( $('#' + nomeCampo).length <= 0 )
        return;
    else
    {
        var formgroup = $('#'+nomeCampo).closest(".form-group");
        
        if ( formgroup.hasClass('has-error') )
            return;
        else
        {
            $('<span id="inputError2Status" class="sr-only">(error)</span>').insertAfter( '#'+nomeCampo );
            $('<span class="fa fa-times form-control-feedback icone-fa-feedback" aria-hidden="true"></span>').insertAfter( '#'+nomeCampo );
              
            if ( msg != null && msg != '' )
                $('<div class="alert alert-danger">' + msg+ '</div>').insertAfter( '#'+nomeCampo );
            
            formgroup.addClass('has-error has-feedback');
        }
    }
}


var feedbackFieldUpdate = function( nomeCampo, msg, warninglevel, iconparam )
{
    if ( $('#' + nomeCampo).length <= 0 )
        return;
    else
    {
        var icon = 'check';
        
        if ( iconparam != '' || iconparam != undefined )
            icon = iconparam;
        
        var formgroup = $('#'+nomeCampo).closest(".form-group");
        
        if ( formgroup.hasClass('has-'+warninglevel) )
            return;
        else
        {
            $('<span id="inputError2Status" class="sr-only">(error)</span>').insertAfter( '#'+nomeCampo );
            $('<span class="fa fa-'+ icon +' form-control-feedback icone-fa-feedback" aria-hidden="true"></span>').insertAfter( '#'+nomeCampo );
              
            if ( msg != null && msg != '' )
                $('<div class="alert alert-'+ warninglevel +'">' + msg+ '</div>').insertAfter( '#'+nomeCampo );
            
            formgroup.addClass('has-'+ warninglevel +' has-feedback');
        }
    }
}

var preencheAlertGeral = function( nomeCampo, msg, type )
{
    if ( msg != null && msg != '' && nomeCampo != null && nomeCampo != '' )
    {
        if ( type == null || type == undefined )
            type = 'danger';

        var alertGeral = 
            '<div class="alert alert-'+ type +'" role="alert" id="alert'+ nomeCampo +'" >'+
            '  <a href="#" class="close" data-dismiss="alert">&times;</a>'+
            '  <div id="errogeral">'+ msg +'</div>'+
            '</div>';
        
        $('#alertArea').append( alertGeral );

        var duration = 3000; // default

        if ( "danger" == type )
            duration = 8000;

        $("#alert" + nomeCampo ).fadeTo( duration , 500).slideUp(500, function(){
            $("#alert" + nomeCampo ).alert('close');
        });
    } 
}



/** 
 * Pula para uma âncora dentro da página
 */
var jump = function( h )
{
	var url = location.href;               
	location.href = "#"+h;                 
	history.replaceState(null,null,url); 
	return false;
}



var keyup_validasenha = function( event ) {
    var text = $('#password').val(); 
    var result = zxcvbn(text, ["eterion", "rdcenter", "radio", "ambiente", "som", "123456"]);
        
    if ( text == '' )
    {
        removeErros();
        return;
    }
    else
    {
        var length = text.length;

        if ( length < 6 || ( result != null && result.score <= 0 ) )
        {
            removeErros();
            
            var texto = "<b>Senha muito fraca</b>" ;
//                        "<br/>" +
//                        "<ul>" +
//                        "  <li>Você NÃO precisa de caracteres complicados para montar sua senha."+
//                        "  <li>Basta que a senha NÃO seja curta e óbvia"+
//                        "</ul>";
            
            preencheErroFieldUpdate( 'password', texto );
        }
        else if ( result != null && ( result.score > 0 && result.score <= 2 ) )
        {
            removeErros();
            feedbackFieldUpdate( 'password', 'Senha razoável', 'warning', 'exclamation-circle' );
        }
        else if (  result != null && result.score >= 3 )
        {
            removeErros();
            feedbackFieldUpdate( 'password', 'Senha Forte', 'success', 'check' );
        }
        else
            removeErros();            
    }
}


var padLeft = function (content, len, chr) {
    var self = content+'';
    return (this<0 && '-' || '')+
            (String(Math.pow( 10, (len || 2)-self.length))
              .slice(1).replace(/0/g,chr||'0') + self);
}



/**
 * Creates an array in which the contents of the given array are interspersed
 * with... something. If that something is a function, it will be called on each
 * insertion.
 */
function intersperse(array, something) {
  if (array.length < 2) { return array }
  var result = [], i = 0, l = array.length
  if (typeof something == 'function') {
    for (; i < l; i ++) {
      if (i !== 0) { result.push(something()) }
      result.push(array[i])
    }
  }
  else {
    for (; i < l; i ++) {
      if (i !== 0) { result.push(something) }
      result.push(array[i])
    }
  }
  return result
}



/**
 * Copiar objetos com facilidade
 */
function extend(from, to)
{
    if (from == null || typeof from != "object") return from;
    if (from.constructor != Object && from.constructor != Array) return from;
    if (from.constructor == Date || from.constructor == RegExp || from.constructor == Function ||
        from.constructor == String || from.constructor == Number || from.constructor == Boolean)
        return new from.constructor(from);

    to = to || new from.constructor();

    for (var name in from)
    {
        to[name] = typeof to[name] == "undefined" ? extend(from[name], null) : to[name];
    }

    return to;
}
