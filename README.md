📘 API Monitoring & Observability Platform

A complete system to track API performance across microservices using Spring Boot (Kotlin), Next.js, and MongoDB.

✔ Features

API request logging (latency, status, sizes, endpoints)

Per-service rate limiting + rate-limit hit logs

Dual MongoDB setup (logs DB + metadata DB)

Alerts for slow APIs, 5xx errors, rate-limit violations

Dashboard with filters, metrics, and incidents

JWT Authentication

🏗 Architecture (Short)
1. Tracking Client (Spring Boot + Kotlin)

Interceptor added to any microservice → logs every API request → sends logs to collector.
Also detects rate-limit hits.

2. Collector Service (Spring Boot + Kotlin)

Stores logs, rate-limit events, alerts, and incident metadata using two MongoDB databases.

3. Dashboard (Next.js)

Displays API logs, rate-limit events, alerts, and incidents.

Collections Used

api_logs

rate_limit_logs

incidents

alerts

users

rate_limit_configs

🔐 Test Users
Username	Password
test1@gmail.com
	Test1!
test2@gmail.com
	Test2!
test3@gmail.com
	Test3!
▶️ How to Run the Project
1️⃣ Run the Collector Service (Gradle – Kotlin Backend)
./gradlew bootRun


Runs on:
👉 http://localhost:8080

Make sure MongoDB is running (or Mongo Atlas credentials are set in application.yaml).

2️⃣ Run the Demo App (Test Microservice – Maven)

This is the small Spring Boot project you use to generate logs.

./mvnw spring-boot:run


Runs on something like:
👉 http://localhost:9090
 (or your configured port)

Any request sent to this demo app → interceptor logs → logs appear in collector DB and dashboard.

3️⃣ Run the Dashboard (Next.js Frontend)
npm install
npm run dev


Runs on:
👉 http://localhost:3000

🧪 Test Rate Limit Behavior

If your demo app's rate limit = 5 req/sec:

Send 20 parallel requests:

for i in {1..20}; do
  curl -s http://localhost:9090/api/test & 
done
wait


You should now see:

New entries in api_logs

New entries in rate_limit_logs

Alerts generated for violations

📁 Project Structure (Short)
collector-service/      ← Kotlin + Spring Boot + Gradle
demo-app/               ← Spring Boot + Maven (test microservice)
dashboard/              ← Next.js frontend
