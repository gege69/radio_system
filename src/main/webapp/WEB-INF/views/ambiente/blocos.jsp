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
              <h3><i class="${func.sizeSmall} ${func.classesIcone}">${func.icone}</i> Configurar Blocos</h3>
              <h4><small>Configuração de blocos de áudio de ${nome}</small></h4>
            </div>

            <div class="col-lg-6 col-md-6" id="alertArea">
            </div>
          </div>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-7 col-md-8 col-sm-8">
              <div class="panel panel-default">
                <div class="panel-body">
                   
                  <jsp:include page="/WEB-INF/views/ambiente/blocos-template.jsp" />

                </div>
              </div>
            </div>

<!--             <div class="col-lg-7 col-md-8 col-sm-8"> -->
            <div class="col-lg-5 col-md-4 col-sm-4 hidden-xs">
              <div class="panel panel-default">
                <div class="panel-body">
                  <h4>Exemplo: <span id="indicadorSalvo" style="display: none;" class="label label-success">Salvo</span></h4>
                  <div class="row">
                    <div class="col-lg-12">
                      <div id="containerExemplo" style="margin-left:10px;"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="spacer-vertical20"></div>

          </div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="">
                <a class="btn btn-primary" id="btnSalvarBloco" href="#">Salvar Alterações</a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="pull-right-not-xs">
                <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/programacoes/view" id="btnAvançado">
                  <i class="material-icons md-18">grid_on</i> Configurar Programação Musical
                </a>
              </div>
            </div>
          </div>

          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="${context}/ambientes/${idAmbiente}/view" >
                  <i class="fa fa-arrow-left"></i>
                  Voltar para ${nome}
                </a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right-not-xs">
                <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
              </div>
            </div>
          </div>
          
        </div>
      </div>
    </div>
    
      
  </div> <!-- /container -->


<script src="https://cdnjs.cloudflare.com/ajax/libs/jsrender/0.9.78/jsrender.min.js"></script>

<script id="viewTmplOpcionais" type="text/x-jsrender"  charset="UTF-8">
  <div class="checkbox col-lg-6 col-md-6 col-sm-6 col-xs-12">
    <label>
      <input type="checkbox" class="checkbox-opcional checkBloco" id="opcional-{{:idOpcional}}" name="opcionais[][idOpcional]" value="{{:idOpcional}}"> {{:nome}}
    </label>
  </div> 
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.7.2/jquery.serializejson.min.js"></script>
<script src="${context}/js/required/jquery.populate.min.js"></script>

<script src="${context}/js/ambiente/blocos.js"  charset="UTF-8"></script>

<jsp:include page="/WEB-INF/views/bottom.jsp" />