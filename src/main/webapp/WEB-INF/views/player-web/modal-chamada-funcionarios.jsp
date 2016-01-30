<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Chamada de Funcionários</h3>
      </div>
      <div class="modal-body">
        <div class="row">
        
          <div class="col-lg-12 col-md-12">
            <div class="form-group">
              <label for="file">Funcionário:</label>
              <select class="form-control" id="funcionario" name="funcionario">
              </select>
            </div>
          </div>
          <div class="col-lg-12 col-md-12">
            <div class="form-group">
              <label for="file">Frase/Chamada:</label>
              <select class="form-control" id="frase" name="frase">
              </select>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Cancelar</button>
        <button class="btn btn-primary" id="botaoConfirma">Ok</button>
      </div>
    </div>
  </div>
</div>

<script src="${context}/js/player-web/modal-chamada-funcionarios.js" charset="UTF-8"></script>

