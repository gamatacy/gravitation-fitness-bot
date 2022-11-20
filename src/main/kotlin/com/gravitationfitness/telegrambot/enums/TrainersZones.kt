package com.gravitationfitness.telegrambot.enums

enum class TrainersZones(val zoneType: String) {
    FIGHT_ZONE("Зона единоборств"),
    AQUA_ZONE("Аква зона"),
    GYM_ZONE("Тренажерный зал"),
    GROUP_ZONE("Групповые программы");

    companion object{
        fun getByValue(value: String): TrainersZones{
            for (zone in TrainersZones.values()){
                if(zone.zoneType == value){
                    return zone
                }
            }
            return FIGHT_ZONE
        }
    }
}
