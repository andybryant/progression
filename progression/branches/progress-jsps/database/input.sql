USE progress;

LOAD DATA LOCAL INFILE "people.txt" INTO TABLE Person;
LOAD DATA LOCAL INFILE "clients.txt" INTO TABLE Client;
LOAD DATA LOCAL INFILE "environments.txt" INTO TABLE Environment;
LOAD DATA LOCAL INFILE "targets.txt" INTO TABLE Target;
LOAD DATA LOCAL INFILE "projects.txt" INTO TABLE Project;
LOAD DATA LOCAL INFILE "artifacts.txt" INTO TABLE Artifact;
LOAD DATA LOCAL INFILE "projArtifacts.txt" INTO TABLE Project_Artifact;
LOAD DATA LOCAL INFILE "deployTargets.txt" INTO TABLE Deploy_Target;
LOAD DATA LOCAL INFILE "links.txt" INTO TABLE Link;
LOAD DATA LOCAL INFILE "environmentLinks.txt" INTO TABLE Environment_Link;
LOAD DATA LOCAL INFILE "builds.txt" INTO TABLE Build;
LOAD DATA LOCAL INFILE "deploys.txt" INTO TABLE Deploy;

