package com.kzerk.compositionapp.domain.repository

import com.kzerk.compositionapp.domain.entity.GameSettings
import com.kzerk.compositionapp.domain.entity.Level
import com.kzerk.compositionapp.domain.entity.Question

interface GameRepository {
	fun generateQuestion(
		maxSumValue: Int,
		countOfOptions: Int
	): Question

	fun getGameSettings(level: Level): GameSettings
}