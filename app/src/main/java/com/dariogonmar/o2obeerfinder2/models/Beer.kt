package com.dariogonmar.o2obeerfinder2.models

import com.google.gson.annotations.SerializedName

typealias BeersResponse = ArrayList<Beer>

data class Beer (
    val id: Long,
    val name: String,
    val description: String,
    val tagline: String,
    @SerializedName("first_brewed") val firstBrewed: String = "",
    @SerializedName("image_url") val imageURL: String = "",
    val abv: Double = 0.0,
    val ibu: Double? = null,
    val targetFg: Long = 0,
    val targetOg: Double = 0.0,
    val ebc: Long? = null,
    val srm: Double? = null,
    val ph: Double? = null,
    val attenuationLevel: Double = 0.0,
    val volume: BoilVolume = BoilVolume(0.0, Unit.celsius),
    val boilVolume: BoilVolume = BoilVolume(0.0, Unit.celsius),
    val method: Method = Method(emptyList(), Fermentation(BoilVolume(0.0, Unit.celsius))),
    val ingredients: Ingredients = Ingredients(emptyList(), emptyList(), ""),
    val foodPairing: List<String> = emptyList(),
    @SerializedName("brewers_tips") val brewersTips: String = "",
    val contributedBy: ContributedBy = ContributedBy.SamMasonSamjbmason
)

data class BoilVolume (
    val value: Double,
    val unit: Unit
)

enum class Unit {
    celsius,
    grams,
    kilograms,
    litres
}

enum class ContributedBy {
    AliSkinnerAliSkinner,
    SamMasonSamjbmason
}

data class Ingredients (
    val malt: List<Malt>,
    val hops: List<Hop>,
    val yeast: String
)

data class Hop (
    val name: String,
    val amount: BoilVolume,
    val add: Add,
    val attribute: Attribute
)

enum class Add {
    DryHop,
    End,
    Middle,
    Start
}

enum class Attribute {
    Aroma,
    AttributeFlavour,
    Bitter,
    Flavour
}

data class Malt (
    val name: String,
    val amount: BoilVolume
)

data class Method (
    val mashTemp: List<MashTemp>,
    val fermentation: Fermentation,
    val twist: String? = null
)

data class Fermentation (
    val temp: BoilVolume
)

data class MashTemp (
    val temp: BoilVolume,
    val duration: Long? = null
)
