
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

| EmpId | ProjectID |  DateFrom  |   DateTo   |
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