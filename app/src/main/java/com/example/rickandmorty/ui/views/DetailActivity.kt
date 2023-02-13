package com.example.csgoskins.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import coil.load
import com.example.csgoskins.R
import com.example.csgoskins.databinding.ActivityDetailBinding
import com.example.csgoskins.databinding.ActivityMainBinding
import com.example.csgoskins.domain.models.CharacterModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras

        bundle?.apply {
            val item: CharacterModel? = getParcelable("item_data")
            binding.detailIv.load(item?.image)
            binding.nameTv.text = item?.name
            binding.detailTv.text =
                item?.created + "\n" + item?.gender + "\n" + item?.name + "\n" + item?.species + "\n" + item?.status + "\n" + item?.type + "\n" + item?.url
        }

        binding.backButton.setOnClickListener(View.OnClickListener { view ->
            onBackPressed()
        })

    }

}