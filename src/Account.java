import java.rmi.RemoteException;
import java.util.ArrayList;

public class Account {

   private String username;
   private long authToken;
   private ArrayList<Message> messages;

    public Account(String username, long authToken)   {
        this.username = username;
        this.authToken = authToken;
        messages = new ArrayList<>();
    }

    public String getUsername() throws RemoteException{
        return username;
    }

    public void addMessage(Message m)
    {
        messages.add(m);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public long getAuthToken() {
        return authToken;
    }
}
