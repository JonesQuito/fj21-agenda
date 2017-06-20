package br.com.caelum.mvc.logica;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.modelo.Contato;
import br.com.caelum.agenda.modelo.dao.ContatoDAO;

public class AlteraAdicionaContatoLogic implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//Captura os par�metros passados na requisi��o
			String idStr = req.getParameter("id");
			String nomeStr = req.getParameter("nome");
			String emailStr = req.getParameter("email");
			String enderecoStr = req.getParameter("endereco");
			String nascimentoStr = req.getParameter("nascimento");
			Calendar dataNascimento;
			
			//Verifica se o par�mtro idStr � nulo.
			if(idStr == null){
				//executa a l�gica de adicionar contato
				Date data = new SimpleDateFormat("dd/MM/yyyy").parse(nascimentoStr);
				dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(data);
				
				//Instancia e seta os atributos de um Contato
				Contato contato = new Contato();
				contato.setNome(nomeStr);
				contato.setEmail(emailStr);
				contato.setEndereco(enderecoStr);
				contato.setDataNascimento(dataNascimento);
				
				//Instancia o DAO e invoca o m�todo de adicionar um contato
				ContatoDAO dao = new ContatoDAO();
				dao.adiciona(contato);
				
				return "mvc?logica=ListaContatosLogic";
				
				//.....
				//.....
			}else if(idStr.isEmpty() || nomeStr.isEmpty() || nascimentoStr.isEmpty()){
					req.setAttribute("erro", "N�o foi poss�vel atualizar o Contato.<br/>O formul�rio n�o foi preenchido de forma adequada");
					req.setAttribute("url", "mvc?logica=ListaContatosLogic");
					return "erroPage.jsp";
				
					
			}else{
				long id = Long.parseLong(idStr);
				Date data = new SimpleDateFormat("dd/MM/yyyy").parse(nascimentoStr);
				dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(data);
				
				//Instanciando um objeto Contato e setando seus atributos
				Contato contato = new Contato();
				contato.setId(id);
				contato.setNome(nomeStr);
				contato.setEmail(emailStr);
				contato.setEndereco(enderecoStr);
				contato.setDataNascimento(dataNascimento);
				
				//Instanciando um Objeto ContatoDAO e executando o m�todo de altera()
				ContatoDAO dao =  new ContatoDAO();
				dao.altera(contato);
			}
		
		return "mvc?logica=ListaContatosLogic";
	}

}
