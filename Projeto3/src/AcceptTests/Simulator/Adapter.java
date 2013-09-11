package AcceptTests.Simulator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adapter {

	public static String ColecaoParaString(Collection<String> collection) {
		if (collection.size() == 0)
			return "{}";
		List<String> lista = new ArrayList<String>(collection);
		String retorno = "";
		for (String numero : lista) {
			retorno = retorno + numero + ",";
		}
		return "{" + retorno.substring(0, retorno.length() - 1) + "}";
	}
	
	public static String adaptadorParaGetAtributoPerfil(String atributo, String resultado){
		if(atributo.equals("historico de caronas")|| atributo.equals("historico de vagas em caronas")){
			return resultado.replace(" ", "");
		}
		return resultado;
	}
}
