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

          <form class="form-horizontal" action="${context}/views/painel/editar-ambiente.jsp">

            <div class="row">
              <div class="col-lg-6 col-md-6">
              
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Nome do Ambiente:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="ambiente">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">E-mail:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="email" class="form-control" id="email">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Telefones:</label>
                  <div class="col-sm-5 col-md-4">
                    <input type="text" class="form-control" id="telefone">
                  </div>
                </div>
                
                <div class="form-group">
                  <div class="col-sm-offset-2 col-md-offset-4 col-sm-5 col-md-4">
                    <input type="text" class="form-control" id="telefone2">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Endereço:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="endereco">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Bairro:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="bairro">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Estado:</label>
                  <div class="col-sm-10 col-md-8">
                    <select class="form-control" id="estaod" name="">
                    </select>
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Cidade:</label>
                  <div class="col-sm-10 col-md-8">
                    <input type="text" class="form-control" id="cidade">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="control-label col-sm-2 col-md-4" for="chave">Anotações:</label>
                  <div class="col-sm-10 col-md-8">
                    <textarea class="form-control" rows="5" id="bnotacoes" name=""></textarea>
                  </div>
                </div>
              </div>
              
              <div class="col-lg-6 col-md-6">
              
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Login:</label>
                  <div class="col-sm-3 col-md-5">
                    <input type="email" class="form-control" id="login" placeholder="Login">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Senha:</label>
                  <div class="col-sm-3 col-md-5">
                    <input type="password" class="form-control" id="senha" placeholder="Senha">
                  </div>
                </div>
                
                <div class="form-group">
                  <label for="login" class="control-label col-sm-2 col-md-4">Fuso-horário:</label>
                  <div class="col-sm-4 col-md-6">
                    <select class="form-control" id="fuso" name="">
                    </select>
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="control-label col-sm-2 col-md-4" for="exampleInputAmount">Opcionais:</label>
                  <div class="checkbox col-sm-8">
                    <label>
                      <input type="checkbox" id="check_ii_vigencia" divexibir="div_vigencia_ii"> Para cada rádio com os áudios opcionais existe o valor
                    </label>
                  </div>
                </div>
              
              </div>
            </div>
                        
            <div class="row">
              <div class="col-md-offset-7 col-sm-offset-7">
                <div class="form-group">
                  <div class="col-sm-offset-5 col-sm-6 col-md-offset-6">
                    <button type="submit" class="btn btn-default">Salvar Alterações</button>
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

<jsp:include page="/bottom.jsp" />