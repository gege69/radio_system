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
        <button class="btn btn-primary" data-dismiss="modal">Ok</button>
      </div>
    </div>
  </div>
</div>

<script  type="text/javascript">

    var listaFuncionarios = function(){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/midias-por-categoria?codigo=chamada_func_nome',
            dataType: 'json'
        }).done( function(json){
            
            var lista = json.rows;
            
            var listitems = "";
            $('#funcionario').empty();
            
            debugger;
            
            $.each( lista, function( idx, obj ){
                listitems += '<option value=' + obj.idMidia + '>' + obj.descricao + '</option>';
            });
            
            $('#funcionario').append(listitems);
        } );
    }
    
    var listaFrase = function(){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/midias-por-categoria?codigo=chamada_func_frase',
            dataType: 'json'
        }).done( function(json){

            var lista = json.rows;
            
            var listitems = "";
            $('#frase').empty();
            
            $.each( lista, function( idx, obj ){
                listitems += '<option value=' +  obj.idMidia + '>' + obj.descricao + '</option>';
            });
            
            $('#frase').append(listitems);
        } );
    }


    $(function(){
    
        listaFuncionarios();
        listaFrase();
        
    });

</script>
