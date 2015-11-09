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
<!--           <div class="col-lg-12 col-md-12"> -->
<!--             <table   -->
<!--                id="table" -->
<!--                data-toggle="table" -->
<%--                data-url="${context}/ambientes/${idAmbiente}/midias-por-categoria?codigo=chamada_inst" --%>
<!--                data-height="400" -->
<!--                data-side-pagination="server" -->
<!--                data-pagination="true" -->
<!--                data-page-size=5 -->
<!--                data-page-list="[5]" -->
<!--                data-locale = "pt_BR" -->
<!--                data-query-params="queryParams" > -->
<!--               <thead> -->
<!--                 <tr> -->
<!--                     <th data-field="nome">Nome</th> -->
<!--                     <th data-field="descricao">Descrição</th> -->
<!--                     <th data-field="dataUpload">Data Upload</th> -->
<!--                 </tr> -->
<!--               </thead> -->
<!--             </table> -->
<!--           </div>                         -->
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Cancelar</button>
        <button class="btn btn-primary" id="botaoConfirma">Ok</button>
      </div>
    </div>
  </div>
</div>


<script  type="text/javascript">

    var listaChamadas = function(){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/${idAmbiente}/midias-por-categoria?codigo=chamada_inst',
            dataType: 'json'
        }).done( function(json){
            
            var lista = json.rows;
            
            var listitems = "";
            $('#chamada_inst').empty();
            
            $.each( lista, function( idx, obj ){
                listitems += '<option value=' + obj.idMidia + '>' + obj.descricao + '</option>';
            });
            
            $('#chamada_inst').append(listitems);
        } );
    }

    var confirma = function()
    {
        var primeiro = $('#chamada_inst').val();
        
        var array = [];
        array[0] = primeiro;
        
        playSequence( array );
        $('#myModal').modal('hide');
    }
    

    $(function(){
        
        listaChamadas();
        
        $("#botaoConfirma").click( function(){
            confirma();
        });          
        
    });

</script>
