package com.travel.withaeng.domain.user

import com.travel.withaeng.converter.UserRoleConverter
import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Table(name = "users")
@Entity
class User(
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "nickname", nullable = false)
    val nickname: String,

    @Column(name = "birth", nullable = true)
    val birth: LocalDate? = null,

    @Column(name = "is_male", nullable = true)
    val isMale: Boolean? = null,

    @Column(name = "profile_image_url")
    val profileImageUrl: String? = null,

    @Column(name = "bio")
    val bio: String? = null,

    @Convert(converter = UserRoleConverter::class)
    @Column(name = "roles")
    val roles: Set<UserRole>,
) : BaseEntity() {

    companion object {

        fun create(
            email: String,
            password: String,
            nickname: String,
            birth: LocalDate? = null,
            isMale: Boolean? = null,
            profileImageUrl: String? = null,
            bio: String? = null,
        ): User {
            return User(
                email = email,
                password = password,
                nickname = nickname,
                profileImageUrl = profileImageUrl,
                birth = birth,
                isMale = isMale,
                bio = bio,
                roles = setOf(UserRole.USER)
            )
        }
    }
}