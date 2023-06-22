Run mvn clean
Run mvn package
Run docker build -t petclinic-mysql-minikube:1.0.0 .
Run docker tag petclinic-mysql-minikube:1.0.0 jtan22/petclinic-mysql-minikube:1.0.0
Run docker push jtan22/petclinic-mysql-minikube:1.0.0
Create mysql-config.yaml
Create mysql-secret.yaml, run echo -n 'petclinic' | base64, in a linux terminal to generate user and pwd.
Create mysql.yaml
Create app.yaml, make sure service/spec/type is NodePort, and service/spec/ports/nodePort is 30100
Run minikube start
Run kubectl get all, to check minikube status
Run kubectl apply -f mysql-config.yaml
Run kubectl get configMap, to check the mysql-config has been applied
Run kubectl describe configMap mysql-config, to check the key-value pairs
Run kubectl apply -f mysql-secret.yaml
Run kubectl get secret, to check the mysql-secret has been applied
Run kubectl describe secret mysql-secret, to check the key-value pairs
Run kubectl apply -f mysql.yaml
Run kubectl get all, to check if pod/mysql-deployment is running, and the service/mysql-service is ClusterIP
Run kubectl logs -f <MYSQL POD NAME>
Run kubectl apply -f app.yaml
Run kubectl get all, to check if pod/petclinic-deployment is running, and the service/petclinic-service is NodePort
Run kubectl logs -f <PETCLINIC POD NAME>
Open another command line, run minikube service petclinic-service
Run kubectl get service
Run kubectl get pod
Run kubectl delete deployment petclinic-deployment
Run kubectl delete service petclinic-service
Run kubectl delete deployment mysql-deployment
Run kubectl delete service mysql-service
Run kubectl delete secret mysql-secret
Run kubectl delete configMap mysql-config
Run minikube stop