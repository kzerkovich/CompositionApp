package com.kzerk.compositionapp.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kzerk.compositionapp.R
import com.kzerk.compositionapp.databinding.FragmentGameBinding
import com.kzerk.compositionapp.domain.entity.GameResult
import com.kzerk.compositionapp.domain.entity.GameSettings
import com.kzerk.compositionapp.domain.entity.Level

class GameFragment : Fragment() {

	private lateinit var level: Level

	private var _binding: FragmentGameBinding? = null
	private val binding: FragmentGameBinding
		get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		parseArgs()
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentGameBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.tvOption1.setOnClickListener {
			launchGameFinishedFragment(
				GameResult(
				true,
				0,
				0,
				GameSettings(0, 0, 0, 0)
			)
			)
		}
	}

	@Suppress("DEPRECATION")
	private fun parseArgs() {
		level = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			requireArguments().getParcelable(KEY_LEVEL, Level::class.java)!!
		} else {
			requireArguments().getParcelable<Level>(KEY_LEVEL) as Level
		}
	}

	private fun launchGameFinishedFragment(gameResult: GameResult) {
		requireActivity().supportFragmentManager.beginTransaction()
			.replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
			.addToBackStack("null")
			.commit()
	}

	companion object {
		const val NAME = "GameFragment"
		private const val KEY_LEVEL = "level"

		fun newInstance(level: Level): GameFragment {
			return GameFragment().apply {
				arguments = Bundle().apply {
					putParcelable(KEY_LEVEL, level)
				}
			}
		}
	}
}