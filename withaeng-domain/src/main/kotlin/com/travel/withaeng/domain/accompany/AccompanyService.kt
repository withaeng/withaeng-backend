package com.travel.withaeng.domain.accompany

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyService(private val accompanyRepository: AccompanyRepository) {

    fun createAccompany(param : CreateAccompanyDTO) : Long {
        val accompanyEntity = param.toEntity();
        accompanyRepository.save(accompanyEntity);
        return accompanyEntity.id;
    }
}