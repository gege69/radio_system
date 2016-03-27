<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

</script>


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="row" id="alertArea">
      </div>
    
    
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Cadastro de Cliente</h3>
          
          <div class="spacer-vertical40"></div>

          <div class="row">
            <div class="col-lg-12">
              <jsp:include page="/WEB-INF/views/generico/template-form-editar-cliente.jsp" />
            </div>
          </div>

          <div class="spacer-vertical20"></div> 

          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="${context}/admin/clientes/searches" >
                  <i class="fa fa-arrow-left"></i>
                  Voltar para Cadastro de Clientes</a>
              </div>            
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <a class="btn btn-default" href="${context}/admin/painel">Painel de Admin</a>
              </div>
            </div>
          </div>
          
        </div>
      </div>
    </div>
      
  </div> <!-- /container -->


<script type="text/javascript" src="${context}/js/required/jquery.serializejson.js" defer></script>
<script type="text/javascript" src="${context}/js/required/jquery.populate.js" async></script>
<script type="text/javascript" src="${context}/js/required/jsrender.min.js" defer></script>
<script type="text/javascript" src="${context}/js/required/jquery.mask.min.js" defer></script>

<link href="${context}/css/bootstrap-table/bootstrap-table.css" rel="stylesheet">

<script type="text/javascript" src="${context}/js/admin/editar-cliente.js" async charset="UTF-8"></script>

<script src="${context}/js/required/bootstrap-table/bootstrap-table.js" ></script>
<script src="${context}/js/required/bootstrap-table/locale/bootstrap-table-pt-BR.js" charset="UTF-8" r></script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />