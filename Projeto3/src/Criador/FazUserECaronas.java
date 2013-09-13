package Criador;
import java.util.*;

import javax.naming.directory.InvalidAttributeIdentifierException;

import Controller.ControllerGeral;
public class FazUserECaronas {

	private static Random gerador = new Random();
	private static String[] origem= {"Travessa Antônio Bernadino de Sena", "Travessa do Juá", "Travessa Cantor Evaldo Braga", "Travessa Estácio de Sá", " Travessa Gonçalves Dias"
			, "Travessa Joaquim Azevedo", "Travessa Maria Lucena Barbosa", " Travessa Professora Djanira Tavares", " Travessa Severino Pimentel", "Travessa São Francisco", "Avenida Aeroclube", "Avenida Amazonas",
			"Avenida Brasil", "Avenida Bélgica","Avenida Cajazeiras", "Avenida Canal", "Avenida Confederação Suíça", "Avenida Deputado Raimundo Asfora", "Avenida Elpídio de Almeida", "Avenida Engenheiro José Celino Filho"};
	
	private static String[] destino= {"Avenida Estados Unidos","Avenida Flórida", "Avenida Francisco Amaro de Lima", "Avenida Francisco Lopes de Almeida", "Avenida Grão-Ducado de Luxemburgo", "Avenida Henrique Alexandrino de Melo",
		"Avenida Holanda", "Avenida Industrial Ademar Veloso da Silveira", "Avenida Internacional", "Avenida Itália", "Avenida Iugoslávia", "Avenida Janúncio Ferreira", "Avenida Joaquim Caroca", "Avenida José Hamilton Alves", "Avenida João Wallig",
		"Avenida Nicarágua", "Avenida Nova Zelândia", "Avenida Plínio Lemos", "Avenida Prefeito Severino Bezerra Cabral", "Avenida Reino Unido da Grã-Bretanha"};
	
	public static  String pegaOrigem(){
		return origem[gerador.nextInt(20)];
	}
	
	public static  String pegaDestino(){
		return destino[gerador.nextInt(20)];
	}
	
	public static String criarData(){
		
		int dia =gerador.nextInt(27);
		int mes= gerador.nextInt(12);
		String ano= "2014";
		if( dia==0 || mes ==0){
			criarData();
		}
		return Integer.toString(dia+1)+"/"+Integer.toString(mes+1)+"/"+ano;
	}
	
	public static  String criarHora(){
		int hora =gerador.nextInt(22);
		int minuto= gerador.nextInt(51);
		return Integer.toString(hora+1)+":"+Integer.toString(minuto+1);
	}
	
	public static  String completoEmail(){
		return "@gmail.com"; 
	}
	
	public static  String completoLogin(){
		return "Usuario";
	}
	public static void gerarUserECaronas(){
		ControllerGeral colocador= new ControllerGeral();
		for (int i = 0; i < 500; i++) {
			try {
				colocador.criarUsuario(completoLogin()+Integer.toString(i), FazUserECaronas.completoLogin()+Integer.toString(i), 
						FazUserECaronas.completoLogin()+Integer.toString(i), FazUserECaronas.completoLogin()+Integer.toString(i), FazUserECaronas.completoLogin()+
						Integer.toString(i)+FazUserECaronas.completoEmail());
			} catch (InvalidAttributeIdentifierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			colocador.cadastrarCarona(FazUserECaronas.completoLogin()+Integer.toString(i), FazUserECaronas.pegaOrigem(), FazUserECaronas.pegaDestino(), 
				FazUserECaronas.criarData(), FazUserECaronas.criarHora(), Integer.toString(4));
			colocador.cadastrarCarona(FazUserECaronas.completoLogin()+Integer.toString(i), FazUserECaronas.pegaOrigem(), FazUserECaronas.pegaDestino(), 
					FazUserECaronas.criarData(), FazUserECaronas.criarHora(), Integer.toString(4));
		}
	
	}


}
