package com.raj.ehttp;

import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
public class EHttpConfiguration extends Configuration {
   private   String pathForJsons;
}
