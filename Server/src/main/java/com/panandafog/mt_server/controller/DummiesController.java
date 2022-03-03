package com.panandafog.mt_server.controller;

import com.panandafog.mt_server.entity.Dummy;
import com.panandafog.mt_server.service.DummiesService;
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
//        try {
//            tmp = service.getAll();
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
        tmp = service.getAll();
        return tmp;
    }

    @PostMapping(value = "/save")
    public String addOrUpdateGood(@RequestBody Dummy dummy) {
        try {
            service.save(dummy);
        } catch (DataIntegrityViolationException ex) {
            return "Already exists.";
        }
        return "Success!";
    }
}
