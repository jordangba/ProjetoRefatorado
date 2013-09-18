package PersistenciaXML;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Controller.ControllerGeral;
import Model.Usuario;

public class Persistencia {

	private String NOME_DO_ARQUIVO;
	
	public Persistencia(String NOME_DO_ARQUIVO){
		this.NOME_DO_ARQUIVO = NOME_DO_ARQUIVO;
	}
	
	public void persistirDados(ControllerGeral controladora) throws IOException {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(NOME_DO_ARQUIVO);
			out = new ObjectOutputStream(fos);
			out.writeObject(controladora);
			out.close();
			System.out.println("Passei no persistir dados");
		} catch (IOException e) {
			System.out.println("deu errado");
			System.err.println(e.getMessage());
					}
	}

	public ControllerGeral lerDados(ControllerGeral controladora) throws IOException {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(NOME_DO_ARQUIVO);
			in = new ObjectInputStream(fis);
			controladora = (ControllerGeral) in.readObject();
			in.close();
		} catch (IOException ex) {
			controladora = new ControllerGeral();
			persistirDados(controladora);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		for (Usuario user : controladora.getUsuarios().getUsuarioSistema().values()) {
			System.out.println(user.getLogin());
		}
		return controladora;
	}

}
