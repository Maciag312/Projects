import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public static int LuckyNumber(final int n, final int k){
        ArrayList<Integer> PeopleToExecute = new ArrayList<>(); //It should be OneWayLinkedCircularList
        for(int i = 0; i<n;i++){
            PeopleToExecute.add(i);
        }
        Iterator it = PeopleToExecute.iterator();
        Integer last = 0;
        while(it.hasNext()){
            for(int i=0; i<k;i++){
                it.next();
            }
            last = (Integer)it.next();
        }
        return last.intValue();
    }
}

class Group{
    private ArrayList<Student> students = new ArrayList<>();
    private double averageGrade = 0.0;
    public double getAverageGrade() {
        return averageGrade;
    }
    public void add(String name, double averageGrade){
        students.add(new Student(name,averageGrade));
    }
    public List<Student> selectGood(){
        averageGrade();
        ArrayList<Student> std = new ArrayList<>();
        for(Student st: students) if(st.getAveMark()>averageGrade) std.add(st);
        return std;
    }
    public void removeBad(double limitValue){
        averageGrade();
        for(Student st: students) if(st.getAveMark()<limitValue) students.remove(st);
    }
    public double averageGrade(){
        double sum = 0.0;
        for(Student st: students) sum+=st.getAveMark();
        return sum/(double)students.size();
    }

}
class Student{
    String name;
    double aveMark;

    public Student(String name, double aveMark) {
        this.name = name;
        this.aveMark = aveMark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAveMark() {
        return aveMark;
    }

    public void setAveMark(double aveMark) {
        this.aveMark = aveMark;
    }
}