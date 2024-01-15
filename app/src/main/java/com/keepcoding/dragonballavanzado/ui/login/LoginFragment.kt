package com.keepcoding.dragonballavanzado.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.keepcoding.dragonballavanzado.R
import com.keepcoding.dragonballavanzado.databinding.FragmentLoginBinding
import com.keepcoding.dragonballavanzado.models.commons.ErrorState
import com.keepcoding.dragonballavanzado.models.commons.ResponseOK
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setObservers()
    }

    private fun handleErrorState(error: ErrorState) {
        Toast.makeText(requireContext(), error.msg, Toast.LENGTH_LONG).show()
    }

    private fun handleResponseTokenState() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    private fun setListeners() {
        binding.login.setOnClickListener {
            viewModel.login(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.username.doAfterTextChanged { newText ->
            viewModel.checkUsernameFormat(newText.toString())
        }

        binding.password.doAfterTextChanged { newText ->
            viewModel.checkPasswordFormat(newText.toString())
        }
    }

    private fun setObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.usernameFormat.collect { correctFormat ->
                binding.usernameFormat.visibility = if (correctFormat) View.GONE else View.VISIBLE
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.passwordFormat.collect { correctFormat ->
                binding.passwordFormat.visibility = if (correctFormat) View.GONE else View.VISIBLE
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.state.collect { state ->
                when (state) {
                    is ErrorState -> handleErrorState(state)
                    is ResponseOK -> handleResponseTokenState()
                    else -> {}
                }
            }
        }
    }
}