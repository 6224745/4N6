package org.ballestero

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.ballestero.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setnavigation()
        binding.btnenvoyer
        title = " Main Activity"
    }
    fun setnavigation(){
        binding.btnenvoyer.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("text", binding.editText.text.toString())
            startActivity(intent)
        }
    }
}