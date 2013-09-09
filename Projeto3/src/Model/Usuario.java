package Model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String login="";
	private String nome="";
	private String endereco= "";
	private String idUser="";
	private String email="";
	private String senha="";
	private List<String> carona;
	private List<String> solicitacoesFeitas;
	private List<String> solicitacoesRecebidas;
	
	public Usuario(String login, String senha, String nome, String endereco, String email){
		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setEndereco(endereco);
		setEmail(email);
		setIdUser(login);
		carona= new ArrayList<String>();
		solicitacoesRecebidas= new ArrayList<String>();
		solicitacoesFeitas= new ArrayList<String>();
		
	}
	
	public void addSolocitacoesFeita(String idSolicitacao){
		this.solicitacoesFeitas.add(idSolicitacao);
	}
	
	public void addSolicitacoesRecebidas(String idSolicitacao){
		this.solicitacoesRecebidas.add(idSolicitacao);
	}
	public List<String> getSolicitacoesFeitas() {
		return solicitacoesFeitas;
	}
	public List<String> getSolicitacoesRecebidas() {
		return solicitacoesRecebidas;
	}
	public Usuario(){
		
	}
	
	public void addCarona(String IdCarona){
		carona.add(IdCarona);
	}
	public List<String> getCarona() {
		return carona;
	}
	private void verificaNome(String nome){
		if( nome== null || nome.isEmpty()){
			throw new IllegalArgumentException("Nome inválido");
		}
	}
	private void verificaLogin(String login){
		if(login== null || login.isEmpty()){
			throw new IllegalArgumentException("Login inválido");
		}
	}
	
	private void verificaEmail(String email){
		if( email== null || email.isEmpty()){
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
}
