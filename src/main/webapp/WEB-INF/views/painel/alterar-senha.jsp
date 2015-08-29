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
    
      <div class="col-md-10 col-sm-10">
        <div class="panel panel-default">
          <div class="panel-body">
            <h3>Alterar Senha<br/>
              <small>Atualize com frequência sua senha</small>
            </h3>
            
            
            <div class="spacer-vertical40"></div>
            
            <div>
              <form class="form-horizontal" id="alterar-senha-form" action="#">
              
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    
                <div class="form-group">
                  <label for="login" class="control-label col-sm-3 col-md-4">Senha Atual:</label>
                  <div class="col-sm-5 col-md-4">
                    <input type="password" class="form-control" id="senha_atual" name="senha_atual" placeholder="Atual">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-3 col-md-4">Nova Senha:</label>
                  <div class="col-sm-5 col-md-4">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Nova">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-3 col-md-4">Confirmação Nova Senha:</label>
                  <div class="col-sm-5 col-md-4">
                    <input type="password" class="form-control" id="matchingPassword" name="matchingPassword" placeholder="Confirmação">
                  </div>
                </div>
                
                <div class="form-group">
                  <div class="col-sm-offset-5 col-sm-6 col-md-offset-6">
                    <button type="button" class="btn btn-default" id="btnAlterar">Alterar Senha</button>
                  </div>
                </div>
              </form>
            </div>
            
          </div>
        </div>
      </div>
      
    </div>
    
    <div class="row">
      <div class="col-md-offset-8 col-sm-offset-7 col-xs-offset-7">
        <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->
  
  
<script type="text/javascript">


    var validaForm = function(){
        
        var isOk = true;
        
        removeErros( $('#alterar-senha-form') );
        
        var arrayCampos = [
                            {field: "senha_atual",        desc : "Senha Atual"},
                            {field: "password",         desc : "Nova senha" }, 
                            {field: "matchingPassword",      desc : "Confirmação da nova senha"}
                          ];
        
        isOk = validaCampos( arrayCampos );
        
        return isOk;
    };

    var alteraSenha = function(){
        
        if ( validaForm() ){
            
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '${context}/alterar-senha',
                dataType: 'json',
                data: toJSON($('#alterar-senha-form').serializeArray()),
                success: function(json){
                  
                    if (json.ok != null && json.ok == true){
                      
                        preencheAlertGeral( "alertArea", "Senha alterada com sucesso.", "success" );
                          
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
        
        $('#btnAlterar').on('click', alteraSenha);
        
    });

</script>
  

<jsp:include page="/WEB-INF/views/bottom.jsp" />