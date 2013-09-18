package Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.naming.directory.InvalidAttributeIdentifierException;

import Criador.FazUserECaronas;
import Model.Carona;
import Model.Solicitacao;
import Model.Usuario;
import PersistenciaXML.ArquivoXML;
import PersistenciaXML.Persistencia;

public class ControllerGeral implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControllerUser usuarios;
	private ControllerCaronas caronas;
	private ControllerSolicitacao solicitacaoes;
	public ArquivoXML arquivo;

	public ControllerGeral() {
		this.usuarios = new ControllerUser();
		this.caronas = new ControllerCaronas();
		this.solicitacaoes = new ControllerSolicitacao();
		try {
			this.arquivo = new ArquivoXML();
		} catch (Exception e) {
		}
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email)
			throws InvalidAttributeIdentifierException, IOException {
		this.usuarios.addUsuarioAoSistema(login, senha, nome, endereco, email);
		this.arquivo.cadastraUsuario(this.usuarios.getUsuarioSistema().get(
				login));
		salvar();
	}

	public String getAtributoUsuario(String login, String atributo) {
		return usuarios.getAtributo(login, atributo);
	}

	public String abrirSessao(String login, String senha) {
		return usuarios.LogarUser(senha, login);
	}

	public String cadastrarCarona(String idUsuario, String origem,
			String destino, String data, String hora, String vagas) throws IOException {
		verificaSessao(idUsuario);
		String retorno = this.caronas.addCaronaAoSistema(this.usuarios
				.getUsuarioSistema().get(idUsuario), origem, destino, data,
				hora, vagas);
		this.arquivo.cadastraCarona(this.caronas.getCaronaDoSistema().get(
				retorno));
		salvar();

		return retorno;
	}

	public String getCarona(String idCarona) {
		return this.caronas.getCarona(idCarona);
	}

	public Carona pegaCarona(String idCarona) {
		return this.caronas.pegaCarona(idCarona);
	}

	public String getTrajeto(String idCarona) {
		return this.caronas.getTrageto(idCarona);
	}

	public String getAtributoCarona(String idCarona, String atributo) {
		return this.caronas.getAtributoCarona(idCarona, atributo);
	}

	public List<String> buscaCarona(String origem, String destino) {
		return this.caronas.buscaCarona(origem, destino);
	}

	public String addPonto(String pontos, String idUser, String idCarona) throws IOException {
		String retorno= this.caronas.addPonto(pontos, idUser, idCarona);
		salvar();
		return retorno;
	}

	public void repostaPonto(String idUser, String idCarona, String idPonto,
			String ponto) throws IOException {
		this.caronas.repostaPonto(idUser, idCarona, idPonto, ponto);
		salvar();
	}

	public void encerrarSessao() {
		this.usuarios.encerrarSessao();
	}

	public String solicitarVaga(String idSessao, String idCarona)
			throws IOException {
		String retorno = this.solicitacaoes.addSolicitacao(this.usuarios
				.getUsuarioSistema().get(idSessao), this.caronas
				.getCaronaDoSistema().get(idCarona));
		this.arquivo.cadastraSolicitacao(this.solicitacaoes
				.getSolicitacoesSistema().get(retorno));
		salvar();
		return retorno;
	}

	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws IOException {
		String retorno = this.solicitacaoes.addSolicitacaoPonto(this.usuarios
				.getUsuarioSistema().get(idSessao), this.caronas
				.getCaronaDoSistema().get(idCarona), ponto);
		this.arquivo.cadastraSolicitacao(this.solicitacaoes
				.getSolicitacoesSistema().get(retorno));
		salvar();
		return retorno;
	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		return this.solicitacaoes.getAtributo(idSolicitacao, atributo);
	}

	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)
			throws IOException {
		this.arquivo.removeSolicitacao(idSolicitacao);
		this.solicitacaoes.recusarSolicitacao(this.usuarios.getLogado(),
				idSolicitacao);
		this.arquivo.cadastraSolicitacao(this.solicitacaoes
				.getSolicitacoesSistema().get(idSolicitacao));
		salvar();
	}

	public void aceitarSolicitacao(String idSessao, String idSolicitacao)
			throws IOException {
		this.arquivo.removeSolicitacao(idSolicitacao);
		this.solicitacaoes.aceitaSolicitacao(this.usuarios.getLogado(),
				idSolicitacao);
		this.arquivo.cadastraSolicitacao(this.solicitacaoes
				.getSolicitacoesSistema().get(idSolicitacao));
		salvar();
	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws IOException {
		this.arquivo.removeSolicitacao(idSolicitacao);
		this.solicitacaoes.desistirRequisicao(this.usuarios.getUsuarioSistema()
				.get(idSessao),
				this.caronas.getCaronaDoSistema().get(idCarona), idSolicitacao);
		this.arquivo.cadastraSolicitacao(this.solicitacaoes
				.getSolicitacoesSistema().get(idSolicitacao));
		salvar();
	}

	public List<Carona> montaListaDeCarona(String idUser) {
		return this.caronas.fazListaCaronaUser(this.usuarios
				.getUsuarioSistema().get(idUser));
	}

	public List<Solicitacao> montaListaDeSolicitacaoFeitas(String idUser) {
		return this.solicitacaoes.fazerListaSolicitacoesFeitas(this.usuarios
				.getUsuarioSistema().get(idUser));

	}

	public List<Solicitacao> montaListaDeSolicitacaoRecebidas(String idUser) {
		return this.solicitacaoes.fazerListaSolicitacoesRecebidas(this.usuarios
				.getUsuarioSistema().get(idUser));
	}

	public List<Solicitacao> montaListaDeSolicitacaoAceitas(String iduser) {
		return this.solicitacaoes.fazerListaSolicitacoesAceitas(this.usuarios
				.getUsuarioSistema().get(iduser));
	}

	public List<Carona> transformaListaDeIdCaronaEmListaDeCarona(
			List<String> idCaronas) {
		return this.caronas.transformaListaDeIdCaronaEmListaDeCarona(idCaronas);
	}

	private void verificaSessao(String idUsuario) {
		if (idUsuario == null || idUsuario.isEmpty()) {
			throw new IllegalAccessError("Sess�o inv�lida");
		} else if (!(this.usuarios.getUsuarioSistema().containsKey(idUsuario))) {
			throw new IllegalAccessError("Sess�o inexistente");
		}
	}

	public ControllerUser getUsuarios() {
		return usuarios;
	}

	public ControllerCaronas getCaronas() {
		return caronas;
	}

	public ControllerSolicitacao getSolicitacaoes() {
		return solicitacaoes;
	}

	public Usuario buscaUsuarioPorId(String id) {
		return usuarios.usuarioBuscaPeloID(id);
	}

	public void reviewCarona(String idSessao, String idCarona, String review) {
		this.usuarios.reviewCarona(idSessao, idCarona, review, this.caronas
				.getCaronaDoSistema().get(idCarona).getUser().getLogin());
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String login, String review) {
		this.usuarios.reviewVagaEmCarona(idSessao, idCarona, review, login);
	}

	public String visualizarPerfil(String idSessao, String login) {
		return this.usuarios.visualizarPerfil(idSessao, login);
	}

	public String getAtributoPerfil(String login, String atributo) {
		return this.usuarios.getAtributoPerfil(login, atributo);
	}

	public void editaPerfil(String idUser, String nome, String endereco,
			String senha, String email)
			throws InvalidAttributeIdentifierException, IOException {
		this.usuarios.editaPerfil(idUser, nome, endereco, senha, email);
		salvar();
	}

	public void addUserECaronas() throws IOException {
		System.out.println("criando users");
		FazUserECaronas.gerarUserECaronas(this);
		salvar();
	}

	// public static void main(String[] args) {
	// ControllerGeral teste = new ControllerGeral();
	// Usuario usuario1 = new
	// Usuario("Tulio5","Tulio5","Tulio5","Tulio5","Tulio5@email.com");
	// Usuario usuario2 = new
	// Usuario("Tulio2","Tulio2","Tulio2","Tulio2","Tulio2@email.com");
	// teste.arquivo.cadastraUsuario(usuario1);
	// teste.arquivo.cadastraUsuario(usuario2);
	// Carona carona1 = new Carona(usuario1, "A", "B","22/12/2015", "20:20",
	// 10);
	// Carona carona2 = new Carona(usuario2, "C", "D","22/12/2015", "20:20",
	// 10);
	// teste.arquivo.cadastraCarona(carona1);
	// teste.arquivo.cadastraCarona(carona2);
	// Solicitacao sol1 = new Solicitacao(usuario1, carona1,new
	// PontoEncontro("A-B", usuario2.getIdUser(), carona1.getIdCarona()));
	// teste.arquivo.cadastraSolicitacao(sol1);
	// }

	public void salvar() throws IOException {
		Persistencia persistencia = new Persistencia("Projeto3.txt");
		System.out.println("Passei no salvar");
		persistencia.persistirDados(this);
	}
}
