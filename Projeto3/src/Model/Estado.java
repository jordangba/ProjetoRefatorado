package Model;

public interface Estado  {

	public Estado mudaEstadoAceitacao(Estado estado);
	public Estado mudaEstadoRejeicao(Estado estado);
	public Estado mudaEstadoDesistir(Estado estado);
}
