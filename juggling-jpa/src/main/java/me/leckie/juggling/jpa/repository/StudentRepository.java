package me.leckie.juggling.jpa.repository;

import me.leckie.juggling.jpa.domain.dataobject.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Leckie
 * @version $Id: StudentRepository.java, v0.1 2019/5/15 15:09 john Exp $$
 */
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update Student t set t.name=?2 where t.id=?1")
    void updateNameClearAutomatically(Long id, String name);

    @Modifying(flushAutomatically = true)
    @Query(value = "update Student t set t.name=?2 where t.id=?1")
    void updateNameFlushAutomatically(Long id, String name);

    @Modifying(flushAutomatically = true)
    @Query(value = "update student t set t.name=concat(t.name, ?2) where t.id=?1", nativeQuery = true)
    void increaseNameFlushAutomatically(Long id, String name);

    @Modifying
    @Query(value = "update student t set t.name=concat(t.name, ?2) where t.id=?1", nativeQuery = true)
    void increaseName(Long id, String name);

    @Modifying(clearAutomatically = true)
    @Query(value = "update Student t set t.gender=?2 where t.id=?1")
    void updateGenderClearAutomatically(Long id, String gender);

    @Modifying(flushAutomatically = true)
    @Query(value = "update Student t set t.gender=?2 where t.id=?1")
    void updateGenderFlushAutomatically(Long id, String gender);

    @Query(value = "from Student t where t.id=?1")
    Student queryById(Long id);

    @Modifying
    @Query(value = "update Student t set t.name=?2 where t.id=?1")
    void updateName(Long id, String name);

    @Modifying
    @Query(value = "update Student t set t.gender=?2 where t.id=?1")
    void updateGender(Long id, String gender);

}
