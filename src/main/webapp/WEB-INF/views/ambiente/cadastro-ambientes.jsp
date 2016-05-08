<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">

    <div class="row">

      <div class="row">
        <div class="col-lg-12 col-md-12" id="alertArea">
        </div>
      </div>

      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Administrar Ambientes<br/>
            <small>Você possui ${qtdAmbientes} ambiente(es) cadastrado(s)</small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
            
              <table  
                 id="table-admin"
                 data-toggle="table"
                 data-url="${context}/ambientes"
                 data-height="400"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=5
                 data-search="true"
                 data-locale = "pt_BR"
                 data-unique-id="idAmbiente"
                 data-query-params="queryParams" >
                <thead>
                  <tr>
                      <th data-field="idAmbiente" class="col-lg-1 col-md-1 col-sm-1 col-xs-1" data-formatter="idFormatter">Id</th>
                      <th data-field="nome" class="col-lg-5 col-md-5 col-sm-5 col-xs-5" data-formatter="nomeFormatter">Nome</th>
                      <th data-field="editar" class="col-lg-2 col-md-2 col-sm-2 col-xs-2" data-formatter="editarFormatter" data-halign="center" data-align="center">Editar</th>
                      <th data-field="espelhar" class="col-lg-2 col-md-2 col-sm-2 col-xs-2" data-formatter="espelharFormatter" data-halign="center" data-align="center">Espelhar</th>
                      <th data-field="ativo" class="col-lg-2 col-md-2 col-sm-2 col-xs-2" data-formatter="ativarFormatter" data-halign="center" data-align="center">Ativar/Inativar</th>
                  </tr>
                </thead>
              </table>
            
            </div>
          </div>
          
<!--           <div class="row"> -->
<!--             <div class="col-lg-10 col-md-10"> -->
<!--               <div class="checkbox"> -->
<!--                 <label> -->
<!--                   <input type="checkbox" id="check_pacote"> Exibir somente ambientes com pacote de Programetes opcionais -->
<!--                 </label> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
          
          <div class="spacer-vertical40"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">          
                <a class="btn btn-primary" href="${context}/ambientes/new">Adicionar Novo Ambiente</a>
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



<div class="modal fade" id="myDialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="titulo-modal">Inativar Ambiente</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="inativarAmbienteForm" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="hidden" id="idAmbienteDialog" name="idAmbiente" value="0">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <h4><span id="nomeAmbiente"></span></h4>
            </div>
          
            <div class="spacer-vertical10"></div>

            <div class="col-lg-12 col-md-12">
              <p>Deseja realmente inativar esse Ambiente?</p>
              <p>Não será possível utilizar o ambiente até que ele seja ativado novamente.</p>
            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnNaoDialog" data-dismiss="modal">Não</button>
        <button type="button" class="btn btn-primary" id="btnConfirmar">Sim</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<script type="text/javascript" src="${context}/js/required/jquery.serializejson.js" defer></script>
<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>
<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">



<script type="text/javascript">

    var pagina = 0, limit = 6;

    function queryParams(params) {

        params.pageNumber = $('#table-admin').bootstrapTable('getOptions').pageNumber;
        
        return params;
    }

    function idFormatter(value, row) {
        return '<a class="btn btn-link" href="${context}/ambientes/'+ row.idAmbiente +'/view">' + value + '</a>';
    }

    function nomeFormatter(value, row) {

        var texto = value;
        if ( row.ativo == false )
            texto = texto + " (inativo)";
        return '<a class="btn btn-link" href="${context}/ambientes/'+ row.idAmbiente +'/view">' + texto + '</a>';
    }
    
    function espelharFormatter(value, row) {
        return '<a class="btn btn-link" href="${context}/ambientes/'+ row.idAmbiente +'/espelhar"> <i class="fa fa-lg fa-files-o"></i></a>';
    }
    
    function editarFormatter(value, row) {
        return '<a class="btn btn-link" href="${context}/ambientes/'+ row.idAmbiente +'/editar"> <i class="fa fa-lg fa-pencil-square-o"></i></a>';
    }

    function ativarFormatter(value, row) {
        var icon = value == true ? "fa-toggle-on" : "fa-toggle-off";
        return '<a class="btn btn-link ativar-class" id="btnAtivarInativar" idAmbiente="'+ row.idAmbiente +'" href="#"><i class="fa fa-lg '+ icon +'"></i></a>';
    }

    var toggleAtivo = function( element )
    {
        var idAmbiente = element.attr("idAmbiente");

        var row = $('#table-admin').bootstrapTable('getRowByUniqueId', idAmbiente);
        
        if ( row.ativo ){
            $('#idAmbienteDialog').val( idAmbiente );
            
            $('#nomeAmbiente').html( idAmbiente + " - " + row.nome )
            
            $('#myDialog').modal('show');
        }
        else {
            ativarAmbiente(idAmbiente);
        }
    }
    

    function ativarAmbiente(idAmbiente)
    {
        if ( idAmbiente == null || idAmbiente == 0 )
            preencheAlertGeral( "alertArea", "Ambiente não encontrado" );

        var url = buildUrl( "/ambientes/ativar");

        var formData =  $('#inativarAmbienteForm').serializeJSON();
        formData.idAmbiente = idAmbiente;
        
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: url,
            data:  JSON.stringify( formData ),
            dataType: 'json'
        }).done( function(json){ 

            if (json.ok == 1){
                preencheAlertGeral( "alertArea", "Ambiente desativado com sucesso", "success" );
                $("#table-admin").bootstrapTable('refresh');
            }
            else{
                preencheErros( json.errors );
            }
        }); 
    }

    function desativarAmbiente() 
    {
        var idAmbiente = $("#idAmbienteDialog").val();
        
        if ( idAmbiente == null || idAmbiente == 0 )
            preencheAlertGeral( "alertArea", "Ambiente não encontrado" );

        var url = buildUrl( "/ambientes/inativar");
        
        var formData =  $('#inativarAmbienteForm').serializeJSON();
        
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: url,
            data:  JSON.stringify(formData),
            dataType: 'json'
        }).done( function(json){ 

            if (json.ok == 1){
                preencheAlertGeral( "alertArea", "Ambiente desativado com sucesso", "success" );
                $("#table-admin").bootstrapTable('refresh');
                $('#myDialog').modal('toggle');
            }
            else{
                $('#myDialog').modal('toggle');
                preencheErros( json.errors );
            }
        });
    } 


    $(function(){
        
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        $("#table-admin").on( 'load-success.bs.table', function( e, data ) {
            $(".ativar-class").click( function(e){
                e.preventDefault();
                toggleAtivo($(this));
            });
        });

        $("#table-admin").on( 'page-change.bs.table', function ( e, number, size ){
            $(".ativar-class").click( function(e){
                e.preventDefault();
                toggleAtivo($(this));
            });
        });

        $("#btnConfirmar").click( function(){
            desativarAmbiente();
        });
       
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />