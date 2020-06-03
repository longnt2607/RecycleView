package vn.edu.ntu.nguyentruonglong.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import vn.edu.ntu.nguyentruonglong.controller.ICartController;
import vn.edu.ntu.nguyentruonglong.model.Product;

public class ShoppingCartActivity extends AppCompatActivity {

    TextView txtShoppingCart;
    ICartController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        controller = (ICartController) getApplication();
        addView();
    }
    private void addView() {
        txtShoppingCart = findViewById(R.id.txtShoppingCart);
        displayShoppingCart();
    }

    private void displayShoppingCart() {
        List<Product> list;
        list = controller.getShoppingCart();
        StringBuilder builder = new StringBuilder();
        for (Product p : list) {
            builder.append(p.getName())
                    .append("\t\t\t")
                    .append(p.getPrice())
                    .append("vnd\n");
        }
        if (builder.toString().length() > 0) {
            txtShoppingCart.setText(builder.toString());
        }
        else {
            txtShoppingCart.setText("Khong co mat hang nao trong gio hang");
        }
    }
}
