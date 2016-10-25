import java.net.*;
import java.io.*;

class Server {

    public static String getMyAddress() {
        String address = "";
        try(final DatagramSocket socket = new DatagramSocket()){
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                address = socket.getLocalAddress().getHostAddress();
                socket.close();
        } catch(Exception exp){}
        return address;
   }
    public static void main (String [] args) throws IOException {
    int port = 50004;
    String hostname = getMyAddress();

    // Server-Socket erzeugen und an Port 50000 binden
    ServerSocket ss = new ServerSocket(50004);

    // Auf eine Client-Verbindung warten und akzeptieren
    System.out.println("Auf Client an "+hostname+":"+port+" warten ...");
    Socket s = ss.accept();
    System.out.println("Verbindung hergestellt!");

    // Eingabestrom
    BufferedReader in =
        new BufferedReader(new InputStreamReader(s.getInputStream()));

    // Ausgabestrom
    Writer out = new OutputStreamWriter(s.getOutputStream());

    // Eingabestrom für Servereingabe
    BufferedReader usr = new BufferedReader(new InputStreamReader(System.in));

    // Abwechselnd Stream einlesen bzw. schreiben
    // Abbruch bei EOF (Str+D/Strg+Z) oder Leerzeichen
    while (true) {

        // Eingabestrom lesen
        String line = in.readLine();
        if (line == null) break;
        //System.out.println("Client: " + line);
        // Servereingabe (User)
        //System.out.print("Server #: ");
        //line = usr.readLine();
        //if (line == null || line.equals("")) break;

        /*************************************************************/
        /*************************************************************/
        /*************************************************************/


         String input[] = line.split(" ", 2);
         String command = (input.length > 0) ? input[0] : null;
         String argument = (input.length > 1) ? input[1] : null;

        // "response" ist der String, der an den Client zurueckgegeben wird.
         String response = "Not a valid command";

        // Reaktionen von Server auf Aktionen von Client

        if (command.equals("NEWGAME")) {
             response = "NEWGAME triggered with the argument: " + argument;
             //TODO
        }

        if (command.equals("CHECK")) {
            response = "CHECK triggered with the argument: " + argument;
            //TODO
        }


        /*************************************************************/
        /*************************************************************/
        /*************************************************************/

        // Ausgabestrom schreiben
        out.write(String.format("%s%n", response));
        out.flush();
    }

    s.shutdownOutput();
    System.out.println("Verbindung beendet!");
    }
}
// vim: ts=4 sta sw=4 et ai
