package edu.chnu.library.controller.ui;

import edu.chnu.library.form.LiteratureChooseForm;
import edu.chnu.library.model.*;
import edu.chnu.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 22.08.2022 20:26
 * @class LiteratureRestController
 */
@Controller
@RequestMapping("/ui/literature")
public class LiteratureController {
    LiteratureService literatureService;
    PublisherService publisherService;
    ArticleService articleService;
    AuthorService authorService;
    WritingService writingService;
    GenreService genreService;
    SubjectService subjectService;
    LibraryService libraryService;
    ReadingRoomService readingRoomService;
    BookCaseService bookCaseService;
    ShelfService shelfService;
    BookTypeService bookTypeService;
    CollectionTypeService collectionTypeService;
    MonographTypeService monographTypeService;
    PeriodicalTypeService periodicalTypeService;
    ExemplarService exemplarService;

    @Autowired
    public LiteratureController(LiteratureService literatureService, PublisherService publisherService,
                                ArticleService articleService, AuthorService authorService, WritingService writingService,
                                GenreService genreService, SubjectService subjectService, LibraryService libraryService,
                                ReadingRoomService readingRoomService, BookCaseService bookCaseService, ShelfService shelfService,
                                BookTypeService bookTypeService, CollectionTypeService collectionTypeService,
                                MonographTypeService monographTypeService, PeriodicalTypeService periodicalTypeService,
                                ExemplarService exemplarService) {
        this.literatureService = literatureService;
        this.publisherService = publisherService;
        this.articleService = articleService;
        this.authorService = authorService;
        this.writingService = writingService;
        this.genreService = genreService;
        this.subjectService = subjectService;
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
        this.bookCaseService = bookCaseService;
        this.shelfService = shelfService;
        this.bookTypeService = bookTypeService;
        this.collectionTypeService = collectionTypeService;
        this.monographTypeService = monographTypeService;
        this.periodicalTypeService = periodicalTypeService;
        this.exemplarService = exemplarService;
    }


    @GetMapping("/show")
    public String literaturePage(HttpServletRequest request, Model model) {
        int page = 0;
        int size = 10;
        String name = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
            name = request.getParameter("name");
        }
        model.addAttribute("literature", literatureService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "literature/literature-all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        Literature literature = literatureService.get(id);
        Map<Library, Long> exemplarCountByLibrary = exemplarService.getAllByLiterature(literature).stream()
                .collect(Collectors.groupingBy(exemplar -> exemplar.getShelf().getBookCase().getReadingRoom().getLibrary(), Collectors.counting()));
        model.addAttribute("exemplarCountByLibrary", exemplarCountByLibrary);
        if (Book.class.equals(literature.getClass())) {
            model.addAttribute("book", (Book) literature);
            return "literature/book/book-show";
        }
        if (Collection.class.equals(literature.getClass())) {
            model.addAttribute("collection", (Collection) literature);
            return "literature/collection/collection-show";
        }
        if (Monograph.class.equals(literature.getClass())) {
            model.addAttribute("monograph", (Monograph) literature);
            return "literature/monograph/monograph-show";
        }
        if (Periodical.class.equals(literature.getClass())) {
            model.addAttribute("periodical", (Periodical) literature);
            return "literature/periodical/periodical-show";
        }
        return "redirect:/literature/show/";
    }

    @GetMapping("/create")
    public String showChooseCategory(Model model) {
        model.addAttribute("categoryChooseForm", new LiteratureChooseForm());
        return "literature/category-choose";
    }

    @PostMapping("/create")
    public String redirectCategory(LiteratureChooseForm literatureChooseForm) {
        switch (literatureChooseForm.getCategory()) {
            case "Book":
                return "redirect:/ui/literature/create/book";
            case "Collection":
                return "redirect:/ui/literature/create/collection";
            case "Monograph":
                return "redirect:/ui/literature/create/monograph";
            case "Periodical":
                return "redirect:/ui/literature/create/periodical";
        }
        return "redirect:/ui/literature/create";
    }

    @GetMapping("/create/book")
    public String showCreateBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("genres", genreService.getAll());
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("writings", writingService.getAll());
        model.addAttribute("bookTypes", bookTypeService.getAll());
        return "literature/book/book-create";
    }

    @PostMapping("/create/book")
    public String performCreateBook(Book book) {
        Literature newOne = literatureService.create(book);
        return "redirect:/ui/literature/show/" + newOne.getId();
    }

    @GetMapping("/create/collection")
    public String showCreateCollection(Model model) {
        model.addAttribute("collection", new Collection());
        model.addAttribute("genres", genreService.getAll());
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("writings", writingService.getAll());
        model.addAttribute("collectionTypes", collectionTypeService.getAll());
        return "literature/collection/collection-create";
    }

    @PostMapping("/create/collection")
    public String performCreateCollection(Collection collection) {
        Literature newOne = literatureService.create(collection);
        return "redirect:/ui/literature/show/" + newOne.getId();
    }

    @GetMapping("/create/monograph")
    public String showCreateMonograph(Model model) {
        model.addAttribute("monograph", new Monograph());
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("monographTypes", monographTypeService.getAll());
        return "literature/monograph/monograph-create";
    }

    @PostMapping("/create/monograph")
    public String performCreateMonograph(Monograph monograph) {
        Literature newOne = literatureService.create(monograph);
        return "redirect:/ui/literature/show/" + newOne.getId();
    }

    @GetMapping("/create/periodical")
    public String showCreatePeriodical(Model model) {
        model.addAttribute("periodical", new Periodical());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("articles", articleService.getAll());
        model.addAttribute("periodicalTypes", periodicalTypeService.getAll());
        return "literature/periodical/periodical-create";
    }

    @PostMapping("/create/periodical")
    public String performCreatePeriodical(Periodical periodical) {
        Literature newOne = literatureService.create(periodical);
        return "redirect:/ui/literature/show/" + newOne.getId();
    }


    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        Literature literature = literatureService.get(id);
        model.addAttribute("publishers", publisherService.getAll());
        model.addAttribute("authors", authorService.getAll());
        if (Book.class.equals(literature.getClass())) {
            model.addAttribute("book", (Book) literature);
            model.addAttribute("writings", writingService.getAll());
            model.addAttribute("genres", genreService.getAll());
            model.addAttribute("bookTypes", bookTypeService.getAll());
            return "literature/book/book-edit";
        }
        if (Collection.class.equals(literature.getClass())) {
            model.addAttribute("collection", (Collection) literature);
            model.addAttribute("writings", writingService.getAll());
            model.addAttribute("genres", genreService.getAll());
            model.addAttribute("collectionTypes", collectionTypeService.getAll());
            return "literature/collection/collection-edit";
        }
        if (Monograph.class.equals(literature.getClass())) {
            model.addAttribute("monograph", (Monograph) literature);
            model.addAttribute("monographTypes", monographTypeService.getAll());
            return "literature/monograph/monograph-edit";
        }
        if (Periodical.class.equals(literature.getClass())) {
            model.addAttribute("periodical", (Periodical) literature);
            model.addAttribute("subjects", subjectService.getAll());
            model.addAttribute("articles", articleService.getAll());
            model.addAttribute("periodicalTypes", periodicalTypeService.getAll());
            return "literature/periodical/periodical-edit";
        }
        return "redirect:/literature/show/";
    }

    @PutMapping("/edit/{id}/book")
    public String performEditBook(@PathVariable String id, Book book) {
        Literature updated = literatureService.update(book);
        return "redirect:/ui/literature/show/" + updated.getId();
    }

    @PutMapping("/edit/{id}/collection")
    public String performEditCollection(@PathVariable String id, Collection collection) {
        Literature updated = literatureService.update(collection);
        return "redirect:/ui/literature/show/" + updated.getId();
    }

    @PutMapping("/edit/{id}/monograph")
    public String performEditMonograph(@PathVariable String id, Monograph monograph) {
        Literature updated = literatureService.update(monograph);
        return "redirect:/ui/literature/show/" + updated.getId();
    }

    @PutMapping("/edit/{id}/periodical")
    public String performEditPeriodical(@PathVariable String id, Periodical periodical) {
        Literature updated = literatureService.update(periodical);
        return "redirect:/ui/literature/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        literatureService.delete(id);
        return "redirect:/ui/literature/show";

    }
}
