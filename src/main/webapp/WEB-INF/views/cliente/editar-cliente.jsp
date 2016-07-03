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
              <h3><i class="fa fa-street-view"></i> Cadastro de Cliente</h3>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>


          
          <div class="spacer-vertical40"></div>

          <div class="row">
            <div class="col-lg-12">


              <form class="form-horizontal" id="clienteform" action="#" method="POST">
              
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" id="idCliente" name="idCliente" value="${idCliente}" >
      
                <div class="row">
                  <div class="col-lg-12 col-md-12">
                  
                    <div class="form-group">
                      <label for="nome" class="control-label col-sm-2 col-md-2">Razão Social</label>
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
                        <div class="col-sm-4 col-md-3 col-lg-2">
                          <div class="pull-right" style="height: 20px;">
                            <button id="linkaddtelefone" class="btn btn-success"><i class="fa fa-lg fa-plus-circle"></i> Adicionar Telefone</button>
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
                      <label for="login" class="control-label col-sm-2 col-md-2">Endereço</label>
                      <div class="col-sm-10 col-md-8">
                        <input type="text" class="form-control" id="logradouro" name="logradouro">
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label for="login" class="control-label col-sm-2 col-md-2">Número</label>
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
                          <option value="AP">Amapá </option>
                          <option value="AM">Amazonas </option>
                          <option value="BA">Bahia </option>
                          <option value="CE">Ceará </option>
                          <option value="DF">Distrito Federal </option>
                          <option value="ES">Espírito Santo </option>
                          <option value="GO">Goiás </option>
                          <option value="MA">Maranhão </option>
                          <option value="MT">Mato Grosso </option>
                          <option value="MS">Mato Grosso do Sul </option>
                          <option value="MG">Minas Gerais </option>
                          <option value="PA">Pará </option>
                          <option value="PB">Paraíba </option>
                          <option value="PR">Paraná </option>
                          <option value="PE">Pernambuco </option>
                          <option value="PI">Piauí </option>
                          <option value="RJ">Rio de Janeiro </option>
                          <option value="RN">Rio Grande do Norte </option>
                          <option value="RS">Rio Grande do Sul </option>
                          <option value="RO">Rondônia </option>
                          <option value="RR">Roraima </option>
                          <option value="SC">Santa Catarina </option>
                          <option value="SP">São Paulo </option>
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
                    
                    <div class="form-group">
                      <label for="login" class="control-label col-sm-2 col-md-2">Data de Cadastro</label>
                      <div class="col-lg-2 col-md-3 col-sm-4">
                        <input type="text" class="form-control" id="dataCriacao" name="dataCriacao" readonly="readonly">
                      </div>
                    </div>
                  </div>
                  
                </div>
                
                
                <div class="row">
                  <div class="col-lg-12 col-md-12">
                    <div class="pull-right">
                      <button type="button" class="btn btn-primary" id="btnSalvar">
                        <i class="fa fa-floppy-o"></i>
                        Salvar Alterações
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
                        <li class="active"><a data-toggle="tab" href="#divResumoFinanceiro">Resumo Financeiro</a></li>

                        <c:if test="${isAdmin}">
                          <li><a data-toggle="tab" href="#divCondicoesComerciais">Condições Comerciais</a></li>
                        </c:if>

                        <li><a data-toggle="tab" href="#divPagamentos">Títulos</a></li>

                        <c:if test="${isAdmin}">
                          <li><a data-toggle="tab" href="#divUsuarios">Usuários</a></li>
                        </c:if>
                      </ul>
                    </div>
                  </div>

                  <div class="spacer-vertical10"></div>
                
                  <div class="tab-content">

                    <div id="divResumoFinanceiro" class="tab-pane fade in active">
                      <form action="#" id="formResumo" class="form-horizontal">
                        <div class="col-lg-6 col-md-6">
                          <div class="form-group">
                            <label for="totalAmbientes" class="control-label col-lg-5 col-sm-4 col-md-5">Total Ambientes: </label>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                              <input type="text" class="form-control inteiro" id="totalAmbientes" name="totalAmbientes" readonly="readonly">
                            </div>

                            <div class="col-lg-2 col-md-2">
                              <div class="">
                                <a class="btn btn-default" href="${context}/admin/clientes/${idCliente}/ambientes" id="btnListagemAmbientes" >
                                  <i class="material-icons md-18">dvr</i> 
                                  Ambientes</a>
                              </div>            
                            </div>
                          </div>
                          
                          <div class="form-group">
                            <label for="ambientesAtivos" class="control-label col-sm-4 col-md-5">Ambientes Ativos: </label>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                              <input type="text" class="form-control inteiro" id="ambientesAtivos" name="ambientesAtivos" readonly="readonly">
                            </div>
                          </div>

                          <div class="form-group">
                            <label for="ambientesInativos" class="control-label col-sm-4 col-md-5">Ambientes Inativos: </label>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                              <input type="text" class="form-control inteiro" id="ambientesInativos" name="ambientesInativos" readonly="readonly">
                            </div>
                          </div> 

                          <div class="form-group">
                            <label for="ambientesInativos" class="control-label col-sm-4 col-md-5">Ambientes Bloqueados: </label>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                              <input type="text" class="form-control inteiro" id="ambientesBloqueados" name="ambientesBloqueados" readonly="readonly">
                            </div>
                          </div> 

                        </div>

                        <div class="col-lg-6 col-md-6">
                          <div class="form-group">
                            <label for="login" class="control-label col-sm-4 col-md-5">Data de Vencimento</label>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                              <input type="text" class="form-control" id="dataVencimento" name="dataVencimento" readonly="readonly">
                            </div>
                          </div>

                          <div class="form-group">
                            <label for="login" class="control-label col-lg-5 col-sm-4 col-md-5">Fechamento de Cobrança</label>
                            <div class="col-lg-7 col-md-7 col-sm-8">
                              <input type="text" class="form-control" id="diasFechamento" name="diasFechamento" readonly="readonly">
                            </div>
                          </div>

                          <div class="form-group">
                            <label for="login" class="control-label col-lg-5 col-sm-4 col-md-5">Bloquear Empresa</label>
                            <div class="col-lg-7 col-md-7 col-sm-8">
                              <input type="text" class="form-control" id="bloquearEmpresa" name="bloquearEmpresa" readonly="readonly">
                            </div>
                          </div>

                          <div class="form-group">
                            <label for="login" class="control-label col-sm-4 col-md-5">Ambientes Grátis</label>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                              <input type="text" class="form-control" id="ambientesGratis" name="ambientesGratis" readonly="readonly">
                            </div>
                          </div>

                        </div>

                      </form>
                    </div>

                    <c:if test="${isAdmin}">
                      <div id="divCondicoesComerciais" class="tab-pane fade in">
                        <div class="col-lg-10 col-md-10">
                          <table  
                             id="tableCondicoesComerciais"
                             data-url="${context}/clientes/${idCliente}/condicoescomerciais"
                             data-height="200"
                             data-side-pagination="server"
                             data-pagination="true"
                             data-page-size=3
                             data-locale = "pt_BR"
                             data-query-params="queryParamsCondicoesComerciais">
                            <thead>
                              <tr>
                                  <th data-field="tipoTaxa.descricao" data-formatter="descricaoTaxaFormatter">Descrição Taxa</th>
                                  <th data-field="valor" data-formatter="valorFormatter">Valor</th>
                                  <th data-field="dataAlteracao">Data Alteração</th>
                              </tr>
                            </thead>
                          </table>
                        </div>
                        
                        <div class="col-lg-2 col-md-2">
                          <div class="">
                            <a class="btn btn-default" href="#" id="btnInserirCondicaoComercial" >
                              <i class="fa fa-plus"></i>
                              Inserir Condição</a>
                          </div>            
                        </div>
                      </div>
                    </c:if>

                    <div id="divPagamentos" class="tab-pane fade in">
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
                                <th data-field="idTitulo">Título</th>
                                <th data-field="dataEmissao">Emissão</th>
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
                          <c:if test="${isAdmin}">
                            <a class="btn btn-default" href="${context}${urlInserirTitulo}?idCliente=${idCliente}" >
                              <i class="fa fa-plus"></i>
                              Inserir Título</a>
                          </c:if>
                        </div>            
                      </div>                
                    </div>

                    <c:if test="${isAdmin}">
                      <div id="divUsuarios" class="tab-pane fade in">
                        <div class="col-lg-12 col-md-12">
                          <table  
                             id="tableUsuarios"
                             data-url="${context}/clientes/${idCliente}/usuarios"
                             data-height="400"
                             data-side-pagination="server"
                             data-pagination="true"
                             data-search=true
                             data-page-size=5
                             data-unique-id="idUsuario"
                             data-locale = "pt_BR"
                             data-query-params="queryParamsUsuarios">
                            <thead>
                              <tr>
                                  <th data-field="nome">Nome</th>
                                  <th data-field="login">Login</th>
                                  <th data-field="email">Email</th>
                                  <th data-field="perfis" data-formatter="perfisUsuarioFormatter">Perfis</th>
                                  <th data-field="editar" data-formatter="senhaUsuarioFormatter">Reset Senha</th>
                              </tr>
                            </thead>
                          </table>
                        </div>
                      </div>
                    </c:if>
                    
                  </div>
                </div>
              </div>
                

              <hr>


            </div>
          </div>

          <div class="spacer-vertical20"></div> 

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <c:if test="${urlVoltarCadastro != null}">
                  <a class="btn btn-default" href="${context}${urlVoltarCadastro}" >
                    <i class="fa fa-arrow-left"></i>
                    Voltar para Cadastro de Clientes</a>
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


<c:if test="${isAdmin}">
  <div id="myModalCondicaoComercial" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">x</button>
          <h3>Condição Comercial</h3>
          
          <div class="row" id="alertAreaModal">
          </div>
          
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-lg-12">
              <form action="#" 
                    id="formCondicaoComercial" 
                    class="form-horizontal"
                    method="POST">

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" id="idClienteModal" name="cliente[idCliente]" value="${idCliente}" >
                <input type="hidden" id="idCondconModal" name="idCondcom" value="" >
                
                <div class="form-group">
                  <label for="idTipoTaxa" class="control-label col-sm-2 col-md-2 col-lg-2">Tipo de Taxa</label>
                  <div class="col-lg-10 col-md-10 col-sm-10">
                    <select class="form-control" id="idTipoTaxa" name="tipoTaxa[idTipotaxa]">
                    </select>
                  </div>
                </div>

                <div class="form-group">
                  <label for="definicaoTaxa" class="control-label col-sm-2 col-md-2">Definição</label>
                  <div class="col-lg-7 col-md-8 col-sm-8">
                    <select class="form-control" id="definicaoTaxa" name="definicaoTaxa">
                      <option value="VALOR" selected="selected" >Valor</option>
                      <option value="PORCENTAGEM" >Porcentagem</option>
                    </select>
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-2">Valor</label>
                  <div class="col-lg-7 col-md-8 col-sm-8">
                    <input type="text" class="form-control money" id="valor" name="valor">
                  </div>
                </div>

              </form>
            </div>
            
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" data-dismiss="modal">Cancelar</button>
          <button class="btn btn-primary" id="btnSalvarCondicao">Salvar</button>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="myModalAlterarSenha">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

          <h4 class="modal-title" id="titulo-modal">Alterar Senha</h4>
        </div>
        <div class="modal-body">

          <div class="row" >
            <div class="col-lg-12" id="alertAreaSenha">
            </div>
          </div>

          <form action="#" class="form-horizontal" id="alteraSenhaForm" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" id="idUsuario" name="idUsuario" value="" >
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-4">Login:</label>
              <div class="col-sm-3 col-md-5">
                <input type="text" class="form-control" id="login" placeholder="Login" style="text-transform: lowercase;" disabled>
              </div>
            </div>

            <div class="form-group">
              <label for="mostrarSenha" class="control-label col-sm-2 col-md-4"></label>
              <div class="checkbox col-lg-4 col-md-4 col-sm-4 col-xs-12">
                <label>
                  <input type="checkbox" id="mostrarSenha" name="mostrarSenha" value="false"> Mostrar senha
                </label>
              </div>
            </div>
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-4">Senha:</label>
              <div class="col-sm-3 col-md-5">
                <input type="password" class="form-control" id="password" name="password" placeholder="Senha">
              </div>
            </div>
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-4">Repetir Senha:</label>
              <div class="col-sm-3 col-md-5">
                <input type="password" class="form-control" id="matchingPassword" name="matchingPassword" placeholder="Repetir Senha">
              </div>
            </div>

          </form>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" id="btnNaoDialog" data-dismiss="modal">Cancelar</button>
          <button type="button" class="btn btn-primary" id="btnConfirmarSenha">Confirmar</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->


</c:if>





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
        <button id="linkaddtelefone" class="btn btn-warning removertelefone"><i class="fa fa-lg fa-minus-circle"></i> Remover</button>
      </div>
    </div>
  </div>
</div>
</script>


<script type="text/javascript" src="${context}/js/required/jquery.serializejson.js" defer></script>
<script type="text/javascript" src="${context}/js/required/jquery.populate.js" async></script>
<script type="text/javascript" src="${context}/js/required/jsrender.min.js" defer></script>
<script type="text/javascript" src="${context}/js/required/jquery.mask.min.js" defer></script>

<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">

<script type="text/javascript" src="${context}/js/required/zxcvbn.js" async></script>

<script type="text/javascript" src="${context}/js/cliente/editar-cliente.js" async charset="UTF-8"></script>

<script src="${context}/js/required/bootstrap-table/bootstrap-table.js" ></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8" r></script>

<style type="text/css">

#tablePagamentosTitulos tr{
  cursor: pointer;
}

</style

<jsp:include page="/WEB-INF/views/bottom.jsp" />