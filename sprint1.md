# Sprint: 2024-11-17 Sprint 1

Product website:  
[https://shycat.fyi/](https://shycat.fyi/)

We used ChatGPT in this project to assist with project setup and debugging and to generate mock event data.  All code other than mock event data was either based on examples from the [DaisyUI Components](https://daisyui.com/components) or hand-written during the programming sessions.

## Sprint Planning 2024-11-04

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
* [Image at start of mob programming 2024-11-13](images/screenshots/Sprint_2024-11-17/Burndown%20screenshot%202024-11-13%20191412.png)
* [Image after completing SHY-11](images/screenshots/Sprint_2024-11-17/Burndown%20screenshot%202024-11-15%20140611.png)

## Daily Scrums

### Daily Scrum 2024-11-10

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
**Impediments**
* **Shelby:** Scheduling!
* **Hamza:** No impediments
* **Yu:** Yes - ran into issues with bugs when practicing unit test creation.  Also, how will team do mob programming technically?  Used PyCharm in class, but can’t use this for our project.
* **Christina:** Yes - no knowledge/experience with React.
* **Tyler:** No impediments

**Impediment Removal Plan**
* Team will try to have standard weekly day to meet - Wednesday.
* Team will use VSCode LiveShare to do mob programming.  Christina will install VSCode and register.
* Yu will work on debugging her trial run unit tests and watch tutorials to learn more.
* Christina will read up on React and try to practice it.

Discussed considering meeting task complete, okayed by Shelby as Product Owner so it was moved to Done.  
[Screenshot of product owner okay](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-10%20112828%20SHY-14%20complete.png)


## Group Programming Sessions

### Session 2024-11-08
**Goal:** Work on SHY-3 child issues SHY-15 (Provision AWS Amplify) and SHY-16 (Provision AWS Route53).

#### Present
* Shelby Grasso
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka (joined partway through)

#### Screenshots
* [Working on Terraform code, initial configuration written by Hamza as starting point](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-08%20191025%20AWS%20Terraform.png)
* [Debugging error when running terraform apply](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-08%20193335%20Terraform%20apply%20error.png)
* [Successfully deployed to AWS Amplify](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-08%20201424%20deployed%20to%20AWS%20Amplify.png)
* [Working on adding custom shycat.fyi domain](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-08%20203019%20custom%20shycat%20domain.png)

### Session 2024-11-10
**Goal:** Work on SHY-3 child issues SHY-17 (Minimal front end landing page), SHY-19 (Unit tests), SHY-20 (Check web access)

#### Present
* Shelby Grasso
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka

We used pre-generated code from https://daisyui.com/components/navbar/ for the navbar component in our landing page, so we primarily spent the time in adding unit tests.

#### Screenshots
* [Adding the navbar component](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-10%20114113%20adding%20navbar.png)
* [Pasting in daisyui navbar](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-10%20114218%20pasting%20in%20daisui%20navbar.png)
* [Unit test failure](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-10%20114907%20unit%20test%20failed.png)
* [Adding unit tests](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-10%20115608%20adding%20unit%20tests.png)
* [Another unit test failure](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-10%20120312%20another%20test%20failed.png)
* [Adding more unit tests](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-10%20120705%20more%20unit%20tests.png)
* [A working SHYC&T website landing page](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-10%20123458%20working%20landing%20page.png)
* [Marking SHY-3 complete](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-10%20123512%20completing%20SHY-3.png)


## Sprint Review 2024-11-15
Present:
* Shelby Grasso (Product Owner)
* Yu Lai
* Christina Molodowitch
* Alexa Barry (Stakeholder)
* Tyler Malka (Scrum Master)

Recording link:  
https://harvard.zoom.us/rec/share/BijIgv10xUF_eo1HzybUW1RcIM6XRBM0QE8a9FNeIaOSVSYbu2QaGMtrxUb5kSYV.HpXTqvctFEsua1qi  
(Unfortunately we didn't think to start it until after the demo - see demo screenshots below.)

### Screenshots
* [Initial discussion](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-15%20141431%E2%80%AFsprint%20review.png)
* [Product demo with mock data](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-15%20141450%20product%20demo%201.png)
* [Product demo - responsive](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-15%20141941%20product%20demo%202.png)
* [Going through backlog items with stakeholder](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-15%20142110%20backlog%201.png)
* [Updating PBI order based on stakeholder feedback](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-15%20143643%20prioritizing%20backlog.png)
* [Adding new backlog item from stakeholder feedback](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-15%20143850%20new%20backlog%20item.png)

### Notes
* Starting with product vision as introduction, reminder of purpose.

#### Product Demo
* Demoed product to stakeholder at https://shycat.fyi
    * Header
    * Individual event cards - each card has image, title, summary, location, date, and tags
    * List view displaying all cards
    * (Note that mock event data was generated by ChatGPT)
* Stakeholder gave feedback on current product demo
    * "Beautiful"
    * "Having it be clean and simple is of utmost importance"
    * She noted that she's often trying to grapple with lots of info at the same time, so having a minimalistic but clear grid is very appealing, especially compared with a site like EventBrite which is visually overstimulating.
    * The way each block raises/moves when hovered over is "delightful".

#### Product Backlog Refinement
* Went over remaining PBIs, discussed each and got feedback from stakeholder on Product Backlog
    * Liked that the design was "thoughful about how people would move through it"
    * Thought limiting the usership to Harvard/MIT was a good idea
    * From her perspective, sorting/filtering gets to the core of the user experience, she wants to find specific events of interest
    * Question: for user-created events, what about user authentication?  (Current PBI ordering has user authentication
    below user-created events.)
        * Idea is that nominally the product wouldn't be "publicly released" until after user registration part is
        finished.
        * Could hypothetically have manual authentication for non-Harvard users (e.g. visiting professor) to create events.
* High priorities: Filtering, Sorting, and Favorite system.  
  * She would prefer to move Favorite system higher.  She wants to be able to organize and collect information to send it out, not typically looking for single events.  For example, she may have group of healthcare biotech founders looking for networking events across MIT/Harvard, wants to be able to collect relevant events and share them as a group.
  * Moved Filtering and Sorting before User page view, moved Favorite system before scraping.
* Added new backlog item: Sharing feature 
    * Stakeholder noted that she works across a lot of different systems/resources for users to
  connect people to, would like an easy way to post an event from shycat.fyi on other systems such as Slack or LinkedIn.
    * Priority high for her as director, because this would be an essential and differentiating feature for her - it's something that EventBrite doesn't do.
* Added new backlog item:  Location map
    * Stakeholder suggested that a map view of event locations would be helpful because there are potentially hundreds of possible locations.


## Sprint Retrospective 2024-11-15
Present:
* Shelby Grasso (Product Owner)
* Hamza Hashmi
* Yu Lai
* Christina Molodowitch
* Tyler Malka (Scrum Master)

Used Plus-Delta Retrospective with Miro board

Plus note groups:
* Accurate forecasting (5)
* Good backlog items (2)
* Stakeholder meeting went well (3)
* Working product (4)
* Nice front end design (2)
* Good teamwork (4)

Delta note groups:
* A little disorganized (1)
* Inefficient mob programming (5)

Focused on the mob programming delta - we all agreed it's not the most efficient way for us to build the product.  There are two competing priorities: practicing skills like mob programming (something we're less likely to use in other contexts) vs pushing forward with the product as quickly as possible.

Added [PBI SHY-29](https://evilore.atlassian.net/browse/SHY-29) to seek clarification from instructor on the purpose of the project and update our practice accordingly.  PBI moved to top of product backlog.

### Screenshots
* [Initial unorganized Plus-Delta notes](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-15%20181150%E2%80%AFplus-delta%20initial.png)
* [Grouped Plus-Delta notes](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-15%20182542%E2%80%AFplus-delta%20organized.png)
* [Added new PBI from retrospective discussion](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-15%20182834%E2%80%AFnew%20pbi.png)
* [Reordered product backlog to better reflect stakeholder priorities](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-15%20184542%E2%80%AFredordering%20backlog.png)
* [Sprint complete!](images/screenshots/Sprint_2024-11-17/Screenshot%202024-11-15%20184824%E2%80%AFsprint%20complete.png)
