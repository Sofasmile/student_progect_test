import java.util.ArrayList;

public class WorkWithStudent
{
    public ArrayList<Student> stud;
    public ArrayList<Group> grp;
    public ArrayList<Professor> prof;

    public WorkWithStudent()
    {
       stud = new ArrayList<Student>();
       grp = new ArrayList<Group>();
       prof = new ArrayList<Professor>();
    }

    public boolean GroupIsEmpty()//Перевірка на наявність студентів в групі
    {
        if(grp.isEmpty()==true)
            return false;
        else
            return true;
    }

    public boolean StudentInGroupIsEmpty(int num)//Перевірка на те, чи в групі є студенти
    {
        if(grp.get(num).GetStudentInGroup().isEmpty()==true)
            return  false;
        else
            return true;
    }
    public boolean ProfessorIsEmpty()//Перевірка на наявність викладачів
    {
        if(prof.isEmpty()==true)
            return false;
        else
            return true;
    }
    public int ProffesorSize()
    {
        return prof.size();
    }
    public int GroupSize()
    {
        return grp.size();
    }
    public void AddGroup(String name, String num)// Додати групу
    {
        Group g = new Group(name, num);
        grp.add(g);
    }

    public void AddStudent(String lastFirstName, String sex, String birthday, String numberPhone, Group group, ArrayList<String> characters)//Додати студента
    {
        Student s = new Student(lastFirstName,sex, birthday, numberPhone, group, characters);
        stud.add(s);
        group.AddStudentInGroup(s);
    }

    public void AddProfessor(String name, String post, String subject)//ДОдати професора
    {
        Professor p = new Professor(name, post, subject);
        prof.add(p);
    }
    public Group FindGroup(int num )//Знайти групу за номером в списку
    {
        return grp.get(num);
    }

    public void GetProfessor(int num)//Дані про професора за номером в списку
    {
        Professor p = prof.get(num);
        System.out.println(p.GetFirstLastName()+ "  " + p.GetPost() + "  " + p.GetSubject());
    }
    public void ShowProfessor()//Вивід інформації про всіх професорів
    {
        int i = 1;
        for(Professor p : prof){
            System.out.printf("%2d. %10s %15s %15s\n", i, p.GetFirstLastName(),p.GetPost(), p.GetSubject());
            i++;
        }
    }

    public void ShowGroup()//Вивід інформації про всі групи
    {
        int i= 1;
        for(Group gr : grp){
            System.out.println(i+ ". " + gr.GetNum());
            i++;
        }
    }

    public void ShowStudentByGroup(int number)// пошук та вивід списку студентів за індексом групи
    {
        int i = 1;
        ArrayList<Student> s = grp.get(number).GetStudentInGroup();
        for(Student person : s){
            System.out.printf("%2d. %5s %10s %10s %10s %10s\n", i, person.GetLastFirstName(), person.GerBirthdey(),person.GetNumberPhone(),person.GetGroup().GetNum(),person.GetSex());
            i++;
        }
    }

    public int CountOfStudentInGroup(int number)//Кількість студетів в групі, за номером групи
    {
        ArrayList<Student> s = grp.get(number).GetStudentInGroup();
        return s.size();
    }
    public String ShowStudentByGroup1(int number, int numberSt) //Пошук студента за номером групи та номером студента в списку групи
    {
        Student s = grp.get(number).GetStudentInGroup().get(numberSt);
        return  s.GetLastFirstName();
    }


    public ArrayList<String> ShowStudentToLider(int number, String character) //Список студентів, які претендують стати старостою
    {
        ArrayList<String> student = new ArrayList<>();
        ArrayList<Student> s = grp.get(number).GetStudentInGroup();
        for(Student person : s) {
            ArrayList<String> temp = person.GetCharacters();
            if (temp.contains(character))
                student.add(person.GetLastFirstName());
        }
        return student;
    }

    public boolean MakeStudentGroupLider(int numberGr, String name )//Зробити студента старостою групи
    {
        ArrayList<Student> s = grp.get(numberGr).GetStudentInGroup();
        for(Student person : s) {
            if(person.GetLastFirstName().equals(name)==true) {
                grp.get(numberGr).AddGroupLider(person);
                return true;
            }
        }
        return  false;
    }

    public String ShowGroupLider(int numberGr)//Вивід ПІБ старости групи
    {
        String s = "";
        Student st = grp.get(numberGr).GetGroupLider();
        if(st != null) {
            s = st.GetLastFirstName();
        }
        return s;
    }

    public void AttendanceOfStudent(int numberGr, int numberSt, boolean present)//Переклиска студентів
    {
        grp.get(numberGr).GetStudentInGroup().get(numberSt).AddPresent(present);
    }

    public int ResultOfAttendance(int numberGr)//РЕзультати переклички
    {
        int countTrue = 0;
        ArrayList<Student> s = grp.get(numberGr).GetStudentInGroup();
        for(Student person : s){
            if(person.GetPresent() == true)
                countTrue++;
        }
        return countTrue;
    }
}
