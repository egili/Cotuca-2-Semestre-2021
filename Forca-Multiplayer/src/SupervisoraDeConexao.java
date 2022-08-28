//package Servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Semaphore;
//import ClassesComuns.*;

public class SupervisoraDeConexao extends Thread {
	private Socket conexao;
	private ControladoraDePartida controladora;
	private Parceiro jogador;
	private ArrayList<Parceiro> jogadores;
	private ObjectOutputStream transmissor;
	private ObjectInputStream receptor;
	public boolean fim = true;
	private ComunicadoDeDados comunicaDados = new ComunicadoDeDados();
	private Comunicado comunicado = new Comunicado();
	private ComunicadoDeComecoPartida comunicadodecomecopartida = new ComunicadoDeComecoPartida();
	private static Palavra palavra;
	private static Tracinhos tracinhos;
	private static ControladorDeErros controladorDeErros;
	private static ControladorDeLetrasJaDigitadas controladoraDeLetras;
	private ComunicadoDeLetraJaDigitada comunicadoDeLetraJaDigitada;
    private char letra = Teclado.getUmChar();
    private ComunicadoDeErro comunicadoDeErro;
    private ComunicadoDeAcerto comunicadoDeAcerto;
    private ComunicadoDeVitoria comunicadoDeVitoria;
    private ComunicadoSalaCheia comunicadoSalaCheia;
    private ComunicadoDePerda comunicadoDePerda;
    private ComunicadoDeFimDeJogo comunicadoDeFimDeJogo;
    private ComunicadoDeResultadoPalavra comunicadoDeResultadoPalavra;
    private ComunicadoDeVez comunicadoDeVez;
    private ComunicadoDeDesligamento comunicadoDeDesligamento;
    private String chutepalavra = Teclado.getUmString();
    private ControladorDePalavrasJaDigitadas controladoraDePalavra;
 
    
	public SupervisoraDeConexao(Socket conexao, ArrayList<Parceiro> usuarios, ControladoraDePartida controladora)
			throws Exception {

		if (conexao == null)
			throw new Exception("Conexao ausente");

		if (usuarios == null)
			throw new Exception("Usuarios ausentes");

		if (controladora == null)
			throw new Exception("Controladora nula");

		this.conexao = conexao;
		this.jogadores = usuarios;
		this.controladora = controladora;
	}

	public void run() {
		try {
			transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
		} catch (Exception erro) {
			return;
		}

		try {
			receptor = new ObjectInputStream(this.conexao.getInputStream());
		} catch (Exception erro) {
			try {
				transmissor.close();
			} catch (Exception falha) {
			}
			return;
		}

		try {
			this.jogador = new Parceiro(this.conexao, receptor, transmissor);
		} catch (Exception erro) {
		}

		try {
			synchronized (jogadores) {
				this.jogadores.add(this.jogador);
				if (jogadores.size() % 3 == 0) {
					Palavra palavra = comunicaDados.getPalavra();
					Palavra copiaPalavra = comunicaDados.getCopiaPalavra();
					Tracinhos tracinhos = comunicaDados.getTracinhos();
					int jogadoresdapartida = jogadores.size();
					if(comunicaDados.getTracinhos().isAindaComTracinhos())
					{
						System.out.println("Palavras: " + tracinhos);
						System.out.println("Digitadas: " + controladoraDeLetras);
					}
				

					for (Parceiro jogador : this.jogadores) {
						jogador.receba(new ComunicadoDeComecoPartida());
					
					}
					ComunicadoDeDados dadosDaForca = new ComunicadoDeDados();
					// para chamar comunicado de vez ai vai da a vez para primeiro jogador
					this.jogadores.get(0).receba(new ComunicadoDeVez(dadosDaForca));
				}
				//if (comunicado == null)
	            	   //return;
	                if (comunicado instanceof PedidoParaEntrar)
	               {
	            	int posJogador = this.jogadores.indexOf(jogador);
	            	if (posJogador > 2)
	            	{
	            	 try {
						((Parceiro) jogador).receba(new ComunicadoSalaCheia());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	 
	            	}
				System.out.println("Infelizmente servidor esta cheio");
			}

			}
		 

		try {
			do {
			} while (!(jogador.espie() instanceof ComunicadoDeVez));

			jogador.envie();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
			for (;;) {
				Comunicado comunicado = this.jogador.envie(); // pega o comunicado o jogador enviou

				if (comunicado == null)
					return;
				else if (comunicado instanceof ComunicadoDeDados) {
					comunicaDados = (ComunicadoDeDados) comunicado;
				}	
				else if (comunicado instanceof PedidoDeLetra) { // TODO: logica do que acontece no servidor qnd o cliente pede uma letra
					boolean jaDigitadas = false;
					boolean palavraTemLetra = false;
					PedidoDeLetra pedidoDeLetra = (PedidoDeLetra) comunicado;
					char letra = pedidoDeLetra.getLetra();
					System.out.println("Qual eh a letra? " + letra);
					if (controladoraDeLetras.isJaDigitada(letra))
					{
						jaDigitadas = true;
						System.err.println("A letra ja foi digitada");
						this.jogador.receba(comunicadoDeLetraJaDigitada);
					} else  {
							comunicaDados.getControladorDeLetrasJaDigitadas().registrarletra(letra);
							int qtd = palavra.getQuantidade(letra);

							if (qtd == 0) {
								System.err.println("A palavra nao tem essa letra!\n");
								comunicaDados.getControladorDeErros().registreUmErro();
								this.jogador.receba(comunicadoDeErro);
							} else {
								for (int i = 0; i < qtd; i++) {
									int posicao = palavra.getPosicaoDaIezimaOcorrencia(i, letra);
									comunicaDados.getTracinhos().revele(posicao, letra);
									
									this.jogador.receba(comunicadoDeAcerto);
									System.out.println("Voce acertou a letra");
									
									if (comunicaDados.getTracinhos().isAindaComTracinhos() == false)
									{
										comunicaDados.getTracinhos().revele(posicao, letra);
										this.jogador.receba(comunicadoDeAcerto);
										System.out.println("Voce acertou a letra");
										this.jogador.receba(comunicadoDeVitoria);
										System.out.println ("Voce acertou a palavra por ter completado as letras");   
									} // fim do if da linha 164
									
								} // fim do for da linha 157
								this.jogador.receba(new ComunicadoDeVitoria());
								 
							}// fim do else da linha 157 depois do else
						} // fim do else da linha 148
					     ComunicadoDeLetra comunicadoDeLetra = new ComunicadoDeLetra();
					     comunicadoDeLetra.setJaDigitada(jaDigitadas);
					     comunicadoDeLetra.setPalavraTemLetra(palavraTemLetra);
					     this.jogador.receba(comunicadoDeLetra);

					}// fim do else da linha 138
				if (comunicado instanceof PedidoDePalavra) {
					PedidoDePalavra pedidoPalavra = (PedidoDePalavra) comunicado;
					String chutepalavra = pedidoPalavra.getPalavra();
                     System.out.println("Qual eh a palavra?" + chutepalavra);
                     if (!controladoraDePalavra.isJaDigitada(chutepalavra))
                    	 controladoraDePalavra.registrepalavra(chutepalavra);
					// Verifica se o jogador acertou ou n�o a palavra na FORCA
					if (palavra.equals(comunicaDados.getPalavra().toString())) {
						// Ele eh avisado da vitoria no jogo
						System.out.println("Voce acertou a palavra");
						jogador.receba(new ComunicadoDeResultadoPalavra(true));
						System.out.println("Voce venceu");
						jogador.receba(comunicadoDeVitoria);

					} else { 
						
						System.out.println("Voce errou a palavra"); 
						jogador.receba(new ComunicadoDeResultadoPalavra(false));
						System.out.println("Voce perdeu");
						jogador.receba(comunicadoDePerda);
						

					} // fim do else da linha 199
					
				}// fim do for da linha 187
					
				else if (comunicado instanceof ComunicadoDeVez) {
					int percorrerVez = this.jogadores.indexOf(this.jogador) + 1;

					if (percorrerVez == 3) {
						percorrerVez = 0;
					}

					this.jogadores.get(percorrerVez).receba(new ComunicadoDeVez(comunicaDados));
				} else if (comunicado instanceof ComunicadoDeVitoria) {
					int percorrerVez = this.jogadores.indexOf(this.jogador) + 1;

					if (percorrerVez == 3) {
						percorrerVez = 0;
					}

					this.jogadores.get(percorrerVez).receba(new ComunicadoDeVez(comunicaDados));
				} // fim do elseif da linha 219
				else if (comunicado instanceof PedidoParaSair) {
					synchronized (this.jogadores) {
						this.jogador.receba(comunicadoDeFimDeJogo);
                        System.out.println ("Voce sera removido do jogo");
						this.jogadores.remove(this.jogador);
					}
					this.jogador.adeus();
				}// fim do elseif da linha 228
				}// fim do for da linha 131
				
		      
			}  catch (Exception erro) {
				try {
					transmissor.close();
					receptor.close();
				} catch (Exception falha) {
				} // tentativa de fechar antes que a thread termine
				return;
			}}


	

	@Override
	public String toString() {
		String ret = "Jogador: " + jogador;
		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		SupervisoraDeConexao supervisora = (SupervisoraDeConexao) obj;

		if (!this.jogador.equals(supervisora.jogador))
			return false;

		if (!this.conexao.equals(supervisora.conexao))
			return false;

		if (!this.controladora.equals(supervisora.controladora))
			return false;

		if (!this.receptor.equals(supervisora.receptor))
			return false;

		if (!this.transmissor.equals(supervisora.transmissor))
			return false;

		if (this.jogadores.size() != supervisora.jogadores.size())
			return false;

		for (int i = 0; i < this.jogadores.size(); i++) {
			if (!this.jogadores.get(i).equals(supervisora.jogadores.get(i)))
				return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int ret = 31;

		ret = ret * 11 + this.controladora.hashCode();
		ret = ret * 11 + this.jogador.hashCode();
		ret = ret * 11 + this.conexao.hashCode();
		ret = ret * 11 + this.receptor.hashCode();
		ret = ret * 11 + this.transmissor.hashCode();

		for (Parceiro cliente : jogadores)
			ret = ret * 11 + cliente.hashCode();

		return ret < 0 ? -ret : ret;
	} // fim do metodo hashcode 
	
}// fim da run da linha 54
	
