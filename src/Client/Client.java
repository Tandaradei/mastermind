import java.net.*;
import java.io.*;

class Client {

    public static void main(String[] args) throws IOException {


        // Konsoleneingabe: IP-Adresse
        Socket s = new Socket("127.0.0.1", 50004);

        if( s.isConnected() )
            System.out.println("Verbindung hergestellt!");
        else
            System.out.println("Verbindung konnte nicht hergestellt werden!");
        
        // Eingabestrom
        BufferedReader in
                = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // Ausgabestrom
        Writer out = new OutputStreamWriter(s.getOutputStream());

        // Eingabestrom für Clienteingabe
        BufferedReader usr = new BufferedReader(new InputStreamReader(System.in));

        // Abwechselnd Stream einlesen bzw. schreiben
        // Abbruch bei EOF (Str+D/Strg+Z) oder Leerzeichen		
        while (true) {
			
			// Clienteingabe (User)
            System.out.print("Client #: ");
            String line = usr.readLine();
            if (line == null || line.equals("")) {
                break;
            }
			// Ausgabestrom schreiben
            out.write(String.format("%s%n", line));
            out.flush();

			// Eingabestrom lesen
            line = in.readLine();
            if (line == null) {
                break;
            }
            System.out.println("Server: " + line);

            /*************************************************************

            String input[] = line.split(" ", 2);
            String command = input[0];
            String argument = input[1];

            if (command) {

            } else {
                System.out.println("Fuck");
            }

            if (command.equals("SETUP")) {
                String reply = "Setup triggered with the argument: " + argument;
                //TODO
                System.out.println(reply);
            }


            *************************************************************/
        }

        s.shutdownOutput();
        System.out.println("Connection closed.");
    }
}
