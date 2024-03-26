import boto3
import pymysql
import os
import json
from datetime import datetime, timedelta, timezone

sqs = boto3.client('sqs')
queue_url = os.environ['FETCHING_QUEUE_URL']

db_host = os.environ['DB_HOST']
db_user = os.environ['DB_USER']
db_password = os.environ['DB_PASSWORD']
db_name = os.environ['DB_NAME']

interval_hours = 6
monitored_window_days = 7


def lambda_handler(event, context):
    current_datetime = datetime.now(timezone.utc)
    interval_start = current_datetime - timedelta(hours=interval_hours)
    monitored_window_start = current_datetime - timedelta(days=monitored_window_days)

    conn = pymysql.connect(host=db_host, user=db_user, passwd=db_password, db=db_name, connect_timeout=5)

    with conn.cursor() as cursor:
        cursor.execute('select telegram_channel_id from channel_subscriptions where active = true')

        for row in cursor.fetchall():
            telegram_channel_id = row[0]

            message_body = json.dumps({
                'telegram_channel_id': telegram_channel_id,
                'from': min(interval_start, monitored_window_start).isoformat(),
                'to': current_datetime.isoformat(),
                'is_backfill': False
            })

            response = sqs.send_message(
                QueueUrl=queue_url,
                MessageBody=message_body)

            print(f"Message sent with ID: {response['MessageId']}")
