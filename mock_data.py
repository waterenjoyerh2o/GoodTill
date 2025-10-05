import os
import random
from datetime import datetime, timedelta

OUTPUT_DIR = "customer_data_files"
NUM_CUSTOMERS = 10

FIRST_NAMES = ["Alex", "Bella", "Chris", "Dana", "Ethan", "Fiona", "George", "Hannah", "Ivan", "Julia"]
LAST_NAMES = ["Smith", "Jones", "Williams", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson"]

PRODUCT_CATALOG = {
    "Produce": ["Fuji Apples", "Organic Strawberries", "Ripe Avocados"],
    "Canned Goods": ["Black Beans Can", "Tuna in Water Can"],
    "Bakery": ["Artisan Sourdough Loaf"],
    "Beverages": ["Orange Juice Carton", "Sparkling Water (12pk)"],
    "Dairy": ["Milk Gallon (Whole)", "Yogurt (32oz)"]
}
CATEGORIES = list(PRODUCT_CATALOG.keys())

ITEM_PRICE_RANGES = {
    "Fuji Apples": (1.99, 3.99),
    "Organic Strawberries": (3.50, 5.50),
    "Ripe Avocados": (1.25, 2.50),
    "Black Beans Can": (0.79, 1.49),
    "Tuna in Water Can": (1.29, 2.99),
    "Artisan Sourdough Loaf": (3.99, 6.99),
    "Orange Juice Carton": (3.00, 5.50),
    "Sparkling Water (12pk)": (4.50, 7.50),
    "Milk Gallon (Whole)": (3.00, 5.00),
    "Yogurt (32oz)": (3.50, 6.50)
}
# --- End Configuration ---


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

        num_items_per_customer = random.randint(10, 20)
        order_lines = []
        for _ in range(num_items_per_customer):
            category = random.choice(CATEGORIES)
            item_name = random.choice(PRODUCT_CATALOG[category])
            quantity = random.randint(1, 4)

            min_price, max_price = ITEM_PRICE_RANGES.get(item_name, (1.00, 10.00))
            item_amount = f"{random.uniform(min_price, max_price):.2f}"
            
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
