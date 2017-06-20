<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.text.SimpleDateFormat"%>
<%@ page
	import="java.util.*,
    	 br.com.caelum.agenda.modelo.dao.*,
    	br.com.caelum.agenda.modelo.Contato"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Contatos</title>

<style>
td {
	padding: 5px;
}

th {
	text-align: left;
	padding: 5px;
}
</style>

</head>
<body>
	<table>
		<tr>
			<th>Nome</th>
			<th>Email</th>
			<th>Endereço</th>
			<th>Nascimento</th>
		</tr>
		<%
			ContatoDAO dao = new ContatoDAO();
			List<Contato> contatos = dao.getLista();
			String cor;
			int index = 0;
			for (Contato contato : contatos) {
				cor = (index % 2 == 0 ? "aaee88" : "ffffff");
				index++;
		%>
		<tr bgcolor=<%=cor%>>
			<td><%=contato.getNome()%></td>
			<td><%=contato.getEmail()%></td>
			<td><%=contato.getEndereco()%></td>
			<td><%=new SimpleDateFormat("dd/MM/yyyy").format(contato.getDataNascimento().getTime())%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>