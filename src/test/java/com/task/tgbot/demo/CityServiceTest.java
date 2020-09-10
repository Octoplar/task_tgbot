package com.task.tgbot.demo;

import com.task.tgbot.demo.entity.CityInfo;
import com.task.tgbot.demo.service.CityService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CityServiceTest {

    @Autowired
    private CityService service;

    @Test
    public void defaultInfo() {
        String minskStr= "CityInfo{name='minsk', info='Do not forget to visit TSUM. Free excursion into Okrestino is possible.'}";
        String mskStr="CityInfo{name='moscow', info='Do not forget to visit Red Square. But you may skip visiting of TSUM.'}";

        Assert.assertEquals(minskStr, service.findByName("minsk") + "");
        Assert.assertEquals(mskStr, service.findByName("moscow") + "");

        Assert.assertEquals(minskStr, service.findByName("Minsk") + "");
        Assert.assertEquals(mskStr, service.findByName("MOSCOW") + "");
    }

    @Test
    public void updateInfo() {
        CityInfo tagil = city("Tagil", "do not forget to visit center");
        CityInfo tagilModified = city("Tagil", "ТАГИИИИИИИИИИИЛ!!!!!!!!!!");

        String initial=tagil.toString();
        String modified=tagilModified.toString();

        service.save(tagil);
        Assert.assertEquals(initial, service.findByName("tagil") + "");

        service.save(tagilModified);
        Assert.assertEquals(modified, service.findByName("tagil") + "");

        service.delete("TAGIL");
        Assert.assertNull(service.findByName("tagil"));

        service.delete("TAGIL");
        Assert.assertNull(service.findByName("tagil"));
    }

    private CityInfo city(String name, String info){
        CityInfo result = new CityInfo();
        result.setName(name);
        result.setInfo(info);
        return result;
    }
}