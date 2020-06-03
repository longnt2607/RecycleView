package vn.edu.ntu.nguyentruonglong.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.ntu.nguyentruonglong.controller.ICartController;
import vn.edu.ntu.nguyentruonglong.model.Product;

public class ShoppingCartActivity extends AppCompatActivity {

    TextView txtShoppingCart;
    ICartController controller;
    Button btnXoa;
    Button btnMua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        controller = (ICartController) getApplication();
        addView();
        addEvent();
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

    private void addEvent() {
        btnXoa = findViewById(R.id.btnCancle);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.clearShoppingCart();
                Toast.makeText(getApplicationContext(), "Xoa gio hang thanh cong.", Toast.LENGTH_SHORT);
                displayShoppingCart();
            }
        });

        btnMua = findViewById(R.id.btnBuy);
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> list;
                list = controller.getShoppingCart();
                int sum = 0;
                for (Product p : list) {
                    sum += p.getPrice();
                }
                Toast.makeText(getApplicationContext(), "Tong gia tri cua gio hang: " + sum + " Vnd.", Toast.LENGTH_SHORT);
            }
        });
    }
}
