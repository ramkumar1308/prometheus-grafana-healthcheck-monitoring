# 📡 Prometheus + Grafana Observability Lab  
### Spring Boot • Blackbox Exporter • Node Exporter • Custom Python Exporter


A complete, reproducible end-to-end observability stack using Docker Compose

⭐ Overview

This project is a fully working observability lab that demonstrates how to monitor:

Application metrics (Spring Boot Actuator + Micrometer Prometheus registry)

Infrastructure/system metrics (Node Exporter)

Synthetic monitoring / uptime checks (Blackbox Exporter)

Custom business logic metrics (Python-based exporter)

Dashboards & visualization (Grafana)

Time-series storage & scraping orchestration (Prometheus)

Everything runs locally via Docker Compose and is configured to work together out-of-the-box.

This stack is intentionally designed the way an SRE team would structure a production observability pipeline.
```
                     ┌────────────────────────────┐
                     │           Grafana           │
                     │  Dashboards & Visualization │
                     └───────────────┬────────────┘
                                     │
                                     ▼
                        ┌────────────────────────┐
                        │       Prometheus       │
                        │     Scrape + Storage   │
                        └───────┬────────┬───────┘
                                │        │
               ┌────────────────┘        └─────────────────┐
               ▼                                            ▼
        ┌──────────────┐                           ┌────────────────┐
        │   java-app    │  ← Spring Boot Actuator  │   blackbox      │ ← Synthetic checks
        │ /actuator/*   │  ← Micrometer metrics    │   exporter      │
        └──────────────┘                           └────────────────┘

        ┌────────────────┐                        ┌─────────────────────┐
        │ node-exporter  │ ← System metrics       │   custom_exporter    │ ← Python metrics
        └────────────────┘                        └─────────────────────┘

```

🚀 Features
1. Java Spring Boot Application Metrics

/actuator/health

/actuator/prometheus

Tagged metrics using Micrometer

Auto-discovered by Prometheus

2. Synthetic Monitoring (Blackbox Exporter)

Probes Java app health endpoint

Measures uptime + latency

Configurable modules (HTTP, TCP, DNS)

3. Node Exporter

CPU

Memory

Filesystem

Load averages

4. Custom Python Exporter

A small example exporter showing how to expose custom Prometheus metrics for domain logic.

5. Grafana Dashboards

Grafana runs at:

http://localhost:3000


(Default credentials: admin / admin)

🐳 Quick Start (Docker Compose)
1. Clone the repository
git clone https://github.com/ramkumar1308/prometheus-grafana-healthcheck-monitoring.git
cd prometheus-grafana-healthcheck-monitoring

2. Start the full stack
docker compose up -d --build

3. Access the components
Service	URL
Prometheus	http://localhost:9090

Grafana	http://localhost:3000

Spring Boot App	http://localhost:8080/actuator

Node Exporter	http://localhost:9100/metrics

Blackbox Exporter	http://localhost:9115/probe
4. Verify targets in Prometheus

Go to:
Prometheus → Status → Targets
All jobs should show UP:

java-app

node-exporter

blackbox

custom-exporter

prometheus

📁 Project Structure
.
├── docker-compose.yml
├── grafana/
│   └── (Grafana data storage)
├── java-app/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/com/example/HealthCheckApplication.java
├── prometheus/
│   ├── prometheus.yml
│   └── alert_rules.yml
├── exporters/
│   ├── custom_exporter.py
│   └── blackbox/
│       └── blackbox.yml
└── README.md

🔧 Prometheus Jobs Included
java-app
- job_name: 'java-app'
  metrics_path: /actuator/prometheus
  static_configs:
    - targets: ['java-app:8080']

blackbox exporter
- job_name: 'blackbox'
  metrics_path: /probe
  params:
    module: [http_2xx]
  static_configs:
    - targets:
        - http://java-app:8080/actuator/health
  relabel_configs:
    - source_labels: [__address__]
      target_label: __param_target
    - source_labels: [__param_target]
      target_label: instance
    - target_label: __address__
      replacement: blackbox-exporter:9115

node exporter
- job_name: 'node-exporter'
  static_configs:
    - targets: ['node-exporter:9100']

custom exporter
- job_name: 'custom-exporter'
  static_configs:
    - targets: ['custom_exporter:8000']

📊 Suggested Grafana Panels

You can build dashboards using:

Java App Health
probe_success{job="blackbox"}

Java App Latency
probe_duration_seconds{job="blackbox"}

JVM Memory (from Spring)
jvm_memory_used_bytes

Node CPU
node_cpu_seconds_total

Custom Python Metric
my_custom_metric

🧪 Testing the Java App Endpoints
curl http://localhost:8080/actuator/health
curl http://localhost:8080/actuator/prometheus

🤝 Contributing

This repository is meant as a learning and demonstration project.
PRs, improvements, or new exporters are welcome.

📜 License

MIT License.
