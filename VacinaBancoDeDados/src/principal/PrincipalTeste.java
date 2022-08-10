/**
 * 
 */
package principal;

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
		
		VacinaRepositorio vacina = new VacinaRepositorio();
		
		if(vacina.excluir(1)) {
			System.out.println("Vacina 1 excluida");
		}

	}

}
