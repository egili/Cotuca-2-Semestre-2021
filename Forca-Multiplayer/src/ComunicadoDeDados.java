//package ClassesComuns;
//
//import Servidor.*;

public class ComunicadoDeDados extends Comunicado {
	private Palavra palavra;
	private Tracinhos tracinhos;
	private ControladorDeLetrasJaDigitadas controladorDeLetrasJaDigitadas;
    private ControladorDeErros controladorDeErros;
    
	public ComunicadoDeDados() {
		try { // Inicia os dados do Jogo da forca
			this.palavra = BancoDePalavras.getPalavraSorteada();
			this.tracinhos = new Tracinhos(palavra.getTamanho());
			this.controladorDeLetrasJaDigitadas = new ControladorDeLetrasJaDigitadas();
			 this.controladorDeErros = new ControladorDeErros ((int)(palavra.getTamanho()*0.6));
			 
		} catch (Exception err) {
			System.err.println("erro construtor");
			err.printStackTrace();
		}
	}

	public Palavra getPalavra() {
		return palavra;
	}

	public void setPalavra(Palavra palavra) {
		this.palavra = palavra;
	}

	public Tracinhos getTracinhos() {
		return tracinhos;
	}

	public void setTracinhos(Tracinhos tracinhos) {
		this.tracinhos = tracinhos;
	}

	public ControladorDeLetrasJaDigitadas getControladorDeLetrasJaDigitadas() {
		return controladorDeLetrasJaDigitadas;
	}

	public void setControladorDeLetrasJaDigitadas(ControladorDeLetrasJaDigitadas controladorDeLetrasJaDigitadas) {
		this.controladorDeLetrasJaDigitadas = controladorDeLetrasJaDigitadas;
	}

	public Palavra getCopiaPalavra() throws Exception {

		if (this.palavra == null)
			throw new Exception("copia nula");

		return new Palavra(this.palavra);
	}
	
	public int getPosicaoIezima(int x, char letra) throws Exception{
		
		if(this.palavra == null)
			throw new Exception("posicao inexistente");
		
		return palavra.getPosicaoDaIezimaOcorrencia(x, letra);
	}
	
    public ControladorDeErros getControladorDeErros() {
        return controladorDeErros;
    }

    public void setControladorDeErros(ControladorDeErros controladorDeErros) {
        this.controladorDeErros = controladorDeErros;
    }

}
