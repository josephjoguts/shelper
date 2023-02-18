package ru.aaa.shelper;

import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class SpringBootVersionInfoContributor implements InfoContributor {
    private static final String VERSION = "1";
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("version", VERSION);
    }
}
