<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />



 <!-- Fixed navbar -->
 
        <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${context}">Radio</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="${context}/views/" id="menu-exem" class="desativado">Exemplo</a></li>
              <li><a href="#" id="menu-1" class="desativado">Menu 1</a></li>
              
              <li class="dropdown" id="menu-2">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" id="link-menu-2">Menu 2<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#"     id="menu-sub1">sub-menu 1</a></li>
                  <li><a href="#"     id="menu-sub2">sub-menu 2</a></li>
                </ul>
              </li>
              
              <li><a href="#" id="menu-3">Menu 3</a></li>
              
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </nav>
