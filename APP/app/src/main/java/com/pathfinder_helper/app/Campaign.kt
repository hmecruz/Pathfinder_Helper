package com.pathfinder_helper.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.pathfinder_helper.app.databinding.CampaignBinding

class Campaign : AppCompatActivity() {

    lateinit var binding : CampaignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CampaignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sample data
        val campaigns = arrayOf("Campaign1", "Campaign2", "Campaign3")

        // Create an adapter using custom layout
        val adapter = ArrayAdapter<String>(
                this,
                R.layout.list_item_campaign, // Use custom layout
                R.id.campaignNameTextView, // TextView id in the custom layout
                campaigns
        )

        // Set the adapter to the ListView
        binding.campaignList.adapter = adapter

        // Set up the SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // Final Text is Entered on the Search View
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (campaigns.contains(query)) {
                    adapter.filter.filter(query)
                }
                return false
            }

            // Every Time a key is pressed on the Search View
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }
}