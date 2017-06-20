package br.com.caelum.mvc.logica;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.modelo.Contato;
import br.com.caelum.agenda.modelo.dao.ContatoDAO;

public class AdicionaContatoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// Capturar os parâmetros passados na requisição
		String nomeStr = req.getParameter("nome");
		String emailStr = req.getParameter("email");
		String enderecoStr = req.getParameter("endereco");
		String nascimentoStr = req.getParameter("nascimento");
		
		//Verifica se o parâmtro isStr é diferente de nulo e os demais são nulos
		if((nomeStr == null) || (emailStr == null) || (enderecoStr == null) || (nascimentoStr == null)){
		
			return "/WEB-INF/jsp/adiciona-contato.jsp";
		}
		else if(nomeStr.isEmpty() || nascimentoStr.isEmpty()){
			// seta atributos no request para serem retomados na página de adição de contatos
			req.setAttribute("nome", nomeStr);
			req.setAttribute("email", emailStr);
			req.setAttribute("endereco", enderecoStr);
			req.setAttribute("nascimento", nascimentoStr);
			
			return "/WEB-INF/jsp/adiciona-contato.jsp";
		}
		else{
			Date data = new SimpleDateFormat("dd/MM/yyyy").parse(nascimentoStr);
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(data);
			
			//Instanciando um objeto Contato e setando seus atributos
			Contato contato = new Contato();
			contato.setNome(nomeStr);
			contato.setEmail(emailStr);
			contato.setEndereco(enderecoStr);
			contato.setDataNascimento(dataNascimento);
			
			//Instanciando um Objeto ContatoDAO e executando o método de altera()
			ContatoDAO dao =  new ContatoDAO();
			dao.adiciona(contato);
		}
		
		return "mvc?logica=ListaContatosLogic";
	}

}
