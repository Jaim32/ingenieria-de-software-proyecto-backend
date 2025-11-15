package org.example.lifesyncbackend.Util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hidro")
public class HidroCalc {
    private double factorMl = 35.0;
    public int metaMl(double pesoKg) {
        return (int) Math.round(pesoKg * factorMl);
    }
    public void setFactorMl(double factorMl) { this.factorMl = factorMl; }
}
