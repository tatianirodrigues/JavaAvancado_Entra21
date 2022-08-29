/**
 * 
 */
package principal;

import java.util.Date;

import controller.VacinaController;
import exception.CampoInvalidoException;
import exception.VacinaSemResponsavelException;
import model.entidade.Pessoa;
import model.entidade.Vacina;
import model.repositorio.PessoaRepositorio;
import model.repositorio.VacinaRepositorio;

/**
 * @author tati
 *
 */
public class PrincipalTeste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		VacinaRepositorio vacinaRep = new VacinaRepositorio();

		PessoaRepositorio pessoaRep = new PessoaRepositorio();

		System.out.println(pessoaRep.consultarTodos());


		Pessoa marcos = pessoaRep.consultarPorId(1);

		Vacina vacinaNova = new Vacina();

		vacinaNova.setDataInicioPesquisa(new Date());
		vacinaNova.setEstagioPesquisa(3);
		vacinaNova.setPesquisadorResponsavel(marcos);

		VacinaController vacinaController = new VacinaController();
		
		try {
			vacinaController.salvar(vacinaNova);
		} catch (VacinaSemResponsavelException | CampoInvalidoException e) {
			System.err.println(e.getMessage());
		}
		
		if(vacinaNova.getId() > 0) {
			System.out.println("Vacina salva");
		}
		
		


		//		
		////		if(vacina.excluir(2)) {
		////			System.out.println("Vacina excluida");
		////		}
		//		
		//		Pessoa pesquisador = new Pessoa ();
		//		pesquisador.setMatricula(3333);
		//		pesquisador.setDataNascimento(new Date());
		//		pesquisador.setNome("Ana");
		//		pesquisador.setCpf("589-2");
		//		
		//		pessoaRep.inserir(pesquisador);
		//		//System.out.println(pessoaRep.inserir(pesquisador));
		//		
		//		
		//		
		//		
		//		
		//		
		//		
		//		System.out.println(vacinaRep.inserir(vacinaNova));
		//		
		//	
		//		//vacina.excluir(3);
		//	
		//		
		//		vacinaNova.setEstagioPesquisa(10);
		//		
		//		vacina.atualizar(vacinaNova);
		//		
		//		
		//		System.out.println(vacina.pesquisarTodas());

	}

}
