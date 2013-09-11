package Bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Controller.ControllerGeral;
import Model.Solicitacao;
import Model.Usuario;
import Model.Carona;

@ManagedBean(name = "usuarioBean")
@SessionScoped
public class usuarioBean {

	private String idUser;
	private String nome;
	private String endereco;
	private String login;
	private String email;
	private int quantCaronas;
	private List<Carona> caronas;
	private ControllerGeral controller;
	private List<Solicitacao> solicitacoes;

	public usuarioBean() {
		iniciaBean();
	}
	
	
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}



	public ControllerGeral getController() {
		return controller;
	}



	public void setController(ControllerGeral controller) {
		this.controller = controller;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getQuantCaronas() {
		return quantCaronas;
	}

	public void setQuantCaronas(int quantCaronas) {
		this.quantCaronas = quantCaronas;
	}

	public List<Carona> getCaronas() {
		return caronas;
	}

	public void setCaronas(List<Carona> caronas) {
		this.caronas = caronas;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("usuarioLogado", null);

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("controller", controller);
		return "Login.xhtml";
	}

	public String buscasCarona() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("usuarioLogado", idUser);

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("controller", controller);

		return "buscaCarona.xhtml";
	}

	public String novaCarona() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("usuarioLogado", idUser);

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("controller", controller);

		return "cadastroCarona.xhtml";
	}
	
	public void iniciaBean(){
		this.idUser = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("usuarioLogado");

		this.controller = (ControllerGeral) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("controller");
		
		Usuario user = controller.buscaUsuarioPorId(idUser);
		this.nome = user.getNome();
		this.endereco = user.getEndereco();
		this.login = user.getLogin();
		this.email = user.getEmail();
		this.quantCaronas = user.getCarona().size();
		this.caronas = controller.montaListaDeCarona(user.getIdUser());
		this.solicitacoes = controller.montaListaDeSolicitacaoRecebidas(user
				.getIdUser());
	}
}
