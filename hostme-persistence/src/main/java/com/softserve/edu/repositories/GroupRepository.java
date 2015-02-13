package com.softserve.edu.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.model.Group;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.User;

@SuppressWarnings("rawtypes")
public interface GroupRepository extends
        PagingAndSortingRepository<Group, Long>, JpaSpecificationExecutor {

    public List<Group> findAllByCreatorUser(User creatorUser, Pageable peagelbe);

    public List<Group> findAllByInterestedUsers(User interestedUsers,
            Pageable peagelbe);

    public List<Group> findAllByStatus(Status status, Pageable peagelbe);

    public List<Group> findAllByCreatorUser(User creatorUser);

    public List<Group> findAllByInterestedUsers(User interestedUsers);

    public List<Group> findAllByStatus(Status status);

    @Modifying
    @Transactional("transactionManager")
    @Query(value = "DELETE FROM groups_subscribers WHERE group_id = ?2 AND user_id = ?1", nativeQuery = true)
    public void unsubscribe(Integer userId, Long groupId);

    @Transactional("transactionManager")
    @Query(value = "SELECT COUNT(*) FROM groups_subscribers WHERE group_id = ?1", nativeQuery = true)
    public Integer getGroupSubscribers(Long groupId);

    @Modifying
    @Transactional("transactionManager")
    @Query(value = "DELETE FROM groups_subscribers WHERE group_id = ?1", nativeQuery = true)
    public void removeGroupSubscription(Long groupId);

    public Long countByCreatorUser(User creatorUser);

}
