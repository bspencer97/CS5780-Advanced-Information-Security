import java.net.Socket;


public class SimpleClient { 

   // network socket
   private Socket s;

   public SimpleClient(String host, int port) throws Exception {
       // open a connection to the server
       s = new Socket(host,port);
   }

   // data transfer
   public void execute() throws Exception {
      int c, k=0,i=0;

      // read data from keyboard until end of file
      while((c = System.in.read()) != -1) {

         // send it to server
         s.getOutputStream().write(c);
         // if carriage return, flush stream
         if ((char)c == '\n' || (char)c == '\r') s.getOutputStream().flush();
         ++k;
      }
      s.getOutputStream().flush();
 
      // read until end of file or same number of characters
      // read from server 
      while((c = s.getInputStream().read()) != -1) {
         System.out.write(c);
         if(++i == k) break;
      }
      System.out.println();
      System.out.println("wrote " +i + " bytes");
      s.close();
   }

   
   public static void main(String[] argv) throws Exception {
      if (argv.length != 2) {
         System.out.println("java SimpleClient <host> <port>");
         System.exit(1);
      }

      String host = argv[0];
      int port = Integer.parseInt(argv[1]);

      new SimpleClient(host,port).execute();
   } 
}
