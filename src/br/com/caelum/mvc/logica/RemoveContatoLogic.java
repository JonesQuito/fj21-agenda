package br.com.caelum.mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.modelo.Contato;
import br.com.caelum.agenda.modelo.dao.ContatoDAO;

public class RemoveContatoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//captura o id do contatos a ser excluído
		long id = Long.parseLong(req.getParameter("id"));
		
		//instanciar um objeto da classe Contato e setar o id
		Contato contato = new Contato();
		contato.setId(id);
		
		//instanciar um dao e executar o método de excluir
		ContatoDAO dao = new ContatoDAO();
		dao.excluir(contato);
		
		//imprimir mensagem no console do servidor
		System.out.println("Excluindo contato... ");
		
		//retorna o nome da pagina de listagem de contatos
		return "mvc?logica=ListaContatosLogic";
	}

}
