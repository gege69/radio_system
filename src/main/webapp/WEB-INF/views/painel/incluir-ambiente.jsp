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
                <input type="text" class="form-control" id="nome_amb" name="nome">
              </div>
            </div>
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-3">Login:</label>
              <div class="col-sm-5 col-md-4">
                <input type="email" class="form-control" id="login_amb" placeholder="Login" name="login" >
              </div>
            </div>
            
            <div class="form-group">
              <label for="login" class="control-label col-sm-2 col-md-3">Senha:</label>
              <div class="col-sm-5 col-md-4">
                <input type="password" class="form-control" id="password_amb" placeholder="Senha" name="password">
              </div>
            </div>
            
            <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <div class="">
                  <a class="btn btn-default" href="${context}/administrar-ambiente" >
                    <i class="fa fa-arrow-left"></i>
                    Voltar para Administrar Ambientes</a>
                </div>            
              </div>
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <div class="text-right">
                  <button type="button" class="btn btn-primary" id="btnAdicionar">
                    <i class="fa fa-floppy-o"></i>
                    Adicionar
                  </button>
                </div>            
              </div>
            </div>
            
            
          </form>
          
          <div class="spacer-vertical40"></div>
          
        </div>  <!-- panel body -->
      </div>
      
      
    </div>
    
    <div class="row">
      <div class="pull-right">
        <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->
  
<script type="text/javascript">


    var validaForm = function(){
        
        var isOk = true;
        
        removeErros( $('#ambiente-form') );
        
        var arrayCampos = [
                            {field: "nome_amb",          desc : "Nome do Ambiente"},
                            {field: "login_amb",         desc : "Login" }, 
                            {field: "password_amb",      desc : "Senha"}
                          ];
        
        isOk = validaCampos( arrayCampos );
        
        return isOk;
    };

    var salvar = function(){
        
        if ( validaForm() ){
            
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '${context}/ambientes',
                dataType: 'json',
                data: JSON.stringify( $('#ambiente-form').serializeJSON() ),
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