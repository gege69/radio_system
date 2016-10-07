<jsp:include page="/WEB-INF/views/main.jsp" />    


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
 
    <div class="row">
    
      <div class="row">
        <div class="loader" id="ajaxload" style="display : none;"></div>
      </div>
      
      <div class="panel panel-default">
        <div class="panel-body">

          <div class="row">
            <div class="col-lg-6 col-md-6">
              <h3><i class="${func.sizeSmall} ${func.classesIcone}">${func.icone}</i> Monitoramento</h3>
              <h4><small>Monitore a execução dos ambientes</small></h4>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>

          <div class="spacer-vertical20"></div>

          <form action="#" id="formRelatorios" class="form-inline" >
          
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" id="idAmbiente" value="${idAmbiente}" />
            
            <div class="form-group">
              <label class="control-label" for="tipo">Seleção de Ambientes:</label>
              <select class="form-control" id="tipo" name="tipo" >
                <option value="ALL" selected>Todos ambientes</option>
                <option value="ON" selected>Apenas ambientes ONLINE</option>
                <option value="OFF" selected>Apenas ambientes OFFLINE</option>
              </select>
            </div>
            
            <div class="form-group">
              <label class="control-label" for="chave">Data Início:</label>
              <div class="input-group date" id="datePickerInicio">
                <input type="text" class="form-control" id="dataInicio" name="dataInicio">
                  <span class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </span>
              </div>
            </div>
            
            <div class="form-group">
              <label class="control-label" for="chave">Data Fim:</label>
              <div class="input-group date" id="datePickerFim">
                <input type="text" class="form-control" id="dataFim" name="dataFim">
                  <span class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </span>
              </div>
            </div>
                              

            <div class="form-group">
                <a class="btn btn-primary text-center" href="#" id="btnGerar"> <i class="fa fa-newspaper-o"></i> Buscar</a>
            </div>

          </form>
        
          <div class="spacer-vertical20"></div>

          <div class="row" id="gridExtra">
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="">
                <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/view" >
                  <i class="fa fa-arrow-left"></i>
                  Voltar para ${nome}
                </a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="pull-right-not-xs">
                <a class="btn btn-default" id="btnGeraCsv" href="#">
                  <i class="material-icons md-18">description</i> Baixar em Excel</a>
              </div>
            </div>
          </div>

          <div class="spacer-vertical20"></div>

          <div class="row">
            <div class="col-lg-12 col-md-12">
                <table  
                 id="tableMonitoramento"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=10
                 data-page-list="[10,25,50,100]"
                 data-locale = "pt_BR"
                 data-query-params="queryParamsMonitoramento" >
                  <thead>
                  </thead>
                </table>
            </div>
          </div>

          <div class="spacer-vertical20"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="pull-right-not-xs">
                <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
              </div>            
            </div>
          </div>
        
      </div>
    </div>
    
    <div id='ajax_loader' style="position: fixed; left: 50%; top: 50%; display: none;">
        
    </div>
      
  </div> <!-- /container -->


<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.min.css" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-pt-BR.min.js" charset="UTF-8"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jsrender/0.9.78/jsrender.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.7.2/jquery.serializejson.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/locales/bootstrap-datepicker.pt-BR.min.js"></script>

<script src="${context}/js/ambiente/monitoramento.js"  charset="UTF-8"></script>


<style type="text/css">

.divCategoria {
  min-height: 22px;
  line-height: 22px;
  display: block;
  padding: 0 0 0 6px;
  margin-bottom: initial;
}
  
</style>



<jsp:include page="/WEB-INF/views/bottom.jsp" />