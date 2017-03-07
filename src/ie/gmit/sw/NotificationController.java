package ie.gmit.sw;

public class NotificationController
{

    Command notification;

    public NotificationController(){}

    public void setCommand(Command command)
    {
        this.notification = command;
    }

    // mimic button being pressed
    public void buttonPresssed()
    {
        notification.execute();
    }

}// End NotificationController
