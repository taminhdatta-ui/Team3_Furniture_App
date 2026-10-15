package com.example.duannoithat

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

import androidx.core.graphics.toColorInt

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout)

        // Ẩn ActionBar
        supportActionBar?.hide()

        // Xử lý WindowInsets để vừa vặn màn hình (Edge-to-Edge)
        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bắt sự kiện click nút back để đóng màn hình
        val btnBack = findViewById<View>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        // Ánh xạ các phần tử của Section Payment Method
        val cardCod = findViewById<MaterialCardView>(R.id.cardCod)
        val cardBank = findViewById<MaterialCardView>(R.id.cardBank)
        val cardWallet = findViewById<MaterialCardView>(R.id.cardWallet)

        val rbCod = findViewById<RadioButton>(R.id.rbCod)
        val rbBank = findViewById<RadioButton>(R.id.rbBank)
        val rbWallet = findViewById<RadioButton>(R.id.rbWallet)

        val tvCodTitle = findViewById<TextView>(R.id.tvCodTitle)
        val tvBankTitle = findViewById<TextView>(R.id.tvBankTitle)
        val tvWalletTitle = findViewById<TextView>(R.id.tvWalletTitle)

        // Hàm thay đổi trạng thái chọn phương thức thanh toán
        fun selectPaymentMethod(selected: String) {
            val greenColor = "#0C5E43".toColorInt()
            val darkColor = "#1A1D1E".toColorInt()
            val grayBorder = "#E5E5EA".toColorInt()
            val grayTint = "#8A8A8E".toColorInt()

            // Cash on Delivery (COD)
            val isCod = selected == "COD"
            cardCod.strokeColor = if (isCod) greenColor else grayBorder
            cardCod.strokeWidth = if (isCod) dpToPx(2) else dpToPx(1)
            rbCod.isChecked = isCod
            rbCod.buttonTintList = ColorStateList.valueOf(if (isCod) greenColor else grayTint)
            tvCodTitle.setTextColor(if (isCod) greenColor else darkColor)

            // Bank Transfer
            val isBank = selected == "BANK"
            cardBank.strokeColor = if (isBank) greenColor else grayBorder
            cardBank.strokeWidth = if (isBank) dpToPx(2) else dpToPx(1)
            rbBank.isChecked = isBank
            rbBank.buttonTintList = ColorStateList.valueOf(if (isBank) greenColor else grayTint)
            tvBankTitle.setTextColor(if (isBank) greenColor else darkColor)

            // E-wallet
            val isWallet = selected == "WALLET"
            cardWallet.strokeColor = if (isWallet) greenColor else grayBorder
            cardWallet.strokeWidth = if (isWallet) dpToPx(2) else dpToPx(1)
            rbWallet.isChecked = isWallet
            rbWallet.buttonTintList = ColorStateList.valueOf(if (isWallet) greenColor else grayTint)
            tvWalletTitle.setTextColor(if (isWallet) greenColor else darkColor)
        }

        cardCod.setOnClickListener { selectPaymentMethod("COD") }
        cardBank.setOnClickListener { selectPaymentMethod("BANK") }
        cardWallet.setOnClickListener { selectPaymentMethod("WALLET") }

        // Bắt sự kiện nút btnPlaceOrder để hiển thị Toast thông báo đặt hàng thành công
        val btnPlaceOrder = findViewById<View>(R.id.btnPlaceOrder)
        btnPlaceOrder.setOnClickListener {
            Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}