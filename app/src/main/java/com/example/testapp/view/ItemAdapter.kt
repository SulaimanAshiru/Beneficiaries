package com.example.testapp.view

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.OnItemClickListener
import com.example.testapp.model.Beneficiary

class ItemAdapter(private val beneficiaries: List<Beneficiary>,
                  private val listener: OnItemClickListener
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context

        val cardView = CardView(context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            ).also { params ->
                params.bottomMargin = 16
                params.topMargin = 16
                params.leftMargin = 16
                params.rightMargin = 16
            }
            radius = 16f
            elevation = 8f
        }

        val itemLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val nameTitleTextView = createTextView(context, "Name" , tag = "Name", isTitle = true)
        val nameValueTextView = createTextView(context, "" , tag = "name_value")
        itemLayout.addView(nameTitleTextView)
        itemLayout.addView(nameValueTextView)

        val beneTypeTitleTextView = createTextView(context, "BeneType", tag = "BeneType", isTitle = true)
        val beneTypeValueTextView = createTextView(context, "", tag = "bene_value")
        itemLayout.addView(beneTypeTitleTextView)
        itemLayout.addView(beneTypeValueTextView)

        val designationTitleTextView = createTextView(context, "Designation", tag = "Designation", isTitle = true)
        val designationValueTextView = createTextView(context, "", tag = "designation_value")
        itemLayout.addView(designationTitleTextView)
        itemLayout.addView(designationValueTextView)

        cardView.addView(itemLayout)

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beneficiary = beneficiaries[position]

        val itemLayout = holder.cardView.getChildAt(0) as LinearLayout

        // Set values for TextViews
        (itemLayout.getChildAt(1) as TextView).let {
            it.text = "${beneficiary.firstName} ${beneficiary.lastName}"
        }
        (itemLayout.getChildAt(3) as TextView).let {
            it.text = beneficiary.beneType
        }
        (itemLayout.getChildAt(5) as TextView).let {
            it.text = beneficiary.designation
        }

        // setClickListener
        holder.itemView.setOnClickListener {
            listener.onItemClick(beneficiary)
        }

    }

    override fun getItemCount(): Int = beneficiaries.size

    // Helper function to create TextViews
    private fun createTextView(context: Context, text: String, tag : String, isTitle : Boolean = false): TextView {
        return TextView(context).apply {
            this.text = text
            textSize = 16f
            this.tag = tag
            setTextColor(Color.BLACK)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also {
                it.bottomMargin = 8
            }
            if (isTitle) {
                setTypeface(typeface, android.graphics.Typeface.BOLD)
                gravity =
                    Gravity.CENTER_HORIZONTAL
            }
        }
    }
}