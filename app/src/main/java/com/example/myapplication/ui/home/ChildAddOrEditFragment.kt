package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentChildAddOrEditBinding
import com.example.myapplication.ui.BaseFragment
import com.example.myapplication.ui.notifyObserver
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.imageview.ShapeableImageView
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ChildAddOrEditFragment : BaseFragment<ChildAddOrEditViewModel>(ChildAddOrEditViewModel::class.java) {

    private var _binding: FragmentChildAddOrEditBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ChildAddOrEditFragment()
    }

    private val circleLinearLayoutMap = mutableMapOf<String, LinearLayout>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChildAddOrEditBinding.inflate(inflater, container, false)
        val root: View = binding.root

        createAvatarsCardInGridLayout()
        return root
    }

    private fun createAvatarsCardInGridLayout() {
        var drawableList = listOf(
            Pair(R.drawable.child_1_icon, "child_1_avatar"),
            Pair(R.drawable.child_2_icon, "child_2_avatar"),
            Pair(R.drawable.child_3_icon, "child_3_avatar"),
            Pair(R.drawable.child_1_icon, "child_1a_avatar")
        )

        circleLinearLayoutMap.clear()

        for (index in drawableList.indices) {
            var cardView : CardView = LayoutInflater.from(context).inflate(
                R.layout.item_avatar,
                binding.gridLayoutAvatarList,
                false
            ) as CardView

            cardView.findViewWithTag<ShapeableImageView>("ShapeableImageView")?.setImageResource(drawableList[index].first)
            val rowSpec = GridLayout.spec(index / 4, 1, 0.25f)
            val colSpec = GridLayout.spec(index % 4, 1, 0.25f)
            val myGLP = GridLayout.LayoutParams(rowSpec, colSpec)

            binding.gridLayoutAvatarList.addView(cardView, myGLP)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = this
        binding.dateFormatter = SimpleDateFormat("yyyy-MM-dd")

        observeShowDataPickerRequest()
    }

    private fun observeShowDataPickerRequest() {
        viewModel.showDatePickerRequest.observe(this.viewLifecycleOwner) {
            showBirthdayDataPicker()
        }
    }

    private fun showBirthdayDataPicker() {
        val title = resources.getString(R.string.select_date)
        val datePicker = MaterialDatePicker.Builder.datePicker().setTitleText(title).build()
        datePicker.addOnPositiveButtonClickListener { value ->
            viewModel.child.value?.let { child ->
                child.birthday = Date(value)
                viewModel.child.notifyObserver()
            }
        }

        datePicker.show(this.parentFragmentManager, "MY_DATE_PICKER_TAG")
    }

}