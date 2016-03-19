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
        
          <form action="#" id="formtocar" class="form-horizontal">

            <div class="form-group">
              <label for="login" class="control-label col-lg-2">Frase Inicial</label>
              <div class="col-lg-10">
                <select class="form-control" id="comboFraseInicial" name="veic_frase_ini">
                </select>
              </div>
            </div> 
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-2 col-sm-3">Marca</label>
              <div class="col-lg-4 col-md-4 col-sm-6">
                <select class="form-control" id="comboMarca" name="veic_marca">
                </select>
              </div>
            </div> 
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-2 col-sm-3">Modelo</label>
              <div class="col-lg-4 col-md-4 col-sm-6">
                <select class="form-control" id="comboModelo" name="veic_modelo">
                </select>
              </div>
            </div> 
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-2">Cor</label>
              <div class="col-lg-4 col-md-4 col-sm-6">
                <select class="form-control" id="comboCor" name="veic_cor">
                </select>
              </div>
            </div> 
            
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-2 col-sm-3">Placa</label>
              <div class="col-lg-2 col-md-2 col-sm-2">
                <select class="form-control" id="comboPlacaLetra1" name="veic_placa_letra">
                </select>
              </div>
              <div class="col-lg-2 col-md-2 col-sm-2">
                <select class="form-control" id="comboPlacaLetra2" name="veic_placa_letra">
                </select>
              </div>
              <div class="col-lg-2 col-md-2 col-sm-2">
                <select class="form-control" id="comboPlacaLetra3" name="veic_placa_letra">
                </select>
              </div>
              
              <div class="col-lg-2 col-md-2 col-sm-2">
                <select class="form-control" id="comboPlacaNumero1" name="veic_placa_numero">
                </select>
              </div>
              <div class="col-lg-2 col-md-2 col-sm-2">
                <select class="form-control" id="comboPlacaNumero2" name="veic_placa_numero">
                </select>
              </div>
            </div> 
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-2">Frase Final</label>
              <div class="col-lg-4 col-md-4 col-sm-6">
                <select class="form-control" id="comboFraseFinal" name="veic_frase_fim">
                </select>
              </div>
            </div>
            
          </form>


        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Cancelar</button>
        <button class="btn btn-primary" id="botaoConfirma">Ok</button>
      </div>
    </div>
  </div>
</div>


<script src="${context}/js/player-web/modal-chamada-veiculos.js" charset="UTF-8"></script>
