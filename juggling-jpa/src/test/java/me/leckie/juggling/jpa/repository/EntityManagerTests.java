package me.leckie.juggling.jpa.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import me.leckie.juggling.jpa.domain.dataobject.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leckie
 * @version $Id: EntityManagerTests.java, v0.1 2019/5/17 9:10 john Exp $$
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EntityManagerTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private List<EntityManager> entityManagers;

    @Test
    @Commit
    public void test() {
        Student student2 = entityManager.find(Student.class, 2L);
        Student student3 = entityManager.find(Student.class, 3L);
        Student student4 = new Student();
        student4.setName("8");
        student4.setGender("44");
        entityManager.persist(student4);
        student2.setName("2");
        student3.setName("3");
        // entityManager.clear();
    }

    @Test
    @Commit
    public void test2() {
        Student student2 = entityManager.find(Student.class, 2L);
        student2.setName("zhangsan");
    }

    @Test
    @Commit
    public void test3() {
        entityManager.setFlushMode(FlushModeType.COMMIT );
        System.out.println(entityManager.getFlushMode().name());
        Student student2 = entityManager.find(Student.class, 2L);
        student2.setName("lisi1");
    }

}
