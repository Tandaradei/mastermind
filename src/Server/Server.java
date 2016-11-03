import java.net.*;
import java.io.*;

class Server {

    public static String scope = "123456789abcdef"; // Möglichkeiten
    public static int length = 5; // Länge

    public static String player; // Name des Spielers
    public static String sequence; // Zufällige Sequenz der Farben

    // Reaktion auf das Kommando NEWGAME (C->S)
    public static String newgame(String argument) {
        player = argument;
        sequence = "";

        for (int i = 0; i < length; i++) {
            sequence += scope.charAt((int) (Math.random() * 15));
        }

        return "SETUP " + length + " " + sequence;
    }

    // Reaktion auf das Kommando CHECK (C->S)
    public static String check(String argument) {
        String result = "";

        for (int i = 0; i < length; i++) {
            char charReal = sequence.charAt(i);
            char charFake = argument.charAt(i);

            if (charReal == charFake) {
                result += "B";
            } else {
                for (int j = 0; j < length; j++) {
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
        try(final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            address = socket.getLocalAddress().getHostAddress();
            socket.close();
        } catch(Exception exp){}
        return address;
    }

    public static void main(String[] args) throws IOException {
        int port = 50004;
        String hostname = getMyAddress();

        // Server-Socket erzeugen und an Port 50000 binden
        ServerSocket ss = new ServerSocket(50004);

        // Auf eine Client-Verbindung warten und akzeptieren
        System.out.println("Auf Client an " + hostname + ":" + port + " warten ...");
        Socket s = ss.accept();
        System.out.println("Verbindung hergestellt!");

        // Eingabestrom
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // Ausgabestrom
        Writer out = new OutputStreamWriter(s.getOutputStream());

        // Eingabestrom für Servereingabe
        BufferedReader usr = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            // Eingabestrom lesen
            String line = in.readLine();
            if (line == null) break;

            // Passende Methode aufrufen
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
