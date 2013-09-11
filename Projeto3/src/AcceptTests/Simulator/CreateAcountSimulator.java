package AcceptTests.Simulator;


import javax.naming.directory.InvalidAttributeIdentifierException;

import Controller.ControllerGeral;


public class CreateAcountSimulator {

	ControllerGeral control= new ControllerGeral();
	
	public void zerarSistema() {
		this.control = new ControllerGeral();
	}
	
	public void criarUsuario(String login, String senha, String nome,
		String endereco, String email) throws InvalidAttributeIdentifierException{
		control.criarUsuario(login, senha, nome, endereco, email);
	}
	
	public String abrirSessao(String login, String senha){
		
		return control.abrirSessao(login, senha);
	}
	
	public String getAtributoUsuario(String login, String atributo){
		return control.getAtributoUsuario(login, atributo);
	}
	
	public String cadastrarCarona (String idUsuario, String origem, String destino, String data, String hora, String vagas){
		return this.control.cadastrarCarona(idUsuario, origem, destino, data, hora, vagas);
	}
	
	public String getCarona(String idCarona){
		return this.control.getCarona(idCarona);
	}
	
	public String  getTrajeto(String idCarona){
		return this.control.getTrajeto(idCarona);
	}
	public String localizarCarona(String idsessao,String origem, String destino){
		return this.control.buscaCarona(origem, destino).toString();
	}
	
	public String getAtributoCarona(String idCarona, String atributo){
		return this.control.getAtributoCarona(idCarona, atributo);
	}
	
	public String sugerirPontoEncontro(String idUser, String idCarona, String pontos){
		return control.addPonto(pontos, idUser, idCarona);
	}
	
	public void responderSugestaoPontoEncontro(String idUser, String idCarona, String idSugestao, String ponto){
		control.repostaPonto(idUser, idCarona, idSugestao, ponto);
	}
	
	public String solicitarVaga(String idSessao, String idCarona){
		return this.control.solicitarVaga(idSessao, idCarona);
	}
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona, String ponto){
		return this.control.solicitarVagaPontoEncontro(idSessao, idCarona, ponto);
	}
	public String getAtributoSolicitacao(String idSolicitacao, String atributo){
		return control.getAtributoSolicitacao(idSolicitacao, atributo);
	}
	
	public void aceitarSolicitacaoPontoEncontro(String idSessao, String idSolicitacao){
		this.control.aceitarSolicitacao(idSessao, idSolicitacao);
	}
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao){
		this.control.rejeitarSolicitacao(idSessao, idSolicitacao);
	}
	
	public void aceitarSolicitacao (String idSessao, String idSolicitacao){
		this.control.aceitarSolicitacao(idSessao, idSolicitacao);
	}
	
	public void desistirRequisicao(String idSessao, String idCarona, String idSolicitacao ){
		this.control.desistirRequisicao(idSessao, idCarona, idSolicitacao);
		
	}
	public void reviewCarona(String idSessao, String idCarona,String review){
		this.control.reviewCarona(idSessao, idCarona, review);
	}
	
	public void reviewVagaEmCarona(String idSessao, String idCarona, String login,String review){
		this.control.reviewVagaEmCarona(idSessao, idCarona, login,review);
	}
	
	public String visualizarPerfil(String idSessao, String login){
		return this.control.visualizarPerfil(idSessao, login);
	}
	
	public String getAtributoPerfil(String login, String atributo){
		return this.control.getAtributoPerfil(login, atributo);
	}
	public void  encerrarSessao(String login) {
		control.encerrarSessao();
	}
	public void encerrarSistema() {
		
	}
	
	

}
