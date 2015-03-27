# A Simple API server in Clams

A simple To Do api server.

We use a large variety of tools in the app, centered around the Clams
library.

## Description

This is a dead simple REST server for a list of To Do tasks.

The REST interface is:

- `GET /api/v1/tasks`          Return a list of all the tasks
- `GET /api/v1/tasks/:id`      Get a single task
- `POST /api/v1/tasks`         Create a new task
- `PUT /api/v1/tasks/:id`      Update a task
- `DELETE /api/v1/tasks/:id`   Permanently delete a task.

For example:

```
  curl -H "Content-Type: application/json" \
       -d '{"task":{"title":"My Task"}}'   \
       http://localhost:4321/api/v1/tasks
```

Gives

```json
{
  "task": {
    "updated_at": "2015-03-26T23:56:29Z",
    "created_at": "2015-03-26T23:56:29Z",
    "completed": false,
    "title": "My Task",
    "id": 100006
  }
}
```

To fetch an existing task:

```
curl http://localhost:4321/api/v1/tasks/100006
```

To get the same result as above.


# Prerequisites

tl;dr Run `make init`.

# Running

`make run`

# Testing

`make test`

# Authors

- Chris Dean <ctdean@standardtreasury.com>
