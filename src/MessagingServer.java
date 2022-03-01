import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessagingServer extends UnicastRemoteObject implements Messaging {

    ArrayList<Account> accounts;

    public MessagingServer() throws RemoteException
    {
        super();
        accounts = new ArrayList<>();
    }

    @Override


        public String[] showAccount(long token) throws RemoteException
    {
        boolean flag = false;
        String[] temp = new String[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            if(accounts.get(i).getAuthToken() == token)
                flag = true;
            temp[i] = (i + 1) + ". " + accounts.get(i).getUsername();
        }

        if (!flag)
        {
            temp = new String[1];
            temp[0] = "Invalid Auth Token";
            return temp;
        }

        return temp;
    }

    public long createAccount(String username) throws RemoteException
    {
        long rtn = validOfUsername(username);
        if (rtn == 1 || rtn == -1)
            return rtn;

        long token = generateCodeByAscii(username);
        Account a = new Account(username, token);
        accounts.add(a);
        return token;
    }

    public String sentMessage(long sender, String receiver, String body) throws RemoteException
    {

        boolean flag = false;
        for (Account a : accounts) {
            if (a.getAuthToken() == sender) {
                flag = true;
                for (Account ac : accounts) {
                    if (ac.getUsername().equals(receiver)) {
                        Message m = new Message(false, a.getUsername(), receiver, body,ac.getMessages().size() + 1);
                        ac.addMessage(m);
                        return "OK";
                    }
                }

            }

        }
        if (flag)
            return "User does not exist";
        return "Invalid Auth Token";
    }

    public String[] showInbox(long token) throws RemoteException
    {
        String[] messages = new String[1];
        for (Account a : accounts) {
            if (a.getAuthToken() == token) {
                messages = new String[a.getMessages().size()];
                for (int i = 0; i < messages.length; i++) {
                    String read = a.getMessages().get(i).getIsRead() ? "" : "*";
                    messages[i] = (a.getMessages().get(i).getMessageID()+1) + ". " + "from: " + a.getMessages().get(i).getSender() + read;
                }
                return messages;
            }
        }
        messages[0] = "Invalid Auth Token";
        return messages;
    }

    public String readMessage(long token, int messageID) throws RemoteException
    {
        for (Account a: accounts) {
            if(a.getAuthToken() == token)
            {
                for (Message m: a.getMessages()) {
                    if(m.getMessageID() == messageID)
                    {
                        m.setRead(true);
                        return "(" + m.getSender() + ") " + m.getBody();
                    }
                }
                return "Message ID does not exist";
            }
        }
        return "Invalid Auth Token";
    }

    public String deleteMessage(long token, int messageID) throws RemoteException
    {
        for (Account a: accounts) {
            if(a.getAuthToken() == token)
            {
                for (Message m: a.getMessages()) {
                    if(m.getMessageID() == messageID)
                    {
                        a.getMessages().remove(m);
                        return "OK";
                    }
                }
                return "Message does not exist";
            }
        }
        return "Invalid Auth Token";
    }

    private long generateCodeByAscii(String username)
    {
        long code=0;
        for(int i=0; i<username.length(); i++) {
            code = (code*10) + ( ((long)username.charAt(i))) ;
        }
        return code;
    }
    private int validOfUsername(String username) throws RemoteException
    {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9_]");
        Matcher matcher = pattern.matcher(username);
        if(matcher.find())
            return 1;
        for (Account ac: accounts) {
            if (ac.getUsername().equals(username))
                return -1;
        }
        return 0;
    }
}
