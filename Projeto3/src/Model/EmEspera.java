package Model;

import java.io.Serializable;

public class EmEspera implements Estado, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Esperando Resposta";
	}

	@Override
	public Estado mudaEstadoAceitacao(Estado estado) {
		return estado = new Aceito();

	}

	@Override
	public Estado mudaEstadoRejeicao(Estado estado) {
		return estado = new Rejeitado();

	}

	@Override
	public Estado mudaEstadoDesistir(Estado estado) {
		return estado = new Desistir();
	}

}
