import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    WorkWithStudent work = new WorkWithStudent();
    Scanner scan = new Scanner(System.in);

    public String tel = "^(0\\d{2})-(\\d{3})-(\\d{4})$";
    public String dateBirth = "^((0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19[5-9][0-9])|(200[0-5])))$";
    Pattern r = Pattern.compile(dateBirth);
    Pattern t = Pattern.compile(tel);

    String character1 = "Товаристський";
    String character2 = "Замкнутий";
    String character3 = "Відповідальний";
    String character4 = "Розсудливий";
    String character5 = "Спокійний";
    String character6 = "Прямолінійний";
    String character7 = "Пунктуальний";
    String character8 = "Сумлінний";
    String character9 = "Цілеспрямлваний";

    public void MainMenu() //Головне меню програми
    {

        boolean isWork = true;
        while(isWork)
        {
            System.out.println("\n__________________________\n1.Створити групи. \n2.Додати студентів. \n3.Додати викладачів. \n4.Перегляд груп. \n5.Обрати групу для роботи. \n6.Переглянути викладачів. \n7.Вихід.\n--------------------------");
            while (!scan.hasNextInt()) {
                System.out.println("Це не число!");
                scan.next();
            }
            int choose = scan.nextInt();
            switch (choose) {
                case 1:
                    MenuAddGroup();
                    break;
                case 2:
                    MenuAddStudent();
                    break;
                case 3:
                    MenuAddProfessor();
                    break;
                case 4:
                    MenuShowGroup();
                    break;
                case 5:
                    int num;
                    System.out.println("Оберіть групу для роботи, за номером:");
                    if(work.GroupIsEmpty()==false) {
                        System.out.println("Спочатку створіть групи!");
                        break;
                    }
                    else {
                        work.ShowGroup();
                        while (!scan.hasNextInt()) {
                            System.out.println("Це не число!");
                            scan.next();
                        }
                        num = scan.nextInt();
                        while (num > work.GroupSize() || num <= 0) {
                            System.out.print("Значення виходить за допустимі межі, спробуйте знову:");
                            while (!scan.hasNextInt()) {
                                System.out.println("Це не число!");
                                scan.next();
                            }
                            num = scan.nextInt();
                        }
                        if(work.StudentInGroupIsEmpty(num-1)==false){
                            System.out.println("В групі студентів немає! Додайте спочатку студентів.");
                        }
                        else
                            MenuWorkWithGroup(num);
                        break;
                    }
                case 6:
                    MenuShowProfessor();
                    break;
                case 7:
                    isWork = false;
                    break;
                default:
                    System.out.println("Значення виходить за діапазон можливих меж! Спробуйте знову!");
                    break;
            }
        }
    }

    public void MenuAddGroup() //Створення груп (1 п. головного меню)
    {
        System.out.println("Введіть назву спеціальності:");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.println("Введіть номер (скорочену назву) спеціальності:");
        String num = scan.nextLine();
        if(name.equals("")==false &&num.equals("")==false)
            work.AddGroup(name, num);
        else
            System.out.println("Спробуйте знову!");
    }

    public void MenuAddStudent()// Створення стуентів (2 п. головного меню)
    {
        if(work.GroupIsEmpty() == false){
            System.out.println("Спочатку створіть групи!");
        }
        else {
            System.out.println("Введіть прізвище та ім'я студента: ");
            scan.nextLine();
            String nameStudent = scan.nextLine();
            String nameSex = "";
            while (nameSex == "" ) {
                System.out.println("Введіть стать студента \n   1.жінка\n   2.чоловік");
                while (!scan.hasNextInt()) {
                    System.out.println("Це не число!");
                    scan.next();
                }
                int res = scan.nextInt();
                switch (res) {
                    case 1 : nameSex  = "жінка";
                        break;
                    case 2: nameSex  = "чоловік";
                        break;
                    default: break;
                }
            }

            String dateBirthday = "";
            while (dateBirthday == "") {
                System.out.println("Введіть дату народження студента (DD-MM-YYYY) від 1950-2005:");
                scan.nextLine();
                dateBirthday = scan.nextLine();
                Matcher m = r.matcher(dateBirthday);
                if (m.find() != true) {
                    System.out.println("Спробуйте знову!");
                    dateBirthday = "";
                }
            }
            String telephone = "";
            while (telephone == "") {
                System.out.println("Введіть номер телефону студента (0ХХ-ХХХ-ХХХХ):");
                telephone = scan.nextLine();
                Matcher m = t.matcher(telephone);
                if (m.find() != true) {
                    System.out.println("Спробуйте знову!");
                    telephone = "";
                }
            }

            ArrayList<String> charakter = ChooseCharakter();
            scan.nextLine();
            Group g = null;
            while (g == null) {
                System.out.println("Оберіть групу, в якій буде навчатися студент. Введіть номер:");
                work.ShowGroup();
                while (!scan.hasNextInt()) {
                    System.out.println("Це не число!");
                    scan.next();
                }
                int groupNum = scan.nextInt();
                while (groupNum > work.GroupSize()||groupNum<=0) {
                    System.out.print("Значення виходить за допустимі межі, спробуйте знову:");
                    while (!scan.hasNextInt()) {
                        System.out.println("Це не число!");
                        scan.next();
                    }
                    groupNum = scan.nextInt();
                }
                g = work.FindGroup(groupNum-1);
                if (g != null)
                    work.AddStudent(nameStudent, nameSex, dateBirthday, telephone, g, charakter);
                else
                    System.out.println("Спробуйте знову!");
            }
        }
    }

    public void MenuAddProfessor()//Створення професорів (3 п. головного меню)
    {
        System.out.println("Введіть ПІБ викладача:");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.println("Введіть посаду викладача:");
        String post = scan.nextLine();
        System.out.println("Введіть предмет, який викладає педагог:");
        String subject = scan.nextLine();
        if(name.equals("")==false &&post.equals("")==false&&subject.equals("")==false)
            work.AddProfessor(name, post, subject);
        else
            System.out.println("Спробуйте знову!");
    }

    public void MenuShowGroup()//Перегляд списку груп (4 п. головного меню)
    {
        if(work.GroupIsEmpty() == false){
            System.out.println("Список пустий! Спочатку створіть групи!");
        }
        else {
            work.ShowGroup();
            System.out.println("Вкажіть номер групи для перегляду списку студентів:");
            while (!scan.hasNextInt()) {
                System.out.println("Це не число!");
                scan.next();
            }
            int num = scan.nextInt();
            while (num > work.GroupSize() || num <= 0) {
                System.out.print("Значення виходить за допустимі межі, спробуйте знову:");
                while (!scan.hasNextInt()) {
                    System.out.println("Це не число!");
                    scan.next();
                }
                num = scan.nextInt();
            }
            if(work.StudentInGroupIsEmpty(num-1)==false)
                System.out.println(" Студенти в групі відсутні!");
            else
                 work.ShowStudentByGroup(num - 1);
        }
    }


    public void MenuWorkWithGroup(int num)//Робота з конкретною групою. Додаткове меню
    {
        boolean workMenu = true;
        while (workMenu) {
            System.out.println("\n__________________________\n1.Переглянути список студентів групи. \n2.Обрати старосту за критеріями. \n3.Переглянути старосту групи. \n4.Зробити перекличку. \n5.Повернутися до попереднього меню\n--------------------------");
            while (!scan.hasNextInt()) {
                System.out.println("Це не число!");
                scan.next();
            }
            int choose = scan.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("В групі навчаються наступні студенти:");
                    work.ShowStudentByGroup(num - 1);
                    break;
                case 2:
                    MenuChooseLider(num);
                    break;
                case 3:
                    System.out.println("Староста групи:");
                    String s = work.ShowGroupLider(num-1);
                    if(s!="")
                        System.out.println(s);
                    else
                        System.out.println("Спочатку оберіть старосту!");

                    break;
                case 4:
                    if(work.ProfessorIsEmpty()==false)
                    {
                        workMenu = false;
                        System.out.println("Cпочатку додайте викладачів!");
                    }
                    else
                        MenuAttendance(num);
                    break;
                case 5:
                    workMenu = false;
                    break;
            }
        }
    }

    public void MenuChooseLider( int num)//Вибір старости за рисою характеру (2 п. другорядного меню)
    {
        String s = MenuChooseCharacterLider(num);
        if(s!="") {
            ArrayList<String> student = work.ShowStudentToLider(num - 1, s);
            if (student.isEmpty() == true) {
                System.out.println("На жаль, в групі немає студентів, з вибраною рисою характеру. \nОберіть іншу рису характеру! ");
                s = MenuChooseCharacterLider(num);
            } else {
                String name = "";
                System.out.println("Оберіть старостою одного з наступних студентів (вкажіть номер):");
                for (int i = 0; i < student.size(); i++)
                    System.out.println((i + 1) + ". " + student.get(i));
                while (!scan.hasNextInt()) {
                    System.out.println("Це не число!");
                    scan.next();
                }
                int numSt = scan.nextInt();
                while (numSt > student.size() || numSt <= 0) {
                    System.out.print("Значення виходить за допустимі межі, спробуйте знову:");
                    while (!scan.hasNextInt()) {
                        System.out.println("Це не число!");
                        scan.next();
                    }
                    numSt = scan.nextInt();
                }
                name = student.get(numSt - 1);
                if (work.MakeStudentGroupLider(num - 1, name) == true)
                    System.out.println("Старосту обрано!");
                else
                    System.out.println("Спробуйте знову!");
            }
        }
    }

    public String MenuChooseCharacterLider(int num) {//продовження вибору старости
        String s = "";
        System.out.println("Оберіть рису характеру, за як1ою буде відбуватися вибір старости: ");
        System.out.println("1." +character3 +"\n2." +character4+"\n3."+character5 + "\n4.Вихід");
        while (!scan.hasNextInt()) {
            System.out.println("Це не число!");
            scan.next();
        }
        int choose = scan.nextInt();
        switch (choose) {
            case 1:
                return character3;
            case 2:
                return character4;
            case 3:
                return character5;
            case 4:
                return s;
            default:
                System.out.println("Значення виходить за діапазон можливих меж! Спробуйте знову!");
                break;
        }
        return s;
    }

    public void MenuAttendance(int num)//Перекличка студентів (4 п. другорядного меню)
    {
        System.out.println("Оберіть викладача, який буде робити перекличку:");
        work.ShowProfessor();
        while (!scan.hasNextInt()) {
            System.out.println("Це не число!");
            scan.next();
        }
        int res = scan.nextInt();
        while (res > work.ProffesorSize()||res<=0) {
            System.out.print("Значення виходить за допустимі межі, спробуйте знову:");
            while (!scan.hasNextInt()) {
                System.out.println("Це не число!");
                scan.next();
            }
            res = scan.nextInt();
        }
        System.out.print("Ви обрали:");
        work.GetProfessor(res-1);
        scan.nextLine();
        System.out.println("Якщо студент присутній, натисніть (+), якщо відсутній (-):");
        int size = work.CountOfStudentInGroup(num-1);
        for(int i = 0; i<size; i++)
        {
            System.out.println(work.ShowStudentByGroup1(num-1,i));
           // scan.nextLine();
            String result = scan.nextLine();
            if(result.contains("+"))
                work.AttendanceOfStudent(num-1,i,true);
            else if(result.contains("-"))
                work.AttendanceOfStudent(num-1,i,false);
            else {
                i--;
                System.out.println("Повторіть знову!");
            }
        }
        System.out.println("\nРезультати переклички:" );
        int isPresent = work.ResultOfAttendance(num-1);
        System.out.println("Кількість студентів присутніх на занятті: " + isPresent);
        System.out.println("Кількість відсутніх студентів: " + (size-isPresent));
    }

    public void MenuShowProfessor() //Вивід списку викладачів (6 п. головного меню)
    {
        if(work.ProfessorIsEmpty() == false){
            System.out.println("Список пустий! Спочатку додайте викладачів!");
        }
        else {
            System.out.println("Викладачі:");
            work.ShowProfessor();
        }
    }

    public ArrayList<String> ChooseCharakter()//Вибір рис характеру, при додаванні студента
    {
        System.out.println("Оберіть три характеристики для студента (вводьте значення по черзі, та натискайте щоразу ентер)");
        System.out.println("1." + character1 + "\n2." + character2 + "\n3." + character3 + "\n4."+character4+ "\n5."+character5 +"\n6." +character6 + "\n7."+character7+ "\n8." +character8+ "\n9."+character9);
        ArrayList<String> characters = new ArrayList<String>();
        for(int i = 0; i<3; i++) {
            System.out.println("Вкажіть номер риси характеру: ");
            while (!scan.hasNextInt()) {
                System.out.println("Це не число!");
                scan.next();
            }
            int choose = scan.nextInt();
            switch (choose) {
                case 1:
                    if(characters.contains(character1))
                        i--;
                    else
                        characters.add(character1);
                    break;
                case 2:
                    if(characters.contains(character2))
                        i--;
                    else
                        characters.add(character2);
                    break;
                case 3:
                    if(characters.contains(character3))
                        i--;
                    else
                        characters.add(character3);
                    break;
                case 4:
                    if(characters.contains(character4))
                        i--;
                    else
                        characters.add(character4);
                    break;
                case 5:
                    if(characters.contains(character5))
                        i--;
                    else
                        characters.add(character5);
                    break;
                case 6:
                    if(characters.contains(character6))
                        i--;
                    else
                        characters.add(character6);
                        break;
                case 7:
                    if(characters.contains(character7))
                        i--;
                    else
                        characters.add(character7);
                        break;
                case 8:
                    if(characters.contains(character8))
                        i--;
                    else
                        characters.add(character8);
                        break;
                case 9:
                    if(characters.contains(character9))
                        i--;
                    else
                        characters.add(character9);
                        break;
                default:
                    i--;
                    System.out.println("Значення виходить за діапазон можливих меж! Спробуйте знову!");
                    break;
            }
        }
        return characters;
    }
}
