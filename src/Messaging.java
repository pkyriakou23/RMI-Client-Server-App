import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Messaging extends Remote {



        public String[] showAccount(long token) throws RemoteException;
        public long createAccount(String username) throws RemoteException;
        public String sentMessage(long sender, String receiver, String body) throws RemoteException;
        public String[] showInbox(long token) throws RemoteException;
        public String readMessage (long token, int messageID) throws RemoteException;
        public String deleteMessage (long token, int messageID) throws RemoteException;
}
