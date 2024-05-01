package com.pathfinder_helper.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pathfinder_helper.app.databinding.FragmentCampiagnBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Campiagn : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentCampiagnBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCampiagnBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set up button click listener
        binding.newCampaignButton.setOnClickListener {
            // Show the pop-up dialog
            val dialogFragment = MyDialogFragment()
            dialogFragment.show(parentFragmentManager, "MyDialogFragment")
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Campiagn().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
