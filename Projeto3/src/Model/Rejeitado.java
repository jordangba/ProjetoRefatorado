package Model;

public class Rejeitado implements Estado {

	public String toString(){
		return "Rejeitado";
	}

	@Override
	public Estado mudaEstadoAceitacao(Estado estado) {
		return null;
	
	}

	@Override
	public Estado mudaEstadoRejeicao(Estado estado) {
		return null;
		
	}
	@Override
	public Estado mudaEstadoDesistir(Estado estado) {
		return estado= new Desistir();
	}
}
