package com.example.yaroslavlquest.bottomnav.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.yaroslavlquest.LoginActivity
import com.example.yaroslavlquest.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException
import android.graphics.Bitmap

import com.google.firebase.storage.UploadTask

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private var filePath: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        loadUserInfo()
        binding.profileImageView.setOnClickListener {
            selectImage()
        }
        binding.logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(context, LoginActivity::class.java))
        }
        return binding.root
    }

    private val pickImageActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            filePath = result.data!!.data

            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, filePath)
                binding.profileImageView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            uploadImage()
        }
    }

    private fun loadUserInfo() {
        FirebaseDatabase.getInstance().getReference().child("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(@NonNull snapshot: DataSnapshot) {
                    val username = snapshot.child("username").value.toString()
                    val profileImage = snapshot.child("profileImage").value.toString()

                    binding.usernameTv.text = username

                    if (profileImage.isNotEmpty()) {
                        Glide.with(context!!).load(profileImage).into(binding.profileImageView)
                    }
                }

                override fun onCancelled(@NonNull error: DatabaseError) {}
            })
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        pickImageActivityResultLauncher.launch(intent)
    }

    private fun uploadImage() {
        filePath?.let {
            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            FirebaseStorage.getInstance().reference.child("images/$uid")
                .putFile(it).addOnSuccessListener {
                    Toast.makeText(context, "Photo upload completed", Toast.LENGTH_SHORT).show()
                    FirebaseStorage.getInstance().reference.child("images/$uid").downloadUrl
                        .addOnSuccessListener { uri: Uri ->
                            FirebaseDatabase.getInstance().reference.child("Users")
                                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                                .child("profileImage").setValue(uri.toString())
                        }
                }
        }
    }

}


