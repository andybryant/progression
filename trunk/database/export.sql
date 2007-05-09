USE progress;

SELECT * INTO OUTFILE "/tmp/progress/people.txt" FROM Person;
SELECT * INTO OUTFILE "/tmp/progress/clients.txt" FROM Client;
SELECT * INTO OUTFILE "/tmp/progress/environments.txt" FROM Environment;
SELECT * INTO OUTFILE "/tmp/progress/targets.txt" FROM Target;
SELECT * INTO OUTFILE "/tmp/progress/projects.txt" FROM Project;
SELECT * INTO OUTFILE "/tmp/progress/artifacts.txt" FROM Artifact;
SELECT * INTO OUTFILE "/tmp/progress/projArtifacts.txt" FROM Project_Artifact;
SELECT * INTO OUTFILE "/tmp/progress/deployTargets.txt" FROM Deploy_Target;
SELECT * INTO OUTFILE "/tmp/progress/links.txt" FROM Link;
SELECT * INTO OUTFILE "/tmp/progress/environmentLinks.txt" FROM Environment_Link;
SELECT * INTO OUTFILE "/tmp/progress/builds.txt" FROM Build;
SELECT * INTO OUTFILE "/tmp/progress/deploys.txt" FROM Deploy;
