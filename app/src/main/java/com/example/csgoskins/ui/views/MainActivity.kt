package com.example.csgoskins.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.csgoskins.databinding.ActivityMainBinding
import com.example.csgoskins.ui.adapters.CharactersAdapter
import com.example.csgoskins.ui.view_models.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var characterAdapter: CharactersAdapter
    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterAdapter = CharactersAdapter()

        binding.recyclerView.apply {
            adapter = characterAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                when {
                    it.load -> Toast.makeText(applicationContext, "Api Loading", Toast.LENGTH_SHORT)
                        .show()

                    it.success.isNotEmpty() -> {
                        Toast.makeText(applicationContext, "Almost Finished", Toast.LENGTH_LONG)
                            .show()
                        characterAdapter.submitList(it.success)
                        it.success.forEach { Log.i("***************", it.id.toString()) }
                    }

                    it.fail.isNotBlank() -> Toast.makeText(
                        applicationContext,
                        it.fail,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }

    }
}