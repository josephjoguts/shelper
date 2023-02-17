package ru.aaa.shelper.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class NotificationResponse {
    List<Project> projectList;
}
