package Controller;

import java.text.SimpleDateFormat;
import java.util.*;


import Model.Carona;
import Model.PontoEncontro;
import Model.Usuario;

public class ControllerCaronas {

	private Map<String,Carona> caronaDoSistema;
	private String datAtual="";
	private String horAtual="";
	
	public ControllerCaronas(){
		this.caronaDoSistema= new HashMap<String, Carona>();
		criaDataEHoraAtual();
	}
	
	
	public String addCaronaAoSistema(Usuario user, String origem, String destino, String data, String hora, String vagas ){
		verificaVagas(vagas);
		Carona carona= new Carona(user, origem, destino, data, hora, Integer.parseInt(vagas));
		this.caronaDoSistema.put(carona.getIdCarona(), carona);
		return carona.getIdCarona();
	}
	
	

	private void verificaVagas(String vagas) {
		if (vagas == null) {
			throw new IllegalArgumentException("Vaga inv�lida");
		}
		if (vagas.isEmpty() || vagas.matches("[a-zA-Z]*")
				|| Integer.parseInt(vagas) <= 0) {
			throw new IllegalArgumentException("Vaga inv�lida");
		}
		
	}


	public String getAtributoCarona(String idCarona, String atributo){
		String retorno ="";
		verificaIdCarona(idCarona);
		verificAtributo(atributo);
		if(atributo.equals("destino")){
			retorno = this.caronaDoSistema.get(idCarona).getDestino();
		}
		else if(atributo.equals("origem")){
			retorno = this.caronaDoSistema.get(idCarona).getOrigem();
		}
		else if(atributo.equals("data")){
			retorno = this.caronaDoSistema.get(idCarona).getData();
		}
		
		else if(atributo.equals("vagas")){
			retorno = Integer.toString(this.caronaDoSistema.get(idCarona).getVagas());
		}
		else{
			throw new IllegalAccessError("Atributo inexistente");
		}
		return retorno;
	}
	
	public String  getCarona(String idCarona){
		verificaCarona(idCarona);
		Carona car= this.caronaDoSistema.get(idCarona);
		return car.getOrigem() +" para "+ car.getDestino()+", no dia "+car.getData()+", as "+car.getHora();
	}
	
	private void verificaCarona(String idCarona){
		if(idCarona==null){
			throw new IllegalAccessError("Carona Inv�lida");
		}
		else if( idCarona.isEmpty()){
			throw new IllegalAccessError("Carona Inexistente");
		}
		else if(!(this.caronaDoSistema.containsKey(idCarona))){
			throw new IllegalAccessError("Carona Inexistente");
		}
	}
	private void verificaIdCarona(String idCarona){
		if(idCarona== null || idCarona.isEmpty()){
			throw new IllegalAccessError("Identificador do carona � inv�lido");
		}
		else if (!(this.caronaDoSistema.containsKey(idCarona))){
			throw new IllegalAccessError("Item inexistente");
		}
	}
	private void verificAtributo(String atributo){
		if(atributo== null || atributo.isEmpty()){
			throw new IllegalAccessError("Atributo inv�lido");
		}
	}
	
	public List<Carona> fazListaCaronaUser(Usuario user){
		List<Carona> resultado= new ArrayList<Carona>();
		 for (String carona : user.getCarona()) {
			resultado.add(this.caronaDoSistema.get(carona));
		}
		 return resultado;
	}
	
	
	
	public String getTrageto(String idCarona){
		verificaTrajeto(idCarona);
		return this.caronaDoSistema.get(idCarona).getTrajeto();
	}
	
	private void verificaTrajeto(String idCarona){
		if(idCarona== null){
			throw new IllegalAccessError("Trajeto Inv�lida");
		}
		else if(idCarona.isEmpty() || !(this.caronaDoSistema.containsKey(idCarona))){
			throw new IllegalAccessError("Trajeto Inexistente");
		}
	}
	
	private void verificaOrigem(String origem ){
		if (origem == null
				|| origem
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%�&*0-9].*")) {
			throw new IllegalArgumentException("Origem inv�lida");
		}
		
	}
	private void verificaDestino(String destino ){
		if (destino == null
				|| destino
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%�&*0-9].*")) {
			throw new IllegalArgumentException("Destino inv�lido");
		}
		
	}
	private void criaDataEHoraAtual(){
		setDatAtual(new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime()));
		
		setHorAtual(new SimpleDateFormat("HH:mm").format(Calendar
				.getInstance().getTime()));
	}
	
	public String getDatAtual() {
		return datAtual;
	}


	public void setDatAtual(String datAtual) {
		this.datAtual = datAtual;
	}


	public String getHorAtual() {
		return horAtual;
	}


	public void setHorAtual(String horAtual) {
		this.horAtual = horAtual;
	}


	public Map<String, Carona> getCaronaDoSistema() {
		return caronaDoSistema;
	}
	public List<String> buscaCarona(String origem, String destino){
		List<String> resultado = new ArrayList<String>();
		verificaOrigem(origem);
		verificaDestino(destino);
		for (Carona carona : this.caronaDoSistema.values()) {			
			 if(origem.isEmpty() && destino.isEmpty()){
					resultado.add(carona.getIdCarona());			
			 }
			 else if(origem.isEmpty() && carona.getDestino().toLowerCase().equals(destino.toLowerCase())){
						 resultado.add(carona.getIdCarona());
	 
			 }			 			 
			 else if (carona.getOrigem().toLowerCase().equals(origem.toLowerCase()) && destino.isEmpty()){
						 resultado.add(carona.getIdCarona());		

			 }
			 else {
				 if(carona.getOrigem().toLowerCase().equals(origem.toLowerCase())){
					 if(carona.getDestino().toLowerCase().equals(destino.toLowerCase())){

							 resultado.add(carona.getIdCarona());						 
					 }
			 }
			 }	
	}
		Collections.sort(resultado);
		return resultado;
}
	
	public String addPonto(String pontos, String idUser, String idCarona){
		PontoEncontro p = new PontoEncontro(pontos, idUser, idCarona);
		this.caronaDoSistema.get(idCarona).addPonto(p);
		return p.getIdponto();
	}
	
	public void repostaPonto(String idUser, String idCarona,String idPonto, String ponto){
		this.caronaDoSistema.get(idCarona).resposta(idPonto, ponto);
	}
}
