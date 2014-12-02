
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Hotel {
    private static int maxAmount = 5;
    private ArrayList<String> people;
     
    public Hotel(){
        people = new ArrayList<String>();  
    } 
    
    public Hotel(ArrayList<String> listPeople){
        this.people = listPeople;
    }

    public ArrayList<String> getPeople() {        
         return people;
    }

    
    public  boolean application(String name, double time){     
       synchronized(this){ 
           if (people.size()< maxAmount){         
            people.add(name);                                                       
            System.out.println("Поселился "+ name + " на "+time/1000+" секунд");            
            System.out.println("Занято "+ people.size()+" мест");           
            for(int i = 0;i<people.size();i++)
              System.out.println(people.get(i));                     
             return true;        
           }         
           else {
            System.out.println("Нет мест для "+ name); 
            try {
                this.wait();
            } catch (InterruptedException ex) {}
          return false;    
        }  
       }  
    }
    
 /*   public void changeFlag(){
        flag = !flag;
    }*/
    
    public synchronized void leave(String surname){
        synchronized(this){
         System.out.println("Выселился "+ surname);        
         people.remove(surname);
         this.notifyAll();
        }
     // flag=true;   
    } 
}
