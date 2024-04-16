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