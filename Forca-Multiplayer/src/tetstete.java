/*

int qtd = palavra.getPosicaoDaIezimaOcorrencia(posicaodaletra,letra);

try {
				if (opcao == 1) {
					System.out.println("Digite uma letra" + letra);
					PedidoDeLetra pedidodeletra = new PedidoDeLetra();
					servidor.receba(pedidodeletra);

					comunicado = null;
					do {
						comunicado = (Comunicado) servidor.espie();
					} while (!(comunicado instanceof PedidoDeLetra));
					if(comunicado instanceof ComunicadoComecouPartida)
					{
						Palavra palavra =BancoDePalavras.getPalavraSorteada();
						Tracinhos tracinhos = null;
						
						ComunicadoDeDados comunicadoDados = null;
						//Palavra copiapalavra = palavra;
						
						comunicadoDados.getCopiaPalavra();
						
						String letrasJaDigitadas = null;
								try
								{
								    tracinhos = new Tracinhos (palavra.getTamanho());
			     				}
								
                             catch(Exception error){}
				         {
				         if((controladorDeLetrasJaDigitadas.isJaDigitada(letra) == true) &&(comunicado instanceof ComunicadoDeLetraJaDigitada))
				        	System.out.println("A letra" + letra + "ja foi digitada");
				         comunicado = (ComunicadoDeLetraJaDigitada)servidor.envie();
				         copiapalavra.getQuantidade(letra);
				         System.out.println(qtd);
				         controladorDeLetrasJaDigitadas.registreletra(letra);
				         
				     	for (int i=0; i<qtd; i++)
						{
							int posicao = copiapalavra.getPosicaoDaIezimaOcorrencia (i,letra);
							tracinhos.revele (posicao, letra);
						}

				         int i = 0 ;

						if((copiapalavra.getQuantidade(letra) > 0) && ((copiapalavra.getPosicaoDaIezimaOcorrencia (i,letra)>0)))
				         {
				           do
				           { 
				        	   comunicado = (Comunicado) servidor.espie();
				           }
				           while (!(comunicado instanceof ComunicadoDeAcerto));
				        	   if ((comunicado instanceof ComunicadoDeDados) && (comunicado instanceof ComunicadoDeVez))
				        		   controladoraDePartida.getJogadores();
				                    
				               comunicado = (ComunicadoDeAcerto) servidor.envie();
				               System.out.println("Jogador" + suavez.getposicaodoJogador() + "acertou a letra!");
				               comunicado = (ComunicadoDeDados) servidor.envie();
				               System.out.println(controladoraDePartida.getJogadores());
         				       System.out.println(comunicadodedados.getTracinhos());
         				       tracinhos.revele(opcao,letra);
				               System.out.println(tracinhos.isAindaComTracinhos());
				               System.out.println("Palavra:" + tracinhos);
				               System.out.println("Digitadas.: "  + letrasJaDigitadas.toString());
				               comunicado = (ComunicadoDeVez) servidor.envie();
       			               System.out.println("Eh a vez do proximo jogador");
       			               controladoraDePartida.toString();
       			               controladoraDePartida.proximoJogador();
				         }
						// Quando o jogador ativo completa a palavra por acertar a letra ele ganha (comunicado de vitoria)
						//e os demais perdem (comunicados de perda);
	                  if ((copiapalavra.getQuantidade(letra)>0) && (copiapalavra.getPosicaoDaIezimaOcorrencia(i, letra) > 0 ) 
	    		        && (tracinhos.isAindaComTracinhos() == false))
	                   {
		                do
                       { 
      	               comunicado = (Comunicado) servidor.espie();
                       }
                       while (comunicado instanceof ComunicadoDeAcerto);
		                
      	               if ((comunicado instanceof ComunicadoDeDados) && (comunicado instanceof ComunicadoDeVitoria))
	                   controladoraDePartida.getJogadores();
		                   
		       			          comunicado = (ComunicadoDeAcerto) servidor.envie();
                      System.out.println("Jogador" + suavez.getposicaodoJogador() + "acertou a letra!");
                      comunicado = (ComunicadoDeDados) servidor.envie();
                      System.out.println(controladoraDePartida.getJogadores());
		              System.out.println(comunicadodedados.getTracinhos());
		              tracinhos.revele(opcao,letra);
                      System.out.println(tracinhos.isAindaComTracinhos());
                      System.out.println("Palavra:" + tracinhos);
                      System.out.println("Digitadas.: "  + letrasJaDigitadas.toString());
                      comunicado = (ComunicadoDeVitoria) servidor.envie();
                      System.out.println("Demais jogadores saem do jogo");
                      comunicado = (ComunicadoDePerda) servidor.envie();
	                  controladoraDePartida.podeJogar(servidor);
	                  grupo.getJogadorDaVez();
	                  controladoraDePartida.fimThreadSupervisora();
	                  comunicado = (ComunicadoDeDesligamento) servidor.envie();
	                  controladoraDePartida.toString();
	                  controladoraDePartida.getJogadores();
	           }     
	         
						else if((copiapalavra.getQuantidade(letra) == 0) && ((copiapalavra.getPosicaoDaIezimaOcorrencia (i,letra) == 0)))
						  
					           do
					           { 
					        	   comunicado = (Comunicado) servidor.espie();
					           }
					           while (comunicado instanceof ComunicadoDeErro);
					        	   if ((comunicado instanceof ComunicadoDeDados) && (comunicado instanceof ComunicadoDeVez))
						                  controladoraDePartida.getJogadores();
					                      
					               comunicado = (ComunicadoDeErro) servidor.envie();
					               System.out.println("Jogador" + suavez.getposicaodoJogador() + "errou a letra!");
					               comunicado = (ComunicadoDeDados) servidor.envie();
					               System.out.println(controladoraDePartida.getJogadores());
					               System.out.println(comunicadodedados.getTracinhos());
	         				       tracinhos.revele(opcao,letra);
					               System.out.println(tracinhos.isAindaComTracinhos());
					               System.out.println("Palavra:" + tracinhos);
					               System.out.println("Digitadas.: "  + letrasJaDigitadas.toString());
					               comunicado = (ComunicadoDeVez) servidor.envie();
	       			               System.out.println("Eh a vez do proximo jogador");
	       			               controladoraDePartida.toString();
	       			               controladoraDePartida.proximoJogador();
	       			               
	       			               
       			         }

						
				         
				}
			}
			}
			
			catch (Exception erro) {
			}

			
			if (opcao == 2)
			{
				
			    String chutepalavra = Teclado.getUmString();
			    
				System.out.println("Digite uma Palavra" + chutepalavra);
				
				chutepalavra = Teclado.getUmString();
				
				servidor.receba(new PedidoDePalavra());
				
				comunicado = null;
				do {
					comunicado = (Comunicado) servidor.espie();
				}

				while (!(comunicado instanceof ComunicadoDeResultadoPalavra));

					if((controladordePalavrasJaDigitadas.isJaDigitada(chutepalavra) == true) &&(comunicado instanceof ComunicadoDePalavraJaDigitada))
						
						
						 //verificar se tem um equals para comparar a palavra digitada com a palavra informada 
						
						System.out.println("A palavra: " + chutepalavra + "ja foi tentada antes, tente novamente, por favor");
					 comunicado = (ComunicadoDePalavraJaDigitada)servidor.envie();
					

				
				if ((comunicado instanceof ComunicadoDePerda) && (comunicado instanceof ComunicadoDeVitoria))
				{
					controladoraDePartida.getJogadores();
				
					
					
					if (chutepalavra.equals(palavra))
					comunicado = (ComunicadoDeAcerto) servidor.envie();
					
					
		               System.out.println("Jogador" + suavez.getposicaodoJogador()  + "acertou a Palavra!");
		               comunicado = (ComunicadoDeDados) servidor.envie();
		               
		               
		               try
						{
							servidor.receba(new PedidoParaSair());
						}
						catch (Exception error) {}

						System.exit(0);
		               
		            
					
				}
				
				else if(!chutepalavra.equals(palavra))
				  {
										
			           do
			           { 
			        	   comunicado = (Comunicado) servidor.espie();
			           }
			           while (!(comunicado instanceof ComunicadoDeErro));
			        	   if ((comunicado instanceof ComunicadoDeDados) && (comunicado instanceof ComunicadoDeVez))
				                  controladoraDePartida.getJogadores();
	                                                  
			               comunicado = (ComunicadoDeErro) servidor.envie();
			               System.out.println("Jogador" + suavez.getposicaodoJogador() + "errou a palavra!");
			               
			               String palavraJaDigitada = chutepalavra; //fazer na classe pedido de palavra para armazenar a palavra ja digitada e depois exibir igual as letras
			               
			               
			               // errou a palavra e saiu do jogo
			               
			               System.out.println("Você errou a palavra e está fora da partida.");
			               comunicado = (ComunicadoDePerda) servidor.envie();
			               
			               comunicado = (ComunicadoDeVez) servidor.envie();
			               comunicado = (ComunicadoDeDados) servidor.envie();
			               controladoraDePartida.proximoJogador();
		                
//			               try
//							{
//								servidor.receba(new PedidoParaSair());
//							}
//							catch (Exception error) {}
//			               
//			               
//
//							System.exit(0); */
