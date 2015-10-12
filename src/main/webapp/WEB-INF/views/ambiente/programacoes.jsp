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


<div class="modal fade" id="myModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal"></h4>
      </div>
      <div class="modal-body">
        <form action="#" id="programacao-generos-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idAmbiente" value="${idAmbiente}">
          <input type="hidden" id="idProgramacaoModal" name="idProgramacao" value="0">
          <input type="hidden" id="horaform" name="horaInicio" value="0">
          <input type="hidden" id="diaform" name="diaSemana" value="">
          
          <div class="row">
            <div class="container col-md-12" id="container-generos">
            </div>
          </div>
        </form>
        
        <div class="row">
          <div class="col-lg-12 col-md-12">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="checkbox col-lg-12 col-md-12">
                  <label>
                    <input type="checkbox" class="checkbox-all" id="genero-all" name="all" value=""> Aplicar essa escolha para o dia inteiro ( respeitando o Expediente configurado )
                  </label>
                </div>                
              </div>
            </div>
          </div>
        </div>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
        <button type="button" class="btn btn-primary" id="btnSalvar">Associar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script id="viewTmpl" type="text/x-jsrender">
<tr>
{{for diasSemana}}
  <td>
    <div id="{{:hora}}-{{:dia}}" hora="{{:hora}}" dia={{:dia}} diadesc={{:diadesc}} idprogramacao="0" class="{{:clazz}}">{{:desc}}</div>
  </td>
{{/for}}  
</tr>                           
</script>  


<script id="viewTmplGeneros" type="text/x-jsrender">
    
      <div class="checkbox col-lg-4 col-md-4 col-sm-6 col-xs-12">
        <label>
          <input type="checkbox" class="checkbox-genero" id="genero-{{:idGenero}}" name="genero[idGenero]" value="{{:idGenero}}"> {{:descricao}}
        </label>
      </div>
      
</script>  


<script type="text/javascript">

    var salvar = function(){
        
        var idProgramacao = $('#idProgramacaoModal').val();

        var geraTodosDia = $('#genero-all').prop('checked');
        
        if ( geraTodosDia )
        {
            salvarGenerosDiaInteiro();
        }
        else if ( idProgramacao == null || idProgramacao == 0 || idProgramacao == undefined )
        {
            salvarProgramacao(); // vai chamar salvarGeneros no .done
        }
        else
        {
            salvarGeneros( idProgramacao );
        }
        
    };
    

    // Esse método salva primeiro a programação para que seja possível salvar os gêneros associados...
    var salvarGenerosDiaInteiro = function() {
        
        var array_values = [];
        $('.checkbox-genero').each( function() {
            if( $(this).is(':checked') ) {
                array_values.push( {idGenero: $(this).val()} );
            }
        });
        
        var idList = { lista : array_values };
        
        var dados = JSON.stringify( idList );
        
        var dia = $('#diaform').val();
        
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/programacoes/generos/'+ dia ,
            dataType: 'json',
            data : dados 
        }).done( function(json){
            if (json.ok != null){
                preencheAlertGeral( "alertArea", "Associação de Gêneros gravada com sucesso.", "success" );
                
                buildTableProgramacao();
                getDados();
                
                $('#myModal').modal('hide'); 
            }
            else{
                preencheErros( json.errors );
            }
        } );
    }
    
    
    // Esse método salva primeiro a programação para que seja possível salvar os gêneros associados...
    var salvarProgramacao = function() {
        
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/programacoes',
            dataType: 'json',
            data : JSON.stringify( $('#programacao-generos-form').serializeJSON() ) 
        }).done( function(json){
            if (json.idProgramacao != null){
                 
                salvarGeneros( json.idProgramacao );
            }
            else{
                preencheErros( json.errors );
            }
        } );
    }
    
    
    var salvarGeneros = function( idProgramacao )
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
            url: '${context}/ambientes/${idAmbiente}/programacoes/'+idProgramacao+'/generos',
            dataType: 'json',
            data : dados 
        }).done( function(json){
            if (json.ok != null){
                preencheAlertGeral( "alertArea", "Associação de Gêneros gravada com sucesso.", "success" );
                
                buildTableProgramacao();
                getDados();
                
                $('#myModal').modal('hide'); 
            }
            else{
                preencheErros( json.errors );
            }
        } );
    }
    
    
    function pad(value, length) {
        return (value.toString().length < length) ? pad("0"+value, length):value;
    }
    
    var buildTableProgramacao = function(){

        var rows = [];
        
        for ( i = 0; i<= 23; i++ )
        {
            var diasSemanaArray = [ { hora : i, dia: "", desc : pad(i, 2) + "<span class='smallhide'>:00</span>", clazz : "schedbasic" }, 
                                    { hora : i, dia: "SEGUNDA", diadesc: "Segunda", desc : "&nbsp;", clazz : "divsched" }, 
                                    { hora : i, dia: "TERCA", diadesc: "Terça", desc : "&nbsp;", clazz : "divsched" }, 
                                    { hora : i, dia: "QUARTA", diadesc: "Quarta", desc : "&nbsp;", clazz : "divsched" }, 
                                    { hora : i, dia: "QUINTA", diadesc: "Quinta", desc : "&nbsp;", clazz : "divsched" }, 
                                    { hora : i, dia: "SEXTA", diadesc: "Sexta", desc : "&nbsp;", clazz : "divsched" }, 
                                    { hora : i, dia: "SABADO", diadesc: "Sábado", desc : "&nbsp;", clazz : "divsched" }, 
                                    { hora : i, dia: "DOMINGO", diadesc: "Domingo", desc : "&nbsp;", clazz : "divsched" } ];
            
            var linha = { diasSemana : diasSemanaArray };
            
            rows[i] = linha;
        }
        
        var tmpl = $.templates('#viewTmpl');
        
        $('#bodytable').empty();
        
        var content = tmpl.render(rows);
        
        $('#bodytable').append(content);
        
        $('.divsched').click( function() {
            openPopup( $( this ) );
        });
        
    };
    
    
    
    var listaGeneros = function(){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/generos',    // busca a lista de gêneros geral ( não restringe pelo ambiente )
            dataType: 'json'
        }).done( function(json){
            
            makeTmplGeneros(json);
            
        } );
    }
    
    var makeTmplGeneros = function(json){
        
        var tmpl = $.templates('#viewTmplGeneros');
        
        $('#container-generos').empty();
        
        var content = tmpl.render(json.rows);
        
        $('#container-generos').append(content);
    };
    
    
    var buscaGenerosAssociados = function( idProgramacao ){
        
        $('.checkbox-genero').prop('checked', false);

        if ( idProgramacao == 0 || idProgramacao == "0" || idProgramacao == null )
            return;
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/programacoes/'+idProgramacao+'/generos',    // busca a lista de generos que está relacionada com o ambiente 
            dataType: 'json'
        }).done( function(json){
            
            var lista = json.rows;

            $.each( lista, function( idx, obj ){
                $('#genero-'+obj.idGenero).prop('checked', true);
            });
            
        });
    }

    
    
    var openPopup = function( element )
    {
        var idProgramacao = element.attr("idprogramacao");
        
        buscaGenerosAssociados(idProgramacao);

        var horaint = parseInt( element.attr("hora") ); 
        
        var horadesc = pad(horaint, 2) + ":00";
        
        $('#genero-all').prop('checked', false);
        
        $('#titulo-modal').html( element.attr("diadesc") + " às " + horadesc );
        
        $('#idProgramacaoModal').val( idProgramacao );
        
        $('#horaform').val( element.attr("hora") );
        $('#diaform').val( element.attr("dia") )
          
        $('#myModal').modal('show');
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
                
                var conteudo = "";
                
                if ( obj.generosCount != null )
                {
                    if ( obj.generosCount == "0" || obj.generosCount == "null" )
                        conteudo = "0<span class='smallhide'> Gêneros</span>";
                    else if ( obj.generosCount == "1" || obj.generosCount == 1 )
                        conteudo = "<span class='bighide'>1</span><span class='smallhide'>"+ obj.generoSingle + "</span>";
                    else
                        conteudo = obj.generosCount+"<span class='smallhide'> Gêneros</span>";
                }
                
//                 var link = "<a href='#' id='linkcelula-" + obj.horaInicio+"-"+obj.diaSemana + "' hora='"+obj.horaInicio+"' dia='"+obj.diaSemana+"' class='btn btn-link linkcelula'>"+conteudo+"</a>";
                
                $celula.html( conteudo );
                $celula.addClass('divschedsel');
                
                $celula.attr("idprogramacao", obj.idProgramacao );

            });
        });
    }

    
    
    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        buildTableProgramacao();

        getDados();
        
        listaGeneros();

        $('#btnSalvar').on('click', salvar);
       
        $('#btnPopup').click( function() {
           $('#myModal').modal('show'); 
        });
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />