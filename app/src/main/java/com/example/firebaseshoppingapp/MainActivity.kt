package com.example.firebaseshoppingapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseshoppingapp.adapter.CategoryAdapter
import com.example.firebaseshoppingapp.model.Category
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val search = findViewById<EditText>(R.id.main_Search)
        val shapeable = findViewById<ShapeableImageView>(R.id.main_Shapeable)
        val recyclerView = findViewById<RecyclerView>(R.id.main_RecyclerView)
        val itemList = ArrayList<Category>()
        val adapter = CategoryAdapter(itemList)
        setupRecyclerView(adapter,recyclerView)
        val db = FirebaseDatabase.getInstance().reference
        val valueEventListener = object : ValueEventListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    itemList.add(Category(i.key.toString(),i.child("image").value.toString()))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity,"Error !",Toast.LENGTH_SHORT).show()
            }

        }
        db.child("Category").addListenerForSingleValueEvent(valueEventListener)
    }

    private fun setupRecyclerView(adapter: CategoryAdapter, recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}