INSERT INTO Users (name, send_email, minimal_grant_amount, maximum_grant_amount, event_types, regions, scales)
VALUES ('test-1', true, 1000, 50000, ARRAY['Type A', 'Type B'], ARRAY['Region 1', 'Region 2'],ARRAY['Small', 'Medium']),
       ('test-2', false, 500, 10000, ARRAY['Type A', 'Type B'], ARRAY['Region 1'], ARRAY['Small']);

INSERT INTO Projects (name, finished, user_id)
VALUES ('Project 1', true, 1),
       ('Project 2', false, 1),
       ('Project 3', true, 2),
       ('Project 4', false, 2),
       ('Project 5', true, 1);

INSERT INTO Grants (name, type, amount, region, scale)
VALUES ('Grant 1', 'Type A', 10000, 'Region 1', 'Small'),
       ('Grant 2', 'Type B', 20000, 'Region 2', 'Medium'),
       ('Grant 3', 'Type C', 5000, 'Region 3', 'Small'),
       ('Grant 4', 'Type A', 15000, 'Region 1', 'Medium'),
       ('Grant 5', 'Type B', 25000, 'Region 2', 'Large');


INSERT INTO Users_Projects (user_id, project_id)
VALUES (1, 1),
       (1, 2),
       (1, 5),
       (2, 3),
       (2, 4);

INSERT INTO Event (user_id, grant_id)
VALUES (1, 1),
       (1, 2),
       (2, 4),
       (2, 5);