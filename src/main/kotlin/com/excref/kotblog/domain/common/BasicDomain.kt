package com.excref.kotblog.domain.common

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * @author Arthur Asatryan
 * @since 6/4/17 8:21 PM
 */
@MappedSuperclass
abstract class BasicDomain(
        @Id
        @GeneratedValue val id: Long? = null,
        val created: LocalDateTime = LocalDateTime.now(),
        val updated: LocalDateTime = LocalDateTime.now(),
        val removed: LocalDateTime? = null
) : Serializable {

    //region Equals, HashCode and ToString
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BasicDomain) return false
        if (id != other.id) return false
        if (created != other.created) return false
        if (updated != other.updated) return false
        if (removed != other.removed) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + created.hashCode()
        result = 31 * result + updated.hashCode()
        result = 31 * result + (removed?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "BasicDomain(id=$id, created=$created, updated=$updated, removed=$removed)"
    }
    //endregion
}