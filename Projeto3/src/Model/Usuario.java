package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login = "";
	private String nome = "";
	private String endereco = "";
	private String idUser = "";
	private String email = "";
	private String senha = "";
	private List<String> carona;
	private List<String> solicitacoesFeitas;
	private List<String> solicitacoesRecebidas;
	private int caronasNaoFuncionou = 0;
	private int caronaSeguras = 0;
	private int faltasEmCaronas = 0;
	private int presencaEmCarona = 0;
	private List<String> solicitacoesAceitas;
	private List<String> listaCaronaAceitas;

	public int getCaronasNaoFuncionou() {
		return caronasNaoFuncionou;
	}

	public void setCaronasNaoFuncionou(int caronasNaoFuncionou) {
		this.caronasNaoFuncionou = caronasNaoFuncionou;
	}

	public int getCaronaSeguras() {
		return caronaSeguras;
	}

	public void setCaronaSeguras(int caronaSeguras) {
		this.caronaSeguras = caronaSeguras;
	}

	public int getFaltasEmCaronas() {
		return faltasEmCaronas;
	}

	public void setFaltasEmCaronas(int faltasEmCaronas) {
		this.faltasEmCaronas = faltasEmCaronas;
	}

	public int getPresencaEmCarona() {
		return presencaEmCarona;
	}

	public void setPresencaEmCarona(int presencaEmCarona) {
		this.presencaEmCarona = presencaEmCarona;
	}

	public List<String> getSolicitacoesAceitas() {
		return solicitacoesAceitas;
	}

	public Usuario(String login, String senha, String nome, String endereco,
			String email) {
		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
		setIdUser(login);
		carona = new ArrayList<String>();
		solicitacoesRecebidas = new ArrayList<String>();
		solicitacoesFeitas = new ArrayList<String>();
		this.listaCaronaAceitas  = new ArrayList<String>();
		solicitacoesAceitas = new ArrayList<String>();

	}

	public void addSolocitacoesFeita(String idSolicitacao) {
		this.solicitacoesFeitas.add(idSolicitacao);
	}

	public void addSolicitacoesRecebidas(String idSolicitacao) {
		this.solicitacoesRecebidas.add(idSolicitacao);
	}

	public List<String> getSolicitacoesFeitas() {
		return solicitacoesFeitas;
	}

	public List<String> getSolicitacoesRecebidas() {
		return solicitacoesRecebidas;
	}

	public Usuario() {

	}

	public void addCarona(String IdCarona) {
		carona.add(IdCarona);
	}

	public List<String> getCarona() {
		return carona;
	}

	private void verificaNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome inválido");
		}
	}

	private void verificaLogin(String login) {
		if (login == null || login.isEmpty()) {
			throw new IllegalArgumentException("Login inválido");
		}
	}

	private void verificaEmail(String email) {
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Email inválido");
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		verificaLogin(login);
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		verificaNome(nome);
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		verificaEmail(email);
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<String> getListaCaronaAceitas() {
		return listaCaronaAceitas;
	}

	public void reviewVagaEmCarona(String idCarona, String review,
			Usuario caroneiro) {
		if (!(caroneiro.getListaCaronaAceitas().contains(idCarona))) {
			throw new IllegalArgumentException(
					"Usuário não possui vaga na carona.");
		}

		else if (review.equals("faltou")) {
			caroneiro.setFaltasEmCaronas(caroneiro.getFaltasEmCaronas() + 1);
		}

		else if (review.equals("não faltou")) {
			caroneiro.setPresencaEmCarona(caroneiro.getPresencaEmCarona() + 1);
		} else {
			throw new IllegalArgumentException("Opção inválida.");
		}
	}

	public void reviewCarona(String idCarona, String review, Usuario donoCarona) {
		if (!(this.listaCaronaAceitas.contains(idCarona))) {
			throw new IllegalArgumentException(
					"Usuário não possui vaga na carona.");
		} else if (review.equals("segura e tranquila")) {
			donoCarona.setCaronaSeguras(donoCarona.getCaronaSeguras() + 1);
		} else if (review.equals("não funcionou")) {
			donoCarona.setCaronasNaoFuncionou(donoCarona
					.getCaronasNaoFuncionou() + 1);

		} else {
			throw new IllegalArgumentException("Opção inválida.");
		}

	}

	public String visualizarPerfil(String login) {
		if (login == null || login.isEmpty() || !(this.login.equals(login))) {
			throw new IllegalAccessError("Login inválido");
		}
		return login;
	}

}
