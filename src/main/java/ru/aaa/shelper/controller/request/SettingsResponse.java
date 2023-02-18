package ru.aaa.shelper.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class SettingsResponse {
    Map<String, String> settings;
}
