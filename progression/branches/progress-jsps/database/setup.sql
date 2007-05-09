CREATE DATABASE progress;

GRANT ALL ON progress.* TO buildadmin@localhost IDENTIFIED BY 'build123';
GRANT INSERT, DELETE, UPDATE, SELECT ON progress.* TO buildapp@localhost IDENTIFIED BY 'build123';

USE progress;

CREATE TABLE Person (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(30) NOT NULL, 
                      email_address VARCHAR(50) NOT NULL,
                      location VARCHAR(30),
                      display_order INTEGER NOT NULL
) engine=InnoDB;

CREATE TABLE Client (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(30) NOT NULL
) engine=InnoDB;

CREATE TABLE Environment (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
                          name VARCHAR(30) NOT NULL,
                          description TEXT,
                          display_order INTEGER NOT NULL
) engine=InnoDB;

CREATE TABLE Target (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
                        environment_id INTEGER NOT NULL REFERENCES Environment(id),
                        name VARCHAR(50) NOT NULL,
                        server_name VARCHAR(50) NOT NULL,
                        container VARCHAR(50),
                        description TEXT
) engine=InnoDB;

CREATE TABLE Project (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(30) NOT NULL, 
                        client_id INTEGER NOT NULL REFERENCES Client(id),
                        description TEXT
) engine=InnoDB;

-- a deployable component
CREATE TABLE Artifact (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(30) NOT NULL, 
                        description TEXT
) engine=InnoDB;

-- artifacts used by a given project
CREATE TABLE Project_Artifact (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
                        project_id INTEGER NOT NULL REFERENCES Project(id),
                        artifact_id INTEGER NOT NULL REFERENCES Artifact(id)
) engine=InnoDB;

-- where artifacts may be deployed in an environment
CREATE TABLE Deploy_Target (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        target_id INTEGER NOT NULL REFERENCES Target(id),
                        artifact_id INTEGER NOT NULL REFERENCES Artifact(id),
                        deploy_id INTEGER NOT NULL REFERENCES Deploy(id),
                        status ENUM('inactive','running') NOT NULL,
                        comments TEXT
) engine=InnoDB;

CREATE TABLE Link (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
                   topic VARCHAR(50), 
                   URL VARCHAR(100) NOT NULL, 
                   name VARCHAR(100) NOT NULL,
                   display_order INTEGER NOT NULL
) engine=InnoDB;

-- artifacts used by a given project
CREATE TABLE Environment_Link (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
                        environment_id INTEGER NOT NULL REFERENCES Environment(id),
                        link_id INTEGER NOT NULL REFERENCES Link(id)
) engine=InnoDB;


CREATE TABLE Build (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      version VARCHAR(50) NOT NULL, 
                      project_id INTEGER NOT NULL REFERENCES Project(id), 
                      artifact_id INTEGER NOT NULL REFERENCES Artifact(id),
                      contact_id INTEGER NOT NULL REFERENCES Person(id), 
                      status ENUM('scheduled', 'pending', 'abandoned', 'complete') NOT NULL,
                      created_timestamp DATETIME NOT NULL,
                      scheduled_timestamp DATETIME NULL,
                      performed_timestamp DATETIME NULL,
                      comments TEXT
) engine=InnoDB;

CREATE TABLE Deploy (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      build_id INTEGER NOT NULL REFERENCES Build(id), 
                      environment_id INTEGER NOT NULL REFERENCES Environment(id), 
                      contact_id INTEGER NOT NULL REFERENCES Person(id), 
                      status ENUM('scheduled', 'pending', 'abandoned', 'complete') NOT NULL,
                      created_timestamp DATETIME NOT NULL,
                      scheduled_timestamp DATETIME NULL,
                      performed_timestamp DATETIME NULL,
                      comments TEXT 
) engine=InnoDB;
