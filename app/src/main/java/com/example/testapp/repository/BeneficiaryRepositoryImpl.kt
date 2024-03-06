package com.example.testapp.repository

import android.content.Context
import com.example.testapp.model.Address
import com.example.testapp.model.Beneficiary
import org.json.JSONArray
import java.io.IOException

class BeneficiaryRepositoryImpl(private val context: Context) : BeneficiaryRepository {

    override fun getBeneficiaries(): List<Beneficiary> {
        val jsonString = try {
            context.assets.open("beneficiaries.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }
        return parseBeneficiariesJson(jsonString)
    }

    private fun parseBeneficiariesJson(jsonString: String): List<Beneficiary> {
        val jsonArray = JSONArray(jsonString)
        val beneficiaries = mutableListOf<Beneficiary>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val beneficiaryAddress = jsonObject.getJSONObject("beneficiaryAddress")
            beneficiaries.add(
                Beneficiary(
                    firstName = jsonObject.optString("firstName"),
                    lastName = jsonObject.optString("lastName"),
                    beneType = jsonObject.optString("beneType"),
                    designationCode = jsonObject.optString("designationCode"),
                    socialSecurityNumber = jsonObject.optString("socialSecurityNumber"),
                    dateOfBirth = jsonObject.optString("dateOfBirth"),
                    middleName = jsonObject.optString("middleName", null),
                    phoneNumber = jsonObject.optString("phoneNumber"),
                    beneficiaryAddress = Address(
                        firstLineMailing = beneficiaryAddress.optString("firstLineMailing"),
                        scndLineMailing = beneficiaryAddress.optString("scndLineMailing", null),
                        city = beneficiaryAddress.optString("city"),
                        zipCode = beneficiaryAddress.optString("zipCode"),
                        stateCode = beneficiaryAddress.optString("stateCode"),
                        country = beneficiaryAddress.optString("country")
                    )
                )
            )
        }

        return beneficiaries.toList()
    }
}