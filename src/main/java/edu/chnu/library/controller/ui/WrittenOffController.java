package edu.chnu.library.controller.ui;

import edu.chnu.library.exception.ExemplarInUseException;
import edu.chnu.library.exception.LiteratureNotLendableException;
import edu.chnu.library.service.WrittenOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 17.10.2022 00:30
 * @class WrittenOffController
 */
@Controller
@RequestMapping("/ui/writtenOffs")
public class WrittenOffController {
    WrittenOffService writtenOffService;

    @Autowired
    public WrittenOffController(WrittenOffService writtenOffService) {
        this.writtenOffService = writtenOffService;
    }

    @GetMapping("/show")
    public String showPage(HttpServletRequest request, Model model) {
        int page = 0;
        int size = 10;
        String name = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
            name = request.getParameter("name");
        }
        model.addAttribute("writtenOffs", writtenOffService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "writtenOff/all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("writtenOff", writtenOffService.get(id));
        return "writtenOff/show";
    }

    @PostMapping("/create/{exemplarId}")
    public String createWrittenOff(@PathVariable String exemplarId, Model model) {
        try {
            writtenOffService.writeOffExemplar(exemplarId);
            return "redirect:/ui/writtenOffs/show";
        } catch (ExemplarInUseException e) {
            model.addAttribute("error", "Екземпляр вже в користуванні");
            return "error/notPossibleAction";
        } catch (LiteratureNotLendableException e){
            model.addAttribute("error", "Література не доступна для видання");
            return "error/notPossibleAction";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        writtenOffService.delete(id);
        return "redirect:/ui/writtenOffs/show";
    }

}
