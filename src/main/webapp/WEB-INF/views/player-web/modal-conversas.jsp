<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Mensagens</h3>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="panel panel-default">
            <div class="panel-body"> 
              <jsp:include page="/WEB-INF/views/mensagens/conversas.jsp" />
            </div>
          </div>  
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>

