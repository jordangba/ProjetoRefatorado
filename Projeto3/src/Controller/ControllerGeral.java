package Controller;


import java.util.List;

import javax.naming.directory.InvalidAttributeIdentifierException;

import Model.Carona;
import Model.Solicitacao;
import Model.Usuario;

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
		return this.caronas.addCaronaAoSistema(this.usuarios.getUsuarioSistema().get(idUsuario), origem, destino, data, hora, vagas);
		
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
	
	public List<Carona> montaListaDeCarona(String idUser){
		return this.caronas.fazListaCaronaUser(this.usuarios.getUsuarioSistema().get(idUser));
	}
	
	public List<Solicitacao> montaListaDeSolicitacaoFeitas(String idUser){
		return this.solicitacaoes.fazerListaSolicitacoesFeitas(this.usuarios.getUsuarioSistema().get(idUser));
		
	}
	
	public List<Solicitacao> montaListaDeSolicitacaoRecebidas(String idUser){
		return this.solicitacaoes.fazerListaSolicitacoesRecebidas(this.usuarios.getUsuarioSistema().get(idUser));
	}
	
	public List<Carona> transformaListaDeIdCaronaEmListaDeCarona(List<String> idCaronas){
		return this.caronas.transformaListaDeIdCaronaEmListaDeCarona(idCaronas);
	}
	
	private void verificaSessao(String idUsuario){
		if(idUsuario== null || idUsuario.isEmpty()){
			throw new IllegalAccessError("Sessão inválida");
		}
		else if(!(this.usuarios.getUsuarioSistema().containsKey(idUsuario))){
			throw new IllegalAccessError("Sessão inexistente");
		}
	}
	
	public Usuario buscaUsuarioPorId(String id){
		return usuarios.usuarioBuscaPeloID(id);
	}
	
	public void reviewCarona(String idSessao, String idCarona, String review){
		this.usuarios.reviewCarona(idSessao, idCarona, review, this.caronas.getCaronaDoSistema().get(idCarona).getUser().getLogin());
	}
	public void reviewVagaEmCarona(String idSessao, String idCarona, String login,String review ){
		this.usuarios.reviewVagaEmCarona(idSessao, idCarona, review, login);
	}
	
	public String visualizarPerfil(String idSessao, String login){
		return this.usuarios.visualizarPerfil(idSessao, login);
	}
	public String getAtributoPerfil(String login, String atributo){
		return this.usuarios.getAtributoPerfil(login, atributo);
	}
}
