package model;

public class Map{
	private Size size;
	
	public Map(Size size) {
		this.size = size;
	}
	
	/* costruttore alternativo che riceve altezza e larghezza come interi, e crea un nuovo oggetto Size da essi. */
	public Map(int width, int height) {
    this(new Size(width, height));
	}
	
	public Size getSize() {
		return size;
	}
	
	public int getWidth() {
	    return size.getWidth();
	}

	public int getHeight() {
	    return size.getHeight();
	}

}
