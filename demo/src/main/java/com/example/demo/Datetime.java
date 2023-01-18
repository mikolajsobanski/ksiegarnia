package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Datetime {
     private String sformatowana;

     public Datetime(){
          LocalDateTime data = LocalDateTime.now();
          DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
          this.sformatowana = data.format(format);
     }

     public String getSformatowana() {
          return sformatowana;
     }

     public void setSformatowana(String sformatowana) {
          this.sformatowana = sformatowana;
     }

     @Override
     public String toString() {
          return "Datetime{" +
                  "sformatowana='" + sformatowana + '\'' +
                  '}';
     }
}
