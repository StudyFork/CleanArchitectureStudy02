package com.egiwon.moviesearch.base

open class BaseIdentifier : Any() {
    open val id = Any()

    override operator fun equals(other: Any?): Boolean = this.hashCode() == other.hashCode()

    override fun hashCode(): Int {
        return id.hashCode()
    }
}