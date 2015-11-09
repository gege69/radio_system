<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
      
        <div class="row" id="alertArea">
        </div>
      
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Gêneros</h3>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-lg-12 col-md-12">
            <form action="#" id="ambiente-generos-form" method="POST">
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
              <input type="hidden" id="idAmbiente" value="${idAmbiente}">
              <div class="form-inline">
                <div class="container col-lg-12 col-md-12" id="view-container">
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Cancelar</button>
        <button class="btn btn-primary" id="btnSalvarGeneros" >Ok</button>
      </div>
    </div>
  </div>
</div>

<script id="viewTmpl" type="text/x-jsrender">
    
      <div class="checkbox col-lg-6 col-md-6 col-sm-6 col-xs-12">
        <label>
          <input type="checkbox" class="checkbox-genero" id="genero-{{:idGenero}}" name="genero[idGenero]" value="{{:idGenero}}"> {{:descricao}}
        </label>
      </div>
      
</script>  


<script type="text/javascript">


    var listaGeneros = function( doJump ){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/generos',    // busca a lista de gêneros geral ( não restringe pelo ambiente )
            dataType: 'json'
        }).done( function(json){
            makeListTmpl(json);
            
            
            $.ajax({
                type: 'GET',
                contentType: 'application/json',
                url: '${context}/ambientes/${idAmbiente}/generos',    // busca a lista de generos que está relacionada com o ambiente 
                dataType: 'json'
            }).done( function(json){
                
                var lista = json.rows;

                $.each( lista, function( idx, obj ){
                    $('#genero-'+obj.idGenero).prop('checked', true);
                });
                
            });
            
        } );
    }


    var salvarGeneros = function()
    {
        var array_values = [];
        $('.checkbox-genero').each( function() {
            if( $(this).is(':checked') ) {
                array_values.push( {idGenero: $(this).val()} );
            }
        });
        
        if ( array_values.length == 0 || array_values == undefined )
        { 
            preencheAlertGeral('alertArea', 'É necessário escolher pelo menos um Gênero Músical');
            return;
        }
        
        var idList = { lista : array_values };
        
        var dados = JSON.stringify( idList );
        
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/generos',
            dataType: 'json',
            data : dados 
        }).done( function(json){
            if (json.ok != null){
//                 preencheAlertGeral( "alertArea", "Associação de Gêneros gravada com sucesso.", "success" );

                geraNovaProgramacaoMusical();

                $('#myModal').modal('hide');
            }
            else{
                preencheErros( json.errors );
            }
        } );
        
    }
    
    var geraNovaProgramacaoMusical = function(){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/api/ambientes/${idAmbiente}/transmissoes/new',  
            dataType: 'json'
        });
    }
    
    
    var makeListTmpl = function(json){
        
        var tmpl = $.templates('#viewTmpl');
        
        $('#view-container').empty();
        
        var content = tmpl.render(json.rows);
        
        $('#view-container').append(content);
    };

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        listaGeneros(false);
        
        $('#btnSalvarGeneros').on('click', function(){
            salvarGeneros();
        });
        
    });

    
</script>