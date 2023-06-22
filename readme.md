1. Run mvn clean
2. Run mvn package
3. Run docker build -t petclinic-mysql-minikube:1.0.0 .
4. Run docker tag petclinic-mysql-minikube:1.0.0 jtan22/petclinic-mysql-minikube:1.0.0
5. Run docker push jtan22/petclinic-mysql-minikube:1.0.0
6. Create mysql-config.yaml
7. Create mysql-secret.yaml, run echo -n 'petclinic' | base64, in a linux terminal to generate user and pwd.
8. Create mysql.yaml
9. Create app.yaml, make sure service/spec/type is NodePort, and service/spec/ports/nodePort is 30100
10. Run minikube start
11. Run kubectl get all, to check minikube status
12. Run kubectl apply -f mysql-config.yaml
13. Run kubectl get configMap, to check the mysql-config has been applied
14. Run kubectl describe configMap mysql-config, to check the key-value pairs
15. Run kubectl apply -f mysql-secret.yaml
16. Run kubectl get secret, to check the mysql-secret has been applied
17. Run kubectl describe secret mysql-secret, to check the key-value pairs
18. Run kubectl apply -f mysql.yaml
19. Run kubectl get all, to check if pod/mysql-deployment is running, and the service/mysql-service is ClusterIP
20. Run kubectl logs -f <MYSQL POD NAME>
21. Run kubectl apply -f app.yaml
22. Run kubectl get all, to check if pod/petclinic-deployment is running, and the service/petclinic-service is NodePort
23. Run kubectl logs -f <PETCLINIC POD NAME>
24. Open another command line, run minikube service petclinic-service
25. Run kubectl get service
26. Run kubectl get pod
27. Run kubectl delete deployment petclinic-deployment
28. Run kubectl delete service petclinic-service
29. Run kubectl delete deployment mysql-deployment
30. Run kubectl delete service mysql-service
31. Run kubectl delete secret mysql-secret
32. Run kubectl delete configMap mysql-config
33. Run minikube stop