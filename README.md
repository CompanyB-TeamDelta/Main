# Main
Repository contents:
1. `data-processor` — service responsible for extracting metadata from fetched data
2. `task-scheduler` — lambda responsible for scheduling data fetching
3. `database/scripts` — database scripts
4. `telegram-module-api.yaml` — API documentation

## data-processor
How to run locally:
1. Fill in data-processor/src/main/resources/application.yml
2. Run `application/bootRun` gradle task
3. Service is ready on port 8080

## task-scheduler
Requires Python 3.8.0

How to run locally:
1. Install required dependencies:
```shell
pip install -r requirements.txt
```
2. Set up environment variables
3. Install package to run lambda locally:
```shell
pip install python-lambda-local
```
4. Create test `event.json`
5. Start the lambda:
```shell
python-lambda-local -f lambda_handler index.py event.json
```
