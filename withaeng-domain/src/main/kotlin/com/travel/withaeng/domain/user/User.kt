package com.travel.withaeng.domain.user

import com.travel.withaeng.converter.UserRoleConverter
import com.travel.withaeng.domain.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "users")
@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long,

    @Column(name = "nickname", nullable = false)
    val nickname: String,

    @Column(name = "password", nullable = true)
    val password: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = true)
    val socialType: SocialType? = null,

    @Column(name = "provider_unique_key", nullable = true)
    val providerUniqueKey: String? = null,

    @Column(name = "birth", nullable = false)
    val birth: LocalDate,

    @Column(name = "is_male", nullable = false)
    val isMale: Boolean,

    @Column(name = "profile_image_url")
    val profileImageUrl: String? = null,

    @Column(name = "bio")
    val bio: String? = null,

    @Convert(converter = UserRoleConverter::class)
    @Column(name = "roles")
    val roles: Set<UserRole>,
) : BaseEntity()