package com.egiwon.moviesearch.base

open class BaseIdentifier : Any() {
    open val id = Any()

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override operator fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        (other as? BaseIdentifier)?.run {
            if (id != other.id) return false
            return true
        }

        return false
    }
}