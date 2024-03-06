package com.example.testapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.model.Beneficiary
import com.example.testapp.repository.BeneficiaryRepository

class BeneficiaryViewModel: ViewModel() {
    private val _beneficiaries = MutableLiveData<List<Beneficiary>>()
    val beneficiaries: LiveData<List<Beneficiary>> = _beneficiaries

    fun fetchBeneficiaries(repository: BeneficiaryRepository) {
        _beneficiaries.value = repository.getBeneficiaries()
    }
}