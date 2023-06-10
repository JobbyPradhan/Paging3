package com.example.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()
    private lateinit var productListAdapter:ProductListAdapter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRec()
        binding.etSearch.doAfterTextChanged {
            viewModel.onEvent(
                BrandListingEvent.OnSearchQueryChanged(it.toString())
            )
        }
        viewModel.pagingDataFlow.observe(this) {
            productListAdapter.submitData(this.lifecycle, it)
        }
    }

    private fun initRec() {
        productListAdapter =
            ProductListAdapter()
        binding.apply {
            recList.apply {
                setHasFixedSize(true)
                itemAnimator = null
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = productListAdapter
            }
        }
    }
}