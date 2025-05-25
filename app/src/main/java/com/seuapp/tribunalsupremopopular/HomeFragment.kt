package com.seuapp.tribunalsupremopopular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.seuapp.tribunalsupremopopular.databinding.FragmentDashboardBinding
import com.seuapp.tribunalsupremopopular.viewmodel.GameViewModel

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) { state ->
            state.currentCase?.let { case ->
                binding.caseTitle.text = case.titulo
                binding.caseDescription.text = case.descricao
                binding.caseImage.setImageResource(getImageResource(case.imagem))
                binding.caseEvidences.text = case.provas.joinToString("\n") { "• $it" }
                binding.investigationOptions.removeAllViews()
                if (state.investigationsDone < state.maxInvestigations) {
                    case.investigacoes.forEachIndexed { index, inv ->
                        val button = Button(requireContext()).apply {
                            text = inv.acao
                            setOnClickListener { viewModel.investigate(index) }
                        }
                        binding.investigationOptions.addView(button)
                    }
                }
                binding.decisionOptions.removeAllViews()
                case.decisoes.filter { !it.requiresInvestigation || state.investigationsDone > 0 }
                    .forEachIndexed { index, decision ->
                        val button = Button(requireContext()).apply {
                            text = decision.texto
                            setOnClickListener { viewModel.makeDecision(index) }
                        }
                        binding.decisionOptions.addView(button)
                    }
                binding.orcamentoBar.progress = state.orcamento
                binding.apoioPopularBar.progress = state.apoioPopular
                // Atualize outras barras de progresso
            }
        }
        binding.viewMediaButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_notifications)
        }
        viewModel.notification.observe(viewLifecycleOwner) { message ->
            Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun getImageResource(imagePath: String): Int {
        return when (imagePath) {
            "caso_01_malas_dinheiro" -> R.drawable.caso_01_malas_dinheiro
            else -> R.drawable.placeholder
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}