public class Professor {
    private String firstLastName;
    private String post;
    private String subject;
    public Professor(String firstLastName, String post, String subject)
    {
        this.firstLastName = firstLastName;
        this.post = post;
        this.subject = subject;
    }

    public String GetFirstLastName()
    {
        return firstLastName;
    }
    public String GetPost()
    {
        return post;
    }
    public String GetSubject()
    {
        return subject;
    }

}
