package com.seuapp.tribunalsupremopopular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seuapp.tribunalsupremopopular.databinding.FragmentNotificationsBinding
import com.seuapp.tribunalsupremopopular.viewmodel.GameViewModel

class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) { state ->
            state.currentCase?.let { case ->
                binding.mediaHeadline.text = "O que dizem sobre o caso..."
                binding.mediaReactions.text = case.midia.joinToString("\n") { "• $it" }
            }
        }
        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_notifications_to_dashboard)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}