package com.ridehub360.ridepal

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.et_email_layout
import kotlinx.android.synthetic.main.activity_sign_up.et_password
import kotlinx.android.synthetic.main.activity_sign_up.et_password_layout

class SignUpActivity : AppCompatActivity() {

    private lateinit var nameTextWatcher: TextWatcher
    private lateinit var emailTextWatcher: TextWatcher
    private lateinit var passwordTextWatcher: TextWatcher
    private lateinit var confirmPasswordTextWatcher: TextWatcher
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setupView()
        observeFields()
    }

    private fun setupView() {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        setClickableString(tv_sign_in.text.toString().trim(), "Sign In", tv_sign_in)
        setupTextWatcher()
        et_name.addTextChangedListener(nameTextWatcher)
        et_email.addTextChangedListener(emailTextWatcher)
        et_password.addTextChangedListener(passwordTextWatcher)
        et_confirm_password.addTextChangedListener(confirmPasswordTextWatcher)
        bt_sign_up.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
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
                startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                finish()
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
                    et_name_layout.apply {
                        isErrorEnabled = true
                        error = "Input a valid Name"
                    }
                } else {
                    et_name_layout.isErrorEnabled = false
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
                    et_email_layout.isErrorEnabled = false
                    viewModel.apply {
                        emailValid.value = true
                        setDataFilled()
                    }
                } else {
                    viewModel.apply {
                        emailValid.value = false
                        setDataFilled()
                    }
                    et_email_layout.apply {
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
                    et_password_layout.isErrorEnabled = false
                    viewModel.apply {
                        passwordValid.value = true
                        setDataFilled()
                    }
                } else {
                    viewModel.apply {
                        passwordValid.value = false
                        setDataFilled()
                    }
                    et_password_layout.apply {
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
                if (s!!.trim() == et_password.text!!.trim()) {
                    viewModel.apply {
                        confirmPasswordValid.value = true
                        setDataFilled()
                    }
                    et_confirm_password_layout.isErrorEnabled = false
                } else {
                    viewModel.apply {
                        confirmPasswordValid.value = false
                        setDataFilled()
                    }
                    et_confirm_password_layout.apply {
                        isErrorEnabled = true
                        error = "Password should be the same"
                    }
                }
            }
        }
    }

    private fun observeFields() {
        viewModel.allDataFilled.observe(this, Observer {
            if (it) {
                bt_sign_up.apply {
                    isEnabled = true
                    setBackgroundColor(resources.getColor(R.color.colorPrimary))
                }
            } else {
                bt_sign_up.apply {
                    isEnabled = false
                    setBackgroundColor(resources.getColor(R.color.buttonDisabledColor))
                }
            }
        })
    }
}