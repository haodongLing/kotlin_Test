package com.haodong.kotlintest

/**
 * created by linghaoDo on 2020-02-21
 * description:
 *
 * version:
 */
class Bird(override val name: String) : Flyer, Animal {
    override fun kind() = super<Flyer>.kind()
    override fun fly() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun eat() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}