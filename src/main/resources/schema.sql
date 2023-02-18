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

SELECT g.id, g.name, g.type, g.amount, g.region, g.scale
                FROM Grants g
                INNER JOIN Project_Grants pg ON g.id = pg.grant_id
                INNER JOIN Projects p ON p.id = pg.project_id
                INNER JOIN Users_Projects up ON p.id = up.project_id
                INNER JOIN Users u ON up.user_id = u.id
                WHERE u.name = ''
                  AND (u.event_types IS NULL OR  ARRAY_CONTAINS(u.event_types, g.type))
                  AND (u.scales IS NULL OR  ARRAY_CONTAINS(u.scales, g.scale))
                  AND (u.regions IS NULL OR  ARRAY_CONTAINS(u.regions, g.region))
                  AND g.amount BETWEEN u.minimal_grant_amount AND u.maximum_grant_amount
                  AND g.id NOT IN (SELECT DISTINCT g.id FROM Grants g
                                                                 INNER JOIN Project_Grants pg ON g.id = pg.grant_id
                                                                 INNER JOIN Projects p ON p.id = pg.project_id
                                                                 INNER JOIN Users_Projects up ON p.id = up.project_id
                                                                 INNER JOIN Users u ON up.user_id = u.id
                                   WHERE u.name = '');
