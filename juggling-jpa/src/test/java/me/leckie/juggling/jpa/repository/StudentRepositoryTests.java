package me.leckie.juggling.jpa.repository;

import me.leckie.juggling.jpa.domain.dataobject.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    @Transactional
    @Commit
    public void test() {
        Student student2 = studentRepository.findById(2L).orElse(null);
        Student student3 = studentRepository.findById(3L).orElse(null);
        studentRepository.updateName(2l, "Vickie123");
        student2.setName("Vickie-set123");
        student3.setName("Leckie-set123");
    }

    @Test
    @Commit
    public void testFlush() {
        Student student = studentRepository.findById(2L).orElse(null);
        Assert.assertNotNull(student);
        student.setGender("FEMALE");
        studentRepository.updateNameFlushAutomatically(2l, "Leckie");
        Assert.assertEquals("FEMALE", student.getGender());
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