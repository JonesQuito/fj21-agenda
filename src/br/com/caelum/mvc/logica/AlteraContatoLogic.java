package br.com.caelum.mvc.logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.modelo.Contato;
import br.com.caelum.agenda.modelo.dao.ContatoDAO;

public class AlteraContatoLogic implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		/* Captura os par�metros passados na requisi��o
		 * Dependendo de onde partiu a requisi��o, os par�metros
		 * podem existir ou n�o
		 * */
		String idStr = req.getParameter("id");
		String nomeStr = req.getParameter("nome");
		String emailStr = req.getParameter("email");
		String enderecoStr = req.getParameter("endereco");
		String nascimentoStr = req.getParameter("nascimento");
		
		/* Verifica se o par�mtro isStr � diferente de nulo e os demais s�o nulos
		 * se isso ocorrer, implica dizer que a requisi��o partiu da listagem de contatos
		 * e deve-se, portanto, buscar esse objeto no banco de dados para lan��-lo ao
		 * formul�rio de edi��o de contatos. 
		 * */
		if((idStr != null) && (nomeStr == null) && (emailStr == null) && (enderecoStr == null) && (nascimentoStr == null)){
			long idContato = Long.parseLong(req.getParameter("id"));
			Contato c = new Contato();
			c = new ContatoDAO().pesquisarContato(idContato);
			req.setAttribute("contato", c);
			return "/WEB-INF/jsp/edita-contato.jsp";
		}
		
		/* Caso a requisi��o tenha sido originada na p�gina de edi��o de contatos (<form/>)
		 * todos os par�metros devem te sido passados. Neste caso resta verificar se os par�metros
		 * foram preenchidos ou se est�o vazios. Caso as campos obrigat�rios estejam vazios,
		 * ser� redirecionado para uma p�gina de erro.
		 */
		else if(idStr.isEmpty() || nomeStr.isEmpty() || nascimentoStr.isEmpty()){
			req.setAttribute("erro", "N�o foi poss�vel atualizar o Contato.<br/>O formul�rio n�o foi preenchido de forma adequada");
			req.setAttribute("url", "mvc?logica=ListaContatosLogic");
			req.setAttribute("LinkMessage", "Listar Contatos");
			return "erroPage.jsp";
		}
		/* Se todos os atributos obrigat�rios tiverem sido passados na requisi��o, um objeto Copntato
		 * ser� instanciado e ter� seus atributos setados com os valores passados na requisi��o.
		 * No momento seguinte, um objeto ContatoDAO ser� criado e seu m�todo altera() ser� invocado
		 * recebendo como par�metro o objeto instanciado.
		 */
		else{
			try{
				long id = Long.parseLong(idStr);
				Date data = new SimpleDateFormat("dd/MM/yyyy").parse(nascimentoStr);
				Calendar dataNascimento = Calendar.getInstance();
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
				
			}catch(ParseException pe){
				req.setAttribute("erro", "N�o foi poss�vel atualizar o Contato.<br/>O formato de data n�o � reconhecido "+pe.getMessage());
				req.setAttribute("url", "mvc?logica=ListaContatosLogic");
				req.setAttribute("LinkMessage", "Listar Contatos");
				return "erroPage.jsp";
			}	
		}
		
		return "mvc?logica=ListaContatosLogic";
	}

}
