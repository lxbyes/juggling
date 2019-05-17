package me.leckie.juggling.jpa.web.controller;

import java.util.List;
import me.leckie.juggling.jpa.domain.dataobject.Student;
import me.leckie.juggling.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leckie
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

    @GetMapping("refresh")
    @Transactional
    public List<Student> refresh(String name) {
        return doSomething(name);
    }

    @GetMapping("refresh_no")
    public List<Student> refreshNo(String name) {
        return doSomething(name);
    }

    private List<Student> doSomething(String name) {
        Student student2 = studentRepository.findById(2L).orElse(null);
        Student student3 = studentRepository.findById(3L).orElse(null);
        student2.setGender(name + "-gender");
        student3.setGender(name + "-gender");
        //studentRepository.updateName(2l, name);
        studentRepository.updateNameFlushAutomatically(2l, name);
        //studentRepository.updateNameClearAutomatically(2l, name);
        student2.setName(name + "-set");
        student3.setName(name + "-set");
        return studentRepository.findAll();
    }


}
