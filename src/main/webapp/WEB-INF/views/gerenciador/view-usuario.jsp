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
          <h3>Usuário<br/>
            <small>Preencha as informações do Usuário</small>
          </h3>
          
          <div class="spacer-vertical40"></div>

          <form class="form-horizontal" id="usuario-form" action="#" method="POST">
          
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" id="idUsuario" name="idUsuario" value="${idUsuario}" >
  
            <div class="row">
              <div class="col-lg-6 col-md-6">
              
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Nome:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="nome" name="nome">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">E-mail:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="email" class="form-control" id="email1" name="email1">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Login:</label>
                  <div class="col-sm-3 col-md-5">
                    <input type="text" class="form-control" id="login" name="login" placeholder="Login">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Senha:</label>
                  <div class="col-sm-3 col-md-5">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Senha">
                  </div>
                </div>
                
              </div>
              
              <div class="col-lg-6 col-md-6">
              
              </div>
            </div>
            
            
            <div class="row">
              <div class="col-lg-12 col-md-12">
                <div class="pull-right">
                  <button type="button" class="btn btn-primary" id="btnSalvar">
                    <i class="fa fa-floppy-o"></i>
                    Salvar Alterações
                  </button>
                </div>            
              </div>
            </div>
            
            <div class="spacer-vertical40"></div>
            
            <div class="row">
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <div class="">
                  <a class="btn btn-default" href="${context}/view-list-usuarios-sistema" >
                    <i class="fa fa-arrow-left"></i>
                    Voltar para Usuários</a>
                </div>            
              </div>
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <div class="pull-right">
                  <a class="btn btn-default" href="${context}/principal">Painel Gerencial</a>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
      
  </div> <!-- /container -->


<script src="${context}/js/required/jquery.serializejson.js"></script>
<script src="${context}/js/required/jquery.populate.js"></script>

<script type="text/javascript">

    var getDados = function( id )
    {
        if ( id == null || id == undefined )
            alert('Id não encontrado');
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/usuarios/'+id,
            dataType: 'json'
        }).done( function(json) {
            
            removeErros( $('#usuario-form') );
             $('#usuario-form').populate(json);
            jump('ncmForm');
        });
    }
    
    var getFusos = function()
    {
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/fusohorarios',
            dataType: 'json'
        }).done( function(json) {
            $.each( json.rows , function (i, fuso){
                var text_str = fuso.offsetfuso + " - " + fuso.alias
                $('#idFusohorario').append($('<option>', { 
                    value: fuso.idFusohorario,
                    text : text_str  
                }));
            });
            
            jump('ncmForm');
        });
        
    }


    var validaForm = function(){
        
        var isOk = true;
        
        removeErros( $('#usuario-form') );
        
        var arrayCampos = [
                            {field: "nome",      desc : "Nome do Ambiente"},
                            {field: "login",         desc : "Login" }, 
                            {field: "password",      desc : "Senha"}
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
                data:  JSON.stringify( $('#usuario-form').serializeJSON() )
                
            }).done( function(json){ 

                if (json.ok == 1){
                    preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
                    jump(''); // topo da pagina
                }
                else{
                    preencheErros( json.errors );
                }
            });
        }
        
    };

    $(function(){
        
        getFusos();

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
       
        $('#btnSalvar').on('click', salvar);
        
        getDados( $('#idUsuario').val() );
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />