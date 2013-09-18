package Bean;

import java.io.IOException;
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
	private List<Solicitacao> solicitadas;
	private List<Solicitacao> aceitas;
	private Solicitacao solicitacaoSelecionada;
	private Solicitacao solicitadaSelecionada;
	
	public usuarioBean() {
		iniciaBean();
	}

	public List<Solicitacao> getSolicitadas() {
		return solicitadas;
	}

	public void setSolicitadas(List<Solicitacao> solicitadas) {
		this.solicitadas = solicitadas;
	}

	public List<Solicitacao> getAceitas() {
		return aceitas;
	}

	public void setAceitas(List<Solicitacao> aceitas) {
		this.aceitas = aceitas;
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

	public Solicitacao getSolicitacaoSelecionada() {
		return solicitacaoSelecionada;
	}

	public void setSolicitacaoSelecionada(Solicitacao solicitacaoSelecionada) {
		this.solicitacaoSelecionada = solicitacaoSelecionada;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("usuarioLogado", null);

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("controller", controller);
		return "Login.xhtml";
	}

	public Solicitacao getSolicitadaSelecionada() {
		return solicitadaSelecionada;
	}

	public void setSolicitadaSelecionada(Solicitacao solicitadaSelecionada) {
		this.solicitadaSelecionada = solicitadaSelecionada;
	}

	public String buscasCarona() {
		mandaInfo();
		return "buscaCarona.xhtml";
	}

	public String novaCarona() {
		mandaInfo();
		return "cadastroCarona.xhtml";
	}

	public String editaPerfil() {
		mandaInfo();
		return "editaPerfil.xhtml";

	}

	public void iniciaBean() {
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
		this.solicitacoes = controller.montaListaDeSolicitacaoRecebidas(idUser);
		this.solicitadas = controller.montaListaDeSolicitacaoFeitas(idUser);
		this.aceitas = controller.montaListaDeSolicitacaoAceitas(idUser);
	}

	private void mandaInfo() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("usuarioLogado", idUser);

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("controller", controller);

		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("buscasBean") != null) {
			buscasBean bean = (buscasBean) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("buscasBean");
			bean.iniciaBean();
		}
		
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("cadastroCaronaBean") != null) {
			cadastroCaronaBean bean = (cadastroCaronaBean) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("cadastroCaronaBean");
			bean.iniciaBean();
		}
	}

	public void aceitar() throws IOException {
		controller.aceitarSolicitacao(this.idUser,
				this.solicitacaoSelecionada.getIdSolicitacao());
		this.aceitas = controller.montaListaDeSolicitacaoAceitas(idUser);
		this.solicitacoes = controller.montaListaDeSolicitacaoRecebidas(idUser);
	}

	public void recusar() throws IOException {
		controller.rejeitarSolicitacao(this.idUser,
				this.solicitacaoSelecionada.getIdSolicitacao());
		this.solicitacoes = controller.montaListaDeSolicitacaoRecebidas(idUser);
	}
	
	public void desistir() throws IOException{
		controller.desistirRequisicao(idUser, solicitadaSelecionada.getCarona().getIdCarona(), solicitadaSelecionada.getIdSolicitacao());
		this.solicitadas = controller.montaListaDeSolicitacaoFeitas(idUser);
	}
}
