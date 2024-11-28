# Sprint: 2024-12-02 Sprint 2

Product website:  
[https://shycat.fyi/](https://shycat.fyi/)

For this project, we used Spring Initializr to generate the structure for the Java Spring-based back end.  We used ChatGPT to generate the sample SQL script used for repository testing and integration REST controller testing.

## Sprint Planning 2024-11-18

#### Present
* Shelby Grasso (Product Owner and Developer)
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka (Scrum Master and Developer)

Only Developers participated in this activity.

**_Goal:_** Set up a minimal back end to communicate with front end and display the info.  
**_Forecast_**: 9 story points  
**_Rationale_**: We're forecasting fewer story points than the last sprint in part because of the upcoming holiday, which means our sprint has fewer effective work days.  Also, we barely finished the items in the previous sprint (we didn't allocate enough time for debugging).  We're pulling in 10 points worth of items, but are anticipating that we may not finish the last item due to the holidays.

Pulled in backlog items:
1. [SHY-29 (Clarification on mob programming/priorities)](https://evilore.atlassian.net/browse/SHY-29)
2. [SHY-2 (User-created events - back end)](https://evilore.atlassian.net/browse/SHY-2)
3. [SHY-44 (User-created events - front end)](https://evilore.atlassian.net/browse/SHY-44)
4. [SHY-13 (Detailed event view)](https://evilore.atlassian.net/browse/SHY-13)

### Screenshots
* [Initial PBI pulled into sprint](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-18%20172853.png)
* [Updating story point estimates](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-18%20174249.png)
* [Decomposing large PBI into two smaller items](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-18%20174411.png)
* [Finished splitting items and updating story points](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-18%20175728.png)

Note that we forgot to update the Sprint name before starting the sprint!  This is fixed later - see Kanban Board screenshots below.


## Sprint Planning Redux 2024-11-24

#### Present
* Shelby Grasso (Product Owner and Developer)
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka (Scrum Master and Developer)

Only Developers participated in this activity.

Following feedback from instructor to "recast your project to be more in line with a computer science course" and "to see algorithms and data structures that you can build test-first", we met again to refocus/update our PBIs and plan for the sprint.  We wanted to prioritize algorithms and more readily testable code instead of our previous plan, which focused on building additional front end components (event submission form and detailed event view).

We removed SHY-2, SHY-13, and SHY-44, and replaced them with:
* [SHY-4 (Automatically find new events from Harvard events calendar)](https://evilore.atlassian.net/browse/SHY-4)
* [SHY-62 (Consistent information for each event)](https://evilore.atlassian.net/browse/SHY-62)

Note that SHY-4 was originally several items down in the Product Backlog, so wasn't initially ready to be pulled into a sprint - in part because it was too large and non-specific.  We decomposed it into SHY-4 and SHY-62, and added acceptance criteria and programmer tasks.

### Screenshots
* [Moving front end stories out of sprint](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-24%20115437.png)
* [Filling in new SHY-62 item](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-24%20121623.png)
* [Kanban board with child issues displayed](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-24%20121739.png)
* [Updated backlog, sprint now at 11 story points](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-24%20123556.png)


## Kanban Board Screenshots
* [Sprint started](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-18%20175906.png)
* [Retrospective PBI completed](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-18%20175912.png)
* [Sprint name updated with correct end date](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-18%20180216.png)
* [Board following restructure of sprint, with child issues displayed](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-24%20121739.png)
* [Board without child issues displayed](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-24%20143840.png)


## Burndown Chart
Reworked graph to have smooth line from 10 story points (size of sprint) to 0.

### Screenshots
* [Initial chart at start of sprint](images/screenshots/Sprint_2024-12-02/Burndown%20screenshot%202024-11-20%20191045.png)
* [Updated to fill through 2024-11-20](images/screenshots/Sprint_2024-12-02/Burndown%20screenshot%202024-11-20%20191144.png)
* [Reworked for changes to sprint total (now 11 story points)](images/screenshots/Sprint_2024-12-02/Burndown%20screenshot%202024-11-24%20123922.png)


## Daily Scrum

### Scrum 2024-11-24

#### Present
* Shelby Grasso
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka

#### Screenshots
* [Group meeting](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-24%20113545.png)
* [Framework](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-24%20113610.png)
* [Answering questions](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-24%20114738.png)

#### What did you do in the last 24 hours that helped you meet the Sprint Goal?
* **Shelby:** Looked into web scraping to become more familiar with it, especially in Java.  Also worked with practice React app.  Confirmed with Alexa (stakeholder) that she's available to meet for Sprint Review on Monday morning 12/2/2024.
* **Hamza:** Investigated Java and Spring in particular.  Also went over how to set up an AWS EC2 instance for use with the back end.
* **Yu:** Worked on refining user stories in Product Backlog Items, based on feedback from professor.
* **Christina:** Prepped for next mob programming session.  Reviewed Spring Boot testing/annotations and investigated Harvard events calendar website data structure.  Downloaded sample web pages and created some test data that could be used in unit tests.
* **Tyler:** Focused on realigning team to meet the new requirement from professor, brainstormed options on how best to incorporate going forward.

#### What will you do in the next 24 hours to meet the Sprint Goal?
* **Shelby:** Participate in mob programming when available, continue to investigate Java/React libraries.
* **Hamza:** Help write back end, prepare for AWS EC2 instance creation to run the back end.
* **Yu:** Participate in mob programming, continue refining user stories based on team discussion/new requirements.
* **Christina:** Participate in mob programming, break down sample data further to make it usable in narrowly-focused unit tests.
* **Tyler:** Participate in mob programming, help to update current and future sprint goals and issues.

#### Do you see any impediment that prevents the Developers from meeting the Sprint Goal? What are the impediments?
* **Shelby:** None, other than availability (travel)
* **Hamza:** Availability, on call this week so may have to suddenly drop out of sessions.
* **Yu:** Had trouble in refining user stories, particularly the pair around creating new events that were separate into front end vs back end - 
* **Christina:** Scheduling/availability (holidays and other responsibilities)
* **Tyler:** Scheduling, other responsibilities

#### What is your impediment removal plan?
To cope with the scheduling/availability problems, we plan to adopt agile flexibility in scheduling meetings and setting up programming sessions, switching to pair programming at times to reduce the number of people whose schedules need to align.  
To address the issue with the current backlog items and sprint plan not meeting the instructor requirements based on feedback, today we will refocus the sprint and update backlog items.

## Group Programming Sessions

### Mob Programming 2024-11-20
Following the feedback from the instructor, we realized that we needed to pivot our sprint to focus more on "algorithms and data-structures that you can build test-first", instead of the additional front end components that made up the bulk of our planned tasks.  For this reason, we decided to switch to back end development with a focus on web scraping as that offers more scope for this type of coding.

The mob programming session was therefore spent in developing an initial back end with persistence for the "Event" data that will be filled in from the scraped webpages and provided to the front end list display.

#### Present
* Shelby Grasso
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka

#### Screenshots
* [Creating Event repository tests](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-20%20193929.png)
* [Filling in expected values](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-20%20195228.png)
* [Adding another test](images/screenshots/Sprint_2024-12-02/Screenshot%202024-11-20%20203449.png)
