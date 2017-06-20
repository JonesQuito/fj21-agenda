<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="caelum" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="width: 50%; margin: auto; padding: 10px; box-shadow: 0px 0px 3px rgba(0,0,0,.5);">
		<c:import url="/cabecalho.jsp"></c:import>
		<center><h3>Edita Contatos</h3></center>
		<hr />
		
		<form method="post" action="mvc?logica=AlteraContatoLogic&id=${contato.id}">	
			<table style="margin: auto;">
				<tr><td>Nome:</td><td><input type="text" name="nome" value="${contato.nome}" size="40"/></td></tr>
				<tr><td>E-mail:</td><td><input type="text" name="email" value="${contato.email}" size="40"/></td></tr>
				<tr><td>Endereço:</td><td><input type="text" name="endereco" value="${contato.endereco}" size="40"/></td></tr>
				<tr><td>Data Nascimento:</td><td><input id="nascimento" type="text" name="nascimento" value='<fmt:formatDate value="${contato.dataNascimento.time}" pattern="dd/MM/yyyy"/>' /></td></tr>
				<tr><td><input type="submit" value="Gravar" /></td> <td><a href="mvc?logica=ListaContatosLogic">Lista de Contatos</a></td></tr>
			</table>
		</form>
		<c:import url="/rodape.jsp"></c:import>
	</div>
</body>
</html>