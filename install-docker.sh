echo 'on'
echo '============================================================='
echo '$                                                           $'
echo '$                      Nepxion Skeleton                     $'
echo '$                                                           $'
echo '$                                                           $'
echo '$                                                           $'
echo '$  Nepxion Technologies All Right Reserved                  $'
echo '$  Copyright(C) 2017                                        $'
echo '$                                                           $'
echo '============================================================='
echo '.'
echo 'off'

set title=Nepxion Skeleton
set color=0a

set PROJECT_NAME=skeleton-spring-cloud
set PROJECT_LIST=skeleton-engine,%PROJECT_NAME%

set DOCKER_HOST=tcp://localhost:2375
echo set DOCKER_CERT_PATH=C:\Users\Neptune\.docker\machine\certs
set IMAGE_NAME=skeleton-spring-cloud
set MACHINE_PORT=2222
set CONTAINER_PORT=2222

if [ ! -d %PROJECT_NAME%\target];then
rmdir /s/q %PROJECT_NAME%\target
fi

echo '执行相关模块的Maven Install'
mvn clean install -DskipTests -pl %PROJECT_LIST% -am

echo '停止和删除Docker容器'
docker stop %IMAGE_NAME%
echo docker kill %IMAGE_NAME%
docker rm %IMAGE_NAME%

echo '删除Docker镜像'
docker rmi %IMAGE_NAME%

cd %PROJECT_NAME%

echo '安装Docker镜像'
mvn package docker:build -DskipTests

echo '安装和启动Docker容器，并自动执行端口映射'
docker run -i -t -p %MACHINE_PORT%:%CONTAINER_PORT% --name %IMAGE_NAME% %IMAGE_NAME%:latest
