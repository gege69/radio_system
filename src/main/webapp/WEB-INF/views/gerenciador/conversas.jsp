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

          <div class="row">   
                   
            <div class="col-lg-6 col-md-6">

              <div class="row">
                <div class="col-lg-12">
                  <div class="pull-right">
                    <a class="btn btn-primary" href="#"><i class="fa fa-envelope-o"></i> Nova Mensagem</a>
                  </div>
                </div>  
              </div>

              <div class="row spacer-vertical10">            
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
                        <th data-field="dataCriacao">Data Conversa</th>
                        <th data-field="dataVigenciaInicio">Vigência Início</th>
                        <th data-field="dataVigenciaFim">Vigência Fim</th>
                        <th data-field="ambiente.nome">Ambiente</th>
                    </tr>
                  </thead>
                </table>
              </div>
            </div>
          
            <div class="col-lg-6 col-md-6">
              
              <div class="panel panel-default">
                <div class="panel-body">
                  <div id="conversa" class="conversa">
                  </div>
                  
                  <form action="#" id="form-evento" class="form" >
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" id="idConversa" value="" />
                
                    <div class="col-lg-10 col-md-9 col-sm-10 col-xs-10">
                      <div class="form-group">
                        <textarea class="form-control" rows="3" id="conteudo" name="conteudo"></textarea>
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
<div class="col-lg-12 col-md-12">
  <div class="{{:htmlclass}} col-lg-10 col-md-10 col-sm-10 col-xs-10">
    <p>{{:conteudo}}</p>
    <span>{{:usuario.nome}}</span><span class="tempo"> - {{:dataEnvio}}</span>
  </div>
</div>
</script>  

<script src="${context}/js/gerenciador/conversas.js"></script>


<style type="text/css">

.tabela-conversas tr{
  cursor: pointer;
}

.conversa {
  height: 300px;
  overflow: auto;

}

.self {
  margin: 5px 5px 5px 5px;
  padding: 5px 5px 5px 5px;
  border: 1px solid #D5C3C3;

  float : right;
}

.other {
  margin: 5px 5px 5px 5px;
  padding: 5px 5px 5px 5px;
  border: 1px solid #D5C3C3;

  float : left;
}

.tempo {
  font-size: small;
  color: #9d9d9d;
}

</style>



<jsp:include page="/WEB-INF/views/bottom.jsp" />