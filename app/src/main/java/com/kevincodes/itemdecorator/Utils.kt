package com.kevincodes.itemdecorator

val fruits: List<Fruit> =
    listOf(
        Fruit(0, "Banana"),
        Fruit(1, "Avocado"),
        Fruit(2, "Apple"),
        Fruit(3, "Pineapple"),
        Fruit(4, "Orange"),
        Fruit(5, "Grape"),
        Fruit(6, "Cherry"),
        Fruit(7, "Mango")
    )

data class Fruit(var id: Int, var fruitName: String)