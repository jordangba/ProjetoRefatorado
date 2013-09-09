package Controller;


import java.util.List;
import javax.naming.directory.InvalidAttributeIdentifierException;

public class ControllerGeral {

	ControllerUser usuarios;
	ControllerCaronas caronas;
	ControllerSolicitacao solicitacaoes;
	
	public ControllerGeral(){
		this.usuarios= new ControllerUser();
		this.caronas= new ControllerCaronas();
		this.solicitacaoes= new ControllerSolicitacao();
	}
	
	
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws InvalidAttributeIdentifierException{
		usuarios.addUsuarioAoSistema(login, senha, nome, endereco, email);
		}
	
	public String getAtributoUsuario(String login, String atributo){
		return usuarios.getAtributo(login, atributo);
	}
	
	public String abrirSessao(String login, String senha){
		
		return usuarios.LogarUser(senha, login);
	}
	
	public String cadastrarCarona (String idUsuario, String origem, String destino, String data, String hora, String vagas){
		verificaSessao(idUsuario);
		String id =this.caronas.addCaronaAoSistema(this.usuarios.getUsuarioSistema().get(idUsuario), origem, destino, data, hora, vagas);
		this.usuarios.getUsuarioSistema().get(idUsuario).addCarona(id);
		return id;
		
	}
	
	public String getCarona(String idCarona){
		return this.caronas.getCarona(idCarona);
	}
	
	public String getTrajeto(String idCarona){
		return this.caronas.getTrageto(idCarona);
	}
	
	public String getAtributoCarona(String idCarona, String atributo){
		return this.caronas.getAtributoCarona(idCarona, atributo);
	}
	
	public List<String> buscaCarona(String origem, String destino){
		return this.caronas.buscaCarona(origem, destino);
	}
	
	private void verificaSessao(String idUsuario){
		if(idUsuario== null || idUsuario.isEmpty()){
			throw new IllegalAccessError( "Sessão inválida");
		}
		else if(!(this.usuarios.getUsuarioSistema().containsKey(idUsuario))){
			throw new IllegalAccessError("Sessão inexistente");
		}
	}
	public String addPonto(String pontos, String idUser, String idCarona){
		return this.caronas.addPonto(pontos, idUser, idCarona);
	}
	
	public void repostaPonto(String idUser, String idCarona,String idPonto ,String ponto){
		this.caronas.repostaPonto(idUser, idCarona, idPonto, ponto);
	}
	
	public void encerrarSessao(){
		this.usuarios.encerrarSessao();
	}
	
	public String solicitarVaga(String idSessao, String idCarona){
		return this.solicitacaoes.addSolicitacao(this.usuarios.getUsuarioSistema().get(idSessao), this.caronas.getCaronaDoSistema().get(idCarona));
	}
	
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona, String ponto){
		return this.solicitacaoes.addSolicitacaoPonto(this.usuarios.getUsuarioSistema().get(idSessao), this.caronas.getCaronaDoSistema().get(idCarona), ponto);
	}
	
	public String getAtributoSolicitacao(String idSolicitacao, String atributo){
		return this.solicitacaoes.getAtributo(idSolicitacao, atributo);
	}
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao){
		this.solicitacaoes.recusarSolicitacao(this.usuarios.getLogado(), idSolicitacao);
	}
	
	public void aceitarSolicitacao (String idSessao, String idSolicitacao){
		this.solicitacaoes.aceitaSolicitacao(this.usuarios.getLogado(), idSolicitacao);
	}
	
	public void desistirRequisicao(String idSessao, String idCarona, String idSolicitacao ){
		this.solicitacaoes.desistirRequisicao(this.usuarios.getUsuarioSistema().get(idSessao), this.caronas.getCaronaDoSistema().get(idCarona), idSolicitacao);
	}



	
}
