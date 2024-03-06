package com.example.testapp.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.OnItemClickListener
import com.example.testapp.model.Beneficiary
import com.example.testapp.repository.BeneficiaryRepositoryImpl
import com.example.testapp.repository.BeneficiaryRepository
import com.example.testapp.viewModel.BeneficiaryViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: BeneficiaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[BeneficiaryViewModel::class.java]

        val repository : BeneficiaryRepository = BeneficiaryRepositoryImpl(this)

        viewModel.fetchBeneficiaries(repository)
        viewModel.beneficiaries.observe(this) { beneficiaries ->
            setupRecyclerView(beneficiaries)
        }

    }

    private fun setupRecyclerView(beneficiaries: List<Beneficiary>) {
        val recyclerView = RecyclerView(this)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = ItemAdapter(beneficiaries, object : OnItemClickListener {
            override fun onItemClick(beneficiary: Beneficiary) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("beneficiary", beneficiary)
                startActivity(intent)
            }
        })
        setContentView(recyclerView)
    }
}