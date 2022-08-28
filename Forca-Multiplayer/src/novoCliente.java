import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
/*
public class novoCliente {

	public static final String HOST_PADRAO = "localhost";
	public static final int PORTA_PADRAO = 3000;

	public static void main(String args[]) {

		if (args.length > 2) { // sobre o tamanho do vetor args
			System.err.println("Uso esperado: java app [HOST[PORTA]]");
			return;
		}

		// declarando os objetos que serao instanciados
		Socket conexao = null;
		ObjectOutputStream transmissor = null;
		ObjectInputStream receptor = null;
		Parceiro servidor = null;
		TratadoraDeComunicados tratadoraDeComunicados = null;
		Pedido pedido = null;
		ArrayList<Parceiro> jogadores = null;
		ControladorDeLetrasJaDigitadas controladorDeLetrasJaDigitadas = null;
		Tracinhos tracinhos = null;
		Palavra palavra = BancoDePalavras.getPalavraSorteada();

		try { // instanciado os objetos declarados
			conexao = Instanciacao.instanciarConexao(args);
			transmissor = Instanciacao.instanciarTransmissor(conexao);
			receptor = Instanciacao.instanciarReceptor(conexao);
			servidor = Instanciacao.instanciarServidor(conexao, receptor, transmissor);
			tratadoraDeComunicados = Instanciacao.instanciarTratadora(servidor);
			jogadores = new ArrayList<Parceiro>();
			controladorDeLetrasJaDigitadas = new ControladorDeLetrasJaDigitadas();
			tracinhos = new Tracinhos(palavra.getTamanho());

		} catch (Exception err) {
			System.err.println(err.getMessage());
			System.err.println("Verifique se o servidor esta ativo.\n"
					+ "Se sim, verifique se o servidor e a porta providos estao corretos!\n");
			System.exit(0);
		}

		tratadoraDeComunicados.start(); // inicia a thread responsavel por tratar os comunicados

		System.out.println("Aguarde os jogadores entrarem na partida");

		Comunicado comunicado = null;

		while (!(comunicado instanceof ComunicadoDeComecoPartida)) {

			try {
				comunicado = (Comunicado) servidor.espie();
			} catch (Exception err) {
				System.err.print(err.getMessage());
			}
		}

		try {
			servidor.receba(comunicado);
			comunicado = servidor.envie();
		} catch (Exception e) {
			System.err.print(e.getMessage());
		}

		System.out.println("O jogo comecou!");

		// jogo
		//for (Parceiro jogador : jogadores) {
			while (true) {
				Comunicado partida = null;
				try {
					do {
						partida = servidor.espie();
					} while (!(partida instanceof ComunicadoDeVez));
					partida = servidor.envie();
				} catch (Exception e) {
				}

				System.out.println("\n sua vez de jogar");

				if (partida instanceof ComunicadoDeVez) {
					comunicado = null;
					do {
						try {
							comunicado = servidor.espie();
						} catch (Exception err) {
							System.err.println(err.getMessage() + " Erro ao espiar");
						}
					} while (!(comunicado instanceof Pedido));

					try {
						pedido = (Pedido) servidor.envie();
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}

					Character opcaoChar = ' ';
					String opcaoString = "";

					for (;;) {
						try {
							opcaoChar = Teclado.getUmChar();
							System.out.println("Bem vindo, a sua conexao com o servidor foi aceita");
							System.out.println("digite 1 para digitar a letra!");
							System.out.println("digite 2 para digitar a palavra!");
							System.out.println("\n digite a sua opcao: " + opcaoChar);

							if (!(opcaoChar.equals('1') || opcaoChar.equals('1')))
								throw new Exception("opcao invalida");
							break;
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}

						//

						try {

							//
							if (opcaoChar.equals('1')) {
								servidor.receba(new PedidoDeLetra());

								do {
									comunicado = (Comunicado) servidor.espie();
								} while (!(comunicado instanceof Pedido)); // espera enviar o pedido

								pedido = (Pedido) servidor.envie(); // espera receber o pedido
								System.out.println(pedido.getPedido());

								while (tracinhos.isAindaComTracinhos()) {
									System.out.println("Palavra...: " + tracinhos);
									System.out.println("Digitadas.: " + controladorDeLetrasJaDigitadas);
									do {
										opcaoChar = Teclado.getUmChar();
										System.out.println("digite sua letra: " + opcaoChar);

										if (controladorDeLetrasJaDigitadas.isJaDigitada(opcaoChar))
											System.err.println("Essa letra ja foi digitada!\n");
										else {
											controladorDeLetrasJaDigitadas.registre(opcaoChar);

											int qtd = palavra.getQuantidade(opcaoChar);

											if (qtd == 0) {
												System.err.println("A palavra nao tem essa letra!\n");
											} else {
												for (int i = 0; i < qtd; i++) {
													int posicao = palavra.getPosicaoDaIezimaOcorrencia(i, opcaoChar);
													tracinhos.revele(posicao, opcaoChar);
												}
												System.out.println();
											}
										}

									} while (!Pedido.isPedidoLetra(pedido));
								}
								//

							}
							//
							else if (opcaoChar.equals('2')) {
								servidor.receba(new PedidoDePalavra());

								do {
									if (comunicado instanceof PedidoDePalavra) {
										pedido = (Pedido) servidor.envie();

										System.out.println(pedido.getPedido());
									}
									comunicado = (Comunicado) servidor.espie();
								} while (!(Pedido.isPedidoLetra(pedido)));

								pedido = (Pedido) servidor.envie();

								do {
									opcaoString = Teclado.getUmString().toUpperCase();
									System.out.println("digite sua palavra: " + opcaoString);

								} while (!Pedido.isPedidoPalavra(pedido));
							}
//
						} catch (Exception erro) {
							System.err.println("\n opcao invalida");
						}

						//
					}
					if (!tracinhos.isAindaComTracinhos())
						System.out.println("Parabens! Voce ganhou! A palavra era mesmo " + palavra + "\n");
				}
				try {
					servidor.receba(new PedidoParaSair());
				} catch (Exception erro) {
				}
				System.out.println("Obrigado por usar esse programa !!");
				System.exit(0);
			}
		}
	}
//}
*/