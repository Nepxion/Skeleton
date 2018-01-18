echo 'on'
echo '============================================================='
echo '$                                                           $'
echo '$                      Nepxion Skeleton                     $'
echo '$                                                           $'
echo '$                                                           $'
echo '$                                                           $'
echo '$  Nepxion Studio All Right Reserved                        $'
echo '$  Copyright (C) 2017-2050                                  $'
echo '$                                                           $'
echo '============================================================='
echo '.'
echo 'off'

title=Nepxion Skeleton
color=0a

REGISTRY_URL=registry.cn-hangzhou.aliyuncs.com
REPOSITORY_NAME=nepxion/skeleton
USER_NAME=nepxion
IMAGE_NAME=skeleton-service
IMAGE_VERSION=latest

# Please input password of username=${USER_NAME} for ${REGISTRY_URL}:
docker login --username=${USER_NAME} ${REGISTRY_URL}
docker rmi ${REGISTRY_URL}/${REPOSITORY_NAME}:${IMAGE_VERSION}
docker tag ${IMAGE_NAME}:${IMAGE_VERSION} ${REGISTRY_URL}/${REPOSITORY_NAME}:${IMAGE_VERSION}
docker push ${REGISTRY_URL}/${REPOSITORY_NAME}:${IMAGE_VERSION}