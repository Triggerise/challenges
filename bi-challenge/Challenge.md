# Challenge

### Querying

Provide us the `SQL` query that would answer the following "questions" about the dataset:

1. Top 10 users with posts having a body larger than 400 characters.

2. Top 10 users with higher average creation time between their posts.


### For discussion

Imagine that we would need to count the number of post edits and store it as a column in the `posts` table.

Describe how you would approach this problem and how the calculation process could work.


### Segmentation

The challenge here is to segment the users, *by month*, based on the their activity (`posts` and `comments`) in each time frame.

The segmentation categories are:
  * Expert User:
    1. having made more than 5 posts having over 100 characters each
    2. having made at least 5 comments
  * Standard User:
    1. having made more than 2 posts having over 50 characters each
    2. having made at least 2 comments
  * Novice User:
    1. having made at least one post or comment

A user that does not meet the `Novice` requirements is an inactive user and does not need to be considered for this segmentation.

The expected output would be similar to a table like this:

| year  | month | user_id | segmentation_category |
| ----- |-------| --------|-----------------------|
| 2016  | 11    | 123     | Expert                |
| 2016  | 11    | 124     | Standard              |
| 2016  | 11    | 125     | Expert                |
| 2016  | 12    | 124     | Novice                |
|                    ...                          |

Provide us the `SQL` and/or `PL/SQL` code that allowed you to get to the table above.
