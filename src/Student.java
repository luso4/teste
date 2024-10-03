public class Student {
    //Class with the information of the student
    int id;
    long time;
    double credit;
    double timeCredit; // Time that the student has avaliable in the park
    int carPark; // TO check if the car is inside 

    public Student (int id, long time, double credit, double timeCredit, int carPark )
    {
        this.id = id;
        this.time = time;
        this.credit = credit;
        this.timeCredit = timeCredit;
        this.carPark = carPark;
    }
    
}
