package com.robotnec.budget.core.domain

import java.io.Serializable


/**
 * @author zak zak@swingpulse.com>
 */
data class Category(override var id: Long = -1,
                    override var name: String) : Serializable, Entity
