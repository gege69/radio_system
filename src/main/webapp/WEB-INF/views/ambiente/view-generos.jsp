<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="row" id="alertArea">
      </div>
    
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Gêneros<br/>
            <small>Selecione os gêneros musicais que serão reproduzidos em ${nome}</small>
          </h3>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-11 col-md-12">
              <div class="panel panel-default">
                <div class="panel-body">
                   
                  <div class="row">
                    <form action="#" id="ambiente-generos-form" method="POST">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                      <input type="hidden" id="id_ambiente" value="${id_ambiente}">
                      <div class="container" id="view-container">
                      </div>
                    </form>
                  </div>
                  
                </div>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="${context}/view-ambiente/${id_ambiente}" >Administrar Ambiente</a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-primary" href="#" id="btnSalvarGeneros">Salvar alterações</a>
              </div>            
            </div>            
          </div>
          
          <div class="spacer-vertical10"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="#" id="btnSalvarGeneros">Modo Avançado</a>
              </div>            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="pull-right">
          <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
        </div>
      </div>
    </div>
      
  </div> <!-- /container -->



<script id="viewTmpl" type="text/x-jsrender">
    <div class="form-inline">
      <div class="checkbox col-lg-4 col-md-4 col-sm-4 col-xs-4">
        <label>
          <input type="checkbox" id="genero-{{:id_genero}}" name="genero[id_genero]" value="{{:id_genero}}"> {{:descricao}}
        </label>
      </div>
    </div>  
</script>  


<script type="text/javascript">


    var listaGeneros = function( id_ambiente, doJump ){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/'+id_ambiente+'/generos',
            dataType: 'json'
        }).done( function(json){
            makeListTmpl(json);
            
            
            $.ajax({
                type: 'GET',
                contentType: 'application/json',
                url: '${context}/ambientes/'+id_ambiente+'/generos-associacao',
                dataType: 'json'
            }).done( function(json){
                
                var lista = json.data;

                $.each( lista, function( idx, obj ){
                    $('#genero-'+obj).prop('checked', true);
                });
                
            });
            
        } );
    }

    
    var salvarGeneros = function( id_ambiente )
    {
        var array_values = [];
        $('input[type=checkbox]').each( function() {
            if( $(this).is(':checked') ) {
                array_values.push( {id_genero: $(this).val()} );
            }
        });
        
        var idList = { lista : array_values };
        
        var dados = JSON.stringify( idList );
        
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '${context}/ambientes/'+id_ambiente+'/generos',
            dataType: 'json',
            data : dados 
        }).done( function(json){
            if (json.ok != null){
                preencheAlertGeral( "alertArea", "Associação de Gêneros gravada com sucesso.", "success" );
                jump(''); // topo da pagina
            }
            else{
                preencheErros( json.errors );
            }
        } );
        
    }
    
    var makeListTmpl = function(json){
        
        var tmpl = $.templates('#viewTmpl');
        
        $('#view-container').empty();
        
        var content = tmpl.render(json.data);
        
        $('#view-container').append(content);
    };

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        var id_ambiente = $('#id_ambiente').val();
        listaGeneros( id_ambiente, false);
        
        $('#btnSalvarGeneros').on('click', function(){
            salvarGeneros( $('#id_ambiente').val() );
        });
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />