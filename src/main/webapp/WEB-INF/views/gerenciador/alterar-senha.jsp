<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
 
    <div class="row">
    
      <div class="col-md-12 col-sm-12">
        <div class="panel panel-default">
          <div class="panel-body">

            <div class="row">
              <div class="col-lg-6 col-md-6">
                <h3>Alterar Senha<br/>
                  <small>Atualize com frequência sua senha</small>
                </h3>
              </div>

              <div class="col-lg-6 col-md-6" id="alertArea">
              </div>
            </div>
            
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
                  <label for="mostrarSenha" class="control-label col-sm-2 col-md-4"></label>
                  <div class="checkbox col-lg-4 col-md-4 col-sm-4 col-xs-12">
                    <label>
                      <input type="checkbox" id="mostrarSenha" name="mostrarSenha" value="false"> Mostrar senha
                    </label>
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-3 col-md-4">Confirmação Nova Senha:</label>
                  <div class="col-sm-5 col-md-4">
                    <input type="password" class="form-control" id="matchingPassword" name="matchingPassword" placeholder="Confirmação">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-3 col-md-4"></label>
                  <div class="col-sm-5 col-md-4">
                    <button type="button" class="btn btn-primary" id="btnAlterar">Alterar Senha</button>
                  </div>
                </div>
              </form>
            </div>
            
            <div class="spacer-vertical80"></div>
          
            <div class="row">
              <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="pull-right-not-xs">
                  <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
                </div>            
              </div>
            </div>
            
          </div>
        </div>
      </div>
      
    </div>
    
  </div> <!-- /container -->
  
  
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.serializeJSON/2.7.2/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="${context}/js/required/zxcvbn.js"></script>
  
  
<script type="text/javascript">


    var validaForm = function(){
        
        var isOk = true;
        
        removeErros();
        
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
                url: '${context}/senha',
                dataType: 'json',
                data: JSON.stringify( $('#alterar-senha-form').serializeJSON() )
            }).done( function(json){ 

                if (json.ok != null && json.ok == true){
                    preencheAlertGeral( "alertArea", "Senha alterada com sucesso.", "success" );
                    jump(''); // topo da pagina
                }
                else
                    preencheErros( json.errors );
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
        
        $('#password').keyup( function( event ) {
            keyup_validasenha( $("alterar-senha-form"), event );
        });
        
        $("#mostrarSenha").click(function(){
            if ( $("#mostrarSenha").prop('checked') ){
                $("#matchingPassword").attr("type", "input");
                $("#password").attr("type", "input");
            }
            else {
                $("#matchingPassword").attr("type", "password");
                $("#password").attr("type", "password");
            }
        }); 
    });
    
</script>
  
  

<jsp:include page="/WEB-INF/views/bottom.jsp" />