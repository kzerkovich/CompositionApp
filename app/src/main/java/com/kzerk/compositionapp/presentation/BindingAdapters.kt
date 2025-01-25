package com.kzerk.compositionapp.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.kzerk.compositionapp.R
import com.kzerk.compositionapp.domain.entity.GameResult

interface OnOptionClickListener {
	fun onOptionClick(option: Int)
}

@SuppressLint("StringFormatMatches")
@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
	textView.text = String.format(
		textView.context.getString(R.string.required_score),
		count
	)
}

@SuppressLint("StringFormatMatches")
@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
	textView.text = String.format(
		textView.context.getString(R.string.score_answers),
		count
	)
}

@SuppressLint("StringFormatMatches")
@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
	textView.text = String.format(
		textView.context.getString(R.string.required_percentage),
		count
	)
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
	if (winner) {
		imageView.setImageResource(R.drawable.ic_smile)
	} else {
		imageView.setImageResource(R.drawable.ic_sad)
	}
}

@SuppressLint("StringFormatMatches")
@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
	val percentage = if (gameResult.countOfQuestions == 0) {
		0
	} else {
		((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
	}
	textView.text = String.format(
		textView.context.getString(R.string.score_percentage),
		percentage
	)
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
	textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
	val color = getColorByState(progressBar.context, enough)
	progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
	val colorResId = if (goodState) {
		android.R.color.holo_green_light
	} else {
		android.R.color.holo_red_light
	}
	return ContextCompat.getColor(context, colorResId)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("numberAsText")
fun bindNUmberAsText(textView: TextView, number: Int) {
	textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
	textView.setOnClickListener {
		clickListener.onOptionClick(textView.text.toString().toInt())
	}
}