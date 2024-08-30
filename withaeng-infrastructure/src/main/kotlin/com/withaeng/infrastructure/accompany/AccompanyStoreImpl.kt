package com.withaeng.infrastructure.accompany

import com.withaeng.domain.accompany.Accompany
import com.withaeng.domain.accompany.AccompanyStore
import org.springframework.stereotype.Component

@Component
class AccompanyStoreImpl(
    private val accompanyJpaRepository: AccompanyJpaRepository,
) : AccompanyStore {

    override fun save(accompany: Accompany): Accompany {
        return accompanyJpaRepository.save(accompany)
    }

}