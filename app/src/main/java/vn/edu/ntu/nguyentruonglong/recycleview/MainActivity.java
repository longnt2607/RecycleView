package vn.edu.ntu.nguyentruonglong.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.nguyentruonglong.controller.CartController;
import vn.edu.ntu.nguyentruonglong.controller.ICartController;
import vn.edu.ntu.nguyentruonglong.model.Product;

public class MainActivity extends AppCompatActivity {

    private List<Product> productList;
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        //addEvent();
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtPrice, txtDecs;
        ImageView imvAddToCart;
        Product product;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtNameProduct);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDecs = this.itemView.findViewById(R.id.txtDesc);
            imvAddToCart = this.itemView.findViewById(R.id.imvAddToCart);
        }

        public void bind(Product p) {
            this.product = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDecs.setText(p.getDesc());
        }
    }

    private class Adapter extends RecyclerView.Adapter<ProductViewHolder> {

        List<Product> productList;

        public Adapter(List<Product> productList) {
            this.productList = productList;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater(); // mainActivity cung cap
            View view = inflater.inflate(R.layout.product, parent, false); // tra ve layout ten la product
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) { //position la vi tri cua product trong adapter
            holder.bind(productList.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), holder.toString(), Toast.LENGTH_LONG);
                }
            });
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }
    }

    private void addControl() {
        recyclerView = findViewById(R.id.rvMatHang);
        productList = new ArrayList<>();
        ICartController controller = (ICartController) getApplication();
        productList = controller.getAllProduct();
        adapter = new Adapter(productList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void addEvent() {
        CartController cartController = new CartController();
        productList = cartController.getAllProduct();
        adapter.notifyDataSetChanged();
    }
}
