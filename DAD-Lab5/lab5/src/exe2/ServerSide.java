package exe2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;




public class ServerSide {


	public static void main(String[] args) {
		int count = 1;
		
			
			try {
				
				// Port to receive and respond to request
				
				int portNo = 4646;
				@SuppressWarnings("resource")
				ServerSocket serverSocket = new ServerSocket(portNo);
				
				System.out.println("\n-----------------Server Side------------------------\n");
				System.out.println("Ready for request...");
				System.out.println("Waiting...");
				
				
				// Server need to be alive forever thus the while(true)
				while (true) {
					
					Socket clientsocket = serverSocket.accept();
					System.out.println("Connection from client successfull\n");
					
					// Create input stream to read object
					ObjectInputStream objectIS = new ObjectInputStream(clientsocket.getInputStream());
					ItemProduct itemproduct = (ItemProduct) objectIS.readObject();
					
					ObjectOutputStream objectOS = new ObjectOutputStream(clientsocket.getOutputStream());
					Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
					
					if(p.matcher(itemproduct.getname()).find()) {
						System.out.println("Sending validation result: "+false);
						System.out.println();
						objectOS.writeBoolean(false);
					}
					else {
						System.out.println("Sending validation result: "+true);
						objectOS.writeBoolean(true);
						itemproduct.setitemProductID(count++);
						System.out.println("Sending object: ");
						System.out.println("Item Product ID: "+itemproduct.getitemProductID());
						System.out.println("Name of Product: "+itemproduct.getname());
						System.out.println("Price of Product: "+itemproduct.getPrice());
						System.out.println();
						objectOS.writeObject(itemproduct);					
					}
					
					// Close all streams
					objectIS.close();
					objectOS.close();
					
				}
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}