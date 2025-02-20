package com.example.exerciceretrofitlistes

import android.os.Bundle
import android.telecom.Call
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.exerciceretrofitlistes.databinding.ActivityMainBinding
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MonAdapter
    private lateinit var adapter2: ObjetAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        fillRecycler()
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun setupRecycler() {
        adapter = MonAdapter()
        binding.rvMonAdapter.adapter = adapter
        binding.rvMonAdapter.setHasFixedSize(true)
        binding.rvMonAdapter.addItemDecoration(
            DividerItemDecoration(
                binding.rvMonAdapter.context, DividerItemDecoration.VERTICAL
            )
        )
        adapter2 = ObjetAdapter() // Créer l'adapteur
        binding.objectcomplexAdapter.adapter = adapter2 // Assigner l'adapteur au RecyclerView
        binding.objectcomplexAdapter.setHasFixedSize(true) // Option pour améliorer les performances
        binding.objectcomplexAdapter.addItemDecoration( // Ajouter un séparateur entre chaque élément
            DividerItemDecoration(
                binding.objectcomplexAdapter.context, DividerItemDecoration.VERTICAL
            )
        )
    }
    private fun fillRecycler() {
        val items: MutableList<Long> = mutableListOf()
        val service: Service = RetrofitUtil.get()
        val call: retrofit2.Call<List<Long>> = service.listLong()
        call.enqueue(object : Callback<List<Long>>{
            override fun onResponse(call: retrofit2.Call<List<Long>>, response: retrofit2.Response<List<Long>>) {
                if (response.isSuccessful) {
                    val resultat = response.body()
                    if (resultat != null) {
                        items.addAll(resultat)
                        adapter.submitList(items)
                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<List<Long>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        val call2 : retrofit2.Call<List<ObjetComplex>> = service.listComplex()
        call2.enqueue(object : Callback<List<ObjetComplex>> {
            override fun onResponse(call: retrofit2.Call<List<ObjetComplex>>, response: retrofit2.Response<List<ObjetComplex>>) {
                if (response.isSuccessful) {
                    val resultat = response.body()
                    if (resultat != null) {
                        val listObjet = mutableListOf<ObjetComplex>()
                        for (i in resultat.indices) {
                            val objet = ObjetComplex(
                                A = resultat[i].A,
                                B = resultat[i].B,
                                C = resultat[i].C
                            )
                            listObjet.add(objet)
                        }
                        adapter2.submitList(listObjet)
                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<List<ObjetComplex>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}