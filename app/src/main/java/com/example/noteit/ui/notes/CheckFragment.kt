package com.example.noteit.ui.notes

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.noteit.databinding.FragmentCheckBinding


class CheckFragment : Fragment() {
    private var _binding : FragmentCheckBinding? = null
    val binding get() = _binding!!

    var toDoItems = mutableListOf<LinearLayout>()
    var txtList = mutableListOf<String>()
    var checkList = mutableListOf<Boolean>()

    var checkCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClicks()
    }

    private fun setClicks() {
        binding.apply {
            txtAddItem.setOnClickListener {
                checkCount++
                if(checkCount<=10){addCheckBox()}
            }
        }
    }

    private fun addCheckBox() {
        val newLinearLayout = LinearLayout(requireContext())
        newLinearLayout.orientation = LinearLayout.HORIZONTAL

        val checkBox = CheckBox(requireContext())
        checkBox.isChecked = false
        newLinearLayout.addView(checkBox)

        val editText = EditText(requireContext())
//        val generatedId = View.generateViewId()
//        editText.id = generatedId
        editText.hint = "Enter new item"
        editText.background = null
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        newLinearLayout.addView(editText)

        toDoItems.add(newLinearLayout)
        binding.Llayout.addView(newLinearLayout)
    }

}