package Model;

public class Desistir implements Estado{

	@Override
	public Estado mudaEstadoAceitacao(Estado estado) {
		return null;
	}

	@Override
	public Estado mudaEstadoRejeicao(Estado estado) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Estado mudaEstadoDesistir(Estado estado) {
		return null;
	}
	
	public String toString(){
		return "Desistiu";
	}

}
