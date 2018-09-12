import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;






public class ChatServer {

	private static List<Client> clients = new ArrayList<Client>();
	private static List<String> rooms = new ArrayList<String>(); 

	public final static int PORT = 33333;
	static int count=0;


	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(PORT))	{
			System.out.println("start server");
			while	(true)	{
				try	{
					Socket s = server.accept();


					Thread task = new Client(s);
					task.start();


				}catch (IOException e){}

			}
		}catch(IOException e){}

	}



	public static class Client extends Thread	{
		private Socket s;
		private Writer out = null;

		//set name, room
		private String name=null;
		private String room=null;


		Client(Socket s) {
			this.s = s;
		}
		public void run()	{
			try {


				s.setSendBufferSize(16384);
				s.setTcpNoDelay(true);
				BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = new OutputStreamWriter(s.getOutputStream());
				out.write("\r\nWelcome, Chanwoo's Chatting Server\r\n type /help\r\n");
				out.write("$ ");
				out.flush();

				String line = null;

				while ((line = input.readLine()) != null)
				{
					String[] command = line.split(" ");
					//out.write(line.length());
					//out.flush();


					/*
					if(name.length()>0){
						out.write("hi "+name+"\r\n");
						out.flush();
					}*/


					if (command[0].equals("/help")&&command.length==1)
					{
						out.write("* /help view help msg\r\n"
								+ "* /name [Nickname] : set nickname\r\n"
								+ "* /show\r\n"
								+ "* /create [RoomName] create room\r\n"
								+ "* /remove [RoomName]\r\n"
								+ "* /enter [RoomName]\r\n"
								+ "* /join [RoomName] = enter\r\n"
								+ "* /exit = quit\r\n"
								+ "* /quit = exit\r\n");
						out.flush();
					}

					else if(command[0].equals("/name")&&command.length==2)
					{


						//out.write(command[1]+"\r\n");
						out.write(line+" : OK\r\n");
						out.flush();

						// 같은이름 들어왔을때 중복처리 해야함.
						name=command[1];
						clients.add(this);
					}

					else if(command[0].equals("/show")&&command.length==1)
					{
						if(rooms.isEmpty()||rooms==null)
						{
							out.write("no rooms try /create roomname\r\n$ ");
							out.flush();
							continue;

						}
						out.write(line+" : OK\r\n--list of chatrooms--\r\n");
						out.flush();
						//print rooms
						for(String room : rooms){
							out.write("["+room+"] ");
						}
						out.write("\r\n");
						out.flush();


					}
					else if(command[0].equals("/create")&&command.length==2)
					{

						//out.write(command[1]+"\r\n");

						out.write(line+" : OK\r\n");
						out.flush();
						rooms.add(command[1]);

					}
					else if(command[0].equals("/remove")&&command.length==2)
					{
						//out.write(command[1]+"\r\n");

						out.write(line+" : OK\r\n");
						out.flush();
						rooms.remove(command[1]);
					}
					else if((command[0].equals("/join")||command[0].equals("/enter"))&&command.length==2)
					{

						if(name==null||name.isEmpty())
						{
							out.write("Need Nickname!!\r\n$ ");
							out.flush();
							continue;
						}	
						//out.write(command[1]+"\r\n");
						out.write(line+" : OK\r\n");
						out.flush();

						if(rooms.contains(command[1]))
						{
							room=command[1];
							String msg;
							String lastmsg=null;

							// 같은방에 있는사람에게 메시지 전달 
							//out.write("["+name+"(me)]"+" : ");


							out.write("room "+room+" joined.\r\n"
									+ "/help\r\n");
							out.flush();
							
//					synchronized (clients) {
//						
//					
//							for (Client c : clients)
//							{	
//								if(c.s.equals(s))continue;
//								if(c.room.equals(room)){
//									c.out.write("hi");
//									c.out.flush();
//								}
//							}
//					}
							while ((msg = input.readLine()) != null){
								/**/								
								
								
								String[] roomcommand = msg.split(" ");
								if(msg.equals("/q")||msg.equals("/quit")||msg.equals("/exit")&&roomcommand.length==1)
								{

									break;
								}
								else if(msg.equals("/show")&&roomcommand.length==1)
								{
									out.write("list of users in room ["+room+"]\r\n");
									for(Client c : clients){
										if(c.room.equals(room)){
											out.write("["+c.name+"] ");
											
										}
									}
									out.write("\r\n");
									out.flush();
								}
								else if(msg.equals("/help")&&roomcommand.length==1)
								{
									out.write("* /help view help msg\r\n"
											+ "* /name [Nickname] : set nickname\r\n"
											+ "* /show : show users\r\n"
											+ "* /exit = /quit = /q\r\n");
									out.flush();
								}
								else if(msg.equals("/name")&&roomcommand.length==2)
								{
									name=roomcommand[1];
								}

								/**/

								//								//check same msg
								//								if(msg&&lastmsg!=null){
								//										
								//									System.out.println("same msg\r\n");
								//									continue;
								//								}

								//broadcast
								
								for (Client c : clients)
								{	
									if(c.s.equals(s))continue;
									if(c.room.equals(room)){
										c.out.write("["+name+"]"+" : "+msg+"\r\n");
										c.out.flush();
									}
								}
								lastmsg=msg;
								//roomcast(this,"wowowow",room);
								//out.write("["+name+"(me)]"+" : ");
								out.write("");
								out.flush();

							}

						}
						else
						{
							out.write("Does not exist in Chatrooms\r\n");
							out.flush();
						}
					}

					else if(command[0].equals("/exit")||command[0].equals("/quit")||command[0].equals("/q")&&command.length==1)
					{
						out.write("Goodbye!\r\n");
						out.flush();
						return;//exit
					}
					else
					{
						out.write(line+" : command not found, type /help\r\n");
						out.flush();
					}
					out.write("$ ");
					out.flush();

					line = null;

  


					for (Client otherClient : clients){
						System.out.println(otherClient.name+" "+otherClient.room+" "+clients.size());
					}


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

