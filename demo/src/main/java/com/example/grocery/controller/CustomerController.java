import com.yourproject.data.CustomerDataLoader;
import com.yourproject.model.Customer;
import com.yourproject.model.FoodItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Path;
import java.util.List;

@RestController
public class CustomerController {
    
    private static final String DATA_DIR = "customer_data_files";
    private static final String CUSTOMER_FILE_FORMAT = "customer%s.txt";

    @GetMapping("/api/inventory/{customerId}")
    public List<FoodItem> getCustomerInventory(@PathVariable String customerId) {
        
        String filename = String.format(CUSTOMER_FILE_FORMAT, customerId);
        Path filePath = Path.of(DATA_DIR, filename);

        try {
            Customer customer = CustomerDataLoader.loadCustomerData(filePath);
            return customer.getInventory();
            
        } catch (Exception e) {
            System.err.println("Error loading customer data for ID: " + customerId + ". " + e.getMessage());
            return List.of(); 
        }
    }
}
