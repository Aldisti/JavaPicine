import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

class AsciiArt {

	private BufferedImage	image;
	private int[][]			mat;

	public	AsciiArt(String path) throws IOException {
		this.image = ImageIO.read(new File(path));
	}

	public void	analyze() {
		int	color;

		this.mat = new int[image.getWidth()][image.getHeight()];
		for (int y = 0; y < this.mat.length; y++) {
			for (int x = 0; x < this.mat[y].length; x++) {
				color = this.image.getRGB(y, x);
				if (color == Color.BLACK.getRGB()) {
					this.mat[y][x] = 1;
				}
				else {
					this.mat[y][x] = 0;
				}
			}
		}
	}

	public int[][]	getMat() {
		if (this.mat == null) {
			this.analyze();
		}
		return (this.mat);
	}

	public void	draw(char[] chars) {
		if (this.mat == null) {
			this.analyze();
		}
		for (int y = 0; y < this.mat.length; y++) {
			for (int x = 0; x < this.mat[y].length; x++) {
				System.out.print(chars[this.mat[y][x]]);
			}
			System.out.println();
		}
	}
}

