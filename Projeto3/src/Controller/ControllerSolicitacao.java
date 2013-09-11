package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Carona;
import Model.PontoEncontro;
import Model.Solicitacao;
import Model.Usuario;



public class ControllerSolicitacao {

	private Map<String,Solicitacao> solicitacoesSistema;
	
	public ControllerSolicitacao(){
		this.solicitacoesSistema= new HashMap<String, Solicitacao>();
	}
	
	
	public String addSolicitacaoPonto(Usuario donaDaSolicitacao, Carona carona, String ponto){
		PontoEncontro pontos = new PontoEncontro(ponto, donaDaSolicitacao.getIdUser(), carona.getIdCarona());
		Solicitacao soli= new Solicitacao(donaDaSolicitacao, carona, pontos);
		this.solicitacoesSistema.put(soli.getIdSolicitacao(), soli);
		return soli.getIdSolicitacao();
	}
	
	public String addSolicitacao(Usuario donaDaSolicitacao, Carona carona){
		Solicitacao soli= new Solicitacao(donaDaSolicitacao, carona);
		this.solicitacoesSistema.put(soli.getIdSolicitacao(), soli);
		return soli.getIdSolicitacao();
	}
	
	public void aceitaSolicitacao(Usuario DonoCarona, String idSolicitacao){
		this.solicitacoesSistema.get(idSolicitacao).aceitaSolicitacao();
	}
	
	public void recusarSolicitacao(Usuario DonoCarona, String idSolicitacao){
		this.solicitacoesSistema.get(idSolicitacao).recusarSolicitacao();
	}
	
	public String getAtributo(String idSolicitacao, String atributo){
		String retorno ="";
		if( atributo.equals("origem")){
			retorno = this.solicitacoesSistema.get(idSolicitacao).getCarona().getOrigem();
		}
		else if( atributo.equals("destino")){
			retorno = this.solicitacoesSistema.get(idSolicitacao).getCarona().getDestino();
		}
		else if( atributo.equals("Dono da carona")){
			retorno = this.solicitacoesSistema.get(idSolicitacao).getDonoCarona().getNome();
		}
		else if (atributo.equals("Dono da solicitacao")){
			retorno = this.solicitacoesSistema.get(idSolicitacao).getDonoDaSolicitacao().getNome();
		}
		else if(atributo.equals("Ponto de Encontro")){
			retorno = this.solicitacoesSistema.get(idSolicitacao).getPonto().toString();
		}
		
		return retorno;
	}
	
	public void desistirRequisicao(Usuario donoSolicitacao, Carona carona, String idSolicitacao){
		this.solicitacoesSistema.get(idSolicitacao).desistirSolicitacao(donoSolicitacao, carona);
	}

	public Map<String, Solicitacao> getSolicitacoesSistema() {
		return solicitacoesSistema;
	}
	
	public List<Solicitacao> fazerListaSolicitacoesFeitas(Usuario user){
		List<Solicitacao> resultado = new ArrayList<Solicitacao>();
		for (String solicitacao : user.getSolicitacoesFeitas()) {
			resultado.add(this.solicitacoesSistema.get(solicitacao));
		}
		return resultado;
	}
	
	public List<Solicitacao> fazerListaSolicitacoesRecebidas(Usuario user){
		List<Solicitacao> resultado = new ArrayList<Solicitacao>();
		for (String solicitacao : user.getSolicitacoesRecebidas()) {
			resultado.add(this.solicitacoesSistema.get(solicitacao));
		}
		return resultado;
	}
	

	
}
