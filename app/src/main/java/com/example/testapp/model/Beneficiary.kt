package com.example.testapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Beneficiary(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val beneType: String,
    val designationCode: String,
    val socialSecurityNumber: String,
    val dateOfBirth: String,
    val phoneNumber: String,
    val beneficiaryAddress: Address
) : Parcelable {
    val designation: String
        get() = when (designationCode) {
            "P" -> "Primary"
            "C" -> "Contingent"
            else -> "Unknown"
        }
}
@Parcelize
data class Address(
    val firstLineMailing: String,
    val scndLineMailing: String,
    val city: String,
    val zipCode: String,
    val stateCode: String,
    val country: String
) : Parcelable
