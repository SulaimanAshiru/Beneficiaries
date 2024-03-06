package com.example.testapp.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.example.testapp.formatDate
import com.example.testapp.model.Beneficiary

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val beneficiary: Beneficiary? = intent.extras?.getParcelable("beneficiary")

        val scrollView = ScrollView(this).apply {
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        }

        val container = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            setPadding(32, 32, 32, 32)
        }

        beneficiary?.let {
            addDetailTextView(container, "Name", "${it.firstName} ${it.lastName}")
            addDetailTextView(container, "Type", it.beneType)
            addDetailTextView(container, "Designation", it.designation)
            addDetailTextView(container, "SSN ", it.socialSecurityNumber)
            addDetailTextView(container, "Date of Birth", formatDate(it.dateOfBirth))
            addDetailTextView(container, "Phone Number", it.phoneNumber)
            val address = "${it.beneficiaryAddress.firstLineMailing}," +
                    " ${it.beneficiaryAddress.zipCode}, ${it.beneficiaryAddress.city}, ${it.beneficiaryAddress.country}"
            addDetailTextView(container, "Address", address)
        }

        scrollView.addView(container)
        setContentView(scrollView)
    }

    private fun addDetailTextView(container: LinearLayout, title: String, content: String) {
        val titleView = TextView(this).apply {
            text = title
            setTypeface(null, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(context, android.R.color.black)) // Set title color
            textSize = 16f
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                bottomMargin = 8
            }
        }
        container.addView(titleView)

        val contentView = TextView(this).apply {
            text = content
            textSize = 14f
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                bottomMargin = 24
            }
        }
        container.addView(contentView)
    }

}