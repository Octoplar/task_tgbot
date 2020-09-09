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
        String minskStr="CityInfo{name='минск', info='Не забудьте посетить ЦУМ. Возможна бесплатная экскурсия по Окрестино'}";
        String mskStr="CityInfo{name='москва', info='Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)))'}";

        Assert.assertEquals(minskStr, service.findByName("минск") + "");
        Assert.assertEquals(mskStr, service.findByName("москва") + "");

        Assert.assertEquals(minskStr, service.findByName("Минск") + "");
        Assert.assertEquals(mskStr, service.findByName("МОСКВА") + "");
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