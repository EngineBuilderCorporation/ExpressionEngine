package ie.gmit.sw;

public class WebMessageCommand implements Command
{

    WebMessage webMessage;

    public WebMessageCommand(WebMessage webMessage)
    {
        this.webMessage = webMessage;
    }

    public void execute()
    {
        webMessage.sendWebMessage();
    }

}
