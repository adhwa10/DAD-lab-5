package exe2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;




public class ClientSide {

	
		public static void main(String[] args) {
			
			System.out.println("\n-----------------Client Side------------------------\n");

			ItemProduct itemproduct = new ItemProduct();
			itemproduct.setname("MCD Special Set");
			itemproduct.setPrice((float)27.00);

			try {

				// establish  connection to server
				int portNo = 4646;
				InetAddress serverAddress = InetAddress.getLocalHost();
				Socket socket = new Socket(serverAddress, portNo);
				ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
				
				// to receive object from the server
				ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
				
				// Get object back from server and display details
				itemproduct = (ItemProduct) objectIS.readObject();
				
				if(objectIS.readBoolean()) {
					
					itemproduct = (ItemProduct) objectIS.readObject();
					
				}
				else {
					System.out.println("Validation failed for Item Product name: "+itemproduct.getname());
				}
				
				// Close all closeable objects
				objectOS.close();
				objectIS.close();
				socket.close();

			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
