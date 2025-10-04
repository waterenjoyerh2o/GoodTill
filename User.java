
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String userID;
    private List<Product> products;

    public User(String userName, String userID){
        this.userName = userName; 
        this.userID = userID;
        this.products = new ArrayList<>(); 

    }

    public String getName(){
        return userName;
    }

    public String getUSerId(){
        return userID;
    }

    public List<Product> getProducts(){
        return products; 
    }

    public static User fileInfo(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String username = reader.readLine().trim();
            String userId = reader.readLine().trim();
            User user = new User(username, userId);
        

            reader.readLine();

            String line;
            while((line = reader.readLine()) != null){

                String[] parts = line.split(",");

                if (parts.length == 5) {
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].replace("$", "").trim());
                    int quantity = Integer.parseInt(parts[2].replace("Quantity:", "").trim());
                    String category = parts[3].replace("Category:", "").trim();
                    LocalDate date = LocalDate.parse(parts[4].replace("Date:", "").trim());

                    Product product = new Product(name, price, quantity, category, date);
                    user.products.add(product);
                }
            }

            
            return user;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

}


