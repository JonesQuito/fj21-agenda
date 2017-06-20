<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1">
<title>Adiciona Contatos</title>

</head>
<body>
	<div style="width: 50%; margin: auto; padding: 10px; box-shadow: 0px 0px 3px rgba(0,0,0,.5);">
		<c:import url="/cabecalho.jsp" />
		
		<h3>Adiciona Contato</h3>
		<hr />
		<form action="mvc?logica=AdicionaContatoLogic" method="post"> 
			<table style="margin: auto;">
				<tr> <td>Nome:</td> <td><input type="text" name="nome" value="${nome}" placeholder="Nome" size="30"/></td></tr>
				<tr> <td>E-mail:</td> <td><input type="text" name="email" value="${email}" placeholder="E-mail" size="30"/></td></tr>
				<tr> <td>Endereço:</td> <td><input type="text" name="endereco" value="${endereco}" placeholder="Endereço" size="30"/></td></tr>
				<tr> <td>Nascimento:</td> <td><input type="text" required="required" value="${nascimento}" placeholder="dd/mm/aaaa" name="nascimento" pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$" /></td></tr>
				<tr> <td><input type="submit" value="Gravar" /></td> <td><a href="mvc?logica=ListaContatosLogic">Lista de Contatos</a></td></tr>
			</table>
				
		</form>
		
		<c:import url="/rodape.jsp"></c:import>
	</div>
</body>
</html>