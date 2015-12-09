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
                    <a class="btn btn-primary" href="#" id="btnNovaMensagem"><i class="fa fa-envelope-o"></i> Nova Mensagem</a>
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
                        <th data-field="idConversa">Id</th>
                        <th data-field="dataCriacao">Data Conversa</th>
                        <th data-field="ambiente.nome">Ambiente</th>
                        <th data-field="dataVigenciaInicio">Vigência Início</th>
                        <th data-field="dataVigenciaFim">Vigência Fim</th>
                    </tr>
                  </thead>
                </table>
              </div>
            </div>
          
            <div id="selecao-participantes" class="col-lg-6 col-md-6" style="display: none;" >
              
              <div class="panel panel-default">
                <div class="panel-body">

                  <h4>Escolha os participantes da Conversação <br/><small>Você será incluído automaticamente</small></h4>
                  <div class="row spacer-vertical10">            
                    <table  
                     id="table-participantes"
                     class="table-participantes"
                     data-toggle="table"
                     data-url="${context}/usuarios?all=true"
                     data-side-pagination="server"
                     data-pagination="true"
                     data-page-size=6
                     data-page-list="[5]"
                     data-locale = "pt_BR"
                     data-height="300"
                     data-query-params="queryParamsParticipantes" >
                      <thead>
                        <tr>
                            <th data-field="state" data-checkbox="true"></th>
                            <th data-field="nome">Nome</th>
                            <th data-field="login">Login</th>
                        </tr>
                      </thead>
                    </table>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-12">
                      <div class="pull-right">
                        <a class="btn btn-primary" href="#" id="btnIniciarConversa">Iniciar <i class="fa fa-arrow-right"></i></a>
                      </div>
                    </div>  
                  </div>
                  
                </div>
              </div>
            </div>
          
            <div id="painel-mensagens" class="col-lg-6 col-md-6">

              <div class="panel panel-default">
                <div class="panel-body">
                
                  <form action="#" id="form-evento" class="form" >
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" id="idConversa" name="idConversa" value="" />
                    
                    <div class="row">
                      <div id="conversa" class="conversa col-lg-12 col-md-12">
                      </div>
                    </div>
                  
                    <div class="row">
                      <div class="col-lg-10 col-md-9 col-sm-10 col-xs-10">
                        <div class="form-group">
                          <textarea class="form-control" rows="3" id="conteudo" name="conteudo"></textarea>
                        </div>
                      </div>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 pull-right">
                        <a class="btn btn-primary" href="#">Enviar</a>
                      </div>
                    </div>
                    
                    <div class="row spacer-vertical10">
                    
                      <div class="form-group col-lg-7 col-md-7 col-sm-7">
                        <label for="login" class="control-label col-lg-3 col-md-3 col-sm-3">Início:</label>
                        <div class="input-group date col-lg-8 col-md-8 col-sm-6">
                          <input type="text" class="form-control" id="dataVigenciaInicio" name="dataVigenciaInicio"/>
                          <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                          </span>
                        </div>
                      </div>

                      <div class="form-group col-lg-7 col-md-7 col-sm-7">
                        <label for="login" class="control-label col-lg-3 col-md-3 col-sm-3">Fim:</label>
                        <div class="input-group date col-lg-8 col-md-8 col-sm-6">
                          <input type="text" class="form-control" id="dataVigenciaFim" name="dataVigenciaFim">
                          <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                          </span>
                        </div>
                      </div>
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
<link href="${context}/css/bootstrap-datepicker3.css" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>

<script src="${context}/js/required/bootstrap-datepicker.min.js"></script>
<script src="${context}/js/required/bootstrap-datepicker.pt-BR.min.js"></script>


<script src="${context}/js/required/jsrender.min.js"></script>


<script id="viewTmplMensagem" type="text/x-jsrender">
<div class="col-lg-12 col-md-12">
  <div class="message {{:htmlclass}} col-lg-10 col-md-10 col-sm-10 col-xs-10">
    <p>{{:conteudo}}</p>
    <div class="nome-tempo {{:htmlclass}}">{{:usuario.nome}} - {{:dataEnvio}}</div>
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
  background: #e5e5e5; 

}

.message {
  margin: 5px 5px 5px 5px;
  padding: 5px 5px 5px 5px;

  background: white;
  border-radius: 2px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);

}

.self {
  float : right;
}

.other {
  float : left;
}


.nome-tempo {
  font-size : 11px;
  color: rgba(0,0,0,.54);
}

</style>



<jsp:include page="/WEB-INF/views/bottom.jsp" />