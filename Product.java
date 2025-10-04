
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Product{
    private String name;
    private double price;
    private int quantity; 
    private LocalDate expireDate; 
    private String catagory; 
    private int upperRange; 
    private int lowerRange; 


    public Product(String name, double price, int quantity, String catagoty, LocalDate expireDate){
        this.name = name; 
        this.expireDate = expireDate; 
        this.lowerRange = lowerRange;
        this.upperRange = upperRange; 

    }


    public String getName(){
        return name; 
    }


    public LocalDate expireDate(){
        return expireDate; 
    }
    //lower and upper range from a different file 
    public Integer checkExpirationDate(){
        LocalDate today = LocalDate.now();
        long daysLeft = ChronoUnit.DAYS.between(today, expireDate);
        if(daysLeft <= lowerRange){
            return 0;
        } else {
            if (daysLeft <= upperRange){
                return 1;
            } else {
                return 2; 
            }
        }

    }
}