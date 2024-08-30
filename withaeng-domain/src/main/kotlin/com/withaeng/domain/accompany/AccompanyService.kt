package com.withaeng.domain.accompany

import com.withaeng.domain.accompany.dto.CreateAccompanyCommand
import com.withaeng.domain.accompany.dto.FindAccompanyDetailResult
import com.withaeng.domain.tag.TagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccompanyService(
    private val accompanyReader: AccompanyReader,
    private val accompanyStore: AccompanyStore,
    private val tagRepository: TagRepository,
) {
    companion object {
        private const val NOT_EXIST_MESSAGE = "해당하는 동행을 찾을 수 없습니다."
    }

    @Transactional
    fun create(command: CreateAccompanyCommand): AccompanyDto {
        val actualTagIds = filterValidTagIds(command.tagIds)
        val actualParams = command.copy(tagIds = actualTagIds)
        val accompanyEntity = Accompany.create(actualParams)
        accompanyStore.save(accompanyEntity)
        return accompanyEntity.toDto()
    }

    @Transactional
    fun update(params: UpdateAccompanyDto): AccompanyDto {
        val accompany = findById(params.accompanyId)
        accompany.update(
            content = params.content,
            tagIds = params.tagIds,
        )

        return accompany.toDto()
    }

    @Transactional(readOnly = true)
    fun detail(accompanyId: Long): FindAccompanyDetailResult {
        return accompanyReader.findAccompanyDetail(accompanyId)
    }

    @Transactional
    fun increaseViewCount(accompanyId: Long) {
        val accompany = findById(accompanyId)
        accompany.increaseViewCount()
    }

    private fun findById(accompanyId: Long): Accompany {
        return accompanyReader.findByIdOrNull(accompanyId)
    }

    // TODO FindAccompanySimpleResult
    fun findAll(): List<FindAccompanyDetailResult> {
        return accompanyReader.findAll()
            .map(FindAccompanyDetailResult::of)
    }

    private fun filterValidTagIds(tagIds: Set<Long>?): Set<Long> {
        if (tagIds == null) return emptySet()
        return tagRepository.findAllById(tagIds)
            .map { it.id }
            .toSet()
    }
}