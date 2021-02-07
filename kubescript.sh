export KUBECONFIG=./kubeconfig
kubectl config view
kubectl get nodes -o wide
kubectl delete deployment rest-register --ignore-not-found=true
kubectl apply -f rest-spring.yaml
kubectl get services rest-register-lb
kubectl get pods