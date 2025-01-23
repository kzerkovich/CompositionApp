package com.kzerk.compositionapp.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kzerk.compositionapp.R
import com.kzerk.compositionapp.databinding.FragmentGameFinishedBinding
import com.kzerk.compositionapp.domain.entity.GameResult


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
		bindViews()
	}

	private fun setupOnClickListeners() {
		binding.buttonRetry.setOnClickListener {
			retryGame()
		}
	}

	@SuppressLint("StringFormatMatches")
	private fun bindViews() = with(binding) {
		emojiResult.setImageResource(getSmileResId())
		tvRequiredAnswers.text = String.format(
			getString(R.string.required_score),
			args.gameResult.gameSettings.minCountOfRightAnswers
		)
		tvScoreAnswers.text = String.format(
			getString(R.string.score_answers),
			args.gameResult.countOfRightAnswers
		)
		tvRequiredPercentage.text = String.format(
			getString(R.string.required_percentage),
			args.gameResult.gameSettings.minPercentOfRightAnswers
		)
		tvScorePercentage.text = String.format(
			getString(R.string.score_percentage),
			getPercentOfRightAnswers()
		)
	}

	private fun getSmileResId(): Int {
		return if (args.gameResult.winner) {
			R.drawable.ic_smile
		} else {
			R.drawable.ic_sad
		}
	}

	private fun getPercentOfRightAnswers() =  with(args.gameResult) {
		if (countOfQuestions == 0) {
			0
		} else {
			((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
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