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
              <h3>Conversão de Músicas</h3>
              <h4><small>As músicas MP3 enviadas ao sistema irão automaticamente ser convertidas para o formato OGG.<br/>
                         Esse formato pode garantir um tamanho menor para as músicas, entretanto, é necessário escolher as configurações de conversão para não perder qualidade na conversão</small></h4>
            </div>
            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>
        
          <div class="spacer-vertical10"></div>

          <div class="row">
            <div class="col-lg-12 col-md-12">
              <form action="#" class="form-horizontal" id="conversaoForm" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf" />
              
                <div class="col-lg-12 col-md-12">
                  <h4><span id="nomeMusicaConverter"></span></h4>
                </div>
              
                <div class="spacer-vertical10"></div>

                <div class="form-group">
                  <label for="login" class="control-label col-sm-4 col-md-3">BitRate</label>
                  <div class="col-lg-4 col-md-4 col-sm-8">
                    <select class="form-control" id="bitRate" name="bitRate">
                      <option value="VARIABLE" selected="selected">BitRate Variável (VBR)</option>
                      <option value="AVERAGE" >BitRate Médio (ABR)</option>
                      <option value="CONSTANT" >BitRate Constante (CBR)</option>
                    </select>
                  </div>
                </div>
                
                <div class="form-group" id="divVariableBR" >
                  <label for="login" class="control-label col-sm-4 col-md-3">Valor do BitRate VARIÁVEL</label>
                  <div class="col-lg-6 col-md-6 col-sm-8">
                    <select class="form-control" id="variableBitRate" name="variableBitRate">
                    </select>
                  </div>
                </div>
                
                <div class="row">
                  <div class="col-lg-offset-3 col-lg-4 col-md-offset-3 col-md-4 col-sm-offset-4 col-sm-8" id="divExemplosBR" style="display: none;">
                    <div class="panel panel-default">
                      <div class="panel-body">
                        <p class="example">Exemplos de BitRate</p>
                        <p>96 kbps <small>(razoável)</small></p>
                        <p>128 kbps <small>(bom)</small></p>
                      </div>
                    </div>
                  </div>
                </div>
                
                <div class="form-group" id="divBR" style="display: none;">
                  <label for="login" class="control-label col-sm-4 col-md-3">Valor do BitRate</label>
                  <div class="col-lg-4 col-md-4 col-sm-8">
                    <input type="text" class="form-control valorBit" id="valorBitRate" name="valorBitRate" placeholder="Default 128 kbps">
                  </div>
                </div>
              </form>
            </div>
          </div>

          <div class="spacer-vertical20"></div>
  
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <a class="btn btn-primary" id="btnSalvarConversaoConfig" href="#">Salvar Alterações</a>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <button class="btn btn-danger" id="btnConverterTudo">Converter Tudo</button>    
              </div>          
            </div>
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


<div class="modal" id="dialogConverterTudo">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Converter Tudo</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="remove-midia-form" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf" />
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <h4>Deseja iniciar o processo para converter todas as músicas do servidor?</h4>
            </div>
          
            <div class="col-lg-12 col-md-12">
              Existem atualmente <span id="qtdMusicas">0</span> MP3 no servidor. Elas serão colocadas em uma fila de conversão.
            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnNaoDialog" data-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-primary" id="btnConfirmarDialog" data-dismiss="modal">Converter Tudo</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.7.2/jquery.serializejson.min.js"></script>
<script src="${context}/js/required/jquery.populate.min.js"></script>
<script type="text/javascript" src="${context}/js/required/jquery.mask.min.js" defer></script>

<script type="text/javascript">


    var salvar = function(){

        var url = buildUrl( "/admin/conversao-config" );

        var dados = $('#conversaoForm').serializeJSON();
        
        if ( dados.variableBitRate && dados.bitRate === "VARIABLE" ){
            dados.valorBitRate = dados.variableBitRate;
        }

        $.ajax({
            
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: 'json',
            data:  JSON.stringify( dados )
            
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

    var getDados = function()
    {
        var url = buildUrl( "/admin/conversao-config" );

        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: url,
            dataType: 'json'
        }).done( function(json) {
            
            removeErros();
            $('#conversaoForm').populate(json);
            
            if ( json.bitRate === "VARIABLE" )
              $("#variableBitRate").val(json.valorBitRate);

            verificaExibicao();
            jump('conversaoForm');
        });
    }

    
    var openDialogConverterTudo = function(){
        $('#dialogConverterTudo').modal('show');
    }


    var converterTudo = function(){

        var url = buildUrl( "/admin/conversao-config/tudo" );

        $.ajax({
            
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: 'json'
        }).done( function(json){ 

            if (json.ok == 1 ){
                preencheAlertGeral( "alertArea", "Processo iniciado com sucesso. Você pode acompanhar o progresso pelas telas de 'Upload Música' e 'Gerência de Músicas'", "success" );
                jump(''); // topo da pagina
            }
        }).fail( function(jqXHR, textStatus, errorThrown){
            var errors = jqXHR.responseJSON.errors;
            preencheErros( errors );
        });
    };
    
    
    var getQuantidadeMusicas = function()
    {
        var url = buildUrl( "/admin/conversao-config/quantidade-mp3" );

        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: url,
            dataType: 'json'
        }).done( function(json) {
            $("#qtdMusicas").empty();
            $("#qtdMusicas").html(json.qtd);
        });
    }

    var listaVariableBitRate = function(){
       
       var url = buildUrl( "/admin/bitrates" );

       return $.ajax({
           type: 'GET',
           contentType: 'application/json',
           url: url,    
           dataType: 'json'
       }).done(function(json){
           $.each( json , function (i, option){
               $("#variableBitRate").append($('<option>', { 
                   value: option.valor,
                   text : option.descricao  
               }));
           });
       });
    }

    var verificaExibicao = function(){
        var valor = $("#bitRate").val();
        
        removeErros();
        if ( valor === "VARIABLE"){
            $("#divVariableBR").show();
            $("#divBR").hide();
            $("#divExemplosBR").hide();
            $("#valorBitRate").val('');
        } else {
            $("#divVariableBR").hide();
            $("#divBR").show();
            $("#divExemplosBR").show();
        }
    }

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        $('#btnSalvarConversaoConfig').on('click', salvar);
        
        getDados();

        $("#bitRate").on("change", function(){
            verificaExibicao();
        });

        listaVariableBitRate();

        $(".valorBit").mask('000');  
        
        $("#btnConverterTudo").click(function(){
            openDialogConverterTudo();
        });
        
        getQuantidadeMusicas();
        
        $("#btnConfirmarDialog").click(function(){
            converterTudo();
        });

    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />