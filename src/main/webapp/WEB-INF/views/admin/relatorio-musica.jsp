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
              <h3>Relatório de Músicas </h3>
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


                      <div class="container col-md-10" id="view-container">
                        <div id="chart_div" style="width:900; height:500"></div>
                      </div>
                      
                      <div class="col-md-2">
                        <button href="#" class="btn btn-success" id="btnRefresh">
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
                  <p><h4><span id="filtroGenero"></span></h4></p>

                  <button type="button" class="btn btn-warning" style="display:none;" id="btnLimparFiltro">
                    <i class="fa fa-refresh"></i>
                    Limpar Filtro
                  </button>
                </div>
              </div>

              <div class="row">
                <div class="col-lg-12 col-md-12">
                  <p><span id="totalMusicas">0 músicas para essa pesquisa</span></p>
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
                 data-unique-id="idMidia"
                 data-search="true"
                 data-page-list = "[7,15,30]"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="idMidia" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">ID</th>
                      <th data-field="nome" class="col-lg-4 col-md-3 col-sm-3 col-xs-3">Nome do Arquivo</th>
                      <th data-field="generosResumo" class="col-lg-2 col-md-3 col-sm-3 col-xs-3">Gênero(s)</th>
                      <th data-field="artist" class="col-lg-2 col-md-2 col-sm-2 col-sm-2">Artista</th>
                      <th data-field="idMidia" data-formatter="editarFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Editar</th>
                      <th data-field="idMidia" data-formatter="removerFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Remover</th>
                      <th data-field="idMidia" data-formatter="playFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-1">Tocar</th>
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



<div class="modal fade" id="myModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Alterar dados da Música</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="altera-nome-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idMidia" name="idMidia" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">

              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Nome</label>
                <div class="col-lg-8 col-md-10">
                  <input type="text" class="form-control" id="nomeMidia" name="nome">
                </div>
              </div>

              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Descrição</label>
                <div class="col-lg-8 col-md-10">
                  <input type="text" class="form-control" id="descricaoMidia" name="descricao">
                </div>
              </div>

              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Artista</label>
                <div class="col-lg-8 col-md-10">
                  <input type="text" class="form-control" id="artistaMidia" name="artist">
                </div>
              </div>

              <h4>Gêneros da música</h4>

              <div class="container col-md-12" id="viewContainerModal">
              </div>

            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
        <button type="button" class="btn btn-primary" id="btnSalvar">Alterar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<div class="modal fade" id="myDialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Remover Música</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="remove-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idMidiaDialog" name="idMidia" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <h4><span id="nomeMusica"></span></h4>
            </div>
          
            <div class="spacer-vertical10"></div>

            <div class="col-lg-12 col-md-12">
              Deseja realmente remover essa Música?
            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnNaoDialog" data-dismiss="modal">Não</button>
        <button type="button" class="btn btn-primary" id="btnConfirmarDelete">Sim</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${context}/js/required/jquery.serializejson.js" defer></script>

<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>
<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">

<script src="${context}/js/required/jsrender.min.js"></script>
<script src="${context}/js/required/reckon.min.js"></script>

<link rel="stylesheet" href="https://cdn.plyr.io/1.3.7/plyr.css" defer>
<script src="https://cdn.plyr.io/1.3.7/plyr.js" defer></script>



<script id="viewTmplModal" type="text/x-jsrender">
    
      <div class="checkbox col-lg-4 col-md-4 col-sm-6 col-xs-12">
        <label>
          <input type="checkbox" class="checkbox-genero-editar" id="genero-editar-{{:idGenero}}" name="genero[idGenero]" value="{{:idGenero}}"> {{:descricao}}
        </label>
      </div>
      
</script>  

<script src="${context}/js/admin/grid-musica.js" charset="UTF-8"></script>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

google.load('visualization', '1.0', {'packages':['corechart']});

google.setOnLoadCallback(drawChart);

var listaGeneros = null;

function drawChart() {

    var url = buildUrl( "/admin/generos/relatorio" );

    return $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: url,    
        dataType: 'json'
    }).done( function(result){
      
        listaGeneros = result;
        
        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Gênero');
        data.addColumn('number', 'Músicas');
        data.addColumn('number', 'id Gênero');

        $.each(result, function(i, el){
            data.addRow([el.genero, el.quantidadeMidias, el.idGenero ]);
        });

        var options = {'title':'Quantidade de Músicas por Gênero',
                'width':900,
                'height':400,
                is3D: true};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);

        google.visualization.events.addListener(chart, 'select', selectHandler);

        function selectHandler(e) {
            console.log( chart.getSelection() );

            var item = chart.getSelection()[0];
            if ( item != null ){
                generoFixado = data.getFormattedValue(item.row, 2); 
                generoStr = data.getFormattedValue(item.row, 0); 
                
                if ( generoFixado != null && generoFixado != 0){
                    $("#table-musicas").bootstrapTable('refresh');
                    $("#filtroGenero").html("Listagem mostrando apenas músicas do Gênero : " + generoStr);
                    $("#btnLimparFiltro").show();
                    jump("table-musicas");
                }
            }
        }
    });
}


$("#btnLimparFiltro").click(function(){
    $("#filtroGenero").empty();
    generoFixado = 0; 
    jump("table-musicas");
    $("#table-musicas").bootstrapTable('refresh');
    $("#btnLimparFiltro").hide();
});

$("#btnRefresh").click(function(){
    document.location.reload(true);
});


</script>



  <jsp:include page="/WEB-INF/views/bottom.jsp" />