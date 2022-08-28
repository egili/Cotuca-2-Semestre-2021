//package Cliente;

//import ClassesComuns.*;

public class TratadoraDeComunicados extends Thread {
	private Parceiro servidor;

	public TratadoraDeComunicados(Parceiro servidor) throws Exception {
		if (servidor == null)
			throw new Exception("Porta invalida");

		this.servidor = servidor;
	}

	public void run() {
		Comunicado comunicado = null;
		for (;;) {
			try {
				comunicado = servidor.espie(); // Permissao
			} catch (Exception e) {
			}
			try {
				if (comunicado instanceof ComunicadoDeDesligamento) {
					System.err.println("\nO servidor vai ser desligado agora..");
					System.err.println("Volte mais tarde!\n");
					servidor.adeus();
					System.exit(0);
				} else if (comunicado instanceof ComunicadoDeFimDeJogo) {
					System.err.println("\nA partida foi encerrada...");
					((Parceiro) servidor).adeus();
					System.exit(0);

				} else if (comunicado instanceof ComunicadoDeVitoria) {
					System.out.println("voce venceu a partida");
					servidor.envie();

				} else if (comunicado instanceof ComunicadoDePerda) {
					System.out.println("\n voce perdeu e sera desconectado do servidor");

					System.out.println("\n obrigado e ate a proxima");

					servidor.envie();

				}
			} catch (Exception erro) {
			}
		}
	}

	@Override
	public String toString() {
		return "Servidor: " + servidor;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		TratadoraDeComunicados tratadora = (TratadoraDeComunicados) obj;

		return this.servidor.equals(tratadora.servidor);
	}

	public int hashCode() {
		int ret = 258;

		ret = ret * 11 + servidor.hashCode();

		return Math.abs(ret);
	}
}
