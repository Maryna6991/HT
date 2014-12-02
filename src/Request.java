
import static java.lang.Thread.sleep;

public class Request implements Runnable{
private String surname;
private double timeAccomodation;
private Hotel h;
private Thread t;
private boolean flag = true;

    Request(Hotel ob,String name, double timeAccomodation){
      this.h = ob;
      surname = name;
      this.timeAccomodation = timeAccomodation;
      t = new Thread(this, surname);
      t.start();
    }

    @Override
    public  void run() {  
     try{
        synchronized(t){
           while (flag){ 
               if(h.application(surname, timeAccomodation)){                         
                  Thread.sleep((long) timeAccomodation);
                  h.leave(surname);  
                  flag = false;        
               } 
           }
        }
     }
      catch(InterruptedException e) {}
    }
         

    public String getSurname(){
     return surname;
    }

    public void setSurname(String surname){
     this.surname = surname;
    }

    public double getTimeAccomodation(){
     return timeAccomodation;
    }

    public void setTimeAccomodation(double timeAccomodation){
     this.timeAccomodation = timeAccomodation;
    }

}