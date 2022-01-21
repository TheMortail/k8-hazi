##Zookeper
    #Install
        helm install -n erdosgyorgy-sandbox zookeeper -f zookeeper.yaml bitnami/zookeeper

##Kafka
    #Install
        helm install -n erdosgyorgy-sandbox kafka -f kafka.yaml bitnami/kafka

    #Create topic
        kubectl run kafka-client --restart='Never' --image docker.io/bitnami/kafka:2.8.1-debian-10-r31 --namespace erdosgyorgy-sandbox --command -- sleep infinity
        kubectl exec --tty -i kafka-client --namespace erdosgyorgy-sandbox -- bash
        cd opt/bitnami/kafka/bin
        kafka-topics.sh --create --zookeeper kafka-zookeeper:2181 --topic hazi --partitions 1 --replication-factor 1
        //!delete the kafka-client pod afterward!\\

##Cassandra
    #Install
            helm install -n erdosgyorgy-sandbox cassandra -f cassandra.yaml bitnami/cassandra

    #Create keyspace
            //Cluster Explorer -> Pod -> Shell:
    		cqlsh -u cassandra -p $CASSANDRA_PASSWORD cassandra
            CREATE KEYSPACE hazi WITH replication = {'class':'SimpleStrategy', 'replication_factor': 1};

##Backend/Frontend
    #Build and push image files
        docker build -t registry.k8s.dev.pnt.hu/gyuri-hazi-backend ./backend
        docker push registry.k8s.dev.pnt.hu/gyuri-hazi-backend
        docker build -t registry.k8s.dev.pnt.hu/gyuri-hazi-frontend ./frontend
        docker push registry.k8s.dev.pnt.hu/gyuri-hazi-frontend

    #Apply yaml files
        kubectl apply -f ./backend/deployment.yaml
        kubectl apply -f ./frontend/deployment.yaml




##Usefull things
    #In case a pod is stuck in Terminated state
        kubectl delete -n erdosgyorgy-sandbox pods/[pod name] --grace-period=0 --force
