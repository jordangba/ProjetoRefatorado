package Bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Controller.ControllerGeral;
import Model.Usuario;

@ManagedBean(name = "editarPerfilBean", eager = true)
@SessionScoped
public class editarPerfilBean {

	private Usuario user;
	private String confirmacaoSenha;
	private String nome;
	private String senha;
	private String endereco;
	private String email;
	private String idUser;
	private ControllerGeral controller;

	public editarPerfilBean() {
		this.controller = (ControllerGeral) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("controller");
		this.idUser = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap().get("usuarioLogado");

		user = controller.buscaUsuarioPorId(idUser);

		this.nome = user.getNome();
		this.endereco = user.getEndereco();
		this.email = user.getEmail();
		this.senha = user.getSenha();
		this.confirmacaoSenha = "";
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String password) {
		this.confirmacaoSenha = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String salvar() {
		try {
			controller.editaPerfil(idUser, nome, endereco, confirmacaoSenha,
					email);
			compartilhaInfo();
			return "telaInicial.xhtml";
		} catch (IllegalArgumentException e) {
			msgUsuario("Usuario invalido", e.getMessage());
			return "";
		} catch (Exception e) {
			msgUsuario("Cadastro invalido", e.getMessage());
			return "";
		}
	}

	public String cancelar() {
		return "telaInicial.xhtml";
	}

	private void msgUsuario(String string1, String string2) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(string1, string2));
	}

	private void compartilhaInfo() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("usuarioLogado", idUser);

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("controller", controller);

		usuarioBean bean = (usuarioBean) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioBean");
		bean.iniciaBean();
	}

}
