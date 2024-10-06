public class Student {
    //Class with the information of the student - 40381
    int id;
    long time;
    double credit;
    double timeCredit; // Time that the student has avaliable in the park - 40381
    int carPark; // TO check if the car is inside - 40381

    public Student (int id, long time, double credit, double timeCredit, int carPark )
    {
        this.id = id;
        this.time = time;
        this.credit = credit;
        this.timeCredit = timeCredit;
        this.carPark = carPark;
    }
    
}
