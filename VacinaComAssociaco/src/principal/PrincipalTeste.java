/**
 * 
 */
package principal;

import java.util.Date;

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
		
//		if(vacina.excluir(2)) {
//			System.out.println("Vacina excluida");
//		}
		
		Pessoa pesquisador = new Pessoa ();
		pesquisador.setMatricula(3333);
		pesquisador.setDataNascimento(new Date());
		pesquisador.setNome("Ana");
		pesquisador.setCpf("589-2");
		
		pessoaRep.inserir(pesquisador);
		//System.out.println(pessoaRep.inserir(pesquisador));
		
		
		Vacina vacinaNova = new Vacina();
		
		vacinaNova.setDataInicioPesquisa(new Date());
		vacinaNova.setEstagioPesquisa(3);
		vacinaNova.setPaisOrigem("turquia");
		vacinaNova.setPesquisadorResponsavel(pesquisador);
		
		
		
		
		System.out.println(vacinaRep.inserir(vacinaNova));
		
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
