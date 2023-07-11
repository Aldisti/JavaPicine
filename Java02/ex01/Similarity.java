import java.io.*;
import java.util.*;
import java.lang.Math;

class Similarity {
	private File					file1;
	private File					file2;
	private Map<String, Integer>	dict;
	private int[]					v1;
	private int[]					v2;

	public	Similarity(File file1, File file2) {
		this.file1 = file1;
		this.file2 = file2;
	}

	public void	merge() {
		Map<String, Integer>	tmp;

		this.dict = new HashMap<>();
		tmp = file1.getDict();
		for (String key : tmp.keySet()) {
			this.dict.merge(key, tmp.get(key), Integer::sum);
		}
		tmp = file2.getDict();
		for (String key : tmp.keySet()) {
			this.dict.merge(key, tmp.get(key), Integer::sum);
		}
	}

	public void	makeVectors() {
		int						i;
		int						len;
		Map<String, Integer>	tmp;

		if (this.dict == null) {
			this.merge();
		}
		len = this.dict.size();
		i = 0;
		this.v1 = new int[len];
		tmp = this.file1.getDict();
		for (String key : this.dict.keySet()) {
			if (tmp.get(key) != null) {
				this.v1[i++] = tmp.get(key);
			}
			else {
				this.v1[i++] = 0;
			}
		}
		i = 0;
		this.v2 = new int[len];
		tmp = this.file2.getDict();
		for (String key : this.dict.keySet()) {
			if (tmp.get(key) != null) {
				this.v2[i++] = tmp.get(key);
			}
			else {
				this.v2[i++] = 0;
			}
		}
	}

	public double	similarity() {
		double	n = 0;
		double	tmp1 = 0;
		double	tmp2 = 0;

		if (this.v1 == null || this.v2 == null) {
			this.makeVectors();
		}

		for (int i = 0; i < v1.length; i++) {
			n += this.v1[i] * this.v2[i];
			tmp1 += this.v1[i] * this.v1[i];
			tmp2 += this.v2[i] * this.v2[i];
		}
		return (n / (Math.sqrt(tmp1) * Math.sqrt(tmp2)));
	}
}
