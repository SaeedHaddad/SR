package com.example.sr

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class AddNotes : AppCompatActivity() {
    val dbTable="Notes"
    var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)




        try{
            var bundle:Bundle= intent.extras!!
            id=bundle.getInt("ID",0)
            if(id!=0) {
                val etTitle:TextView=findViewById(R.id.etTitle)
                etTitle.setText(bundle.getString("name") )
                val etDes:TextView=findViewById(R.id.etDes)
                etDes.setText(bundle.getString("des") )

            }
        }catch (ex:Exception){}


    }

    fun  buAdd(view:View){
        var dbManager=DbManager(this)

        var values= ContentValues()
        val etTitle:TextView=findViewById(R.id.etTitle)
        values.put("Title",etTitle.text.toString())
        val etDes:TextView=findViewById(R.id.etDes)
        values.put("Description",etDes.text.toString())


        if(id==0) {
            val ID = dbManager.Insert(values)
            if (ID > 0) {
                Toast.makeText(this, " note is added", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, " cannot add note ", Toast.LENGTH_LONG).show()
            }
        }else{
            var selectionArs= arrayOf(id.toString())
            val ID = dbManager.Update(values,"ID=?",selectionArs)
            if (ID > 0) {
                Toast.makeText(this, " note is added", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, " cannot add note ", Toast.LENGTH_LONG).show()
            }
        }

    }
}