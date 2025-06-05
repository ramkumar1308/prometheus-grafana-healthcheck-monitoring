from prometheus_client import start_http_server, Gauge
import random
import time

cpu_usage = Gauge('custom_cpu_usage', 'Custom CPU usage metric')

def collect_metrics():
    while True:
        cpu_usage.set(random.uniform(10.0, 90.0))
        time.sleep(5)

if __name__ == "__main__":
    start_http_server(8000)
    collect_metrics()