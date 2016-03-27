<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

          <form class="form-horizontal" id="clienteform" action="#" method="POST">
          
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" id="idCliente" name="idCliente" value="${idCliente}" >
  
            <div class="row">
              <div class="col-lg-12 col-md-12">
              
                <div class="form-group">
                  <label for="nome" class="control-label col-sm-2 col-md-2">Raz�o Social</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="razaosocial" name="razaosocial">
                  </div>
                </div>
                
                <div class="form-group">
                    <label for="login" class="control-label col-sm-2 col-md-2">Nome Fantasia</label>
                    <div class="col-sm-8 col-md-6">
                      <input type="text" class="form-control" id="nomefantasia" name="nomefantasia">
                    </div>
                    <div class="col-lg-2 col-md-2 col-sm-2">
                      <div class="checkbox">
                        <label>
                          <input type="checkbox" id="ativo" name="ativo" value="true"> Ativo
                        </label>
                      </div>
                    </div>

                </div>

                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2">CNPJ</label>
                  <div class="col-lg-4 col-md-4 col-sm-6">
                    <input type="text" class="form-control cnpj" id="cnpj" name="cnpj">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2">Email</label>
                  <div class="col-lg-4 col-md-4 col-sm-6">
                    <input type="text" class="form-control" id="email" name="email">
                  </div>
                </div>
                
<!--                 Fazer aqui o lance igual o eventos -->
                <div id="containertelefones">

                  <div class="form-group">
                    <input type="hidden" id="idTelefone0" name="telefones[][idTelefone]">
                    <label for="login" class="control-label col-sm-2 col-md-2 col-xs-12">Telefone</label>
                    <div class="col-lg-1 col-md-1 col-sm-2 col-xs-2 ">
                      <input type="text" class="form-control ddd" id="ddd0" name="telefones[][ddd]" maxlength="3">
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-10 ">
                      <input type="text" class="form-control phone" id="numero0" name="telefones[][numero]" >
                    </div>
                    <div class="col-sm-3 col-md-2 col-lg-2">
                      <div class="pull-right" style="height: 20px;">
                        <a href="#" id="linkaddtelefone"><i class="fa fa-lg fa-plus-circle"></i> Adicionar Telefone</a>
                      </div>
                    </div>
                  </div>

                </div>
                
                
                <div class="form-group">
                  <label for="cep" class="control-label col-sm-2 col-md-2">CEP</label>
                  <div class="col-lg-2 col-md-3 col-sm-4 ">
                    <input type="text" class="form-control" id="cep" name="cep" maxlength="8">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2">Endere�o</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="logradouro" name="logradouro">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2">N�mero</label>
                  <div class="col-lg-2 col-md-3 col-sm-4">
                    <input type="text" class="form-control" id="numero" name="numero">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2">Complemento</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="complemento" name="complemento">
                  </div>
                </div>

                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2">Bairro</label>
                  <div class="col-lg-4 col-md-4 col-sm-6">
                    <input type="text" class="form-control" id="bairro" name="bairro">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2">Estado</label>
                  <div class="col-lg-4 col-md-4 col-sm-6">
                    <select class="form-control" id="estado" name="estado">
                      <option value="AC">Acre </option>
                      <option value="AL">Alagoas </option>
                      <option value="AP">Amap� </option>
                      <option value="AM">Amazonas </option>
                      <option value="BA">Bahia </option>
                      <option value="CE">Cear� </option>
                      <option value="DF">Distrito Federal </option>
                      <option value="ES">Esp�rito Santo </option>
                      <option value="GO">Goi�s </option>
                      <option value="MA">Maranh�o </option>
                      <option value="MT">Mato Grosso </option>
                      <option value="MS">Mato Grosso do Sul </option>
                      <option value="MG">Minas Gerais </option>
                      <option value="PA">Par� </option>
                      <option value="PB">Para�ba </option>
                      <option value="PR">Paran� </option>
                      <option value="PE">Pernambuco </option>
                      <option value="PI">Piau� </option>
                      <option value="RJ">Rio de Janeiro </option>
                      <option value="RN">Rio Grande do Norte </option>
                      <option value="RS">Rio Grande do Sul </option>
                      <option value="RO">Rond�nia </option>
                      <option value="RR">Roraima </option>
                      <option value="SC">Santa Catarina </option>
                      <option value="SP">S�o Paulo </option>
                      <option value="SE">Sergipe </option>
                      <option value="TO">Tocantins</option>
                    </select>
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2">Cidade</label>
                  <div class="col-lg-4 col-md-4 col-sm-6">
                    <input type="text" class="form-control" id="cidade" name="cidade">
                  </div>
                </div>
                
              </div>
              
            </div>
            
            
            <div class="row">
              <div class="col-lg-12 col-md-12">
                <div class="pull-right">
                  <button type="button" class="btn btn-primary" id="btnSalvar">
                    <i class="fa fa-floppy-o"></i>
                    Salvar Altera��es
                  </button>
                </div>            
              </div>
            </div>

          </form>
            


          <div class="row" >
            <div class="col-lg-12">
            
              <div class="spacer-vertical10"></div>
              
              <div class="row">
                <div class="col-lg-12 col-md-12">
                  <ul class="nav nav-tabs" id="abas">
                    <li><a data-toggle="tab" href="#divResumoFinanceiro">Resumo Financeiro</a></li>
                    <li><a data-toggle="tab" href="#divCondicoesComerciais">Condi��es Comerciais</a></li>
                    <li><a data-toggle="tab" href="#divPagamentos">Pagamentos</a></li>
                    <li><a data-toggle="tab" href="#divUsuarios">Usu�rios</a></li>
                  </ul>
                </div>
              </div>

              <div class="spacer-vertical10"></div>
            
              <div class="tab-content">
                <div id="divCondicoesComerciais" class="tab-pane fade in active">
                  <div class="col-lg-10 col-md-10">
                    <table  
                       id="tableCondicoesComerciais"
                       data-toggle="table"
                       data-url="${context}/clientes/${idCliente}/condicoescomerciais"
                       data-height="200"
                       data-side-pagination="server"
                       data-pagination="true"
                       data-page-size=3
                       data-locale = "pt_BR"
                       data-query-params="queryParamsCondicoesComerciais">
                      <thead>
                        <tr>
                            <th data-field="tipoTaxa.descricao">Descri��o Taxa</th>
                            <th data-field="valor" data-formatter="valorFormatter">Valor</th>
                            <th data-field="dataAlteracao">Data Altera��o</th>
                        </tr>
                      </thead>
                    </table>
                  </div>
                  
                  <div class="col-lg-2 col-md-2">
                    <div class="">
                      <a class="btn btn-default" href="#" id="btnInserirCondicaoComercial" >
                        <i class="fa fa-plus"></i>
                        Inserir Condi��o</a>
                    </div>            
                  </div>
                </div>
                <div id="divResumoFinanceiro" class="tab-pane fade in active">
                  <div class="col-lg-12 col-md-12">
                    <form action="#" id="formResumo" class="form-inline">

                      <div class="form-group">
                        <label for="cep">Total Ambientes: </label>
                        <input type="text" class="form-control inteiro" id="totalAmbientes" name="totalAmbientes" readonly="readonly">
                      </div>
                      
                      <div class="form-group">
                        <label for="login">Ambientes Ativos: </label>
                        <input type="text" class="form-control inteiro" id="ambientesAtivos" name="ambientesAtivos" readonly="readonly">
                      </div>

                      <div class="form-group">
                        <label for="login">Ambientes Inativos: </label>
                        <input type="text" class="form-control inteiro" id="ambientesInativos" name="ambientesInativos" readonly="readonly">
                      </div> 
                    </form>
                  </div>

                
                </div>
                <div id="divPagamentos" class="tab-pane fade in active">
                  <div class="col-lg-10 col-md-10">
                    <table  
                       id="tablePagamentosTitulos"
                       data-url="${context}/clientes/${idCliente}/titulos"
                       data-height="200"
                       data-side-pagination="server"
                       data-pagination="true"
                       data-page-size=3
                       data-locale="pt_BR"
                       data-query-params="queryParamsPag">
                      <thead>
                        <tr>
                            <th data-field="idTitulo">T�tulo</th>
                            <th data-field="dataEmissao">Emiss�o</th>
                            <th data-field="dataVencimento">Vencimento</th>
                            <th data-field="dataPagamento">Pagamento</th>
                            <th data-field="dataCancelamento">Cancelamento</th>
                            <th data-field="valorTotal">Valor</th>
                            <th data-field="valorDescontos">Descontos</th>
                            <th data-field="valorPago">Vl. Pago</th>
                        </tr>
                      </thead>
                    </table>
                  </div>
                  
                  <div class="col-lg-2 col-md-2">
                    <div class="">
                      <a class="btn btn-default" href="#" >
                        <i class="fa fa-plus"></i>
                        Inserir T�tulo</a>
                    </div>            
                  </div>                
                </div>
                <div id="divUsuarios" class="tab-pane fade in active">
                  teste usuarios
                </div>
              </div>
            </div>
          </div>
            

          <hr>


<script id="viewTmplTelefones" type="text/x-jsrender"  charset="UTF-8">

<div class="linha-telefone">

  <div class="form-group">
    <input type="hidden" name="telefones[{{:id}}][idTelefone]" value="{{:id}}">
    <label for="login" class="control-label col-sm-2 col-md-2 col-xs-12">Telefone</label>
    <div class="col-lg-1 col-md-1 col-sm-2 col-xs-2 ">
      <input type="text" class="form-control ddd" id="ddd0" name="telefones[{{:id}}][ddd]" maxlength="3" value="{{:ddd}}">
    </div>
    <div class="col-lg-3 col-md-3 col-sm-4 col-xs-10 ">
      <input type="text" class="form-control phone" id="telefone0" name="telefones[{{:id}}][numero]" value="{{:numero}}" >
    </div>
    <div class="col-sm-3 col-md-2 col-lg-2">
      <div class="pull-right" style="height: 20px;">
        <a href="javascript:;" style="color : #B23F3F;" class="removertelefone"><i class="fa fa-lg fa-minus-circle"></i> Remover</a>
      </div>
    </div>
  </div>
</div>
</script>


<style type="text/css">

#linkaddtelefone{
    color: #326432;
    margin: 2px;
    display: block;
    margin-top: 7px;
    white-space: nowrap;
}

</style>


<div id="myModalCondicaoComercial" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Condi��o Comercial</h3>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-lg-12">
            <form action="#" 
                  id="formCondicaoComercial" 
                  class="form-horizontal"
                  method="POST">
                  
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
              <input type="hidden" id="idClienteModal" name="idCliente" value="${idCliente}" >
              
              <div class="form-group">
                <label for="posicaoVinheta" class="control-label col-sm-2 col-md-2">Tipo de Taxa</label>
                <div class="col-lg-2 col-md-3 col-sm-4">
                  <select class="form-control" id="tipoTaxa.idTipoTaxa" name="tipoTaxa.idTipotaxa">
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label for="qtdMusicas" class="control-label col-sm-2 col-md-2">Defini��o</label>
                <div class="col-lg-2 col-md-3 col-sm-4">
                  <select class="form-control" id="definicaoTaxa" name="definicaoTaxa">
                    <option value="VALOR" >Valor</option>
                    <option value="PORCENTAGEM" >Porcentagem</option>
                  </select>
                </div>
              </div>
              
              <div class="form-group">
                <label for="login" class="control-label col-sm-2 col-md-2">Valor</label>
                <div class="col-lg-2 col-md-3 col-sm-4">
                  <input type="text" class="form-control double" id="valor" name="valor">
                </div>
              </div>

            </form>
          </div>
          
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Cancelar</button>
        <button class="btn btn-primary" id="btnSalvarCondicao" data-dismiss="modal">Salvar</button>
      </div>
    </div>
  </div>
</div>

