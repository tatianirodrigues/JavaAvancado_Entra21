package controller;

import exception.CampoInvalidoException;
import exception.VacinaSemResponsavelException;
import model.entidade.Vacina;
import model.repositorio.VacinaRepositorio;

public class VacinaController {

		private VacinaRepositorio repositorio = new VacinaRepositorio();
		
		
		public Vacina salvar(Vacina novaVacina) throws VacinaSemResponsavelException, CampoInvalidoException {
			
			if (novaVacina.getPesquisadorResponsavel() == null) {
				
				throw new VacinaSemResponsavelException("Informe o pesquisador responsavel");
		
			}
			
			if (novaVacina.getPaisOrigem() == null || novaVacina.getPaisOrigem().trim().isEmpty()) {
				throw new CampoInvalidoException("Informe o pais de origem");
			}
			
			if (novaVacina.getEstagioPesquisa() <= 0) {
				throw new CampoInvalidoException("Informe o estagio da pesquisa");
			}
			
			
			novaVacina = repositorio.inserir(novaVacina);
			
			return novaVacina;
			
		}
}
