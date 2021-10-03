docker build -t suhenrys/tsp_api:latest .
docker push suhenrys/tsp_api:latest
kubectl delete deployment tsp_api
kubectl apply -f .\k8s\deployment.yaml