package model.entidade;

import java.util.Date;

/**
 * @author guest01
 *
 */
	public class Vacina {

		//Atributos
		private int id;
		private String paisOrigem;
		private int estagioPesquisa;
		private Date dataInicioPesquisa;
		private Pessoa pesquisadorResponsavel;
		
		
		//Métodos
		
		//Getters e setters
		public String getPaisOrigem() {
			return paisOrigem;
		}
		public void setPaisOrigem(String paisOrigem) {
			this.paisOrigem = paisOrigem;
		}
		public int getEstagioPesquisa() {
			return estagioPesquisa;
		}
		public void setEstagioPesquisa(int estagioPesquisa) {
			this.estagioPesquisa = estagioPesquisa;
		}
		public Date getDataInicioPesquisa() {
			return dataInicioPesquisa;
		}
		public void setDataInicioPesquisa(Date dataInicioPesquisa) {
			this.dataInicioPesquisa = dataInicioPesquisa;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		//ToString
		
		public Pessoa getPesquisadorResponsavel() {
			return pesquisadorResponsavel;
		}
		@Override
		public String toString() {
			return "Vacina [id=" + id + ", paisOrigem=" + paisOrigem + ", estagioPesquisa=" + estagioPesquisa
					+ ", dataInicioPesquisa=" + dataInicioPesquisa + ", pesquisadorResponsavel="
					+ pesquisadorResponsavel + "]";
		}
		public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
			this.pesquisadorResponsavel = pesquisadorResponsavel;
		}
		
		
	
}