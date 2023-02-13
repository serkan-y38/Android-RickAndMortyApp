package com.example.csgoskins.ui.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.csgoskins.databinding.ActivityMainBinding
import com.example.csgoskins.domain.models.CharacterModel
import com.example.csgoskins.ui.adapters.CharactersAdapter
import com.example.csgoskins.ui.view_models.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var characterAdapter: CharactersAdapter
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var list: List<CharacterModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRv()
        actions()

    }


    private fun actions() {
        binding.sv.setOnQueryTextListener(this)

        binding.sv.setOnClickListener(View.OnClickListener { view ->
            setUpRv()
        })

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            search(newText)
        }
        return true
    }

    private fun search(text: String) {

        val filteredlist = ArrayList<CharacterModel>()

        list.forEach {
            if (it.name.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(it)
            }
        }
        if (filteredlist.isEmpty()) {
            setUpRv()
        } else {
            characterAdapter.submitList(filteredlist)
        }

    }

    private fun setUpRv() {

        characterAdapter = CharactersAdapter(applicationContext)

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
                        characterAdapter.submitList(it.success)
                        list = it.success
                        it.success.forEach { Log.i("***************", it.name.toString()) }
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