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
              <h3><i class="${func.sizeSmall} ${func.classesIcone}">${func.icone}</i> Eventos</h3>
              <h4><small>Reprodu��o de M�dias com hora marcada</small></h4>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>

          <div class="spacer-vertical20"></div>

          <form action="#" id="form-evento" class="form" >
            <div class="row">
            
              <div class="col-lg-6 col-md-6">
                <div class="panel panel-default">
                  <div class="panel-body">
                    <div class="row">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                      <input type="hidden" id="idAmbiente" value="${idAmbiente}" />
                      
                      <input type="hidden" id="idEvento" name="idEvento" value="" />
                      
                      <div class="col-lg-12 col-md-12 col-sm-12">
                        <div class="form-group">
                          <select class="form-control" id="combocategoria" name="categoria[idCategoria]" >
                            <option value="" disabled selected>Selecione o tipo de m�dia</option>
                          </select>
                        </div>
                        
                        <div class="form-group">
                          <select class="form-control" id="combomidia" name="midia[idMidia]"  >
                            <option value="" disabled selected>Selecione a m�dia</option>
                          </select>
                        </div>
                        
                        <div class="form-group">
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repeteDomingo" name="repeteDomingo" value="true"> Domingo
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repeteDomingo" name="repeteSegunda" value="true"> Segunda
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repeteDomingo" name="repeteTerca" value="true"> Ter�a
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repeteDomingo" name="repeteQuarta" value="true"> Quarta
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repeteDomingo" name="repeteQuinta" value="true"> Quinta
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repeteDomingo" name="repeteSexta" value="true"> Sexta
                            </label>
                          </div>
                          <div class="checkbox">
                            <label>
                              <input type="checkbox" id="repeteDomingo" name="repeteSabado" value="true"> S�bado
                            </label>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label class="control-label" for="chave">Data In�cio:</label>
                          <div class="input-group date col-lg-4 col-md-6 col-sm-5">
                            <input type="text" class="form-control" id="dataInicio" name="dataInicio">
                              <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                              </span>
                          </div>
                        </div>
                        
                        <div class="form-group">
                          <label class="control-label" for="chave">Data Fim:</label>
                          <div class="input-group date col-lg-4 col-md-6 col-sm-5">
                            <input type="text" class="form-control" id="dataFim" name="dataFim">
                              <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                              </span>
                          </div>
                        </div>
                        
                        
                        <div class="form-group">
  
                          <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                              <label class="control-label" for="chave">Hor�rios:</label>
                            </div>
                            
                            <div class="col-lg-6 col-md-6 col-sm-6">
                              <div class=" pull-right-not-xs">
                                <a href="#" id="linkAddHorario" style="color: #326432;"><i class="fa fa-lg fa-plus-circle"></i> Adicionar Hor�rio</a>
                              </div>
                            </div>
                          </div>

                          <div class="col-lg-12" id="containerHorarios">
                                                    
                            <div class="row spacer-vertical10">
                            
                              <input type="hidden"   name="horarios[][idEventoHorario]" id="idEventoHorario0"  />
                            
                              <div class="col-lg-3 col-md-4 col-sm-5 col-xs-4">
                                <label class="control-label" for="chave">Hora:</label>
                                <div class="input-group spinner" data-trigger="spinner">
                                  <input type="text" value="1" data-rule="hour" class="form-control input-horario" name="horarios[][hora]" id="hora0">
                                  <span class="input-group-addon">
                                    <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-angle-up"></i></a>
                                    <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-angle-down"></i></a>
                                  </span>
                                </div>
                              </div>
                              <div class="col-lg-3 col-md-4 col-sm-5 col-xs-4">
                                <label class="control-label" for="chave">Minuto:</label>
                                <div class="input-group spinner" data-trigger="spinner">
                                  <input type="text" value="1" data-rule="minute" class="form-control input-horario" name="horarios[][minuto]" id="minuto0">
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
                 class="tabela-eventos"
                 data-toggle="table"
                 data-detail-view="true"
                 data-url="${context}/ambientes/${idAmbiente}/eventos/"
                 data-side-pagination="server"
                 data-pagination="true"
                 data-page-size=9
                 data-page-list="[5]"
                 data-locale = "pt_BR"
                 data-height="500"
                 data-detail-formatter="detailFormatter"
                 data-query-params="queryParamsEventos" >
                  <thead>
                    <tr>
<!--                         <th data-field="idEvento" >Id</th> -->
                        <th data-field="idEvento" data-formatter="linhaFormatter">Eventos Programados</th>
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
                <a class="btn btn-primary text-center" href="#" id="btnSalvarEvento"> Salvar Altera��es</a>
                <a class="btn btn-default text-center" href="#" id="btnLimpar"> Limpar</a>
              </div>
            </div>
          </div>          
          
          <div class="spacer-vertical40"></div>

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
              <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/view" >
                <i class="fa fa-arrow-left"></i>
                Voltar para ${nome}
              </a>
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

<link href="${context}/css/jquery.spinner/bootstrap-spinner.min.css" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/locale/bootstrap-table-pt-BR.min.js" charset="UTF-8"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jsrender/0.9.78/jsrender.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.7.2/jquery.serializejson.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/locales/bootstrap-datepicker.pt-BR.min.js"></script>

<script src="${context}/js/required/jquery.spinner/jquery.spinner.min.js"></script>
<script src="${context}/js/required/jquery.populate.min.js"></script>
<script src="${context}/js/ambiente/eventos.js"  charset="UTF-8"></script>


<script id="viewTmplHorarios" type="text/x-jsrender"  charset="UTF-8">
<div class="row spacer-vertical10 linha-horarios">
  
  <input type="hidden"   name="horarios[{{:id}}][idEventoHorario]" value="{{:id}}" />

  <div class="col-lg-3 col-md-4 col-sm-5 col-xs-4">
  <div class="input-group spinner" data-trigger="spinner">
    <input type="text" data-rule="hour" class="form-control" name="horarios[{{:id}}][hora]" value="{{:hora}}" >
    <span class="input-group-addon">
    <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-angle-up"></i></a>
    <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-angle-down"></i></a>
    </span>
  </div>
  </div>
  
  <div class="col-lg-3 col-md-4 col-sm-5 col-xs-4">
  <div class="input-group spinner" data-trigger="spinner">
    <input type="text" data-rule="minute" class="form-control" name="horarios[{{:id}}][minuto]" value="{{:minuto}}" >
    <span class="input-group-addon">
    <a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-angle-up"></i></a>
    <a href="javascript:;" class="spin-down" data-spin="down"><i class="fa fa-angle-down"></i></a>
    </span>
  </div>
  </div>

  <div class="col-lg-3 col-md-4 col-sm-2 col-xs-4">
    <div class="input-group" style=" margin-top: 6px;">
      <a href="javascript:;" style="color : #B23F3F;" class="removerLink"><i class="fa fa-lg fa-minus-circle"></i> Remover</a>
    </div>
  </div>

</div>
</script>  


<style type="text/css">

.tabela-eventos tr{
  cursor: pointer;
}

  
</style>



<jsp:include page="/WEB-INF/views/bottom.jsp" />