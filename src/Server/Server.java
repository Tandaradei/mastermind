import java.net.*;
import java.io.*;

class Server {

    public static String scope[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    public static String player = "";
    public static String sequence = "";
    public static int number = 0;

    public static String newgame(String argument) {
        player = argument;
        number = (int) (Math.random() * (14)) + 2;

        for (int i = 0; i < number; i++) {
            sequence += scope[(int) (Math.random() * (15))];
        }

        return "SETUP " + number + " " + sequence;
    }

    public static String check(String argument) {
        String result = "";

        for (int i = 0; i < argument.length(); i++) {
            char charFake = argument.charAt(i);
            char charReal = sequence.charAt(i);

            if (charFake == charReal) {
                result += "B";
            } else {
                for (int j = 0; j < sequence.length(); j++) {
                    if (charFake == sequence.charAt(j)) {
                        result += "W";
                        break;
                    }
                }
            }
        }

        result = (result.length() == 0) ? "0" : result;

        return "RESULT " + result;
    }



    public static String getMyAddress() {
        String address = "";
        try(final DatagramSocket socket = new DatagramSocket()){
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                address = socket.getLocalAddress().getHostAddress();
                socket.close();
        } catch(Exception exp){}
        return address;
   }
    public static void main(String [] args) throws IOException {
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


        //Router
        String response = "Not a valid command";
        String input[] = line.split(" ", 2);
        String command = (input.length > 0) ? input[0] : null;
        String argument = (input.length > 1) ? input[1] : null;

        response = (command.equals("NEWGAME")) ? newgame(argument) : response;
        response = (command.equals("CHECK")) ? check(argument) : response;

        // Ausgabestrom schreiben
        out.write(String.format("%s%n", response));
        out.flush();
    }

    s.shutdownOutput();
    System.out.println("Verbindung beendet!");
    }
}
// vim: ts=4 sta sw=4 et ai
