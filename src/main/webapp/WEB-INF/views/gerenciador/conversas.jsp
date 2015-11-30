<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Mensagens</h3>
          
          <div class="col-lg-6 col-md-6">
            <table  
             id="table-conversas"
             class="tabela-conversas"
             data-toggle="table"
             data-url="${context}/conversas"
             data-side-pagination="server"
             data-pagination="true"
             data-page-size=7
             data-page-list="[5]"
             data-locale = "pt_BR"
             data-height="400"
             data-query-params="queryParamsConversas" >
              <thead>
                <tr>
<!--                     <th data-field="idConversa" data-formatter="linhaFormatter">Mensagens</th> -->
                    <th data-field="idConversa">Id</th>
                    <th data-field="dataVigenciaInicio">Início</th>
                    <th data-field="dataVigenciaFim">Fim</th>
                    <th data-field="idConversa" data-formatter="ultimaMsgFormatter">Ult Msg</th>
                </tr>
              </thead>
            </table>
          </div>
          
          <div class="col-lg-6 col-md-6">
            
            <div class="panel panel-default">
              <div class="panel-body">
                <div id="conversa" class="fill">
                  <ol class="conversation" id="container">
                  </ol>
                </div>
                
                <form action="#" id="form-evento" class="form" >
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                  <input type="hidden" id="idConversa" value="" />
              
                  <div class="col-lg-10 col-md-9 col-sm-10 col-xs-10">
                    <div class="form-group">
                      <input type="text" class="form-control" id="conteudo" name="conteudo" />
                    </div>
                  </div>
                  <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 pull-right">
                    <a class="btn btn-default" href="#">Enviar</a>
                  </div>
                </form>
              </div>
            </div>
          
          </div>

          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
        <div class="">
        </div>            
      </div>
      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
        <div class="pull-right">
          <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
        </div>
      </div>
    </div>
      
  </div> <!-- /container -->



<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">

<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>


<script src="${context}/js/required/jsrender.min.js"></script>


<script id="viewTmplMensagem" type="text/x-jsrender">
<li class="self">
  <div class="messages">
    <p>{{:conteudo}}</p>
    <span>{{:usuario.nome}}</span>
  </div>
</li>
</script>  

<script src="${context}/js/gerenciador/conversas.js"></script>


<style type="text/css">

.tabela-conversas tr{
  cursor: pointer;
}

.conversation li {
  display: flex;
}



html,body{
height:100%;
}

.fill { 
    min-height: 100%;
    height: 300px;
    overflow: auto;
}
  
.messages {
  margin-right: 10px;
}  


</style>



<jsp:include page="/WEB-INF/views/bottom.jsp" />