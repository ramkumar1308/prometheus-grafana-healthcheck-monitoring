FROM python:3.11-slim

# Set working directory inside the container
WORKDIR /app

# Copy the exporter script into the container
COPY custom_exporter.py .

# Install any Python dependencies here if needed
# For a typical Prometheus exporter using prometheus_client:
RUN pip install prometheus_client

# Expose the port your exporter listens on
EXPOSE 8000

# Default command to run the exporter
CMD ["python", "custom_exporter.py"]

