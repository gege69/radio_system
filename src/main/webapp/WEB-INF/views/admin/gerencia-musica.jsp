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
              <h3>Gerência de Músicas </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>

          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              
              <div class="row">
                <div class="col-lg-12 col-md-12">

                  <div class="panel panel-default">
                    <div class="panel-body">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf" />
                      <input type="hidden" name="sucessos" value="${sucessos}" id="sucessos" />

                      <div class="container col-md-10" id="view-container">
                        <div id="chart_div" style="width:900; height:500"></div>
                      </div>
                      
                      <div class="col-md-2">
                        <button href="#" class="btn btn-success" id="btnRefreshGrafico">
                          <i class="fa fa-refresh"></i>
                          Atualizar Gráfico
                        </button>
                      </div>

                    </div>
                  </div>

                </div>
              
              </div>
            
              <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                  <div class="pull-right">
                    <a class="btn btn-default" href="${context}/admin/upload-musica/view">
                    <span class="fa-stack">
                      <i class="fa fa-cloud fa-stack-2x"></i>
                      <i class="fa fa-music fa-stack-1x fa-inverse"></i>
                    </span>
                    &nbsp; Upload de Músicas</a>    
                  </div>          
                </div>
              </div>            

              <div class="spacer-vertical20"></div>

              <div class="row">
                <div class="col-lg-12 col-md-12">
                  <p>
                      <div class="alert alert-info col-md-6" role="alert" style="display : none;" id="filtroGenero"></div>
                  </p>
                </div>
              </div>

              <div class="row">
                <div class="col-lg-12 col-md-12">
                  <p><span class="totalMusicas">0</span> <span id="totalMusicasTexto"></span></p>
                </div>
              </div>
              
              <div id="toolbar">
                <div class="form-inline" role="form">
                  <button type="button" class="btn btn-default" id="btnLimparFiltro">
                    <i class="fa fa-refresh"></i>
                    Limpar Filtro
                  </button>
                  <button type="button" class="btn btn-default" id="btnAlterarGenerosGeral" title="Alterar Gêneros de TODAS as músicas que estão sendo listadas pela Grid" >
                    <i class="material-icons md-18">library_music</i>
                    Alterar Gêneros
                  </button>
                  <button type="button" class="btn btn-default" id="btnDeletarSelecao" title="Deletar TODAS as músicas que estão sendo listadas pela Grid">
                    <i class="material-icons md-18">delete_forever</i>
                    Deletar Músicas
                  </button>
                </div>
              </div>
              <table  
                 id="table-musicas"
                 data-toggle="table"
                 data-url="${context}/admin/midias/musicas"
                 data-height="555"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=7
                 data-locale = "pt_BR"
                 data-toolbar="#toolbar"
                 data-unique-id="idMidia"
                 data-click-to-select="true"
                 data-search="true"
                 data-page-list = "[7,15,30,100]"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="state" data-checkbox="true" style="width: 36px;"></th>
                      <th data-field="nome">Nome do Arquivo</th>
                      <th data-field="generosResumo">Gênero(s)</th>
                      <th data-field="artist">Artista</th>
                      <th data-field="idMidia" data-click-to-select="false" data-formatter="editarFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Editar</th>
                      <th data-field="idMidia" data-click-to-select="false" data-formatter="removerFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Remover</th>
                      <th data-field="idMidia" data-click-to-select="false" data-formatter="playFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Tocar</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical10"></div>

          <div class="player" id="player1" style="display:none;" >
              <audio controls>
                  <source src="" type="audio/ogg">
              </audio>
          </div>
          
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                <a class="btn btn-default" href="${context}/admin/upload-painel/view">
                <i class="fa fa-arrow-left"></i>
                Voltar para Upload de Mídias</a>    
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/admin/painel">Painel de Admin</a>    
              </div>          
            </div>
          </div>            
        </div>
      </div>
    </div>
    
      
  </div> <!-- /container -->


<jsp:include page="/WEB-INF/views/admin/template-modais-grid-musica.jsp" />


<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.7.2/jquery.serializejson.min.js" defer></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-pt-BR.min.js" charset="UTF-8"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet">


<script src="https://cdnjs.cloudflare.com/ajax/libs/jsrender/0.9.78/jsrender.min.js"></script>

<link rel="stylesheet" href="https://cdn.plyr.io/1.3.7/plyr.css" defer>
<script src="https://cdn.plyr.io/1.3.7/plyr.js" defer></script>


<script id="viewTmpl" type="text/x-jsrender">
    
      <div class="checkbox col-lg-4 col-md-4 col-sm-6 col-xs-12">
        <label>
          <input type="checkbox" class="checkbox-genero" idGenero="{{:idGenero}}" name="genero[idGenero]" value="{{:idGenero}}"> {{:descricao}}
        </label>
      </div>
      
</script>  

<link href="${context}/css/animate.min.css" rel="stylesheet" defer>

<script src="${context}/js/admin/grid-musica.js" charset="UTF-8"></script>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

google.load('visualization', '1.0', {'packages':['corechart']});

var listaGeneros = null;

function fixarGenero( idGenero, nomeGenero ){
    generoFixado = idGenero;
    $("#table-musicas").bootstrapTable('refresh');
    $("#filtroGenero").show();
    $("#filtroGenero").html('Listagem mostrando apenas músicas do Gênero : <strong>' + nomeGenero + '</strong>' );
    $("#btnLimparFiltro").show();
    jump("table-musicas");
}

var options;
var data;
var chart;


function getData() {
    var url = buildUrl( "/admin/generos/relatorio" );

    return $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,    
        dataType: 'json'
    });
}

function prepareData(result){
    
    listaGeneros = result;
    
    // Create the data table.
    data = new google.visualization.DataTable();
    data.addColumn('string', 'Gênero');
    data.addColumn('number', 'Músicas');
    data.addColumn('number', 'id Gênero');

    $.each(result, function(i, el){
        data.addRow([el.genero, el.quantidadeMidias, el.idGenero ]);
    });

    return data;
}

function drawChart() {

    getData().done( function(result){
      
        data = prepareData(result);

        options = {'title':'Quantidade de Músicas por Gênero',
                'width':900,
                'height':400,
                is3D: true};

        // Instantiate and draw our chart, passing in some options.
        chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);

        google.visualization.events.addListener(chart, 'select', selectHandler);

        function selectHandler(e) {
            console.log( chart.getSelection() );

            var item = chart.getSelection()[0];
            if ( item != null ){
                var idGenero = data.getFormattedValue(item.row, 2); 
                var nomeGenero = data.getFormattedValue(item.row, 0); 
                
                if ( idGenero != null && idGenero != 0){
                    fixarGenero(idGenero, nomeGenero);
                }
            }
        }
    });
}


var functionRefreshGrafico = function(){
    getData().done( function(result){
        data = prepareData(result);
        chart.draw(data, options);
    });
}

$("#btnRefreshGrafico").click( functionRefreshGrafico );

$(document).ready(function(){
    $(document).ready(drawChart); 
});


</script>


<style type="text/css">

body.modal-open {
    overflow: visible;
}

#table-musicas tr{
  cursor: pointer;
}

</style>


  <jsp:include page="/WEB-INF/views/bottom.jsp" />