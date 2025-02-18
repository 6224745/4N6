package org.ballestero

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.ballestero.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDouble.setOnClickListener {
            val service: Service = RetrofitUtil.get()
            val number: Int = binding.editTextNumber.text.toString().toInt()
            val call: Call<Int> = service.getDouble(number)
            call.enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        val resultat = response.body()
                        Toast.makeText(this@MainActivity, "Doubled: $resultat", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failure: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}