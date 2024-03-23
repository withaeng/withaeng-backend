package com.travel.withaeng.domain.accompany

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccompanyService(

    private val accompanyRepository: AccompanyRepository,
    private val accompanyHistRepository : AccompanyHistRepository,
    private val accompanyDestinationRepository : AccompanyDestinationRepository,
    private val accompanyTagRepository: AccompanyTagRepository

) {

    @Transactional
    fun createAccompany(param : CreateAccompanyDTO) : Long {
        val accompanyEntity = param.toEntity()
        accompanyRepository.save(accompanyEntity)

        val accompanyHistEntity = param.toHistEntity(accompanyEntity)
        val accompanyDestinationEntity = param.toDestinationEntity(accompanyEntity.accompanyId)
        val accompanyTagEntityList = param.toTagEntity(accompanyEntity.accompanyId)

        accompanyHistRepository.save(accompanyHistEntity)
        accompanyDestinationRepository.save(accompanyDestinationEntity)

        if(accompanyTagEntityList != null){
            accompanyTagRepository.saveAll(accompanyTagEntityList)
        }

        return accompanyEntity.accompanyId;
    }

    fun getOne(param : Long) : ReadAccompanyDTO {
        val accompanyEntity = accompanyRepository.findByAccompanyId(param)
        val accompanyDestinationEntity = accompanyDestinationRepository.findByAccompanyId(param)
        return ReadAccompanyDTO.toDto(accompanyEntity, accompanyDestinationEntity)
    }

    fun getList(param : Long) : ReadAccompanyDTO {
        val accompanyEntity = accompanyRepository.findByAccompanyId(param)
        val accompanyDestinationEntity = accompanyDestinationRepository.findByAccompanyId(param)
        return ReadAccompanyDTO.toDto(accompanyEntity, accompanyDestinationEntity)
    }

    @Transactional
    fun incrViewCnt(param : Long) : Long{
        val accompanyEntity = accompanyRepository.findByAccompanyId(param)
        accompanyEntity.let {
            it.viewCnt++
        }
        return param
    }
}