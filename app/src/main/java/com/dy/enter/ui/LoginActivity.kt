package com.dy.enter.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Context
import android.support.design.widget.TextInputLayout
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast

import com.dy.enter.R
import com.dy.enter.interfaces.ILoginView
import com.dy.enter.presenter.LoginPresenter


class LoginActivity : BaseActivity(), ILoginView {

    private var mProgressView: View? = null
    private var mLoginFormView: View? = null
    private var usernameWrapper: TextInputLayout? = null
    private var passwordWrapper: TextInputLayout? = null
    private var mLoginPresenter: LoginPresenter? = null

    override fun initVariables() {

    }

    override fun initViews(savedInstanceState: Bundle) {
        setContentView(R.layout.activity_login)
        usernameWrapper = findViewById(R.id.usernameWrapper) as TextInputLayout?
        passwordWrapper = findViewById(R.id.passwordWrapper) as TextInputLayout?


        val mEmailSignInButton = findViewById(R.id.btnLogin) as Button?
        mEmailSignInButton!!.setOnClickListener {
            hideKeyboard()

            mLoginPresenter!!.login(usernameWrapper!!.editText!!.text.toString(),
                    passwordWrapper!!.editText!!.text.toString())
        }

        mLoginFormView = findViewById(R.id.login_form)
        mProgressView = findViewById(R.id.login_progress)


        mLoginPresenter = LoginPresenter(this, this)
    }

    override fun loadData() {

    }

    private fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

            mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
            mLoginFormView!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

            mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
            mProgressView!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
            mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
        }
    }


    override fun showProgress() {
        showProgress(true)
    }

    override fun hideProgress() {
        showProgress(false)
    }

    override fun setPasswordError(error: String) {
        passwordWrapper!!.editText!!.requestFocus()
        passwordWrapper!!.editText!!.error = error
    }

    override fun setUserNameError(error: String) {
        usernameWrapper!!.editText!!.error = error
        usernameWrapper!!.editText!!.requestFocus()
    }

    override fun getUsername(): String {
        return usernameWrapper!!.editText!!.text.toString()
    }

    override fun getPassword(): String {
        return passwordWrapper!!.editText!!.text.toString()
    }

    override fun loginSuccess() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
    }

    override fun loginFailure() {
        Toast.makeText(this, "login failure", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mLoginPresenter!!.doDestroy()

    }

}

