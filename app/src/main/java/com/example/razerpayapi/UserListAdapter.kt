package com.example.razerpayapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserListAdapter(private val context:Context, private val userList: List<Item>):RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.name_textView)
        val email:TextView = itemView.findViewById(R.id.emailText_textView)
        val contact:TextView = itemView.findViewById(R.id.contact_textView)
        val  id:TextView = itemView.findViewById(R.id.id_textView)
        val updateButton:ImageButton = itemView.findViewById(R.id.update_Button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
      return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = userList[position].name
        holder.email.text = userList[position].email
        holder.contact.text = userList[position].contact
        holder.id.text = userList[position].id

        holder.updateButton.setOnClickListener {
            val intent = Intent(context,UpdateActivity::class.java)
            intent.putExtra("name",userList[position].name)
            intent.putExtra("email",userList[position].email)
            intent.putExtra("contact",userList[position].contact)
            intent.putExtra("id",userList[position].id)

            context.startActivity(intent)
        }
    }
}

