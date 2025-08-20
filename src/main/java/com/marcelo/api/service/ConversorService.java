package com.marcelo.api.service;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ConversorService {

    public Date converteDataUs(Date data) {

        System.out.println("----------------------4");
        String dataUs = new SimpleDateFormat("yyyy-MM-dd").format(data);
        Date dataConv = new Date(dataUs);
        return dataConv;
    }
}
