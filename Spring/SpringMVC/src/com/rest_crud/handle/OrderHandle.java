package com.rest_crud.handle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName OrderHandle
 * @Description Details
 * @Author Josen
 * @Create 2020/7/29 13:12
 */
@Controller
public class OrderHandle {

    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public String handlePost(){
        System.out.println("REST Post...");
        return "success";
    }

    @RequestMapping(value = "/order/{id}",method = RequestMethod.GET)
    public String handleGet(@PathVariable("id") String id){
        System.out.println("REST Get - id="+id);
        return "success";
    }

    @RequestMapping(value = "/order/{id}",method = RequestMethod.DELETE)
    public String handleDelete(@PathVariable("id") String id){
        System.out.println("REST Delete - id="+id);
        return "success";
    }

    @RequestMapping(value="/order/{id}",method = RequestMethod.PUT)
    public String handlePut(@PathVariable("id") String id){
        System.out.println("REST Put - id="+id);
        return "success";
    }
}
