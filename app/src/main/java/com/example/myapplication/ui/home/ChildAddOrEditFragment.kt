package com.example.myapplication.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.cardview.widget.CardView
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding.inflate
import com.example.myapplication.databinding.FragmentHomeBinding
import android.widget.TextView
import androidx.databinding.DataBindingUtil.inflate
import com.example.myapplication.databinding.ActivityMainBinding.bind
import com.example.myapplication.databinding.FragmentChildAddOrEditBinding
import com.example.myapplication.ui.BaseFragment
import com.google.android.material.imageview.ShapeableImageView

class ChildAddOrEditFragment : BaseFragment<ChildAddOrEditViewModel>(ChildAddOrEditViewModel::class.java) {

    private var _binding: FragmentChildAddOrEditBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ChildAddOrEditFragment()
    }

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
            R.drawable.child_1_icon,
            R.drawable.child_3_icon,
            R.drawable.child_1_icon,
            R.drawable.child_1_icon
        )

        for (index in drawableList.indices) {
            var cardView : CardView = LayoutInflater.from(context).inflate(
                R.layout.item_avatar,
                binding.gridLayoutAvatarList,
                false
            ) as CardView

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
    }

}