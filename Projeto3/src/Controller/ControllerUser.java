package Controller;
import java.util.HashMap;
import java.util.Map;

import javax.naming.directory.InvalidAttributeIdentifierException;

import Model.Usuario;


public class ControllerUser {
	
	private Map<String,Usuario> usuarioSistema;
	private Usuario logado;

	public ControllerUser(){
		this.usuarioSistema = new HashMap<String, Usuario>();	
		this.logado= new Usuario();
	}
	
	public void addUsuarioAoSistema(String login, String senha, String nome, String endereco, String email) throws InvalidAttributeIdentifierException {
		verificaSeLoginExiste(login);
		verificaSEmailExiste(email);
		Usuario user= new Usuario(login, senha, nome, endereco, email);
		this.usuarioSistema.put(user.getLogin(), user);
	}
	
	public String LogarUser(String senha, String login){
		verificaLoginESenha(login, senha);
		logado= usuarioBuscaPeloID(usuarioSistema.get(login).getIdUser());
		return usuarioSistema.get(login).getIdUser();
	}
	
	private void verificaLoginESenha(String login, String senha){
		verificaLogin(login);
		if(usuarioSistema.get(login).getSenha().equals(senha)==false){
			throw new IllegalAccessError("Login inválido");
		}
	}

	private void verificaLogin(String login) throws IllegalAccessError {
		if(login==null || login.isEmpty() ){
			throw new IllegalAccessError("Login inválido");
		}
		if(!(usuarioSistema.containsKey(login))){
			throw new IllegalAccessError("Usuário inexistente");
		}
	}

	private void verificaSeLoginExiste(String login)
			throws InvalidAttributeIdentifierException {
		if(usuarioSistema.containsKey(login)){
			throw new InvalidAttributeIdentifierException("Já existe um usuário com este login");
		}
	}
	public Usuario usuarioBuscaPeloID(String idUser){
		for (Usuario user : usuarioSistema.values()) {
			if(user.getIdUser().equals(idUser)){
				return user;
			}	
		}
		return null;
	}

	private void verificaSEmailExiste(String email) throws InvalidAttributeIdentifierException{
		for (Usuario user : usuarioSistema.values()) {
			if(user.getEmail().equals(email)){
				throw new InvalidAttributeIdentifierException("Já existe um usuário com este email");
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

	public String getAtributo(String login, String atributo){
		String retorno ="";
	    verificaLogin(login);
		if(atributo== null || atributo.isEmpty()){
			throw new  IllegalArgumentException("Atributo inválido");
		}
		else if(atributo.equals("nome")){
			retorno = this.logado.getNome();
		}
		else if(atributo.equals("endereco")){
			retorno = this.logado.getEndereco();
		}
		else{
			throw new  IllegalArgumentException("Atributo inexistente");
		}
		return retorno;
		
	}
	public void encerrarSessao(){

		this.logado= new Usuario();

	}
	
}