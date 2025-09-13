package com.example.riderapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView rvProducts;
    private ProductAdapter adapter;
    private List<Product> productList = new ArrayList<>();
    private List<Product> filteredList = new ArrayList<>();

    private EditText etSearch;
    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cakes);

        // Initialize views
        rvProducts = findViewById(R.id.rvProducts);
        etSearch = findViewById(R.id.etSearch);
        chipGroup = findViewById(R.id.chipGroup);

        // Setup RecyclerView
        rvProducts.setLayoutManager(new GridLayoutManager(this, 3));

        // Load products
        loadProducts();

        // Initialize adapter with filteredList (start empty)
        adapter = new ProductAdapter(filteredList);
        rvProducts.setAdapter(adapter);

        // Setup chips and search
        setupChips();
        setupSearch();

        // Show default category ("Cakes") at start
        Chip defaultChip = chipGroup.findViewById(R.id.chipCakes);
        if (defaultChip != null) {
            defaultChip.setChecked(true);
            filterByCategory(defaultChip.getText().toString());
        }
    }

    // Load all products
    private void loadProducts() {
        productList.clear();

        // ====== CAKES ======
        productList.add(new Product("Choco Cherry Cake", R.drawable.cake1, 150, "Cakes"));
        productList.add(new Product("Pastel Delight Round Cake", R.drawable.cake2, 180, "Cakes"));
        productList.add(new Product("Creamy Choco Cake", R.drawable.cake3, 160, "Cakes"));
        productList.add(new Product("Mocha Celebration Cake Round", R.drawable.cake4, 200, "Cakes"));
        productList.add(new Product("Chocolate Cake", R.drawable.cake5, 170, "Cakes"));
        productList.add(new Product("Junior Cake, Ube Temptation", R.drawable.cake6, 190, "Cakes"));
        productList.add(new Product("Choco Celebration On Cake Round", R.drawable.cake7, 210, "Cakes"));
        productList.add(new Product("Yema Round Celebration On Cake", R.drawable.cake8, 180, "Cakes"));
        productList.add(new Product("Choco Caramel Cake", R.drawable.cake9, 160, "Cakes"));
        productList.add(new Product("Moist Cake", R.drawable.cake10, 150, "Cakes"));
        productList.add(new Product("Mocha Celebration On Cake Rectangle", R.drawable.cake11, 200, "Cakes"));
        productList.add(new Product("Ube Macapuno Cake", R.drawable.cake12, 180, "Cakes"));
        productList.add(new Product("Yema Rectangle Celebration On Cake", R.drawable.cake13, 180, "Cakes"));
        productList.add(new Product("Roll Choco Fudge", R.drawable.cake14, 140, "Cakes"));
        productList.add(new Product("Roll, Ube Macapuno", R.drawable.cake15, 150, "Cakes"));
        productList.add(new Product("Roll, Nutty Caramel Cake", R.drawable.cake16, 160, "Cakes"));
        productList.add(new Product("Junior Cake, Mango Cream Deluxe", R.drawable.cake17, 200, "Cakes"));
        productList.add(new Product("Choco Celebration On Cake Rectangle", R.drawable.cake18, 210, "Cakes"));
        productList.add(new Product("Butterfly Sanctuary", R.drawable.cake19, 190, "Cakes"));
        productList.add(new Product("Beauty Cake", R.drawable.cake20, 180, "Cakes"));
        productList.add(new Product("Space Adventure", R.drawable.cake21, 220, "Cakes"));
        productList.add(new Product("Glitz’n Glam Cake", R.drawable.cake22, 200, "Cakes"));
        productList.add(new Product("Flamingo Cake", R.drawable.cake23, 180, "Cakes"));
        productList.add(new Product("Enchanted Cake", R.drawable.cake24, 190, "Cakes"));
        productList.add(new Product("Candy Drizzle Cake", R.drawable.cake25, 170, "Cakes"));
        productList.add(new Product("Unicorn Cake", R.drawable.cake26, 200, "Cakes"));
        productList.add(new Product("Cat Castle Cake", R.drawable.cake27, 160, "Cakes"));
        productList.add(new Product("Peppa Cake", R.drawable.cake28, 180, "Cakes"));
        productList.add(new Product("Ice Cream Heaven Cake", R.drawable.cake29, 170, "Cakes"));
        productList.add(new Product("Rainbow Unicorn", R.drawable.cake30, 210, "Cakes"));
        productList.add(new Product("Princess Cake", R.drawable.cake31, 200, "Cakes"));
        productList.add(new Product("Blooming Flower Cake", R.drawable.cake32, 190, "Cakes"));
        productList.add(new Product("Elephant Cake", R.drawable.cake33, 180, "Cakes"));
        productList.add(new Product("Racers Cake", R.drawable.cake34, 160, "Cakes"));
        productList.add(new Product("Detective Cake", R.drawable.cake35, 170, "Cakes"));
        productList.add(new Product("Nautical Cake", R.drawable.cake36, 180, "Cakes"));
        productList.add(new Product("Sea Adventure Cake", R.drawable.cake37, 190, "Cakes"));
        productList.add(new Product("Jungle Explore Cake", R.drawable.cake38, 200, "Cakes"));
        productList.add(new Product("Nursery Cake", R.drawable.cake39, 170, "Cakes"));
        productList.add(new Product("Spider Web Cake", R.drawable.cake40, 180, "Cakes"));

        // ====== BREAD ======
        productList.add(new Product("Taisan Soft Cake", R.drawable.bread1, 150, "Bread"));
        productList.add(new Product("Ubeng Ube Loaf", R.drawable.bread2, 160, "Bread"));
        productList.add(new Product("Pandecoconut", R.drawable.bread3, 140, "Bread"));
        productList.add(new Product("Pande esapana", R.drawable.bread4, 150, "Bread"));
        productList.add(new Product("Ube Cheese Pandesal", R.drawable.bread5, 120, "Bread"));
        productList.add(new Product("Mamon Cup", R.drawable.bread6, 100, "Bread"));
        productList.add(new Product("Delightful Treats Choco", R.drawable.bread7, 150, "Bread"));
        productList.add(new Product("Crinkles Cokie", R.drawable.bread8, 130, "Bread"));
        productList.add(new Product("Pinoy Tasty", R.drawable.bread9, 110, "Bread"));
        productList.add(new Product("Jumbo Sandwich Loaf", R.drawable.bread10, 180, "Bread"));
        productList.add(new Product("Wheat Bread", R.drawable.bread11, 150, "Bread"));

        // ====== PASTRY ======
        productList.add(new Product("Egg Pie Leche Plan", R.drawable.pastry1, 90, "Pastry"));
        productList.add(new Product("Brownie Bites", R.drawable.pastry2, 80, "Pastry"));
        productList.add(new Product("Cluster Ensaymada Ube with Cheese", R.drawable.pastry3, 100, "Pastry"));
        productList.add(new Product("Custard Surprise", R.drawable.pastry4, 85, "Pastry"));
        productList.add(new Product("Mini Cinamon Roll", R.drawable.pastry5, 70, "Pastry"));
        productList.add(new Product("Ensaymada Ube", R.drawable.pastry6, 95, "Pastry"));
        productList.add(new Product("Ensaymada Cheese", R.drawable.pastry7, 95, "Pastry"));
        productList.add(new Product("Snap n’ roll", R.drawable.pastry8, 80, "Pastry"));
        productList.add(new Product("Cheesy Ensaymada", R.drawable.pastry9, 100, "Pastry"));
        productList.add(new Product("Egg pie caramel", R.drawable.pastry10, 90, "Pastry"));
        productList.add(new Product("Cheesy Butter Softy", R.drawable.pastry11, 100, "Pastry"));
        productList.add(new Product("Mamon", R.drawable.pastry12, 70, "Pastry"));
        productList.add(new Product("Choco Bar", R.drawable.pastry13, 80, "Pastry"));
    }

    // Setup chips filter
    private void setupChips() {
        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            if (chip != null) {
                String category = chip.getText().toString();
                filterByCategory(category);
            }
        });
    }

    // Filter products by category
    private void filterByCategory(String category) {
        filteredList.clear();
        for (Product p : productList) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                filteredList.add(p);
            }
        }
        adapter.notifyDataSetChanged();
    }

    // Setup search filter
    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchProducts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    // Search products by name AND selected category
    private void searchProducts(String query) {
        filteredList.clear();
        String selectedCategory = getSelectedCategory();

        for (Product p : productList) {
            if (p.getCategory().equalsIgnoreCase(selectedCategory)
                    && p.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(p);
            }
        }
        adapter.notifyDataSetChanged();
    }

    // Get currently selected category from chips
    private String getSelectedCategory() {
        int checkedId = chipGroup.getCheckedChipId();
        Chip chip = chipGroup.findViewById(checkedId);
        return chip != null ? chip.getText().toString() : "Cakes";
    }
}
