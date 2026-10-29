import os

import requests
from slack_sdk import WebClient

SLACK_API = "https://slack.com/api"
TOKEN = os.environ.get("SLACK_BOT_TOKEN", "")
client = WebClient(token=TOKEN)


def _get(method, **params):
    response = requests.get(f"{SLACK_API}/{method}", params=params, headers={"Authorization": f"Bearer {TOKEN}"})
    return response.json()


def channel_id(name):
    for channel in _get("channels.list", exclude_archived=True)["channels"]:  # expect: slack-channels-retired
        if channel["name"] == name:
            return channel["id"]
    return None


def recent_messages(channel):
    return client.channels_history(channel=channel)["messages"]  # expect: slack-channels-retired


def archive(channel):
    url = f"{SLACK_API}/channels.history"  # expect: slack-channels-retired
    return requests.get(url, params={"channel": channel}, headers={"Authorization": f"Bearer {TOKEN}"}).json()


def post(channel, text):
    return client.chat_postMessage(channel=channel, text=text)["ok"]
