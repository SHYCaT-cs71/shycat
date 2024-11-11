# shycat

CSCI E-71 Final project

**Team Name**: SHYCaT  

**Roster:**
- Shelby Grasso (Product Owner)
- Hanza Hashmi
- Yu Lai
- Christina Molodowitch
- and
- Tyler Malka (Scrum Master)

Link to Slack channel:  
https://agilesoftwarecourse.slack.com/archives/C07UJ3T3ZCY

JIRA board:  
https://evilore.atlassian.net/jira/software/projects/SHY/boards/1/backlog

Product Backlog Ordering Rationale:  
https://docs.google.com/document/d/1U0csWbap6kD9ooz6uzyg18jOZR-xALIhj7Bq7jelGtM/edit?usp=sharing

Project Vision Document:  
https://github.com/SHYCaT-cs71/shycat/blob/main/Shycat%20Project%20Vision.pdf

Detailed User Persona:  
https://www.figma.com/design/tJYam6D8osfEJDzZgDfX2o/SHYCaT_persona?node-id=0-1&node-type=canvas&t=zd44JGXoeKK9zDvC-0

***Near Product Vision:*** Centralized events hub for Harvard/MIT
***Far Product Vision:*** Trusted event network for academic communities

### Stakeholders:
* Director of Student/Community Programming
* Student at Harvard
* Event Host

### Definition of Ready:
* Refined: Does everyone agree on what the item entails and how it will be implemented?
* Actionable: Can the developers begin working on the item immediately?  Are there any external dependencies that need to be resolved?
* Value: Is it clear what value the item provides to the project and the end user?
* Estimated: Does the item have an agreed-upon story point value?  Is the item sized appropriately to be completed within a single sprint?
* Acceptance criteria: Are there a clearly-defined set of testable criteria that the developers can use to measure whether the item is implemented fully and correctly?

# Sprint: 2024-11-17 Sprint 1

## Sprint Planning 2024-11-4

Present:
* Shelby Grasso
* Hamza Hasmi
* Christina Molodowitch
* Tyler Malka

Only developers (listed above) participated in the Sprint Planning.

**Goal:** To have a minimally functioning website that's publicly accessible.  
**Forecast**: 11 story points  
**Rationale**: The requirement to use pair programming and mob programming raises concerns about shared availability, so we’re being conservative in our estimate.  We’re also using technologies that not everyone is familiar with, so we expect there to be a learning curve as less experienced members get up to speed.  The first backlog items have to be completed in order because they are blockers for each other, so we can’t work on them in parallel.

Pulled in backlog items:
1. SHY-14 (Optimized meetings)
2. SHY-3 (Functioning website)
3. SHY-11 (Main events listing view)

[See Kanban Board in JIRA.](https://evilore.atlassian.net/jira/software/projects/SHY/boards/1)

## Burndown Chart
Created a simple burndown chart in Google Sheets since the JIRA chart didn't meet assignment requirements:   
https://docs.google.com/spreadsheets/d/1nUfLnhID7GA6UObXBBVUMOgjMd3ZHZWAGSbBza8dfU4/edit?usp=sharing

* [Image from daily scrum 2024-11-10 before marking meeting issue done.](images/screenshots/Sprint_2024-11-17/Burndown%20screenshot%202024-11-10%20112922.png)
* [Image after mob programming 2024-11-10, after both SHY-14 and SHY-3 marked done.](images/screenshots/Sprint_2024-11-17/Burndown%20screenshot%202024-11-10%20123539.png)

## Daily Scrums

### Daily Scrum 2024-11-10
11:07 AM - 11:22 AM

#### Present
* Shelby Grasso (Product Owner)
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka (Scrum Master)

#### What did you do in the last 24 hours that helped you meet the Sprint Goal?
* **Shelby:** Did research into Jest and React, went through tutorials
* **Hamza:** Fixed issue with webapp not running properly on Amplify, set up directory structure for React app and installed libraries.
* **Yu:** Practiced with Jest and React components, tried to build test project to try out Jest.  Ran into issues with bugs in unit tests
* **Christina:** Read through initial React documentation and basics on how to use Jest with React.  Also made basic Sprint burndown chart in Google Sheets.
* **Tyler:** Sorted out domain redirection to web application, got it working with AWS.  Looked over daisy.io library recommended by Yu.  Read up on Scrum Master responsibilities for daily scrum session.

#### What will you do in the next 24 hours to meet the Sprint Goal?
* **Shelby:** Depends on what we decide to do next - will rework product vision and revisit stakeholders from part 1 feedback
* **Hamza:** Help write components for web app and unit tests.
* **Yu:** Will work on debugging unit tests, contribute to front end components and unit tests.
* **Christina:** Continue reading up on React and Jest testing, update burndown chart.  (Will also install VSCode with LiveShare.)
* **Tyler:** Facilitate progress, assisting where needed, work on pair/mob programming to build components/tests.

#### Do you see any impediment that prevents the Developers from meeting the Sprint Goal? What are the impediments? What is your impediment removal plan?
* **Shelby:** Scheduling!
* **Hamza:** No impediments
* **Yu:** Ran into issues with bugs in unit tests - will work on debugging this, watch tutorials to learn more.  Also, how to do mob programming (technically?)  Used PyCharm in class, but can’t use this for our project.  Use VSCode?  (VSCode LiveShare)
* **Christina:** No knowledge/experience with React, will continue to read up on it and practice.  Also needs to install VSCode.
* **Tyler:** No real impediments - organizing and ensuring everyone feels productive

Discussed considering meeting task complete, okayed by Shelby as Product Owner so it was moved to Done.  
[Screenshot of product owner okay](images/screenshots/Sprint_2024-11-17/Issue%20complete%20Screenshot%202024-11-10%20112828.png)

