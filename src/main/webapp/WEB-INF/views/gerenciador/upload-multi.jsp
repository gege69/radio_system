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
              <h3>Upload multi-Ambiente <br/>
                <small>Marque a categoria da mídia que deseja enviar e os ambientes que devem recebê-la</small>
              </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>
          
          <form action="#" 
            method="POST" 
            id="ambiente-upload-multi" 
            class="form" >
          
            <div class="row">
            
              <div class="col-lg-6 col-md-6">
                <div class="panel panel-default">
                  <div class="panel-body">
                    <div class="row">
                      
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                      <input type="hidden" id="idCategoria" name="idCategoria" value="${idCategoria}">
                      
                      <input type="file" id="fileupload" name="file" multiple style="display : none;">

                      <div class="col-lg-2 col-md-3 col-sm-4">
                        <span class="btn btn-primary btn-file">
                            Escolha os arquivos<input type="file" id="outrofileupload" name="file2" multiple>
                        </span>
                      </div>

                      <input type="file" id="fileupload" name="file" multiple style="display : none;">

                      <div class="col-lg-offset-4 col-md-offset-5 col-sm-offset-4">          
                        <p class="form-control-static" id="static-arquivos"></p>
                      </div>

                      <div class="spacer-vertical10"></div>

                      <div class="col-lg-12" id="resultados">
                        <div id="progress" class="progress">
                            <div class="progress-bar progress-bar-success"></div>
                        </div>
                        <div id="files" class="files"></div>            
                      </div>

                      <div class="spacer-vertical10"></div>

                      <div class="col-lg-12">
                        <div class="form-group">
                          <label for="file">Descrição:</label>
                          <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Preencha alguma descrição da mídia">
                        </div>
                      </div>

                      <div class="col-lg-12">
                        <div class="form-group">
                          <label class="control-label" for="chave">Data Início Validade:</label>
                          <div class="input-group date col-lg-5 col-md-7 col-sm-12">
                            <input type="text" class="form-control" id="dataInicio" name="dataInicio">
                              <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                              </span>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label class="control-label" for="chave">Data Fim Validade:</label>
                          <div class="input-group date col-lg-5 col-md-7 col-sm-12">
                            <input type="text" class="form-control" id="dataFim" name="dataFim">
                              <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                              </span>
                          </div>
                        </div>
                      </div>
                      

                      <div class="col-lg-12">
                        <div class="form-group">
                          <label for="file">Categorias:</label>
                          <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                            <div class="form-group" id="checkBoxContainer">
                            </div>
                          </div>
                        </div>
                      </div>
  
                    </div> 
                  </div>
                </div>
              </div>
              
              <div class="col-lg-6 col-md-6">
                <table  
                 id="table-ambientes"
                 data-toggle="table"
                 data-select-item-name="selectItem"
                 data-url="${context}/ambientes"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=7
                 data-locale = "pt_BR"
                 data-query-params="queryParamsAmbiente" >
                  <thead>
                    <tr>
                        <th data-field="state" data-checkbox="true"></th>
                        <th data-field="nome">Ambientes</th>
                    </tr>
                  </thead>
                </table>
              </div>
              
            </div>
          
                                
          </form>

          <div class="spacer-vertical40"></div>
        
          <div class="row row-centered">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
              <div class="">
                <a class="btn btn-primary text-center" href="#" id="btnIniciar">
                  <i class="fa fa-3x fa-cloud-upload pull-left"></i>
                  Upload Mídia para
                  ambientes<br/> selecionados</a>
              </div>
            </div>
          </div>          
          
          <div class="spacer-vertical40"></div>

          <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
              </div>            
            </div>
          </div>
        
      </div>
    </div>
    
      
  </div> <!-- /container -->


<script src="${context}/js/required/jsrender.min.js"></script>
<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>

<script src="${context}/js/required/jquery-ui.min.js"></script>

<script src="${context}/js/required/jquery.iframe-transport.js"></script>
<script src="${context}/js/required/jquery.fileupload.js"></script>

<script src="${context}/js/required/bootstrap-datepicker.min.js"></script>
<script src="${context}/js/required/bootstrap-datepicker.pt-BR.min.js"></script>
<link href="${context}/css/bootstrap-datepicker3.css" rel="stylesheet">

<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">

<script id="viewTmpl" type="text/x-jsrender">

      <div class="checkbox col-lg-6 col-md-6 col-sm-6 col-xs-6">
        <label>
          <input type="checkbox" id="inlineCheck{{:idCategoria}}" name="categorias[idCategoria]" class="checkbox-categoria" value="{{:idCategoria}}"> {{:nome}}
        </label>
      </div>
</script>  



<script type="text/javascript">

    var $table = $('#table-ambientes');
    
    
    function queryParamsAmbiente(params) {

        params.pageNumber = $table.bootstrapTable('getOptions').pageNumber;
        
        return params;
    }
    
    
    var listaCategorias = function( doJump ){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/categorias?simpleUpload=true',
            dataType: 'json'
        }).done( function(json){
            makeListTmpl(json);
            
            var lista = json.rows;
            
            var id_categoria_tela = $('#idCategoria').val();

            $.each( lista, function( idx, obj ){

                if ( obj.idCategoria == id_categoria_tela )
                    $('#inlineCheck'+obj.idCategoria).prop('checked', true);
            });
            
        } );
    }
    
    
    var makeListTmpl = function(json){
        
        var tmpl = $.templates('#viewTmpl');
        
        $('#checkBoxContainer').empty();
        
        var content = tmpl.render(json.rows);"/home/pazin/workspace/java/projetos/bootstrap-table-examples/methods/checkBy-uncheckBy.html"
        
        $('#checkBoxContainer').append(content);
    };
    


    var getAmbientesSelecionados = function()
    {
        var array_values = [];

        var selecao = $table.bootstrapTable('getSelections');

        $(selecao).each(function(){
            var linha = this;
            array_values.push( parseInt( linha.idAmbiente ) );
        });
        
        return array_values;
    }
    

    var getCategoriasSelecionadas = function()
    {
        var array_values = [];

        $('.checkbox-categoria').each( function() {
            if( $(this).is(':checked') ) {
                array_values.push( parseInt( $(this).val() ) );
            }
        });
        
        return array_values;
    }


    var configuraUploader = function() 
    {

        var _url = buildUrl( "/api/upload-midia-multi-ambientes" );
        
        $('#fileupload').fileupload({
            dataType: 'json',
            url : _url,
            formData: { 
                _csrf: $("#csrf").val() 
            },
            add: function (e, data) {
                
                var array_values = getCategoriasSelecionadas();

                var array_ambientes = getAmbientesSelecionados();

                data.formData = { 
                                  _csrf: $("#csrf").val(), 
                                  "categorias[]" : array_values,
                                  "ambientes[]" : array_ambientes,
                                  iniciovalidade : $("#dataInicio").val(),
                                  fimvalidade : $("#dataFim").val()
                                };
                
                $("#btnIniciar").prop("disabled", true);
                
                data.submit();
            },
            fail: function (e, data) {
                var errors = data.jqXHR.responseJSON.errors;
                preencheErros( errors );
                $("#btnIniciar").prop("disabled", false);
                $('#progress .progress-bar').css(
                        'width',
                        0 + '%'
                    );
            },
            stop : function(e, data) {
                preencheAlertGeral( "alertArea", "Upload realizado com sucesso", "success" );
                $("#btnIniciar").prop("disabled", false);
                $('#progress .progress-bar').css(
                        'width',
                        0 + '%'
                    );
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .progress-bar').css(
                    'width',
                    progress + '%'
                );
                
                
            } 
        }); 
        
    }

    var iniciarUpload = function()
    {
        var filesList = $('#outrofileupload')[0].files;
        
        if ( filesList == null || filesList.length == 0 ) { 
            preencheAlertGeral( "alertArea", "Selecione as músicas e algum gênero primeiro.");
            return;
        }
        
        var selecao = $table.bootstrapTable('getSelections');
        
        if ( selecao.length <= 0 )
        {
            preencheAlertGeral("alertArea", "Escolha pelo menos um ambiente para receber o arquivo.", "danger");
            return false;
        }

        var array_values = getCategoriasSelecionadas();
        
        if ( array_values == null || array_values.length == 0 ){
            preencheAlertGeral( "alertArea", "Escolha pelo menos uma categoria para classificar o arquivo.");
            return;
        }
        
        $('#fileupload').fileupload('add', { files : filesList } );
    }

    var mostrarArquivos = function()
    {
        var filesList = $('#outrofileupload')[0].files; 
       
        if ( filesList && filesList.length > 0 )
          $("#static-arquivos").html( filesList.length + " arquivo(s) selecionado(s)" );
    }

    
//     // futuramente tornar esse Upload em ajax
//     var preparaUpload = function()
//     {
//         $('input[name="ambientes[]"]').remove();
        
//         // pegar as categorias e criar inputs hidden no form
//         var selecao = $table.bootstrapTable('getSelections');
        
//         if ( selecao.length <= 0 )
//         {
//             preencheAlertGeral("alertArea", "Escolha pelo menos um ambiente para receber o arquivo.", "danger");
//             return false;
//         }
        
//         var categoriasCheckadas = $( ".check-categorias:checked" ).length;
        
//         if ( categoriasCheckadas <=0 )
//         {
//             preencheAlertGeral("alertArea", "Escolha pelo menos uma categoria para classificar o arquivo.", "danger");
//             return false;
//         }
            
//         $(selecao).each(function(){
//             var linha = this;

//             $('<input>').attr({
//                 type: 'hidden',
//                 id : 'ambiente',
//                 name : 'ambientes[]',
//                 value : linha.idAmbiente
//             }).appendTo('#ambiente-upload-multi');
//         });
        
//         return true;
//     }

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        $('.input-group.date').datepicker({
            format: "dd/mm/yyyy",
            clearBtn: true,
            language: "pt-BR",
            todayBtn : "linked",
            autoclose : true
        });
        
        listaCategorias();

        configuraUploader();

        $("#outrofileupload").click(function(){
            $(this).val();
        });

        $("#outrofileupload").change(function(){
            mostrarArquivos();
        });

        $("#btnIniciar").click( function(){
            iniciarUpload();  
        });
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />