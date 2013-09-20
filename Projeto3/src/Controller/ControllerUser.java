package Controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.naming.directory.InvalidAttributeIdentifierException;

import Model.Usuario;

public class ControllerUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Usuario> usuarioSistema;
	private Usuario logado;

	public ControllerUser() {
		this.usuarioSistema = new HashMap<String, Usuario>();
		this.logado = new Usuario();
	}

	public void addUsuarioAoSistema(String login, String senha, String nome,
			String endereco, String email)
			throws InvalidAttributeIdentifierException {
		verificaSeLoginExiste(login);
		verificaSEmailExiste(email);
		Usuario user = new Usuario(login, senha, nome, endereco, email);
		this.usuarioSistema.put(user.getLogin(), user);
	}

	public String LogarUser(String senha, String login) {
		verificaLoginESenha(login, senha);
		logado = usuarioBuscaPeloID(usuarioSistema.get(login).getIdUser());
		return usuarioSistema.get(login).getIdUser();
	}

	private void verificaLoginESenha(String login, String senha) {
		verificaLogin(login);
		if (usuarioSistema.get(login).getSenha().equals(senha) == false) {
			throw new IllegalAccessError("Login inválido");
		}
	}

	private void verificaLogin(String login) throws IllegalAccessError {
		if (login == null || login.isEmpty()) {
			throw new IllegalAccessError("Login inválido");
		}
		if (!(usuarioSistema.containsKey(login))) {
			throw new IllegalAccessError("Usuário inexistente");
		}
	}

	private void verificaSeLoginExiste(String login)
			throws InvalidAttributeIdentifierException {
		if (usuarioSistema.containsKey(login)) {
			throw new InvalidAttributeIdentifierException(
					"Já existe um usuário com este login");
		}
	}

	public Usuario usuarioBuscaPeloID(String idUser) {
		for (Usuario user : usuarioSistema.values()) {
			if (user.getIdUser().equals(idUser)) {
				return user;
			}
		}
		return null;
	}

	private void verificaSEmailExiste(String email)
			throws InvalidAttributeIdentifierException {
		for (Usuario user : usuarioSistema.values()) {
			if (user.getEmail().equals(email)) {
				throw new InvalidAttributeIdentifierException(
						"Já existe um usuário com este email");
			}

		}
	}

	public Usuario getLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

	public Map<String, Usuario> getUsuarioSistema() {
		return usuarioSistema;
	}

	public String getAtributo(String login, String atributo) {
		String retorno = "";
		verificaLogin(login);
		if (atributo == null || atributo.isEmpty()) {
			throw new IllegalArgumentException("Atributo inválido");
		} else if (atributo.equals("nome")) {
			retorno = this.usuarioSistema.get(login).getNome();
		} else if (atributo.equals("endereco")) {
			retorno = this.usuarioSistema.get(login).getEndereco();
		} else {
			throw new IllegalArgumentException("Atributo inexistente");
		}
		return retorno;

	}

	public void encerrarSessao() {

		this.logado = new Usuario();

	}

	public void reviewCarona(String idSessao, String idCarona, String review,
			String login) {
		this.usuarioSistema.get(idSessao).reviewCarona(idCarona, review,
				this.usuarioSistema.get(login));
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String review, String login) {
		this.usuarioSistema.get(idSessao).reviewVagaEmCarona(idCarona, review,
				this.usuarioSistema.get(login));
	}

	public String visualizarPerfil(String idSessao, String login) {
		return this.usuarioSistema.get(idSessao).visualizarPerfil(login);
	}

	public String getAtributoPerfil(String login, String atributo) {
		String retorno = "";
		if (atributo.equals("nome")) {
			retorno = this.usuarioSistema.get(login).getNome();
		} else if (atributo.equals("endereco")) {
			retorno = this.usuarioSistema.get(login).getEndereco();
		} else if (atributo.equals("email")) {
			retorno = this.usuarioSistema.get(login).getEmail();
		} else if (atributo.equals("caronas seguras e tranquilas")) {
			retorno = Integer.toString(this.usuarioSistema.get(login)
					.getCaronaSeguras());
		} else if (atributo.equals("caronas que não funcionaram")) {
			retorno = Integer.toString(this.usuarioSistema.get(login)
					.getCaronasNaoFuncionou());
		} else if (atributo.equals("faltas em vagas de caronas")) {

			retorno = Integer.toString(this.usuarioSistema.get(login)
					.getFaltasEmCaronas());
		}

		else if (atributo.equals("presenças em vagas de caronas")) {
			retorno = Integer.toString(this.usuarioSistema.get(login)
					.getPresencaEmCarona());
		}
		if (atributo.equals("historico de caronas")) {
			retorno = this.usuarioSistema.get(login).getCarona().toString();
		} else if (atributo.equals("historico de vagas em caronas")) {
			retorno = this.usuarioSistema.get(login).getListaCaronaAceitas()
					.toString();
		}
		return retorno;

	}

	public void editaPerfil(String idUser, String nome, String endereco,
			String senha, String email)
			throws InvalidAttributeIdentifierException {
		if (!nome.isEmpty() && !endereco.isEmpty() && !senha.isEmpty()
				&& !email.isEmpty()) {
			Usuario usuario = this.usuarioSistema.get(idUser);
			if (!usuario.getEmail().equals(email)) {
				verificaSEmailExiste(email);
				usuario.setEmail(email);
			}
			usuario.setEndereco(endereco);
			usuario.setNome(nome);
			usuario.setSenha(senha);
		}
	}

}