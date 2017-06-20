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
		/* Captura os parâmetros passados na requisição
		 * Dependendo de onde partiu a requisição, os parâmetros
		 * podem existir ou não
		 * */
		String idStr = req.getParameter("id");
		String nomeStr = req.getParameter("nome");
		String emailStr = req.getParameter("email");
		String enderecoStr = req.getParameter("endereco");
		String nascimentoStr = req.getParameter("nascimento");
		
		/* Verifica se o parâmtro isStr é diferente de nulo e os demais são nulos
		 * se isso ocorrer, implica dizer que a requisição partiu da listagem de contatos
		 * e deve-se, portanto, buscar esse objeto no banco de dados para lançá-lo ao
		 * formulário de edição de contatos. 
		 * */
		if((idStr != null) && (nomeStr == null) && (emailStr == null) && (enderecoStr == null) && (nascimentoStr == null)){
			long idContato = Long.parseLong(req.getParameter("id"));
			Contato c = new Contato();
			c = new ContatoDAO().pesquisarContato(idContato);
			req.setAttribute("contato", c);
			return "/WEB-INF/jsp/edita-contato.jsp";
		}
		
		/* Caso a requisição tenha sido originada na página de edição de contatos (<form/>)
		 * todos os parâmetros devem te sido passados. Neste caso resta verificar se os parâmetros
		 * foram preenchidos ou se estão vazios. Caso as campos obrigatórios estejam vazios,
		 * será redirecionado para uma página de erro.
		 */
		else if(idStr.isEmpty() || nomeStr.isEmpty() || nascimentoStr.isEmpty()){
			req.setAttribute("erro", "Não foi possível atualizar o Contato.<br/>O formulário não foi preenchido de forma adequada");
			req.setAttribute("url", "mvc?logica=ListaContatosLogic");
			req.setAttribute("LinkMessage", "Listar Contatos");
			return "erroPage.jsp";
		}
		/* Se todos os atributos obrigatórios tiverem sido passados na requisição, um objeto Copntato
		 * será instanciado e terá seus atributos setados com os valores passados na requisição.
		 * No momento seguinte, um objeto ContatoDAO será criado e seu método altera() será invocado
		 * recebendo como parâmetro o objeto instanciado.
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
				
				//Instanciando um Objeto ContatoDAO e executando o método de altera()
				ContatoDAO dao =  new ContatoDAO();
				dao.altera(contato);
				
			}catch(ParseException pe){
				req.setAttribute("erro", "Não foi possível atualizar o Contato.<br/>O formato de data não é reconhecido "+pe.getMessage());
				req.setAttribute("url", "mvc?logica=ListaContatosLogic");
				req.setAttribute("LinkMessage", "Listar Contatos");
				return "erroPage.jsp";
			}	
		}
		
		return "mvc?logica=ListaContatosLogic";
	}

}
