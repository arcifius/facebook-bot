# Facebook Post Manager

## Overview
This project tracks an eadbox (https://eadbox.com) school looking for new courses. Every new course is promoted on Facebook and you can choose how it should look, it is possible to include support for multiple schools tracking with minor changes.

## Architecture
The project relies on interfaces and depends on a MongoDB instance.
- ### Interfaces
  **IFetcher**: Network resources. Define how network resources should be acquired.  
  **IState**: State management. Define how the project should handle state.  
  **IPublisher**: Post logics. It is possible to add support for other social networks.  
  **IAction**: Job actions. The scheduler will dispatch jobs and jobs will dispatch actions.  
  
 - ### MongoDB
    The current implementation uses a MongoDB instance to store/retrieve school with courses. If you wish to use another mechanism to manage state you will need to implement the IState interface.
 
 - ### Scheduler
    The project is using sundial scheduler (https://github.com/knowm/Sundial), based on a cron rule it will dispatch a job to verify new courses; when new courses were found another job will be scheduled to handle Facebook promotion.
    
## Configuration
You can configure the project through env vars or through an .env file that must be placed in the root folder of the project.
  ### FACEBOOK SETTINGS
   **FBBOT_FB_PAGE_ID**  
   **FBBOT_FB_PAGE_TOKEN**  
   **FBBOT_FB_PUBLICATION_TYPE** (`TEXT_ONLY`, `IMAGE_ONLY`, `TEXT_AND_IMAGE`) defaults to `TEXT_AND_IMAGE`  
   **FBBOT_FB_TEXT_TEMPLATE** (defaults to `Confira nosso novo curso: %NEW_COURSE_TITLE%`)  
   Note that some words will be replaced for you `%NUMBER_OF_COURSES%`, `%NEW_COURSE_TITLE%`, `%SCHOOL_NAME%`
  
  ### ROBOT SETTINGS
  **FBBOT_ROBOT_MODE** (defaults to `POOLING`)  
  You dont need to define this as, currently, only `POOLING` is supported.
  
  ### DATABASE SETTINGS
  **FBBOT_DATABASE_URI** (defaults to `mongodb://localhost:27017`)  
  
  ### SCHEDULER
  **FBBOT_SCHEDULER_SCHOOL**  
  **FBBOT_SCHEDULER_INTERVAL** (default is `0/60 * * * * ?`)  
  
 ## Usage
 Pending
  
