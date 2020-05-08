package com.haodong.kotlintest

/**
 * created by linghaoDo on 2020-04-23
 * description:
 *
 * version:
 */
sealed class Shape {
    class Circle(val radius: Double) : Shape()
    class RectRangle(val width: Double, val height: Double) : Shape()
    class Triangle(val base: Double, val height: Double) : Shape()

}

fun getArea(shape: Shape): Double = when (shape) {
    is Shape.Circle -> Math.PI * shape.radius * shape.radius
    is Shape.RectRangle -> shape.height * shape.width
    is Shape.Triangle -> shape.base * shape.height / 2

}