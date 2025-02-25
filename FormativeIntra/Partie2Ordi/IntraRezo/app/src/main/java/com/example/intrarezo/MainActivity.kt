package com.example.intrarezo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intrarezo.databinding.ActivityMainBinding
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
        binding.btnEnvoi.setOnClickListener({
            val service: Service = RetrofitUtil.get()
            val num: String = binding.num.text.toString()
            val call: Call<String> = service.listReposString(num)
            call.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        // http 200 http tout s'est bien passé
                        val resultat = response.body()
                        var list: List<String>
                        if (resultat != null) {
                            for(i in resultat) {
                                list = resultat.split(",")
                                var resultat = ""
                                for (i in 0..list.size - 1) {
                                    resultat += list[i] + "\n"
                                }
                                binding.Text.text = resultat
                            }
                        }
                    } else {
                        // cas d'erreur http 400 404 etc.
                        binding.Text.text = "REPONSE ERREUR : " + response.code()
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    // erreur accès réseau ou alors GSON
                    binding.Text.text = "PAS DE REPONSE : " + t.message
                }
            })
        })
    }
}