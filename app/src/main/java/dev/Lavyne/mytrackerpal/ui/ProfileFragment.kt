package dev.Lavyne.mytrackerpal.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import dev.Lavyne.mytrackerpal.R
import dev.Lavyne.mytrackerpal.ViewModel.UserViewModel
import dev.Lavyne.mytrackerpal.databinding.FragmentProfileBinding
import dev.Lavyne.mytrackerpal.models.ProfileRequest
import dev.Lavyne.mytrackerpal.utils.Constants

class ProfileFragment : Fragment() {
    lateinit var binding:FragmentProfileBinding
    lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        userViewModel=
//            ViewModelProvider(this).get(userViewModel::class.java)
        binding= FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
        // Inflate the layout for this fragment
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener {
            validateProfile()
        }

        }

    fun validateProfile() {
        var gender = binding.etGender.text.toString()
        var dateofbirth = binding.etDob.text.toString()
        var weight = binding.etWeight.text.toString()
        var height = binding.etHeight.text.toString()
        var nickname = binding.etName.text.toString()
        var error = false

        if (nickname.isBlank()) {
            binding.tilName.error = "Name required"
        }
        if (gender.isBlank()) {
            binding.tilGender.error = "Gender required"
        }
        if (dateofbirth.isBlank()) {
            binding.tilDob.error = "Date of birth name required"
        }
        if (weight.isBlank()) {
            binding.tilWeight.error = "Weight is required"

        }
        if (height.isBlank()) {
            binding.tilHeight.error = "Height required"
        }
        if (!error) {
            val profilerequest =ProfileRequest(gender,weight,dateofbirth,height,nickname )
            userViewModel.profileUser(profilerequest)


        }

    }
    }


