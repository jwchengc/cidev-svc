IMAGE_PREFIX = registry.isspaas.com/cityinsight/
COMPONENT = cidev-svc
ifndef BUILD_TAG
  BUILD_TAG = latest
endif
IMAGE = $(IMAGE_PREFIX)$(COMPONENT):$(BUILD_TAG)
ifndef KUBE_OPS
  KUBE_OPS = --server=http://10.16.117.160:8080 --namespace=cityinsight
endif

clean:
	mvn clean

compile: clean
	mvn -U -DskipTests=true -Dmaven.javadoc.skip=true deploy

exec:
	mvn --offline -f $(COMPONENT)-provider/pom.xml exec:java -Dexec.mainClass=com.alibaba.dubbo.container.Main -Dexec.args=javaconfig

build:
	docker build -t $(IMAGE) .

push:
	docker push $(IMAGE)

run:
	docker run --rm -it -e spring.profiles.active=application -e DUBBO_REGISTRY_ADDRESS=kube://10.16.117.20:2181 -e DUBBO_REGISTRY_REGISTER=true -e DUBBO_PROTOCOL_KUBE_HOST=10.16.113.42 -e DUBBO_PROTOCOL_KUBE_PORT=18888 -p 18888:8888 $(IMAGE)

deploy:
	kubectl create -f kube-rc.yaml $(KUBE_OPS)

redeploy:
	kubectl replace -f kube-rc.yaml $(KUBE_OPS)

undeploy:
	kubectl delete -f kube-rc.yaml $(KUBE_OPS)

status: 
	kubectl describe rc/$(COMPONENT) $(KUBE_OPS)
	kubectl describe pod -l name=$(COMPONENT) $(KUBE_OPS)

logs: 
	kubectl logs -f $$(kubectl get pod -l name=$(COMPONENT) $(KUBE_OPS) | awk 'NR==2 {print $$1}') $(KUBE_OPS)

deploy-svc:
	kubectl create -f kube-svc.yaml $(KUBE_OPS)

undeploy-svc:
	kubectl delete -f kube-svc.yaml $(KUBE_OPS)

