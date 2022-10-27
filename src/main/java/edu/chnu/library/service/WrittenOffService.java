package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.ExemplarInUseException;
import edu.chnu.library.exception.LiteratureNotLendableException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Exemplar;
import edu.chnu.library.model.Literature;
import edu.chnu.library.model.WrittenOff;
import edu.chnu.library.repository.mongo.WrittenOffMongoRepository;
import edu.chnu.library.repository.sql.WrittenOffSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:24
 * @class WrittenOffService
 */
@Service
public class WrittenOffService {
    WrittenOffSqlRepository writtenOffSqlRepository;
    WrittenOffMongoRepository writtenOffMongoRepository;
    ExemplarService exemplarService;

    @Autowired
    public WrittenOffService(WrittenOffSqlRepository writtenOffSqlRepository, WrittenOffMongoRepository writtenOffMongoRepository,
                             ExemplarService exemplarService) {
        this.writtenOffSqlRepository = writtenOffSqlRepository;
        this.writtenOffMongoRepository = writtenOffMongoRepository;
        this.exemplarService = exemplarService;
    }

    //@PostConstruct
    void init() {
    }

    public WrittenOff create(WrittenOff writtenOff) {
        LocalDateTime time = LocalDateTime.now();
        writtenOff.setCreatedAt(time);
        writtenOff.setUpdatedAt(time);
        writtenOffMongoRepository.save(writtenOff);
        return writtenOffSqlRepository.save(writtenOff);
    }

    public WrittenOff get(String id) {
        return writtenOffSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("WrittenOff with id:" + id + " not found "));
    }

    public WrittenOff tryGet(String id) {
        return writtenOffSqlRepository.findById(id).orElse(null);
    }

    public WrittenOff update(WrittenOff writtenOff) {
        WrittenOff oldOne = get(writtenOff.getId());
        writtenOff.setCreatedAt(oldOne.getCreatedAt());
        writtenOff.setUpdatedAt(LocalDateTime.now());
        writtenOffMongoRepository.save(writtenOff);
        return writtenOffSqlRepository.save(writtenOff);
    }

    public void delete(String id) {
        writtenOffMongoRepository.deleteById(id);
        writtenOffSqlRepository.deleteById(id);
    }

    public List<WrittenOff> getAll() {
        return writtenOffSqlRepository.findAll();
    }

    public Page<WrittenOff> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return writtenOffSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    public WrittenOff writeOffExemplar(String exemplarId) {
        Exemplar exemplar = exemplarService.get(exemplarId);
        Literature literature = exemplar.getLiterature();
        if (!literature.isLendable()){
            throw new LiteratureNotLendableException();
        }
        if (exemplar.isLend()) {
            throw new ExemplarInUseException();
        }
        WrittenOff writtenOff = tryGet(literature.getId());
        if (writtenOff == null) {
            writtenOff = WrittenOff.builder()
                    .id(literature.getId())
                    .isn(literature.getIsn())
                    .name(literature.getName())
                    .publishingYear(literature.getPublishingYear())
                    .quantity(0)
                    .publisher(literature.getPublisher())
                    .numberOfPages(literature.getNumberOfPages())
                    .description(literature.getDescription())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
        }
        writtenOff.setQuantity(writtenOff.getQuantity() + 1);
        writtenOff.setUpdatedAt(LocalDateTime.now());
        writtenOffMongoRepository.save(writtenOff);
        writtenOffSqlRepository.save(writtenOff);
        exemplarService.delete(exemplarId);
        return writtenOff;
    }

    public List<WrittenOff> getAllByNameContaining(String name, Sort sort) {
        try {
            return writtenOffSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<WrittenOff> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return writtenOffSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<WrittenOff> getAllPaginated(PageRequest pageRequest) {
        return writtenOffSqlRepository.findAll(pageRequest);
    }
}
