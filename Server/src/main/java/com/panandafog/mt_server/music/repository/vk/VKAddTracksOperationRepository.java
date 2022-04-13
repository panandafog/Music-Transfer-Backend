package com.panandafog.mt_server.music.repository.vk;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.music.entities.vk.VKAddTracksOperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VKAddTracksOperationRepository extends PagingAndSortingRepository<VKAddTracksOperationEntity, Integer> {

    List<VKAddTracksOperationEntity> findByIdAndUser(Integer id, AppUser user);

    Page<VKAddTracksOperationEntity> findByUser(AppUser user, Pageable pageable);
}
