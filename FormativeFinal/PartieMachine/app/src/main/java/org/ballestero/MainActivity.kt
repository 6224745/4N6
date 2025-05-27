package org.ballestero

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.ballestero.databinding.ActivityMainBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.ButSend.setOnClickListener {
            val service = RetrofitUtil.get()
            val request = binding.Text.text.toString()
            val call: Call<String> = service.envoiString(request)
            call.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        // http 200 http tout s'est bien passé
                        val resultat = response.body()
                        binding.TextReponse.text = resultat ?: "Réponse vide"
                    } else {
                        // cas d'erreur http 400 404 etc.
                        binding.TextReponse.text = "Erreur HTTP"
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    // erreur accès réseau ou alors GSON
                    binding.TextReponse.text = "Erreur de connexion"
                }
            })
        }
    }
}