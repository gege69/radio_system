<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Chamada Proprietário de Veículo</h3>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-lg-12 col-md-12">
            <div class="form-group">
              <label for="file">Exemplo:</label>
              <select class="form-control" id="tipo" name="tipo">
                <option value="1" >Carro</option>
                <option value="2" >Moto</option>
              </select>
            </div>
          </div>
          <div class="col-lg-12 col-md-12">
            <div class="form-group">
              <label for="file">Exemplo:</label>
              <select class="form-control" id="marca" name="marca">
                <option value="1" >Volkswagen</option>
                <option value="2" >Fiat</option>
                <option value="3" >Chevrolet</option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Ok</button>
      </div>
    </div>
  </div>
</div>