package com.example.duannoithat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 1. DATA MODEL
data class CartItem(
    val name: String,
    val type: String,
    val price: Double,
    val imageRes: Int,
    var quantity: Int
)

// 2. ADAPTER NẠP ẢNH TỪ DRAWABLE
class CartAdapter(private val list: List<CartItem>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProduct: ImageView = view.findViewById(R.id.ivProduct)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvType: TextView = view.findViewById(R.id.tvType)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvQuantity: TextView = view.findViewById(R.id.tvQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvName.text = item.name
        holder.tvType.text = item.type
        holder.tvPrice.text = "$${item.price.toInt()}"
        holder.tvQuantity.text = item.quantity.toString()

        // Hiển thị ảnh thật từ drawable
        holder.ivProduct.setImageResource(item.imageRes)
    }

    override fun getItemCount() = list.size
}

// 3. MAIN CART ACTIVITY
class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        supportActionBar?.hide()

        // Nạp danh sách sản phẩm
        val cartList = listOf(
            CartItem("Yellow Chair", "Armchair High", 80.0, R.drawable.yellow_chair, 1),
            CartItem("White Chair", "Armchair High", 175.0, R.drawable.white_chair, 1),
            CartItem("Black Chair", "Armchair High", 80.0, R.drawable.black_chair, 0)
        )

        val rvCart = findViewById<RecyclerView>(R.id.rvCart)
        rvCart.layoutManager = LinearLayoutManager(this)
        rvCart.adapter = CartAdapter(cartList)

        // Bấm nút quay lại (Back)
        findViewById<View>(R.id.btnBack)?.setOnClickListener {
            finish()
        }

        // Chuyển sang màn CheckoutActivity khi bấm Proceed to Checkout
        findViewById<AppCompatButton>(R.id.btnCheckout).setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }
    }
}