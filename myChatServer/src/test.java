import java.lang.reflect.Array;
import java.util.ArrayList;

public class test {

	public static void main(String[] args) {

		String test="/name hello";

		String test2="/name";

		String command[]=test2.split(" ");
		
		if((command[0].equals("/name")||command[0].equals("/enter"))&&command.length==2)
		{

			System.out.println("hi");
		}
		

	ArrayList<String> name = new ArrayList<>();
	//name.add("hello");
		if(name.isEmpty()){
			System.out.println("name");
		}
	}

}
