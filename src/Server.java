import java.rmi.registry.*;

public class Server {


    public static void main(String[] args) throws Exception {

            MessagingServer stub = new MessagingServer();
            Registry rmiRegistry = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
            rmiRegistry.rebind("messaging", stub);
    }

}

