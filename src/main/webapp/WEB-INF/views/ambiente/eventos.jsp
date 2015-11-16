<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>



  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="row" id="alertArea">
      </div>
      
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Eventos <br/>
            <small>Reprodução de Mídias com hora marcada</small>
          </h3>
          
          
          <form action="#" id="form-evento" class="form" >
            <div class="row">
            
              <div class="col-lg-6 col-md-6">
                <div class="panel panel-default">
                  <div class="panel-body">
                    <div class="row">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                      <input type="hidden" id="idAmbiente" value="${idAmbiente}" />
                      
                      <div class="col-lg-12 col-md-12 col-sm-12">
                        <div class="form-group">
                          <select class="form-control" id="combocategoria" name="categoria[idCategoria]" >
                            <option value="" disabled selected>Selecione o tipo de mídia</option>
                          </select>
                        </div>
                        
                        <div class="form-group">
                          <select class="form-control" id="combomidia" name="midia[idMidia]"  >
                            <option value="" disabled selected>Selecione a mídia</option>
                          </select>
                        </div>
                        
                        <div class="form-group">
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repetirDomingo" name="repetirDomingo" value="true"> Domingo
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repetirDomingo" name="repetirSegunda" value="true"> Segunda
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repetirDomingo" name="repetirTerca" value="true"> Terça
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repetirDomingo" name="repetirQuarta" value="true"> Quarta
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repetirDomingo" name="repetirQuinta" value="true"> Quinta
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repetirDomingo" name="repetirSexta" value="true"> Sexta
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repetirDomingo" name="repetirSabado" value="true"> Sábado
                            </label>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label class="control-label" for="chave">Data Início:</label>
                          <div class="input-group date">
                            <input type="text" class="form-control" id="dataInicio" name="dataInicio">
                              <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                              </span>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label class="control-label" for="chave">Data Fim:</label>
                          <div class="input-group date">
                            <input type="text" class="form-control" id="dataFim" name="dataFim">
                              <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                              </span>
                          </div>
                        </div>
                        
                        
                        <div class="form-group">
  
                          <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                              <label class="control-label" for="chave">Horários:</label>
                            </div>
                            
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                              <div class=" pull-right">
                                <a href="#" id="linkAddHorario">Adicionar Horário</a>
                              </div>
                            </div>
                          </div>

                          <div class="col-lg-12" id="containerHorarios">
                                                    
                            <div class="row spacer-vertical10">
                              <div class="col-lg-3 col-md-4 col-sm-6 col-xs-6">
                                <label class="control-label" for="chave">Hora:</label>
                                <div class="input-group spinner" data-trigger="spinner">
                                  <input type="text" value="1" data-rule="hour" class="form-control" name="horarios[][hora]" >
                                  <span class="input-group-addon">
                                    <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-angle-up"></i></a>
                                    <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-angle-down"></i></a>
                                  </span>
                                </div>
                              </div>
                              <div class="col-lg-3 col-md-4 col-sm-6 col-xs-6">
                                <label class="control-label" for="chave">Minuto:</label>
                                <div class="input-group spinner" data-trigger="spinner">
                                  <input type="text" value="1" data-rule="minute" class="form-control" name="horarios[][minuto]" >
                                  <span class="input-group-addon">
                                    <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-angle-up"></i></a>
                                    <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-angle-down"></i></a>
                                  </span>
                                </div>
                              </div>
                            </div>
                             
                          </div> <!-- col -->
                                                      
                        </div> <!-- form group -->
                        
                      </div>
  
                    </div> 
                  </div>
                </div>
              </div>
              
              <div class="col-lg-6 col-md-6">
                <table  
                 id="table-eventos"
                 data-toggle="table"
                 data-detail-view="true"
                 data-url="${context}/ambientes/${idAmbiente}/eventos/"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=7
                 data-page-list="[7]"
                 data-locale = "pt_BR"
                 data-height="400"
                 data-detail-formatter="detailFormatter"
                 data-query-params="queryParamsEventos" >
                  <thead>
                    <tr>
                        <th data-field="dataInicio">Data Início</th>
                        <th data-field="dataFim">Data Fim</th>
                    </tr>
                  </thead>
                </table>
              </div>
              
            </div>
          
                                
          </form>

          <div class="spacer-vertical20"></div>
        
          <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
              <div class="">
                <a class="btn btn-primary text-center" href="#" id="btnSalvarEvento"> Salvar Alterações</a>
              </div>
            </div>
          </div>          
          
          <div class="spacer-vertical40"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/view" >
                <i class="fa fa-arrow-left"></i>
                Voltar para ${nome}
              </a>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
              </div>            
            </div>
          </div>
        
      </div>
    </div>
    
      
  </div> <!-- /container -->


<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">
<link href="${context}/css/bootstrap-datepicker3.css" rel="stylesheet">
<link href="${context}/css/bootstrap-spinner.css" rel="stylesheet">


<script src="${context}/js/required/jquery-ui.min.js"></script> 
<script src="${context}/js/required/bootstrap-table/bootstrap-table.js"></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8"></script>

<script src="${context}/js/required/bootstrap-datepicker.min.js"></script>
<script src="${context}/js/required/bootstrap-datepicker.pt-BR.min.js"></script>

<script src="${context}/js/required/jquery.spinner.min.js"></script>
<script src="${context}/js/required/jsrender.min.js"></script>
<script src="${context}/js/required/jquery.serializejson.js"></script>


<script src="${context}/js/ambiente/eventos.js"  charset="UTF-8"></script>


<script id="viewTmplHorarios" type="text/x-jsrender"  charset="UTF-8">
<div class="row spacer-vertical10">
  <div class="col-lg-3 col-md-4 col-sm-6 col-xs-6">
  <div class="input-group spinner" data-trigger="spinner">
    <input type="text" data-rule="hour" class="form-control" name="horarios[{{:id}}][hora]" value="{{:hora}}" >
    <span class="input-group-addon">
    <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-angle-up"></i></a>
    <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-angle-down"></i></a>
    </span>
  </div>
  </div>
  
  <div class="col-lg-3 col-md-4 col-sm-6 col-xs-6">
  <div class="input-group spinner" data-trigger="spinner">
    <input type="text" data-rule="minute" class="form-control" name="horarios[{{:id}}][minuto]" value="{{:minuto}}" >
    <span class="input-group-addon">
    <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-angle-up"></i></a>
    <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-angle-down"></i></a>
    </span>
  </div>
  </div>
</div>
</script>  


<jsp:include page="/WEB-INF/views/bottom.jsp" />