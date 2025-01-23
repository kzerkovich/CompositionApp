package com.kzerk.compositionapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kzerk.compositionapp.databinding.FragmentChooseLevelBinding
import com.kzerk.compositionapp.domain.entity.Level


class ChooseLevelFragment : Fragment() {

	private var _binding: FragmentChooseLevelBinding? = null
	private val binding
		get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
		super.onViewCreated(view, savedInstanceState)

		buttonLevelTest.setOnClickListener {
			launchGameFragment(Level.TEST)
		}

		buttonLevelEasy.setOnClickListener {
			launchGameFragment(Level.EASY)
		}

		buttonLevelNormal.setOnClickListener {
			launchGameFragment(Level.NORMAL)
		}

		buttonLevelHard.setOnClickListener {
			launchGameFragment(Level.HARD)
		}
	}

	private fun launchGameFragment(level: Level) {
		findNavController()
			.navigate(ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level))
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}