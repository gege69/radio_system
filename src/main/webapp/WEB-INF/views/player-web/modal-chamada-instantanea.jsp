<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Chamadas Instantâneas</h3>
      </div>
      <div class="modal-body">
        <div class="row">
        
          <div class="col-lg-12 col-md-12">
            <div class="form-group">
              <label for="file">Chamadas Instantâneas:</label>
              <select class="form-control" id="chamada_inst" name="chamada_inst">
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


<script src="${context}/js/player-web/modal-chamada-instantanea.js" charset="UTF-8"></script>
