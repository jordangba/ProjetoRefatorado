package Model;

public class EmEspera implements Estado{
	
	public String toString(){
		return "Esperando Resposta";
	}



	@Override
	public Estado mudaEstadoAceitacao(Estado estado) {
		return estado= new Aceito();

		
	}

	@Override
	public Estado mudaEstadoRejeicao(Estado estado) {
		return estado = new Rejeitado();
		
	}



	@Override
	public Estado mudaEstadoDesistir(Estado estado) {
		return estado= new Desistir();
	}

}
