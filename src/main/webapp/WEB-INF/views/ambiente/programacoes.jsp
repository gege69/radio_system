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
          <h3>Programação Musical ( Gêneros )<br/>
            <small>Programação musical do ${nome}</small>
          </h3>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="panel panel-default">
                <div class="panel-body">
                   
                  <div class="row">
                    <div class="col-lg-12 col-md-12">
                      <table class="table table-striped table-bordered sched" style="table-layout: fixed; word-wrap: break-word;">
                        <thead>
                          <td>
                            #
                          </td>
                          <td>
                            SEGUNDA
                          </td>
                          <td>
                            TERÇA
                          </td>
                          <td>
                            QUARTA
                          </td>
                          <td>
                            QUINTA
                          </td>
                          <td>
                            SEXTA
                          </td>
                          <td>
                            SÁBADO
                          </td>
                          <td>
                            DOMINGO
                          </td>
                        </thead>
                        <tbody id="bodytable">
                                       
                        </tbody>
                      </table>
                    </div>
                  </div>
                  
                    
                  <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                      <a class="btn btn-primary" id="btnSalvarExpediente" href="#">Salvar Alterações</a>
                    </div>
                  </div>                     
                  
                </div>
              </div>
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
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



<script id="viewTmpl" type="text/x-jsrender">
<tr>
{{for diasSemana}}
  <td>
    <div id="{{:hora}}-{{:dia}}" hora="{{:hora}}" dia={{:dia}} class="divsched">{{:desc}}</div>
  </td>
{{/for}}  
</tr>                           
</script>  


<script type="text/javascript">

    var salvar = function(){
        
        $.ajax({
            
            type: 'POST',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/programacoes',
            dataType: 'json',
            data:  JSON.stringify( $('#ambiente-expediente-form').serializeJSON() )
            
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
    
    function pad(value, length) {
        return (value.toString().length < length) ? pad("0"+value, length):value;
    }
    
    var buildTable = function(){

        var rows = [];
        
        for ( i = 0; i<= 23; i++ )
        {
            var diasSemanaArray = [ { hora : i, dia: "", desc : pad(i, 2) + ":00" }, 
                                    { hora : i, dia: "SEGUNDA", desc : "&nbsp;" }, 
                                    { hora : i, dia: "TERCA", desc : "&nbsp;" }, 
                                    { hora : i, dia: "QUARTA", desc : "&nbsp;" }, 
                                    { hora : i, dia: "QUINTA", desc : "&nbsp;" }, 
                                    { hora : i, dia: "SEXTA", desc : "&nbsp;" }, 
                                    { hora : i, dia: "SABADO", desc : "&nbsp;" }, 
                                    { hora : i, dia: "DOMINGO", desc : "&nbsp;" } ];
            
            var linha = { diasSemana : diasSemanaArray };
            
            rows[i] = linha;
        }
        
        var tmpl = $.templates('#viewTmpl');
        
        $('#bodytable').empty();
        
        var content = tmpl.render(rows);
        
        $('#bodytable').append(content);
    };
    
    
    var openPopup = function( element )
    {
        var par = element.parent();
        
        
        
    }
    

    var getDados = function()
    {
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/programacoes',
            dataType: 'json'
        }).done( function(json) {
            
            $.each( json.rows, function( index, obj ) {

                var $celula = $('#'+obj.horaInicio+'-'+obj.diaSemana);
                
                var link = "<a href='#' id='linkcelula-" + obj.horaInicio+"-"+obj.diaSemana + "' hora='"+obj.horaInicio+"' dia='"+obj.diaSemana+"' class='btn btn-link linkcelula'>2 Gêneros</a>";
                
                $celula.html( link );
                $celula.addClass('divschedsel');

            });
            
            $('.linkcelula').click( function() {
                openPopup( $( this ) );
            });
            
        });
    }

    
    
    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        $('#btnSalvarExpediente').on('click', salvar);

        buildTable();

        getDados();
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />