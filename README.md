# Prometheus-Grafana Healthcheck Monitoring

This project provides a full setup for monitoring a Java application and system metrics using Prometheus and Grafana.

## 🚀 Features
- Java app metrics exposed via Micrometer + Spring Boot Actuator
- Prometheus setup to scrape custom and system metrics
- Sample custom Python exporter simulating CPU metrics
- Alert rules for high CPU usage
- Grafana dashboard with CPU visualization
- Docker Compose setup for local deployment

## 🧪 Stack
- Java (Spring Boot + Micrometer)
- Prometheus
- Grafana
- Python (custom exporter)
- Docker Compose

## 🧰 Setup Instructions

### 1. Start Monitoring Stack
```bash
docker-compose up -d
```

### 2. Access Services
- Prometheus: [http://localhost:9090](http://localhost:9090)
- Grafana: [http://localhost:3000](http://localhost:3000)
  - Default credentials: `admin` / `admin`

### 3. Import Dashboard
Upload `grafana/dashboards/health_dashboard.json` into Grafana.

## 📦 Endpoints
- Java App: `localhost:8080/actuator/prometheus`
- Custom Exporter: `localhost:8000/metrics`

## 📊 Alerts
Alerts are defined in `alerts/alert_rules.yml` and loaded by Prometheus on startup.

## 📁 File Structure
```
.
├── alerts/
├── exporters/
├── grafana/
│   └── dashboards/
├── java-app/
├── prometheus/
└── docker-compose.yml
```

## 📌 Next Steps
- Add email/Slack alert integrations
- Expand Grafana dashboard for memory, disk, and service uptime

---

This setup is ideal for showcasing SRE monitoring expertise in interviews and real-world automation scenarios.