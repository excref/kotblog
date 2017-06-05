package com.excref.kotblog.blog.service.common

import java.util.*
import javax.persistence.MappedSuperclass

/**
 * @author Arthur Asatryan
 * @since 6/4/17 8:26 PM
 */
@MappedSuperclass
abstract class UuidAwareDomain(val uuid: String = UUID.randomUUID().toString()) : BasicDomain() {
    //region Equals, HashCode and ToString
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UuidAwareDomain) return false
        if (!super.equals(other)) return false
        if (uuid != other.uuid) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + uuid.hashCode()
        return result
    }

    override fun toString(): String {
        return "UuidAwareDomain(uuid='$uuid') ${super.toString()}"
    }
    //endregion
}