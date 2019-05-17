package me.leckie.juggling.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import me.leckie.juggling.jpa.domain.dataobject.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leckie
 * @version $Id: StudentRepositoryTests.java, v0.1 2019/5/15 15:52 john Exp $$
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Commit
    public void testNoFlushAndClear() {
        Student studentBefore = studentRepository.findById(2L).orElse(null);
        studentRepository.updateName(2l, studentBefore.getName() + "-updateName");
        // 查的是一级缓存
        Student studentAfter = studentRepository.findById(2L).orElse(null);
        Assert.assertEquals(studentBefore.getName(), studentAfter.getName());
    }

    @Test
    @Commit
    public void testIncrease() {
        Student studentBefore = studentRepository.findById(2L).orElse(null);
        studentBefore.setName("xixi2");
        studentRepository.increaseName(2l, "--increase");
        Student studentAfter = studentRepository.findById(2L).orElse(null);
        // 没有刷新，数据库中的字段和一级缓存不一致
        Assert.assertEquals(studentBefore.getName(), studentAfter.getName());
    }

    @Test
    @Commit
    public void testQueryFlush() {
        entityManager.setFlushMode(FlushModeType.COMMIT);
        Student student = studentRepository.findById(2L).orElse(null);
        student.setName("queryName");
        studentRepository.updateNameFlushAutomatically(2L, "transactional");
        Student student1 = studentRepository.findById(2L).orElse(null);
        Assert.assertTrue(student == student1);
    }

    @Test
    @Commit
    public void testIncreaseFlush() {
        Student studentBefore = studentRepository.findById(2L).orElse(null);
        studentBefore.setName("Lec");
        studentRepository.increaseNameFlushAutomatically(2l, "--increase");
        Student studentAfter = studentRepository.findById(2L).orElse(null);
        // 刷新，一级缓存中的改动刷新到数据库
        Assert.assertEquals(studentBefore.getName() + "--increase", studentAfter.getName());
    }


    @Test
    @Commit
    public void testClear() {
        Student student = studentRepository.findById(2L).orElse(null);
        Assert.assertNotNull(student);
        student.setGender("FEMALE");
        studentRepository.updateNameClearAutomatically(3l, "Leckie5");
        Assert.assertEquals("FEMALE", student.getGender());
        Assert.assertEquals("MALE", studentRepository.findById(2L).get().getGender());
    }

}