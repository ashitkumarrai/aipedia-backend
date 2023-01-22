package com.aipedia.aipediabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aipedia.aipediabackend.entity.ToolCard;
import com.aipedia.aipediabackend.exceptionhandler.RecordNotFoundException;
import com.aipedia.aipediabackend.repository.ToolCardRepository;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;




@RestController
@RequestMapping("/toolcard")
public class ToolCardController {

    @Autowired
    ToolCardRepository tr;


    @GetMapping(value = "/")
    public List<ToolCard> getToolCard(){

        return tr.findAll();
    }

    @GetMapping(value = "/{id}")
    public ToolCard getToolCardById(@PathVariable("id") String id) throws RecordNotFoundException {

        return tr.findById(id).orElseThrow(() -> new RecordNotFoundException("toolcard is not found in db"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateToolcard(@PathVariable("id") String id, @Valid @RequestBody ToolCard t)
            throws RecordNotFoundException {

        ToolCard op = tr.findById(id).orElseThrow(() -> new RecordNotFoundException("toolcard is not found in db"));

        Map<String, String> ex = new HashMap<>();

        // blank check and null checks

        if (op.getId().equals(id)) {
            if (t.getTitle() != null && !("".equals(t.getTitle()))) {
                op.setTitle(t.getTitle());

            }
            if (t.getDescription() != null && !("".equals(t.getDescription()))) {
                op.setDescription(t.getDescription());

            }

            if (t.getHashTag() != null && !("".equals(t.getHashTag()))) {
                op.setHashTag(t.getHashTag());

            }

            if (t.getTag() != null && !("".equals(t.getTag()))) {
                op.setTag(t.getTag());
            }

            
            if (t.getImg() != null && !("".equals(t.getImg()))) {
                op.setImg(t.getImg());
            }
        }

        else {

            ex.put("Response", "PathVariable id doesn't matched with requestbody id.");
            return new ResponseEntity<>(ex, HttpStatus.NOT_ACCEPTABLE);
        }

        ToolCard tempD = tr.save(op);
        return new ResponseEntity<>(tempD, HttpStatus.ACCEPTED);

    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createToolCard(@Valid @RequestBody ToolCard d) {

        ToolCard tcObj;

        tcObj = tr.save(d);

        EntityModel<ToolCard> entityModel = EntityModel.of(tr.findById(tcObj.getId()).get());
        Link link = WebMvcLinkBuilder.linkTo(methodOn(ToolCardController.class).getToolCardById(tcObj.getId()))
                .withRel("new-toolcard");

        entityModel.add(link);
        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, String>> deleteToolCardById(@PathVariable("id") String id) throws Exception {

        Map<String, String> map = new HashMap<>();
        try {
            tr.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecordNotFoundException(e.getLocalizedMessage());
        }
        map.put("Response", "deleted from database");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

    }
}