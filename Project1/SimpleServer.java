import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer implements Runnable {

   // server's socket
   private ServerSocket s;

   // server's port
   private int port;

   public SimpleServer(int p) throws Exception {

      // open server socket and start listening
      s = new ServerSocket(port = p);
   }

   public class RequestHandler implements Runnable {
      private Socket sock;

      private RequestHandler(java.net.Socket x) {
         sock = x;
      }

      public void run() {
         try {
            System.out.println("connect...");
            int c;

            // read the bytes from the socket
            // and convert the case
            while((c = sock.getInputStream().read()) != -1) {
                if(c >= 97 && c <= 122) {
                  c -= 32;
                } else if (c >= 65 && c <=90) {
                  c += 32;
                }
                // write it back
                sock.getOutputStream().write(c);
                // flush output if no more data on input
                if (sock.getInputStream().available() == 0) {
                   sock.getOutputStream().flush();
                }
            }
            sock.getOutputStream().flush();
            sock.close();
            System.out.println("disconnect...");
         } catch (Exception e) {
            System.out.println("HANDLER: " + e);
         }
      } 
   }

   public void run() {
      while(true) {
         try {
            // accept a connection and run handler in a new thread
            new Thread(new RequestHandler(s.accept())).run();
         } catch(Exception e) {
            System.out.println("SERVER: " + e);
         }
      }
   } 


  public static void main(String[] argv) throws Exception {
     if (argv.length != 1) {
        System.out.println("java SimpleServer <port>");
        System.exit(1);
     }
     new SimpleServer(Integer.parseInt(argv[0])).run();
  }
   

}
