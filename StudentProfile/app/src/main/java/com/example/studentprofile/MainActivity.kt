package com.example.studentprofile

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.studentprofile.databinding.ActivityMainBinding
import com.example.studentprofile.utils.gone
import com.example.studentprofile.utils.hideKeyboard
import com.example.studentprofile.utils.show
import com.example.studentprofile.utils.toAcademicRanking
import com.example.studentprofile.utils.toast
import com.example.studentprofile.utils.trimmedText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressGpa.max = 40   // 4.0 x 10

        // Hien diem mac dinh ban dau
        displayGpa(3.0)

        setupUpdateClick()
        setupLiveTyping()
    }

    // ============ 1. BAT SU KIEN CLICK + VALIDATE ============
    private fun setupUpdateClick() {
        binding.btnUpdateGpa.setOnClickListener {

            // B2: lay chuoi bang extension trimmedText()
            // (doi dau phay thanh dau cham cho ban phim dung dau phay)
            val rawInput = binding.edtGpaInput.trimmedText().replace(',', '.')

            // B3: parse an toan, tra ve null neu la chu hoac rong (khong crash)
            val newGpa = rawInput.toDoubleOrNull()

            // B4: kiem tra khoang hop le
            if (newGpa == null || newGpa !in 0.0..4.0) {
                // B5: gan loi truc tiep len EditText
                binding.edtGpaInput.error = "Vui long nhap GPA hop le (0.0 - 4.0)"
                binding.edtGpaInput.requestFocus()
                toast("Diem so khong hop le, vui long kiem tra lai!")
                return@setOnClickListener   // dung thuc thi
            }

            // Du lieu hop le: xoa loi va cap nhat
            binding.edtGpaInput.error = null
            binding.edtGpaInput.hideKeyboard()
            updateStudentScore(newGpa)
        }
    }

    // ============ 2. LANG NGHE NGUOI DUNG GO TUNG KY TU ============
    private fun setupLiveTyping() {
        binding.edtGpaInput.doOnTextChanged { text, _, _, _ ->
            val input = text?.toString()?.trim()?.replace(',', '.') ?: ""

            if (input.isNotEmpty()) {
                // Tu dong xoa loi do cu khi nguoi dung bat dau sua
                binding.edtGpaInput.error = null

                // Xem truoc xep loai theo thoi gian thuc
                val tempScore = input.toDoubleOrNull()
                if (tempScore != null && tempScore in 0.0..4.0) {
                    binding.tvPreviewRanking.text =
                        "Du kien: ${tempScore.toAcademicRanking()}"
                    binding.tvPreviewRanking.show()   // dung extension
                } else {
                    binding.tvPreviewRanking.gone()
                }
            } else {
                binding.tvPreviewRanking.gone()
            }
        }
    }

    // ============ 3. CAP NHAT GIAO DIEN (chi goi khi du lieu hop le) ============
    private fun updateStudentScore(newGpa: Double) {
        displayGpa(newGpa)
        binding.edtGpaInput.text?.clear()
        binding.tvPreviewRanking.gone()
        toast("Da cap nhat GPA: $newGpa")
        Log.d("STUDENT_GPA", "GPA moi = $newGpa")
    }

    // Ve GPA, thanh tien do va xep loai len the thong tin
    private fun displayGpa(gpa: Double) {
        with(binding) {
            tvGpa.text = gpa.toString()
            progressGpa.setProgressCompat((gpa * 10).toInt(), true)
            tvRanking.text = "Xep loai: ${gpa.toAcademicRanking()}"
        }
    }
}