package model;

public class Map{
	private Size size;
	private int riverTop;
	private int riverBottom;
	
	public Map(Size size, int riverTop, int riverBottom) {
		this.size = size;
		this.riverTop = riverTop;
		this.riverBottom = riverBottom;
	}
	
	public Map(int width, int height, int riverTop, int riverBottom) {
		this(new Size(width, height), riverTop, riverBottom);
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

	// getter per acqua
    public int getRiverTop() {
        return riverTop;
    }

    public int getRiverBottom() {
        return riverBottom;
    }
}
