# Sprint: 12-16 Sprint 3

Product website:  
[https://www.shycat.fyi/](https://www.shycat.fyi/)

#### Sprint README Sections
* [Sprint Planning 2024-12-02](#sprint-planning-2024-12-02)
* [Kanban Board](#kanban-board)
* [Burndown Chart](#burndown-chart)
* [Daily Scrum](#daily-scrum)
* [Group Programming](#group-programming)
* [Tests Added](#tests-added)
* [CI/CD](#cicd)
* [Sprint Review Practice](#sprint-review-practice)

ChatGPT/AI was used for debugging and problem solving.  It was also used to fill in the repetitive single-property change unit tests in NightlyEventScrapingJobUnitTest in the nested HasEventChangedTests class, based on two manually-written examples.  (We already had far more than the minimum 10 new tests added, but wanted to ensure that the `hasEventChanged()` method was thoroughly checked.)


## Sprint Planning 2024-12-02

#### Present
* Shelby Grasso (Product Owner and Developer)
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka (Scrum Master and Developer)

Only Developers participated in this activity.

**_Goal:_** Display pulled in events.

**_Forecast_**: 12 story points  

**_Rationale_**: We didn't finish all the issues in the previous sprint, but that was largely due to the loss of time from the holidays.  (Note that we hadn't actually expected to finish, based on our forecast of 9 vs the 11 story points in the sprint.)  For this sprint, per the retrospective and associated backlog item, we're going to focus on pair programming so we can take advantage of our different skill sets and work in parallel.  This means we hope to get more accomplished even compared to our first sprint, which was 11 story points, so we've upped the forecast to 12.

#### Pulled in backlog items:
* [SHY-76 (Retrospective item to switch to pair programming)](https://evilore.atlassian.net/browse/SHY-76)
* [SHY-4 (Automatically find new events from Harvard events calendar)](https://evilore.atlassian.net/browse/SHY-4)
* [SHY-75 (Show default images for events)](https://evilore.atlassian.net/browse/SHY-75)
* [SHY-77 (Show correct time information)](https://evilore.atlassian.net/browse/SHY-77)
* [SHY-13 (View event details)](https://evilore.atlassian.net/browse/SHY-13)

#### Screenshots
* [Expanding PBI added in previous Sprint Review based on stakeholder feedback](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-02%20163523.png)
* [Pulling PBIs into next sprint](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-02%20164532.png)
* [Next sprint started](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-02%20164721.png)


## Kanban Board
Board link:  
https://evilore.atlassian.net/jira/software/projects/SHY/boards/1

Screenshots taken since board disappears once sprint is completed.

### Screenshots
* [Start of sprint, child issues shown](images/screenshots/Sprint_2024-12-16/Kanban%20screenshot%202024-12-02%20164730.png)
* [Start of sprint, child issues shown 2](images/screenshots/Sprint_2024-12-16/Kanban%20screenshot%202024-12-02%20172100.png)
* [Start of sprint, child issues hidden](images/screenshots/Sprint_2024-12-16/Kanban%20screenshot%202024-12-02%20172116.png)
* [Completed SHY-75](images/screenshots/Sprint_2024-12-16/Kanban%20screenshot%202024-12-04%20191322.png)
* [Completed SHY-76](images/screenshots/Sprint_2024-12-16/Kanban%20screenshot%202024-12-04%20191547.png)
* [Completed SHY-77](images/screenshots/Sprint_2024-12-16/Kanban%20screenshot%202024-12-09%20184005.png)
* [Kanban status after SHY-77 completed, child issues hidden](images/screenshots/Sprint_2024-12-16/Kanban%20screenshot%202024-12-09%20220327.png)
* [Kanban after SHY-4 completed, child issues hidden](images/screenshots/Sprint_2024-12-16/Kanban%20screenshot%202024-12-11%20214444.png)
* [Same as above, showing child issues](images/screenshots/Sprint_2024-12-16/Kanban%20screenshot%202024-12-11%20214459.png)
* [Kanban after SHY-13 completed, all issues now done, child issues hidden](images/screenshots/Sprint_2024-12-16/Kanban%20screenshot%202024-12-15%20171026.png)
* [Same as above, showing child issues](images/screenshots/Sprint_2024-12-16/Kanban%20screenshot%202024-12-15%20171003.png)


## Burndown Chart
[Link to chart](https://docs.google.com/spreadsheets/d/1nUfLnhID7GA6UObXBBVUMOgjMd3ZHZWAGSbBza8dfU4/edit?gid=1650701791#gid=1650701791)

### Screenshots
* [Start of sprint](images/screenshots/Sprint_2024-12-16/Burndown%20screenshot%202024-12-02%20172355.png)
* [2024-12-04](images/screenshots/Sprint_2024-12-16/Burndown%20screenshot%202024-12-04%20180546.png)
* [2024-12-08](images/screenshots/Sprint_2024-12-16/Burndown%20screenshot%202024-12-08%20160330.png)
* [2024-12-09](images/screenshots/Sprint_2024-12-16/Burndown%20screenshot%202024-12-09%20164634.png)
* [2024-12-11](images/screenshots/Sprint_2024-12-16/Burndown%20screenshot%202024-12-11%20192700.png)
* [2024-12-15](images/screenshots/Sprint_2024-12-16/Burndown%20screenshot%202024-12-15%20161140.png)
* [Last issue completed](images/screenshots/Sprint_2024-12-16/Burndown%20screenshot%202024-12-15%20171123.png)


## Daily Scrum

### 2024-12-09

#### Present
* Shelby Grasso (Product Owner and Developer)
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka (Scrum Master and Developer)

#### Screenshots
* [Group discussing, with notes](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-09%20164426.png)

#### What did you do in the last 24 hours that helped you meet the Sprint Goal?
* **Shelby:** Participated in mob programming, working on web scraping functionality.
* **Hamza:** Participated in pair programming, working on back end.
* **Yu:** Participated in pair programming with Tyler and Shelby, working on front end.
* **Christina:** Participated in mob programming, working on web scraping.
* **Tyler:** Participated in mob/pair programming, working on back end.

#### What will you do in the next 24 hours to meet the Sprint Goal?
* **Shelby:** Participate in mob/pair programming, focused on front end.
* **Hamza:** Continue with pair programming on back end.
* **Yu:** Not sure?  (See impediment.)
* **Christina:** Participate in mob/pair programming and write DDL SQL script to create MySQL schema/user/table.
* **Tyler:** Participate in mob/pair programming.  Also make sure that group is prioritizing tasks for a successful final presentation, so that web-scraped data will be displayed on the live web site in production.

#### Do you see any impediment that prevents the Developers from meeting the Sprint Goal? What are the impediments?
* **Shelby:** None.
* **Hamza:** None.
* **Yu:** Wasn't available for the previous meeting, so not sure what to tackle next.
* **Christina:** Lack of time (other responsibilities).
* **Tyler:** None.

#### What is your impediment removal plan?
Update Yu on current team progress and get her input on next steps. 


## Group Programming

### Mob/Pair Programming 2024-12-04
We split into two groups to do pair/group programming, one group focused on the back end web scraping SHY-4 issue and the other focused on the front end default image SHY-75 issue.  [Whole group image before splitting.](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-04%20180504.png)

#### Back End
* Hamza Hashmi
* Christina Molodowitch

*Screenshots*
* [Pair programming](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-04%20182300.png)
* [TDD test failed](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-04%20182800.png)
* [Test passes](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-04%20183100.png)

#### Front End
* Shelby Grasso
* Yu Lai
* Tyler Malka

*Screenshots*
* [TDD test fails](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-04%20184732.png)
* [Test passes](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-04%20185320.png)

#### Whole Group Rejoins
Yu had to leave, but the remaining 4 rejoined group - SHY-75 completed
* [Updated front end](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-04%20191042.png)


### Mob Programming 2024-12-08
We focused on the SHY-4 web scraping issue.

#### Present
* Shelby Grasso
* Christina Molodowitch
* Tyler Malka

#### Screenshots
* [Adding unit test](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-08%20161927.png)
* [Test fails (different test)](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-08%20165557.png)
* [Test passes after implementing method](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-08%20165618.png)


### Mob/Pair Programming 2024-12-09
We split into two groups again, to focus separately on web scraping (SHY-4) for the back end and date/time formatting (SHY-77) for the front end.

#### Back End
* Hamza Hashmi
* Christina Molodowitch

*Screenshots*
* [TDD test failed](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-09%20172700.png)
* [Another TDD test failed](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-09%20174300.png)
* [Filling in method to make test above pass](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-09%20174310.png)

#### Front End
* Shelby Grasso
* Yu Lai
* Christina Molodowitch (joined towards the end)
* Tyler Malka

*Screenshots*
* [Mob programming](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-09%20170629.png)
* [Test fails](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-09%20173557.png)
* [Debugging test failure](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-09%20181033.png)
* [Tests pass](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-09%20181430.png)


### Mob/Pair Programming 2024-12-11
We started by looking at the current state and remaining tasks, then split into two groups, to focus separately on back end tasks (finishing SHY-4 web scraping and adding a new endpoint for SHY-13) and front end tasks (starting on building the event details view for SHY-13).

#### Present
* Shelby Grasso (SHY-13)
* Hamza Hashmi (SHY-4 and SHY-13)
* Yu Lai (SHY-13)
* Christina Molodowitch (SHY-4 and SHY-13)

#### Screenshots
* [Reviewing progress all together](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-11%20192241.png)
* [Ditto](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-11%20195129.png)
* [SHY-13 pair programming, building events view](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-11%20202554.png)
* [SHY-4 pair programming, setting up MySQL persistence](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-11%20204229.png)
* [SHY-4 fully deployed and live](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-11%20204239.png)
* [Adding new REST endpoint to support SHY-13](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-11%20204500.png)


### Mob Programming 2024-12-15
We finished up the event details view for SHY-13 and tweaked the returned events list slightly to only include current events (today onward).  We also worked on adding the BDD tests using Cucumber.js (see screenshots in the [Tests Added - BDD Testing section](#bdd-testing) below).

#### Present
* Shelby Grasso
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka

#### Screenshots
* [Finishing event details component and tests](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-15%20163729.png)
* [All front end tests passing](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-15%20163747.png)


## Tests Added
### Back end
* Tests for new single event endpoint (2 in rest.EventControllerIntegrationTest, 1 in rest.EventControllerUnitTest)
* Tests for new EventRepository methods `getEventByOriginalId()` and `getEventsByStartDateTime_DateAfter()` (2 tests)
* All tests in web_scraping package
    * DateTimeDeserializerTest (2 tests)
    * HarvardEventWebScraperImplTest (21 tests)
    * NightlyEventScrapingJobUnitTest (23 tests, including 14 single-property change tests generated by AI)

### Front end
* New test in EventCard.test.js to check use of default image.
* All tests for new EventDetail component in EventDetail.test.js (5 tests).
* New tests for `formatDate()` function in Utilities.test.js (2 tests).

### BDD Testing
Added use of cucumber.js library to front end, and added 2 scenarios for date format depending on whether time is included or not, in features directory under webapp/shycat.
* [display_event_start.feature](webapp/shycat/features/display_event_start.feature)
* [stepdefs.js](webapp/shycat/features/step_definitions/stepdefs.js)

#### Screenshots
* [Adding BDD tests](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-15%20165512.png)
* [Two scenarios, both passing](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-15%20165639.png)


## CI/CD
We continued using the same CI/CD system for front and back end as in the previous sprint.

### Back End
Back end CI/CD leverages GitHub Actions with a workflow in [maven.yml](.github/workflows/maven.yml) to first run all test and build the jar, and then deploy it to an AWS EC2 instance, with a final health check to make sure it's successfully launched.

#### Screenshots
* [All back end tests passing](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-08%20180444.png)
* [Successful deployment with health check](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-08%20180829.png)
* [CI/CD history, blocks deployment if tests fail](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-08%20180756.png)
* [Back end tests passing in CI/CD Github system](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-15%20170902.png)

### Front End
Front end CI/CD uses AWS Amplify with [amplify.yml](amplify.yml) to first run all tests (using yarn), then build and deploy the web application.  It was updated to run the new BDD tests as well as the existing unit tests.

#### Screenshots
* [Front end CI/CD after completing SHY-75](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-04%20190942.png)
* [After failing a few times due to dependency issues, SHY-76 update successfully deployed](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-09%20183826.png)
* [Front end update SHY-76 (date/time formatting) deployed](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-09%20183849.png)
* [Front end with BDD tests added](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-15%20170649.png)


## Sprint Retrospective 2024-12-15
Present:
* Shelby Grasso (Product Owner)
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka (Scrum Master)

Used Plus-Delta Retrospective with [MIRO board](https://miro.com/app/board/uXjVL2mbVi4=/)

Plus note groups:
* Aesthetics achieved (4)
* Got work done (5)
* BDD implemented (2)
* CI/CD works well (1)
* More efficient (6)

Delta note groups:
* Project is incomplete (6)
* Steep learning curve (1)

Given that main delta is the project being incomplete and lack of time to work on it going forward, added new backlog item [SHY-80](https://evilore.atlassian.net/browse/SHY-80) around streamlining the sprint planning and ensuring that item selection takes that into account. 

### Screenshots
* [Pluses and Deltas filled in](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-15%20171537.png)
* [Notes grouped together](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-15%20172217.png)


## Sprint Review Practice
First pass: split demo between devs, went through basics of talk  
Had final rehearsal on Monday 2024-12-16

### Present
* Shelby Grasso
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka

### Screenshots
* [Initial review: Sample slide #1](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-15%20172547.png)
* [Initial review: Sample slide #2](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-15%20172605.png)
* [Final rehearsal: Shelby covers intro](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-16%20143825.png)
* [Final rehearsal: Demo 1](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-16%20144027.png)
* [Final rehearsal: Demo 4](images/screenshots/Sprint_2024-12-16/Screenshot%202024-12-16%20144352.png)
