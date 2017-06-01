package com.robotnec.budget.core.persistence.dao

/**
 * @author zak zak@swingpulse.com>
 */
interface BaseDao<T> {

    val all: List<T>

    fun findById(id: Long): T

    fun remove(id: Long): Boolean

    fun createOrUpdate(entity: T): Boolean
}
