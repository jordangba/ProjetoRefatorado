package Bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Controller.ControllerGeral;

@ManagedBean(name = "cadastroCaronaBean", eager = true)
@SessionScoped
public class cadastroCaronaBean {

	private String origem;
	private String destino;
	private String data;
	private String hora;
	private String minutos;
	private String vagas;
	private String idUser;
	private ControllerGeral controller;

	public cadastroCaronaBean() {
		this.origem = "";
		this.destino = "";
		this.hora = "";
		this.minutos = "";
		this.vagas = "";

		this.idUser = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("usuarioLogado");

		this.controller = (ControllerGeral) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("controller");
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMinutos() {
		return minutos;
	}

	public void setMinutos(String minutos) {
		this.minutos = minutos;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}

	public String getIdUser() {
		return idUser;
	}

	public String cancela() {
		return "telainicial.xhtml";
	}

	public String cadastra() {
		try {
			controller.cadastrarCarona(idUser, origem, destino, data, hora,
					vagas);
			compartilhaInfo();
			msgUsuario("Carona Cadastrada", "");
			return "telaInicial.xhtml";
		} catch (IllegalArgumentException e) {
			msgUsuario("Carona nï¿œo cadastrada", e.getMessage());
		}
		return "";
	}

	private void msgUsuario(String string1, String string2) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(string1, string2));
	}

	public void limpa() {
		this.origem = "";
		this.destino = "";
		this.hora = "";
		this.minutos = "";
		this.vagas = "";
	}

	private void compartilhaInfo() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("usuarioLogado", idUser);

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("controller", controller);

		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("usuarioBean") != null) {
			usuarioBean bean = (usuarioBean) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("usuarioBean");
			bean.iniciaBean();
		}

	}

}
