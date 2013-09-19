package Model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Carona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String origem = "";
	private String destino = "";
	private String idCarona = "";
	private String data = "";
	private String hora = "";
	private int vagas = 0;
	private String dataAtual = "";
	private String horaAtual = "";
	private Usuario idUser;
	private List<PontoEncontro> pontos;
	private List<String> solicitacoesConfirmadas;

	public Carona(Usuario idUser, String origem, String destino, String data,
			String hora, int vagas) {
		setOrigem(origem);
		setDestino(destino);
		setData(data);
		setHora(hora);
		setVagas(vagas);
		this.idUser = idUser;
		criaDataEHoraAtual();
		setIdCarona(this.idUser
				+ Integer.toString(this.idUser.getCarona().size()));
		checaHoraEData(data, hora);
		pontos = new ArrayList<PontoEncontro>();
		this.solicitacoesConfirmadas= new ArrayList<String>();
		this.idUser.addCarona(this.idCarona);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((dataAtual == null) ? 0 : dataAtual.hashCode());
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result
				+ ((horaAtual == null) ? 0 : horaAtual.hashCode());
		result = prime * result
				+ ((idCarona == null) ? 0 : idCarona.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		return result;
	}

	public List<PontoEncontro> getPontos() {
		return pontos;
	}

	public List<String> getSolicitacoesConfirmadas() {
		return solicitacoesConfirmadas;
	}

	public void addPonto(PontoEncontro ponto) {
		pontos.add(ponto);
	}

	public void resposta(String idPonto, String ponto) {
		for (PontoEncontro pont : pontos) {
			if (pont.getIdponto().equals(idPonto)) {
				pont.setPonto(ponto);
			}
		}
	}

	public Usuario getUser() {
		return idUser;
	}

	public void setIdCarona(String idCarona) {
		this.idCarona = idCarona;
	}

	private void criaDataEHoraAtual() {
		setDataAtual(new SimpleDateFormat("dd/MM/yyyy").format(Calendar
				.getInstance().getTime()));

		setHoraAtual(new SimpleDateFormat("HH:mm").format(Calendar
				.getInstance().getTime()));
	}

	private void verificaOrigem(String origem) {
		if ((origem == null)
				|| (origem
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%ï¿½&*0-9].*"))
				|| (origem.trim().equals(""))) {
			throw new IllegalArgumentException("Origem inválida");
		}
	}

	private void verificaDestino(String destino) {
		if ((destino == null)
				|| (destino
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%ï¿½&*0-9].*"))
				|| (destino.trim().equals(""))) {
			throw new IllegalArgumentException("Destino inválido");
		}
	}

	private void verificaVagas(int vagas) {
		if (vagas < 1) {
			throw new IllegalArgumentException("Vaga inválida");
		}
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public void setHoraAtual(String horaAtual) {
		this.horaAtual = horaAtual;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		verificaOrigem(origem);
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		verificaDestino(destino);
		this.destino = destino;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		checaData(data);
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		checaHoraInvalida(hora);
		this.hora = hora;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		verificaVagas(vagas);
		this.vagas = vagas;
	}

	public String getIdCarona() {
		return idCarona;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public String getHoraAtual() {
		return horaAtual;
	}

	private void checaHoraInvalida(String hora) {
		if (hora == null || hora.isEmpty() || hora.matches("[a-zA-Z]*")) {
			throw new IllegalArgumentException("Hora inválida");
		}
		String[] listaHoraMinuto = hora.split(":");
		try {
			int horas = Integer.parseInt(listaHoraMinuto[0]);
			int minutos = Integer.parseInt(listaHoraMinuto[1]);
			if ((horas >= 24) || (minutos >= 60)) {
				throw new IllegalArgumentException("Hora inválida");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Hora inválida");
		}
	}

	private void checaData(String stringData) {
		if (stringData == null || stringData.isEmpty()) {
			throw new IllegalArgumentException("Data inválida");
		}

		Calendar data = Calendar.getInstance();
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			formato.setLenient(false);
			data.setTime(formato.parse(stringData));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Data inválida");
		}

		Calendar dataAtual = Calendar.getInstance();
		data.add(Calendar.HOUR_OF_DAY, 24);
		data.add(Calendar.MINUTE, 59);
		if (dataAtual.getTime().compareTo(data.getTime()) == 1) {
			throw new IllegalArgumentException("Data inválida");
		}
	}

	private void checaHoraEData(String data, String hora) {
		if (this.dataAtual.toString().compareTo(data.toString()) == 0) {
			if (this.horaAtual.toString().compareTo(hora.toString()) > 0) {
				throw new IllegalArgumentException("Hora inválida");
			}
		}
	}

	public String getTrajeto() {
		return this.origem + " - " + this.destino;
	}

}
