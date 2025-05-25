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
                            setBackgroundResource(R.drawable.button_background)
                            setTextColor(resources.getColor(R.color.text, null))
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
                            setBackgroundResource(R.drawable.button_background)
                            setTextColor(resources.getColor(R.color.text, null))
                            setOnClickListener { viewModel.makeDecision(index) }
                        }
                        binding.decisionOptions.addView(button)
                    }
                binding.orcamentoBar.progress = state.orcamento
                binding.apoioPopularBar.progress = state.apoioPopular
                binding.respeitoInstitucionalBar.progress = state.respeitoInstitucional
                binding.influenciaPoliticaBar.progress = state.influenciaPolitica
                binding.relacaoImprensaBar.progress = state.relacaoImprensa
                binding.relacaoGovernoBar.progress = state.relacaoGoverno
                binding.relacaoOngsBar.progress = state.relacaoONGs
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
            "balanca_da_justica" -> R.drawable.balanca_da_justica
            "caso_01_malas_dinheiro" -> R.drawable.caso_01_malas_dinheiro
            "caso_02_protestos" -> R.drawable.caso_02_protestos
            "caso_03_vacina" -> R.drawable.caso_03_vacina
            "caso_04_vazamento" -> R.drawable.caso_04_vazamento
            "cyber_security_background" -> R.drawable.cyber_security_background
            "diplomacia_01_fronteira" -> R.drawable.diplomacia_01_fronteira
            "diplomacia_02_embargo" -> R.drawable.diplomacia_02_embargo
            "diplomacy_global" -> R.drawable.diplomacy_global
            "favicon" -> R.drawable.favicon
            "mesa_negociacoes" -> R.drawable.mesa_negociacoes
            "law_pictures" -> R.drawable.law_pictures
            "pf_3" -> R.drawable.pf_3
            "placeholder" -> R.drawable.placeholder
            "placeholder_advanced" -> R.drawable.placeholder_advanced
            "ponte_ciclopedonale" -> R.drawable.ponte_ciclopedonale
            "projeto_01_ponte" -> R.drawable.projeto_01_ponte
            "projeto_02_energia" -> R.drawable.projeto_02_energia
            "tribunal_com_relogio" -> R.drawable.tribunal_com_relogio
            "sala_tribunal" -> R.drawable.sala_tribunal
            else -> R.drawable.placeholder
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}