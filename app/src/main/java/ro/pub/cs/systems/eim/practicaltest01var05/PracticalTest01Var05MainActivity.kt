package ro.pub.cs.systems.eim.practicaltest01var05

import androidx.activity.enableEdgeToEdge
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class PracticalTest01Var05MainActivity : AppCompatActivity() {

    // O listă pentru a stoca textele butoanelor apăsate
    private val pressedButtons = mutableListOf<String>()
    private lateinit var textView: TextView
    private var buttonPressCount = 0  // Variabilă pentru numărarea apăsărilor de butoane
    private lateinit var buttonStartSecondActivity: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test_01_var05_main)
        buttonStartSecondActivity = findViewById(R.id.buttonStartSecondActivity)  // Legătura corectă a butonului

        savedInstanceState?.let {
            buttonPressCount = it.getInt("BUTTON_PRESS_COUNT", 0)
            // Afișăm un Toast cu valoarea restaurată
            Toast.makeText(this, "Numărul total de apăsări restaurat: $buttonPressCount", Toast.LENGTH_SHORT).show()
        }

        // Setarea pentru margini pe ecran complet
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(
                left = systemBars.left,
                top = systemBars.top,
                right = systemBars.right,
                bottom = systemBars.bottom
            )
            insets
        }

        // Referință la TextView pentru a afișa textele butoanelor
        textView = findViewById(R.id.textView)

        // Referințe la butoane
        val buttonTopLeft: Button = findViewById(R.id.buttonTopLeft)
        val buttonTopRight: Button = findViewById(R.id.buttonTopRight)
        val buttonCenter: Button = findViewById(R.id.buttonCenter)
        val buttonBottomLeft: Button = findViewById(R.id.buttonBottomLeft)
        val buttonBottomRight: Button = findViewById(R.id.buttonBottomRight)
        val buttonNewActivity: Button = findViewById(R.id.buttonNewActivity)

        // Funcție pentru actualizarea textului în TextView
        fun updateTextView(buttonText: String) {
            if (!pressedButtons.contains(buttonText)) {
                pressedButtons.add(buttonText)
                textView.text = "Buton apăsat: " + pressedButtons.joinToString(", ")
            }
            buttonPressCount++  // Incrementăm contorul la fiecare apăsare de buton

        }

        // Setare click listener pentru fiecare buton
        buttonTopLeft.setOnClickListener { updateTextView(buttonTopLeft.text.toString()) }
        buttonTopRight.setOnClickListener { updateTextView(buttonTopRight.text.toString()) }
        buttonCenter.setOnClickListener { updateTextView(buttonCenter.text.toString()) }
        buttonBottomLeft.setOnClickListener { updateTextView(buttonBottomLeft.text.toString()) }
        buttonBottomRight.setOnClickListener { updateTextView(buttonBottomRight.text.toString()) }
        buttonStartSecondActivity.setOnClickListener {
            val template = "Acesta este șablonul transmis la activitatea secundară."

            // Creăm intenția pentru a lansa SecondActivity
            val intent = Intent(this, PracticalTest01Var05SecondaryActivity::class.java)
            intent.putExtra("TEMPLATE", template)
            startActivityForResult(intent, 1)  // Lansează SecondActivity și așteaptă rezultatul
        }

        buttonNewActivity.setOnClickListener {
            val intent = Intent(this, NumberButtonPressesActivity::class.java)
            intent.putExtra("BUTTON_PRESS_COUNT", buttonPressCount)  // Transmitem numărul de apăsări
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("BUTTON_PRESS_COUNT", buttonPressCount)
    }

    // Funcția de activare a marginii Edge-to-Edge
    private fun enableEdgeToEdge() {
        window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }

    // Preluăm rezultatul din SecondActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val result = data?.getStringExtra("RESULT")
                textView.text = "Rezultatul: $result"
            } else if (resultCode == RESULT_CANCELED) {
                val result = data?.getStringExtra("RESULT")
                textView.text = "Rezultatul: $result"
            }
        }
    }
}
