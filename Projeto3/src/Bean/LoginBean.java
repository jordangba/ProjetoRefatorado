package Bean;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Controller.ControllerGeral;
import Model.Usuario;

@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class LoginBean {

	private String login;
	private String password;
	private String nome;
	private String loginCadastro;
	private String senhaCadastro;
	private String endereco;
	private String email;
	private ControllerGeral controller;

	public LoginBean() {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get("controller") != null) {
			controller = (ControllerGeral) FacesContext.getCurrentInstance()
					.getExternalContext().getRequestMap().get("controller");
		} else {
			this.controller = new ControllerGeral();
		}
		this.login = "";
		this.password = "";
		this.nome = "";
		this.loginCadastro = "";
		this.senhaCadastro = "";
		this.endereco = "";
		this.email = "";
	}

	public String logar() {
		try {
			usuarioLogar(login, password);
			limpa();
			return "telaInicial.xhtml";
		} catch (Exception e) {
			msgUsuario("Login N�o realizado", e.getMessage());
			return "";
		}

	}

	private void usuarioLogar(String login, String password) {
		FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getRequestMap()
				.put("usuarioLogado",
						controller.buscaUsuarioPorId(this.controller
								.abrirSessao(login, password)));

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put("controller", controller);

	}

	public String cadastra() {
		try {
			controller.criarUsuario(this.loginCadastro, this.senhaCadastro,
					this.nome, this.endereco, this.email);
			usuarioLogar(loginCadastro, senhaCadastro);
			limpa();
			return "telaInicial.xhtml";
		} catch (IllegalArgumentException e) {
			msgUsuario("Usuario invalido", e.getMessage());
			return "";
		} catch (Exception e) {
			msgUsuario("Cadastro invalido", e.getMessage());
			return "";
		}

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLoginCadastro() {
		return loginCadastro;
	}

	public void setLoginCadastro(String loginCadastro) {
		this.loginCadastro = loginCadastro;
	}

	public String getSenhaCadastro() {
		return senhaCadastro;
	}

	public void setSenhaCadastro(String senhaCadastro) {
		this.senhaCadastro = senhaCadastro;
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

	private void msgUsuario(String string1, String string2) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(string1, string2));
	}

	public void limpa() {
		this.login = "";
		this.password = "";
		this.nome = "";
		this.loginCadastro = "";
		this.senhaCadastro = "";
		this.endereco = "";
		this.email = "";
	}
}
