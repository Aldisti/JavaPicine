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

	private static void	rotateClockwise(int[][] mat) {
		int	tmp;
		int	n = mat.length;

		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				tmp = mat[i][j];
				mat[i][j] = mat[n - 1 - j][i];
				mat[n - 1 - j][i] = mat[n - 1 - i][n - 1 - j];
				mat[n - 1 - i][n - 1 - j] = mat[j][n - 1 - i];
				mat[j][n - 1 - i] = tmp;
			}
		}
	}

	private static void	mirror(int[][] mat) {
		int	tmp;
		int	n;

		for (int y = 0; y < mat.length; y++) {
			n = mat[y].length - 1;
			for (int x = 0; x < mat[y].length / 2; x++) {
				tmp = mat[y][x];
				mat[y][x] = mat[y][n - x];
				mat[y][n - x] = tmp;
			}
		}
	}

	public void	analyze() {
		this.mat = new int[image.getHeight()][image.getWidth()];
		for (int y = 0; y < this.mat.length; y++) {
			for (int x = 0; x < this.mat[y].length; x++) {
				if (this.image.getRGB(x, y) == Color.BLACK.getRGB()) {
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

