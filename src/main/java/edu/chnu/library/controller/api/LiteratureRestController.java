package edu.chnu.library.controller.api;

import edu.chnu.library.model.Literature;
import edu.chnu.library.service.LiteratureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 25.10.2022 21:15
 * @class LiteratureRestController
 */
@RestController
@RequestMapping("/api/literature")
public class LiteratureRestController {
    LiteratureService service;

    @Autowired
    public LiteratureRestController(LiteratureService service) {
        this.service = service;
    }

    @GetMapping("/createdInPeriod/{lowerDate}&{upperDate}")
    public List<Literature> CreatedInPeriod(@PathVariable String lowerDate, @PathVariable String upperDate) {
        return service.getCreatedInPeriod(lowerDate, upperDate);
    }

    @GetMapping("/usedBy/{readerId}/inPeriod/{lowerDate}&{upperDate}")
    public List<Literature> usedByReaderInPeriod(@PathVariable String readerId, @PathVariable String lowerDate, @PathVariable String upperDate) {
        return service.getUsedByReaderInPeriod(readerId, lowerDate, upperDate);
    }

    @GetMapping("/withAuthor/{authorId}")
    public List<Literature> withAuthor(@PathVariable String authorId) {
        return service.getContainsAuthor(authorId);
    }

    @GetMapping("/withWriting/{writingId}")
    public List<Literature> withWriting(@PathVariable String writingId) {
        return service.getContainsWriting(writingId);
    }
}
