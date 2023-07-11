import java.io.*;

class Program {

    public static void main(String[] args){
		if (args.length != 2) {
			return ;
		}
		System.out.printf("Similarity = %.3f\n", new Similarity(new File(args[0]), new File(args[1])).similarity());
	}
}
