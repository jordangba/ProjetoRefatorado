package Model;

import java.io.Serializable;

public class PontoEncontro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ponto;
	private String idponto;
	private String idUser;
	private String idCarona;

	public PontoEncontro(String pontos, String idUser, String idCarona) {
		setPonto(pontos);
		this.idCarona = idCarona;
		this.idUser = idUser;
		setIdponto(Integer.toString(hashCode()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ponto == null) ? 0 : ponto.hashCode());
		return result;
	}

	public void setIdponto(String idponto) {
		this.idponto = idponto;
	}

	private void verificaPonto(String ponto) {
		if (ponto.isEmpty()) {
			throw new IllegalArgumentException("Ponto Inválido");
		}
	}

	public String getPonto() {
		return ponto;
	}

	public void setPonto(String ponto) {
		verificaPonto(ponto);
		this.ponto = ponto;
	}

	public String getIdponto() {
		return idponto;
	}

	public String getIdUser() {
		return idUser;
	}

	public String getIdCarona() {
		return idCarona;
	}

	public String toString() {
		return this.ponto;
	}
}
