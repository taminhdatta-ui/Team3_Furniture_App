package com.example.studentprofile.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast

// ---- An / Hien View ----
fun View.show() { visibility = View.VISIBLE }

fun View.gone() { visibility = View.GONE }

fun View.invisible() { visibility = View.INVISIBLE }

// ---- Toast nhanh gon ----
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

// ---- Lay chuoi tu EditText da cat khoang trang thua ----
fun EditText.trimmedText(): String = text.toString().trim()

// ---- Quy doi GPA (he 4.0) sang xep loai hoc luc ----
fun Double.toAcademicRanking(): String = when {
    this >= 3.6 -> "Xuat sac (Excellent)"
    this >= 3.2 -> "Gioi (Very Good)"
    this >= 2.5 -> "Kha (Good)"
    this >= 2.0 -> "Trung binh (Average)"
    this >= 1.0 -> "Yeu (Weak)"
    else -> "Kem (Poor)"
}

// ---- Them: dong ban phim sau khi cap nhat ----
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}