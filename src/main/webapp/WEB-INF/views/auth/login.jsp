<jsp:include page="../main.jsp" />    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.4.0/animate.min.css" rel="stylesheet">

  <div class="container theme-showcase">
  
    <div class="jumbotron">
      <h1 >
        <div class="row row-centered">
          <img id="logo" src="${context}/images/eterion.jpg"> 
        </div>
      </h1>
    </div>

    <div class="row row-centered">
      <div class="col-xs-12 col-md-5 col-sm-6 col-lg-5 col-centered">
      
        <form action="login" method="POST">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="form-group">
            <label for="login">Login</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="Login" value="">
          </div>
          <div class="form-group">
            <label for="senha">Senha</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Senha" value="">
          </div>
          <button type="submit" class="btn btn-default">Entrar</button>
          
          <div class="spacer-vertical20"></div>
          
          <c:if test="${not empty param.err}">
              <div class="alert alert-danger">
                  <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
              </div>
          </c:if> 
        </form>
        
      </div>
    </div>
    
    <div class="spacer-vertical80"></div>
    
    
<!--     <div class="row"> -->
<!--       <div class="col-xs-12 col-md-5 col-sm-6 col-lg-5 col-centered"> -->
<%--         <a href="${context}/register">Clique aqui para se cadastrar</a> --%>
<!--       </div> -->
<!--     </div> -->
      
  </div> <!-- /container -->



<script type="text/javascript">

$(function(){

    var opcoes = ['hinge', 'rotateIn', 'tada', 'hinge', 'jello', 'fadeInLeft', 'hinge', 'flipInX', 'lighSpeedIn'];
    
    var minimum = 0;
    var maximum = 8;
    var randomnumber = Math.floor(Math.random() * (maximum - minimum + 1)) + minimum;
    
//     $('#logo').addClass('animated intfinite' + opcoes[randomnumber]);

    $('#logo').addClass('animated flipInX');
    
});

</script>


<jsp:include page="../bottom.jsp" />