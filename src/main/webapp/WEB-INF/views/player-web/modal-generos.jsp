<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
      
        <div class="row" id="alertArea">
        </div>
      
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Gêneros</h3>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-lg-12 col-md-12">
            <form action="#" id="ambiente-generos-form" method="POST">
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
              <input type="hidden" id="idAmbiente" value="${idAmbiente}">
              <div class="form-inline">
                <div class="container col-lg-12 col-md-12" id="view-container">
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Cancelar</button>
        <button class="btn btn-primary" id="btnSalvarGeneros" >Ok</button>
      </div>
    </div>
  </div>
</div>

<script id="viewTmpl" type="text/x-jsrender">
    
      <div class="checkbox col-lg-6 col-md-6 col-sm-6 col-xs-12">
        <label>
          <input type="checkbox" class="checkbox-genero" id="genero-{{:idGenero}}" name="genero[idGenero]" value="{{:idGenero}}"> {{:descricao}}
        </label>
      </div>
      
</script>  


<script src="${context}/js/player-web/modal-generos.js" charset="UTF-8"></script>

