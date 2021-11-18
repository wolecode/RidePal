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
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    private lateinit var emailTextWatcher: TextWatcher
    private lateinit var passwordTextWatcher: TextWatcher
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        setupView()
        observeFields()
    }

    private fun setupView() {
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        setClickableString(tv_sign_up.text.toString().trim(), "Sign Up", tv_sign_up)
        setupTextWatcher()
        et_email.addTextChangedListener(emailTextWatcher)
        et_password.addTextChangedListener(passwordTextWatcher)
        bt_sign_in.setOnClickListener {
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
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
                finish()
            }
        }, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv.text = spannableString
        tv.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setupTextWatcher() {
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

        passwordTextWatcher = object: TextWatcher{
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
    }

    private fun observeFields() {
        viewModel.allDataFilled.observe(this, Observer {
            if (it) {
                bt_sign_in.apply {
                    isEnabled = true
                    setBackgroundColor(resources.getColor(R.color.colorPrimary))
                }
            } else {
                bt_sign_in.apply {
                    isEnabled = false
                    setBackgroundColor(resources.getColor(R.color.buttonDisabledColor))
                }
            }
        })
    }
}