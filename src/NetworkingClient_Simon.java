import java.io.*;
import java.net.*;
public class NetworkingClient_Simon {
    public static void main(String[] args) {

        Socket client = null;

        // Default portnumber
        int portnumber = 8080;
        if(args.length >= 1)
        {
            portnumber = Integer.parseInt(args[0]);
        }

        for (int i = 0; i < 10; i++) {
            try {
                String message = "";

                // Create a client socket
                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket is created " + client);

                // Create an output strea, of the client socket
                OutputStream clientOut = client.getOutputStream();
                PrintWriter printWriter = new PrintWriter(clientOut, true);

                // Create an input stream of the client socket
                InputStream clientIn = client.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientIn));

                System.out.println("Enter your name. Type Bye to exit");

                // Read data from the standard input devices and write it
                // to the output stream of the clinet socket
                message = bufferedReader.readLine().trim();
                printWriter.println(message);

                // Read data from the input stream of the client socket
                System.out.println("Message returned from the server = " + bufferedReader.readLine());

                printWriter.close();
                bufferedReader.close();
                client.close();

                // Stop the operation
                if (message.equalsIgnoreCase("Bye"))
                {
                    break;
                }

            } catch (IOException ie) {
                System.out.println("I/O error " + ie);
            }
        }
    }
}