package me.leckie.juggling.jpa.web.controller;

import java.util.List;
import me.leckie.juggling.jpa.domain.dataobject.Student;
import me.leckie.juggling.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laixianbo
 * @version $Id: DataJpaController.java, v0.1 2019/5/15 16:34 john Exp $$
 */
@RestController
public class DataJpaController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("data")
    public List<Student> getData() {
        return studentRepository.findAll();
    }

}
