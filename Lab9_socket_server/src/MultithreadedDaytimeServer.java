import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MultithreadedDaytimeServer {
	
	public final static int PORT = 1300;
	int count=0;
		
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(PORT))	{
			while	(true)	{
				try	{
					Socket connection = server.accept();
					Thread task = new DaytimeThread(connection);
					task.start();
					count++;
				}catch (IOException e){}
				
			}
		}catch(IOException e){}

		System.out.println(count);
	}



private static class DaytimeThread extends Thread	{
	private Socket connection;
	DaytimeThread(Socket connection) {
		this.connection = connection;
	}
	
	@Override
	public void run()	{
		try {
			Writer out = new OutputStreamWriter(connection.getOutputStream());
			Date now = new Date();
			out.write(now.toString()+"\r\n");
			out.flush();
			}catch(IOException e){}
		finally{
		try{
			connection.close();
		}catch(IOException e){
		
		}
		}
	}
}
}

