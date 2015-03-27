-- name: task-fetch
-- Get a single task
SELECT *
FROM task
WHERE id = :id

-- name: task-fetch-all
-- Get all tasks
SELECT *
FROM task

-- name: task-create<!
-- Create a new task
INSERT INTO task
(title, completed, created_at, updated_at)
VALUES
(:title, :completed, :created_at, :updated_at)

-- name: task-delete!
-- delete a task
DELETE FROM task
WHERE id = :id
