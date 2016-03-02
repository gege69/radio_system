<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Upload de Chamadas de Veículos<br/>
<%--             <small>Você possui ${qtdGeneros} gênero(os) cadastrado(s)</small> --%>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              
              <div class="row">
                <div class="form-group">
                  <label for="login" class="control-label col-lg-3 col-sm-3 col-md-3">Componente da Chamada:</label>
                  <div class="col-lg-6 col-md-6">
                    <select class="form-control" id="categoria-combo" name="categoria">
                      <option value="veic_frase_ini" selected="selected">Frase Inicial</option>
                      <option value="veic_marca">Marca</option>
                      <option value="veic_modelo">Modelo</option>
                      <option value="veic_cor">Cor</option>
                      <option value="veic_frase_fim">Frase Final</option>
                    </select>
                  </div>
                </div> 
              </div>

              <div class="spacer-vertical20"></div>
              
              <div class="row">
                <div class="col-lg-6 col-md-7">

                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf" />


                  <div id="drop-area-div" class="uploader">
                    <div>Arraste arquivos de <span id="categoria-span">Frase Inicial</span> aqui</div>
                    <div class="or">-ou-</div>
                    <div class="browser">
                      <label>
                      <span id="botao-arquivo">Clique no botão para adicionar arquivos de 'Frase Inicial'</span>
                      <input type="file" name="files[]" multiple="multiple" title="Clique para adicionar">
                      </label>
                    </div>
                  </div>

                </div>
              
              </div>
            
              <div class="spacer-vertical20"></div>
              
              <table  
                 id="table-chamadas-veiculos"
                 data-toggle="table"
                 data-url="${context}/admin/midias"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=6
                 data-locale = "pt_BR"
                 data-page-list = "[6,12,25]"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="nome" class="col-lg-7 col-md-7">Nome</th>
                      <th data-field="idMidia" data-formatter="editarFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-2">Trocar Nome</th>
                      <th data-field="idMidia" data-formatter="removerFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-2">Remover</th>
                      <th data-field="idMidia" data-formatter="playFormatter" class="col-lg-1 col-md-1 col-sm-1 col-xs-2">Tocar</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
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

<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>

<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>
<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">

<script src="${context}/js/required/dmuploader.js"></script>


<script type="text/javascript">

    var pagina = 0, limit = 6;

    function queryParams(params) {

        params.pageNumber = $('#table-chamadas-veiculos').bootstrapTable('getOptions').pageNumber;
        params.codigo = $("#categoria-combo").val();
        
        return params;
    };

    function editarFormatter(value, row) {
        return '<a class="btn btn-link editar-class" id="btnEditarChamada" idMidia="'+ row.idMidia +'" href="#"> <i class="fa fa-lg fa-pencil-square-o"></i></a>';
    }
    
    function removerFormatter(value, row) {
        return '<a class="btn btn-link remover-class" id="btnRemoverChamada" idMidia="'+ row.idMidia +'" href="#"> <i class="fa fa-lg fa-times"></i></a>';
    }

    function playFormatter(value, row) {
        return '<a class="btn btn-link play-class" id="btnPlayChamada" idMidia="'+ row.idMidia +'" href="#"> <i class="fa fa-lg fa-play-circle"></i></a>';
    }
    
    var editarGeneroSelecionado = function( e, row, el )
    {
        if ( row == null ) 
            return;
        
        if ( row.idGenero == null || row.idGenero == 0 )
        {
            preencheAlertGeral("alertArea", "Gênero não foi selecionado corretamente.", "danger");
            return false;
        }
        else
        {
            var url = buildUrl( "/admin/generos/{idGenero}/view", { 
                idGenero: row.idGenero 
            });
            
            window.location = url;
        }
    };
    
    
    var buscaValor = function()
    {
        var result = $("#categoria-combo").val();
        console.log(result);
        return result;
    }
    
   
    
    
    
    // LEMBRAR DE MINIFICAR A BOSTA DO UPLOADER
    var configuraUploader = function(){

        var url = buildUrl( "/api/admin/upload-chamadas-veiculos" );

        $('#drop-area-div').dmUploader({
          url: url,
          dataType: 'json',
          extraData: { 
              _csrf: $("#csrf").val(), 
              codigo : buscaValor 
          },
          onComplete: function(){
             $("#table-chamadas-veiculos").bootstrapTable('refresh');
          },
          onUploadProgress: function(id, percent){
            var percentStr = percent + '%';
//             $.danidemo.updateFileProgress(id, percentStr);
          },
          onUploadError: function(id, message){
              
              console.log( "Tudo errado !!");
//             $.danidemo.updateFileStatus(id, 'error', message);

//             $.danidemo.addLog('#demo-debug', 'error', 'Failed to Upload file #' + id + ': ' + message);
          },
          onFileTypeError: function(file){
//             $.danidemo.addLog('#demo-debug', 'error', 'File \'' + file.name + '\' cannot be added: must be an image');
          },
          onFallbackMode: function(message){
//             $.danidemo.addLog('#demo-debug', 'info', 'Browser not supported(do something else here!): ' + message);
          }
        });
        
    };
    

    $(function(){
        
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
 
        $("#categoria-combo").change( function() {
           $("#table-chamadas-veiculos").bootstrapTable('refresh');
           var texto = $("#categoria-combo :selected").text();
           $("#categoria-span").html( texto );
           $("#botao-arquivo").html( "Clique para adicionar arquivos de '" + texto +"'" );
           console.log( $(this).val() );
           configuraUploader( $(this).val() );
        });

        
        configuraUploader();
       
    });

</script>

<style type="text/css">

#table-generos tr{
  cursor: pointer;
}

.uploader
{
  border: 2px dotted #A5A5C7;
  width: 100%;
  color: #92AAB0;
  text-align: center;
  vertical-align: middle;
  padding: 30px 0px;
  margin-bottom: 10px;
  font-size: 200%; 

  cursor: default;

  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
.uploader div.or {
  font-size: 50%;
  font-weight: bold;
  color: #C0C0C0;
  padding: 10px;
}

.uploader div.browser label {
  background-color: #5a7bc2;
  padding: 5px 15px;
  color: white;
  padding: 6px 0px;
  font-size: 40%;
  font-weight: bold;
  cursor: pointer;
  border-radius: 2px;
  position: relative;
  overflow: hidden;
  display: block;
  width: 300px;
  margin: 20px auto 0px auto;

  box-shadow: 2px 2px 2px #888888;
}

.uploader div.browser span {
  cursor: pointer;
}


.uploader div.browser input {
  position: absolute;
  top: 0;
  right: 0;
  margin: 0;
  border: solid transparent;
  border-width: 0 0 100px 200px;
  opacity: .0;
  filter: alpha(opacity= 0);
  -o-transform: translate(250px,-50px) scale(1);
  -moz-transform: translate(-300px,0) scale(4);
  direction: ltr;
  cursor: pointer;
}

.uploader div.browser label:hover {
  background-color: #427fed;
}

</style>

<jsp:include page="/WEB-INF/views/bottom.jsp" />