package ro.pub.cs.systems.eim.practicaltest01var05

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var05SecondaryActivity : AppCompatActivity() {

    private lateinit var templateTextView: TextView
    private lateinit var buttonVerify: Button
    private lateinit var buttonCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var05_secondary)

        // Preluăm șablonul transmis din MainActivity
        val template = intent.getStringExtra("TEMPLATE") ?: "Șablonul nu a fost transmis"

        // Legăm câmpul TextView pentru a arăta șablonul
        templateTextView = findViewById(R.id.templateTextView)
        templateTextView.text = template

        // Legăm butoanele
        buttonVerify = findViewById(R.id.buttonVerify)
        buttonCancel = findViewById(R.id.buttonCancel)

        // Setăm comportamentele pentru butoane
        buttonVerify.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("RESULT", "Verify a fost apăsat!")
            setResult(RESULT_OK, resultIntent)
            finish()  // Revenim la activitatea anterioară
        }

        buttonCancel.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("RESULT", "Cancel a fost apăsat!")
            setResult(RESULT_CANCELED, resultIntent)
            finish()  // Revenim la activitatea anterioară
        }
    }
}
