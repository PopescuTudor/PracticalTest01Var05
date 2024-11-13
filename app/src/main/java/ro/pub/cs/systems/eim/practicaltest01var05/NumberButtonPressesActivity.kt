package ro.pub.cs.systems.eim.practicaltest01var05

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast

class NumberButtonPressesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.number_button_presses_activity)

        // Preluăm numărul de apăsări de butoane din Intent
        val buttonPressCount = intent.getIntExtra("BUTTON_PRESS_COUNT", 0)

        // Afișăm numărul de apăsări în Toast
        Toast.makeText(this, "Numărul total de apăsări: $buttonPressCount", Toast.LENGTH_SHORT).show()
    }
}