package ContarVocales;

public class ContadorHilo{
	int vocales;
	String text;
	public ContadorHilo(int vocales, String text) {
		super();
		this.vocales = vocales;
		this.text = text;
	}
	public synchronized int get() {
		return vocales;
	}
	public synchronized void add() {
		vocales++;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ContadorHilo(String text) {
		super();
		this.text = text;
	}
	
}
