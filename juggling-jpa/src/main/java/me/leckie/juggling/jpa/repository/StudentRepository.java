package me.leckie.juggling.jpa.repository;

import me.leckie.juggling.jpa.domain.dataobject.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Leckie
 * @version $Id: StudentRepository.java, v0.1 2019/5/15 15:09 john Exp $$
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update Student t set t.name=?2 where t.id=?1")
    void updateNameClearAutomatically(Long id, String name);

    @Modifying(flushAutomatically = true)
    @Query(value = "update Student t set t.name=?2 where t.id=?1")
    void updateNameFlushAutomatically(Long id, String name);

    @Modifying(clearAutomatically = true)
    @Query(value = "update Student t set t.gender=?2 where t.id=?1")
    void updateGenderClearAutomatically(Long id, String gender);

    @Modifying(flushAutomatically = true)
    @Query(value = "update Student t set t.gender=?2 where t.id=?1")
    void updateGenderFlushAutomatically(Long id, String gender);

}
