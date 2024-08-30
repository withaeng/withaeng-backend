package com.withaeng.infrastructure.accompany

import com.withaeng.domain.accompany.Accompany
import org.springframework.data.jpa.repository.JpaRepository

interface AccompanyJpaRepository : JpaRepository<Accompany, Long>