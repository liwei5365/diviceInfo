package com.nokia.handler;/*
package com.nokia.handler;

import com.nokia.test.UsedEntity;
import com.nokia.test.UsedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("used")
public class UsedHandler {
    @Autowired
    private UsedStatus usedStatus;

    private Map<String, Object> returnMap = new HashMap<>();

    */
/*@GetMapping("data")
    @ResponseBody
    public Map<String, Object> usedTest(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //returnMap.clear();
                returnMap = usedStatus.execute();
            }
        },0,1000);
        return returnMap;
    }*//*


    @GetMapping("test")
    public String usedTestHtml(Model model){
       */
/* Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //returnMap.clear();
                returnMap = usedStatus.execute();
            }
        },0,1000);*//*

       // UsedEntity usedEntity = usedStatus.execute();
       // model.addAttribute("returnUsed",usedEntity);
        return "usedHtml";
    }

}
*/
