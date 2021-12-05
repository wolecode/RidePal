package com.ridehub360.ridepal.ui.login

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ridehub360.ridepal.MainActivity
import com.ridehub360.ridepal.R
import com.ridehub360.ridepal.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    lateinit var binding: FragmentSignUpBinding
    private lateinit var nameTextWatcher: TextWatcher
    private lateinit var emailTextWatcher: TextWatcher
    private lateinit var passwordTextWatcher: TextWatcher
    private lateinit var confirmPasswordTextWatcher: TextWatcher
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentSignUpBinding.inflate(inflater).let {
            binding = it
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        observeFields()
    }

    private fun setupView() {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        with(binding) {
            setClickableString(tvSignIn.text.toString().trim(), "Sign In", tvSignIn)
            setupTextWatcher()
            etName.addTextChangedListener(nameTextWatcher)
            etEmail.addTextChangedListener(emailTextWatcher)
            etPassword.addTextChangedListener(passwordTextWatcher)
            etConfirmPassword.addTextChangedListener(confirmPasswordTextWatcher)
            btSignUp.setOnClickListener {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
        }

    }

    private fun setClickableString(wholeText: String, clickableText: String, tv: TextView) {
        val spannableString = SpannableString(wholeText)
        val startIndex = wholeText.indexOf(clickableText)
        val endIndex = startIndex + clickableText.length
        spannableString.setSpan(StyleSpan(Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#3172AC")), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableString.setSpan(object: ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
            override fun onClick(widget: View) {
                val controller = findNavController()
                val action = SignUpFragmentDirections.signUpFragmentToSignInFragment()
                controller.navigateUp()
                //startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                //finish()
            }
        }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv.text = spannableString
        tv.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setupTextWatcher() {
        nameTextWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if ((s!!.isEmpty() || s.isNullOrBlank())) {
                    viewModel.apply {
                        nameValid.value = false
                        viewModel.setDataFilled()
                    }
                    binding.etNameLayout.apply {
                        isErrorEnabled = true
                        error = "Input a valid Name"
                    }
                } else {
                    binding.etNameLayout.isErrorEnabled = false
                    viewModel.apply {
                        nameValid.value = true
                        viewModel.setDataFilled()
                    }
                }
            }
        }

        emailTextWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.toString().contains("@") && s.toString().contains(".")) {
                    binding.etEmailLayout.isErrorEnabled = false
                    viewModel.apply {
                        emailValid.value = true
                        setDataFilled()
                    }
                } else {
                    viewModel.apply {
                        emailValid.value = false
                        setDataFilled()
                    }
                    binding.etEmailLayout.apply {
                        isErrorEnabled = true
                        error = "Input a valid Email Address"
                    }
                }
            }
        }

        passwordTextWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.trim().length >= 8) {
                    binding.etPasswordLayout.isErrorEnabled = false
                    viewModel.apply {
                        passwordValid.value = true
                        setDataFilled()
                    }
                } else {
                    viewModel.apply {
                        passwordValid.value = false
                        setDataFilled()
                    }
                    binding.etPasswordLayout.apply {
                        isErrorEnabled = true
                        error = "Password should be at least 8 characters"
                    }
                }
            }
        }

        confirmPasswordTextWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.trim() == binding.etPassword.text!!.trim()) {
                    viewModel.apply {
                        confirmPasswordValid.value = true
                        setDataFilled()
                    }
                    binding.etConfirmPasswordLayout.isErrorEnabled = false
                } else {
                    viewModel.apply {
                        confirmPasswordValid.value = false
                        setDataFilled()
                    }
                    binding.etConfirmPasswordLayout.apply {
                        isErrorEnabled = true
                        error = "Password should be the same"
                    }
                }
            }
        }
    }

    private fun observeFields() {
        viewModel.allDataFilled.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.btSignUp.apply {
                    isEnabled = true
                    setBackgroundColor(resources.getColor(R.color.colorPrimary))
                }
            } else {
                binding.btSignUp.apply {
                    isEnabled = false
                    setBackgroundColor(resources.getColor(R.color.buttonDisabledColor))
                }
            }
        })
    }
}