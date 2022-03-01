public class Message {
    private boolean isRead;
    private String sender;
    private String receiver;
    private String body;
    private int messageID;

    public Message(boolean isRead, String sender, String receiver, String body, int messageID) {
        this.isRead = isRead;
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
        this.messageID = messageID;
        //ID
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }
}
