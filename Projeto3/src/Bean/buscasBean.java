package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import Controller.ControllerGeral;
import Model.Carona;

@ManagedBean(name = "buscasBean")
@SessionScoped
public class buscasBean {

	private String idUser;
	private String origem;
	private String destino;
	private String sugestaoLocal;
	private List<Carona> caronas;
	private ControllerGeral controller;
	private Carona caronaSelecionada;

	public buscasBean() {
		iniciaBean();
	}

	public void iniciaBean() {
		this.idUser = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("usuarioLogado");

		this.controller = (ControllerGeral) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("controller");

		this.origem = "";
		this.destino = "";
		this.sugestaoLocal = "";
		this.caronas = new ArrayList<Carona>();
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

	public String getSugestaoLocal() {
		return sugestaoLocal;
	}

	public void setSugestaoLocal(String sugestaoLocal) {
		this.sugestaoLocal = sugestaoLocal;
	}

	public List<Carona> getCaronas() {
		return caronas;
	}

	public void setCaronas(List<Carona> caronas) {
		this.caronas = caronas;
	}

	public Carona getCaronaSelecionada() {
		return caronaSelecionada;
	}

	public void setCaronaSelecionada(Carona caronaSeelecionada) {
		this.caronaSelecionada = caronaSeelecionada;
	}

	private void mandaInfo() {
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

	public void buscar() {
		List<String> idCaronas = controller.buscaCarona(origem, destino);
		for (String id : idCaronas) {
			caronas.add(controller.pegaCarona(id));
		}
	}

	public void addSolicitacao() {
		if (this.sugestaoLocal.isEmpty()) {
			controller.solicitarVaga(idUser, caronaSelecionada.getIdCarona());
		} else {
			controller.solicitarVagaPontoEncontro(idUser,
					caronaSelecionada.getIdCarona(), this.sugestaoLocal);
		}
		msgUsuario("Carona solicitada", "");
	}

	public String voltar() {
		mandaInfo();
		return "telaInicial.xhtml";
	}
	
	private void msgUsuario(String string1, String string2) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(string1, string2));
	}
}
