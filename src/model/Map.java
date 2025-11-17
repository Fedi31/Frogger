package model;

public class Map{
	private Size size;
	
	public Map(Size size) {
		this.size = size;
	}
	
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
