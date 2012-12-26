echo "cp kankantu.war to root.war"
cp target/kankantu.war target/root.war

echo "scp war to vps:69.85.92.97"
scp target/root.war root@69.85.92.97:~/tools/jetty/webapps

echo "OK"
