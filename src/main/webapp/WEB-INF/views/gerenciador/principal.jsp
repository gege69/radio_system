<jsp:include page="/WEB-INF/views/main.jsp" />    


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<jsp:include page="/WEB-INF/views/top.jsp" />    

  <div class="container">
  
    <div class="row">
      <div class="panel panel-default">
        <div class="panel-heading">
        
          <div class="row">
            <div class="col-lg-6 col-md-6">
              Painel Gerencial <a href="${context}/clientes/view">${razaoSocial}</a>
            </div>
            
            <div class="col-lg-6 col-md-6">
              <div class="pull-right">
                <c:if test="${isDono}">
                  <button class="btn btn-default" id="btnParametros"><i class="fa fa-lg fa-cog"></i> <i class="fa fa-caret-down"></i> </button>
                </c:if>
              </div>
            </div>
          </div>
        </div>
        
        <div class="panel-body">
          
          <div class="row">
          
              <div class="row row-centered">
                <div class="container">
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md btn-md botao-main" href="${context}/ambientes/new">
                      <i class="fa fa-2x icone-main fa-plus"></i>
                      <i class="fa fa-3x icone-main fa-tasks"></i>
                      <span class="label-botao-main">Incluir Ambiente</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/ambientes/searches">
                      <i class="fa fa-3x icone-main fa-tasks"></i>
                      <span class="label-botao-main">Administar Ambientes</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/senha/edit">
                      <i class="fa fa-3x icone-main fa-lock"></i>
                      <span class="label-botao-main">Alterar Senha</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/conversas/view">
                      <i class="fa fa-3x icone-main fa-envelope-o"></i>
                      <span class="label-botao-main">Mensagens</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main disabled" data-toggle="modal" href="${context}/fazer">
                      <i class="fa fa-3x icone-main fa-eye"></i>
                      <span class="label-botao-main">Monitoramento</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/view-atalho-cham-func">
                      <i class="fa fa-3x icone-main fa-users"></i>
                      <span class="label-botao-main">Chamada de</br>Funcionários</span>
                    </a>
                  </div>
                </div>              
              </div>
              
              <div class="row row-centered">
                <div class="container">
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/view-upload-multi">
                      <i class="fa fa-3x icone-main fa-cloud-upload"></i>
                      <span class="label-botao-main">Upload Ambientes</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/clientes/view">
                      <i class="fa fa-3x icone-main fa-street-view"></i>
                      <span class="label-botao-main">Dados de Cliente</span>
                    </a> 
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main disabled" data-toggle="modal" href="${context}/fazer">
                      <i class="fa fa-3x icone-main fa-floppy-o"></i>
                      <span class="label-botao-main">Softwares</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <a class="btn btn-default btn-block btn-md botao-main" href="${context}/usuarios/searches">
                      <i class="fa fa-3x icone-main fa-user-plus"></i>
                      <span class="label-botao-main">Usuários do<br/>Sistema</span>
                    </a>
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                  </div>
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                    <c:if test="${isAdministrador}">
                      <a class="btn btn-default btn-block btn-md botao-main" href="${context}/admin/painel">
                        <i class="fa fa-3x icone-main fa-street-view"></i>
                        <span class="label-botao-main">ADMINISTRAR</span>
                      </a> 
                    </c:if>
                  </div>
                </div>              
              </div>
              
              <div class="row row-centered">
                <div class="container">
                  <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                  </div>
                </div>
              </div>
          </div>
          
          <div class="spacer-vertical40">
          </div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              Você possui ${qtdAmbientes} ambientes cadastrados
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="pull-right">
                <c:url var="logoutUrl" value="/logout"/>
                <form action="${logoutUrl}" method="post">
                  <input type="submit" class="btn btn-link" value="Log out" />
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
              </div>
            </div>
          </div>
          
        </div>
      </div>
    </div>
      
  </div> <!-- /container -->


  
<div id="myModalParametros" class="modal fade" tabindex="-1" role="dialog" style="display : none;">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">x</button>
        <h3>Parâmetros</h3>
      </div>
      <div class="modal-body">

        <form action="#" class="form" id="formParametros" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

          <div class="row row-centered">
            <div class="radiobutton col-lg-6 col-md-6 col-sm-12 col-xs-12">
              <label>
              <input type="radio" id="temaDefault" name="tema" value="default"> 
                <img alt="" src="${context}/images/themes/default.png" class="botaoImagemTema">
              </label>
            </div>
            <div class="radiobutton col-lg-6 col-md-6 col-sm-12 col-xs-12">
              <label>
              <input type="radio" id="temaSuperhero" name="tema" value="superhero"> 
                <img alt="" src="${context}/images/themes/superhero.png" class="botaoImagemTema">
              </label>
            </div>
            <div class="radiobutton col-lg-6 col-md-6 col-sm-12 col-xs-12">
              <label>
              <input type="radio" id="temaUnited" name="tema" value="united"> 
                <img alt="" src="${context}/images/themes/united.png" class="botaoImagemTema">
              </label>
            </div>
            <div class="radiobutton col-lg-6 col-md-6 col-sm-12 col-xs-12">
              <label>
              <input type="radio" id="temaDarkly" name="tema" value="darkly"> 
                <img alt="" src="${context}/images/themes/darkly.png" class="botaoImagemTema">
              </label>
            </div>
            <div class="radiobutton col-lg-6 col-md-6 col-sm-12 col-xs-12">
              <label>
              <input type="radio" id="temaJournal" name="tema" value="journal"> 
                <img alt="" src="${context}/images/themes/journal.png" class="botaoImagemTema">
              </label>
            </div>
            <div class="radiobutton col-lg-6 col-md-6 col-sm-12 col-xs-12">
              <label>
              <input type="radio" id="temaReadable" name="tema" value="readable"> 
                <img alt="" src="${context}/images/themes/readable.png" class="botaoImagemTema">
              </label>
            </div>
            <div class="radiobutton col-lg-6 col-md-6 col-sm-12 col-xs-12">
              <label>
              <input type="radio" id="temaSlate" name="tema" value="slate"> 
                <img alt="" src="${context}/images/themes/slate.png" class="botaoImagemTema">
              </label>
            </div>
          </div>              
        </form>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">Fechar</button>
        <button class="btn btn-primary" id="btnConfirmar">Confirmar</button>
      </div>
    </div>
  </div>
</div>

<script src="${context}/js/required/jquery.serializejson.js"></script>
<script src="${context}/js/required/jquery.populate.js"></script>

<script type="text/javascript">


    var getDados = function()
    {
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: '${context}/parametros',
            dataType: 'json'
        }).done( function(json) {
            removeErros();
            $('#formParametros').populate(json);

            $("#myModalParametros").modal({
                show:true, 
                backdrop: 'static',              
                keyboard: false
            });
        });
    }


    var salvarParametro = function()
    {
        var dados = $('#formParametros').serializeJSON();

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '${context}/api/parametros',
            dataType: 'json',
            data : JSON.stringify( dados )
        }).done( function(json){
            if (json.ok != null){
                location.reload();
            }
            else{
                preencheErros( json.errors );
            }
        } );
    }



$(function(){

    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    
    $("#btnParametros").click(function(){
        getDados();
    });
    
    $("#btnConfirmar").click(function(){
        salvarParametro();
    })

});

</script>


<style type="text/css">

.botaoImagemTema {
  width : 280px;
  border : solid 1px #ccc;
}

.modal-body {
    height:auto;
    overflow:auto;
}

</style>



<jsp:include page="/WEB-INF/views/versao.jsp" />

<jsp:include page="/WEB-INF/views/bottom.jsp" />