docker build -t registry.k8s.dev.pnt.hu/gyuri-hazi-backend ./backend
docker push registry.k8s.dev.pnt.hu/gyuri-hazi-backend

docker build -t registry.k8s.dev.pnt.hu/gyuri-hazi-frontend ./frontend
docker push registry.k8s.dev.pnt.hu/gyuri-hazi-frontend

kubectl apply -f ./backend/deployment.yaml
kubectl apply -f ./frontend/deployment.yaml
