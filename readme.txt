##Zookeper
    #Install
        helm install -n erdosgyorgy-sandbox zookeeper -f zookeeper.yaml bitnami/zookeeper

##Kafka
    #Install
        helm install -n erdosgyorgy-sandbox kafka -f kafka.yaml bitnami/kafka

    #Create topic
        //Cluster Explorer -> Pod -> Shell:
        cd opt/bitnami/kafka/bin
        kafka-topics.sh --create --zookeeper zookeeper:2181 --topic hazi --partitions 1 --replication-factor 1

##Cassandra
    #Install
            helm install -n erdosgyorgy-sandbox cassandra -f cassandra.yaml bitnami/cassandra
            //Wait until the initialization finishes!

    #Create keyspace
            //Cluster Explorer -> Pod -> Shell:
    		cqlsh -u cassandra -p cassandra cassandra
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

##Ingress
    On rancher -> Load Balancing -> new rule (ingress.jpg, ingress2.jpg for reference)

##Usefull things
    #In case a pod is stuck in Terminated state
        kubectl delete -n erdosgyorgy-sandbox pods/[pod name] --grace-period=0 --force

    #In case npm/yarn doesn't want to build after a network change
        sudo systemctl restart docker