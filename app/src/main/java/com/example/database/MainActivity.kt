package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database.adapter.DataAdapter
import com.example.database.manager.RealmManager
import com.example.database.model.Post
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(){
    lateinit var recyclerView: RecyclerView
    lateinit var adapter : DataAdapter
    lateinit var flag_button : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        recyclerView=findViewById(R.id.recyclerview)


        val manager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        manager.reverseLayout=true
        manager.stackFromEnd=true
        recyclerView.layoutManager = manager


        flag_button=findViewById(R.id.fab)
        flag_button.setOnClickListener{
            showMessageBox()
        }
        refreshAdapter()
    }

    private fun refreshAdapter() {
        val arr=RealmManager.instance!!.loadPosts()
       adapter= DataAdapter(this,arr)
        Log.d("@@@", arr.toString())
        recyclerView!!.adapter=adapter
    }









  private  fun showMessageBox(){

        //Inflate the dialog as custom view
        val messageBoxView = LayoutInflater.from(this).inflate(R.layout.item_dialog_edit_text, null)

        //AlertDialogBuilder
        val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)


        //show dialog
        val  messageBoxInstance = messageBoxBuilder.show()
        val tv_cancel=messageBoxView.findViewById<TextView>(R.id.tv_cancel_dialog)
      val tv_save=messageBoxView.findViewById<TextView>(R.id.tv_save_dialog)


     val et_text=messageBoxView.findViewById<EditText>(R.id.et_dialog)



      tv_save.setOnClickListener {
          val post = Post(RealmManager.instance!!.loadPosts().size,et_text.text.toString())
          RealmManager.instance!!.savePost(post)
          adapter.setIntemList(post)
          messageBoxInstance.dismiss()

      }



        //set Listener
      tv_cancel.setOnClickListener(){
            //close dialog
            messageBoxInstance.dismiss()
        }
    }

}