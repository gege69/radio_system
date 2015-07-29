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
          <h3>Editar Ambiente</h3>
          
          <div class="spacer-vertical40"></div>

          <form class="form-horizontal" id="ambiente-form" action="#" method="PUT">
            <input type="hidden" id="id_ambiente_amb" name="id_ambiente_amb" value="${id_ambiente_amb}" >
  
            <div class="row">
              <div class="col-lg-6 col-md-6">
              
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Nome do Ambiente:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="nm_ambiente_amb" name="nm_ambiente_amb">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">E-mail:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="email" class="form-control" id="ds_email_aml" name="email.ds_email_aml">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Telefones:</label>
                  <div class="col-sm-5 col-md-4">
                    <input type="text" class="form-control" id="cd_telefone1_amb" name="cd_telefone1_amb">
                  </div>
                </div>
                
                <div class="form-group">
                  <div class="col-sm-offset-2 col-md-offset-4 col-sm-5 col-md-4">
                    <input type="text" class="form-control" id="dt_alteracao_amb" name="dt_alteracao_amb">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Endereço:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="nm_logradouro_aen" name="nm_logradouro_aen">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Bairro:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="nm_bairro_aen" name="nm_bairro_aen">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Estado:</label>
                  <div class="col-sm-10 col-md-8">
                    <select class="form-control" id="nm_estado_aen" name="nm_estado_aen">
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
                    <input type="text" class="form-control" id="nm_cidade_aen" name="nm_cidade_aen">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="control-label col-sm-2 col-md-4" for="chave">Anotações:</label>
                  <div class="col-sm-10 col-md-8">
                    <textarea class="form-control" rows="5" id="ds_anotacoes_amb" name="ds_anotacoes_amb"></textarea>
                  </div>
                </div>
              </div>
              
              <div class="col-lg-6 col-md-6">
              
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Login:</label>
                  <div class="col-sm-3 col-md-5">
                    <input type="text" class="form-control" id="cd_login_amb" name="cd_login_amb" placeholder="Login">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Senha:</label>
                  <div class="col-sm-3 col-md-5">
                    <input type="password" class="form-control" id="cd_password_amb" name="cd_password_amb" placeholder="Senha">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Fuso-horário:</label>
                  <div class="col-sm-4 col-md-6">
                    <select class="form-control" id="fuso" name="">
                      <option value="">Implementar...</option>
                    </select>
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="control-label col-sm-2 col-md-4" for="exampleInputAmount">Opcionais:</label>
                  <div class="checkbox col-sm-8">
                    <label>
                      <input type="checkbox" id="fl_opcionais_amb" name="fl_opcionais_amb"> Para cada rádio com os áudios opcionais existe o valor de R$ XX mensais.
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
              <div class="col-md-offset-7 col-sm-offset-7">
                <div class="form-group">
                  <div class="col-sm-offset-5 col-sm-6 col-md-offset-6">
                    <button type="button" class="btn btn-default" id="btnSalvar">Salvar Alterações</button>
                  </div>
                </div>
              </div>
            </div>
          
          </form>
          

          
        </div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-offset-10 col-sm-offset-9 col-xs-offset-7">
        <a class="btn btn-default" href="${context}/views/painel/principal.jsp">Painel Gerencial</a>
      </div>
    </div>
      
  </div> <!-- /container -->


<script type="text/javascript">


    var preencheForm = function( json )
    {
        removeErros( $('#ambiente-form') );
    
        $('#id_ambiente_amb').val( json.id_ambiente_amb );
        $('#nm_ambiente_amb').val( json.nm_ambiente_amb );
        
        var emails = json.emails;
        
        if ( emails != null && emails.length > 0 )
        {
            $('#ds_email_aml').val( emails[0].ds_email_aml );        
        }
      
        $('#cd_telefone1_amb').val( json.cd_telefone1_amb );
        $('#cd_telefone2_amb').val( json.cd_telefone2_amb );
        
        var enderecos = json.enderecos;
        
        if ( enderecos != null && enderecos.length > 0 )
        {
            $('#nm_logradouro_aen').val( json.nm_logradouro_aen );
            $('#nm_bairro_aen').val( json.nm_bairro_aen );
            $('#nm_estado_aen').val( json.nm_estado_aen );
            $('#nm_cidade_aen').val( json.nm_cidade_aen );
        }

        $('#ds_anotacoes_amb').val( json.ds_anotacoes_amb );
        $('#cd_login_amb').val( json.cd_login_amb );
        $('#cd_password_amb').val( json.cd_password_amb );

    }  

    var getDados = function( id )
    {
        if ( id == null || id == undefined )
            alert('Id não encontrado');
        
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/gerenciador/ambientes/'+id,
            dataType: 'json',
            success: function(json){
                  
                preencheForm( json );
                
                jump('ncmForm');
            }
          });
    }


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
            
            var formobj = $('#ambiente-form').serializeArray();
            
            // TODO ver como serializar lista....
            
            
            $.ajax({
                type: 'PUT',
                contentType: 'application/json',
                url: '${context}/gerenciador/ambientes',
                dataType: 'json',
                data: toJSON( formobj ),
                success: function(json){
                  
                    if (json.id != null){
                      
                        preencheAlertGeral( "alertArea", "Registro salvo com sucesso.", "success" );
                          
                        jump(''); // topo da pagina
                    }
                    else{
                        
                        $.each(json.errors, function(pos){
                            
                            var obj = json.errors[pos];
                            
                            preencheErroField( obj.message, obj.field );
                            
                        });    
                    }
                }
            });
        }
        
    };

    $(function(){
       
        $('#btnSalvar').on('click', salvar);
        
        getDados( $('#id_ambiente_amb').val() );
        
    });

</script>


<jsp:include page="/bottom.jsp" />