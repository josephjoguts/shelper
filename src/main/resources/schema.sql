CREATE TABLE Users (
                       id IDENTITY PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       send_email BOOLEAN,
                       minimal_grant_amount BIGINT,
                       maximum_grant_amount BIGINT,
                       event_types VARCHAR(100) ARRAY,
                       regions VARCHAR(100) ARRAY,
                       scales VARCHAR(100) ARRAY
);

CREATE TABLE Projects (
                          id IDENTITY PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          finished BOOLEAN,
                          user_id BIGINT  NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE Grants (
                        id IDENTITY PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        type VARCHAR(255),
                        amount BIGINT,
                        region VARCHAR(255),
                        scale VARCHAR(255)
);

CREATE TABLE Project_Grants (
                                project_id BIGINT  NOT NULL,
                                grant_id BIGINT  NOT NULL,
                                PRIMARY KEY (project_id, grant_id),
                                FOREIGN KEY (project_id) REFERENCES Projects(id),
                                FOREIGN KEY (grant_id) REFERENCES Grants(id)
);

CREATE TABLE Users_Projects (
                                user_id BIGINT  NOT NULL,
                                project_id BIGINT  NOT NULL,
                                PRIMARY KEY (user_id, project_id),
                                FOREIGN KEY (user_id) REFERENCES Users(id),
                                FOREIGN KEY (project_id) REFERENCES Projects(id)
);

CREATE TABLE Event (
                       id IDENTITY PRIMARY KEY,
                       user_id BIGINT  NOT NULL,
                       grant_id BIGINT  NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES Users(id),
                       FOREIGN KEY (grant_id) REFERENCES Grants(id)
);

select Projects.name from Projects inner join Users U on U.id = Projects.user_id where Projects.finished = FALSE;
