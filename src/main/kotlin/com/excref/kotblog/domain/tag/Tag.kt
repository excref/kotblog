package com.excref.kotblog.domain.tag

import java.io.Serializable
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Arthur Asatryan
 * @since 6/4/17 3:13 PM
 */
@Entity
data class Tag(
        val name: String = "",
        val uuid: String = UUID.randomUUID().toString(),
        @Id
        @GeneratedValue val id: Long? = null
) : Serializable