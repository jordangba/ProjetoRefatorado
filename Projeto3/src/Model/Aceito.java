package Model;

public class Aceito implements Estado {

	
	public String toString(){
		return "Aceito";
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
