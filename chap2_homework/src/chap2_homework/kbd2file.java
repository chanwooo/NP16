package chap2_homework;

import java.io.*;

public class kbd2file {

	public static void p3kbd2file() throws IOException {

		byte[] linebuf = new byte[1024];
		int n;
		
		FileOutputStream outfile = new FileOutputStream("lab02out.txt");
		
			while((n = System.in.read(linebuf))>=0)
				outfile.write(linebuf,0,n);
			outfile.close();	      

	}
	
	public static void p4file2scr(String filename) throws IOException {

		byte[] linebuf = new byte[1024];
		int n;
		
		FileInputStream infile = new FileInputStream(filename);
		
			while((n = infile.read(linebuf))>=0)
				System.out.write(linebuf,0,n);
			infile.close();
			 
	}
	public static void main(String[] args) throws IOException {
	

		p3kbd2file();
		p4file2scr(args[0]);
	}

}
