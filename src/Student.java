public class Student {
    //Class with the information of the student - 40381
    int id;
    long time;
    double credit;
    long timeCredit; // Time that the student has avaliable in the park - 40381 ; Change to long - 40381
    int carPark; // TO check if the car is inside - 40381

    public Student (int id, long time, double credit, long timeCredit, int carPark )
    {
        this.id = id;
        this.time = time;
        this.credit = credit;
        this.timeCredit = timeCredit;
        this.carPark = carPark;
    }
    
}
