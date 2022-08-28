//package ClassesComuns;

public class PedidoDePalavra extends Comunicado implements Cloneable{
	
	private String chutedepalavra;	
	public String getPalavra() {
	    return chutedepalavra;
	 }

	public void setPalavra(String chutedepalavra) {
	     this.chutedepalavra = chutedepalavra;
	}	
}