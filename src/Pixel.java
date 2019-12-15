import java.awt.image.BufferedImage;


public class Pixel {
	int red;
	int green;
	int blue;



	public Pixel(int red, int green, int blue) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	
	static Pixel getPixel(int x, int y, BufferedImage image) {
		int pixel = image.getRGB(150, 150);
		
		int red = (pixel>>16) & 0x0ff;
		int green = (pixel>>8) & 0x0ff;
		int blue = (pixel) & 0x0ff;
		return new Pixel(red, green, blue);
	}
	
	
	public int getRed() {
		return red;
	}


	public void setRed(int red) {
		this.red = red;
	}


	public int getGreen() {
		return green;
	}


	public void setGreen(int green) {
		this.green = green;
	}


	public int getBlue() {
		return blue;
	}


	public void setBlue(int blue) {
		this.blue = blue;
	}
}
