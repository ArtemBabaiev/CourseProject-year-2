package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.AdultFactory;
import edu.babaiev.libr.factory.PupilFactory;
import edu.babaiev.libr.factory.ScientistFactory;
import edu.babaiev.libr.factory.StudentFactory;
import edu.babaiev.libr.model.Adult;
import edu.babaiev.libr.model.Pupil;
import edu.babaiev.libr.model.Scientist;
import edu.babaiev.libr.model.Student;
import edu.babaiev.libr.service.AdultService;
import edu.babaiev.libr.service.PupilService;
import edu.babaiev.libr.service.ScientistService;
import edu.babaiev.libr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 04:12
 * @class ReaderSeeder
 */
@Component
public class ReaderSeeder {
    AdultService adultService;
    AdultFactory adultFactory;

    PupilService pupilService;
    PupilFactory pupilFactory;

    ScientistService scientistService;
    ScientistFactory scientistFactory;

    StudentService studentService;
    StudentFactory studentFactory;

    @Autowired
    public ReaderSeeder(AdultService adultService, AdultFactory adultFactory, PupilService pupilService, PupilFactory pupilFactory, ScientistService scientistService, ScientistFactory scientistFactory, StudentService studentService, StudentFactory studentFactory) {
        this.adultService = adultService;
        this.adultFactory = adultFactory;
        this.pupilService = pupilService;
        this.pupilFactory = pupilFactory;
        this.scientistService = scientistService;
        this.scientistFactory = scientistFactory;
        this.studentService = studentService;
        this.studentFactory = studentFactory;
    }

    public void Seed(){
        List<Adult> listB = adultFactory.generate();
        for (Adult item:listB) {
            adultService.create(item);
        }

        List<Pupil> listC = pupilFactory.generate();
        for (Pupil item:listC) {
            pupilService.create(item);
        }

        List<Scientist> listM = scientistFactory.generate();
        for (Scientist item:listM) {
            scientistService.create(item);
        }

        List<Student> listP = studentFactory.generate();
        for (Student item:listP) {
            studentService.create(item);
        }
    }
}
