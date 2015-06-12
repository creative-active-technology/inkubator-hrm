/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inkubator.common.util.JsonConverter;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Zulfi A
 * @author Deni Husni FR
 */
@Controller
@RequestMapping("user_rest")
public class EmpDataController {

    protected transient Logger LOGGER = Logger.getLogger(getClass());
    @Autowired
    private JsonConverter jsonConverter;
//    @Autowired
//    EmpDataService empDataService;
//    @Autowired
//    BioDataService bioDataService;

    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    public @ResponseBody
    String getByUserName(@PathVariable String userName) {

        LOGGER.info("sssssssssssssssssssssssssss" + userName);
        String result = null;
        try {
//            EmpData empData = empDataService.getEntityByNik("10000004");

            EmpDataRest userDataRest = new EmpDataRest();
            userDataRest.setName("DENi");
            userDataRest.setNik("121212");
////            userDataRest.setNik(empData.getNik());
//                System.out.println("Jnson " + result);
//
//            if (userDataRest != null) {
            result = jsonConverter.getJson(userDataRest);
//            }
        } catch (Exception ex) {
           LOGGER.error(result, ex);
        }
        return result;
    }

}
