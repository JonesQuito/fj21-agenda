<%@page import="br.com.caelum.agenda.modelo.dao.ContatoDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!-- IMPORTANDO TAGLIBS DA JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- IMPORTANDO TAGLIBS DA JSTL -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar contatos com JSTL</title>

<link 	rel="stylesheet" 
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
		integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" 
		crossorigin="anonymous">
</head>
<body>
	<c:import url="cabecalho.jsp" />

	<center>
		<h1>Relação de contatos</h1>
		<hr />
	     <table  class="table table-houve" style="width: 80%">
	         <tr>
	             <th>Código</th>
	             <th>Nome</th>
	             <th>Email</th>
	             <th>Endereço</th>
	             <th>Nascimento</th>
	             <th colspan="2">Ações</th>
	         </tr>
	         <c:forEach var="contato" items="${contatos}" varStatus="contador">
	         <tr bgcolor="#${contador.count  % 2 == 0? 'aaee88' : 'ffffff' }">
	             <td>${contador.count}</td>
	             <td>${contato.nome}</td>
	             <c:if test="${not empty contato.email}">
	             	<td><a href="mailto:${contato.email}">${contato.email}</a></td>
	             </c:if>
	             <c:if test="${empty contato.email}">
	             <td>E-mail não informado</td>
	             </c:if>
	             <td>${contato.endereco}</td>
	             <td><fmt:formatDate value="${contato.dataNascimento.time}" pattern="dd/MM/yyyy" /></td>
	             <td><a href="disciplina_editar.jsp"><span class="glyphicon glyphicon-pencil"></span> Editar</a></td> 
	             <td><a href="mvc?logica=RemoveContatoLogic&id=${contato.id}" ><span class="glyphicon glyphicon-trash"></span>Exluir</a></td>
	         </tr>
	         </c:forEach>
	         
	     </table>
	</center>
	
	<c:import url="rodape.jsp"></c:import>

</body>
</html>