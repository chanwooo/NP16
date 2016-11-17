/*	
  	read form keyboard input and print the input string to the screen!
	20160920_b089020_chanwoo
*/

package practice1_stream;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer; 

public class practice1_stream {

	public static void main(String[] args) throws IOException {
		//mykbd2scr();
		//p1kbd2scr();
		//p2bufkbd2scr();
		//p3kbd2file();
		//p4file2scr(args[0]);
		p5file2scr2(args[0]);
	}




	public static void mykbd2scr() throws IOException {
		
		System.out.print("input : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		System.out.println("output : "+br.readLine());
	}
		
	
	
	public static void p1kbd2scr() throws IOException {
		byte[] linebuf = new byte[1024];
		int n;
		
	 
			while((n = System.in.read(linebuf))>=0)
				System.out.write(linebuf,0,n);
	
	
	}

	
	
	public static void p2bufkbd2scr() throws IOException {
	
	
		String linebuf;
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		while((linebuf = stdin.readLine())!=null)
		{
			System.out.println(linebuf);
		}
		
	
	}

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
	

	public static void p5file2scr2(String filename) throws IOException {

		String linebuf;
		
		BufferedReader infile = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"unicode"));
		PrintWriter outfile = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out,"utf-8")));
		
		
		while((linebuf = infile.readLine()) != null){
			System.out.println(linebuf);
		}
		
		infile.close();
	}

}