package com.example.testapp.repository

import com.example.testapp.model.Beneficiary

interface BeneficiaryRepository {
    fun getBeneficiaries(): List<Beneficiary>
}