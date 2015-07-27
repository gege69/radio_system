<jsp:include page="/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Administrar Ambiente<br/>
            <small>Você possui <span id="qtd-ambientes"></span> ambiente(es) cadastrado(s)</small>
          </h3>
          
          <div class="spacer-vertical40"></div>
          
          
          <div class="row">
            <div class="col-lg-10 col-md-10">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-2 col-sm-4 col-xs-5">Ambiente</div>
                    <div class="col-md-offset-10 col-sm-offset-9 col-xs-offset-9">Opções</div>
                  </div>
                </div>
                <div class="panel-body">
                  <div class="table-responsive">
                  
                    <table class="table table-hover">
                      <tbody id="tableBody">
                      </tbody>
                      
                    </table>
                    
                  </div> 
                </div>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-10 col-md-10">
              <div class="checkbox">
                <label>
                  <input type="checkbox" id="check_pacote"> Somente com pacote de Programetes opcionais
                </label>
              </div>
              
              <div class="spacer-vertical40"></div>
              
              <div class="">
                <a class="btn btn-default" href="${context}/views/painel/incluir-ambiente.jsp">Adicionar Novo Ambiente</a>
              </div>            
            </div>
          </div>
          
          <div class="spacer-vertical40"></div>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-offset-10 col-sm-offset-9 col-xs-offset-7">
        <a class="btn btn-default" href="${context}/views/painel/principal.jsp">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->



  
  
<script id="GridBodyTmpl" type="text/x-jsrender">
<tr>
  <td width="80%">
     {{:nm_ambiente_amb}}
  </td>
  <td>
    <a class="btn btn-link" href="${context}/gerenciador/espelhar_ambientes/{{:id_ambiente_amb}}">
      <i class="fa fa-lg fa-files-o"></i>
    </a>
  </td>
  <td>
    <a class="btn btn-link" href="${context}/gerenciador/editar_ambientes/{{:id_ambiente_amb}}">
      <i class="fa fa-lg fa-pencil-square-o"></i>
    </a>
  </td>
  <td>
    <a class="btn btn-link" href="#">
      <i class="fa fa-lg fa-trash-o"></i>
    </a>
  </td>
</tr>
</script>  

<script type="text/javascript">

    var pagina = 0, limit = 6;

    var listaAmbientes = function( doJump ){
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/gerenciador/ambientes',
            dataType: 'json',
            data: {'pagina': pagina},
            success: function(json){
                
                makeListTmpl(json);
            }
        });
    }

    var makeListTmpl = function(json){
        
        var tmpl = $.templates('#GridBodyTmpl');
        
        $('#tableBody').empty();
        
        var content = tmpl.render(json.data);
        
        $('#tableBody').append(content);
    };

    $(function(){
       
        listaAmbientes(false);
        
    });

</script>


<jsp:include page="/bottom.jsp" />