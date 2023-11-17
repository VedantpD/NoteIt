package com.example.noteit.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteit.adapter.MyAdapter
import com.example.noteit.core.base.BaseActivity
import com.example.noteit.core.utils.CheckContract
import com.example.noteit.core.utils.TextContract
import com.example.noteit.core.utils.repository.YourRepository
import com.example.noteit.core.utils.roomDb.YourDatabase
import com.example.noteit.databinding.ActivityMainBinding
import com.example.noteit.model.YourEntity
import com.example.noteit.ui.notes.NotesActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var repository: YourRepository
    private var clicked : Boolean = false

    var list : LinkedHashMap<String,String> = LinkedHashMap()

    private val checkContract = registerForActivityResult(CheckContract()){
        if(!it.isEmpty()){
            addToMainList(it,"C")
        }
    }
    private val textContract = registerForActivityResult(TextContract()){
        if(!it.isEmpty()){
            addToMainList(it,"T")
        }
    }


//    private val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim) }
//    private val rotateClose : Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim) }
//    private val fromButton : Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim) }
//    private val toButton : Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = YourRepository(YourDatabase.getDatabase(this).yourDao())
        setClicks()
        addToMainList()
    }

    private fun setClicks() {
        binding.apply {
            flbtnCheck.setOnClickListener {
                checkContract.launch("Check")
            }
            flbtnText.setOnClickListener {
                textContract.launch("Text")
            }
            flbtnAdd.setOnClickListener {
                onAddButtonClicked()
            }
        }
    }

    private fun onAddButtonClicked() {
        clicked = !clicked

        //setAnimation(clicked)
        setVisiblity(clicked)

    }
    private fun setVisiblity(clicked: Boolean) {
        if(clicked) {
            binding.apply {
                Log.d("maini",clicked.toString())
                flbtnCheck.visibility = View.VISIBLE
                flbtnText.visibility = View.VISIBLE
            }
        }else{
            binding.apply {
                Log.d("maini",clicked.toString())
                flbtnCheck.visibility = View.GONE
                flbtnText.visibility = View.GONE
            }
        }
    }

    private fun addToMainList(value: String? = null,type: String? = null) {
        lifecycleScope.launch {
            try {
                if (value != null && type != null) {
                    val yourEntity = YourEntity(value = value,type)
                    repository.insertData(yourEntity)
                }
                val data = repository.getAllData()

                withContext(Dispatchers.Main) {
                    val allValues = data.map { it.value }
                    val allTypes = data.map { it.type }

                    list.clear()
                    for (x in allValues.indices) {
                        list[allValues[x]] = allTypes[x]
                    }
                    setRecyclerView()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun setRecyclerView() {
        val adapter = MyAdapter(this,list)
        binding.recyclerView.adapter = adapter
        val layoutManager = GridLayoutManager(this,4,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.layoutManager = layoutManager
    }

    override fun onBackPressed() {
        finish()
    }

//    private fun setAnimation(clicked: Boolean) {
//        if(clicked) {
//            binding.apply {
//                flbtnCheck.startAnimation(fromButton)
//                flbtnText.startAnimation(fromButton)
//                flbtnAdd.startAnimation(rotateOpen)
//            }
//        }else{
//            binding.apply {
//                flbtnCheck.startAnimation(toButton)
//                flbtnText.startAnimation(toButton)
//                flbtnAdd.startAnimation(rotateClose)
//            }
//        }
//    }
}