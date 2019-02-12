import java.util.ArrayList;

public class Student {
    private String lastFirstName;
    private String sex;
    private String birthday;
    private String numberPhone;
    private Group group;
    private int id;
    private ArrayList<String> characters;
    private boolean present;

    public Student(String lastFirstName, String sex, String birthday, String numberPhone, Group group, ArrayList<String> characters)
    {
        this.lastFirstName = lastFirstName;
        this.sex = sex;
        this.birthday = birthday;
        this.numberPhone = numberPhone;
        this.group = group;
        this.characters = characters;
        this.present = false;
    }
    public void AddPresent(boolean pres)
    {
        present = pres;
    }
    public boolean GetPresent()
    {
        return present;
    }
    public String GetLastFirstName()
    {
        return lastFirstName;
    }
    public String GetSex()
    {
        return sex;
    }
    public String GerBirthdey()
    {
        return birthday;
    }
    public String GetNumberPhone()
    {
        return  numberPhone;
    }
    public Group GetGroup()
    {
        return group;
    }
    public ArrayList<String> GetCharacters()
    {
        return characters;
    }




}
