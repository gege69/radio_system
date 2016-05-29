<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">

    <div class="row">
    
      <div class="panel panel-default">
        <div class="panel-body">

          <div class="row">
            <div class="col-lg-6 col-md-6">
              <h3><i class="fa ${icone}"></i> Gêneros<br/>
                <small>Selecione os gêneros musicais que serão reproduzidos em ${nome}</small>
              </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="panel panel-default">
                <div class="panel-body">
                   
                  <div class="row">
                    <form action="#" id="ambiente-generos-form" method="POST">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                      <input type="hidden" id="idAmbiente" value="${idAmbiente}">
                      <div class="form-inline">
                        <div class="container" id="view-container">
                        </div>
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
                <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/programacoes/view" id="btnAvançado"><i class="fa fa-list-ol"></i> Configurar Programação Musical</a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-primary" href="#" id="btnSalvarGeneros">Salvar alterações</a>
              </div>            
            </div>            
          </div>
          
          <div class="spacer-vertical80"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/view" >
                  <i class="fa fa-arrow-left"></i>
                  Voltar para ${nome}
                </a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
              </div>
            </div>
          </div>
          
        </div>
      </div>
    </div>
    
      
  </div> <!-- /container -->

<script src="${context}/js/required/jsrender.min.js"></script>

<script id="viewTmpl" type="text/x-jsrender">
    
      <div class="checkbox col-lg-4 col-md-4 col-sm-6 col-xs-12">
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


<jsp:include page="/WEB-INF/views/bottom.jsp" />