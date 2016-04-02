<jsp:include page="/WEB-INF/views/main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="_csrf" th:content="${_csrf.token}"/>


</script>


  <div class="container">
  
    <div class="jumbotron">
      <h2>Logotipo aqui!</h2>
    </div>

    <div class="row">
    
      <div class="row" id="alertArea">
      </div>
    
    
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Editar Ambiente</h3>
          
          <div class="spacer-vertical40"></div>

          <form class="form-horizontal" id="ambiente-form" action="#" method="POST">
          
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" id="idAmbiente_amb" name="idAmbiente" value="${idAmbiente}" >
  
            <div class="row">
              <div class="col-lg-6 col-md-6">
              
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Nome do Ambiente:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="nome_amb" name="nome">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">E-mail:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="email" class="form-control" id="email1_amb" name="email1">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Telefones:</label>
                  <div class="col-sm-5 col-md-4">
                    <input type="text" class="form-control" id="telefone1_amb" name="telefone1">
                  </div>
                </div>
                
                <div class="form-group">
                  <div class="col-sm-offset-2 col-md-offset-4 col-sm-5 col-md-4">
                    <input type="text" class="form-control" id="telefone2_amb" name="telefone2">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">CEP:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="cep_amb" name="cep" maxlength="8">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Endereço:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="logradouro_amb" name="logradouro">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Bairro:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="bairro_amb" name="bairro">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Estado:</label>
                  <div class="col-sm-10 col-md-8">
                    <select class="form-control" id="estado_amb" name="estado">
                      <option value="AC">Acre </option>
                      <option value="AL">Alagoas </option>
                      <option value="AP">Amapá </option>
                      <option value="AM">Amazonas </option>
                      <option value="BA">Bahia </option>
                      <option value="CE">Ceará </option>
                      <option value="DF">Distrito Federal </option>
                      <option value="ES">Espírito Santo </option>
                      <option value="GO">Goiás </option>
                      <option value="MA">Maranhão </option>
                      <option value="MT">Mato Grosso </option>
                      <option value="MS">Mato Grosso do Sul </option>
                      <option value="MG">Minas Gerais </option>
                      <option value="PA">Pará </option>
                      <option value="PB">Paraíba </option>
                      <option value="PR">Paraná </option>
                      <option value="PE">Pernambuco </option>
                      <option value="PI">Piauí </option>
                      <option value="RJ">Rio de Janeiro </option>
                      <option value="RN">Rio Grande do Norte </option>
                      <option value="RS">Rio Grande do Sul </option>
                      <option value="RO">Rondônia </option>
                      <option value="RR">Roraima </option>
                      <option value="SC">Santa Catarina </option>
                      <option value="SP">São Paulo </option>
                      <option value="SE">Sergipe </option>
                      <option value="TO">Tocantins</option>
                    </select>
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Cidade:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="cidade_amb" name="cidade">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="control-label col-sm-2 col-md-4" for="chave">Anotações:</label>
                  <div class="col-sm-10 col-md-8">
                    <textarea class="form-control" rows="5" id="anotacoes_amb" name="anotacoes"></textarea>
                  </div>
                </div>
              </div>
              
              <div class="col-lg-6 col-md-6">
              
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Login:</label>
                  <div class="col-sm-3 col-md-5">
                    <input type="text" class="form-control" id="login_amb" name="login" placeholder="Login">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Senha:</label>
                  <div class="col-sm-3 col-md-5">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Senha">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Fuso-horário:</label>
                  <div class="col-sm-4 col-md-6">
                    <select class="form-control" id="idFusohorario" name="fusoHorario[idFusohorario]">
                    </select>
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="control-label col-sm-2 col-md-4" for="exampleInputAmount">Opcionais:</label>
                  <div class="checkbox col-sm-8">
                    <label>
                      <input type="checkbox" id="opcionais_amb" name="opcionais" value="true"> Para cada rádio com os áudios opcionais existe o valor de R$ XX mensais.
                    </label>
                  </div>
<!--                   <div class="checkbox col-sm-8"> -->
<!--                     <label> -->
<!--                       <input type="checkbox" id="fl_opcionais_amb" name="fl_opcionais_amb"> Para cada rádio com os áudios opcionais existe o valor de R$ XX mensais. -->
<!--                     </label> -->
<!--                   </div> -->
                </div>
                
              
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
                  <a class="btn btn-default" href="${context}/ambientes/searches" >
                    <i class="fa fa-arrow-left"></i>
                    Voltar para Administrar Ambientes</a>
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


<script type="text/javascript" src="${context}/js/required/jquery.serializejson.js"></script>
<script type="text/javascript" src="${context}/js/required/jquery.populate.js"></script>
<script type="text/javascript" src="${context}/js/required/zxcvbn.js"></script>

<script type="text/javascript">

    var getDados = function()
    {
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/ambientes/'+ ${idAmbiente},
            dataType: 'json'
        }).done( function(json) {
            
            removeErros( $('#ambiente-form') );
             $('#ambiente-form').populate(json);
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
        
        removeErros( $('#ambiente-form') );
        
        var arrayCampos = [
                            {field: "nome_amb",      desc : "Nome do Ambiente"},
                            {field: "login_amb",         desc : "Login" }, 
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
                data:  JSON.stringify( $('#ambiente-form').serializeJSON() )
                
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
        
        getDados();
        
        $('#password').keyup( function( event ) {
            keyup_validasenha( event );
        });
        
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />