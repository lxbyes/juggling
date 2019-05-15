package me.leckie.juggling.jpa.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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

    @Test
    public void test() {
    }

}