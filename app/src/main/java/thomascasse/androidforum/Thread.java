package thomascasse.androidforum;

public class Thread
{
    private String Name;
    private String Title;
    private String Desc;

    public Thread(String name, String title, String desc)
    {
        Name = name;
        Title = title;
        Desc = desc;
    }

    public String getName()
    {
        return Name;
    }
    public void setName(String name)
    {
        Name = name;
    }
    public String getTitle() { return Title; }
    public void setTitle(String title) { Title = title; }
    public String getDesc() { return Desc; }
    public void setDesc(String desc) { Desc = desc; }
}
