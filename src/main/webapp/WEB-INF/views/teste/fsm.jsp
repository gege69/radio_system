<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

  <div>
    <button id="btnLegal">Legal</button>
    <button id="btnBacana">Bacana</button>
    <button id="btnMoido">Moido</button>
    <button id="btnUpa">Upa</button>
  </div>
  
  <br>
  <br>
  <div>
    <button id="btnStep1">Step1</button>
    <button id="btnStep2">Step2</button>
    <button id="btnStep3">Step3</button>
  </div>

  <br>
  <br>
  <div>
    <button id="btnSalva">Salva</button>
    <button id="btnSalvaPesquisa">Salva Pesquisa</button>
    <button id="btnEdita">Edita</button>
    <button id="btnEditaPesquisa">Edita Pesquisa</button>
  </div>

</body>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<%-- <script src="${context}/js/jfstm.jquery.js"></script> --%>
<script src="${context}/js/state-machine.min.js"></script>

<script type="text/javascript">

$(function(){


//   $('#btnLegal')
//   $('#btnBacana')
//   $('#btnMoido')
//   $('#btnUpa')

    var fsm = StateMachine.create({
        initial: 'editando',
        events: [
          { name: 'salva',  from: 'editando',  to: 'mostrando' },
          { name: 'salvaPesquisa', from: 'editando', to: 'mostrando'    },
          { name: 'edita',  from: 'mostrando',    to: 'editando' },
          { name: 'editaPesquisa', from: 'mostrando', to: 'editando'  }
      ]
    });
    
    fsm.oneditando = function(){
        $('#btnLegal').hide();
        $('#btnBacana').show();
        $('#btnMoido').show();
        $('#btnUpa').hide();
    };
    
    fsm.onmostrando = function(){
        $('#btnLegal').show();
        $('#btnBacana').hide();
        $('#btnMoido').hide();
        $('#btnUpa').show();
    };


    $("#btnSalva").click(function(){
        fsm.salva();
    });
    $("#btnSalvaPesquisa").click(function(){
        fsm.salvaPesquisa();
    });

    $("#btnEdita").click(function(){
        fsm.edita();
    });

    $("#btnEditaPesquisa").click(function(){
        fsm.editaPesquisa();
    });




//   $('#btnLegal')
//     .jfsm('step1',{visible: true})
//     .jfsm('step2',{visible: false})
//     .jfsm('step3',{visible: false});
//   $('#btnBacana')
//     .jfsm('step1',{visible: false})
//     .jfsm('step2',{visible: true})
//     .jfsm('step3',{visible: false});    
//   $('#btnMoido')
//     .jfsm('step1',{visible: false})
//     .jfsm('step2',{visible: false})
//     .jfsm('step3',{visible: true});
//   $('#btnUpa')
//     .jfsm('step1',{visible: false})
//     .jfsm('step2',{visible: true})
//     .jfsm('step3',{visible: true});

  
//   $('#btnStep1').click(function(){
//       $.jfsm('step1').done(function(){
// //           alert('feito!');
//       });
//   });
    
//   $('#btnStep2').click(function(){
//       $.jfsm('step2').done(function(){
// //           alert('feito 2!');
//       });
//   });

//   $('#btnStep3').click(function(){
//       $.jfsm('step3').done(function(){
// //           alert('feito 3!');
//       });
//   });






});






</script>

</html>