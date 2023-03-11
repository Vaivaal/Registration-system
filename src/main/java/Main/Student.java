package Main;

import java.util.List;

public class Student {

    private String name;
    private String surname;
    private int group;
    private int id;

    private List<String> datesAttended;
    private List<String> datesDidntAttend;
    private List<String> attendance;

    public Student(String name, String surname, int group, int id){
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.id = id;
    }

    public Student(String name, String surname, int group, int id, List<String> attendance,
                   List<String> datesAttended, List<String> datesDidntAttend){
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.id = id;
        this.datesAttended = datesAttended;
        this.datesDidntAttend = datesDidntAttend;
        this.attendance = attendance;
    }

    public String getName() {
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public int getGroup(){
        return group;
    }

    public int getId(){
        return id;
    }

    public List<String> getAttended() {
        return datesAttended;
    }

    public List<String> getDidntAttend() {
        return datesDidntAttend;
    }

    public List<String> getAttendance(){
        return attendance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setGroup(int group){
        this.group = group;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setAttended(List<String> datesAttended) {
        this.datesAttended = datesAttended;
    }

    public void setDidntAttend(List<String> datesDidntAttend) {
        this.datesDidntAttend = datesDidntAttend;
    }

    public void setAttendance(List<String> attendance){
        this.attendance = attendance;
    }
}
