package com.kzerk.compositionapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kzerk.compositionapp.databinding.FragmentGameFinishedBinding


class GameFinishedFragment : Fragment() {
	private val args by navArgs<GameFinishedFragmentArgs>()

	private var _binding: FragmentGameFinishedBinding? = null
	private val binding: FragmentGameFinishedBinding
		get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupOnClickListeners()
		binding.gameResult = args.gameResult
	}

	private fun setupOnClickListeners() {
		binding.buttonRetry.setOnClickListener {
			retryGame()
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private fun retryGame() {
		findNavController().popBackStack()
	}
}