package com.panandafog.mt_server.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dummies")
public class DummiesController {

    @Autowired
    private DummiesService service;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Dummy> getAllDummies() {
        List<Dummy> tmp = null;
        tmp = service.getAll();
        return tmp;
    }

    @PostMapping(value = "/save")
    public String addOrUpdateDummy(@RequestBody Dummy dummy) {
//        try {
            service.save(dummy);
//        } catch (DataIntegrityViolationException ex) {
//            return "Already exists.";
//        }
        return "Success!";
    }
}
