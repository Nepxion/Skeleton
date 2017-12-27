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

title=Nepxion Skeleton
color=0a

PROJECT_NAME=skeleton-spring-cloud
PROJECT_LIST=skeleton-engine,${PROJECT_NAME}

DOCKER_HOST=tcp://localhost:2375
# DOCKER_CERT_PATH=C:\Users\Neptune\.docker\machine\certs
IMAGE_NAME=skeleton-spring-cloud
MACHINE_PORT=2222
CONTAINER_PORT=2222

if [ ! -d ${PROJECT_NAME}\target];then
rmdir /s/q ${PROJECT_NAME}\target
fi

# 执行相关模块的Maven Install
mvn clean install -DskipTests -pl ${PROJECT_LIST} -am

# 停止和删除Docker容器
docker stop ${IMAGE_NAME}
# docker kill ${IMAGE_NAME}
docker rm ${IMAGE_NAME}

# 删除Docker镜像
docker rmi ${IMAGE_NAME}

cd ${PROJECT_NAME}

# 安装Docker镜像
mvn package docker:build -DskipTests

# 安装和启动Docker容器，并自动执行端口映射
docker run -i -t -p ${MACHINE_PORT}:${CONTAINER_PORT} --name ${IMAGE_NAME} ${IMAGE_NAME}:latest
