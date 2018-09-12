import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class charServer {
	
	public final static int PORT = 1300;
	static int count=0;
		
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(PORT))	{
			while	(true)	{
				try	{
					Socket s = server.accept();
					Thread task = new client(s);
					task.start();
					
					count++;
					System.out.println(count);

				}catch (IOException e){}
				
			}
		}catch(IOException e){}

	}



private static class client extends Thread	{
	private Socket s;
	client(Socket s) {
		this.s = s;
	}
	
	@Override
	public void run()	{
		try {
			
			String name="0";
			String room="0";

			s.setSendBufferSize(16384);
	        s.setTcpNoDelay(true);
	        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
	        OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
	        out.write("welcome");
	        //set name, room
	        
	        
	        String line = null;
	        
	        while ((line = input.readLine()) != null)
	        {
	        	//System.out.println("test");
	        	out.write(line);
	        	out.write(line+"hello");
	        	out.write("blah");
				out.flush();
	        
	        }
			
		
			}catch(IOException e){}
		finally{
		try{
			s.close();
		}catch(IOException e){
		
		}
		}
	}
}
}

