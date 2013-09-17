package Model;

import java.io.Serializable;

public class Rejeitado implements Estado, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Rejeitado";
	}

	@Override
	public Estado mudaEstadoAceitacao(Estado estado) {
		return null;

	}

	@Override
	public Estado mudaEstadoRejeicao(Estado estado) {
		return null;

	}

	@Override
	public Estado mudaEstadoDesistir(Estado estado) {
		return estado = new Desistir();
	}
}
