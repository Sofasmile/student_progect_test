import java.util.ArrayList;

public class Group {
    private String name;
    private String num;
    private ArrayList<Student> studentInGroup;
    private Student groupLider;
    public Group(String name, String num)
    {
        this.name = name;
        this.num = num;
        studentInGroup = new ArrayList<>();
    }
    public void AddGroupLider(Student s)
    {
        groupLider = s;
    }
    public Student GetGroupLider()
    {
        return groupLider;
    }
    public void AddStudentInGroup(Student s)
    {
        studentInGroup.add(s);
    }
    public ArrayList<Student> GetStudentInGroup()
    {
        return studentInGroup;
    }
    public String GetNum()
    {
        return num;
    }
}
