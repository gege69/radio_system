
var toJSON = function(data) {

	var obj = {};

	$.each(data, function() {
		if (obj[this.name]) {
			if (!obj[this.name].push) {
				obj[this.name] = [ obj[this.name] ];
			}
			obj[this.name].push(this.value || '');
		} else {
			obj[this.name] = this.value || '';
		}
	});

	return JSON.stringify(obj);
};



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


var removeErros = function( form )
{
  form.find('.form-group').removeClass('has-error');
  form.find('#inputError2Status').remove();
  form.find('.glyphicon-remove').remove();
  form.find('.alert').remove();
  
  $('.alert').remove(); // limpando geral  
}

var formReset = function( form )
{
	removeErros(form);
	
	$(form).find(':input').not(':button, :submit, :reset, :hidden, :checkbox, :radio').val('');
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
		
		preencheErroField( msgDefault, nomeCampo );
		
		isOk = false;
	}
	
	return isOk;
}


var preencheErroField = function( msg, nomeCampo )
{
  $('<span id="inputError2Status" class="sr-only">(error)</span>').insertAfter( '#'+nomeCampo );
  $('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>').insertAfter( '#'+nomeCampo );
  
  if ( msg != null && msg != '' )
    $('<div class="alert alert-danger">' + msg+ '</div>').insertAfter( '#'+nomeCampo );

  var formgroup = $('#'+nomeCampo).closest(".form-group");
  
  formgroup.addClass('has-error has-feedback');
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
    
    $('#'+nomeCampo).append( alertGeral );

    var duration = 3000; // default
    
    if ( "danger" == type )
        duration = 7000;
    
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

