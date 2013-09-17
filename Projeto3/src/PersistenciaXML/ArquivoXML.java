package PersistenciaXML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import Model.Carona;
import Model.PontoEncontro;
import Model.Solicitacao;
import Model.Usuario;

public class ArquivoXML implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Element controllerGeral;

	private Element controllerUsuario;
	private Element usuarios;

	private Element controllerCaronas;
	private Element caronas;

	private Element controllerSolicitacoes;
	private Element solicitacoes;
	private Element pontos;

	private Document documento;

	public ArquivoXML() {
		this.controllerGeral = new Element("ControllerGeral");

		this.controllerUsuario = new Element("ControllerDeUsuarios");
		this.usuarios = new Element("Usuarios");
		this.controllerUsuario.addContent(this.usuarios);

		this.controllerCaronas = new Element("ControllerDeCaronas");
		this.caronas = new Element("Caronas");
		this.controllerCaronas.addContent(this.caronas);

		this.controllerSolicitacoes = new Element("ControllerDeSolicitacoes");
		this.solicitacoes = new Element("Solicitacoes");
		this.controllerSolicitacoes.addContent(this.solicitacoes);

		this.controllerGeral.addContent(controllerUsuario);
		this.controllerGeral.addContent(controllerCaronas);
		this.controllerGeral.addContent(controllerSolicitacoes);
		this.documento = new Document(controllerGeral);
		persistirArquivo();
	}

	public void cadastraUsuario(Usuario usuario) {
		Element novoUsuario = new Element("Usuario");

		Element nome = new Element("Nome");
		nome.addContent(usuario.getNome());
		novoUsuario.addContent(nome);

		Element login = new Element("Login");
		login.addContent(usuario.getLogin());
		novoUsuario.addContent(login);

		Element email = new Element("Email");
		email.addContent(usuario.getEmail());
		novoUsuario.addContent(email);

		Element endereco = new Element("Endereco");
		endereco.addContent(usuario.getEndereco());
		novoUsuario.addContent(endereco);

		Element senha = new Element("Senha");
		senha.addContent(usuario.getSenha());
		novoUsuario.addContent(senha);

		Element idUsuario = new Element("IDUsuario");
		idUsuario.addContent(usuario.getIdUser());
		novoUsuario.addContent(idUsuario);

		this.usuarios.addContent(novoUsuario);
		persistirArquivo();
	}

	public void cadastraCarona(Carona carona) {
		Element novaCarona = new Element("Carona");

		Element origem = new Element("Origem");
		origem.addContent(carona.getOrigem());
		novaCarona.addContent(origem);

		Element destino = new Element("Destino");
		destino.addContent(carona.getDestino());
		novaCarona.addContent(destino);

		Element data = new Element("Data");
		data.addContent(carona.getData());
		novaCarona.addContent(data);

		Element hora = new Element("Hora");
		hora.addContent(carona.getHora());
		novaCarona.addContent(hora);

		Element vagas = new Element("Vagas");
		vagas.addContent(Integer.toString(carona.getVagas()));
		novaCarona.addContent(vagas);

		this.caronas.addContent(novaCarona);
		persistirArquivo();
	}

	public void cadastraSolicitacao(Solicitacao solicitacao) {
		Element novaSolicitacao = new Element("Solicitacao");

		Element donoDaSol = new Element("DonoDaSolicitacao");
		donoDaSol.addContent(solicitacao.getDonoCarona().getIdUser());
		novaSolicitacao.addContent(donoDaSol);

		Element carona = new Element("Carona");
		carona.addContent(solicitacao.getCarona().getIdCarona());
		novaSolicitacao.addContent(carona);

		Element idSol = new Element("IDSolicitacao");
		idSol.addContent(solicitacao.getIdSolicitacao());
		novaSolicitacao.addContent(idSol);

		Element donoCarona = new Element("DonoDaCarona");
		donoCarona.addContent(solicitacao.getDonoCarona().getIdUser());
		novaSolicitacao.addContent(donoCarona);

		if (solicitacao.getPonto() != null) {
			Element pontoDeEncontro = new Element("PontoDeEncontro");
			pontoDeEncontro.addContent(solicitacao.getPonto().getIdponto());
			novaSolicitacao.addContent(pontoDeEncontro);
		}

		Element estado = new Element("EstadoDaSolicitacao");
		estado.addContent(solicitacao.getEstado().toString());
		novaSolicitacao.addContent(estado);

		this.solicitacoes.addContent(novaSolicitacao);
		persistirArquivo();
	}

	public void cadastraPontos(PontoEncontro ponto) {
		Element novoPonto = new Element("PontoDeEncontro");

		Element idUser = new Element("IDUsuario");
		idUser.addContent(ponto.getIdUser());
		novoPonto.addContent(idUser);

		Element idCarona = new Element("IDCarona");
		idCarona.addContent(ponto.getIdCarona());
		novoPonto.addContent(idCarona);

		Element pontos = new Element("Pontos");
		pontos.addContent(ponto.getPonto());
		novoPonto.addContent(pontos);

	}

	public void persistirArquivo() {
		XMLOutputter xout = new XMLOutputter();
		try {
			FileWriter arquivo = new FileWriter(new File("Persistencia.xml"));
			xout.output(this.documento, arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeSolicitacao(String idSolicitacao) {
		for (int i = 0; i < this.solicitacoes.getChildren().size(); i++) {
			if (((Element) this.solicitacoes.getChildren().get(i))
					.getChild("IDSolicitacao").getValue().equals(idSolicitacao)) {
				this.solicitacoes.removeContent(i);
			}
		}
	}

}
