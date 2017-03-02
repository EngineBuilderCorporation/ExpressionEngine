package ie.gmit.sw;

public class EmailCommand implements Command
{

    Email email;

    public EmailCommand(Email email)
    {
        this.email = email;
    }

    public void execute()
    {
        email.sendEmail();
    }

}// End class EmailCommand
