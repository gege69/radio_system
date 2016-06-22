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
              <h3>Cadastro de Título
                <p>
                  <small>
                    <p>O Título representa uma cobrança ao Cliente.</p>
                    <p>Caso seja um feita com boleto você poderá preencher o campo Linha digitável.</p>
                  </small>
                </p>
              </h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>

          <div class="row">
            <div class="col-lg-12">


              <form class="form" id="tituloform" action="#" method="POST">
              
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" id="idTitulo" name="idTitulo" value="${idTitulo}" >
                <input type="hidden" id="idCliente" name="cliente[idCliente]" value="${idCliente}" >
      
                <div class="row">
                  <div class="col-lg-offset-1 col-md-offset-1 col-lg-10 col-md-10">
                  
                    <div class="row">
                      <div class="col-lg-2 col-md-3 col-sm-3 col-xs-5">
                        <div class="form-group">
                          <label for="cliente">Número do Título</label>
                          <input type="text" class="form-control" id="numeroTitulo" name="numeroTitulo" readonly="readonly" value="${idTitulo}">
                        </div>
                      </div>
                    </div>

                    <div class="form-group">
                      <label for="cliente">Cliente</label>
                      <input type="text" class="form-control" id="cliente" name="razaosocial" readonly="readonly">
                    </div>

                    <div class="row">
                      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                        <div class="form-group">
                          <label for="cliente">CNPJ Cliente</label>
                          <input type="text" class="form-control cnpj" id="cnpjCliente" name="cnpj" readonly="readonly">
                        </div>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="form-group">
                          <label class="control-label" for="dataEmissao">Data Emissão</label>
                          <div class="input-group date" id="dataEmissaoDate">
                            <input type="text" class="form-control" id="dataEmissao" name="dataEmissao">
                              <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                              </span>
                          </div>
                        </div>                  
                      </div>

                      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="form-group">
                          <label class="control-label" for="dataVencimento">Data Vencimento</label>
                          <div class="input-group date" id="dataVencimentoDate">
                            <input type="text" class="form-control" id="dataVencimento" name="dataVencimento">
                              <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                              </span>
                          </div>
                        </div>                  
                      </div>

                      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="form-group">
                          <label class="control-label" for="dataPagamento">Data Pagamento</label>
                          <div class="input-group date" id="dataPagamentoDate">
                            <input type="text" class="form-control" id="dataPagamento" name="dataPagamento">
                              <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                              </span>
                          </div>
                        </div>                  
                      </div>
                      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="form-group">
                          <label class="control-label" for="dataCancelamento">Data Cancelamento</label>
                          <div class="input-group date" id="dataCancelamentoDate">
                            <input type="text" class="form-control" id="dataCancelamento" name="dataCancelamento">
                              <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                              </span>
                          </div>
                        </div>                  
                      </div>
                    </div>


                    <div class="row">
                      <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                        <div class="form-group">
                          <label for="numeroNotaFiscal" class="control-label">Nota Fiscal</label>
                          <input type="text" class="form-control" id="numeroNotaFiscal" name="numeroNotaFiscal">
                        </div>
                      </div>
                    </div>

                    <div class="form-group">
                      <label for="historico" class="control-label">Histórico</label>
                      <textarea class="form-control" rows="3" id="historico" name="historico"></textarea>
                    </div>

                    <div class="form-group">
                      <label for="linhadigitavel" class="control-label">Linha Digitável</label>
                      <input type="text" class="form-control" id="linhadigitavel" name="linhadigitavel">
                    </div>

                    <div class="row">
                      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="form-group">
                          <label for="valorLiquido" class="control-label">Valor Líquido</label>
                          <input type="text" class="form-control money" id="valorLiquido" name="valorLiquido" value="0,00">
                        </div>
                      </div>
                      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="form-group">
                          <label for="valorTaxas" class="control-label">Valor Taxas</label>
                          <input type="text" class="form-control money" id="valorTaxas" name="valorTaxas" value="0,00">
                        </div>
                      </div>
                      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="form-group">
                          <label for="valorJuros" class="control-label">Valor Juros</label>
                          <input type="text" class="form-control money" id="valorJuros" name="valorJuros" value="0,00">
                        </div>
                      </div>
                      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="form-group">
                          <label for="valorAcresc" class="control-label">Valor Acréscimos</label>
                          <input type="text" class="form-control money" id="valorAcresc" name="valorAcresc" value="0,00">
                        </div>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="form-group">
                          <label for="valorDescontos" class="control-label">Valor Descontos</label>
                          <input type="text" class="form-control money" id="valorDescontos" name="valorDescontos" value="0,00">
                        </div>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="form-group">
                          <label for="valorTotal" class="control-label">Valor Total <br/><span class="explicacao">( Líquido + Taxas + Juros + Acréscimos ) - Descontos</span></label>
                          <input type="text" class="form-control" id="valorTotal" name="valorTotal" readonly="readonly" value="0,00">
                        </div>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="form-group">
                          <label for="valorPago" class="control-label">Valor Pago</label>
                          <input type="text" class="form-control money" id="valorPago" name="valorPago" value="0,00">
                        </div>
                      </div>
                    </div>

                    
                  </div>
                  
                </div>
                
                <div class="row">
                  <div class="col-lg-12 col-md-12">
                    <div class="pull-right">

                      <c:if test="${idTitulo != null && idTitulo > 0}">
                        <button type="button" class="btn btn-danger" id="btnCancelar">
                          <i class="fa fa-close"></i>
                          Cancelar Título
                        </button>
                      </c:if>
                      <button type="button" class="btn btn-primary" id="btnSalvar">
                        <i class="fa fa-floppy-o"></i>
                        Salvar Título
                      </button>
                    </div>            
                  </div>
                </div>

              </form>
                
            </div>
          </div>

          <div class="spacer-vertical40"></div> 

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <c:if test="${urlVoltarCadastro != null}">
                  <a class="btn btn-default" href="${context}${urlVoltarCadastro}" >
                    <i class="fa fa-arrow-left"></i>
                    Voltar para ${nomeCliente}</a>
                </c:if>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}${urlVoltarPainel}">${nomePainel}</a>
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
        <h4 class="modal-title" id="titulo-modal">Cancelar Título</h4>
      </div>
      <div class="modal-body">
        <form action="#" class="form-horizontal" id="dialogEspelhar">
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              Desenha realmente cancelar o Título ${idTitulo}?
            </div> 
          </div>
          
        </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnNaoDialog" data-dismiss="modal">Não</button>
        <button type="button" class="btn btn-primary" id="btnConfirmarCancelar" data-dismiss="modal">Sim</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<style type="text/css">

.explicacao{
    font-weight: normal;
    font-size: smaller;
}

</style>





<script type="text/javascript" src="${context}/js/required/jquery.serializejson.js" defer></script>
<script type="text/javascript" src="${context}/js/required/jquery.populate.js" async></script>
<script type="text/javascript" src="${context}/js/required/jquery.mask.min.js" defer></script>
<script type="text/javascript" src="${context}/js/required/jquery.maskMoney.min.js" defer></script>
<script type="text/javascript" src="${context}/js/required/moment-with-locales.min.js" defer></script>

<%-- <link href="${context}/css/bootstrap-datepicker3.css" rel="stylesheet"> --%>

<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker.min.css" rel="stylesheet">

<%-- <script src="${context}/js/required/bootstrap-datepicker.min.js"></script> --%>
<%-- <script src="${context}/js/required/bootstrap-datepicker.pt-BR.min.js"></script> --%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/locales/bootstrap-datepicker.pt-BR.min.js"></script>

<script type="text/javascript" src="${context}/js/titulo/editar-titulo.js" async charset="UTF-8"></script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />