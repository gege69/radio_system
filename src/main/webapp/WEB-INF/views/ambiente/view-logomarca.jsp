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

        <c:if test="${not empty error}">      
          <div class="alert alert-danger" role="alert" id="alertalertArea" >
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <div id="errogeral">${error}</div>
          </div>
        </c:if>
        
        <c:if test="${not empty success}">      
          <div class="alert alert-success" role="alert" id="alertalertArea" >
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <div id="errogeral">${success}</div>
          </div>
        </c:if>
        
      </div>
      
      <div class="panel panel-default">
        <div class="panel-body">
          <h3>Logomarca<br/>
            <small>Enviar logo marca do Ambiente ${nome}<br/>
                   Somente arquivos de imagem serão aceitos ( .PNG, .JPG, .JPEG, .GIF )</small>
          </h3>
          
          <div class="spacer-vertical20"></div>
          
          <div class="row">
            <div class="col-lg-12 col-md-12">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    
                    <div class="col-md-12">
                      <form action="${context}/ambientes/${idAmbiente}/view-logomarca" 
                            method="POST" 
                            id="ambiente-upload-midia" 
                            class="form" 
                            enctype="multipart/form-data">
                      
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        
                        <div class="col-lg-7 col-md-9 col-sm-12">
                          <div class="form-group">
                            <label for="file">Arquivo:</label>
                            <input type="file" class="form-control" id="arquivo" name="file" placeholder="algum arquivo">
                          </div>
                        </div>

                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                          <div class="form-group" id="checkBoxContainer">
                          </div>
                        </div>
                        
                        <div class="row">
                          <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                            <div class="form-group">
                              <a class="btn btn-primary" href="#" id="btnUploadLogo">Upload Logo</a>
                            </div>
                          </div>
                        </div>
                        
                      </form>
                    </div>
                    
                    
                  </div>
                </div>
              
                <div class="panel-body">
                  <div class="row row-centered">
                    <img alt="logo" src="${context}/ambientes/${idAmbiente}/logomarca" id="marca">
                  </div>
                </div>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
              <div class="">
                <a class="btn btn-default" href="${context}/view-ambiente/${idAmbiente}" >
                  <i class="fa fa-arrow-left"></i>
                  Voltar para ${nome}
                </a>
              </div>            
            </div>
          </div>
          
        </div>
      </div>
    </div>
    

      
  </div> <!-- /container -->




<script type="text/javascript">

    var upload = function()
    {
        $("#ambiente-upload-midia").submit();
    }

    $(function(){

        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        
        var idAmbiente = $('#idAmbiente').val();
        
        $('#btnUploadLogo').on('click', function(){
            upload();
        });
        
    });

</script>


<jsp:include page="/WEB-INF/views/bottom.jsp" />