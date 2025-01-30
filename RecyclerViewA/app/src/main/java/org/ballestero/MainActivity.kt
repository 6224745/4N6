package org.ballestero

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import org.ballestero.Adapter.MonAdapter
import org.ballestero.databinding.ActivityMainBinding
import org.ballestero.model.Secret
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MonAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        fillRecycler()
    }
    private fun setupRecycler() {
        adapter = MonAdapter() // Créer l'adapteur
        binding.rvPersonneAdapter.adapter = adapter // Assigner l'adapteur au RecyclerView
        binding.rvPersonneAdapter.setHasFixedSize(true) // Option pour améliorer les performances
        binding.rvPersonneAdapter.addItemDecoration( // Ajouter un séparateur entre chaque élément
            DividerItemDecoration(
                binding.rvPersonneAdapter.context, DividerItemDecoration.VERTICAL
            )
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun fillRecycler() {
        val items: MutableList<Secret> = mutableListOf()
        for (i in 1..1000) {
            val item = Secret(
                nom = "Objet #$i",
                date = LocalDateTime.of(2025, (1..12).random(), (1..25).random(), (1..23).random(), (1..59).random(), (1..59).random()),
                nbGrand = (0..100).random().toLong()
            )
            items.add(item)
        }
        adapter.submitList(items) // Pour changer le contenu de la liste, utiliser submitList de l'adapteur
    }
}