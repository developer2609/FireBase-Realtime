package com.developer.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.developer.chatapp.adapter.RvAdapter
import com.developer.chatapp.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseDatabase:FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var rvAdapter: RvAdapter
    lateinit var list: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("messages")


         binding.btnSend.setOnClickListener {
             var key = reference.push().key
             val message = binding.edtMessage.text.toString().trim()
             if (message.isNotEmpty()) {
                 reference.child(key!!).setValue(message)
                 Toast.makeText(this, "Send message", Toast.LENGTH_SHORT).show()
                 binding.edtMessage.text.clear()
             }else{
                 Toast.makeText(this, "text is empty", Toast.LENGTH_SHORT).show()
             }


         }


        list= ArrayList()

        rvAdapter= RvAdapter(list)
         binding.rvMessage.adapter=rvAdapter

        reference.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (child in snapshot.children) {
                    list.add(child.value.toString())

                }

                rvAdapter.notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })






    }
}