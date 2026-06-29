package com.kayevo.sportive_match.domain.generate_team_match

import kotlin.random.Random

class RandomNumberGenerator() {
    fun nextInt(from: Int, until: Int): Int = Random.nextInt(from, until)
}