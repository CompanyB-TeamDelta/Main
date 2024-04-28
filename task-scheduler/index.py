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

monitored_window_days = 1


def lambda_handler(event, context):
    conn = pymysql.connect(host=db_host, user=db_user, passwd=db_password, db=db_name, connect_timeout=5)

    with conn.cursor() as cursor:
        cursor.execute("select value from configs where name = 'polling_interval_hours'")
        interval_hours = int(cursor.fetchone()[0])

        current_datetime = datetime.now(timezone.utc)
        interval_start = current_datetime - timedelta(hours=interval_hours)
        monitored_window_start = current_datetime - timedelta(days=monitored_window_days)

        cursor.execute('select username, live_monitored from channel_subscriptions where active = true')
        subscription_rows = cursor.fetchall()

        for row in subscription_rows:
            telegram_channel_id = row[0]
            is_live_monitored = bool(row[1])

            message_body = json.dumps({
                'telegram_channel_id': telegram_channel_id,
                'from': min(interval_start, monitored_window_start).isoformat(),
                'to': current_datetime.isoformat(),
                'is_backfill': False,
                'is_live_monitored': is_live_monitored
            })

            response = sqs.send_message(
                QueueUrl=queue_url,
                MessageBody=message_body)

            print(f"Message sent with ID: {response['MessageId']}")
