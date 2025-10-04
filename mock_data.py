import os
import random
from datetime import datetime, timedelta

OUTPUT_DIR = "customer_data_files"
NUM_CUSTOMERS = 10

FIRST_NAMES = ["Alex", "Bella", "Chris", "Dana", "Ethan", "Fiona", "George", "Hannah", "Ivan", "Julia"]
LAST_NAMES = ["Smith", "Jones", "Williams", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson"]

PRODUCT_CATALOG = {
    "Produce": ["Fuji Apples", "Ripe Bananas", "Seedless Grapes", "Organic Strawberries", "Ripe Avocados"],
    "Canned Goods": ["Black Beans Can", "Diced Tomatoes Can", "Tuna in Water Can", "Cream of Mushroom Soup Can", "Peeled Peaches Can"],
    "Bakery": ["Artisan Sourdough Loaf", "Whole Wheat Rolls", "Bagels (6-pack)"],
    "Beverages": ["Fair Trade Coffee (Ground)", "Orange Juice Carton", "Sparkling Water (12pk)"],
    "Dairy": ["Milk Gallon (Whole)", "Cheddar Cheese Block", "Yogurt (32oz)"],
    "Meat & Seafood": ["Ground Beef (1lb)", "Salmon Fillet", "Chicken Breasts (2lb)"]
}
CATEGORIES = list(PRODUCT_CATALOG.keys())

def generate_random_data_file():
    print(f"Starting data generation. Creating directory: '{OUTPUT_DIR}'...")

    try:
        os.makedirs(OUTPUT_DIR, exist_ok=True)
        print(f"Directory '{OUTPUT_DIR}' ensured.")
    except Exception as e:
        print(f"Error creating directory: {e}")
        return

    for i in range(1, NUM_CUSTOMERS + 1):
        filename = f"customer{i:03d}.txt"
        filepath = os.path.join(OUTPUT_DIR, filename)

        first = random.choice(FIRST_NAMES)
        last = random.choice(LAST_NAMES)
        username = f"{first}{last}{random.randint(10, 99)}"
        account_id = f"{random.randint(10000000, 99999999)}"

        num_items_per_customer = random.randint(20, 30)
        order_lines = []
        for _ in range(num_items_per_customer):
            category = random.choice(CATEGORIES)
            item_name = random.choice(PRODUCT_CATALOG[category])
            quantity = random.randint(1, 4)
            
            if category in ["Produce", "Canned Goods", "Bakery"]:
                item_amount = f"{random.uniform(1.25, 6.99):.2f}"
            elif category == "Dairy":
                item_amount = f"{random.uniform(2.50, 15.00):.2f}"
            else: 
                item_amount = f"{random.uniform(5.00, 35.00):.2f}"
            
            start_date = datetime(2025, 9, 1)
            end_date = datetime.now()
            time_difference = end_date - start_date
            random_days = random.randint(0, time_difference.days)    
            purchase_date = (start_date + timedelta(days=random_days)).strftime("%Y-%m-%d")
            order_line = f"{item_name}, ${item_amount}, Quantity: {quantity}, Category: {category}, Date: {purchase_date}"
            order_lines.append(order_line)

        try:
            with open(filepath, 'w') as f:
                f.write(username + "\n")
                f.write(account_id + "\n")
                f.write("--- ORDERS ---\n")
                for line in order_lines:
                    f.write(line + "\n")

            print(f"  > Created {filepath} with {num_items_per_customer} items.")

        except IOError as e:
            print(f"Error writing to file {filepath}: {e}")
            
    print("\nData generation complete")

if __name__ == "__main__":
    generate_random_data_file()
