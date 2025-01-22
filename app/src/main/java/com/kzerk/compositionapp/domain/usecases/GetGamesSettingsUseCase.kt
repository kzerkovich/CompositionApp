package com.kzerk.compositionapp.domain.usecases

import com.kzerk.compositionapp.domain.entity.GameSettings
import com.kzerk.compositionapp.domain.entity.Level
import com.kzerk.compositionapp.domain.repository.GameRepository

class GetGamesSettingsUseCase(
	private val repository: GameRepository
) {

	operator fun invoke(level: Level): GameSettings {
		return repository.getGameSettings(level)
	}
}