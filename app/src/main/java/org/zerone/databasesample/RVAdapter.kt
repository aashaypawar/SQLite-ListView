package org.zerone.databasesample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class RVAdapter(var this_context: Context, var resources: Int, var items: List<UserObjRV>): ArrayAdapter<UserObjRV>(this_context, resources, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(this_context)
        val view = layoutInflater.inflate(resources, null)

        val tvNameCard = view.findViewById<TextView>(R.id.name_card)
        val tvAgeCard = view.findViewById<TextView>(R.id.age_card)

        tvNameCard.text = items[position].userName
        tvAgeCard.text = items[position].userAge.toString()

        return view
    }

}