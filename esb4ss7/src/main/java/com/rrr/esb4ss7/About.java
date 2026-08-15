package com.rrr.esb4ss7;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class About {

    @Value("${my.name:Venkat}")
    private String name;

    public String getName() {
        return name;
    }


}
