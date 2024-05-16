package com.pathfinder_helper.app

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.pathfinder_helper.app.databinding.ActivityMainBinding

import android.app.Dialog
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // Inicialização das visualizações
        drawerLayout = findViewById(R.id.main)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)
        // Configuração da Toolbar
        setSupportActionBar(toolbar)

        // Configuração do ActionBarDrawerToggle
        navView.bringToFront()
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        drawerLayout.bringToFront()

        // Navbar
        replaceFragment(Lore())

        binding.navbar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.Lore -> replaceFragment(Lore())
                R.id.Spells -> replaceFragment(spells())
                R.id.Items -> replaceFragment(Items())
                R.id.Character -> replaceFragment(Character())
                R.id.Campaign -> replaceFragment(Campaign())
                else -> return@setOnItemSelectedListener false
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.linearLayout2, fragment)
            .commit()
    }

    private fun enableEdgeToEdge() {
        // Enable edge-to-edge display on compatible devices
        window.decorView.systemUiVisibility = (
                window.decorView.systemUiVisibility
                        or android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}
class MyDialogFragmentCampaign : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create a dialog builder
        val builder = AlertDialog.Builder(requireContext())

        // Inflate the custom layout for the dialog
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.popup_new_campaign, null)

        // Find buttons in the custom layout
        val okButton: Button = view.findViewById(R.id.new_campaign_confirm)
        val cancelButton: Button = view.findViewById(R.id.new_campaign_cancel)

        // Set click listeners for the buttons
        okButton.setOnClickListener {
            // Handle OK button click
            val dialogFragment = MyDialogConfirm()
            dialogFragment.show(parentFragmentManager, "MyDialogConfirm")
            // For example, you can dismiss the dialog
            dismiss()
        }

        cancelButton.setOnClickListener {
            // Handle Cancel button click
            // For example, you can dismiss the dialog
            dismiss()
        }

        // Set the custom view to the dialog builder
        builder.setView(view)

        // Create and return the AlertDialog instance
        return builder.create()
    }
}

class MyDialogFragmentCharacetr : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create a dialog builder
        val builder = AlertDialog.Builder(requireContext())

        // Inflate the custom layout for the dialog
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.popup_new_charcter, null)

        // Find buttons in the custom layout
        val okButton: Button = view.findViewById(R.id.new_charcter_confirm)
        val cancelButton: Button = view.findViewById(R.id.new_charcter_cancel)

        // Set click listeners for the buttons
        okButton.setOnClickListener {
            // Handle OK button click
            val dialogFragment = MyDialogConfirm()
            dialogFragment.show(parentFragmentManager, "MyDialogConfirm")
            // For example, you can dismiss the dialog
            dismiss()
        }

        cancelButton.setOnClickListener {
            // Handle Cancel button click
            // For example, you can dismiss the dialog
            dismiss()
        }

        // Set the custom view to the dialog builder
        builder.setView(view)

        // Create and return the AlertDialog instance
        return builder.create()
    }
}

class MyDialogConfirm : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create a dialog builder
        val builder = AlertDialog.Builder(requireContext())

        // Inflate the custom layout for the dialog
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.popup_confirmed, null)

        // Set the custom view to the dialog builder
        builder.setView(view)

        // Create and return the AlertDialog instance
        return builder.create()
    }
}
