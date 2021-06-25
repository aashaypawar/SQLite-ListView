package org.zerone.databasesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = DataBaseHelper(this)
        for (i in 0..30){
            database.insertData("Name$i", i)
        }
        val userObjList = database.readData()
        database.close()

        val lv = findViewById<ListView>(R.id.LVMainActivity)
        lv.adapter = RVAdapter(this, R.layout.user_obj_card, userObjList)

    }
}