package com.pathfinder_helper.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.pathfinder_helper.app.databinding.FragmentCharacterBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Character.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Character : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    // View binding instance
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set up button click listener
        binding.newCharacterButton.setOnClickListener {
            // Show the pop-up dialog
            val dialogFragment = MyDialogFragmentCharacetr()
            dialogFragment.show(parentFragmentManager, "MyDialogFragmentCharacter")
        }

        // Sample data
        val campaigns = arrayOf("Branco", "Preto", "Irlandês")

        // Create an adapter using custom layout
        val campaignAdapter: ArrayAdapter<String> = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                campaigns
        )

        // Set the adapter to the ListView
        binding.CharacterList.adapter = campaignAdapter

        // Set up the SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // Final Text is Entered on the Search View
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (campaigns.contains(query)) {
                    campaignAdapter.filter.filter(query)
                }
                return false
            }

            // Every Time a key is pressed on the Search View
            override fun onQueryTextChange(newText: String?): Boolean {
                campaignAdapter.filter.filter(newText)
                return false
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Campaign.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                Character().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
