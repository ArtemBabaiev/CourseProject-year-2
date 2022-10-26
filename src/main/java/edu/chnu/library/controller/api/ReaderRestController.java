package edu.chnu.library.controller.api;

import edu.chnu.library.dto.ReaderActivityDTO;
import edu.chnu.library.model.Reader;
import edu.chnu.library.service.ReaderService;
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
 * @date 25.10.2022 23:24
 * @class ReaderRestController
 */
@RestController
@RequestMapping("/api/readers")
public class ReaderRestController {
    ReaderService service;

    @Autowired
    public ReaderRestController(ReaderService service) {
        this.service = service;
    }

    @GetMapping("/lendLiterature/{literatureId}")
    List<Reader> whichLendLiterature(@PathVariable String literatureId) {
        return service.getReadersThatLendLiterature(literatureId);
    }

    @GetMapping("/withNoActivityInPeriod/{lowerDate}&{upperDate}")
    List<ReaderActivityDTO> withNoActivityInPeriod(@PathVariable String lowerDate, @PathVariable String upperDate) {
        return service.getWithNoActivityInPeriod(lowerDate, upperDate);
    }

    @GetMapping("/withOverdue")
    List<Reader> withOverdue() {
        return service.getWithOverdue();
    }
}
