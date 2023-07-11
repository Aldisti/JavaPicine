import java.util.*;
import java.io.*;

class File {
	private String					path;
	private FileReader				file;
	private BufferedReader			buf;
	private Map<String, Integer>	dict;
	private String[]				words;
	private int[]					occ;
	private char[]					PUNCTUATION = {'.', ',', ':', ';', '!', '?', '\'', '\"', '(', ')'};

	public	File(String path) {
		this.path = path;
		this.dict = new HashMap<>();
		this.words = null;
		this.occ = null;
	}

	public void			analyze() {
		String		line;
		String[]	tmp;

		try {
			this.file = new FileReader(this.path);
			this.buf = new BufferedReader(file);
			while ((line = this.buf.readLine()) != null) {
				for (int i = 0; i < PUNCTUATION.length; i++) {
					line = line.replace(PUNCTUATION[i], ' ');
				}
				tmp = line.split("\\s+");
				for (int i = 0; i < tmp.length; i++) {
					this.dict.merge(tmp[i], 1, Integer::sum);
				}
			}
			this.file.close();
		}
		catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
	}

	public Map<String, Integer>	getDict() {
		if (this.dict.size() == 0) {
			this.analyze();
		}
		return (this.dict);
	}

	public String[]		getWords() {
		int	i = 0;

		if (this.words != null && this.words.length == this.dict.size()) {
			return (this.words);
		}
		this.words = new String[this.dict.size()];
		for (String key : this.dict.keySet()) {
			this.words[i++] = key;
		}
		return (this.words);
	}

	public int[]		getOcc() {
		int	i = 0;

		if (this.occ != null && this.occ.length == this.dict.size()) {
			return (this.occ);
		}
		this.occ = new int[this.dict.size()];
		for (String key : this.dict.keySet()) {
			this.occ[i++] = this.dict.get(key);
		}
		return (this.occ);
	}
}

