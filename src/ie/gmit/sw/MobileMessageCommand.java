package ie.gmit.sw;

public class MobileMessageCommand implements Command
{

    MobileMessage mobileMessage;

    public MobileMessageCommand(MobileMessage mobileMessage)
    {
        this.mobileMessage = mobileMessage;
    }

    public void execute()
    {
        mobileMessage.sendMobileMessage();
    }

}
