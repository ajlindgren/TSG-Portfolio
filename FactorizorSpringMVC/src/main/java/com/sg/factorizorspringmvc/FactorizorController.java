/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.factorizorspringmvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */

@RestController
public class FactorizorController {
    
    public FactorizorController() {
    
    }
    
    @RequestMapping("factorNumber/{num}")
    public Map<String, Object> factorNumber(@PathVariable(value="num") Integer num) {
        
        List<Integer> factorList = new ArrayList<>();
        int factorSum = 0;
        boolean isPrime = false;
        boolean isPerfect = false;
        int numberToFactor = num;
        
        for (int i = 1; i < numberToFactor; i++) {
            if (numberToFactor % i == 0) {
                factorList.add(i);
                factorSum += i;
            }
        }
        
        if (factorSum == numberToFactor){
            isPerfect = true;
        }
        
        if (factorSum == 1){
            isPrime = true;
        }
        
        Map<String, Object> model = new HashMap<>();
        model.put("numberToFactor", numberToFactor);
        model.put("factors", factorList);
        model.put("isPrime", isPrime);
        model.put("isPerfect", isPerfect);
        
        return model;
    }
    
    @RequestMapping(value = "factorNumber/{num}", method = RequestMethod.PUT)
    //@RequestMapping("factorNumber/{num}")
    public Map<String, Object> saveFactor(@PathVariable(value="num") Integer num) {
        
        Map<String, Object> model = factorNumber(num);
        
        return model;
    }
}
