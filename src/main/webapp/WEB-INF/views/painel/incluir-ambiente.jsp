<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="row" id="alertArea">
      </div>
    
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Incluir Ambiente<br/>
            <small>Preencha as informações</small>
          </h3>
          
          
          <div class="spacer-vertical40"></div>
          
          <form class="form-horizontal" id="ambiente-form" action="#" role="form">
            
            <!-- Necessário pro Spring -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
           
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-3">Nome do Ambiente:</label>
              <div class="col-sm-10 col-md-8">
                <input type="text" class="form-control" id="nm_ambiente_amb" name="nm_ambiente_amb">
              </div>
            </div>
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-3">Login:</label>
              <div class="col-sm-5 col-md-4">
                <input type="email" class="form-control" id="cd_login_amb" placeholder="Login" name="cd_login_amb" >
              </div>
            </div>
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-3">Senha:</label>
              <div class="col-sm-5 col-md-4">
                <input type="password" class="form-control" id="cd_password_amb" placeholder="Senha" name="cd_password_amb">
              </div>
            </div>
            
            <div class="form-group">
              <div class="col-sm-offset-5 col-sm-6 col-md-offset-6">
                <button type="button" class="btn btn-default" id="btnAdicionar">Adicionar</button>
              </div>
            </div>
          </form>
          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-offset-10 col-sm-offset-9 col-xs-offset-7">
        <a class="btn btn-default" href="${context}/gerenciador/principal">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->
  
<script type="text/javascript">


    var validaForm = function(){
        
        var isOk = true;
        
        removeErros( $('#ambiente-form') );
        
        var arrayCampos = [
                            {field: "nm_ambiente_amb",      desc : "Nome do Ambiente"},
                            {field: "cd_login_amb",         desc : "Login" }, 
                            {field: "cd_password_amb",      desc : "Senha"}
                          ];
        
        isOk = validaCampos( arrayCampos );
        
        return isOk;
    };

    var salvar = function(){
        
        if ( validaForm() ){
            
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '${context}/gerenciador/ambientes',
                dataType: 'json',
                data: toJSON($('#ambiente-form').serializeArray()),
                success: function(json){
                  
                    if (json.id != null){
                      
                        preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
                          
                        jump(''); // topo da pagina
                    }
                    else{
                        preencheErros( json.errors );
                    }
                }
            });
        }
        
    };

    $(function(){
       
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        $('#btnAdicionar').on('click', salvar);
        
    });

</script>
  

<jsp:include page="/WEB-INF/views/bottom.jsp" />