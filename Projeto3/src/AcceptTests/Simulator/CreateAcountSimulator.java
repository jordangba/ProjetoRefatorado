package AcceptTests.Simulator;

import java.io.IOException;
import java.io.Serializable;

import javax.naming.directory.InvalidAttributeIdentifierException;

import Controller.ControllerGeral;
import PersistenciaXML.Persistencia;

public class CreateAcountSimulator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControllerGeral control = new ControllerGeral();
	private Persistencia persistencia = new Persistencia("Projeto3.txt");

	public void zerarSistema() {
		this.control = new ControllerGeral();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email)
			throws InvalidAttributeIdentifierException, IOException {
		control.criarUsuario(login, senha, nome, endereco, email);
	}

	public String abrirSessao(String login, String senha) {

		return control.abrirSessao(login, senha);
	}

	public String getAtributoUsuario(String login, String atributo) {
		return control.getAtributoUsuario(login, atributo);
	}

	public String cadastrarCarona(String idUsuario, String origem,
			String destino, String data, String hora, String vagas) throws IOException {
		return this.control.cadastrarCarona(idUsuario, origem, destino, data,
				hora, vagas);
	}

	public String getCarona(String idCarona) {
		return this.control.getCarona(idCarona);
	}

	public String getTrajeto(String idCarona) {
		return this.control.getTrajeto(idCarona);
	}

	public String localizarCarona(String idsessao, String origem, String destino) {
		return Adapter.ColecaoParaString(this.control.buscaCarona(origem,
				destino));
	}

	public String getAtributoCarona(String idCarona, String atributo) {
		return this.control.getAtributoCarona(idCarona, atributo);
	}

	public String sugerirPontoEncontro(String idUser, String idCarona,
			String pontos) throws IOException {
		return control.addPonto(pontos, idUser, idCarona);
	}

	public void responderSugestaoPontoEncontro(String idUser, String idCarona,
			String idSugestao, String ponto) throws IOException {
		control.repostaPonto(idUser, idCarona, idSugestao, ponto);
	}

	public String solicitarVaga(String idSessao, String idCarona) throws IOException {
		return this.control.solicitarVaga(idSessao, idCarona);
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws IOException {
		return this.control.solicitarVagaPontoEncontro(idSessao, idCarona,
				ponto);
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		return control.getAtributoSolicitacao(idSolicitacao, atributo);
	}

	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws IOException {
		this.control.aceitarSolicitacao(idSessao, idSolicitacao);
	}

	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws IOException {
		this.control.rejeitarSolicitacao(idSessao, idSolicitacao);
	}

	public void aceitarSolicitacao(String idSessao, String idSolicitacao) throws IOException {
		this.control.aceitarSolicitacao(idSessao, idSolicitacao);
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws IOException {
		this.control.desistirRequisicao(idSessao, idCarona, idSolicitacao);

	}

	public void reviewCarona(String idSessao, String idCarona, String review) {
		this.control.reviewCarona(idSessao, idCarona, review);
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String login, String review) {
		this.control.reviewVagaEmCarona(idSessao, idCarona, login, review);
	}

	public String visualizarPerfil(String idSessao, String login) {
		return this.control.visualizarPerfil(idSessao, login);
	}

	public String getAtributoPerfil(String login, String atributo) {
		return Adapter.adaptadorParaGetAtributoPerfil(atributo,
				this.control.getAtributoPerfil(login, atributo));
	}

	public void encerrarSessao(String login) {
		control.encerrarSessao();
	}
	
	public String getCaronaUsuario(String idUser, int indexCarona){
		return this.control.getUsuarios().getUsuarioSistema().get(idUser).getCarona().get(indexCarona-1);
	}

	public void encerrarSistema() {

		try {
			persistencia.persistirDados(this.control);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reiniciarSistema() {
		Persistencia persistencia = new Persistencia("Projeto3.txt");
		
		try {
			this.control = persistencia.lerDados(this.control);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
public String getTodasCaronasUsuario(String idUser){
		return Adapter.ColecaoParaString(this.control.getUsuarios().getUsuarioSistema().get(idUser).getCarona());
	}
	public String getSolicitacoesConfirmadas(String idUser, String idCarona){
		return Adapter.ColecaoParaString(this.control.getCaronas().getCaronaDoSistema().get(idCarona).getSolicitacoesConfirmadas());
	
	}
	
	public String getPontosSugeridos(String idUser, String idCarona){
		return this.control.getCaronas().getCaronaDoSistema().get(idCarona).getPontos().toString();
	}
	public String getPontosEncontro(String idUser, String idCarona){
		return this.control.getCaronas().getCaronaDoSistema().get(idCarona).getPontos().toString();
	}
	public String getSolicitacoesPendentes(String idUser, String idCarona){
		return Adapter.ColecaoParaString(this.control.getUsuarios().getUsuarioSistema().get(idUser).getSolicitacoesFeitas());
	}

}
