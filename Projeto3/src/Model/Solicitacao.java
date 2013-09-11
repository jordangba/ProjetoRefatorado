package Model;

import java.awt.IllegalComponentStateException;

public class Solicitacao {
	
	
	private Usuario donoDaSolicitacao;
	private Carona carona;
	private String idSolicitacao;
	private Usuario donoCarona;
	private PontoEncontro ponto;
	private Estado estado;
	
	
	public Solicitacao(Usuario donaDaSolicitacao, Carona carona, PontoEncontro ponto){
		setDonoDaSolicitacao(donaDaSolicitacao);
		setCarona(carona);
		setDonoCarona(carona.getUser());
		setPonto(ponto);
		estado= new EmEspera();
		setIdSolicitacao(Integer.toString(hashCode()));
		this.donoDaSolicitacao.addSolocitacoesFeita(idSolicitacao);
		this.donoCarona.addSolicitacoesRecebidas(idSolicitacao);
	
	}
	
	public Solicitacao(Usuario donaDaSolicitacao, Carona carona){
		setDonoDaSolicitacao(donaDaSolicitacao);
		setCarona(carona);
		setDonoCarona(carona.getUser());
		estado= new EmEspera();
		setIdSolicitacao(Integer.toString(hashCode()));
		this.donoDaSolicitacao.addSolocitacoesFeita(idSolicitacao);
		this.donoCarona.addSolicitacoesRecebidas(idSolicitacao);

	
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carona == null) ? 0 : carona.hashCode());
		result = prime
				* result
				+ ((donoDaSolicitacao == null) ? 0 : donoDaSolicitacao
						.hashCode());
		result = prime * result + ((ponto == null) ? 0 : ponto.hashCode());
		return result;
	}



	public void aceitaSolicitacao(){
		if((carona.getVagas()>1 &&(estado instanceof EmEspera))){
			int vagas= carona.getVagas()-1;
			carona.setVagas(vagas);
			this.estado=estado.mudaEstadoAceitacao(this.estado);
			donoDaSolicitacao.getSolicitacoesAceitas().add(carona.getIdCarona());
			donoDaSolicitacao.getSolicitacoesFeitas().remove(idSolicitacao);
		}
		else{
			throw new IllegalComponentStateException("Solicitação inexistente");
		}
	}
	public void recusarSolicitacao(){
		if(estado instanceof EmEspera){
			this.estado=estado.mudaEstadoRejeicao(this.estado);
		}
		else{
			throw new IllegalComponentStateException("Solicitação inexistente");
		}
	}

	public Usuario getDonoDaSolicitacao() {
		return donoDaSolicitacao;
	}
	public void setDonoDaSolicitacao(Usuario donoDaSolicitacao) {
		this.donoDaSolicitacao = donoDaSolicitacao;
	}

	public Carona getCarona() {
		return carona;
	}
	public void setCarona(Carona carona) {
		this.carona = carona;
	}
	public String getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(String idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	public PontoEncontro getPonto() {
		return ponto;
	}
	public void setPonto(PontoEncontro ponto) {
		this.ponto = ponto;
	}
	
	public void desistirSolicitacao(Usuario user, Carona carona){
		if( estado instanceof Aceito){
			int vagas= carona.getVagas() +1;
			carona.setVagas(vagas);
			this.estado= estado.mudaEstadoDesistir(estado);
		}
		else if(estado instanceof EmEspera){
			this.estado= estado.mudaEstadoDesistir(estado);
		}
	}

	public void setDonoCarona(Usuario donoCarona) {
		this.donoCarona = donoCarona;
	}
	
	
	public Usuario getDonoCarona() {
		return donoCarona;
	}
	

	
}
