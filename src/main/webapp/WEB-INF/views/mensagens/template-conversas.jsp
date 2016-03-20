

          <input type="hidden" id="idUsuario" value="${idUsuario}"> 

          <div class="row">   
                   
            <div class="col-lg-6 col-md-6 col-sm-4">

              <div class="row">
                <div class="col-lg-12">
                  <div class="pull-right">
                    <a class="btn btn-primary" href="#" id="btnNovaMensagem"><i class="fa fa-envelope-o"></i> Nova Mensagem</a>
                  </div>
                </div>  
              </div>

              <div class="row spacer-vertical10">            
                <div class="col-lg-12">
                  <table  
                   id="table-conversas"
                   class="table-conversas"
                   data-toggle="table"
                   data-url="${pageContext.request.contextPath}/api/conversas"
                   data-side-pagination="server"
                   data-pagination="true"
                   data-page-size=7
                   data-page-list="[5]"
                   data-locale = "pt_BR"
                   data-height="400"
                   data-query-params="queryParamsConversas" >
                    <thead>
                      <tr>
  <!--                         <th data-field="idConversa">Id</th> -->
                          <th data-field="dataAtualizacao">Atualização em:</th>
                          <th data-field="participantes">Ambiente(s) e Usuário(s)</th>
  <!--                         <th data-field="dataVigenciaInicio">Vigência Início</th> -->
  <!--                         <th data-field="dataVigenciaFim">Vigência Fim</th> -->
                      </tr>
                    </thead>
                  </table>
                </div>
              </div>
            </div>
          
            <div id="selecao-participantes" class="col-lg-6 col-md-6 col-sm-8" style="display: none;" >
              
              <div class="panel panel-default">
                <div class="panel-body">

                  <h4>Escolha os participantes da Conversação <br/><small>(Você será incluído automaticamente)</small></h4>
                  <div class="row spacer-vertical10">            
                    <table  
                     id="table-participantes"
                     class="table-participantes"
                     data-toggle="table"
                     data-url="${pageContext.request.contextPath}/api/conversas/usuarios"
                     data-side-pagination="server"
                     data-pagination="false"
                     data-locale = "pt_BR"
                     data-show-pagination-switch = "false"
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
                  
                  <div class="row spacer-vertical10">
                    
                    <form action="#" id="form-inicio-conversa" class="form" >
                    
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    
                      <div class="form-group col-lg-9 col-md-9 col-sm-9">
                        <label for="dataVigenciaInicio" class="control-label col-lg-4 col-md-4 col-sm-4">Início:</label>
                        <div class="input-group date col-lg-7 col-md-7 col-sm-6">
                          <input type="text" class="form-control" id="dataVigenciaInicio" name="dataVigenciaInicio"/>
                          <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                          </span>
                        </div>
                      </div>

                      <div class="form-group col-lg-9 col-md-9 col-sm-9">
                        <label for="dataVigenciaFim" class="control-label col-lg-4 col-md-4 col-sm-4">Fim:</label>
                        <div class="input-group date col-lg-7 col-md-7 col-sm-6">
                          <input type="text" class="form-control" id="dataVigenciaFim" name="dataVigenciaFim">
                          <span class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                          </span>
                        </div>
                      </div>
                      
                      <div class="form-group col-lg-9 col-md-9 col-sm-9">
                        <label for="emailContato" class="control-label col-lg-4 col-md-4 col-sm-4">Email Contato:</label>
                        <div class="input-group col-lg-7 col-md-7 col-sm-6">
                          <input type="email" class="form-control" id="emailContato"  name="emailContato">
                        </div>
                      </div>
                    </div>
                    
                  </form>
                  
                  <div class="row spacer-vertical10">
                    <div class="col-lg-12">
                      <div class="pull-right">
                        <a class="btn btn-primary" href="#" id="btnIniciar">Iniciar <i class="fa fa-arrow-right"></i></a>
                      </div>
                    </div>  
                  </div>
                  
                </div>
              </div>
            </div>
          
            <div id="painel-mensagens" class="col-lg-6 col-md-6 col-sm-8">

              <div class="panel panel-default">
                <div class="panel-body">
                
                  <form action="#" id="form-mensagem" class="form" >
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" id="idConversa" name="idConversa" value="" />
                    
                    <div class="row">
                      <div id="conversa" class="conversa col-lg-12 col-md-12">
                      </div>
                    </div>
                    
                    <div class="row spacer-vertical10">
                      <div class="col-lg-9 col-md-8 col-sm-8 col-xs-9">
                        <div class="form-group">
                          <textarea class="form-control" rows="3" id="conteudo" name="conteudo"></textarea>
                        </div>
                      </div>
                      <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                        <a class="btn btn-primary" href="#"  id="btnEnviarMensagem">Enviar</a>
                      </div>
                    </div>
                    
                    
                  </form>
                  
                  
                </div>
              </div>
            </div>
            
          </div>
          
       
      
      
      
<script id="viewTmplMensagem" type="text/x-jsrender">
<div class="col-lg-12 col-md-12">
  <div class="well well-sm {{:htmlclass}} col-lg-10 col-md-10 col-sm-10 col-xs-10">
    <p>{{:conteudoHtml}}</p>
    <div class="nome-tempo {{:htmlclass}}">{{:usuario}} - {{:dataEnvio}}</div>
  </div>
</div>
</script>  

      
      
      
<style type="text/css">

.table-conversas tr{
  cursor: pointer;
}

.table-participantes tr{
  cursor: pointer;
}

.conversa {
  height: 300px;
  overflow: auto;
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
      

