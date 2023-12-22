
# Documentation of my project

## Table of contents

- [Overview of the issue](#overview-of-the-issue)
  - [Task](#task)
  - [Input data](#input-data)
  - [Specific requirements](#specific-requirements)
  - [Bonus](#bonus)
  - [Delivery](#delivery)
- [My understanding of the assignment](#my-understanding-of-the-assignment)
- [My approaching on the issue](#my-approach-on-the-issue)
  - [Additional information](#additional-information)
- [Algorithms](#algorithms)
  - [Main issue algorithm](#main-issue-algorithm)
  - [CRUD for employees (The bonus)](#crud-for-employees-the-bonus)
    - [Create algorithm](#create-algorithm)
    - [Read algorithm](#read-algorithm)
    - [Update algorithm](#update-algorithm)
    - [Delete algorithm](#delete-algorithm)

# Overview of the issue

## Task

Create an application that identifies the `pair of employees` who have worked together on `common projects` for the `longest period` of time and the `time for each of those projects`.

## Input data

CSV file with the following format:

**EmpID, ProjectID, DateFrom, DateTo**

### Sample input

143, 12, 2013-11-01, 2014-01-05

218, 10, 2012-05-16, NULL

143, 10, 2009-01-01, 2011-04-27

...

| EmpID | ProjectID |  DateFrom  |   DateTo   |
|:-----:|:---------:|:----------:|:----------:|
|  143  |    12     | 2013-11-01 | 2014-01-05 |
|  218  |    10     | 2012-05-16 |    NULL    |
|  143  |    10     | 2009-01-01 | 2011-04-27 |
|  ...  |    ...    |    ...     |    ...     |

### Sample output

143, 218, 8

10, 5

12, 3

## Specific requirements

- DateTo can be `NULL`, equivalent to today
- We are interested in the number of `days` they have worked together
- The input data must be loaded to the program from a `CSV file`
- More than one date format to be supported, `extra points` will be given if `all date formats` are supported
- In a `README.md` file summarize your `understanding` for the task and your `algorithm`
- **Do not use** external libraries for CSV parsing
- Follow `clean code` conventions

## Bonus

- Implement `persistence` of the data
- Implement `CRUD` for Employees

## Delivery

- The task solution needs to be uploaded in `GitHub`

# My understanding of the assignment

The task's initial description is pretty straightforward: There is a CSV file which stores records in certain format, providing the needed data to extract what the sample output shows.

From what I see, the sample output's first line is:

- First employee's ID
- Second employee's ID
- Total days they worked together

The second line:

- First project's ID the two employees worked on
- Days they worked on it together

Next lines (if any) repeat the second line's pattern and then the days from each line must sum to the total days in the first line

If more than one date format must be supported, it would be best to make some kind of date parser during runtime since storing the dates in different formats would be inconsistent and confusing. I think showing the date formats options for the user to chose on the startup menu of the program is a reasonable and valid approach.

# My approach on the issue
I am choosing to develop my project as a console app, the easiest approach for me, since I was busy with other projects and some personal work, so I did not have enough time to learn, grasp and practice the SQL database and it's functionalities after the initial introduction we were studying during the course.

I am using ASCII symbols combined with some functionalities such as StringBuilder to achieve menu interfaces.

## Additional information
In the Main class I keep four public fields which are used throughout the whole app
- dateParser (For the custom date format)
- employees arraylist
- lines arraylist (For saving changes to the data file)
- changesMade (Boolean that indicates whether there have been made changes to the data file content)

I used ANSI colors for better UI when printing different types of messages:
- Blue => when showing information (except menus)
- Green => when creating finishes successfully
- Yellow => when performing deleting actions or showing more important information

For error messages I used regular System err, since it is printed in red.
# Algorithms
## Main issue algorithm

The main issue of the task is to extract the two employees which have been working the longest. My algorithm for doing this involves these steps:
- Iterating with two for loops over the employees, the inner loop being one index higher to avoid unnecessary looping
- Getting the two employees based on the indexes from the loops
- Using function to calculate how many days they worked together
  - That is done with iterating using two loops over the two arrays of assigned projects which come from the two employees
  - Creating ArrayList to add each project and the day span which they worked together
  - Checking if the first loop's iterated assignment ID is the same as the second loop's
  - If true, checking if the range of starting and ending dates overlap
    - If starting range is before ending range, extract the days between the ranges, then adding the result to a variable totalDays since the extracted value is just for the current project, it is possible for multiple projects being assigned to the same two employees
    - Adding the result to the ArrayList of projects and days
  - Check if the total days of the current pair are more than the last recorded one
    - If true, the projects and days ArrayList is assigned to a field in the class
  - Check if the last recorded total days are less than the new record, if true assign new value
- Repeat until all employees have been iterated through
- Printing the result on the console in a formatted way as a menu, giving option to return to main menu which provides more functions

## CRUD for employees (The bonus)
### Create algorithm
This action is managed by several methods
- First, a random ID between 1 and 10000 is generated, while checking for existing IDs
- If it is not existing, a new employee is created via constructor
- After that immediately to the employee must be assigned at least one project, since an employee must have at least one assignment before saving to file
  - The assignment must have start and end date, both must be before or equal to today's date but end date must be either before or equal to the start date as well
  - The dates are checked by the formatter which user chose initially
  - After first assignment, the user will be asked if the assigning has been done or not, allowing adding more than one assignment before finishing creating the employee
    - While creating second, third... n assignments, validations are made ensuring that no project is assigned twice to the same employee
- After creating new employee, the record is added to the lines global field for updating the data file upon exiting

### Read algorithm
There are two ways of reading the employees
- The first way is reading all the employees, which happens by showing a menu with listed employees
  - This menu is created with the ArrayList employees, which is created when the CSVReader is performing the initial reading of the data file
- The second way is managed by first taking the index of wanted employee by submitting a number from the menu provided.

- It takes the employee and displays its ID as well as the project which are assigned to it
- In the details menu there are options to edit and delete the employee

### Update algorithm
This action is managed by multiple methods and has more than one way of updating the employee
- The first way is by adding more assignments using the same method when assigning projects to new employees
- The second way is by editing a project assignment from all the assignments of the employee
  - This is achieved by displaying a menu with all the assignments and the user can choose a number corresponding to the index of the assignment
  - This will show another menu with details for the assignment, displaying project ID, start and end dates
    - In this menu there are options of editing the start and end dates, which are using the same methods as when creating new employee and assigning projects while validating the dates
- The third way is by deleting an assignment from the employee, which is accessible only when there are two or more assignments, since the minimum allowed is one and deleting it will result wrong data being present in the data file
  - This is achieved by again displaying a list of all assignments and when selecting one based on number, a message will ask if the user really wants to delete the assignment, which is cancelled or confirmed based on the choice

All these changes will edit the lines stored in the entry point allowing to save the changes upon exiting.

### Delete algorithm
This can be performed when the user has opened the details menu about a certain employee
- It displays a message asking for confirmation to delete the employee
- Based on choice it will be cancelled or confirmed
- Before the employee is deleted, a loop iterates over the lines, so it can clear all the assignments which have a parameter empID same as the employee's ID
- When done, the employee is removed from the employees ArrayList and the lines are updated

In almost all menus there is the "0. Go back" option as the first one, so the user can navigate back to the previous menu since they are nested based on the choices made

In the main menu, when "0" is chosen, the application will save the changes with the CSVWriter and after successful saving it will close.