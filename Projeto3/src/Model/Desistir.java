package Model;

import java.io.Serializable;

public class Desistir implements Estado, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Estado mudaEstadoAceitacao(Estado estado) {
		return null;
	}

	@Override
	public Estado mudaEstadoRejeicao(Estado estado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estado mudaEstadoDesistir(Estado estado) {
		return null;
	}

	public String toString() {
		return "Desistiu";
	}

}
